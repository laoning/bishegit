/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面の初期化結果モデルです。
 */
public final class AcctGrpAthModel {

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * アカウント名
     */
    public String crmAccountName;

    /**
     * アカウント名
     */
    public String termCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
