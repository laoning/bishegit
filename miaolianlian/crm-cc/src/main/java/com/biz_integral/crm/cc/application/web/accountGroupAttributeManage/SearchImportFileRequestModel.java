/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountGroupAttributeManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント属性非表示インポートボタン押下submitモデル
 */
public final class SearchImportFileRequestModel {

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
