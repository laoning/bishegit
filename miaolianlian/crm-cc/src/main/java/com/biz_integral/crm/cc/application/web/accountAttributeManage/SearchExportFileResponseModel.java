/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.MessageResponse;

/**
 * CRMアカウント属性のエクスポート処理レスポンスモデルです。
 */
public final class SearchExportFileResponseModel extends MessageResponse {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = 4217075000450863526L;

    /** ファイルID */
    public String fileId;

    /** ファイル名 */
    public String fileName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
