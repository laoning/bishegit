/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

import com.biz_integral.crm.cc.application.controller.common.AccountClassSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.AccountClassSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.application.mapper.AccountClassSelectDialogControllerMapper;
import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogResultDto;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link AccountClassSelectDialogControllerMapper}の実装です。
 */
public class AccountClassSelectDialogControllerMapperImpl implements
        AccountClassSelectDialogControllerMapper {

    /**
     * {@link ContextContainer コンテキストコンテナ}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}の実装です。
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountClassSelectDialogCriteriaDto searchConvert(
            AccountClassSelectDialogFilterRequestModel request) {

        AccountClassSelectDialogCriteriaDto criteria =
            beanMapper.map(request, AccountClassSelectDialogCriteriaDto.class);

        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteria.localeId = contextContainer.getUserContext().getLocaleID();
        criteria.deleteFlag = "0";

        String sortBy = request.sortBy;
        if (!StringUtil.isEmpty(sortBy)) {
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
    public PagingResult<AccountClassSelectDialogFilterResponseModel> searchConvert(
            PagingResult<AccountClassSelectDialogResultDto> searchResult) {

        PagingResult<AccountClassSelectDialogFilterResponseModel> result =
            new PagingResult<AccountClassSelectDialogFilterResponseModel>();
        AccountClassSelectDialogFilterResponseModel model;
        for (AccountClassSelectDialogResultDto dist : searchResult) {
            model =
                beanMapper.map(
                    dist,
                    AccountClassSelectDialogFilterResponseModel.class);
            result.add(model);
        }
        result.setAllRecordCount(searchResult.getAllRecordCount());
        result.setLimit(searchResult.getLimit());
        result.setOffset(searchResult.getOffset());

        return result;
    }
}
