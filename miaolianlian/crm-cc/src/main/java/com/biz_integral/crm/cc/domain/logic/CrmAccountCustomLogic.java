/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMアカウントビジネスロジックの差し込み処理を提供します。
 */
public interface CrmAccountCustomLogic {

    /**
     * 
     * CRMアカウント担当チェックの差し込む処理です。
     * 
     * @param crmAccountCheckChargeDto
     *            CRMアカウント担当チェックモデル
     * @return List<CRMアカウントコード>
     */
    List<String> checkCharge(CrmAccountCheckChargeDto crmAccountCheckChargeDto);

    /**
     * CRMアカウントの差し込む更新処理です。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントDTO
     */
    void update(CrmCcAccountDto crmCcAccountDto);

    /**
     * CRMアカウント一覧検索の差し込む処理です。
     * 
     * @param criteriaDto
     *            検索条件
     * @return {@link AccountSearchResultDto}
     */
    PagingResult<AccountSearchResultDto> getEntityList(
            AccountSearchCriteriaDto criteriaDto);
}
