/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント分類所属一覧検索結果モデルです。
 */
public final class EntryFilterResponseModel {

    /**
     * アカウント分類コード
     */
    public String crmAccountClassCd;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * アカウント名
     */
    public String crmAccountName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
