/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.model;

import jp.co.nttdata.intra_mart.solr.model.IntramartInputDocument;

import com.biz_integral.crm.extension.solr.constants.BizIntegralSolrConstants;
import com.biz_integral.foundation.core.util.StringUtil;

/**
 * Biz用のSolr登録用ドキュメントのインターフェース
 */
public interface BizIntegralInputDocument extends IntramartInputDocument {

    /** 会社コードのfield名 */
    public static final String FIELD_COMPANY = "company";
    /** ロケールIDのfield名 */
    public static final String FIELD_LOCALE = "locale";
    /** ngram名称マッチング文字列のfield名 */
    public static final String FIELD_NAME_SYNONYM_NGRAM = "name_synonym_ngram";
    /** morph名称マッチング文字列のfield名 */
    public static final String FIELD_NAME_SYNONYM_MORPH = "name_synonym_morph";

    /**
     * 会社コードを追加します。
     * 
     * @param s
     *            会社コード
     */
    public abstract void addCompany(String s);

    /**
     * 会社コードを設定します。
     * 
     * @param s
     *            会社コード
     */
    public abstract void setCompany(String s);

    /**
     * ロケールIDを追加します。
     * 
     * @param s
     *            ロケールID
     */
    public abstract void addLocale(String s);

    /**
     * ロケールIDを設定します。
     * 
     * @param s
     *            ロケールID
     */
    public abstract void setLocale(String s);

    /**
     * 名称マッチング文字列を追加します。<br>
     * ngram,morphの両フィールドに追加します。<br>
     * 名称マッチング文字列を{@link StringUtil#isEmpty(String)}で判定します。<br>
     * 空の場合は追加されません。
     * 
     * @param s
     *            名称マッチング文字列
     */
    public abstract void addNameSynonym(String s);

    /**
     * 名称マッチング文字列を設定します。<br>
     * ngram,morphの両フィールドに設定します。<br>
     * 名称マッチング文字列を{@link StringUtil#isEmpty(String)}で判定します。<br>
     * 空の場合は設定されません。
     * 
     * @param s
     *            名称マッチング文字列
     */
    public abstract void setNameSynonym(String s);

    /**
     * 検索文字列を追加します。<br>
     * ngram,morphの両フィールドに追加します。<br>
     * 検索文字列を{@link StringUtil#isEmpty(String)}で判定します。<br>
     * 空の場合は追加されません。
     * 
     * @param s
     *            検索文字列
     */
    public abstract void addTextWithNullCheck(String s);

    /**
     * 検索文字列を設定します。<br>
     * ngram,morphの両フィールドに追加します。<br>
     * 検索文字列を{@link StringUtil#isEmpty(String)}で判定します。<br>
     * 空の場合は設定されません。
     * 
     * @param s
     *            検索文字列
     */
    public abstract void setTextWithNullCheck(String s);

    /**
     * フィールドにオブジェクトを追加します。<br>
     * オブジェクトが文字列の場合、{@link StringUtil#isEmpty(String)}で判定します。<br>
     * 空の場合は追加されません。
     * 
     * @param s
     *            フィールド
     * @param obj
     *            オブジェクト
     */
    public abstract void addFieldWithNullCheck(String s, Object obj);

    /**
     * フィールドにオブジェクトを設定します。<br>
     * オブジェクトが文字列の場合、{@link StringUtil#isEmpty(String)}で判定します。<br>
     * 空の場合は設定されません。
     * 
     * @param s
     *            フィールド
     * @param obj
     *            オブジェクト
     */
    public abstract void setFieldWithNullCheck(String s, Object obj);

    /**
     * 文書の対応する検索方法を設定します。<br>
     * 対応する機能の列挙体{@link BizIntegralSolrConstants.SEARCH_FUNCTION} を設定します。<br>
     * 
     * @param function
     *            対応機能
     * @param flag
     *            判定
     */
    public abstract void setFunction(
            BizIntegralSolrConstants.SEARCH_FUNCTION function, String flag);
}
