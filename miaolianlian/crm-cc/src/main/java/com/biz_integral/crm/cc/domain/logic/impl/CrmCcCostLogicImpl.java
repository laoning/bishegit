/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcCostDao;
import com.biz_integral.crm.cc.domain.entity.CrmCcCost;
import com.biz_integral.crm.cc.domain.logic.CrmCcCostLogic;
import com.biz_integral.crm.sa.domain.dto.ExpApplySetDialogSearchCriteriaDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRM経費ロジックの実装です。<br/>
 */
public class CrmCcCostLogicImpl implements CrmCcCostLogic {

    /**
     * CRM経費に関するDAO
     */
    @Resource
    protected CrmCcCostDao crmCcCostDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CrmCcCost> getEntityList(
            ExpApplySetDialogSearchCriteriaDto searchCriteria) {
        return this.crmCcCostDao.findBySearchCriteria(searchCriteria);
    }
}
