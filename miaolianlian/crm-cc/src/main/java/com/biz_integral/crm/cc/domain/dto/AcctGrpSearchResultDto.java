/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * アカウントグループ一覧検索結果モデルです。
 */
public final class AcctGrpSearchResultDto {

    /**
     * アカウントグループコード
     */
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
        return ToStringBuilder.reflectionToString(this);
    }
}
