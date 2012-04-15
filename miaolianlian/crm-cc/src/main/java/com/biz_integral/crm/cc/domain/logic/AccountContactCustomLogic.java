/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMアカウントコンタクトビジネスロジックの差し込み処理を提供します。
 */
public interface AccountContactCustomLogic {

    /**
     * CRMアカウントコンタクトのコンタクト一覧の差し込む処理です。
     * 
     * @param criteriadto
     *            CRMアカウントコンタクトDTO
     * @return CRMアカウントコンタクトリスト
     */
    PagingResult<CrmCcAccountContactResultDto> getContactEntityList(
            CrmCcAccountContactCriteriaDto criteriadto);

    /**
     * CRMアカウントコンタクトのアカウント一覧の差し込む処理です。
     * 
     * @param criteriadto
     *            CRMアカウントコンタクトDTO
     * @return CRMアカウントコンタクトリスト
     */
    PagingResult<CrmCcAccountContactResultDto> getAccountEntityList(
            CrmCcAccountContactCriteriaDto criteriadto);
}
