/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.CrmCcDepartmentCmnRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcDepartmentCmnResponseModel;
import com.biz_integral.crm.cc.application.mapper.CrmCcDepartmentCmnMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcDepartmentCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;

/**
 * 組織コードの一件取得で利用するマッパーの実装です。
 */
public class CrmCcDepartmentCmnMapperImpl implements CrmCcDepartmentCmnMapper {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}の実装です。
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcDepartmentCmnDto convert(CrmCcDepartmentCmnRequestModel model) {

        CrmCcDepartmentCmnDto crmCcDepartmentCmnDto =
            beanMapper.map(model, CrmCcDepartmentCmnDto.class);

        crmCcDepartmentCmnDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        crmCcDepartmentCmnDto.localeId =
            contextContainer.getUserContext().getLocaleID();

        return crmCcDepartmentCmnDto;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcDepartmentCmnResponseModel convert(
            CrmCcDepartmentCmn crmCcDepartmentCmn) {
        CrmCcDepartmentCmnResponseModel model =
            new CrmCcDepartmentCmnResponseModel();
        if (crmCcDepartmentCmn != null) {
            model =
                beanMapper.map(
                    crmCcDepartmentCmn,
                    CrmCcDepartmentCmnResponseModel.class);
        }

        return model;
    }

}
