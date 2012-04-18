/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeDept;

/**
 * CRMコンタクト担当組織のロジックです。
 */
public interface CrmContactChargeDepartmentLogic {

    /**
     * CRMコンタクト担当組織の一覧を取得します。
     * 
     * @param chargeDeptCriteriaDto
     *            CRMコンタクト担当組織DTO
     * @return CRMコンタクト担当組織リスト
     */
    public abstract List<CrmCcContactChargeDeptResultDto> getEntityList(
            CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto);

    /**
     * CRMコンタクト担当組織の登録します。
     * 
     * @param chargeDeptCriteriaDtoList
     *            CRMコンタクト担当組織DTO
     * @param chargeUserCriteriaDtoList
     *            CRMコンタクト担当者DTO
     * @param crmContactCd
     *            コンタクトコード
     */
    public abstract void create(
            List<CrmCcContactChargeDeptCriteriaDto> chargeDeptCriteriaDtoList,
            List<CrmCcContactChargeUserCriteriaDto> chargeUserCriteriaDtoList,
            String crmContactCd);

    /**
     * CRMコンタクト担当組織の前期間を更新します。
     * 
     * @param criteriaDto
     *            CRMコンタクト担当組織モデル
     * @param entity
     *            CrmCcContactChargeDeptエンティティクラス
     */
    public abstract void updateBeforeTerm(
            CrmCcContactChargeDeptCriteriaDto criteriaDto,
            CrmCcContactChargeDept entity);

    /**
     * CRMコンタクト担当組織の中期間を更新します。
     * 
     * @param criteriaDto
     *            CRMコンタクト担当組織モデル
     * @param entity
     *            CrmCcContactChargeDeptエンティティクラス
     */
    public abstract void updateBetweenTerm(
            CrmCcContactChargeDeptCriteriaDto criteriaDto,
            CrmCcContactChargeDept entity);

    /**
     * CRMコンタクト担当組織の後期間を更新します。
     * 
     * @param criteriaDto
     *            CRMコンタクト担当組織モデル
     * @param entity
     *            CrmCcContactChargeDeptエンティティクラス
     */
    public abstract void updateAfterTerm(
            CrmCcContactChargeDeptCriteriaDto criteriaDto,
            CrmCcContactChargeDept entity);
}
