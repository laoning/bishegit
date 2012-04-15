/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.MessageResponse;

/**
 * アカウント分類所属ファイルのエクスポート用レスポンスモデルです。
 */
public class SearchExportFileResponseModel extends MessageResponse {
    /**
     * serialVersionUID = 1L;
     */
    private static final long serialVersionUID = 1L;

    /**
     * ファイルID
     */
    public String fileId;

    /**
     * ファイル名
     */
    public String fileName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
