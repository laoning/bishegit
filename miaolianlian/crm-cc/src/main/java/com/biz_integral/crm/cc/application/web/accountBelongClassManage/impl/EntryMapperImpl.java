/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;

import com.biz_integral.crm.cc.application.web.accountBelongClassManage.EntryAccountAddRequestModel;
import com.biz_integral.crm.cc.application.web.accountBelongClassManage.EntryAccountCheckModel;
import com.biz_integral.crm.cc.application.web.accountBelongClassManage.EntryAccountDeleteModel;
import com.biz_integral.crm.cc.application.web.accountBelongClassManage.EntryAccountDeleteRequestModel;
import com.biz_integral.crm.cc.application.web.accountBelongClassManage.EntryFilterRequestModel;
import com.biz_integral.crm.cc.application.web.accountBelongClassManage.EntryFilterResponseModel;
import com.biz_integral.crm.cc.application.web.accountBelongClassManage.EntryMapper;
import com.biz_integral.crm.cc.domain.dto.AccountClassAthSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link EntryMapper}の実装です。
 */
public class EntryMapperImpl implements EntryMapper {

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
            EntryFilterRequestModel request) {

        CrmCcAccountClassAthSearchCriteriaDto criteria =
            beanMapper
                .map(request, CrmCcAccountClassAthSearchCriteriaDto.class);

        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteria.localeId = contextContainer.getUserContext().getLocaleID();
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
    public PagingResult<EntryFilterResponseModel> convert(
            PagingResult<AccountClassAthSearchResultDto> searchResult) {

        PagingResult<EntryFilterResponseModel> result =
            new PagingResult<EntryFilterResponseModel>();
        EntryFilterResponseModel model;

        for (AccountClassAthSearchResultDto dist : searchResult) {
            model = beanMapper.map(dist, EntryFilterResponseModel.class);
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
    public List<CrmCcAccountClassAth> convert(
            EntryAccountAddRequestModel request) {

        List<CrmCcAccountClassAth> result =
            new ArrayList<CrmCcAccountClassAth>();
        CrmCcAccountClassAth model;

        for (EntryAccountCheckModel dist : request.accountCdList) {
            model = beanMapper.map(dist, CrmCcAccountClassAth.class);
            model.setCompanyCd(contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode());
            model.setCrmAccountClassCd(request.crmAccountClassCd);
            result.add(model);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountClassAth> convert(
            EntryAccountDeleteRequestModel request) {

        List<CrmCcAccountClassAth> result =
            new ArrayList<CrmCcAccountClassAth>();
        CrmCcAccountClassAth model;

        for (EntryAccountDeleteModel delete : request.accountCdList) {
            if ("true".equals(delete.select)) {
                model = beanMapper.map(delete, CrmCcAccountClassAth.class);
                model.setCompanyCd(contextContainer
                    .getApplicationContext()
                    .getFeatureContext()
                    .getCompanyCode());
                result.add(model);
            }
        }
        return result;
    }

}
