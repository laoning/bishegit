/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント属性登録／更新画面のモジュール一覧引数モデルです。
 */
public final class EntryModuleRequestModel {

    /**
     * 選択
     */
    public String select;

    /**
     * モジュールID
     */
    public String moduleId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
