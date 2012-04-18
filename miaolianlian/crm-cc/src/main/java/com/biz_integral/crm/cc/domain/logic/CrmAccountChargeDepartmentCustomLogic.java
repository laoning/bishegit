/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptResultDto;

/**
 * CRMアカウント担当組織ビジネスロジックの差し込み処理を提供します。
 */
public interface CrmAccountChargeDepartmentCustomLogic {

    /**
     * CRMアカウント担当組織の一覧の差し込む処理です。
     * 
     * @param chargeDeptCriteriaDto
     *            CRMアカウント担当組織DTO
     * @return CRMアカウント担当組織リスト
     */
    List<CrmCcAccountChargeDeptResultDto> getEntityList(
            CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto);

}
