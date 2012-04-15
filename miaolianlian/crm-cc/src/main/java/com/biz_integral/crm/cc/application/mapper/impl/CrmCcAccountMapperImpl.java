/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.CrmCcAccountRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcAccountResponseModel;
import com.biz_integral.crm.cc.application.mapper.CrmCcAccountMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * アカウントコードの一件取得で利用するマッパーの実装です。
 */
public class CrmCcAccountMapperImpl implements CrmCcAccountMapper {
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
    public CrmCcAccountDto convert(CrmCcAccountRequestModel model) {
        CrmCcAccountDto crmCcAccount =
            beanMapper.map(model, CrmCcAccountDto.class);

        crmCcAccount.localeId = contextContainer.getUserContext().getLocaleID();

        crmCcAccount.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        crmCcAccount.startDate = DateUtil.nowDate();

        crmCcAccount.endDate = DateUtil.nowDate();

        return crmCcAccount;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public CrmCcAccountResponseModel convert(CrmCcAccount crmCcAccountResult) {
        CrmCcAccountResponseModel model = new CrmCcAccountResponseModel();
        if (crmCcAccountResult != null) {
            model =
                beanMapper.map(
                    crmCcAccountResult,
                    CrmCcAccountResponseModel.class);
        }

        return model;
    }

}
