/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.intra_mart.common.platform.log.Logger;
import jp.co.intra_mart.foundation.scenario.ScriptRuntimeException;
import jp.co.intra_mart.foundation.solr.display.DynamicDocTypeHandler;
import jp.co.intra_mart.foundation.solr.display.FacetInfo;
import jp.co.intra_mart.framework.base.service.ServiceManager;
import jp.co.intra_mart.framework.base.web.bean.HelperBeanException;
import jp.co.intra_mart.framework.base.web.util.HTMLEncoder;
import jp.co.intra_mart.system.javascript.imapi.RuntimeObject;
import jp.co.intra_mart.system.javascript.imapi.ValueObject;
import jp.co.intra_mart.system.session.web.context.PresentationPagePropertyHandler;
import jp.co.nttdata.intra_mart.solr.exception.SearchException;
import jp.co.nttdata.intra_mart.solr.model.FacetCount;
import jp.co.nttdata.intra_mart.solr.model.FacetCountList;
import jp.co.nttdata.intra_mart.solr.model.IntramartInputDocument;
import jp.co.nttdata.intra_mart.solr.model.SearchResult;
import jp.co.nttdata.intra_mart.solr.model.SearchResultItem;
import jp.co.nttdata.intra_mart.solr.util.Config;

import com.biz_integral.crm.extension.solr.BizIntegralSolrManager;
import com.biz_integral.crm.extension.solr.display.SolrDisplayManager;

/**
 * Solr検索結果画面HelperBean.<br>
 * Solrサーバへの問い合わせ結果を表示する為の<br>
 * ヘルパークラスです。
 */
public class SolrSearchResultHelperBean extends AbstractSolrSearchHelperBean {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = -3398934125786649251L;

    /** logger */
    private static Logger logger = Logger.getLogger();

    /** デフォルトDateフォーマット */
    private static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

    /** 絞込み検索　トグルID属性プレフィックス */
    public static final String DOC_TYPE_ID_PREFIX = "imcs_toggle_";

    /** 絞込み検索　トグルID属性サフィックス */
    public static final String DOC_TYPE_ID_SUFFIX = "_a";

    /** 絞込み検索　ファセットID属性プレフィックス */
    public static final String FACET_ID_PREFIX = "facet_";

    /** Solrディスプレイマネージャ */
    private SolrDisplayManager manager;

    /** プレゼンテーションページハンドラ */
    private PresentationPagePropertyHandler pagehandler;

    /** 設定ファイル情報格納クラス */
    private Config conf;

    /** 検索結果アイテムリスト */
    private List<SearchResultItem> itemList;

    /** 検索結果対象アイテム */
    private SearchResultItem item;

    /** 検索結果対象文書種別 */
    private String targetType;

    /** ファセット情報マップ */
    private LinkedHashMap<String, FacetInfo> facetmap;

    /** ファセット表示フラグ */
    private boolean isShowFacets;

    /** ファセット条件表示文字列 */
    private String facetSearchStr;

    /** ファセット絞込み画面HTML */
    private String facetSearchHtml;

    /** 子要素を持つ文書種別数 */
    private int toggleCount;

    /** 文書種別数 */
    private int docTypeCount;

    /** 合計検索結果数 */
    private long dataTotalCount;

    /** 検索結果数 */
    private long dataCount;

    /** 検索開始位置 */
    private long offset;

    /** エンコーディング */
    private String encoding;

    /** 要約の表示 */
    private String despalySnaippets;

    /** エラーフラグ */
    private boolean isError;

    /** 初期表示フラグ */
    private boolean isInit;

    /** JSページ読み込みエラー時表示HTML */
    private static String[] errorPageHtml;

    static {
        errorPageHtml = new String[2];
        StringBuffer buf = new StringBuffer();
        buf.append("<table width=\"100%\"><tr><td><font color=\"red\">");
        errorPageHtml[0] = buf.toString();
        buf = new StringBuffer();
        buf.append("</font></td><tr></table>");
        errorPageHtml[1] = buf.toString();
    }

    /**
     * コンストラクタ
     * 
     * @throws HelperBeanException
     */
    public SolrSearchResultHelperBean() throws HelperBeanException {
        super();
        facetmap = new LinkedHashMap<String, FacetInfo>();
    }

    /**
     * 画面の初期化
     * 
     * @throws HelperBeanException
     *             AcknowledgeListHelperBeanの生成時に例外が発生
     */
    @Override
    public void init() throws HelperBeanException {
        ServiceManager serviceManager = null;

        if (getRequest().getParameter("caller") != null
            && getRequest().getParameter("caller").equals("menu")) {
            isInit = true;
            return;
        }

        // 検索結果を取得
        SearchResult searchResult =
            (SearchResult) getRequest().getAttribute("SOLR_SEARCH_RESULT");

        isError = (Boolean) getRequest().getAttribute("SOLR_SEARCH_ERROR");

        try {
            serviceManager = ServiceManager.getServiceManager();
            encoding = serviceManager.getEncoding(getRequest(), getResponse());
        } catch (Exception e) {
            throw new HelperBeanException(e.getMessage());
        }

        this.manager = SolrDisplayManager.getInstance();
        this.pagehandler = PresentationPagePropertyHandler.instance();

        // 検索結果が存在する場合
        if (searchResult != null) {

            despalySnaippets = getRequest().getParameter("display_snippets");
            itemList = searchResult.getResultItems();
            List<FacetCountList> facetCountList =
                searchResult.getFacetCountLists();
            dataTotalCount = searchResult.getNumberFound();
            dataCount = searchResult.getNumberReturned();
            offset = searchResult.getOffset();

            try {
                // conf = IntramartSolrManager.getCachedConfig();
                conf = BizIntegralSolrManager.getCachedConfig();
            } catch (SearchException e) {
                throw new HelperBeanException(e.getMessage());
            }

            // ファセット情報Mapの作成
            createFacetMap(facetCountList);
        }
    }

    /**
     * ファセット情報Mapを作成します。
     * 
     * @param facetCountList
     *            ファセットカウントリスト
     * @throws HelperBeanException
     */
    private void createFacetMap(List<FacetCountList> facetCountList)
        throws HelperBeanException {
        DynamicDocTypeHandler tempHandler = null;
        Map<String, FacetInfo> map = new LinkedHashMap<String, FacetInfo>();
        FacetCountList targetList = null;
        int facetcheckparent = 0;
        int facetcheckchild = 0;
        long facetSearchCount = 0;
        // 動的種別を都度読みする場合
        if (!manager.isOnMemory()) {
            // 動的文書種別ハンドラのインスタンス化
            tempHandler = new DynamicDocTypeHandler();
        }

        for (FacetCountList list : facetCountList) {
            // ファセット対象フィールドが業務種別
            if (list.getName().equals(IntramartInputDocument.FIELD_TYPE)
                && list.getFacetCounts() != null
                && list.getFacetCounts().size() >= 1) {
                targetList = list;
                break;
            }
        }

        if (targetList == null)
            return;

        for (FacetCount facet : targetList.getFacetCounts()) {
            FacetInfo info = null;

            // 自身の種別
            String type = (String) facet.getExpression();

            if (!manager.isTypeExist(type)) {
                continue;
            }

            // 親の業務種別を取得
            String[] parentstypes = manager.getParentIds(type);

            // 現在絞り込みが行われている階層と同階層の場合
            try {
                if (getFacetSearchDepth() != -1
                    && getFacetSearchDepth() >= parentstypes.length) {
                    if (getFacetSearchDepth() == parentstypes.length) {
                        facetSearchStr =
                            manager.createTypeNameWithDepth(
                                tempHandler,
                                type,
                                getContext());
                        facetSearchCount = facet.getCount();
                    }
                    // ファセットリンク表示対象外
                    continue;
                }

                if (!map.containsKey(type)) {
                    info =
                        new FacetInfo(type, manager.getTypeName(
                            tempHandler,
                            type,
                            getContext()), facet.getCount());
                    info.setDepth(parentstypes.length);
                    map.put(type, info);
                } else {
                    info = map.get(type);
                    if (info.getName() == null) {
                        info.setName(manager.getTypeName(
                            tempHandler,
                            type,
                            getContext()));
                        info.setDocumentCount((facet.getCount()));
                    }
                    info.setDepth(parentstypes.length);
                }
            } catch (IOException e) {
                throw new HelperBeanException(e.getMessage(), e);
            }

            // 子種別の場合一つ上の親種別のファセット情報に追加
            if ((parentstypes.length > 0 && getFacetSearchDepth() == -1)
                || (getFacetSearchDepth() != -1 && parentstypes.length > getFacetSearchDepth() + 1)) {
                String parentType = parentstypes[parentstypes.length - 1];
                if (!map.containsKey(parentType)) {
                    map.put(parentType, new FacetInfo(parentType));
                }
                map.get(parentType).addChildFacet(info);
                if (map.get(parentType).getChildFacetInfo().size() > 1) {
                    facetcheckchild++;
                }
            } else {
                facetmap.put(type, info);
                facetcheckparent++;
            }
        }

        // ファセット絞込み表示判定
        // 最上位の親種別が２つ以上いるまたは、一つの親種別に二つ以上の子種別がいる場合
        if (facetcheckparent > 1 || facetcheckchild > 0) {
            // ファセットを表示
            isShowFacets = true;

        } else if (facetcheckparent == 1) {

            FacetInfo one = facetmap.entrySet().iterator().next().getValue();
            if (one.getChildFacetInfo().isEmpty()) {
                // 絞込み条件あり、子種別なし
                if (facetSearchCount > 0
                    && facetSearchCount != one.getDocumentCount()) {
                    // 絞込み条件の種別と配下の種別のカウント数が異なる場合
                    isShowFacets = true;
                }
            }
            // 親種別と子種別が一つずつの場合
            while (!one.getChildFacetInfo().isEmpty()) {

                if (one.getDocumentCount() != one
                    .getChildFacetInfo()
                    .get(0)
                    .getDocumentCount()) {
                    isShowFacets = true;
                    break;
                }
                one = one.getChildFacetInfo().get(0);
            }
        }
        if (isShowFacets) {
            createFacetHtml(null, 0);
        }
    }

    /**
     * 該当文書種別のテンプレートがスクリプト開発モデルか否かを 取得します。
     * 
     * @return スクリプト開発モデルか否かの判定結果
     * @throws HelperBeanException
     */
    public boolean isPresentationPage() throws HelperBeanException {
        String type = targetType;
        String templeteUrl = manager.getTempleteUrl(type);
        if (templeteUrl == null) {
            String message =
                getMessage("SOLR.display.display.error.templete_url_not_found");
            message = message + " type[" + type + "]";
            throw new HelperBeanException(message);
        }
        return templeteUrl.endsWith(pagehandler.getURLSuffix())
            || templeteUrl.endsWith(pagehandler.getNonSecureURLSuffix());
    }

    /**
     * パラメータ付テンプレートURLを取得します。<br>
     * 該当文書種別のテンプレートがスクリプト開発モデルではない場合に<br>
     * 呼び出されます。
     * 
     * @return パラメータ付テンプレートURL
     * @throws SearchException
     * @throws HelperBeanException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public String getTempleteUrlWithParams() throws HelperBeanException,
        SearchException, IOException {
        boolean appendFlg = false;
        String type = targetType;
        String templeteUrl = manager.getTempleteUrl(type);
        // テンプレートURLが見つからない場合
        if (templeteUrl == null) {
            String message =
                getMessage("SOLR.display.display.error.templete_url_not_found");
            message = message + " type[" + type + "]";
            throw new HelperBeanException(message);
        }

        if (!templeteUrl.startsWith("/"))
            templeteUrl = "/" + templeteUrl;

        // デフォルトフィールド
        List<String> fieldList = new ArrayList<String>();
        fieldList.add(IntramartInputDocument.FIELD_ID);
        fieldList.add(IntramartInputDocument.FIELD_TYPE);
        fieldList.add(IntramartInputDocument.FIELD_URL);
        fieldList.add(IntramartInputDocument.FIELD_ID_ORIGINAL);
        fieldList.add(IntramartInputDocument.FIELD_TITLE);
        // fieldList.add("display_snippets");

        // リクエスト要求フィールドを取得
        String[] fields = manager.getRequireFields(type);
        fieldList.addAll(Arrays.asList(fields));

        StringBuffer paramStr = new StringBuffer();
        paramStr.append(templeteUrl);

        for (String workField : fieldList) {

            // ロケール文字列を置換する。
            String field =
                MessageFormat.format(workField, getContext().getLocaleID());

            if (item.getField(field) == null
                || item.getField(field).toString().length() == 0) {
                continue;
            }

            if (!appendFlg) {
                paramStr.append("?");
                appendFlg = true;
            } else {
                paramStr.append("&");
            }

            if (field.equals(IntramartInputDocument.FIELD_TYPE)) {
                paramStr.append(encode(field) + "=");
                paramStr.append(encode(type));
                continue;
            }

            // マルチバリュー
            if (item.getField(field) instanceof List) {
                List<Object> paramList = (List<Object>) item.getField(field);
                int index = 0;
                for (Object param : paramList) {
                    // Date型フィールド
                    if (param instanceof Date
                        && (manager.getDateFormat(type, field) != null || manager
                            .getDefaultDateFormat(type) != null)) {
                        if (index > 0) {
                            paramStr.append("&");
                        }
                        setDateField(type, field, paramStr);

                    } else {
                        if (index > 0) {
                            paramStr.append("&");
                        }
                        paramStr.append(encode(field) + "=");
                        paramStr.append(encode(param.toString()));
                    }
                    index++;
                }

                // Date型フィールド
            } else if (item.getField(field) instanceof Date
                && (manager.getDateFormat(type, field) != null || manager
                    .getDefaultDateFormat(type) != null)) {

                setDateField(type, field, paramStr);
            } else {
                paramStr.append(encode(field) + "=");
                paramStr.append(encode(item.getField(field).toString()));
            }
        }

        // 要約を表示する場合、要約情報を設定
        if (despalySnaippets.equals("on")) {
            addSnaippets(paramStr, appendFlg);
        }

        return paramStr.toString();
    }

    /**
     * パラメータ付テンプレートURLを取得します。<br>
     * 該当文書種別のテンプレートがスクリプト開発モデルではない場合に<br>
     * 呼び出されます。
     * 
     * @return パラメータ付テンプレートURL
     * @throws SearchException
     * @throws HelperBeanException
     * @throws ScriptRuntimeException
     * @throws UnsupportedEncodingException
     * @throws SearchException
     * @throws FileNotFoundException
     */
    @SuppressWarnings("unchecked")
    public String getPresentationPageHTML() throws HelperBeanException,
        ScriptRuntimeException, UnsupportedEncodingException, SearchException,
        FileNotFoundException {
        String html = "";
        String type = targetType;
        String templeteUrl = manager.getTempleteUrl(type);
        // テンプレートURLが見つからない場合
        if (templeteUrl == null) {
            String message =
                getMessage("SOLR.display.display.error.templete_url_not_found");
            message = message + " type[" + type + "]";
            throw new HelperBeanException(message);
        }

        // urlに拡張子（".jssp"、".jssps"）があれば、削除
        if (templeteUrl.endsWith(pagehandler.getURLSuffix())
            || templeteUrl.endsWith(pagehandler.getNonSecureURLSuffix())) {
            templeteUrl =
                templeteUrl.substring(0, templeteUrl.lastIndexOf(pagehandler
                    .getNonSecureURLSuffix()));
        }

        // リクエストパラメータの設定
        HashMap<String, Object> map = new HashMap<String, Object>();
        // デフォルトフィールド
        map.put(IntramartInputDocument.FIELD_ID, item
            .getField(IntramartInputDocument.FIELD_ID) == null ? "" : item
            .getField(IntramartInputDocument.FIELD_ID)
            .toString());
        map.put(IntramartInputDocument.FIELD_TYPE, type);
        map.put(IntramartInputDocument.FIELD_URL, item
            .getField(IntramartInputDocument.FIELD_URL) == null ? "" : item
            .getField(IntramartInputDocument.FIELD_URL)
            .toString());
        map.put(IntramartInputDocument.FIELD_ID_ORIGINAL, item
            .getField(IntramartInputDocument.FIELD_ID_ORIGINAL) == null
            ? ""
            : item
                .getField(IntramartInputDocument.FIELD_ID_ORIGINAL)
                .toString());
        map.put(IntramartInputDocument.FIELD_TITLE, item
            .getField(IntramartInputDocument.FIELD_TITLE) == null ? "" : item
            .getField(IntramartInputDocument.FIELD_TITLE)
            .toString());
        map.put("display_snippets", getDisplaySnippets());

        // 要求フィールド
        String[] fields = manager.getRequireFields(type);
        StringBuffer paramStr = new StringBuffer();
        paramStr.append(templeteUrl);

        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            if (item.getField(field) == null
                || item.getField(field).toString().length() == 0) {
                continue;
            }

            // マルチバリュー
            if (item.getField(field) instanceof List) {
                List<Object> paramList = (List<Object>) item.getField(field);
                String[] params = new String[paramList.size()];

                for (int j = 0; j < paramList.size(); j++) {
                    Object param = paramList.get(j);

                    // Date型フィールド
                    if (param instanceof Date
                        && (manager.getDateFormat(type, field) != null || manager
                            .getDefaultDateFormat(type) != null)) {

                        params[j] =
                            getDateFieldDateFormat(type, field).format(param);
                    } else {
                        params[j] = param.toString();
                    }
                }
                map.put(field, RuntimeObject.newArrayInstance(params));

                // Date型フィールド
            } else if (item.getField(field) instanceof Date
                && (manager.getDateFormat(type, field) != null || manager
                    .getDefaultDateFormat(type) != null)) {

                map.put(field, getDateFieldDateFormat(type, field).format(
                    item.getField(field)));
            } else {
                map.put(field, item.getField(field).toString());
            }
        }

        // 要約を表示する場合、要約情報を設定
        if (despalySnaippets.equals("on")) {
            addSnaippets(map);
        }
        // ＨＴＭＬソースの生成
        SolrPresentationPage page =
            new SolrPresentationPage(getRequest(), getResponse());

        try {
            html =
                page
                    .execute(templeteUrl, new Object[] { new ValueObject(map) });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String message =
                getMessage(
                    "SOLR.display.search.error.presentation_page_execute",
                    type);
            html = errorPageHtml[0] + message + errorPageHtml[1];
        }
        return html;
    }

    /**
     * マップオブジェクトに要約情報を追加します。
     * 
     * @param map
     *            　マップオブジェクト
     * @throws SearchException
     * @throws SearchException
     * @throws SearchException
     */
    private void addSnaippets(HashMap<String, Object> map)
        throws SearchException {
        String prefix = "snippet_";

        List<String> snaippetFields = new ArrayList<String>();

        // ngram解析を使用する
        if (conf.getUseNgram()) {
            snaippetFields.add(IntramartInputDocument.FIELD_TEXT_NGRAM);
        }

        // 形態素解析を使用する
        if (conf.getUseMorph()) {
            snaippetFields.add(IntramartInputDocument.FIELD_TEXT_MORPH);
        }

        for (String snaippetField : snaippetFields) {

            List<String> list = item.getSnippetList(snaippetField);
            if (list == null)
                continue;

            String[] snaippets = new String[list.size()];

            for (int i = 0; i < list.size(); i++) {

                snaippets[i] = editSnappetsHighlight(list.get(i));
            }
            map.put(prefix + snaippetField, RuntimeObject
                .newArrayInstance(snaippets));
        }
    }

    /**
     * 対象文字列に要約情報を追加します。
     * 
     * @param paramStr
     *            　対象文字列
     * @param appendFlg
     *            パラメータ情報付加フラグ
     * @throws SearchException
     * @throws HelperBeanException
     * @throws UnsupportedEncodingException
     */
    private void addSnaippets(StringBuffer paramStr, boolean appendFlg)
        throws HelperBeanException, SearchException,
        UnsupportedEncodingException {
        String prefix = "snippet_";

        List<String> snaippetFields = new ArrayList<String>();

        // ngram解析を使用する
        if (conf.getUseNgram()) {
            snaippetFields.add(IntramartInputDocument.FIELD_TEXT_NGRAM);
        }

        // 形態素解析を使用する
        if (conf.getUseMorph()) {
            snaippetFields.add(IntramartInputDocument.FIELD_TEXT_MORPH);
        }

        for (String snaippetField : snaippetFields) {

            List<String> list = item.getSnippetList(snaippetField);
            if (list == null)
                continue;

            for (String snippet : list) {
                if (!appendFlg) {
                    paramStr.append("?");
                    appendFlg = true;
                } else {
                    paramStr.append("&");
                }
                paramStr.append(encode(prefix + snaippetField) + "=");
                paramStr.append(encode(editSnappetsHighlight((snippet))));
            }
        }
    }

    /**
     * ハイライト表示の設定を参照し、ハイライトを表示しない場合は <br>
     * 要約からハイライトタグを除去します。<br>
     * 
     * @param snappet
     *            要約
     * @return ハイライト表示の設定に沿った要約
     */
    private String editSnappetsHighlight(String snappet) {
        // ハイライト表示を行わない場合
        if (!manager.useHighlight()) {
            // solrハイライトタグを除去して返却
            return snappet
                .replaceAll(SolrDisplayManager.SOLR_HIGHLIGHT_PRE, "")
                .replaceAll(SolrDisplayManager.SOLR_HIGHLIGHT_POST, "");
        }
        return snappet;
    }

    /**
     * ドキュメント種別を取得します。 <br>
     * ドキュメント種別が階層構造を持つ場合は<br>
     * 最下(=自身)位のドキュメント種別を取得します。
     * 
     * @return ドキュメント種別
     */
    @SuppressWarnings("unchecked")
    public String getDocumentType() {
        String targetType = "";

        if (item.getField(IntramartInputDocument.FIELD_TYPE) instanceof List) {
            int parentsNumber = -1;
            List<String> typeList =
                (List<String>) item.getField(IntramartInputDocument.FIELD_TYPE);
            for (String type : typeList) {
                if (!manager.isTypeExist(type)) {
                    targetType = type;
                    break;
                }

                if (manager.getParentIds(type).length > parentsNumber) {
                    parentsNumber = manager.getParentIds(type).length;
                    targetType = type;
                }
            }
        } else {
            targetType =
                (String) item.getField(IntramartInputDocument.FIELD_TYPE);
        }
        return targetType;
    }

    /**
     * Date型のフィールド値を指定フォーマットで変換して<br>
     * パラメータ文字列に追加します。
     * 
     * @param type
     *            文書種別
     * @param field
     *            フィールド名
     * @param paramStr
     *            パラメータ文字列
     * @throws UnsupportedEncodingException
     *             フォーマットエラー
     */
    private void setDateField(String type, String field, StringBuffer paramStr)
        throws UnsupportedEncodingException {
        paramStr.append(encode(field) + "=");
        paramStr.append(encode(getDateFieldDateFormat(type, field).format(
            item.getField(field))));
    }

    /**
     * 文書種別とフィールドに対応するDateフォーマットを取得します。
     * 
     * @param type
     *            文書種別
     * @param field
     *            フィールド名 パラメータ文字列
     * 
     * @return Dateフォーマット
     */
    private DateFormat getDateFieldDateFormat(String type, String field) {

        DateFormat df = null;
        // フィールドにDateフォーマットが存在する場合
        if (manager.getDateFormat(type, field) != null) {
            df = new SimpleDateFormat(manager.getDateFormat(type, field));
            // 文書種別にデフォルトDateフォーマットが存在する場合
        } else if (manager.getDefaultDateFormat(type) != null) {
            df = new SimpleDateFormat(manager.getDefaultDateFormat(type));
        } else {
            df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        }
        return df;
    }

    /**
     * 検索結果一覧から該当インデックスのアイテムを処理対象に設定します。
     * 
     * @param index
     *            アイテムのインデックス
     * @return 設定ファイルに文書種別が存在した場合はtrue
     */
    public boolean setTargetItem(int index) {
        this.item = itemList.get(index);
        this.targetType = getDocumentType();
        return (manager.isTypeExist(this.targetType) && manager
            .getTempleteUrl(this.targetType) != null);
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
     * ファセットマップを取得します。
     * 
     * @return ファセットマップ
     */
    public Map<String, FacetInfo> getFacetMap() {
        return this.facetmap;
    }

    /**
     * 要約の表示・非表示設定を返却します。
     * 
     * @return 要約の表示・非表示設定
     */
    public String getDisplaySnippets() {
        return this.despalySnaippets;
    }

    /**
     * エンコードを行います。
     * 
     * @param string
     *            対象文字列
     * @return エンコード後対象文字列
     * @throws UnsupportedEncodingException
     */
    public String encode(String string) throws UnsupportedEncodingException {

        return URLEncoder.encode(string, this.encoding);
    }

    /**
     * 合計検索結果数を返却します。
     * 
     * @return 合計検索結果数
     */
    public long getDataTotalCount() {
        return dataTotalCount;
    }

    /**
     * 検索結果数を取得します。
     * 
     * @return 検索結果数
     */
    public long getDataCount() {
        return dataCount;
    }

    /**
     * 検索開始位置を返却します。
     * 
     * @return 検索開始位置
     */
    public long getOffset() {
        return offset;
    }

    /**
     * ファセットを表示するか否かを返却します。
     * 
     * @return ファセットを表示するか否か
     */
    public boolean isShowFacets() {
        return this.isShowFacets;
    }

    /**
     * ファセット条件表示文字列を返却します。
     * 
     * @return ファセット条件表示文字列
     */
    public String getFacetSearchStr() {
        return this.facetSearchStr;
    }

    /**
     * 絞込み条件のファセットの階層
     * 
     * @return 絞込み条件のファセットの階層
     */
    public int getFacetSearchDepth() {

        return getRequest().getParameter("facet_search_depth") != null
            ? Integer.parseInt(getRequest().getParameter("facet_search_depth"))
            : -1;
    }

    /**
     * エラーか否かを返却します。
     * 
     * @return エラーか否か
     */
    public boolean isError() {
        return this.isError;
    }

    /**
     * 初期表示か否かを返却します。
     * 
     * @return 初期表示か
     */
    public boolean isInit() {
        return this.isInit;
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

    /**
     * トグルの件数を返却します。
     * 
     * @return トグルの件数
     */
    public int getToggleCount() {
        return this.toggleCount;
    }

    /**
     * 絞込み検索用HTML文字列を取得します。
     * 
     * @return 絞込み検索用HTML文字列
     */
    public String getFacetHtml() {
        return this.facetSearchHtml;
    }

    /**
     * 絞込み検索用HTML文字列を取得します。
     * 
     * @param list
     *            ファセット情報一覧
     * @param index
     *            インデックス
     * 
     * @return 絞込み検索用HTML文字列
     */
    private String createFacetHtml(List<FacetInfo> list, int index) {
        StringBuffer buf = new StringBuffer();
        int facetDepth = manager.getFacetDepth();
        String toggleId = "";
        if (index == 0) {
            FacetInfo root = new FacetInfo();
            root.setChildFacetInfo(new ArrayList<FacetInfo>(facetmap.values()));
            list = root.getSortedChildFacetInfo();

            if (getFacetSearchStr() != null) {
                buf.append("<table>");
                buf.append("<tr>");
                buf.append("<td nowrap><strong>"
                    + getFacetSearchStr()
                    + "</td>");
                buf.append("</tr>");
                buf.append("</table>");
            }
        }
        if (index == facetDepth) {
            return "";
        }

        for (FacetInfo info : list) {
            docTypeCount++;
            buf.append("<table>");
            buf.append("<tr>");
            for (int i = 0; i < index; i++) {
                buf.append("<td nowrap width = '15px'></td>");
            }

            if (!info.getChildFacetInfo().isEmpty() && index != facetDepth - 1) {
                toggleCount++;
                toggleId = DOC_TYPE_ID_PREFIX + toggleCount;
                buf.append("<td nowrap id = '" + toggleId);
                buf.append(DOC_TYPE_ID_SUFFIX + "' class='arrow_c'");
                buf.append(" onclick='javascript:toggleF(\""
                    + toggleId
                    + "\")'></td>");
            } else {
                buf
                    .append("<td  nowrap class='round' onclick='javascript:searchByFacets(");
                buf.append(docTypeCount + "," + info.getDepth());
                buf.append(")'></td>");
            }
            buf
                .append("<td nowrap><strong><A href='javascript:searchByFacets(");
            buf.append(docTypeCount + "," + info.getDepth());
            buf.append(")'>");
            buf.append("<input type=\"hidden\" id=\"");
            buf.append(FACET_ID_PREFIX + docTypeCount + "\"");
            buf.append(" value = \""
                + HTMLEncoder.encodeCaption(info.getId())
                + "\" >");
            buf.append(HTMLEncoder.encodeCaption(info.getName()));
            buf.append("&nbsp;(");
            buf.append(info.getDocumentCount());
            buf.append(")</A>&nbsp;&nbsp;&nbsp;&nbsp;</td>");
            buf.append("</tr>");
            buf.append("</table>");
            if (!info.getChildFacetInfo().isEmpty()) {
                buf
                    .append("<div id= '"
                        + toggleId
                        + "' style='display:none;'>");
                buf.append(createFacetHtml(
                    info.getSortedChildFacetInfo(),
                    index + 1));
                buf.append("</div>");
            }
        }
        if (index == 0) {
            this.facetSearchHtml = buf.toString();
        }
        return buf.toString();
    }

    /**
     * タイトルを取得します。<br>
     * リソースキーは{@link #getBizMessage(String)}を利用して名称を取得します。<br>
     * 
     * @param key
     *            Biz用のリソースキー
     * @return リソース名称 会社名[会社コード]
     * @throws HelperBeanException
     *             ログインユーザ情報取得時に例外が発生
     */
    public String getTitle(String key) throws HelperBeanException {

        return getBizMessage("CRM.CC.solrApplicationFullSearch");
    }
}
