/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;

import com.biz_integral.crm.cc.application.web.accountAttributeManage.SearchExportFileRequestModel;
import com.biz_integral.crm.cc.application.web.accountAttributeManage.SearchFilterRequestModel;
import com.biz_integral.crm.cc.application.web.accountAttributeManage.SearchFilterResponseModel;
import com.biz_integral.crm.cc.application.web.accountAttributeManage.SearchMapper;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.util.FileUtil;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
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
     * 区分に関する名称管理APIです。
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link BeanMapper}
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountAttributeSearchCriteriaDto convert(
            SearchFilterRequestModel request) {

        AccountAttributeSearchCriteriaDto criteria =
            beanMapper.map(request, AccountAttributeSearchCriteriaDto.class);

        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteria.localeId = contextContainer.getUserContext().getLocaleID();
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
    public AccountAttributeSearchCriteriaDto convert(
            SearchExportFileRequestModel request) {

        AccountAttributeSearchCriteriaDto criteria =
            beanMapper.map(request, AccountAttributeSearchCriteriaDto.class);

        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteria.localeId = contextContainer.getUserContext().getLocaleID();
        criteria.deleteFlag = false;
        criteria.fileName =
            FileUtil.addFileExtension(request.fileName, request.fileKind);

        return criteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<SearchFilterResponseModel> convert(
            PagingResult<AccountAttributeSearchResultDto> searchResult) {

        PagingResult<SearchFilterResponseModel> result =
            new PagingResult<SearchFilterResponseModel>();
        SearchFilterResponseModel model;

        for (AccountAttributeSearchResultDto dist : searchResult) {
            model = beanMapper.map(dist, SearchFilterResponseModel.class);
            result.add(model);
        }
        result.setAllRecordCount(searchResult.getAllRecordCount());
        result.setLimit(searchResult.getLimit());
        result.setOffset(searchResult.getOffset());

        return result;
    }
}
