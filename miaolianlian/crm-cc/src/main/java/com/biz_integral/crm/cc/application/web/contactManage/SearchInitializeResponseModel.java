/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * コンタクト検索初期化ボタン押下responseモデル
 */
public final class SearchInitializeResponseModel {

    /**
     * コンタクト利用フラグ
     */
    public String useType;

    /**
     * コンタクト新規フラグ
     */
    public boolean contactNewFlag;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
