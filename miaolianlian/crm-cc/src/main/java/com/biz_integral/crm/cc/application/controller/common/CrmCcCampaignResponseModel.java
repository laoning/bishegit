/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 
 * キャンペーンコード の検索結果モデルです。
 */
public class CrmCcCampaignResponseModel {

    /** キャンペーンコード */
    public String campaignName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
