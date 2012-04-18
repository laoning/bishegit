/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント分類所属削除モデルです。
 */
public final class EntryAccountDeleteModel {

    /**
     * 選択
     */
    public String select;

    /**
     * アカウント分類所属コード
     */
    public String crmAccountClassCd;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
