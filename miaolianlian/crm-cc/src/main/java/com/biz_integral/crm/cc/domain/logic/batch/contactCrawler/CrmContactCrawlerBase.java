/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.contactCrawler;

import java.util.Properties;

import org.seasar.framework.container.SingletonS2Container;

import com.biz_integral.crm.cc.application.web.contactCrawler.CrawlerLogic;
import com.biz_integral.crm.cc.domain.logic.batch.CrmBatchBase;
import com.biz_integral.crm.cc.domain.logic.batch.types.BatchCrawlingType;
import com.biz_integral.crm.cc.domain.logic.constants.CrmSolrConstans;

/**
 * コンタクトクローラ基底クラス
 */
public abstract class CrmContactCrawlerBase extends CrmBatchBase {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void run(Properties args) {
        CrawlerLogic logic =
            SingletonS2Container.getComponent(CrawlerLogic.class);

        logic.execute(getCrawlingType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationId() {
        return CrmSolrConstans.DOCUMENT_INFO.CRM_CONTACT.applicationId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getBatchId() {
        return CrmSolrConstans.DOCUMENT_INFO.CRM_CONTACT.documentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getModuleId() {
        return CrmSolrConstans.DOCUMENT_INFO.CRM_CONTACT.moduleId();
    }

    /**
     * クローラ実行タイプを取得します。
     * 
     * @return クローラ実行タイプ
     */
    protected abstract BatchCrawlingType getCrawlingType();
}
