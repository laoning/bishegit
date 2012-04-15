/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * CRMアカウント分類コード検索用レスポンスです。
 */
public final class CrmCcAccountClassResponseModel {

    /**
     * CRMアカウント分類名
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
