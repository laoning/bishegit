/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面の登録結果モデルです。
 */
public final class EntrySaveResponseModel {

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 期間コード
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
