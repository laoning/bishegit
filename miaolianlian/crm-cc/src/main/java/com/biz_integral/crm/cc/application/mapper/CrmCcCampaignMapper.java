/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.CrmCcCampaignRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcCampaignResponseModel;
import com.biz_integral.crm.sc.domain.dto.CrmScCampaignDto;
import com.biz_integral.crm.sc.domain.entity.CrmScCampaign;

/**
 * キャンペーンの一件取得で利用するマッパーです。
 */
public interface CrmCcCampaignMapper {

    /**
     * 
     * キャンペーン入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            キャンペーン検索条件モデル
     * @return キャンペーンDTO
     */
    public CrmScCampaignDto convert(CrmCcCampaignRequestModel model);

    /**
     * エンティティクラスをキャンペーン検索結果モデルに変換します。
     * 
     * @param crmScCampaign
     *            エンティティクラス
     * @return {@link CrmCcCampaignResponseModel}
     */
    public CrmCcCampaignResponseModel convert(CrmScCampaign crmScCampaign);
}
