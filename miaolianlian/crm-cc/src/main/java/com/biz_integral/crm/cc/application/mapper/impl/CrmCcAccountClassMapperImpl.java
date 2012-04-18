/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.CrmCcAccountClassRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcAccountClassResponseModel;
import com.biz_integral.crm.cc.application.mapper.CrmCcAccountClassMapper;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;

public class CrmCcAccountClassMapperImpl implements CrmCcAccountClassMapper {

    /**
     * {@link ContextContainer コンテキストコンテナ}
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
    public CrmCcAccountClass convert(CrmCcAccountClassRequestModel model) {
        CrmCcAccountClass entity =
            beanMapper.map(model, CrmCcAccountClass.class);
        entity.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        entity.setLocaleId(contextContainer.getUserContext().getLocaleID());
        return entity;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountClassResponseModel convert(
            CrmCcAccountClass crmCcAccountClass) {

        CrmCcAccountClassResponseModel model =
            new CrmCcAccountClassResponseModel();
        if (crmCcAccountClass != null) {
            model.crmAccountClassName =
                crmCcAccountClass.getCrmAccountClassName();
        }
        return model;
    }
}
