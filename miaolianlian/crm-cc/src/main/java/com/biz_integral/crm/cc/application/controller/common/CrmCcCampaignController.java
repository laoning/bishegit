/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.CrmCcCampaignMapper;
import com.biz_integral.crm.cc.domain.logic.CampaignLogic;
import com.biz_integral.crm.sc.domain.dto.CrmScCampaignDto;
import com.biz_integral.crm.sc.domain.entity.CrmScCampaign;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * キャンペーンの一件取得です。
 */
public class CrmCcCampaignController {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected CampaignLogic campaignLogic;

    /**
     * キャンペーンマップー。
     */
    @Resource
    protected CrmCcCampaignMapper crmCcCampaignMapper;

    /**
     * 
     * キャンペーンの一件取得です。
     * 
     * @param model
     *            キャンペーンコード の検索条件モデル
     * @return {@link CrmCcCampaignResponseModel}
     */
    @Execute
    @Validation
    public CrmCcCampaignResponseModel getCampaignName(
            CrmCcCampaignRequestModel model) {
        if (model.campaignCd == null || model.campaignCd.length() == 0) {
            return new CrmCcCampaignResponseModel();
        }

        CrmScCampaignDto criteria = crmCcCampaignMapper.convert(model);

        CrmScCampaign crmScCampaign = campaignLogic.getEntity(criteria);

        CrmCcCampaignResponseModel resultmodel =
            crmCcCampaignMapper.convert(crmScCampaign);

        return resultmodel;

    }
}
