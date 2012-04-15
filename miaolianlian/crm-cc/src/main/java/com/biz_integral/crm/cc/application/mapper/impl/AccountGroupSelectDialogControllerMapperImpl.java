/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

import com.biz_integral.crm.cc.application.controller.common.AccountGroupSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.AccountGroupSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.application.mapper.AccountGroupSelectDialogControllerMapper;
import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogResultDto;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link AccountGroupSelectDialogControllerMapper}の実装です。
 */
public class AccountGroupSelectDialogControllerMapperImpl implements
        AccountGroupSelectDialogControllerMapper {

    /**
     * コンテキストコンテナ。
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
    public AccountGroupSelectDialogCriteriaDto searchConvert(
            AccountGroupSelectDialogFilterRequestModel request) {

        AccountGroupSelectDialogCriteriaDto criteria =
            beanMapper.map(request, AccountGroupSelectDialogCriteriaDto.class);

        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteria.localeId = contextContainer.getUserContext().getLocaleID();

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
    public PagingResult<AccountGroupSelectDialogFilterResponseModel> searchConvert(
            PagingResult<AccountGroupSelectDialogResultDto> searchResult) {

        PagingResult<AccountGroupSelectDialogFilterResponseModel> result =
            new PagingResult<AccountGroupSelectDialogFilterResponseModel>();
        AccountGroupSelectDialogFilterResponseModel model;
        for (AccountGroupSelectDialogResultDto dist : searchResult) {
            model =
                beanMapper.map(
                    dist,
                    AccountGroupSelectDialogFilterResponseModel.class);
            result.add(model);
        }
        result.setAllRecordCount(searchResult.getAllRecordCount());
        result.setLimit(searchResult.getLimit());
        result.setOffset(searchResult.getOffset());

        return result;
    }
}
