/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.controller;

import jp.co.intra_mart.foundation.solr.search.controller.SolrSearchServiceResult;
import jp.co.intra_mart.framework.base.service.ServiceResult;
import jp.co.intra_mart.framework.system.exception.ApplicationException;
import jp.co.intra_mart.framework.system.exception.SystemException;

import com.biz_integral.crm.extension.solr.model.ContextLoadingModel;
import com.biz_integral.crm.extension.solr.search.event.ContextLoadingEventResult;

/**
 * 業務検索初期サービスコントローラー<br>
 * 名称取得用に会社コードを取得します。
 */
public class SolrInitSearchServiceController extends
        AbstractSolrServiceController {

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceResult service() throws SystemException, ApplicationException {
        SolrSearchServiceResult results = new SolrSearchServiceResult();

        ContextLoadingModel model = getContextLoadingModel();
        ContextLoadingEventResult eventResult = executeEvent(model);
        setContext(eventResult.getModel());

        return results;
    }
}
