/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.CrmCcContactRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcContactResponseModel;
import com.biz_integral.crm.cc.application.mapper.CrmCcContactMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * CRMコンタクトの一件取得で利用するマッパーの実装です。
 */
public class CrmCcContactMapperImpl implements CrmCcContactMapper {

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
    public CrmCcContactDto convert(CrmCcContactRequestModel model) {

        CrmCcContactDto crmCcContact =
            beanMapper.map(model, CrmCcContactDto.class);

        crmCcContact.localeId = contextContainer.getUserContext().getLocaleID();

        crmCcContact.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        crmCcContact.startDate = DateUtil.nowDate();

        crmCcContact.endDate = DateUtil.nowDate();

        return crmCcContact;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactResponseModel convert(CrmCcContact crmCcContact) {
        CrmCcContactResponseModel mode = new CrmCcContactResponseModel();

        if (crmCcContact != null) {
            mode =
                beanMapper.map(crmCcContact, CrmCcContactResponseModel.class);
        }
        return mode;
    }
}
