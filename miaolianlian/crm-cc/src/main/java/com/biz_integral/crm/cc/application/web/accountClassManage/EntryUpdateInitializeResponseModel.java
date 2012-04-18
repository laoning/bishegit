/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountClassManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント分類登録／更新 更新モード初期化レスポンスモデルです。
 */
public final class EntryUpdateInitializeResponseModel {

    /** CRMアカウント分類名 */
    public String crmAccountClassName;

    /** バージョン番号 */
    public long versionNo;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
