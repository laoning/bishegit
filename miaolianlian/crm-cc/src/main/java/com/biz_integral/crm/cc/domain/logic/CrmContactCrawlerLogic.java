/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import jp.co.intra_mart.foundation.solr.crawler.exception.SolrCrawlerException;

import com.biz_integral.crm.cc.domain.logic.batch.types.BatchCrawlingType;

/**
 * CRMコンタクトのクローラロジックです。
 */
public interface CrmContactCrawlerLogic {

    /**
     * クローラロジックを実行します。
     * 
     * @param crawlingType
     *            実行タイプ
     * @throws SolrCrawlerException
     *             Solrアクセス例外
     */
    public void execute(BatchCrawlingType crawlingType)
        throws SolrCrawlerException;
}
