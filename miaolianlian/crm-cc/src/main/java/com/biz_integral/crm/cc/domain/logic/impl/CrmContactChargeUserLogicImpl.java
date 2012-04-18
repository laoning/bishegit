/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcContactChargeUserDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcUserCmnDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeUser;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeUserNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;
import com.biz_integral.crm.cc.domain.logic.CrmContactChargeUserLogic;
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
 * CrmContactChargeUserLogicロジックの実装です。
 */
public class CrmContactChargeUserLogicImpl implements CrmContactChargeUserLogic {

    /**
     * CRMコンタクト担当者に関するDAO
     */
    @Resource
    protected CrmCcContactChargeUserDao crmCcContactChargeUserDao;

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
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcContactChargeUserResultDto> getEntityList(
            CrmCcContactChargeUserCriteriaDto criteriaDto) {
        return crmCcContactChargeUserDao.getEntityList(criteriaDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(
            List<CrmCcContactChargeUserCriteriaDto> chargeUserCriteriaDtoList,
            String crmContactCd) {
        // 入力値をチェックします。
        validateChargeUserCriteriaDto(chargeUserCriteriaDtoList);
        if (chargeUserCriteriaDtoList == null
            || chargeUserCriteriaDtoList.isEmpty()) {
            CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto =
                new CrmCcContactChargeUserCriteriaDto();
            chargeUserCriteriaDto.companyCd =
                contextContainer
                    .getApplicationContext()
                    .getFeatureContext()
                    .getCompanyCode();
            chargeUserCriteriaDto.localeId =
                contextContainer.getUserContext().getLocaleID();
            chargeUserCriteriaDto.crmContactCd = crmContactCd;
            List<CrmCcContactChargeUser> result =
                crmCcContactChargeUserDao
                    .getCreateEntityList(chargeUserCriteriaDto);
            if (result != null && !result.isEmpty()) {
                // CRMコンタクト担当者の論理削除を行いします。
                for (CrmCcContactChargeUser chargeUser : result) {
                    if (!chargeUser.isDeleteFlag()) {
                        updateDeleteFlag(chargeUser);
                    }
                }
            }
            return;
        }
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
        List<CrmCcContactChargeUser> resultList =
            new ArrayList<CrmCcContactChargeUser>();

        for (CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto : chargeUserCriteriaDtoList) {
            criteriaUserCdList.add(chargeUserCriteriaDto.userCd);
            List<CrmCcContactChargeUser> result =
                crmCcContactChargeUserDao
                    .getCreateEntityList(chargeUserCriteriaDto);
            resultList.addAll(result);
        }
        for (CrmCcContactChargeUser resultDto : resultList) {
            resultUserCdList.add(resultDto.getUserCd());
            if (!criteriaUserCdList.contains(resultDto.getUserCd())) {
                // CRMコンタクト担当者の論理削除を行いします。
                updateDeleteFlag(resultDto);
            }
        }
        for (CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto : chargeUserCriteriaDtoList) {
            if (resultUserCdList.contains(chargeUserCriteriaDto.userCd)) {
                // CRMコンタクト担当者の更新を行いします。
                List<CrmCcContactChargeUser> entityList =
                    crmCcContactChargeUserDao.findByPkIgnoreTerm(
                        chargeUserCriteriaDto.companyCd,
                        chargeUserCriteriaDto.crmContactCd,
                        chargeUserCriteriaDto.userCd);
                if (entityList.size() == 1) {
                    updateBeforeTerm(chargeUserCriteriaDto, null);
                    updateBetweenTerm(chargeUserCriteriaDto, entityList.get(0));
                    updateAfterTerm(chargeUserCriteriaDto, null);
                } else if (entityList.size() == 2) {
                    CrmCcContactChargeUser pattern2No1 =
                        new CrmCcContactChargeUser();
                    CrmCcContactChargeUser pattern2No2 =
                        new CrmCcContactChargeUser();
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
                    CrmCcContactChargeUser pattern3No1 =
                        new CrmCcContactChargeUser();
                    CrmCcContactChargeUser pattern3No2 =
                        new CrmCcContactChargeUser();
                    CrmCcContactChargeUser pattern3No3 =
                        new CrmCcContactChargeUser();
                    for (CrmCcContactChargeUser entity : entityList) {
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
                // CRMコンタクト担当者の登録を行いします。
                validateUserCd(chargeUserCriteriaDto);
                updateBeforeTerm(chargeUserCriteriaDto, null);
                updateBetweenTerm(chargeUserCriteriaDto, null);
                updateAfterTerm(chargeUserCriteriaDto, null);
            }
        }

    }

    /**
     * ユーザコードコードの存在をチェックします。
     * 
     * @param chargeUserCriteriaDto
     *            CRMコンタクト担当者モデル
     */
    private void validateUserCd(
            CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto) {
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
     * CRMコンタクト担当者の登録場合、入力値をチェックします。
     * 
     * @param chargeUserCriteriaDtoList
     *            CRMコンタクト担当者モデル
     */
    private void validateChargeUserCriteriaDto(
            List<CrmCcContactChargeUserCriteriaDto> chargeUserCriteriaDtoList) {
        // 入力値リストのモデルが1件以上の場合、入力値リストのモデルに対して以下のチェックを実行する。
        if (chargeUserCriteriaDtoList != null
            && !chargeUserCriteriaDtoList.isEmpty()) {

            ValidationResults vr = new ValidationResults();
            int count = 0;

            for (CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto : chargeUserCriteriaDtoList) {
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
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00042").addParameter(
                    MessageBuilder.$("CRM.CC.chargeUser")).toMessage()));
                throw new ValidationException(vr);
            }
        }
    }

    /**
     * CRMコンタクト担当者の論理削除を行いします。
     * 
     * @param resultDto
     *            CRMコンタクト担当者モデル
     */
    private void updateDeleteFlag(CrmCcContactChargeUser resultDto) {

        resultDto.setDeleteFlag(true);
        crmCcContactChargeUserDao.updateIncludes(
            resultDto,
            CrmCcContactChargeUserNames.deleteFlag(),
            CrmCcContactChargeUserNames.recordUserCd(),
            CrmCcContactChargeUserNames.recordDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBeforeTerm(CrmCcContactChargeUserCriteriaDto criteriaDto,
            CrmCcContactChargeUser entity) {
        CrmCcContactChargeUser crmCcContactChargeUser =
            new CrmCcContactChargeUser();
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

            // CRMコンタクト担当者の登録を行いします。
            setCrmCcContactChargeUser(crmCcContactChargeUser, criteriaDto);
            crmCcContactChargeUser.setStartDate(bizConfigurationProvider
                .getStartDate());
            crmCcContactChargeUserDao.insert(crmCcContactChargeUser);

        } else if ((entity != null) && (criteriaStartDate.equals(sysStartDate))) {

            // CRMコンタクト担当者の物理削除を行いします。
            crmCcContactChargeUserDao.delete(entity);

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

            // CRMコンタクト担当者の更新を行いします。
            entity.setStartDate(bizConfigurationProvider.getStartDate());
            entity.setEndDate(criteriaDto.startDate);
            entity.setMainChargeFlag(false);
            entity.setDeleteFlag(true);
            crmCcContactChargeUserDao.updateIncludes(
                entity,
                CrmCcContactChargeUserNames.startDate(),
                CrmCcContactChargeUserNames.endDate(),
                CrmCcContactChargeUserNames.mainChargeFlag(),
                CrmCcContactChargeUserNames.deleteFlag(),
                CrmCcContactChargeUserNames.recordUserCd(),
                CrmCcContactChargeUserNames.recordDate());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBetweenTerm(
            CrmCcContactChargeUserCriteriaDto criteriaDto,
            CrmCcContactChargeUser entity) {
        CrmCcContactChargeUser crmCcContactChargeUser =
            new CrmCcContactChargeUser();

        if (entity == null) {
            // CRMコンタクト担当者の登録を行いします。
            setCrmCcContactChargeUser(crmCcContactChargeUser, criteriaDto);
            crmCcContactChargeUser.setStartDate(criteriaDto.startDate);
            crmCcContactChargeUser.setEndDate(DateUtil.getAddDay(
                criteriaDto.endDate,
                1));
            if ("1".equals(criteriaDto.mainChargeFlag)) {
                crmCcContactChargeUser.setMainChargeFlag(true);
            }
            crmCcContactChargeUser.setDeleteFlag(false);
            crmCcContactChargeUserDao.insert(crmCcContactChargeUser);
        } else {
            // CRMコンタクト担当者の更新を行いします。
            entity.setStartDate(criteriaDto.startDate);
            entity.setEndDate(DateUtil.getAddDay(criteriaDto.endDate, 1));
            if ("0".equals(criteriaDto.mainChargeFlag)) {
                entity.setMainChargeFlag(false);
            } else if ("1".equals(criteriaDto.mainChargeFlag)) {
                entity.setMainChargeFlag(true);
            }
            entity.setDeleteFlag(false);
            crmCcContactChargeUserDao.updateIncludes(
                entity,
                CrmCcContactChargeUserNames.startDate(),
                CrmCcContactChargeUserNames.endDate(),
                CrmCcContactChargeUserNames.mainChargeFlag(),
                CrmCcContactChargeUserNames.deleteFlag(),
                CrmCcContactChargeUserNames.recordUserCd(),
                CrmCcContactChargeUserNames.recordDate());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAfterTerm(CrmCcContactChargeUserCriteriaDto criteriaDto,
            CrmCcContactChargeUser entity) {
        CrmCcContactChargeUser crmCcContactChargeUser =
            new CrmCcContactChargeUser();
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
            // CRMコンタクト担当者の登録を行いします。
            setCrmCcContactChargeUser(crmCcContactChargeUser, criteriaDto);
            crmCcContactChargeUser.setStartDate(DateUtil.getAddDay(
                criteriaDto.endDate,
                1));
            crmCcContactChargeUser.setEndDate(bizConfigurationProvider
                .getEndDate());
            crmCcContactChargeUserDao.insert(crmCcContactChargeUser);

        } else if ((entity != null) && (criteriaDtoEndDate.equals(sysEndDate))) {
            // CRMコンタクト担当者の物理削除を行いします。
            crmCcContactChargeUserDao.delete(entity);
        } else if ((entity != null)
            && (!criteriaDtoEndDate.equals(sysEndDate))
            && (criteriaDtoEndDate.equals(dateFormat.format(
                entity.getStartDate()).toString()))) {
            // 処理なし
        } else if ((entity != null)
            && (!criteriaDtoEndDate.equals(sysEndDate))
            && (!criteriaDtoEndDate.equals(dateFormat.format(
                entity.getStartDate()).toString()))) {
            // CRMコンタクト担当者の更新を行いします。
            entity.setStartDate(DateUtil.getAddDay(criteriaDto.endDate, 1));
            entity.setEndDate(bizConfigurationProvider.getEndDate());
            entity.setMainChargeFlag(false);
            entity.setDeleteFlag(true);
            crmCcContactChargeUserDao.updateIncludes(
                entity,
                CrmCcContactChargeUserNames.startDate(),
                CrmCcContactChargeUserNames.endDate(),
                CrmCcContactChargeUserNames.mainChargeFlag(),
                CrmCcContactChargeUserNames.deleteFlag(),
                CrmCcContactChargeUserNames.recordUserCd(),
                CrmCcContactChargeUserNames.recordDate());
        }

    }

    /**
     * エンティティの設定します。
     * 
     * @param crmCcContactChargeUser
     *            エンティティ
     * @param criteriaDto
     *            条件Dto
     */
    private void setCrmCcContactChargeUser(
            CrmCcContactChargeUser crmCcContactChargeUser,
            CrmCcContactChargeUserCriteriaDto criteriaDto) {
        crmCcContactChargeUser.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        crmCcContactChargeUser.setCrmContactCd(criteriaDto.crmContactCd);
        crmCcContactChargeUser.setUserCd(criteriaDto.userCd);

        crmCcContactChargeUser.setTermCd(UniqueIdGenerator
            .getInstance()
            .create());
        crmCcContactChargeUser.setEndDate(criteriaDto.startDate);
        crmCcContactChargeUser.setMainChargeFlag(false);
        crmCcContactChargeUser.setDeleteFlag(true);
        crmCcContactChargeUser.setSortKey(0L);
    }
}
