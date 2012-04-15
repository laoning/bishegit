/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.event;

import jp.co.intra_mart.framework.base.event.EventResult;

import com.biz_integral.crm.extension.solr.model.ContextLoadingModel;
import com.biz_integral.crm.extension.solr.search.event.listener.ContextLoadingEventListener;

/**
 * {@link ContextLoadingEventListener}実行結果を保持する{@code EventResult}です。
 * 
 */
public class ContextLoadingEventResult implements EventResult {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * コンテクストロード結果を保持するJavaBeanです。
     */
    private ContextLoadingModel model = null;

    /**
     * コンストラクタです。
     * 
     * @param model
     *            コンテクストロード結果を保持するJavaBean
     */
    public ContextLoadingEventResult(ContextLoadingModel model) {
        this.model = model;
    }

    /**
     * コンテクストロード結果を保持するJavaBeanを返します。
     * 
     * @return コンテクストロード結果を保持するJavaBean
     */
    public ContextLoadingModel getModel() {
        return model;
    }

}
