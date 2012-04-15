/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountClassManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウント分類非表示インポートボタン押下submitモデル
 */
public final class SearchImportFileRequestModel {

    /** ロケールID */
    @Required(arg0 = @Arg(key = "CRM.CC.localeId"))
    public String localeId;

    /** アップロードコンテキストID */
    public String uploadContextId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
