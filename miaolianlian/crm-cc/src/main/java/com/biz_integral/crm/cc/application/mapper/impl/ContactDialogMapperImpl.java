/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;

import com.biz_integral.crm.cc.application.controller.common.ContactSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.ContactSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.application.mapper.ContactDialogMapper;
import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link ContactDialogMapper}の実装です。
 * 
 */
public class ContactDialogMapperImpl implements ContactDialogMapper {

    /**
     * {@link BeanMapper}
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link EnumNamesRegistry 区分に関する名称管理}
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link ParameterLogic}
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@inheritDoc}
     */
    @Override
    public ContactSearchCriteriaDto convert(
            ContactSelectDialogFilterRequestModel model) {
        ContactSearchCriteriaDto criteriaDto =
            beanMapper.map(model, ContactSearchCriteriaDto.class);

        criteriaDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteriaDto.userCode = contextContainer.getUserContext().getUserID();
        criteriaDto.localeID = contextContainer.getUserContext().getLocaleID();
        criteriaDto.departmentSetCd =
            parameterLogic.getEntity("CRMCC0004").toString();

        String sortItem = model.sortItem;
        if (StringUtil.isNotEmpty(sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(model.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }

            criteriaDto.setFirstOrderBySpec(sortItem, spec);
        }

        return criteriaDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<ContactSelectDialogFilterResponseModel> convert(
            PagingResult<ContactSearchResultDto> crmCcContact) {

        PagingResult<ContactSelectDialogFilterResponseModel> pagingList =
            new PagingResult<ContactSelectDialogFilterResponseModel>();

        for (ContactSearchResultDto contact : crmCcContact.getResultList()) {
            ContactSelectDialogFilterResponseModel model =
                beanMapper.map(
                    contact,
                    ContactSelectDialogFilterResponseModel.class);
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(crmCcContact.getAllRecordCount());

        return pagingList;
    }

}
