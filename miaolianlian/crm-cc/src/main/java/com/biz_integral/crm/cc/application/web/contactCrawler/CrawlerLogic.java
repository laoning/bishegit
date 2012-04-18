/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactCrawler;

import com.biz_integral.crm.cc.domain.logic.batch.types.BatchCrawlingType;

/**
 * クローラロジックのインターフェース
 */
public interface CrawlerLogic {

    /**
     * クローラーを実行します。
     * 
     * @param crawlingType
     *            会社コード
     */
    public void execute(BatchCrawlingType crawlingType);
}