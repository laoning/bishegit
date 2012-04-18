/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;

import com.biz_integral.crm.cc.application.web.accountBelongClassManage.SearchExportFileRequestModel;
import com.biz_integral.crm.cc.application.web.accountBelongClassManage.SearchFilterRequestModel;
import com.biz_integral.crm.cc.application.web.accountBelongClassManage.SearchFilterResponseModel;
import com.biz_integral.crm.cc.application.web.accountBelongClassManage.SearchMapper;
import com.biz_integral.crm.cc.domain.dto.AccountClassAthSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.util.FileUtil;
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
    public CrmCcAccountClassAthSearchCriteriaDto convert(
            SearchFilterRequestModel request) {

        CrmCcAccountClassAthSearchCriteriaDto criteria =
            beanMapper
                .map(request, CrmCcAccountClassAthSearchCriteriaDto.class);

        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteria.deleteFlag = "0";

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
            PagingResult<AccountClassAthSearchResultDto> searchResult) {

        PagingResult<SearchFilterResponseModel> result =
            new PagingResult<SearchFilterResponseModel>();
        SearchFilterResponseModel model;

        for (AccountClassAthSearchResultDto dist : searchResult) {
            model = beanMapper.map(dist, SearchFilterResponseModel.class);
            result.add(model);
        }
        result.setAllRecordCount(searchResult.getAllRecordCount());
        result.setLimit(searchResult.getLimit());
        result.setOffset(searchResult.getOffset());

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountClassAthSearchCriteriaDto convert(
            SearchExportFileRequestModel request) {

        CrmCcAccountClassAthSearchCriteriaDto criteria =
            beanMapper
                .map(request, CrmCcAccountClassAthSearchCriteriaDto.class);

        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteria.deleteFlag = "0";

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

        criteria.fileName =
            FileUtil.addFileExtension(request.fileName, request.fileKind);

        return criteria;
    }

}
