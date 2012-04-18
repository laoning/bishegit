/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.util;

import java.util.Locale;

import org.seasar.extension.jdbc.name.PropertyName;
import org.seasar.framework.util.StringUtil;

/**
 * solrのユーティリティクラス<br>
 * solrのダイナミックフィールドの名称を作成します。<br>
 * ＤＢの型や国際化対応、1レコードに対する複数データの有無に応じて、<br>
 * 適切な名称を作成する必要があります。<br>
 */
public class SolrUtil {

    /**
     * ダイナミックフィールドのデータ型を表す列挙体です。
     */
    public enum DATA_TYPE_SUFFIX {
        /** 文字列 */
        STRING("_string"),
        /** int数値 */
        INTEGER("_integer"),
        /** long数値 */
        LONG("_long"),
        /** 日付 */
        DATE("_date");

        /** データ型を表す接尾語 */
        private String key;

        /**
         * コンストラクタ
         * 
         * @param key
         *            データを表す接尾語
         */
        private DATA_TYPE_SUFFIX(String key) {
            this.key = key;
        }

        /**
         * データを表す接尾語を返却します。
         * 
         * @return データを表す接尾語
         */
        public String key() {
            return key;
        }
    }

    // /** _string ダイナミックフィールドの接尾語 */
    // public static final String SOLR_DYNAMIC_FIELD_STRING_SUFFIX = "_string";
    // /** _integer ダイナミックフィールドの接尾語 */
    // public static final String SOLR_DYNAMIC_FIELD_INTEGER_SUFFIX =
    // "_integer";
    // /** _long ダイナミックフィールドの接尾語 */
    // public static final String SOLR_DYNAMIC_FIELD_LONG_SUFFIX = "_long";
    // /** _date ダイナミックフィールドの接尾語 */
    // public static final String SOLR_DYNAMIC_FIELD_DATE_SUFFIX = "_date";

    /** _mlt ダイナミックフィールドの接尾語（マルチバリュー対応） */
    public static final String SOLR_DYNAMIC_FIELD_SUFFIX_FOR_MLT = "_mlt";
    /** _ ダイナミックフィールドの接続後 */
    public static final String SOLR_DYNAMIC_FIELD_SEPALATOR = "_";

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * 次の処理を呼びます
     * {@link #getDynamicName(String, DATA_TYPE_SUFFIX, boolean, Locale)}<br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param name
     *            名称（_区切り）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @return ダイナミックフィールド用の名称
     */
    public static String getDynamicName(String name, DATA_TYPE_SUFFIX suffix) {
        return getDynamicName(name, suffix, false, null);
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * 次の処理を呼びます
     * {@link #getDynamicName(String, DATA_TYPE_SUFFIX, boolean, Locale)}<br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param name
     *            名称（_区切り）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @param isMulti
     *            マルチバリュー判定
     * @return ダイナミックフィールド用の名称
     */
    public static String getDynamicName(String name, DATA_TYPE_SUFFIX suffix,
            boolean isMulti) {
        return getDynamicName(name, suffix, isMulti, null);
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * 次の処理を呼びます
     * {@link #getDynamicName(String, DATA_TYPE_SUFFIX, boolean, Locale)}<br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param name
     *            名称（_区切り）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @param locale
     *            ロケール
     * @return ダイナミックフィールド用の名称
     */
    public static String getDynamicName(String name, DATA_TYPE_SUFFIX suffix,
            Locale locale) {
        return getDynamicName(name, suffix, false, locale);
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * 結果は次のようになります。<br>
     * 
     * <pre>
     * マルチバリューでない　かつ　ロケールがない場合　名称_列挙体のキー
     * マルチバリューでない　かつ　ロケールがある場合　名称_ロケール_列挙体のキー
     * マルチバリューである　かつ　ロケールがない場合　名称_列挙体のキー_mlt
     * マルチバリューである　かつ　ロケールがある場合　名称_ロケール_列挙体のキー_mlt
     * </pre>
     * 
     * 名称の最後が列挙体のキーと重複する場合は、列挙体のキーの追加は行いません。<br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param name
     *            名称（_区切り）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @param isMulti
     *            マルチバリュー判定
     * @param locale
     *            ロケール
     * @return ダイナミックフィールド用の名称
     */
    public static String getDynamicName(String name, DATA_TYPE_SUFFIX suffix,
            boolean isMulti, Locale locale) {
        String newName = name;
        String suffixName = "";
        if (name.endsWith(suffix.key)) {
            newName = newName.substring(0, newName.lastIndexOf(suffix.key));
        }

        if (!(locale == null)) {
            suffixName =
                new StringBuilder()
                    .append(suffixName)
                    .append(SOLR_DYNAMIC_FIELD_SEPALATOR)
                    .append(locale.getLanguage())
                    .toString();
        }

        suffixName =
            new StringBuilder()
                .append(suffixName)
                .append(suffix.key)
                .toString();

        if (isMulti) {
            suffixName =
                new StringBuilder().append(suffixName).append(
                    SOLR_DYNAMIC_FIELD_SUFFIX_FOR_MLT).toString();
        }

        return new StringBuilder()
            .append(newName)
            .append(suffixName)
            .toString();
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * 名称（キャメルケース）のデキャメライズ後に次の処理を呼びます
     * {@link #getDynamicName(String, DATA_TYPE_SUFFIX, boolean, Locale)}<br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param name
     *            名称（キャメルケース）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @return ダイナミックフィールド用の名称
     */
    public static String getDynamicNameWithDecamelize(String name,
            DATA_TYPE_SUFFIX suffix) {
        return getDynamicNameWithDecamelize(name, suffix, false, null);
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * 名称（キャメルケース）のデキャメライズ後に次の処理を呼びます
     * {@link #getDynamicName(String, DATA_TYPE_SUFFIX, boolean, Locale)}<br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param name
     *            名称（キャメルケース）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @param isMulti
     *            マルチバリュー判定
     * @return ダイナミックフィールド用の名称
     */
    public static String getDynamicNameWithDecamelize(String name,
            DATA_TYPE_SUFFIX suffix, boolean isMulti) {
        return getDynamicNameWithDecamelize(name, suffix, isMulti, null);
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * 名称（キャメルケース）のデキャメライズ後に次の処理を呼びます
     * {@link #getDynamicName(String, DATA_TYPE_SUFFIX, boolean, Locale)}<br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param name
     *            名称（キャメルケース）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @param locale
     *            ロケール
     * @return ダイナミックフィールド用の名称
     */
    public static String getDynamicNameWithDecamelize(String name,
            DATA_TYPE_SUFFIX suffix, Locale locale) {
        return getDynamicNameWithDecamelize(name, suffix, false, locale);
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * 名称（キャメルケース）のデキャメライズ、小文字変変換後に次の処理を呼びます
     * {@link #getDynamicName(String, DATA_TYPE_SUFFIX, boolean, Locale)}<br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param name
     *            名称（キャメルケース）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @param isMulti
     *            マルチバリュー判定
     * @param locale
     *            ロケール
     * @return ダイナミックフィールド用の名称
     */
    public static String getDynamicNameWithDecamelize(String name,
            DATA_TYPE_SUFFIX suffix, boolean isMulti, Locale locale) {
        return getDynamicName(
            StringUtil.decamelize(name).toLowerCase(),
            suffix,
            isMulti,
            locale);
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * PropertyNameの名称を取得して次の処理を呼びます
     * {@link #getDynamicNameWithDecamelize(String, DATA_TYPE_SUFFIX, boolean, Locale)}
     * <br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param <T>
     *            PropertyNameの設定型
     * @param name
     *            名称（キャメルケース）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @return ダイナミックフィールド用の名称
     */
    public static <T> String getDynamicName(PropertyName<T> name,
            DATA_TYPE_SUFFIX suffix) {
        return getDynamicName(name, suffix, false, null);
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * PropertyNameの名称を取得して次の処理を呼びます
     * {@link #getDynamicNameWithDecamelize(String, DATA_TYPE_SUFFIX, boolean, Locale)}
     * <br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param <T>
     *            PropertyNameの設定型
     * @param name
     *            名称（キャメルケース）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @param isMulti
     *            マルチバリュー判定
     * @return ダイナミックフィールド用の名称
     */
    public static <T> String getDynamicName(PropertyName<T> name,
            DATA_TYPE_SUFFIX suffix, boolean isMulti) {
        return getDynamicName(name, suffix, isMulti, null);
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * PropertyNameの名称を取得して次の処理を呼びます
     * {@link #getDynamicNameWithDecamelize(String, DATA_TYPE_SUFFIX, boolean, Locale)}
     * <br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param <T>
     *            PropertyNameの設定型
     * @param name
     *            名称（キャメルケース）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @param locale
     *            ロケール
     * @return ダイナミックフィールド用の名称
     */
    public static <T> String getDynamicName(PropertyName<T> name,
            DATA_TYPE_SUFFIX suffix, Locale locale) {
        return getDynamicName(name, suffix, false, locale);
    }

    /**
     * 名称からダイナミックフィールド用の名称を作成します。<br>
     * PropertyNameの名称を取得して次の処理を呼びます
     * {@link #getDynamicNameWithDecamelize(String, DATA_TYPE_SUFFIX, boolean, Locale)}
     * <br>
     * また、名称のnullチェックは行っていません。
     * 
     * @param <T>
     *            PropertyNameの設定型
     * @param name
     *            名称（キャメルケース）
     * @param suffix
     *            ダイナミックフィールドの接尾語をあらわす列挙体
     * @param isMulti
     *            マルチバリュー判定
     * @param locale
     *            ロケール
     * @return ダイナミックフィールド用の名称
     */
    public static <T> String getDynamicName(PropertyName<T> name,
            DATA_TYPE_SUFFIX suffix, boolean isMulti, Locale locale) {
        return getDynamicNameWithDecamelize(
            name.toString(),
            suffix,
            isMulti,
            locale);
    }

}
