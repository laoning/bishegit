/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * ユーザダイアログ検索用レスポンスです。
 */
public final class UserDialogFilterResponseModel {

    /**
     * select
     */
    public String select;

    /**
     * icnselect
     */
    public String icnselect;

    /**
     * ユーザコード
     */
    public String userCd;

    /**
     * ユーザ名
     */
    public String userName;

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
