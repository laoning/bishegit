/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.config;

import java.util.List;

import com.biz_integral.crm.cc.domain.logic.batch.exception.BatchRuntimeException;

/**
 * クローラの動作設定情報を返却するインターフェースです。
 */
public interface CrmSolrCrawlerConfig {

    /**
     * 初回設定日を返却します。
     * 
     * @return 初回設定日
     * @throws BatchRuntimeException
     */
    public String getInitialDate();

    /**
     * 会社別の初回設定日を返却します。<br>
     * 会社別の有効な初回設定日が取得できない場合、<br> {@link CrmSolrCrawlerConfig#getInitialDate()}
     * の結果を返却します。
     * 
     * @param documentType
     *            文書種別
     * @param companyCd
     *            会社コード
     * @return 初回設定日 @
     */
    public String getInitialDate(String documentType, String companyCd);

    /**
     * １処理件数を返却します。
     * 
     * @return １処理件数
     */
    public long getOneTransactionNumber();

    /**
     * 文書種別の１処理件数を返却します。<br>
     * 文書種別の有効な１処理件数が取得できない場合、<br>
     * {@link CrmSolrCrawlerConfig#getOneTransactionNumber()}の結果を返却します。
     * 
     * @param doumentType
     *            文書種別
     * @return １処理件数
     */
    public long getOneTransactionNumber(String doumentType);

    /**
     * 対象の会社コードリストを返却します。
     * 
     * @param documentType
     *            文書種別
     * @return 会社コードリスト @
     */
    public List<String> getCompanyList(String documentType);

    /**
     * 再作成クローラ用の再作成範囲指定月数を返却します。
     * 
     * @param documentType
     *            文書種別
     * @param companyCd
     *            会社コード
     * @return 再作成月数 @
     */
    public int getReindexMonth(String documentType, String companyCd);

}
