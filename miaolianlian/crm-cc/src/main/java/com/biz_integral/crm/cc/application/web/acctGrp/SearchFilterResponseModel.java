/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウントグループ一覧検索結果モデルです。
 */
public final class SearchFilterResponseModel {

    /** アカウントグループコード */
    public String crmAccountGroupCd;

    /**
     * アカウントグループ名
     */
    public String crmAccountGroupName;

    /**
     * 組織名
     */
    public String departmentName;

    /**
     * ユーザ名
     */
    public String userName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
