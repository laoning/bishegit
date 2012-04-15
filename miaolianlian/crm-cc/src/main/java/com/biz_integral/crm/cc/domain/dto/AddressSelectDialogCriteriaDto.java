/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * 住所選択ダイアログの検索条件です。
 */
public final class AddressSelectDialogCriteriaDto extends PagerSupport {

    /**
     * 検索基準日
     */
    public Date searchBaseDate;

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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
