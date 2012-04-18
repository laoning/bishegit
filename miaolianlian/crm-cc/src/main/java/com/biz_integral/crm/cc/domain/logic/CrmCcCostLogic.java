/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.entity.CrmCcCost;
import com.biz_integral.crm.sa.domain.dto.ExpApplySetDialogSearchCriteriaDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRM経費ロジックです。<br/>
 */
public interface CrmCcCostLogic {

    /**
     * 検索条件に従い、検索を行います。<br/>
     * 
     * @param searchCriteria
     *            CRM経費検索条件モデル
     * @return CrmCcCostエンティティ
     */
    public PagingResult<CrmCcCost> getEntityList(
            ExpApplySetDialogSearchCriteriaDto searchCriteria);
}