/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面の取引先選択ボタン条件Dtoです。
 */
public final class CustomerSelectCriteriaDto {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * 取引先コード
     */
    public String customerCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
