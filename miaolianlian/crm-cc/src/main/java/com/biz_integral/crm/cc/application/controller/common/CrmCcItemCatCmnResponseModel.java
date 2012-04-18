/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 品目コード検索結果モデルです。
 */
public class CrmCcItemCatCmnResponseModel {

    /** 品目名 */
    public String itemCategoryName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
