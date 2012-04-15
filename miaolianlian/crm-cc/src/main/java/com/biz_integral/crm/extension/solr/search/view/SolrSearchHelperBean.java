/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.intra_mart.foundation.solr.display.DocumentTypeInfo;
import jp.co.intra_mart.framework.base.web.bean.HelperBeanException;
import jp.co.intra_mart.framework.base.web.util.HTMLEncoder;

import com.biz_integral.crm.extension.solr.display.SolrDisplayManager;

/**
 * Solr検索画面HelperBean.<br>
 * Solrサーバへの問い合わせ画面の為の<br>
 * ヘルパークラスです。
 * 
 */
public class SolrSearchHelperBean extends AbstractSolrSearchHelperBean {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = 5938826896980918298L;

    /** Solrディスプレイマネージャ */
    private SolrDisplayManager manager;

    /** 文書種別リスト */
    private List<HashMap<String, String>> documentTypeList;

    /** 表示件数リスト */
    private List<HashMap<String, String>> displayCountList;

    /** 検索条件 */
    private String searchConditions;

    /** 検索結果無しフラグ */
    private boolean detaNotFoundFlg = false;

    /** エラーフラグ */
    private boolean isError;

    /**
     * コンストラクタ
     * 
     * @throws HelperBeanException
     */
    public SolrSearchHelperBean() throws HelperBeanException {
        super();
    }

    /**
     * 画面の初期化
     * 
     * @throws HelperBeanException
     *             AcknowledgeListHelperBeanの生成時に例外が発生
     */
    @Override
    public void init() throws HelperBeanException {

        if (getRequest().getAttribute("dataNoFound") != null
            && (Boolean) getRequest().getAttribute("dataNoFound")) {
            detaNotFoundFlg = true;
            isError = (Boolean) getRequest().getAttribute("SOLR_SEARCH_ERROR");
        }

        this.manager = SolrDisplayManager.getInstance();

        // 文章種別リストの作成
        createDocTypeList();

        // 表示件数リストの作成
        createDisplayCountList();

        searchConditions =
            (String) getRequest().getAttribute("SOLR_SEARCH_CONDITIONS");

    }

    /**
     * 業務種別コンボを作成します。
     * 
     * @exception HelperBeanException
     *                メッセージ取得時例外
     */
    private void createDocTypeList() throws HelperBeanException {
        LinkedHashMap<String, DocumentTypeInfo> map = manager.getTypeMap();

        Iterator<Map.Entry<String, DocumentTypeInfo>> ite =
            map.entrySet().iterator();

        documentTypeList = new ArrayList<HashMap<String, String>>();
        while (ite.hasNext()) {
            Map.Entry<String, DocumentTypeInfo> entry = ite.next();
            if (!entry.getValue().isDisplayCombo()) {
                continue;
            }
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("text", getBizMessage(entry
                .getValue()
                .getDisplayStringKey()));
            temp.put("value", entry.getKey());

            documentTypeList.add(temp);
        }
    }

    /**
     * 業務種別コンボを取得します。
     * 
     * @return 業務種別コンボ
     */
    public List<HashMap<String, String>> getDocTypeList() {

        return documentTypeList;
    }

    /**
     * 業務種別コンボの選択値リストを取得します。
     * 
     * @return 業務種別コンボの選択値リスト
     */
    public List<String> getSelectedType() {

        List<String> sel = new ArrayList<String>();

        if (getRequest().getParameter("doc_type") != null) {
            sel.add(getRequsetKeyWords("doc_type"));
        }
        return sel;
    }

    /**
     * 表示件数コンボを作成します。
     * 
     */
    private void createDisplayCountList() {
        String[] displayCounts = manager.getDisplayCounts();

        displayCountList = new ArrayList<HashMap<String, String>>();
        for (String displayCount : displayCounts) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("text", displayCount
                + getMessage("SOLR.display.search.label.display_count.unit"));
            temp.put("value", displayCount);

            displayCountList.add(temp);

        }
    }

    /**
     * 表示件数コンボを取得します。
     * 
     * @return 表示件数コンボ
     */
    public List<HashMap<String, String>> getDisplayCountList() {

        return displayCountList;
    }

    /**
     * 表示件数コンボの選択値リストを取得します。
     * 
     * @return 表示件数コンボの選択値リスト
     */
    public List<String> getSelectedDisplayCount() {

        List<String> sel = new ArrayList<String>();

        if (getRequest().getParameter("display_count") != null) {
            sel.add(getRequsetKeyWords("display_count"));
        } else {
            sel.add(manager.getDefaultDisplayCount());
        }
        return sel;
    }

    /**
     * リクエストから該当キーの値を取得します。
     * 
     * @param key
     *            キー
     * @return 値
     */
    public String getRequsetKeyWords(String key) {

        String value = null;
        if (getRequest().getParameter(key) != null) {
            value = getRequest().getParameter(key);
        }

        if (value == null
            || (key.equals("search_conditions") && value.length() == 0)) {
            value = (String) getRequest().getAttribute(key);
        }
        return value == null ? "" : HTMLEncoder.encodeValue(value);
    }

    /**
     * 単純検索の検索文字列を取得します。
     * 
     * @return 単純検索の検索文字列
     */
    public String getSearchConditions() {
        return this.searchConditions == null ? "" : HTMLEncoder
            .encodeValue(searchConditions);
    }

    /**
     * 検索結果が存在するか否かを返却します。
     * 
     * @return 検索結果が存在するか否か
     */
    public boolean isDetaNotFoundFlg() {
        return detaNotFoundFlg;
    }

    /**
     * エラーメッセージを返却します。
     * 
     * @return エラーメッセージ
     */
    public String getErrorMessage() {

        if (this.isError
            && getRequest().getAttribute("SOLR_SEARCH_ERROR_MSG_KEY") != null) {
            return getMessage((String) getRequest().getAttribute(
                "SOLR_SEARCH_ERROR_MSG_KEY"));
        } else if (this.isError) {
            return getMessage("SOLR.display.search.error.failed.get_document");
        }
        return getMessage("SOLR.display.search.error.document_not_found");
    }
}
