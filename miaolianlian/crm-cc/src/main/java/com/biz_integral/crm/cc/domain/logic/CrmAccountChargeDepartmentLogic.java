/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeDept;

/**
 * CRMアカウント担当組織のロジックです。
 */
public interface CrmAccountChargeDepartmentLogic {

    /**
     * CRMアカウント担当組織の一覧を取得します。
     * 
     * @param chargeDeptCriteriaDto
     *            CRMアカウント担当組織DTO
     * @return CRMアカウント担当組織リスト
     */
    public abstract List<CrmCcAccountChargeDeptResultDto> getEntityList(
            CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto);

    /**
     * CRMアカウント担当組織の登録します。
     * 
     * @param chargeDeptCriteriaDtoList
     *            CRMアカウント担当組織DTO
     */
    public abstract void create(
            List<CrmCcAccountChargeDeptCriteriaDto> chargeDeptCriteriaDtoList);

    /**
     * CRMアカウント担当組織の前期間を更新します。
     * 
     * @param criteriaDto
     *            CRMアカウント担当組織モデル
     * @param entity
     *            CrmCcAccountChargeDeptエンティティクラス
     */
    public abstract void updateBeforeTerm(
            CrmCcAccountChargeDeptCriteriaDto criteriaDto,
            CrmCcAccountChargeDept entity);

    /**
     * CRMアカウント担当組織の中期間を更新します。
     * 
     * @param criteriaDto
     *            CRMアカウント担当組織モデル
     * @param entity
     *            CrmCcAccountChargeDeptエンティティクラス
     */
    public abstract void updateBetweenTerm(
            CrmCcAccountChargeDeptCriteriaDto criteriaDto,
            CrmCcAccountChargeDept entity);

    /**
     * CRMアカウント担当組織の後期間を更新します。
     * 
     * @param criteriaDto
     *            CRMアカウント担当組織モデル
     * @param entity
     *            CrmCcAccountChargeDeptエンティティクラス
     */
    public abstract void updateAfterTerm(
            CrmCcAccountChargeDeptCriteriaDto criteriaDto,
            CrmCcAccountChargeDept entity);

}
