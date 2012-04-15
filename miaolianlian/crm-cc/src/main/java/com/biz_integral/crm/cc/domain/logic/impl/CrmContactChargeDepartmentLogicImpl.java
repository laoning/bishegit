/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcContactChargeDeptDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcDepartmentCmnDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeDeptNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.cc.domain.logic.CrmContactChargeDepartmentLogic;
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
 * CrmContactChargeDepartmentLogicロジックの実装です。
 */
public class CrmContactChargeDepartmentLogicImpl implements
        CrmContactChargeDepartmentLogic {

    /**
     * CRMコンタクト担当組織に関するDAO
     */
    @Resource
    protected CrmCcContactChargeDeptDao crmCcContactChargeDeptDao;

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
     * {@link BeanMapper}
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * 担当組織共通
     */
    @Resource
    protected CrmCcDepartmentCmnDao crmCcDepartmentCmnDao;

    /**
     * パラメータロジック
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcContactChargeDeptResultDto> getEntityList(
            CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto) {
        return crmCcContactChargeDeptDao.getEntityList(chargeDeptCriteriaDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(
            List<CrmCcContactChargeDeptCriteriaDto> chargeDeptCriteriaDtoList,
            List<CrmCcContactChargeUserCriteriaDto> chargeUserCriteriaDtoList,
            String crmContactCd) {

        // 入力値リストのモデルと、入力値２リストのモデルに対し、以下のチェックを実行する。
        validateChargeDto(chargeDeptCriteriaDtoList, chargeUserCriteriaDtoList);
        // 入力値リストのモデルが1件以上の場合、入力値リストのモデルに対して以下のチェックを実行する。
        validateChargeDeptCriteriaDto(chargeDeptCriteriaDtoList);

        if (chargeDeptCriteriaDtoList == null
            || chargeDeptCriteriaDtoList.isEmpty()) {
            CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto =
                new CrmCcContactChargeDeptCriteriaDto();
            chargeDeptCriteriaDto.companyCd =
                contextContainer
                    .getApplicationContext()
                    .getFeatureContext()
                    .getCompanyCode();
            chargeDeptCriteriaDto.localeId =
                contextContainer.getUserContext().getLocaleID();
            chargeDeptCriteriaDto.crmContactCd = crmContactCd;
            List<CrmCcContactChargeDept> result =
                crmCcContactChargeDeptDao
                    .getCreateEntityList(chargeDeptCriteriaDto);
            if (result != null && !result.isEmpty()) {
                // CRMコンタクト担当組織の論理削除を行いします。
                for (CrmCcContactChargeDept chargeDept : result) {
                    if (!chargeDept.isDeleteFlag()) {
                        updateDeleteFlag(chargeDept);
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
        List<String> resultDeptCdList = new ArrayList<String>();
        List<String> criteriaDeptCdList = new ArrayList<String>();
        List<CrmCcContactChargeDept> resultList =
            new ArrayList<CrmCcContactChargeDept>();

        for (CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto : chargeDeptCriteriaDtoList) {
            criteriaDeptCdList.add(chargeDeptCriteriaDto.departmentCd);
            List<CrmCcContactChargeDept> result =
                crmCcContactChargeDeptDao
                    .getCreateEntityList(chargeDeptCriteriaDto);
            resultList.addAll(result);
        }
        for (CrmCcContactChargeDept resultDto : resultList) {
            resultDeptCdList.add(resultDto.getDepartmentCd());
            if (!criteriaDeptCdList.contains(resultDto.getDepartmentCd())) {
                // CRMコンタクト担当組織の論理削除を行いします。
                updateDeleteFlag(resultDto);
            }
        }
        for (CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto : chargeDeptCriteriaDtoList) {
            if (resultDeptCdList.contains(chargeDeptCriteriaDto.departmentCd)) {
                // CRMコンタクト担当組織の更新を行いします。
                List<CrmCcContactChargeDept> entityList =
                    crmCcContactChargeDeptDao.findByPkIgnoreTerm(
                        chargeDeptCriteriaDto.companyCd,
                        chargeDeptCriteriaDto.crmContactCd,
                        chargeDeptCriteriaDto.departmentCd);
                if (entityList.size() == 1) {
                    updateBeforeTerm(chargeDeptCriteriaDto, null);
                    updateBetweenTerm(chargeDeptCriteriaDto, entityList.get(0));
                    updateAfterTerm(chargeDeptCriteriaDto, null);
                } else if (entityList.size() == 2) {
                    CrmCcContactChargeDept pattern2No1 =
                        new CrmCcContactChargeDept();
                    CrmCcContactChargeDept pattern2No2 =
                        new CrmCcContactChargeDept();
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
                    CrmCcContactChargeDept pattern3No1 =
                        new CrmCcContactChargeDept();
                    CrmCcContactChargeDept pattern3No2 =
                        new CrmCcContactChargeDept();
                    CrmCcContactChargeDept pattern3No3 =
                        new CrmCcContactChargeDept();
                    for (CrmCcContactChargeDept entity : entityList) {
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
                // CRMコンタクト担当組織の登録を行いします。
                validateDepartmentCd(chargeDeptCriteriaDto);
                updateBeforeTerm(chargeDeptCriteriaDto, null);
                updateBetweenTerm(chargeDeptCriteriaDto, null);
                updateAfterTerm(chargeDeptCriteriaDto, null);
            }
        }

    }

    /**
     * 入力値リストのモデルと、入力値２リストのモデルに対し、以下のチェックを実行する。
     * 
     * @param chargeDeptCriteriaDtoList
     *            入力値リスト
     * @param chargeUserCriteriaDtoList
     *            入力値２リスト
     */
    private void validateChargeDto(
            List<CrmCcContactChargeDeptCriteriaDto> chargeDeptCriteriaDtoList,
            List<CrmCcContactChargeUserCriteriaDto> chargeUserCriteriaDtoList) {
        if ((chargeDeptCriteriaDtoList == null || chargeDeptCriteriaDtoList
            .isEmpty())
            && (chargeUserCriteriaDtoList == null || chargeUserCriteriaDtoList
                .isEmpty())) {
            // OK
        } else if ((chargeDeptCriteriaDtoList == null || chargeDeptCriteriaDtoList
            .size() == 0)
            && (chargeUserCriteriaDtoList != null && !chargeUserCriteriaDtoList
                .isEmpty())) {
            ValidationResults vr = new ValidationResults();
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00041")
                .addParameter(MessageBuilder.$("CRM.CC.chargeDepartment"))
                .toMessage()));
            throw new ValidationException(vr);

        } else if ((chargeDeptCriteriaDtoList != null && !chargeDeptCriteriaDtoList
            .isEmpty())
            && (chargeUserCriteriaDtoList == null || chargeUserCriteriaDtoList
                .isEmpty())) {
            ValidationResults vr = new ValidationResults();
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00041")
                .addParameter(MessageBuilder.$("CRM.CC.chargeUser"))
                .toMessage()));
            throw new ValidationException(vr);
        } else if ((chargeDeptCriteriaDtoList != null && !chargeDeptCriteriaDtoList
            .isEmpty())
            && (chargeUserCriteriaDtoList != null && !chargeUserCriteriaDtoList
                .isEmpty())) {
            // OK
        }
    }

    /**
     * CRMコンタクト担当組織の登録場合、入力値をチェックします。
     * 
     * @param chargeDeptCriteriaDtoList
     *            CRMコンタクト担当組織モデルリスト
     */
    private void validateChargeDeptCriteriaDto(
            List<CrmCcContactChargeDeptCriteriaDto> chargeDeptCriteriaDtoList) {
        // 入力値リストのモデルが1件以上の場合
        if (chargeDeptCriteriaDtoList != null
            && !chargeDeptCriteriaDtoList.isEmpty()) {

            ValidationResults vr = new ValidationResults();
            int count = 0;

            for (CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto : chargeDeptCriteriaDtoList) {
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
                vr.add(new ValidationResult(MessageBuilder.create(
                    "E.CRM.CC.00042").addParameter(
                    MessageBuilder.$("CRM.CC.chargeDepartment")).toMessage()));
                throw new ValidationException(vr);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBeforeTerm(CrmCcContactChargeDeptCriteriaDto criteriaDto,
            CrmCcContactChargeDept entity) {
        CrmCcContactChargeDept crmCcContactChargeDept =
            new CrmCcContactChargeDept();
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
            // 処理なし。
        } else if ((entity == null)
            && (!criteriaStartDate.equals(sysStartDate))) {
            // CRMコンタクト担当組織の登録を行いします。
            setCrmCcContactChargeDept(crmCcContactChargeDept, criteriaDto);
            crmCcContactChargeDept.setStartDate(bizConfigurationProvider
                .getStartDate());
            crmCcContactChargeDeptDao.insert(crmCcContactChargeDept);

        } else if ((entity != null) && (criteriaStartDate.equals(sysStartDate))) {
            // CRMコンタクト担当組織の物理削除を行いします。
            crmCcContactChargeDeptDao.delete(entity);

        } else if ((entity != null)
            && (!criteriaStartDate.equals(sysStartDate))
            && (criteriaStartDate.equals(dateFormat
                .format(entity.getEndDate())
                .toString()))) {
            // 処理なし。
        } else if ((entity != null)
            && (!criteriaStartDate.equals(sysStartDate))
            && (!criteriaStartDate.equals(dateFormat
                .format(entity.getEndDate())
                .toString()))) {
            // CRMコンタクト担当組織の更新を行いします。
            entity.setStartDate(bizConfigurationProvider.getStartDate());
            entity.setEndDate(criteriaDto.startDate);
            entity.setMainChargeFlag(false);
            entity.setDeleteFlag(true);
            crmCcContactChargeDeptDao.updateIncludes(
                entity,
                CrmCcContactChargeDeptNames.startDate(),
                CrmCcContactChargeDeptNames.endDate(),
                CrmCcContactChargeDeptNames.mainChargeFlag(),
                CrmCcContactChargeDeptNames.deleteFlag(),
                CrmCcContactChargeDeptNames.recordUserCd(),
                CrmCcContactChargeDeptNames.recordDate());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBetweenTerm(
            CrmCcContactChargeDeptCriteriaDto criteriaDto,
            CrmCcContactChargeDept entity) {
        CrmCcContactChargeDept crmCcContactChargeDept =
            new CrmCcContactChargeDept();

        if (entity == null) {
            // CRMコンタクト担当組織の登録を行いします。
            setCrmCcContactChargeDept(crmCcContactChargeDept, criteriaDto);
            crmCcContactChargeDept.setStartDate(criteriaDto.startDate);
            crmCcContactChargeDept.setEndDate(DateUtil.getAddDay(
                criteriaDto.endDate,
                1));
            if ("1".equals(criteriaDto.mainChargeFlag)) {
                crmCcContactChargeDept.setMainChargeFlag(true);
            }
            crmCcContactChargeDept.setDeleteFlag(false);
            crmCcContactChargeDeptDao.insert(crmCcContactChargeDept);
        } else {
            // CRMコンタクト担当組織の更新を行いします。
            entity.setStartDate(criteriaDto.startDate);
            entity.setEndDate(DateUtil.getAddDay(criteriaDto.endDate, 1));
            if ("0".equals(criteriaDto.mainChargeFlag)) {
                entity.setMainChargeFlag(false);
            } else if ("1".equals(criteriaDto.mainChargeFlag)) {
                entity.setMainChargeFlag(true);
            }
            entity.setDeleteFlag(false);
            crmCcContactChargeDeptDao.updateIncludes(
                entity,
                CrmCcContactChargeDeptNames.startDate(),
                CrmCcContactChargeDeptNames.endDate(),
                CrmCcContactChargeDeptNames.mainChargeFlag(),
                CrmCcContactChargeDeptNames.deleteFlag(),
                CrmCcContactChargeDeptNames.recordUserCd(),
                CrmCcContactChargeDeptNames.recordDate());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAfterTerm(CrmCcContactChargeDeptCriteriaDto criteriaDto,
            CrmCcContactChargeDept entity) {
        CrmCcContactChargeDept crmCcContactChargeDept =
            new CrmCcContactChargeDept();
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
            // CRMコンタクト担当組織の登録を行いします。
            setCrmCcContactChargeDept(crmCcContactChargeDept, criteriaDto);
            crmCcContactChargeDept.setStartDate(DateUtil.getAddDay(
                criteriaDto.endDate,
                1));
            crmCcContactChargeDept.setEndDate(bizConfigurationProvider
                .getEndDate());
            crmCcContactChargeDept.setMainChargeFlag(false);
            crmCcContactChargeDept.setDeleteFlag(true);
            crmCcContactChargeDeptDao.insert(crmCcContactChargeDept);

        } else if ((entity != null) && (criteriaDtoEndDate.equals(sysEndDate))) {
            // CRMコンタクト担当組織の物理削除を行いします。
            crmCcContactChargeDeptDao.delete(entity);
        } else if ((entity != null)
            && (!criteriaDtoEndDate.equals(sysEndDate))
            && (criteriaDtoEndDate.equals(dateFormat.format(
                entity.getStartDate()).toString()))) {
            // 処理なし
        } else if ((entity != null)
            && (!criteriaDtoEndDate.equals(sysEndDate))
            && (!criteriaDtoEndDate.equals(dateFormat.format(
                entity.getStartDate()).toString()))) {
            // CRMコンタクト担当組織の更新を行いします。
            entity.setStartDate(DateUtil.getAddDay(criteriaDto.endDate, 1));
            entity.setEndDate(bizConfigurationProvider.getEndDate());
            entity.setMainChargeFlag(false);
            entity.setDeleteFlag(true);
            crmCcContactChargeDeptDao.updateIncludes(
                entity,
                CrmCcContactChargeDeptNames.startDate(),
                CrmCcContactChargeDeptNames.endDate(),
                CrmCcContactChargeDeptNames.mainChargeFlag(),
                CrmCcContactChargeDeptNames.deleteFlag(),
                CrmCcContactChargeDeptNames.recordUserCd(),
                CrmCcContactChargeDeptNames.recordDate());
        }
    }

    /**
     * CRMコンタクト担当組織の論理削除を行いします。
     * 
     * @param resultDto
     *            CRMコンタクト担当組織モデル
     */
    private void updateDeleteFlag(CrmCcContactChargeDept resultDto) {

        resultDto.setDeleteFlag(true);
        crmCcContactChargeDeptDao.updateIncludes(
            resultDto,
            CrmCcContactChargeDeptNames.deleteFlag(),
            CrmCcContactChargeDeptNames.recordUserCd(),
            CrmCcContactChargeDeptNames.recordDate());

    }

    /**
     * 組織コードの存在をチェックします。
     * 
     * @param chargeDeptCriteriaDto
     *            CRMコンタクト担当組織モデル
     */
    private void validateDepartmentCd(
            CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto) {
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
     * エンティティの設定します。
     * 
     * @param crmCcContactChargeDept
     *            エンティティ
     * @param criteriaDto
     *            条件Dto
     */
    private void setCrmCcContactChargeDept(
            CrmCcContactChargeDept crmCcContactChargeDept,
            CrmCcContactChargeDeptCriteriaDto criteriaDto) {
        crmCcContactChargeDept.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        crmCcContactChargeDept.setCrmContactCd(criteriaDto.crmContactCd);
        crmCcContactChargeDept.setDepartmentCd(criteriaDto.departmentCd);

        crmCcContactChargeDept.setTermCd(UniqueIdGenerator
            .getInstance()
            .create());
        crmCcContactChargeDept.setEndDate(criteriaDto.startDate);
        crmCcContactChargeDept.setMainChargeFlag(false);
        crmCcContactChargeDept.setDeleteFlag(true);
        crmCcContactChargeDept.setSortKey(0L);
    }
}
