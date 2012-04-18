/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * 取引先選択ダイアログの検索条件です。
 */
public final class CustomerSelectDialogCriteriaDto extends PagerSupport {

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * 検索基準日
     */
    public Date searchBaseDate;

    /**
     * 取引先コード
     */
    public String customerCd;

    /**
     * 取引先名
     */
    public String customerName;

    /**
     * 取引先略称
     */
    public String customerShortName;

    /**
     * 取引先検索名
     */
    public String customerSearchName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
