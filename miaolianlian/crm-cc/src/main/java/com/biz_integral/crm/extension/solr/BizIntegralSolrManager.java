/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import jp.co.nttdata.intra_mart.solr.IntramartSolrManager;
import jp.co.nttdata.intra_mart.solr.exception.SearchException;
import jp.co.nttdata.intra_mart.solr.util.Config;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;

import com.biz_integral.crm.extension.solr.util.BizIntegralSolrConfig;
import com.biz_integral.foundation.core.logging.BizLogger;
import com.biz_integral.foundation.core.logging.BizLoggerFactory;
import com.biz_integral.foundation.core.logging.LogLevel;

/**
 * Solrの管理クラスです。<br>
 * Bizで用意したSolrサーバーへのアクセスを管理します。
 */
public class BizIntegralSolrManager extends IntramartSolrManager {

    /** log */
    private static BizLogger logger =
        BizLoggerFactory.getLogger(BizIntegralSolrManager.class);

    /** Biz用のApacheSolr設定 */
    private BizIntegralSolrConfig bizConfig;

    /**
     * Biz用のSolr設定を取得します。
     * 
     * @return Biz用のSolr設定
     */
    public BizIntegralSolrConfig getBizConfig() {
        return bizConfig;
    }

    /**
     * Biz用のSolr設定を設定します。
     * 
     * @param bizConfig
     *            Biz用のSolr設定
     */
    public void setBizConfig(BizIntegralSolrConfig bizConfig) {
        this.bizConfig = bizConfig;
    }

    /**
     * コンストラクタ
     * 
     * @param user
     *            ユーザID
     * @param company
     *            会社コード
     * @param loginGroup
     *            ログイングループID
     * @param date
     *            現在日付
     * @throws SearchException
     *             Solrへのアクセス例外等
     */
    protected BizIntegralSolrManager(String user, String company,
            String loginGroup, Date date) throws SearchException {
        super(user, loginGroup, date);

        // bizのconfigファイルの設定により、
        // urlを設定し直す。
        bizConfig = new BizIntegralSolrConfig();
        setSolrServer(createSolrServer(getConfig(), loginGroup, company));
        setSearcher(createSearcher(getSolrServer()));
    }

    /**
     * コンストラクタ（テスト用）
     */
    protected BizIntegralSolrManager() {
    }

    /**
     * SolrManagerを返却します。
     * 
     * @param user
     *            ユーザID
     * @param company
     *            会社コード
     * @param group
     *            ログイングループID
     * @return 初期化されたSolrManager
     * @throws SearchException
     *             Solrへのアクセス例外
     */
    public static BizIntegralSolrManager getInstance(String user,
            String company, String group) throws SearchException {
        return getInstance(user, company, group, new Date());
    }

    /**
     * SolrManagerを返却します。
     * 
     * @param user
     *            ユーザID
     * @param company
     *            会社コード
     * @param group
     *            ログイングループID
     * @param date
     *            検索日付
     * @return 初期化されたSolrManager
     * @throws SearchException
     *             Solrへのアクセス例外
     */
    public static BizIntegralSolrManager getInstance(String user,
            String company, String group, Date date) throws SearchException {

        return new BizIntegralSolrManager(user, company, group, date);
    }

    /**
     * Solrの設定内容をリロードします。
     * 
     * @return true:リロード完了、false:リロード失敗
     * @throws SearchException
     *             Solrへのアクセス例外
     */
    public boolean reload() throws SearchException {
        try {
            CommonsHttpSolrServer targetServer =
                (CommonsHttpSolrServer) getSolrServer();
            String path = targetServer.getBaseURL();
            SolrServer coreServer =
                new CommonsHttpSolrServer(path.substring(0, path
                    .lastIndexOf("/")));
            String coreName = path.substring(path.lastIndexOf("/") + 1);
            CoreAdminResponse response =
                CoreAdminRequest.reloadCore(coreName, coreServer);
            logger.log(LogLevel.DEBUG, new StringBuilder().append(
                "Core reload time = ").append(response.getQTime()).toString());
        } catch (MalformedURLException e) {
            throw new SearchException(e);
        } catch (IOException e) {
            throw new SearchException(e);
        } catch (SolrServerException e) {
            throw new SearchException(e);
        }
        return true;
    }

    /**
     * Biz用のSolrサーバーを設定します。
     * 
     * @param config
     *            imart用のSolr設定
     * @param loginGroup
     *            ログイングループID
     * @param company
     *            会社コード
     * @return Biz用の設定ファイルから特定されたSolrのURLで初期された、 SolrServerを返却
     * @throws SearchException
     */
    protected SolrServer createSolrServer(Config config, String loginGroup,
            String company) throws SearchException {
        String url = bizConfig.getSolrUrl(loginGroup, company);
        if (url == null) {
            throw new SearchException("Solr URL is not specified.");
        }
        if (url.equals(config.getSolrUrl(loginGroup))) {
            throw new SearchException(
                "Biz Solr URL is the same as IM ContentsSearch Solr URL");
        }
        try {
            return new CommonsHttpSolrServer(url);
        } catch (MalformedURLException e) {
            throw new SearchException(e);
        }
    }
}
