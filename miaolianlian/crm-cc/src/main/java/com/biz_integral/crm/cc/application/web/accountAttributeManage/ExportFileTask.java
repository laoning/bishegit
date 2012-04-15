/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.logic.CrmAccountAttributeLogic;
import com.biz_integral.foundation.core.context.lifecycle.BizContext;
import com.biz_integral.service.async.AsyncTaskSupport;

/**
 * 非表示エクスポートボタン押下（非同期）処理です。
 */
public class ExportFileTask extends AsyncTaskSupport {

    /**
     * アカウント属性検索画面に関するビジネスロジック
     */
    @Resource
    protected CrmAccountAttributeLogic crmAccountAttributeLogic;

    /**
     * アカウント属性検索画面で利用するマッパー
     */
    @Resource
    protected SearchMapper searchMapper;

    /**
     * 非表示エクスポートボタン押下（非同期）処理です。
     * 
     * @param exportFileModel
     *            ファイルのエクスポート処理リクエストモデル
     */
    @BizContext
    public void asyncExportFile(
            AccountAttributeSearchCriteriaDto exportFileModel) {
        crmAccountAttributeLogic.createFile(exportFileModel);
    }
}
