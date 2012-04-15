/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.logic.CrmAccountClassAthLogic;
import com.biz_integral.foundation.core.context.lifecycle.BizContext;
import com.biz_integral.service.async.AsyncTaskSupport;

/**
 * 非表示エクスポートボタン押下（非同期）処理です。
 */
public class ExportFileTask extends AsyncTaskSupport {

    /**
     * アカウント分類所属のロジック
     */
    @Resource
    protected CrmAccountClassAthLogic crmAccountClassAthLogic;

    /**
     * 非表示エクスポートボタン押下（非同期）処理です。
     * 
     * @param exportFileModel
     *            ファイルのエクスポート処理リクエストモデル
     */
    @BizContext
    public void asyncExportFile(
            CrmCcAccountClassAthSearchCriteriaDto exportFileModel) {
        crmAccountClassAthLogic.createFile(exportFileModel);
    }
}
