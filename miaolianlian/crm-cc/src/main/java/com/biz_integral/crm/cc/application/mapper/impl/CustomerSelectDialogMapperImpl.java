/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

import com.biz_integral.crm.cc.application.controller.common.CustomerSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CustomerSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.application.mapper.CustomerSelectDialogMapper;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CustomerSelectDialogMapper}の実装です。
 * 
 */
public class CustomerSelectDialogMapperImpl implements
        CustomerSelectDialogMapper {

    /**
     * {@link BeanMapper}の実装です。
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerSelectDialogCriteriaDto convert(
            CustomerSelectDialogFilterRequestModel model) {

        CustomerSelectDialogCriteriaDto criteria =
            beanMapper.map(model, CustomerSelectDialogCriteriaDto.class);

        String sortItem = model.sortItem;
        if (StringUtil.isNotEmpty(sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(model.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }

            criteria.setFirstOrderBySpec(sortItem, spec);
        }
        criteria.localeId = contextContainer.getUserContext().getLocaleID();
        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        return criteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CustomerSelectDialogFilterResponseModel> convert(
            PagingResult<CrmCcCustomerCmn> crmCcCustomerCmn) {

        PagingResult<CustomerSelectDialogFilterResponseModel> pagingList =
            new PagingResult<CustomerSelectDialogFilterResponseModel>();

        for (CrmCcCustomerCmn customerCmn : crmCcCustomerCmn.getResultList()) {
            CustomerSelectDialogFilterResponseModel model =
                beanMapper.map(
                    customerCmn,
                    CustomerSelectDialogFilterResponseModel.class);
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(crmCcCustomerCmn.getAllRecordCount());
        pagingList.setLimit(crmCcCustomerCmn.getLimit());
        pagingList.setOffset(crmCcCustomerCmn.getOffset());
        return pagingList;
    }
}
