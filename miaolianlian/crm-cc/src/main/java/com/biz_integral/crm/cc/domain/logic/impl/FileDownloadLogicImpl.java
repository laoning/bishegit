/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.FileExportResponseModel;
import com.biz_integral.crm.cc.domain.logic.FileDownloadLogic;
import com.biz_integral.crm.cc.domain.util.FileUtil;
import com.biz_integral.service.storage.SharedFile;
import com.biz_integral.service.storage.SharedStorageManager;

/**
 * ファイルダウンロードのロジックの実装です。
 */
public class FileDownloadLogicImpl implements FileDownloadLogic {

    /**
     * {@link SharedStorageManager}
     */
    @Resource
    private SharedStorageManager sharedStorageManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public FileExportResponseModel getFile(String fileUploadContextId) {
        FileExportResponseModel model = new FileExportResponseModel();

        // （１）ファイル管理情報を取得します。
        List<SharedFile> sharedFileList =
            sharedStorageManager.findFileInfo(fileUploadContextId);

        // （２）戻り値を設定します。
        if (sharedFileList != null && sharedFileList.size() != 0) {
            model.fileId = FileUtil.createKey(sharedFileList.get(0));
            model.fileName = sharedFileList.get(0).getLogicalFileName();
        }
        return model;
    }
}
