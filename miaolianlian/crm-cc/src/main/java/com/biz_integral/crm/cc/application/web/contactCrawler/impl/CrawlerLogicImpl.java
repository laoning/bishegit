/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactCrawler.impl;

import javax.annotation.Resource;

import jp.co.intra_mart.foundation.solr.crawler.exception.SolrCrawlerException;

import com.biz_integral.crm.cc.application.web.contactCrawler.CrawlerLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactCrawlerLogic;
import com.biz_integral.crm.cc.domain.logic.batch.types.BatchCrawlingType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.lifecycle.BizContext;

/**
 * クローラロジックの実行クラス
 */
public class CrawlerLogicImpl implements CrawlerLogic {

    /**
     * コンストラクタ
     */
    public CrawlerLogicImpl() {
    }

    /** コンテキストコンテナ */
    @Resource
    public ContextContainer contextContainer;

    /** クローラロジック */
    @Resource
    public CrmContactCrawlerLogic crmContactCrawlerLogic;

    /**
     * {@inheritDoc}
     */
    @BizContext
    public void execute(BatchCrawlingType crawlingType) {
        try {
            crmContactCrawlerLogic.execute(crawlingType);
        } catch (SolrCrawlerException e) {
            throw new RuntimeException(e);
        }

    }
}
