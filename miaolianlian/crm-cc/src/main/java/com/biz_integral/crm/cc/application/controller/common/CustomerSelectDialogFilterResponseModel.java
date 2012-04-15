/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 取引先選択ダイアログ一覧検索結果モデルです。
 * 
 */
public final class CustomerSelectDialogFilterResponseModel {

    /**
     * 取引先コード
     */
    public String customerCd;

    /**
     * 取引先名
     */
    public String customerName;

    /**
     * 開始日
     */
    public Date startDate;

    /**
     * 終了日
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
