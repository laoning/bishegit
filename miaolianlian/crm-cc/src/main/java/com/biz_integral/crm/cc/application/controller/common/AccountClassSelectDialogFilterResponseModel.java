/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント分類選択ダイアログ検索用レスポンスです。
 */
public final class AccountClassSelectDialogFilterResponseModel {

    /**
     * アカウント分類コード
     */
    public String crmAccountClassCd;

    /**
     * アカウント分類名
     */
    public String crmAccountClassName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
