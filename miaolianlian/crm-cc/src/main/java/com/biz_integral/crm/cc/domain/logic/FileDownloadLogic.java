/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.application.controller.common.FileExportResponseModel;

/**
 * ファイルダウンロードのロジックです。
 */
public interface FileDownloadLogic {

    /**
     * 
     * ファイル取得のロジックです。<br/>
     * 
     * @param fileUploadContextId
     *            ファイルアップロードコンテキストID
     * @return ファイル作成処理結果モデル
     */
    public FileExportResponseModel getFile(String fileUploadContextId);

}
