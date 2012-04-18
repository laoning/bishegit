/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * アカウント分類一覧検索結果モデルです。
 */
public final class AccountClassSearchResultDto implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 2924591474179932464L;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * アカウント分類コード
     */
    public String crmAccountClassCd;

    /**
     * アカウント分類名
     */
    public String crmAccountClassName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
