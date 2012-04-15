/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.synonymPattern;

import java.util.Properties;

import org.seasar.framework.container.SingletonS2Container;

import com.biz_integral.crm.cc.application.web.synonymPattern.SynonymPatternBatchLogic;
import com.biz_integral.crm.cc.domain.logic.batch.CrmBatchBase;

/**
 * 類義語パターン
 */
public class SynonymPatternBatch extends CrmBatchBase {

    /** アプリケーションID */
    private static final String APPLICATION_ID = "crm";
    /** モジュールID */
    private static final String MODULE_ID = "cc";
    /** 業務名 */
    private static final String USECASE_ID = "synonymPattern";

    /**
     * コンストラクタ
     */
    public SynonymPatternBatch() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void run(Properties args) {
        SynonymPatternBatchLogic logic =
            SingletonS2Container.getComponent(SynonymPatternBatchLogic.class);

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
