/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserResultDto;

/**
 * CRMアカウント担当者ビジネスロジックの差し込み処理を提供します。
 */
public interface CrmAccountChargeUserCustomLogic {

    /**
     * CRMアカウント担当者の一覧の差し込む処理です。
     * 
     * @param criteriaDto
     *            CRMアカウント担当者DTO
     * @return CRMアカウント担当者リスト
     */
    List<CrmCcAccountChargeUserResultDto> getEntityList(
            CrmCcAccountChargeUserCriteriaDto criteriaDto);
}
