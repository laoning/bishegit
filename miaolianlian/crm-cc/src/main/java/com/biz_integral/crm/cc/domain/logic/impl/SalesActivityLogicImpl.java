/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.SalesActivityCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityResultDto;
import com.biz_integral.crm.cc.domain.logic.SalesActivityLogic;
import com.biz_integral.crm.sa.domain.dao.CrmSaSalesActDao;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * SalesActivityLogicロジックの実装です。
 */
public class SalesActivityLogicImpl implements SalesActivityLogic {

    /**
     * {@link CrmSaSalesActDao 営業活動に関するDAO}
     */
    @Resource
    protected CrmSaSalesActDao crmSaSalesActDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<SalesActivityResultDto> getEntityList(
            SalesActivityCriteriaDto salesActivityCriteriaDto) {
        if (salesActivityCriteriaDto.salesActivityDateTo != null) {
            salesActivityCriteriaDto.salesActivityDateTo =
                DateUtil
                    .getCalculator(salesActivityCriteriaDto.salesActivityDateTo)
                    .addDay(1)
                    .asDate();
        }
        return crmSaSalesActDao
            .getAccountContactEntityList(salesActivityCriteriaDto);

    }
}
