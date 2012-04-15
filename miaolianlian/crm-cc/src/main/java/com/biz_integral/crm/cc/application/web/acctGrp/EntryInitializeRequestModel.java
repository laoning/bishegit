/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウントグループ登録/更新画面の登録条件モデルです。
 */
public final class EntryInitializeRequestModel {

    // モード
    public String processModeType;

    // アカウントグループコード
    public String crmAccountGroupCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
