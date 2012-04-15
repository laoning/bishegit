/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeUser;

/**
 * CRMアカウント担当者のロジックです。
 */
public interface CrmAccountChargeUserLogic {

    /**
     * CRMアカウント担当者の一覧を取得します。
     * 
     * @param criteriaDto
     *            CRMアカウント担当者DTO
     * @return CRMアカウント担当者リスト
     */
    public abstract List<CrmCcAccountChargeUserResultDto> getEntityList(
            CrmCcAccountChargeUserCriteriaDto criteriaDto);

    /**
     * CRMアカウント担当者の登録します。
     * 
     * @param chargeUserCriteriaDtoList
     *            CRMアカウント担当者DTO
     */
    public abstract void create(
            List<CrmCcAccountChargeUserCriteriaDto> chargeUserCriteriaDtoList);

    /**
     * CRMアカウント担当者の前期間を更新します。
     * 
     * @param criteriaDto
     *            CRMアカウント担当者モデル
     * @param entity
     *            CrmCcAccountChargeUserエンティティクラス
     */
    public abstract void updateBeforeTerm(
            CrmCcAccountChargeUserCriteriaDto criteriaDto,
            CrmCcAccountChargeUser entity);

    /**
     * CRMアカウント担当者の中期間を更新します。
     * 
     * @param criteriaDto
     *            CRMアカウント担当者モデル
     * @param entity
     *            CrmCcAccountChargeUserエンティティクラス
     */
    public abstract void updateBetweenTerm(
            CrmCcAccountChargeUserCriteriaDto criteriaDto,
            CrmCcAccountChargeUser entity);

    /**
     * CRMアカウント担当者の後期間を更新します。
     * 
     * @param criteriaDto
     *            CRMアカウント担当者モデル
     * @param entity
     *            CrmCcAccountChargeUserエンティティクラス
     */
    public abstract void updateAfterTerm(
            CrmCcAccountChargeUserCriteriaDto criteriaDto,
            CrmCcAccountChargeUser entity);

}
