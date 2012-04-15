package com.biz_integral.crm.extension.solr.util;

import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import jp.co.intra_mart.foundation.solr.display.SolrDisplayManager;
import jp.co.intra_mart.framework.base.web.util.HTMLEncoder;

/**
 * Solr検索結果テンプレートユーティリティー.<br />
 * Solr検索結果テンプレート表示のユーティリティークラスです。<br>
 * 
 * @name TemplateViewUtil
 * @scope public
 * @since 7.1
 * @author INTRAMART
 * @version 1.0
 */
public class TemplateViewUtil {

    /** リクエスト */
    HttpServletRequest request;

    /**
     * コンストラクタ
     */
    public TemplateViewUtil() {
    }

    /**
     * コンストラクタ
     * 
     * @param request
     *            リクエスト
     */
    public TemplateViewUtil(HttpServletRequest request) {
        this.request = request;

        // for (Map.Entry entry : request.getParameterMap().entrySet()) {
        //
        // }
        // Map map = request.getParameterMap();
        // map.
        // for (Map.Entry<String, String> entry : map.entrySet()) {
        // entry.getKey();
        // entry.getValue();
        // }
    }

    /**
     * パラメータにロケールを追加します。<br>
     * パラメータcrm_account_name_{0}_stringと日本語ロケールが渡された場合、<br>
     * crm_account_name_ja_stringのように変換して返却します。
     * 
     * @param param
     *            パラメータ（ロケール名に変換する箇所を{0}で記述）
     * @param locale
     *            ロケール
     * @return パラメータ（ロケール対応のパラメータに変換）
     */
    public String getLocaleParam(String param, Locale locale) {
        return MessageFormat.format(param, locale.getLanguage());
    }

    /**
     * リクエストから指定したパラメータの値を返却します。<br>
     * パラメータが存在しない場合は空文字を返却します。
     * 
     * @param param
     *            　パラメータ
     * @return パラメータの値
     */
    public String getRequestParam(String param) {
        String value = null;
        if (request.getParameter(param) != null) {
            value = request.getParameter(param);
        }

        return value == null ? "" : value;
    }

    /**
     * リクエストから指定したパラメータの値をHTMLエンコードして返却します。<br>
     * パラメータが存在しない場合は空文字を返却します。
     * 
     * @param param
     *            　パラメータ
     * @return エンコードしたパラメータの値
     */
    public String getEncodedRequestParam(String param) {
        String value = null;
        if (request.getParameter(param) != null) {
            value = request.getParameter(param);
        }

        return value == null ? "" : HTMLEncoder.encodeCaption(value);
    }

    /**
     * リクエストから指定したパラメータの値をHTMLエンコードして返却します。<br>
     * パラメータは{@link #getLocaleParam(String, Locale)}を利用して変換されます<br>
     * パラメータが存在しない場合は空文字を返却します。
     * 
     * @param param
     *            パラメータ（ロケール名に変換する箇所を{0}で記述）
     * @param locale
     *            ロケール
     * @return エンコードしたパラメータの値
     */
    public String getEncodedRequestParam(String param, Locale locale) {
        return getEncodedRequestParam(getLocaleParam(param, locale));
    }

    /**
     * リクエストから指定したパラメータの値をHTMLエンコードしてString配列で返却します。<br>
     * パラメータが存在しない場合は空の配列を返却します。
     * 
     * @param param
     *            　パラメータ
     * @return エンコードしたパラメータの値配列
     */
    public String[] getEncodedRequestValues(String param) {
        String[] values = new String[0];
        if (request.getParameterValues(param) != null) {
            values = request.getParameterValues(param);
            for (int i = 0; i < values.length; i++) {
                values[i] = HTMLEncoder.encodeCaption(values[i]);
            }
        }

        return values;
    }

    /**
     * リクエストから指定したパラメータの値をHTMLエンコードしてString配列で返却します。<br>
     * パラメータは{@link #getLocaleParam(String, Locale)}を利用して変換されます<br>
     * パラメータが存在しない場合は空の配列を返却します。
     * 
     * @param param
     *            パラメータ（ロケール名に変換する箇所を{0}で記述）
     * @param locale
     *            ロケール
     * @return エンコードしたパラメータの値配列
     */
    public String[] getEncodedRequestValues(String param, Locale locale) {
        return getEncodedRequestValues(getLocaleParam(param, locale));
    }

    /**
     * リクエストから指定したパラメータの値をHTMLエンコードして返却します。<br>
     * パラメータに複数の値が含まれている場合はString配列、<br>
     * 一つのみの場合は文字列を返却します。<br>
     * 
     * パラメータが存在しない場合は空文字を返却します。
     * 
     * @param param
     *            　パラメータ
     * @return エンコードしたパラメータの値配列
     */
    public Object getEncodedRequestParameter(String param) {

        if (request.getParameterValues(param) != null
            && request.getParameterValues(param).length > 1) {
            return getEncodedRequestValues(param);
        }
        return getEncodedRequestParam(param);
    }

    /**
     * リクエストから指定したパラメータの値をHTMLエンコードして返却します。<br>
     * パラメータは{@link #getLocaleParam(String, Locale)}を利用して変換されます<br>
     * パラメータに複数の値が含まれている場合はString配列、<br>
     * 一つのみの場合は文字列を返却します。<br>
     * 
     * パラメータが存在しない場合は空文字を返却します。
     * 
     * @param param
     *            パラメータ（ロケール名に変換する箇所を{0}で記述）
     * @param locale
     *            ロケール
     * @return エンコードしたパラメータの値配列
     */
    public Object getEncodedRequestParameter(String param, Locale locale) {
        return getEncodedRequestParameter(getLocaleParam(param, locale));
    }

    /**
     * リクエストから要約表示のパラメータ値を取得して、<br>
     * 要約を表示するか否かを返却します。
     * 
     * @return 要約を表示するか否か
     */
    public boolean isDisplaySnippets() {
        return !getRequestParam("display_snippets").equals("off");
    }

    /**
     * リクエストから要約のパラメータ値を取得して、<br>
     * HTMLエンコード済みの要約を返却します。<br>
     * 但し、ハイライト表示のためのHTMLタグのエンコードは行いません。<br>
     * また要約内の改行コードは空文字に変換されます。
     * 
     * @return エンコード済みの要約
     */
    public String getEncodedSnippets() {
        SolrDisplayManager mng = SolrDisplayManager.getInstance();
        String snippetStr = "";
        String[] snippets = request.getParameterValues("snippet_text_ngram");
        if (snippets == null || snippets.length == 0) {
            snippets = request.getParameterValues("snippet_text_morph");
        }
        if (snippets == null) {
            snippets = new String[0];
        }

        StringBuffer buffer = new StringBuffer();
        for (String snippet : snippets) {
            if (buffer.length() > 0) {
                buffer.append(".....");
            }
            buffer.append(snippet);
        }

        // ハイライト表示を行わない場合
        if (!mng.useHighlight()) {
            // 改行タグを除去して返却
            return HTMLEncoder.encodeCaption(buffer.toString()).replaceAll(
                "<BR>|<br>",
                "");
        }

        snippetStr = HTMLEncoder.encodeCaption(buffer.toString());
        // エンコーディングしたsolrハイライト要素を元に戻す
        snippetStr =
            snippetStr.replaceAll(
                HTMLEncoder
                    .encodeCaption(SolrDisplayManager.SOLR_HIGHLIGHT_PRE),
                SolrDisplayManager.SOLR_HIGHLIGHT_PRE).replaceAll(
                HTMLEncoder
                    .encodeCaption(SolrDisplayManager.SOLR_HIGHLIGHT_POST),
                "</span>").replaceAll("<BR>|<br>", "");

        return snippetStr;
    }
}
