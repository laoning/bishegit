/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

import com.biz_integral.crm.cc.application.controller.common.AddressSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.AddressSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.application.mapper.AddressSelectDialogMapper;
import com.biz_integral.crm.cc.domain.dto.AddressSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcSkcsCmn;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link AddressSelectDialogMapperImpl}の実装です。
 * 
 */
public class AddressSelectDialogMapperImpl implements AddressSelectDialogMapper {

    /**
     * {@link BeanMapper}の実装です。
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressSelectDialogCriteriaDto convert(
            AddressSelectDialogFilterRequestModel model) {

        AddressSelectDialogCriteriaDto criteria =
            beanMapper.map(model, AddressSelectDialogCriteriaDto.class);

        String sortItem = model.sortItem;
        if (StringUtil.isNotEmpty(sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(model.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }

            criteria.setFirstOrderBySpec(sortItem, spec);
        }

        return criteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AddressSelectDialogFilterResponseModel> convert(
            PagingResult<CrmCcSkcsCmn> crmCcSkcsCmn) {

        PagingResult<AddressSelectDialogFilterResponseModel> pagingList =
            new PagingResult<AddressSelectDialogFilterResponseModel>();

        for (CrmCcSkcsCmn bizSkcs : crmCcSkcsCmn.getResultList()) {
            AddressSelectDialogFilterResponseModel model =
                beanMapper.map(
                    bizSkcs,
                    AddressSelectDialogFilterResponseModel.class);
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(crmCcSkcsCmn.getAllRecordCount());
        pagingList.setLimit(crmCcSkcsCmn.getLimit());
        pagingList.setOffset(crmCcSkcsCmn.getOffset());
        return pagingList;
    }
}
