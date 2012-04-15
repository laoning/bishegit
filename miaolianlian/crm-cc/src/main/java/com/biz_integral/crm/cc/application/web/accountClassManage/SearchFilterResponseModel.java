/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountClassManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント分類一覧検索結果モデルです。
 */
public final class SearchFilterResponseModel {

    /** ロケールID */
    public String localeId;

    /** アカウント分類コード */
    public String crmAccountClassCd;

    /** アカウント分類名 */
    public String crmAccountClassName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
