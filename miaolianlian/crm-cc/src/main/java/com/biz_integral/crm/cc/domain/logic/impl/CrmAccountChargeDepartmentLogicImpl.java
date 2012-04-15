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

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountChargeDeptDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcDepartmentCmnDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeDeptNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.cc.domain.logic.CrmAccountChargeDepartmentCustomLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountChargeDepartmentLogic;
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
import com.biz_integral.service.persistence.LogicalDelete;

/**
 * CrmAccountChargeDepartmentLogicロジックの実装です。
 */
public class CrmAccountChargeDepartmentLogicImpl implements
        CrmAccountChargeDepartmentLogic {

    /**
     * CRMアカウント担当組織に関するDAO
     */
    @Resource
    protected CrmCcAccountChargeDeptDao crmCcAccountChargeDeptDao;

    /**
     * アプリケーション共通マスタ
     */
    @Resource
    protected BizConfigurationProvider bizConfigurationProvider;

    /**
     * コンテキストコンテナ。
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * パラメータロジック
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * 担当組織共通
     */
    @Resource
    protected CrmCcDepartmentCmnDao crmCcDepartmentCmnDao;

    /**
     * {@link BeanMapper}
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * {@link CrmAccountChargeDepartmentCustomLogic 差し込みモジュールクラス}
     */
    protected CrmAccountChargeDepartmentCustomLogic crmAccountChargeDepartmentCustomLogic =
        new DefaultCrmAccountChargeDepartmentCustomLogic();

    /**
     * 差し込みモジュールをセットします。
     * 
     * @param crmAccountChargeDepartmentCustomLogic
     */
    @Binding(bindingType = BindingType.MAY)
    public void setChargeDepartmentCustom(
            CrmAccountChargeDepartmentCustomLogic crmAccountChargeDepartmentCustomLogic) {
        this.crmAccountChargeDepartmentCustomLogic =
            crmAccountChargeDepartmentCustomLogic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountChargeDeptResultDto> getEntityList(
            CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto) {
        return crmAccountChargeDepartmentCustomLogic
            .getEntityList(chargeDeptCriteriaDto);
    }

    /**
     * 処理の差し込み口のデフォルトのクラスです。
     */
    private class DefaultCrmAccountChargeDepartmentCustomLogic implements
            CrmAccountChargeDepartmentCustomLogic {

        /**
         * {@inheritDoc}
         */
        @Override
        public List<CrmCcAccountChargeDeptResultDto> getEntityList(
                CrmCcAccountChargeDeptCriteriaDto criteriaDto) {
            return crmCcAccountChargeDeptDao.getEntityList(criteriaDto);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(
            List<CrmCcAccountChargeDeptCriteriaDto> chargeDeptCriteriaDtoList) {

        // 入力値をチェックします。
        validateChargeDeptCriteriaDto(chargeDeptCriteriaDtoList);
        SimpleDateFormat dateFormat =
            new SimpleDateFormat(
                com.biz_integral.foundation.core.util.DateUtil.DATE_FORMAT_HYPHEN);
        String sysStartDate =
            dateFormat
                .format(bizConfigurationProvider.getStartDate())
                .toString();
        String sysEndDate =
            dateFormat.format(bizConfigurationProvider.getEndDate()).toString();
        List<String> resultDeptCdList = new ArrayList<String>();
        List<String> criteriaDeptCdList = new ArrayList<String>();
        List<CrmCcAccountChargeDept> resultList =
            new ArrayList<CrmCcAccountChargeDept>();

        for (CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto : chargeDeptCriteriaDtoList) {
            criteriaDeptCdList.add(chargeDeptCriteriaDto.departmentCd);
            List<CrmCcAccountChargeDept> result =
                crmCcAccountChargeDeptDao
                    .getCreateEntityList(chargeDeptCriteriaDto);
            resultList.addAll(result);
        }
        for (CrmCcAccountChargeDept resultDto : resultList) {
            resultDeptCdList.add(resultDto.getDepartmentCd());
            if (!criteriaDeptCdList.contains(resultDto.getDepartmentCd())) {
                // CRMアカウント担当組織の論理削除を行いします。
                updateDeleteFlag(resultDto);
            }
        }
        for (CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto : chargeDeptCriteriaDtoList) {
            if (resultDeptCdList.contains(chargeDeptCriteriaDto.departmentCd)) {
                // CRMアカウント担当組織の更新を行いします。
                List<CrmCcAccountChargeDept> entityList =
                    crmCcAccountChargeDeptDao.findByPkIgnoreTerm(
                        chargeDeptCriteriaDto.companyCd,
                        chargeDeptCriteriaDto.crmAccountCd,
                        chargeDeptCriteriaDto.departmentCd,
                        chargeDeptCriteriaDto.crmDomainCd);
                if (entityList.size() == 1) {
                    updateBeforeTerm(chargeDeptCriteriaDto, null);
                    updateBetweenTerm(chargeDeptCriteriaDto, entityList.get(0));
                    updateAfterTerm(chargeDeptCriteriaDto, null);
                } else if (entityList.size() == 2) {
                    CrmCcAccountChargeDept pattern2No1 =
                        new CrmCcAccountChargeDept();
                    CrmCcAccountChargeDept pattern2No2 =
                        new CrmCcAccountChargeDept();
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
                        updateBeforeTerm(chargeDeptCriteriaDto, null);
                        updateBetweenTerm(chargeDeptCriteriaDto, pattern2No1);
                        updateAfterTerm(chargeDeptCriteriaDto, pattern2No2);
                    }
                    if (!pattern2No2DeleteFlag) {
                        updateBeforeTerm(chargeDeptCriteriaDto, pattern2No1);
                        updateBetweenTerm(chargeDeptCriteriaDto, pattern2No2);
                        updateAfterTerm(chargeDeptCriteriaDto, null);
                    }
                    if (pattern2No1DeleteFlag && pattern2No2DeleteFlag) {
                        updateBeforeTerm(chargeDeptCriteriaDto, pattern2No1);
                        updateBetweenTerm(chargeDeptCriteriaDto, pattern2No2);
                        updateAfterTerm(chargeDeptCriteriaDto, null);
                    }
                } else if (entityList.size() == 3) {
                    CrmCcAccountChargeDept pattern3No1 =
                        new CrmCcAccountChargeDept();
                    CrmCcAccountChargeDept pattern3No2 =
                        new CrmCcAccountChargeDept();
                    CrmCcAccountChargeDept pattern3No3 =
                        new CrmCcAccountChargeDept();
                    for (CrmCcAccountChargeDept entity : entityList) {
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
                    updateBeforeTerm(chargeDeptCriteriaDto, pattern3No1);
                    updateBetweenTerm(chargeDeptCriteriaDto, pattern3No2);
                    updateAfterTerm(chargeDeptCriteriaDto, pattern3No3);
                }

            } else {
                // CRMアカウント担当組織の登録を行いします。
                validateDepartmentCd(chargeDeptCriteriaDto);
                updateBeforeTerm(chargeDeptCriteriaDto, null);
                updateBetweenTerm(chargeDeptCriteriaDto, null);
                updateAfterTerm(chargeDeptCriteriaDto, null);
            }
        }
    }

    /**
     * 組織コードの存在をチェックします。
     * 
     * @param chargeDeptCriteriaDto
     *            CRMアカウント担当組織モデル
     */
    private void validateDepartmentCd(
            CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto) {
        ValidationResults vr = new ValidationResults();
        List<CrmCcDepartmentCmn> crmCcDepartmentCmnList =
            crmCcDepartmentCmnDao.findByPkIgnoreTerm(
                contextContainer.getFeatureContext().getCompanyCode(),
                parameterLogic.getEntity("CRMCC0004").toString(),
                chargeDeptCriteriaDto.departmentCd,
                contextContainer.getUserContext().getLocaleID(),
                LogicalDelete.NO_RESTRICTION);

        if (crmCcDepartmentCmnList.isEmpty()) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00024")
                .addParameter(MessageBuilder.$("CRM.CC.departmentCd"))
                .addParameter(
                    MessageBuilder.$(chargeDeptCriteriaDto.departmentCd))
                .toMessage()));
            throw new ValidationException(vr);
        }
    }

    /**
     * CRMアカウント担当組織の論理削除を行いします。
     * 
     * @param resultDto
     *            CRMアカウント担当組織モデル
     */
    private void updateDeleteFlag(CrmCcAccountChargeDept resultDto) {
        resultDto.setDeleteFlag(true);
        crmCcAccountChargeDeptDao.updateIncludes(
            resultDto,
            CrmCcAccountChargeDeptNames.deleteFlag(),
            CrmCcAccountChargeDeptNames.recordUserCd(),
            CrmCcAccountChargeDeptNames.recordDate());

    }

    /**
     * CRMアカウント担当組織の登録場合、入力値をチェックします。
     * 
     * @param chargeDeptCriteriaDtoList
     *            CRMアカウント担当組織モデルリスト
     */
    private void validateChargeDeptCriteriaDto(
            List<CrmCcAccountChargeDeptCriteriaDto> chargeDeptCriteriaDtoList) {
        ValidationResults vr = new ValidationResults();
        int count = 0;

        // 担当組織有無チェックします。
        if (chargeDeptCriteriaDtoList.size() == 0) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00041")
                .addParameter(MessageBuilder.$("CRM.CC.chargeDepartment"))
                .toMessage()));
            throw new ValidationException(vr);
        }
        for (CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto : chargeDeptCriteriaDtoList) {
            if ("1".equals(chargeDeptCriteriaDto.mainChargeFlag)) {
                count = count + 1;
            }

            // 期間大小チェックします。
            if (chargeDeptCriteriaDto.startDate
                .after(chargeDeptCriteriaDto.endDate)) {
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00005").addParameter(
                    MessageBuilder.$("CRM.CC.periodTo")).addParameter(
                    MessageBuilder.$("CRM.CC.periodFrom")).toMessage()));
                throw new ValidationException(vr);
            }
        }

        // 主担当組織１件チェックします。
        if (count != 1) {
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00042")
                .addParameter(MessageBuilder.$("CRM.CC.chargeDepartment"))
                .toMessage()));
            throw new ValidationException(vr);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBeforeTerm(CrmCcAccountChargeDeptCriteriaDto criteriaDto,
            CrmCcAccountChargeDept entity) {
        CrmCcAccountChargeDept crmCcAccountChargeDept =
            new CrmCcAccountChargeDept();
        SimpleDateFormat dateFormat =
            new SimpleDateFormat(
                com.biz_integral.foundation.core.util.DateUtil.DATE_FORMAT_HYPHEN);
        String sysStartDate =
            dateFormat
                .format(bizConfigurationProvider.getStartDate())
                .toString();
        String criteriaStartDate =
            dateFormat.format(criteriaDto.startDate).toString();
        if ((entity == null) && (sysStartDate.equals(criteriaStartDate))) {
            // 処理なし。
        } else if ((entity == null)
            && (!sysStartDate.equals(criteriaStartDate))) {
            // CRMアカウント担当組織の登録を行いします。
            setCrmCcAccountChargeDept(crmCcAccountChargeDept, criteriaDto);
            crmCcAccountChargeDept.setStartDate(bizConfigurationProvider
                .getStartDate());
            crmCcAccountChargeDeptDao.insert(crmCcAccountChargeDept);

        } else if ((entity != null) && (sysStartDate.equals(criteriaStartDate))) {
            // CRMアカウント担当組織の物理削除を行いします。
            crmCcAccountChargeDeptDao.delete(entity);

        } else if ((entity != null)
            && (!sysStartDate.equals(criteriaStartDate))
            && (criteriaStartDate.equals(dateFormat
                .format(entity.getEndDate())
                .toString()))) {
            // 処理なし。
        } else if ((entity != null)
            && (!sysStartDate.equals(criteriaStartDate))
            && (!criteriaStartDate.equals(dateFormat
                .format(entity.getEndDate())
                .toString()))) {
            // CRMアカウント担当組織の更新を行いします。
            entity.setStartDate(bizConfigurationProvider.getStartDate());
            entity.setEndDate(criteriaDto.startDate);
            entity.setMainChargeFlag(false);
            entity.setDeleteFlag(true);
            crmCcAccountChargeDeptDao.updateIncludes(
                entity,
                CrmCcAccountChargeDeptNames.startDate(),
                CrmCcAccountChargeDeptNames.endDate(),
                CrmCcAccountChargeDeptNames.mainChargeFlag(),
                CrmCcAccountChargeDeptNames.deleteFlag(),
                CrmCcAccountChargeDeptNames.recordUserCd(),
                CrmCcAccountChargeDeptNames.recordDate());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBetweenTerm(
            CrmCcAccountChargeDeptCriteriaDto criteriaDto,
            CrmCcAccountChargeDept entity) {
        CrmCcAccountChargeDept crmCcAccountChargeDept =
            new CrmCcAccountChargeDept();

        if (entity == null) {
            // CRMアカウント担当組織の登録を行いします。
            setCrmCcAccountChargeDept(crmCcAccountChargeDept, criteriaDto);
            crmCcAccountChargeDept.setStartDate(criteriaDto.startDate);
            crmCcAccountChargeDept.setEndDate(DateUtil.getAddDay(
                criteriaDto.endDate,
                1));
            if ("1".equals(criteriaDto.mainChargeFlag)) {
                crmCcAccountChargeDept.setMainChargeFlag(true);
            }
            crmCcAccountChargeDept.setDeleteFlag(false);
            crmCcAccountChargeDeptDao.insert(crmCcAccountChargeDept);
        } else {
            // CRMアカウント担当組織の更新を行いします。
            entity.setStartDate(criteriaDto.startDate);
            entity.setEndDate(DateUtil.getAddDay(criteriaDto.endDate, 1));
            if ("0".equals(criteriaDto.mainChargeFlag)) {
                entity.setMainChargeFlag(false);
            } else if ("1".equals(criteriaDto.mainChargeFlag)) {
                entity.setMainChargeFlag(true);
            }
            entity.setDeleteFlag(false);
            crmCcAccountChargeDeptDao.updateIncludes(
                entity,
                CrmCcAccountChargeDeptNames.startDate(),
                CrmCcAccountChargeDeptNames.endDate(),
                CrmCcAccountChargeDeptNames.mainChargeFlag(),
                CrmCcAccountChargeDeptNames.deleteFlag(),
                CrmCcAccountChargeDeptNames.recordUserCd(),
                CrmCcAccountChargeDeptNames.recordDate());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAfterTerm(CrmCcAccountChargeDeptCriteriaDto criteriaDto,
            CrmCcAccountChargeDept entity) {
        CrmCcAccountChargeDept crmCcAccountChargeDept =
            new CrmCcAccountChargeDept();
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
            // CRMアカウント担当組織の登録を行いします。
            setCrmCcAccountChargeDept(crmCcAccountChargeDept, criteriaDto);
            crmCcAccountChargeDept.setStartDate(DateUtil.getAddDay(
                criteriaDto.endDate,
                1));
            crmCcAccountChargeDept.setEndDate(bizConfigurationProvider
                .getEndDate());
            crmCcAccountChargeDept.setMainChargeFlag(false);
            crmCcAccountChargeDept.setDeleteFlag(true);
            crmCcAccountChargeDeptDao.insert(crmCcAccountChargeDept);

        } else if ((entity != null) && (criteriaDtoEndDate.equals(sysEndDate))) {
            // CRMアカウント担当組織の物理削除を行いします。
            crmCcAccountChargeDeptDao.delete(entity);
        } else if ((entity != null)
            && (!criteriaDtoEndDate.equals(sysEndDate))
            && (criteriaDtoEndDate.equals(dateFormat.format(
                entity.getStartDate()).toString()))) {
            // 処理なし
        } else if ((entity != null)
            && (!criteriaDtoEndDate.equals(sysEndDate))
            && (!criteriaDtoEndDate.equals(dateFormat.format(
                entity.getStartDate()).toString()))) {
            // CRMアカウント担当組織の更新を行いします。
            entity.setStartDate(DateUtil.getAddDay(criteriaDto.endDate, 1));
            entity.setEndDate(bizConfigurationProvider.getEndDate());
            entity.setMainChargeFlag(false);
            entity.setDeleteFlag(true);
            crmCcAccountChargeDeptDao.updateIncludes(
                entity,
                CrmCcAccountChargeDeptNames.startDate(),
                CrmCcAccountChargeDeptNames.endDate(),
                CrmCcAccountChargeDeptNames.mainChargeFlag(),
                CrmCcAccountChargeDeptNames.deleteFlag(),
                CrmCcAccountChargeDeptNames.recordUserCd(),
                CrmCcAccountChargeDeptNames.recordDate());
        }
    }

    /**
     * エンティティの設定します。
     * 
     * @param crmCcAccountChargeDept
     *            エンティティ
     * @param criteriaDto
     *            条件Dto
     */
    private void setCrmCcAccountChargeDept(
            CrmCcAccountChargeDept crmCcAccountChargeDept,
            CrmCcAccountChargeDeptCriteriaDto criteriaDto) {
        crmCcAccountChargeDept.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        crmCcAccountChargeDept.setCrmAccountCd(criteriaDto.crmAccountCd);
        crmCcAccountChargeDept.setDepartmentCd(criteriaDto.departmentCd);

        crmCcAccountChargeDept.setCrmDomainCd(parameterLogic.getEntity(
            "CRMCC0002").toString());
        crmCcAccountChargeDept.setTermCd(UniqueIdGenerator
            .getInstance()
            .create());
        crmCcAccountChargeDept.setEndDate(criteriaDto.startDate);
        crmCcAccountChargeDept.setMainChargeFlag(false);
        crmCcAccountChargeDept.setDeleteFlag(true);
        crmCcAccountChargeDept.setSortKey(0L);
    }
}
