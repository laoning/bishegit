/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.event;

import jp.co.intra_mart.framework.base.event.Event;

import com.biz_integral.crm.extension.solr.model.ContextLoadingModel;
import com.biz_integral.crm.extension.solr.search.event.listener.ContextLoadingEventListener;

/**
 * {@link ContextLoadingEventListener}実行時に利用する{@code Event}です。
 * 
 */
public class ContextLoadingEvent extends Event {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * コンテクストロード結果を保持するJavaBeanです。
     */
    private ContextLoadingModel model = null;

    /**
     * コンテクストロード結果を保持するJavaBeanを取得します。
     * 
     * @return コンテクストロード結果を保持するJavaBean
     */
    public ContextLoadingModel getModel() {
        return model;
    }

    /**
     * コンテクストロード結果を保持するJavaBeanを設定します。
     * 
     * @param model
     *            コンテクストロード結果を保持するJavaBean
     */
    public void setModel(ContextLoadingModel model) {
        this.model = model;
    }

}
