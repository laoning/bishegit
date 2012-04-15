/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.MessageResponse;

/**
 * エクスポートボタン押下responseモデルです。
 */
public final class FileExportResponseModel extends MessageResponse {

    /**
     * FieldName
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
