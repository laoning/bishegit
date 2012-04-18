/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jp.co.intra_mart.foundation.solr.search.condition.OptionSearchConditionAnalyzer;
import jp.co.intra_mart.foundation.solr.search.condition.SearchConditionAnalyzer;
import jp.co.intra_mart.foundation.solr.search.condition.SearchConditionBuilder;
import jp.co.intra_mart.foundation.solr.search.condition.SimpleSearchConditionAnalyzer;
import jp.co.intra_mart.foundation.solr.search.controller.SolrSearchServiceResult;
import jp.co.intra_mart.framework.base.service.ServiceResult;
import jp.co.intra_mart.framework.system.exception.ApplicationException;
import jp.co.intra_mart.framework.system.exception.SystemException;
import jp.co.nttdata.intra_mart.solr.IntramartSolrManager;
import jp.co.nttdata.intra_mart.solr.domain.SolrSearchCondition;
import jp.co.nttdata.intra_mart.solr.domain.SolrSearchQuery;
import jp.co.nttdata.intra_mart.solr.exception.SearchException;
import jp.co.nttdata.intra_mart.solr.model.SearchResult;
import jp.co.nttdata.intra_mart.solr.util.Config;

import com.biz_integral.crm.extension.solr.BizIntegralSolrManager;
import com.biz_integral.crm.extension.solr.constants.BizIntegralSolrConstants.SEARCH_FUNCTION;
import com.biz_integral.crm.extension.solr.model.ContextLoadingModel;
import com.biz_integral.crm.extension.solr.model.SearchFunctionCondition;
import com.biz_integral.crm.extension.solr.search.event.ContextLoadingEventResult;
import com.biz_integral.foundation.core.logging.BizLogger;
import com.biz_integral.foundation.core.logging.BizLoggerFactory;
import com.biz_integral.foundation.core.logging.LogLevel;

/**
 * 業務検索サービスコントローラー<br>
 * コンテキストの作成はAPF対応待ちのため<br>
 * intra-martの標準機能を用いて、コンテキストの類似情報を取得しています。
 */
public class SolrSearchServiceController extends AbstractSolrServiceController /* ContextLoadingServiceController */{

    /** log */
    private static BizLogger log =
        BizLoggerFactory.getLogger(SolrSearchServiceController.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceResult service() throws SystemException, ApplicationException {
        SolrSearchServiceResult results = new SolrSearchServiceResult();

        ContextLoadingModel model = getContextLoadingModel();

        ContextLoadingEventResult eventResult = executeEvent(model);

        setContext(eventResult.getModel());
        String companyCd = eventResult.getModel().getCompanyCode();

        String searchConditions = null;
        java.text.DateFormat df = new SimpleDateFormat("HH:mm:ss  SSS");

        log.log(LogLevel.DEBUG, new StringBuilder().append("#start:").append(
            df.format(Calendar.getInstance().getTime())).toString());

        try {

            SearchConditionAnalyzer analyzer = null;
            SolrSearchQuery query = new SolrSearchQuery();
            Config conf = IntramartSolrManager.getCachedConfig();
            String type = null;
            String searchKind = getRequest().getParameter("search_kind");

            // 検索オプションの利用の有無
            if (searchKind.equals("simple_search")) {
                searchConditions =
                    getRequest().getParameter("search_conditions");
                analyzer = new SimpleSearchConditionAnalyzer(searchConditions);
            } else {
                analyzer =
                    (new OptionSearchConditionAnalyzer(getRequest()
                        .getParameter("and_conditions"), getRequest()
                        .getParameter("or_conditions"), getRequest()
                        .getParameter("and_not_conditions"), getRequest()
                        .getParameter("phrase_conditions")));
            }

            // ファセット検索の選択
            if (getRequest().getParameter("facet_search") != null
                && getRequest().getParameter("facet_search").length() > 0) {
                type = getRequest().getParameter("facet_search");
            } else {
                type = getRequest().getParameter("doc_type");
            }

            // ユーザコード
            String userCd = getUserInfo().getUserID();
            // ログイングループID
            String loginGroupId = getUserInfo().getLoginGroupID();
            // ロケールID
            Locale locale = getUserInfo().getLocale();

            // intra-martの文書検索マネージャーの初期化
            BizIntegralSolrManager mng =
                BizIntegralSolrManager.getInstance(
                    userCd,
                    companyCd,
                    loginGroupId);

            // 検索条件作成クラスの初期化
            SearchConditionBuilder builder =
                new SearchConditionBuilder(analyzer, conf.getUseNgram(), conf
                    .getUseMorph());

            // ファセットがある場合はファセット検索の追加
            if (type != null) {
                builder.setCondtion(builder.buildStringFieldCondion(
                    "type",
                    type));
            }

            // 検索条件をチェック
            String errorKey = builder.check();

            // 検索オプション利用時は、展開済みの検索条件を設定する
            if (!searchKind.equals("simple_search")) {
                searchConditions = analyzer.getSearchConditions();
            }

            // 検索条件に不備がある場合
            if (errorKey != null) {
                results.setErrorMessageKey(errorKey);
                throw new SearchException(errorKey);
            }

            // Biz拡張 solrの条件に会社コードとロケールIDを追加する。
            SolrSearchCondition companyCondition = new SolrSearchCondition();
            companyCondition.term("company", companyCd);

            SolrSearchCondition localeCondition = new SolrSearchCondition();
            localeCondition.term("locale", locale.getLanguage());

            SearchFunctionCondition functionCondition =
                new SearchFunctionCondition(SEARCH_FUNCTION.FULL_SEARCH);

            // queryクラスの設定
            SolrSearchCondition searchCondition = builder.build();

            // 条件をまとめる
            SolrSearchCondition condition =
                SolrSearchCondition.and(
                    searchCondition,
                    companyCondition,
                    localeCondition,
                    functionCondition);

            String pageNo = getRequest().getParameter("page_no");
            String dispCount = getRequest().getParameter("display_count");
            String disSnippets = getRequest().getParameter("display_snippets");

            query.setCondition(condition);
            query.addFacetField("type");
            query.addFieldToOutput("score");
            query.addFieldToOutput("*");
            if (disSnippets.equals("on")) {
                if (conf.getUseNgram()) {
                    query.addFieldToGenerateSnippetsFor("text_ngram");
                }

                if (conf.getUseMorph()) {
                    query.addFieldToGenerateSnippetsFor("text_morph");
                }
            }

            query.setOffset(Long.valueOf(pageNo).longValue()
                * Long.valueOf(dispCount).longValue());
            query.setRows(Long.valueOf(dispCount).longValue());

            log.log(LogLevel.DEBUG, new StringBuilder()
                .append(this.toString())
                .append("#search-start:")
                .append(df.format(Calendar.getInstance().getTime()))
                .toString());

            // 検索処理
            SearchResult result = mng.searchDocuments(query);

            log.log(LogLevel.DEBUG, new StringBuilder()
                .append(this.toString())
                .append("#search-end:")
                .append(df.format(Calendar.getInstance().getTime()))
                .toString());

            // 検索結果の設定
            results.setSearchResult(result);
            results.setSearchConditions(searchConditions);

            log.log(LogLevel.DEBUG, new StringBuilder()
                .append(this.toString())
                .append("#end:")
                .append(df.format(Calendar.getInstance().getTime()))
                .toString());
        } catch (Exception e) {
            log.log(LogLevel.ERROR, e.getMessage());
            if (searchConditions == null) {
                results.setSearchConditions(getRequest().getParameter(
                    "search_conditions"));
            } else {
                results.setSearchConditions(searchConditions);
                results.setError(true);
            }
        }
        return results;
    }
}
