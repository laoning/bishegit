/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.CrmCcCampaignRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcCampaignResponseModel;
import com.biz_integral.crm.cc.application.mapper.CrmCcCampaignMapper;
import com.biz_integral.crm.sc.domain.dto.CrmScCampaignDto;
import com.biz_integral.crm.sc.domain.entity.CrmScCampaign;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;

/**
 * キャンペーンの一件取得で利用するマッパーの実装です。
 */
public class CrmCcCampaignMapperImpl implements CrmCcCampaignMapper {

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
    public CrmScCampaignDto convert(CrmCcCampaignRequestModel model) {
        CrmScCampaignDto crmScCampaign =
            beanMapper.map(model, CrmScCampaignDto.class);

        crmScCampaign.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        return crmScCampaign;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcCampaignResponseModel convert(CrmScCampaign crmScCampaign) {

        CrmCcCampaignResponseModel model = new CrmCcCampaignResponseModel();
        if (crmScCampaign != null) {
            model =
                beanMapper.map(crmScCampaign, CrmCcCampaignResponseModel.class);
        }
        return model;
    }

}
