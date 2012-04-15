/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountChargeUserDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcUserCmnDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeUser;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeUserNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;
import com.biz_integral.crm.cc.domain.logic.CrmAccountChargeUserCustomLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountChargeUserLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.util.DateUtil;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.domain.master.BizConfigurationProvider;

/**
 * CrmAccountChargeUserLogicロジックの実装です。
 */
public class CrmAccountChargeUserLogicImpl implements CrmAccountChargeUserLogic {

    /**
     * CRMアカウント担当者に関するDAO
     */
    @Resource
    protected CrmCcAccountChargeUserDao crmCcAccountChargeUserDao;

    /**
     * パラメータロジック
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * ユーザ_共通に関するDAO
     */
    @Resource
    protected CrmCcUserCmnDao crmCcUserCmnDao;

    /**
     * {@link BizConfigurationProvider}
     */
    @Resource
    protected BizConfigurationProvider bizConfigurationProvider;

    /**
     * コンテキストコンテナ。
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * {@link CrmAccountChargeUserCustomLogic 差し込みモジュールクラス}
     */
    protected CrmAccountChargeUserCustomLogic crmAccountChargeUserCustomLogic =
        new DefaultCrmAccountChargeUserCustomLogic();

    /**
     * 差し込みモジュールをセットします。
     * 
     * @param crmAccountChargeUserCustomLogic
     */
    @Binding(bindingType = BindingType.MAY)
    public void setChargeUserCustom(
            CrmAccountChargeUserCustomLogic crmAccountChargeUserCustomLogic) {
        this.crmAccountChargeUserCustomLogic = crmAccountChargeUserCustomLogic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountChargeUserResultDto> getEntityList(
            CrmCcAccountChargeUserCriteriaDto criteriaDto) {
        return crmAccountChargeUserCustomLogic.getEntityList(criteriaDto);
    }

    /**
     * 処理の差し込み口のデフォルトのクラスです。
     */
    private class DefaultCrmAccountChargeUserCustomLogic implements
            CrmAccountChargeUserCustomLogic {

        /**
         * {@inheritDoc}
         */
        @Override
        public List<CrmCcAccountChargeUserResultDto> getEntityList(
                CrmCcAccountChargeUserCriteriaDto criteriaDto) {

            return crmCcAccountChargeUserDao.getEntityList(criteriaDto);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(
            List<CrmCcAccountChargeUserCriteriaDto> chargeUserCriteriaDtoList) {

        // 入力値をチェックします。
        validateChargeUserCriteriaDto(chargeUserCriteriaDtoList);
        SimpleDateFormat dateFormat =
            new SimpleDateFormat(
                com.biz_integral.foundation.core.util.DateUtil.DATE_FORMAT_HYPHEN);
        String sysStartDate =
            dateFormat
                .format(bizConfigurationProvider.getStartDate())
                .toString();
        String sysEndDate =
            dateFormat.format(bizConfigurationProvider.getEndDate()).toString();
        List<String> resultUserCdList = new ArrayList<String>();
        List<String> criteriaUserCdList = new ArrayList<String>();
        List<CrmCcAccountChargeUser> resultList =
            new ArrayList<CrmCcAccountChargeUser>();
        for (CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto : chargeUserCriteriaDtoList) {
            criteriaUserCdList.add(chargeUserCriteriaDto.userCd);
            List<CrmCcAccountChargeUser> result =
                crmCcAccountChargeUserDao
                    .getCreateEntityList(chargeUserCriteriaDto);
            resultList.addAll(result);
        }
        for (CrmCcAccountChargeUser resultDto : resultList) {
            resultUserCdList.add(resultDto.getUserCd());
            if (!criteriaUserCdList.contains(resultDto.getUserCd())) {
                // CRMアカウント担当組織の論理削除を行いします。
                updateDeleteFlag(resultDto);
            }
        }
        for (CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto : chargeUserCriteriaDtoList) {
            if (resultUserCdList.contains(chargeUserCriteriaDto.userCd)) {
                // CRMアカウント担当組織の更新を行いします。
                List<CrmCcAccountChargeUser> entityList =
                    crmCcAccountChargeUserDao.findByPkIgnoreTerm(
                        chargeUserCriteriaDto.companyCd,
                        chargeUserCriteriaDto.crmAccountCd,
                        chargeUserCriteriaDto.userCd,
                        chargeUserCriteriaDto.crmDomainCd);
                if (entityList.size() == 1) {
                    updateBeforeTerm(chargeUserCriteriaDto, null);
                    updateBetweenTerm(chargeUserCriteriaDto, entityList.get(0));
                    updateAfterTerm(chargeUserCriteriaDto, null);
                } else if (entityList.size() == 2) {
                    CrmCcAccountChargeUser pattern2No1 =
                        new CrmCcAccountChargeUser();
                    CrmCcAccountChargeUser pattern2No2 =
                        new CrmCcAccountChargeUser();
                    if (sysStartDate.equals(dateFormat.format(
                        entityList.get(0).getStartDate()).toString())) {
                        pattern2No1 = entityList.get(0);
                        pattern2No2 = entityList.get(1);
                    } else {
                        pattern2No1 = entityList.get(1);
                        pattern2No2 = entityList.get(0);
                    }
                    boolean pattern2No1DeleteFlag = pattern2No1.isDeleteFlag();
                    boolean pattern2No2DeleteFlag = pattern2No2.isDeleteFlag();
                    if (!pattern2No1DeleteFlag) {
                        updateBeforeTerm(chargeUserCriteriaDto, null);
                        updateBetweenTerm(chargeUserCriteriaDto, pattern2No1);
                        updateAfterTerm(chargeUserCriteriaDto, pattern2No2);
                    }
                    if (!pattern2No2DeleteFlag) {
                        updateBeforeTerm(chargeUserCriteriaDto, pattern2No1);
                        updateBetweenTerm(chargeUserCriteriaDto, pattern2No2);
                        updateAfterTerm(chargeUserCriteriaDto, null);
                    }
                    if (pattern2No1DeleteFlag && pattern2No2DeleteFlag) {
                        updateBeforeTerm(chargeUserCriteriaDto, pattern2No1);
                        updateBetweenTerm(chargeUserCriteriaDto, pattern2No2);
                        updateAfterTerm(chargeUserCriteriaDto, null);
                    }
                } else if (entityList.size() == 3) {
                    CrmCcAccountChargeUser pattern3No1 =
                        new CrmCcAccountChargeUser();
                    CrmCcAccountChargeUser pattern3No2 =
                        new CrmCcAccountChargeUser();
                    CrmCcAccountChargeUser pattern3No3 =
                        new CrmCcAccountChargeUser();
                    for (CrmCcAccountChargeUser entity : entityList) {
                        if (sysStartDate.equals(dateFormat.format(
                            entity.getStartDate()).toString())) {
                            pattern3No1 = entity;
                        } else if (sysEndDate.equals(dateFormat.format(
                            entity.getEndDate()).toString())) {
                            pattern3No3 = entity;
                        } else {
                            pattern3No2 = entity;
                        }
                    }
                    updateBeforeTerm(chargeUserCriteriaDto, pattern3No1);
                    updateBetweenTerm(chargeUserCriteriaDto, pattern3No2);
                    updateAfterTerm(chargeUserCriteriaDto, pattern3No3);
                }

            } else {
                // CRMアカウント担当組織の登録を行いします。
                validateUserCd(chargeUserCriteriaDto);
                updateBeforeTerm(chargeUserCriteriaDto, null);
                updateBetweenTerm(chargeUserCriteriaDto, null);
                updateAfterTerm(chargeUserCriteriaDto, null);
            }
        }
    }

    /**
     * CRMアカウント担当者の登録場合、入力値をチェックします。
     * 
     * @param chargeUserCriteriaDtoList
     *            CRMアカウント担当者モデル
     */
    private void validateChargeUserCriteriaDto(
            List<CrmCcAccountChargeUserCriteriaDto> chargeUserCriteriaDtoList) {
        ValidationResults vr = new ValidationResults();
        int count = 0;

        // 担当者有無チェックします。
        if (chargeUserCriteriaDtoList.size() == 0) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00041")
                .addParameter(MessageBuilder.$("CRM.CC.chargeUser"))
                .toMessage()));
            throw new ValidationException(vr);
        }
        for (CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto : chargeUserCriteriaDtoList) {
            if ("1".equals(chargeUserCriteriaDto.mainChargeFlag)) {
                count = count + 1;
            }

            // 期間大小チェックします。
            if (chargeUserCriteriaDto.startDate
                .after(chargeUserCriteriaDto.endDate)) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00005").addParameter(
                    MessageBuilder.$("CRM.CC.periodTo")).addParameter(
                    MessageBuilder.$("CRM.CC.periodFrom")).toMessage()));
                throw new ValidationException(vr);
            }
        }

        // 主担当者１件チェックします。
        if (count != 1) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00042")
                .addParameter(MessageBuilder.$("CRM.CC.chargeUser"))
                .toMessage()));
            throw new ValidationException(vr);
        }
    }

    /**
     * ユーザコードコードの存在をチェックします。
     * 
     * @param chargeUserCriteriaDto
     *            CRMアカウント担当者モデル
     */
    private void validateUserCd(
            CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto) {
        ValidationResults vr = new ValidationResults();
        boolean found = false;
        List<CrmCcUserCmn> crmCcUserCmnList = crmCcUserCmnDao.findAll();
        for (CrmCcUserCmn crmCcUserCmn : crmCcUserCmnList) {
            if ((chargeUserCriteriaDto.userCd.equals(crmCcUserCmn.getUserCd()))
                && (!found)) {
                found = true;
            }
        }
        if (!found) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00024")
                .addParameter(MessageBuilder.$("CRM.CC.userCd"))
                .addParameter(MessageBuilder.$(chargeUserCriteriaDto.userCd))
                .toMessage()));
            throw new ValidationException(vr);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBeforeTerm(CrmCcAccountChargeUserCriteriaDto criteriaDto,
            CrmCcAccountChargeUser entity) {
        CrmCcAccountChargeUser crmCcAccountChargeUser =
            new CrmCcAccountChargeUser();
        SimpleDateFormat dateFormat =
            new SimpleDateFormat(
                com.biz_integral.foundation.core.util.DateUtil.DATE_FORMAT_HYPHEN);
        String sysStartDate =
            dateFormat
                .format(bizConfigurationProvider.getStartDate())
                .toString();
        String criteriaStartDate =
            dateFormat.format(criteriaDto.startDate).toString();

        if ((entity == null) && (criteriaStartDate.equals(sysStartDate))) {
            // 処理なし
        } else if ((entity == null)
            && (!criteriaStartDate.equals(sysStartDate))) {

            // CRMアカウント担当者の登録を行いします。
            setCrmCcAccountChargeUser(crmCcAccountChargeUser, criteriaDto);
            crmCcAccountChargeUser.setStartDate(bizConfigurationProvider
                .getStartDate());
            crmCcAccountChargeUser.setCreateUserCd(contextContainer
                .getUserContext()
                .getUserID());
            crmCcAccountChargeUser
                .setCreateDate(com.biz_integral.foundation.core.util.DateUtil
                    .nowDate());
            crmCcAccountChargeUserDao.insert(crmCcAccountChargeUser);

        } else if ((entity != null) && (criteriaStartDate.equals(sysStartDate))) {

            // CRMアカウント担当者の物理削除を行いします。
            crmCcAccountChargeUserDao.delete(entity);

        } else if ((entity != null)
            && (!criteriaStartDate.equals(sysStartDate))
            && (criteriaStartDate.equals(dateFormat
                .format(entity.getEndDate())
                .toString()))) {
            // 処理なし
        } else if ((entity != null)
            && (!criteriaStartDate.equals(sysStartDate))
            && (!criteriaStartDate.equals(dateFormat
                .format(entity.getEndDate())
                .toString()))) {

            // CRMアカウント担当者の更新を行いします。
            entity.setStartDate(bizConfigurationProvider.getStartDate());
            entity.setEndDate(criteriaDto.startDate);
            entity.setMainChargeFlag(false);
            entity.setDeleteFlag(true);
            crmCcAccountChargeUserDao.updateIncludes(
                entity,
                CrmCcAccountChargeUserNames.startDate(),
                CrmCcAccountChargeUserNames.endDate(),
                CrmCcAccountChargeUserNames.mainChargeFlag(),
                CrmCcAccountChargeUserNames.deleteFlag(),
                CrmCcAccountChargeUserNames.recordUserCd(),
                CrmCcAccountChargeUserNames.recordDate());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBetweenTerm(
            CrmCcAccountChargeUserCriteriaDto criteriaDto,
            CrmCcAccountChargeUser entity) {
        CrmCcAccountChargeUser crmCcAccountChargeUser =
            new CrmCcAccountChargeUser();

        if (entity == null) {
            // CRMアカウント担当者の登録を行いします。
            setCrmCcAccountChargeUser(crmCcAccountChargeUser, criteriaDto);
            crmCcAccountChargeUser.setStartDate(criteriaDto.startDate);
            crmCcAccountChargeUser.setEndDate(DateUtil.getAddDay(
                criteriaDto.endDate,
                1));
            if ("1".equals(criteriaDto.mainChargeFlag)) {
                crmCcAccountChargeUser.setMainChargeFlag(true);
            }
            crmCcAccountChargeUser.setDeleteFlag(false);
            crmCcAccountChargeUserDao.insert(crmCcAccountChargeUser);
        } else {
            // CRMアカウント担当者の更新を行いします。
            entity.setStartDate(criteriaDto.startDate);
            entity.setEndDate(DateUtil.getAddDay(criteriaDto.endDate, 1));
            if ("0".equals(criteriaDto.mainChargeFlag)) {
                entity.setMainChargeFlag(false);
            } else if ("1".equals(criteriaDto.mainChargeFlag)) {
                entity.setMainChargeFlag(true);
            }
            entity.setDeleteFlag(false);
            crmCcAccountChargeUserDao.updateIncludes(
                entity,
                CrmCcAccountChargeUserNames.startDate(),
                CrmCcAccountChargeUserNames.endDate(),
                CrmCcAccountChargeUserNames.mainChargeFlag(),
                CrmCcAccountChargeUserNames.deleteFlag(),
                CrmCcAccountChargeUserNames.recordUserCd(),
                CrmCcAccountChargeUserNames.recordDate());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAfterTerm(CrmCcAccountChargeUserCriteriaDto criteriaDto,
            CrmCcAccountChargeUser entity) {
        CrmCcAccountChargeUser crmCcAccountChargeUser =
            new CrmCcAccountChargeUser();

        SimpleDateFormat dateFormat =
            new SimpleDateFormat(
                com.biz_integral.foundation.core.util.DateUtil.DATE_FORMAT_HYPHEN);
        String sysEndDate =
            dateFormat.format(bizConfigurationProvider.getEndDate()).toString();

        String criteriaDtoEndDate =
            dateFormat
                .format(DateUtil.getAddDay(criteriaDto.endDate, 1))
                .toString();

        if ((entity == null) && (criteriaDtoEndDate.equals(sysEndDate))) {
            // 処理なし
        } else if ((entity == null) && (!criteriaDtoEndDate.equals(sysEndDate))) {
            // CRMアカウント担当者の登録を行いします。
            setCrmCcAccountChargeUser(crmCcAccountChargeUser, criteriaDto);
            crmCcAccountChargeUser.setStartDate(DateUtil.getAddDay(
                criteriaDto.endDate,
                1));
            crmCcAccountChargeUser.setEndDate(bizConfigurationProvider
                .getEndDate());
            crmCcAccountChargeUser.setMainChargeFlag(false);
            crmCcAccountChargeUser.setDeleteFlag(true);
            crmCcAccountChargeUserDao.insert(crmCcAccountChargeUser);

        } else if ((entity != null) && (criteriaDtoEndDate.equals(sysEndDate))) {
            // CRMアカウント担当者の物理削除を行いします。
            crmCcAccountChargeUserDao.delete(entity);
        } else if ((entity != null)
            && (!criteriaDtoEndDate.equals(sysEndDate))
            && (criteriaDtoEndDate.equals(dateFormat.format(
                entity.getStartDate()).toString()))) {
            // 処理なし
        } else if ((entity != null)
            && (!criteriaDtoEndDate.equals(sysEndDate))
            && (!criteriaDtoEndDate.equals(dateFormat.format(
                entity.getStartDate()).toString()))) {
            // CRMアカウント担当者の更新を行いします。
            entity.setStartDate(DateUtil.getAddDay(criteriaDto.endDate, 1));
            entity.setEndDate(bizConfigurationProvider.getEndDate());
            entity.setMainChargeFlag(false);
            entity.setDeleteFlag(true);
            crmCcAccountChargeUserDao.updateIncludes(
                entity,
                CrmCcAccountChargeUserNames.startDate(),
                CrmCcAccountChargeUserNames.endDate(),
                CrmCcAccountChargeUserNames.mainChargeFlag(),
                CrmCcAccountChargeUserNames.deleteFlag(),
                CrmCcAccountChargeUserNames.recordUserCd(),
                CrmCcAccountChargeUserNames.recordDate());
        }
    }

    /**
     * CRMアカウント担当者の論理削除を行う
     * 
     * @param resultDto
     *            CRMアカウント担当者モデル
     */
    private void updateDeleteFlag(CrmCcAccountChargeUser resultDto) {

        resultDto.setDeleteFlag(true);
        crmCcAccountChargeUserDao.updateIncludes(
            resultDto,
            CrmCcAccountChargeUserNames.deleteFlag(),
            CrmCcAccountChargeUserNames.recordUserCd(),
            CrmCcAccountChargeUserNames.recordDate());

    }

    /**
     * エンティティの設定します。
     * 
     * @param crmCcAccountChargeUser
     *            エンティティ
     * @param criteriaDto
     *            条件Dto
     */
    private void setCrmCcAccountChargeUser(
            CrmCcAccountChargeUser crmCcAccountChargeUser,
            CrmCcAccountChargeUserCriteriaDto criteriaDto) {
        crmCcAccountChargeUser.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        crmCcAccountChargeUser.setCrmAccountCd(criteriaDto.crmAccountCd);
        crmCcAccountChargeUser.setUserCd(criteriaDto.userCd);

        crmCcAccountChargeUser.setCrmDomainCd(parameterLogic.getEntity(
            "CRMCC0002").toString());
        crmCcAccountChargeUser.setTermCd(UniqueIdGenerator
            .getInstance()
            .create());
        crmCcAccountChargeUser.setEndDate(criteriaDto.startDate);
        crmCcAccountChargeUser.setMainChargeFlag(false);
        crmCcAccountChargeUser.setDeleteFlag(true);
        crmCcAccountChargeUser.setSortKey(0L);
    }
}
