/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.config;

import java.util.List;

/**
 * クローラの動作設定情報を返却するインターフェースです。
 */
public interface CrmBatchConfig {

    /**
     * バッチ種別を返却します。
     * 
     * @return バッチ種別 @
     */
    public String getBatchType();

    /**
     * バッチ種別を返却します。
     * 
     * @param applicationId
     *            アプリケーションID
     * @param moduleId
     *            モジュールID
     * @param usecaseId
     *            ユースケースID
     * @return バッチ種別 @
     */
    public String getBatchType(String applicationId, String moduleId,
            String usecaseId);

    /**
     * 対象の会社コードリストを返却します。
     * 
     * @param batchType
     *            バッチ種別
     * @return 会社コードリスト @
     */
    public List<String> getCompanyList(String batchType);
}
