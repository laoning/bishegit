/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * アカウント分類所属一覧検索結果モデルです。
 */
public final class AccountClassAthSearchResultDto {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = -3303311512679839957L;

    /**
     * アカウント分類コード
     */
    public String crmAccountClassCd;

    /**
     * アカウント分類名
     */
    public String crmAccountClassName;

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
        return ToStringBuilder.reflectionToString(this);
    }
}
