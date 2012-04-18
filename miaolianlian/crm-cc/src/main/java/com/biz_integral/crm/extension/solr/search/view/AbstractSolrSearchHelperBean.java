/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.view;

import java.util.Locale;

import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.security.message.MessageManager;
import jp.co.intra_mart.framework.base.web.bean.HelperBean;
import jp.co.intra_mart.framework.base.web.bean.HelperBeanException;

import com.biz_integral.crm.extension.solr.constants.BizIntegralSolrConstants;
import com.biz_integral.crm.extension.solr.model.ContextLoadingModel;
import com.biz_integral.foundation.core.message.NamedItem;
import com.biz_integral.foundation.core.message.impl.DefaultNamedItemResource;

/**
 * Solr検索画面AbstractHelperBean.<br>
 * Solrサーバへの問い合わせ画面の為の<br>
 * 抽象化ヘルパークラスです。
 * 
 */
public abstract class AbstractSolrSearchHelperBean extends HelperBean {

    /**
     * デフォルトシリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * 
     * @throws HelperBeanException
     */
    public AbstractSolrSearchHelperBean() throws HelperBeanException {
        super();
    }

    /**
     * 該当キーのメッセージを取得します。
     * 
     * @param key
     *            キー
     * @return メッセージ
     */
    public String getMessage(String key) {
        String message = key;
        try {
            message = MessageManager.getInstance().getMessage(key);
        } catch (AccessSecurityException e) {
            // ignore
        }
        return message;
    }

    /**
     * 該当キーのメッセージを取得します。
     * 
     * @param key
     *            キー
     * @param args
     *            引数
     * @return メッセージ
     */
    public String getMessage(String key, String args) {
        String message = key;
        try {
            message = MessageManager.getInstance().getMessage(key, args);
        } catch (AccessSecurityException e) {
            // ignore
        }
        return message;
    }

    /**
     * Bizの名称文字列を取得します。<br>
     * Bizの{@link DefaultNamedItemResource}を利用して名称を取得します。<br>
     * その際に会社コードとロケールを必要とします。<br>
     * 会社コードとロケールは{@link #getContext()}から取得します。
     * 
     * @param key
     *            Biz用のリソースキー
     * @return 名称文字列
     * @throws HelperBeanException
     *             ログインユーザ情報取得時に例外が発生
     */
    public String getBizMessage(String key) throws HelperBeanException {
        DefaultNamedItemResource defaultNamedItemResource =
            new DefaultNamedItemResource();

        return defaultNamedItemResource.getString(new NamedItem(
            key,
            getContext().getCompanyCode()), new Locale(getContext()
            .getLocaleID()));

    }

    /**
     * コンテキストローディングモデルを取得します。<br>
     * 
     * @return コンテキストローディングモデル
     */
    public ContextLoadingModel getContext() {
        return (ContextLoadingModel) getRequest().getAttribute(
            BizIntegralSolrConstants.REQ_KEY_BIZ_CONTEXT);
    }

    /**
     * コンテキストローディングモデルを取得します。<br>
     * 
     * @return コンテキストローディングモデル
     */
    public Locale getLocale() {
        return new Locale(((ContextLoadingModel) getRequest().getAttribute(
            BizIntegralSolrConstants.REQ_KEY_BIZ_CONTEXT)).getLocaleID());
    }
}
