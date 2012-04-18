/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountClassManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.MessageResponse;

/**
 * アカウント分類登録／更新削除ボタン押下レスポンスモデルです。
 */
public final class EntryDeleteResponseModel extends MessageResponse {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = 617062386501921139L;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
