/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.common;

import jp.co.intra_mart.foundation.solr.util.IdentifierUtil;

import com.biz_integral.foundation.core.util.StringUtil;

/**
 * Solrの文書IDを作成します。
 */
public class BizIntegralCrawlerUtil {

    /** 会社コードのprefix */
    private static final String COMPANY_PREFIX = "c";
    /** ロケールIDのprefix */
    private static final String LOCALE_PREFIX = "l";
    /** キーコードのprefix */
    private static final String KEY_PREFIX = "k";
    /** separator */
    private static final String SEPARATOR = "_";

    /**
     * 文書IDを作成します。<br>
     * アプリケーションID_モジュールID_機能名_c_会社コード_k_キー<br>
     * ロケールを含まない文書用です。
     * 
     * @param applicationId
     *            アプリケーションID
     * @param moduleId
     *            モジュールID
     * @param documentId
     *            ドキュメントID
     * @param companyCd
     *            会社コード
     * @param key
     *            キーコード
     * @return 文書ID
     */
    public static String makeDocumentId(String applicationId, String moduleId,
            String documentId, String companyCd, String key) {
        String prefix =
            new StringBuilder(makePrefix(applicationId, moduleId, documentId))
                .append(SEPARATOR)
                .toString();

        return IdentifierUtil.createDocumentId(
            prefix,
            COMPANY_PREFIX,
            companyCd,
            KEY_PREFIX,
            key);
    }

    /**
     * 文書IDを作成します。<br>
     * アプリケーションID_モジュールID_機能名_c_会社コード_l_ロケールID_k_キー<br>
     * ロケールを含む文書用です。
     * 
     * @param applicationId
     *            アプリケーションID
     * @param moduleId
     *            モジュールID
     * @param documentId
     *            ドキュメントID
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param key
     *            キーコード
     * @return 文書ID
     */
    public static String makeDocumentId(String applicationId, String moduleId,
            String documentId, String companyCd, String localeId, String key) {
        String prefix =
            new StringBuilder(makePrefix(applicationId, moduleId, documentId))
                .append(SEPARATOR)
                .toString();
        return IdentifierUtil.createDocumentId(
            prefix,
            COMPANY_PREFIX,
            companyCd,
            LOCALE_PREFIX,
            localeId,
            KEY_PREFIX,
            key);
    }

    /**
     * 文書IDのprefixを作成します。<br>
     * アプリケーションID_モジュールID_機能名<br>
     * 作成されるprefixは文書固有の文書種別です。
     * 
     * @param applicationId
     *            アプリケーションID
     * @param moduleId
     *            モジュールID
     * @param documentId
     *            文書ID
     * @return 文書IDのprefix
     */
    public static String makePrefix(String applicationId, String moduleId,
            String documentId) {
        return StringUtil.concat(new String[] {
            applicationId,
            moduleId,
            documentId }, SEPARATOR);
    }
}
