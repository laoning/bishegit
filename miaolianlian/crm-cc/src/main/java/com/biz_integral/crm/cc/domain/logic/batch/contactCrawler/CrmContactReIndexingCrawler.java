/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.contactCrawler;

import com.biz_integral.crm.cc.domain.logic.batch.types.BatchCrawlingType;

/**
 * コンタクト再作成クローラ
 */
public class CrmContactReIndexingCrawler extends CrmContactCrawlerBase {

    /**
     * コンストラクタ
     */
    public CrmContactReIndexingCrawler() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BatchCrawlingType getCrawlingType() {
        return BatchCrawlingType.REINDEXING;
    }
}
