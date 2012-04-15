/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.intra_mart.foundation.scenario.PresentationPage;

/**
 * Solr用プレゼンテーション・ページ実行環境提供クラス.<br>
 * PresentationPageクラスの具象クラス<br>
 * プレゼンテーション・ページとなるスクリプトを コンパイルして実行可能な環境を提供します。
 * 
 * @name SolrPresentationPage
 * @scope public
 * @since 7.1
 * @author INTRAMART
 * @version 1.0
 */
class SolrPresentationPage extends PresentationPage {

    /**
     * 任意のセッションを持つページ作成オブジェクトを構築します。<br>
     * <br>
     * 
     * @param request
     *            サーブレットの引数オブジェクト
     * @param response
     *            サーブレットのレスポンスオブジェクト
     * @exception IllegalArgumentException
     *                引数のいずれかが NULL である場合に スローされます
     */
    public SolrPresentationPage(HttpServletRequest request,
            HttpServletResponse response) throws IllegalArgumentException {
        super(request, response);

    }
}