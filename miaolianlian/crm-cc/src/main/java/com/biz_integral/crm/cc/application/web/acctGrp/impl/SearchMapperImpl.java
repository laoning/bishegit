/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;

import com.biz_integral.crm.cc.application.web.acctGrp.SearchFilterRequestModel;
import com.biz_integral.crm.cc.application.web.acctGrp.SearchFilterResponseModel;
import com.biz_integral.crm.cc.application.web.acctGrp.SearchMapper;
import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchResultDto;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link SearchMapper}の実装です。
 */
public class SearchMapperImpl implements SearchMapper {

    /**
     * {@link ContextContainer コンテキストコンテナ}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public AcctGrpSearchCriteriaDto convert(SearchFilterRequestModel request) {

        AcctGrpSearchCriteriaDto criteria =
            beanMapper.map(request, AcctGrpSearchCriteriaDto.class);

        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteria.mainChargeFlag = true;
        criteria.deleteFlag = false;

        String sortBy = request.sortBy;
        if (StringUtil.isNotEmpty(sortBy)) {
            boolean isAsc = BooleanConversionUtil.toBoolean(request.isAsc);
            OrderingSpec spec;
            if (isAsc) {
                spec = OrderingSpec.ASC;
            } else {
                spec = OrderingSpec.DESC;
            }
            criteria.setFirstOrderBySpec(sortBy, spec);
        }
        return criteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<SearchFilterResponseModel> convert(
            PagingResult<AcctGrpSearchResultDto> searchResult) {

        PagingResult<SearchFilterResponseModel> result =
            new PagingResult<SearchFilterResponseModel>();
        SearchFilterResponseModel model;

        for (AcctGrpSearchResultDto dist : searchResult) {
            model = beanMapper.map(dist, SearchFilterResponseModel.class);
            result.add(model);
        }
        result.setAllRecordCount(searchResult.getAllRecordCount());
        result.setLimit(searchResult.getLimit());
        result.setOffset(searchResult.getOffset());

        return result;
    }
}
