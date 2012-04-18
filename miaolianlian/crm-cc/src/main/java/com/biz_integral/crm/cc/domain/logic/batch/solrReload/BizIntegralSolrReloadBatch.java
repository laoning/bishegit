/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.solrReload;

import java.util.Properties;

import org.seasar.framework.container.SingletonS2Container;

import com.biz_integral.crm.cc.application.web.solrReload.BizIntegralSolrReloadBatchLogic;
import com.biz_integral.crm.cc.domain.logic.batch.CrmBatchBase;

/**
 * Solr設定の再読み込み
 */
public class BizIntegralSolrReloadBatch extends CrmBatchBase {

    /** アプリケーションID */
    private static final String APPLICATION_ID = "crm";
    /** モジュールID */
    private static final String MODULE_ID = "cc";
    /** 業務名 */
    private static final String USECASE_ID = "solrReload";

    /**
     * コンストラクタ
     */
    public BizIntegralSolrReloadBatch() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void run(Properties args) {
        BizIntegralSolrReloadBatchLogic logic =
            SingletonS2Container
                .getComponent(BizIntegralSolrReloadBatchLogic.class);

        logic.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationId() {
        return APPLICATION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getModuleId() {
        return MODULE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getBatchId() {
        return USECASE_ID;
    }

}
