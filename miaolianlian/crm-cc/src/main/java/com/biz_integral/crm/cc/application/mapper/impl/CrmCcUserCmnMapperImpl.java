/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.CrmCcUserCmnRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcUserCmnResponseModel;
import com.biz_integral.crm.cc.application.mapper.CrmCcUserCmnMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcUserCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;

/**
 * 担当者コード共通利用するマッパーの実装です。
 */
public class CrmCcUserCmnMapperImpl implements CrmCcUserCmnMapper {

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
    public CrmCcUserCmnDto convert(CrmCcUserCmnRequestModel model) {
        CrmCcUserCmnDto dto = beanMapper.map(model, CrmCcUserCmnDto.class);
        dto.localeId = contextContainer.getUserContext().getLocaleID();
        return dto;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcUserCmnResponseModel convert(CrmCcUserCmn crmCcUserCmn) {
        CrmCcUserCmnResponseModel model = new CrmCcUserCmnResponseModel();

        if (crmCcUserCmn != null) {

            model.chargeUserName = crmCcUserCmn.getUserName();
        }
        return model;
    }
}
