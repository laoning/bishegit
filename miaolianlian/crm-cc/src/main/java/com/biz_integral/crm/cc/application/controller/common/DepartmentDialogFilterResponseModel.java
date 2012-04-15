/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 組織ダイアログ一覧検索結果モデルです。
 * 
 */
public final class DepartmentDialogFilterResponseModel {

    /**
     * 選択セル
     */
    public String select;

    /**
     * 組織コード
     */
    public String departmentCd;

    /**
     * 組織名
     */
    public String departmentName;

    /**
     * 有効期間開始日
     */
    public Date effectiveTermStart;

    /**
     * 有効期間終了日
     */
    public Date effectiveTermEnd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
