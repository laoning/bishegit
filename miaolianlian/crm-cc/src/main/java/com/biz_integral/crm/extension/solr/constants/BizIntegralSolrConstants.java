/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.constants;

/**
 * solrのコンスタント
 */
public class BizIntegralSolrConstants {

    /** リクエストキー BIZコンテキスト */
    public static final String REQ_KEY_BIZ_CONTEXT = "biz_context";

    /**
     * 登録及び検索するデータの、対応機能を設定するフィールドを定義します。<br>
     * 全文検索に対応する場合は、<br>
     * <code>text_ngrm,text_morph</code>への設定が必須となります。<br>
     * 名寄よ検索に対応する場合は、<br>
     * <code>name_synonym_ngrm,name_synonym_morph</code>への設定が必須となります。<br>
     */
    public enum SEARCH_FUNCTION {
        /** 全文検索 */
        FULL_SEARCH("is_full_search_string", "0"),
        /** 名寄せ検索 */
        SYNONYM_SEARCH("is_synonym_search_string", "0");

        /** フィールド名 */
        private String field;
        /** 初期値 1:true 0:false */
        private String default_flag;

        /**
         * コンストラクタ
         * 
         * @param field
         *            フィールド名
         * @param default_flag
         *            初期値 1:true 0:false
         */
        private SEARCH_FUNCTION(String field, String default_flag) {
            this.field = field;
            this.default_flag = default_flag;
        }

        /**
         * フィールド名を返却します。
         * 
         * @return フィールド名
         */
        public String field() {
            return field;
        }

        /**
         * 初期値を返却します。
         * 
         * @return 初期値
         */
        public String default_flag() {
            return default_flag;
        }
    }
}
