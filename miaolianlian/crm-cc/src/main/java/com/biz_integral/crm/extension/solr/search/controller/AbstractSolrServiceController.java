/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.controller;

import jp.co.intra_mart.framework.base.service.ServiceControllerAdapter;
import jp.co.intra_mart.framework.system.exception.ApplicationException;
import jp.co.intra_mart.framework.system.exception.SystemException;

import com.biz_integral.crm.extension.solr.constants.BizIntegralSolrConstants;
import com.biz_integral.crm.extension.solr.model.ContextLoadingModel;
import com.biz_integral.crm.extension.solr.search.event.ContextLoadingEvent;
import com.biz_integral.crm.extension.solr.search.event.ContextLoadingEventResult;
import com.biz_integral.crm.extension.solr.search.event.listener.ContextLoadingEventListener;
import com.biz_integral.crm.extension.solr.search.helper.ContextLoadingRequestParser;

/**
 * 業務検索初期サービスコントローラー<br>
 * 名称取得用に会社コードを取得します。
 */
public abstract class AbstractSolrServiceController extends
        ServiceControllerAdapter {

    /**
     * {@link ContextLoadingEventListener}実行アプリケーションキー
     */
    private static final String APPLICATION_KEY = "crm_solr";

    /**
     * {@link ContextLoadingEventListener}実行イベントキー
     */
    private static final String EVENT_KEY = "context_loading";

    /**
     * コンテキストをリクエストに設定します。<br>
     * リクエストキー{@link BizIntegralSolrConstants#REQ_KEY_BIZ_CONTEXT}に設定します。
     * 
     * @param model
     *            コンテキストローディングモデル
     */
    protected void setContext(ContextLoadingModel model) {
        getRequest().setAttribute(
            BizIntegralSolrConstants.REQ_KEY_BIZ_CONTEXT,
            model);
    }

    /**
     * リクエストを解析してモデルを初期化します。<br>
     * 
     * @return コンテキストローディングモデル
     * @throws SystemException
     *             リクエストの解析に失敗した場合に発生します。
     */
    protected ContextLoadingModel getContextLoadingModel()
        throws SystemException {
        ContextLoadingModel model = new ContextLoadingModel();
        parseRequest(model);
        return model;
    }

    /**
     * <p>
     * リクエストパラメータを解析します。
     * </p>
     * <p>
     * 本処理では{@link ContextLoadingRequestParser}へ処理委譲を行い、リクエストパラメータの解析を行います。
     * </p>
     * 
     * @param model
     *            {@code ContextLoadingModel}
     * @throws SystemException
     */
    protected void parseRequest(ContextLoadingModel model)
        throws SystemException {
        ContextLoadingRequestParser parser =
            new ContextLoadingRequestParser(getRequest(), getResponse());
        parser.parse(model);
    }

    /**
     * {@link ContextLoadingEventListener}を実行します。
     * 
     * @param model
     *            {@link ContextLoadingModel}
     * @return {@link ContextLoadingEventResult}
     * @throws SystemException
     *             アクセスセキュリティ情報の取得失敗時に発生します。
     * @throws ApplicationException
     *             本例外は発生しません。
     */
    protected ContextLoadingEventResult executeEvent(ContextLoadingModel model)
        throws SystemException, ApplicationException {
        ContextLoadingEvent event =
            (ContextLoadingEvent) createEvent(APPLICATION_KEY, EVENT_KEY);
        event.setModel(model);
        ContextLoadingEventResult eventResult =
            (ContextLoadingEventResult) dispatchEvent(event);
        return eventResult;
    }
}
