/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrpHierarchy;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AcctGrpHierarchyDto;
import com.biz_integral.crm.cc.domain.logic.AcctGrpHierarchyLogic;
import com.biz_integral.foundation.core.context.lifecycle.BizContext;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.service.async.AsyncTaskSupport;

/**
 * 非表示エクスポートボタン押下（非同期）処理です。
 */
public class ExportFileTask extends AsyncTaskSupport {

    /**
     * {@link AcctGrpHierarchyLogic}
     */
    @Resource
    protected AcctGrpHierarchyLogic acctGrpHierarchyLogic;

    /**
     * 非表示エクスポートボタン押下（非同期）処理です。
     * 
     * @param exportFileModel
     *            ファイルのエクスポート処理リクエストモデル
     */
    @BizContext
    public void asyncExportFile(AcctGrpHierarchyDto exportFileModel) {
        acctGrpHierarchyLogic.exportFile(exportFileModel, UniqueIdGenerator
            .getInstance()
            .create());
    }
}
