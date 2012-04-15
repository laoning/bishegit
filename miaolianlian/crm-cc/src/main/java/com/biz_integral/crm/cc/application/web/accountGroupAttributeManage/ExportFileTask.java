/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountGroupAttributeManage;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.logic.CrmAccountGroupAttributeLogic;
import com.biz_integral.foundation.core.context.lifecycle.BizContext;
import com.biz_integral.service.async.AsyncTaskSupport;

/**
 * 非表示エクスポートボタン押下（非同期）処理です。
 */
public class ExportFileTask extends AsyncTaskSupport {

    /**
     * アカウントグループ属性検索画面に関するビジネスロジック
     */
    @Resource
    protected CrmAccountGroupAttributeLogic crmAccountGroupAttributeLogic;

    /**
     * アカウントグループ属性検索画面で利用するマッパー
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
            AccountGroupAttributeSearchCriteriaDto exportFileModel) {
        crmAccountGroupAttributeLogic.createFile(exportFileModel);
    }
}
