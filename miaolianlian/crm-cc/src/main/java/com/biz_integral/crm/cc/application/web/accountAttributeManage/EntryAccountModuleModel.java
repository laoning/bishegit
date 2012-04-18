/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント利用モジュール設定モデル
 */
public final class EntryAccountModuleModel {

    /**
     * 選択
     */
    public String select;

    /**
     * モジュールID
     */
    public String moduleId;

    /**
     * モジュール名
     */
    public String moduleName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
