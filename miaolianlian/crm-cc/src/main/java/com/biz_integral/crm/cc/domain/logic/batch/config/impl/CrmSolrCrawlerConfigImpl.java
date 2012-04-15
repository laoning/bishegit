/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.config.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.biz_integral.crm.cc.domain.logic.batch.config.CrmConfigBase;
import com.biz_integral.crm.cc.domain.logic.batch.config.CrmSolrCrawlerConfig;
import com.biz_integral.crm.cc.domain.logic.batch.exception.BatchRuntimeException;
import com.biz_integral.foundation.core.exception.ParseRuntimeException;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.StringUtil;

/**
 * CRMソーラクローラ設定ファイルを扱います。
 */
public class CrmSolrCrawlerConfigImpl extends CrmConfigBase implements
        CrmSolrCrawlerConfig {

    // 読み出しようのXPATH
    /** 初回設定日 */
    private static final String XPATH_DEFAULT_INITIAL_DATE =
        "/biz-solr-crawler-config/initial_date";
    /** １処理件数 */
    private static final String XPATH_DEFAULT_ONE_TRANSACTION_NUMBER =
        "/biz-solr-crawler-config/one_transaction_number";
    /** 文書別の対象会社コード */
    private static final String XPATH_TARGET_COMPANY =
        "/biz-solr-crawler-config/target_company"
            + "/document_type[@id=''{0}'']";
    /** 文書別の１処理件数 */
    private static final String XPATH_CRAWLER_CONFIG_ONE_TRANSACTION_NUMBER =
        "/biz-solr-crawler-config/crawler_config"
            + "/document_type[@id=''{0}'']/one_transaction_number";
    /** 文書別・会社別のクローラ実行設定「初回設定日」 */
    private static final String XPATH_CRAWLER_CONFIG_INITIAL_DATE =
        "/biz-solr-crawler-config/crawler_config"
            + "/document_type[@id=''{0}'']/company[@code=''{1}'']/initial_date";
    /** 文書別・会社別のクローラ実行設定「再作成月数」 */
    private static final String XPATH_CRAWLER_CONFIG_REINDEX_MONTH =
        "/biz-solr-crawler-config/crawler_config"
            + "/document_type[@id=''{0}'']/company[@code=''{1}'']/reindex_month";

    /**
     * コンストラクタ<br>
     * biz-solr-crawler-config.xmlを読み込みます。<br>
     * 
     * @param fileName
     *            コンフィグファイル名
     */
    public CrmSolrCrawlerConfigImpl(String fileName) {
        super(fileName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitialDate() {
        Node node = getNode(XPATH_DEFAULT_INITIAL_DATE);

        if (node == null || StringUtil.isEmpty(node.getTextContent())) {

            throw new BatchRuntimeException(MessageBuilder.create(
                "E.CRM.CC.00089").addParameter(fileName).addParameter(
                XPATH_DEFAULT_INITIAL_DATE).toMessage());
        }

        String date = node.getTextContent();
        try {
            // フォーマットチェック
            DateUtil.parse(date);
        } catch (ParseRuntimeException e) {

            throw new BatchRuntimeException(e, MessageBuilder.create(
                "E.CRM.CC.00090").addParameter(fileName).addParameter(
                XPATH_DEFAULT_INITIAL_DATE).addParameter(date).toMessage());

        }
        return date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitialDate(String documentType, String companyCd) {
        // Node node = getNode(XPATH_DEFAULT_INITIAL_DATE);
        Node node =
            getNode(MessageFormat.format(
                XPATH_CRAWLER_CONFIG_INITIAL_DATE,
                documentType,
                companyCd));

        if (node != null) {
            String date = node.getTextContent();
            if (!StringUtil.isEmpty(date)) {
                try {
                    DateUtil.parse(date);
                } catch (ParseRuntimeException e) {

                    throw new BatchRuntimeException(e, MessageBuilder.create(
                        "E.CRM.CC.00090").addParameter(fileName).addParameter(
                        MessageFormat.format(
                            XPATH_CRAWLER_CONFIG_INITIAL_DATE,
                            documentType,
                            companyCd)).addParameter(date).toMessage());

                }
                return date;
            }

        }

        return getInitialDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getOneTransactionNumber() {
        Node node = getNode(XPATH_DEFAULT_ONE_TRANSACTION_NUMBER);

        if (node == null || StringUtil.isEmpty(node.getTextContent())) {

            throw new BatchRuntimeException(MessageBuilder.create(
                "E.CRM.CC.00089").addParameter(fileName).addParameter(
                XPATH_DEFAULT_ONE_TRANSACTION_NUMBER).toMessage());
        }

        long longNumber = 0L;
        String strNumber = node.getTextContent();
        try {
            // フォーマットチェック
            longNumber = Long.parseLong(strNumber);
        } catch (NumberFormatException e) {

            throw new BatchRuntimeException(e, MessageBuilder
                .create("E.CRM.CC.00090")
                .addParameter(fileName)
                .addParameter(XPATH_DEFAULT_ONE_TRANSACTION_NUMBER)
                .addParameter(strNumber)
                .toMessage());

        }

        if (0L == longNumber) {
            throw new BatchRuntimeException(MessageBuilder
                .create("E.CRM.CC.00090")
                .addParameter(fileName)
                .addParameter(XPATH_DEFAULT_ONE_TRANSACTION_NUMBER)
                .addParameter(strNumber)
                .toMessage());
        }

        return longNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getOneTransactionNumber(String documentType) {
        Node node =
            getNode(MessageFormat.format(
                XPATH_CRAWLER_CONFIG_ONE_TRANSACTION_NUMBER,
                documentType));

        if (node == null || StringUtil.isEmpty(node.getTextContent())) {
            return getOneTransactionNumber();
        }

        long longNumber = 0L;
        String strNumber = node.getTextContent();
        try {
            // フォーマットチェック
            longNumber = Long.parseLong(strNumber);
        } catch (NumberFormatException e) {

            throw new BatchRuntimeException(e, MessageBuilder.create(
                "E.CRM.CC.00090").addParameter(fileName).addParameter(
                MessageFormat.format(
                    XPATH_CRAWLER_CONFIG_ONE_TRANSACTION_NUMBER,
                    documentType)).addParameter(strNumber).toMessage());

        }

        if (0L == longNumber) {
            return getOneTransactionNumber();
        }

        return longNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getCompanyList(String documentType) {

        Node parentNode =
            getNode(MessageFormat.format(XPATH_TARGET_COMPANY, documentType));
        NodeList nodeList = parentNode.getChildNodes();

        List<String> companyList = new ArrayList<String>();
        for (int i = 0, length = nodeList.getLength(); i < length; i++) {
            Node node = nodeList.item(i);
            if (toBoolean(node.getTextContent())) {
                companyList.add(node
                    .getAttributes()
                    .getNamedItem("code")
                    .getTextContent());
            }
        }
        return companyList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getReindexMonth(String documentType, String companyCd) {
        Node node =
            getNode(MessageFormat.format(
                XPATH_CRAWLER_CONFIG_REINDEX_MONTH,
                documentType,
                companyCd));

        int reindexMonth = 0;

        if (node != null) {
            String index = node.getTextContent();
            try {
                if (StringUtil.isEmpty(index)) {
                    return 0;
                }
                reindexMonth = Integer.parseInt(node.getTextContent());

            } catch (NumberFormatException e) {
                throw new BatchRuntimeException(e, MessageBuilder.create(
                    "E.CRM.CC.00090").addParameter(fileName).addParameter(
                    MessageFormat.format(
                        XPATH_CRAWLER_CONFIG_REINDEX_MONTH,
                        documentType,
                        companyCd)).addParameter(index).toMessage());
            }
        } else {
            throw new BatchRuntimeException(MessageBuilder.create(
                "E.CRM.CC.00089").addParameter(fileName).addParameter(
                MessageFormat.format(
                    XPATH_CRAWLER_CONFIG_REINDEX_MONTH,
                    documentType,
                    companyCd)).toMessage());
        }

        return reindexMonth;
    }
}
