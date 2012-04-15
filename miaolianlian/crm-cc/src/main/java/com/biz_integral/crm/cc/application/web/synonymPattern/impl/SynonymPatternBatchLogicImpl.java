/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.synonymPattern.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.web.synonymPattern.SynonymPatternBatchLogic;
import com.biz_integral.crm.cc.domain.logic.SynonymPatternLogic;
import com.biz_integral.foundation.core.context.lifecycle.BizContext;

public class SynonymPatternBatchLogicImpl implements SynonymPatternBatchLogic {

    /**
     * {@link SynonymPatternLogic}
     */
    @Resource
    public SynonymPatternLogic synonymPatternLogic;

    /**
     * コンストラクタ
     */
    public SynonymPatternBatchLogicImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @BizContext
    public void execute() {
        synonymPatternLogic.outputFile();
    }
}
