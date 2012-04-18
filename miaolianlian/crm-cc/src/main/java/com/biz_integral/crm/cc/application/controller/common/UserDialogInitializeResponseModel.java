/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * ユーザダイアログ初期化用レスポンスです。
 */
public final class UserDialogInitializeResponseModel {

    /**
     * システム日付
     */
    public Date systemDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
