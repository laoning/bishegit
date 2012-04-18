/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 住所選択ダイアログ一覧検索結果モデルです。
 * 
 */
public final class AddressSelectDialogFilterResponseModel {

    /**
     * 都道府県コード
     */
    public String tdfkCd;

    /**
     * 都道府県名
     */
    public String tdfkAddress;

    /**
     * 市区町村コード
     */
    public String skcsCd;

    /**
     * 市区町村名
     */
    public String skcsAddress;

    /**
     * 期間（自）
     */
    public Date startDate;

    /**
     * 期間（至）
     */
    public Date endDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
