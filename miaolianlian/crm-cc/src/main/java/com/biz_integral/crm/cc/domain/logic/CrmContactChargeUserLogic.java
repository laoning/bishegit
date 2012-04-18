/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeUser;

/**
 * CRMコンタクト担当者のロジックです。
 */
public interface CrmContactChargeUserLogic {

    /**
     * CRMコンタクト担当者の一覧を取得します。
     * 
     * @param chargeUserCriteriaDto
     *            CRMコンタクト担当者DTO
     * @return CRMコンタクト担当者リスト
     */
    public abstract List<CrmCcContactChargeUserResultDto> getEntityList(
            CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto);

    /**
     * CRMコンタクト担当者の登録します。
     * 
     * @param chargeUserCriteriaDtoList
     *            CRMコンタクト担当者DTO
     * @param crmContactCd
     *            コンタクトコード
     */

    public abstract void create(
            List<CrmCcContactChargeUserCriteriaDto> chargeUserCriteriaDtoList,
            String crmContactCd);

    /**
     * CRMコンタクト担当者の前期間を更新します。
     * 
     * @param criteriaDto
     *            CRMコンタクト担当者モデル
     * @param entity
     *            CrmCcContactChargeUserエンティティクラス
     */
    public abstract void updateBeforeTerm(
            CrmCcContactChargeUserCriteriaDto criteriaDto,
            CrmCcContactChargeUser entity);

    /**
     * CRMコンタクト担当者の中期間を更新します。
     * 
     * @param criteriaDto
     *            CRMコンタクト担当者モデル
     * @param entity
     *            CrmCcContactChargeUserエンティティクラス
     */
    public abstract void updateBetweenTerm(
            CrmCcContactChargeUserCriteriaDto criteriaDto,
            CrmCcContactChargeUser entity);

    /**
     * CRMコンタクト担当者の後期間を更新します。
     * 
     * @param criteriaDto
     *            CRMコンタクト担当者モデル
     * @param entity
     *            CrmCcContactChargeUserエンティティクラス
     */
    public abstract void updateAfterTerm(
            CrmCcContactChargeUserCriteriaDto criteriaDto,
            CrmCcContactChargeUser entity);

}
