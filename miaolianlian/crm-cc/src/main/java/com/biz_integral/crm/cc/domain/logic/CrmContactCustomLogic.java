/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMコンタクトビジネスロジックの差し込み処理を提供します。
 */
public interface CrmContactCustomLogic {

    /**
     * CRMコンタクト一覧検索差し込む処理です。
     * 
     * @param criteriaDto
     *            検索条件
     * @return {@link ContactSearchResultDto}
     */
    PagingResult<ContactSearchResultDto> getEntityList(
            ContactSearchCriteriaDto criteriaDto);

    /**
     * CRMコンタクトの差し込む更新処理です。
     * 
     * @param crmCcContactDto
     *            CRMコンタクトDTO
     */
    void update(CrmCcContactDto crmCcContactDto);

    /**
     * CRMコンタクト担当チェックの差し込む処理です。
     * 
     * @param crmContactCheckChargeDto
     *            CRMコンタクト担当チェックモデル
     * @return List<CRMコンタクトコード>
     */
    List<String> checkCharge(CrmContactCheckChargeDto crmContactCheckChargeDto);

}
