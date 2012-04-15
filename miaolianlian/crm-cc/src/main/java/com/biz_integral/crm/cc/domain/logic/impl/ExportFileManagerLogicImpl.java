/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.logic.ExportFileManagerLogic;
import com.biz_integral.crm.sc.domain.logic.EntryExportFileManagerLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.service.async.AsyncTaskClient;
import com.biz_integral.service.async.Request;
import com.biz_integral.service.async.domain.types.ExecutePriorityType;

public class ExportFileManagerLogicImpl<T> implements ExportFileManagerLogic<T> {

    /**
     * {@link AsyncTaskClient}
     */
    @Resource
    protected AsyncTaskClient asyncTaskClient;

    /**
     * {@link ContextContainer}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link EntryExportFileManagerLogic}
     */
    @Override
    public String register(T dto, String asyncTaskConfigCd) {

        Request request = new Request();
        request.setAsyncTaskConfigCd(asyncTaskConfigCd);
        request.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        request.setParameterBean(dto);
        request.setComment("");
        request.setExecutePriorityType(ExecutePriorityType.MIDDLE);
        return asyncTaskClient.register(request);
    }

}
