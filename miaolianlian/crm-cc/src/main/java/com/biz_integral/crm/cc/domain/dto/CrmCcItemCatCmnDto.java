/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 品目コード一件取得条件です。
 */
public class CrmCcItemCatCmnDto {

    /** 品目カテゴリセットコード */
    public String itemSetCd;

    /** ロケールID */
    public String localeId;

    /** 期間コード */
    public String termCd;

    /** 品目コード */
    public String itemCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
