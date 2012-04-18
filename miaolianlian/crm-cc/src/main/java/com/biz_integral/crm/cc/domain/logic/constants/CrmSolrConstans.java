/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.constants;

/**
 * Solrのコンスト定義
 */
public class CrmSolrConstans {

    /** configファイルのパス */
    public static final String CONFIG_FILE_NAME =
        "conf/biz-solr-crawler-config-crm.xml";

    /** 文書の列挙体 */
    public enum DOCUMENT_INFO {
        /** CRMアカウント */
        CRM_ACCOUNT("crm", "cc", "crmAccount"),
        /** CRMコンタクト */
        CRM_CONTACT("crm", "cc", "crmContact"),
        /** 案件 */
        PROPOSAL("crm", "pm", "proposal"),
        /** 営業活動 */
        SALES_ACTIVITY("crm", "sa", "salesActivity");

        /** アプリケーションID */
        private String applicationId;
        /** モジュールID */
        private String moduleId;
        /** 文書ID */
        private String documentId;

        /**
         * コンストラクタ
         * 
         * @param applicationId
         *            アプリケーションID
         * @param moduleId
         *            モジュールID
         * @param documentId
         *            ドキュメントID
         */
        private DOCUMENT_INFO(String applicationId, String moduleId,
                String documentId) {
            this.applicationId = applicationId;
            this.moduleId = moduleId;
            this.documentId = documentId;
        }

        /**
         * アプリケーションIDを返却します。
         * 
         * @return アプリケーションID
         */
        public String applicationId() {
            return applicationId;
        }

        /**
         * モジュールIDを返却します。
         * 
         * @return モジュールID
         */
        public String moduleId() {
            return moduleId;
        }

        /**
         * 文書IDを返却します。
         * 
         * @return 文書ID
         */
        public String documentId() {
            return documentId;
        }
    }

    /** 詳細を表示する画面URL */
    public enum DETAIL {
        /** 営業活動 */
        SALES_ACTIVITY("/imart/crm_sa-salesActivityPlan_entry.service");

        /** 詳細URL */
        private String url;

        /**
         * コンストラクタ
         * 
         * @param url
         *            詳細URL
         */
        private DETAIL(String url) {
            this.url = url;
        }

        /**
         * 詳細URLを返却します。
         * 
         * @return 詳細URL
         */
        public String url() {
            return url;
        }

    }

    /** CRM共通フィールド 更新日時 */
    public static final String CRM_FIELD_UPDATE_DATE = "update_date_string";

    /**
     * クローラ実行日保存ファイルパス<br>
     * {0}はアプリケーションID<br>
     * {1}は会社コード
     */
    public static final String LAST_CRAWLING_DATE_PATH =
        "biz-integral/{0}/{1}/batch/crawler/";
    /** クローラ実行日保存ファイル名 */
    public static final String LAST_CRAWLING_DATE_FILENAME =
        "_last_crawling_date";

}
