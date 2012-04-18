/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.contactCrawler;

import com.biz_integral.crm.cc.domain.logic.batch.types.BatchCrawlingType;

/**
 * コンタクト差分クローラ
 */
public class CrmContactDeltaCrawler extends CrmContactCrawlerBase {

    /**
     * コンストラクタ
     */
    public CrmContactDeltaCrawler() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BatchCrawlingType getCrawlingType() {
        return BatchCrawlingType.DELTA;
    }
}
