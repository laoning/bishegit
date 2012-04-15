/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.solrReload.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.web.solrReload.BizIntegralSolrReloadBatchLogic;
import com.biz_integral.crm.cc.domain.logic.BizIntegralSolrLogic;
import com.biz_integral.foundation.core.context.lifecycle.BizContext;

public class BizIntegralSolrReloadBatchLogicImpl implements
        BizIntegralSolrReloadBatchLogic {

    /**
     * {@link SolrReloadLogic}
     */
    @Resource
    public BizIntegralSolrLogic bizIntegralSolrLogic;

    /**
     * コンストラクタ
     */
    public BizIntegralSolrReloadBatchLogicImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @BizContext
    public void execute() {
        bizIntegralSolrLogic.reload();
    }
}
