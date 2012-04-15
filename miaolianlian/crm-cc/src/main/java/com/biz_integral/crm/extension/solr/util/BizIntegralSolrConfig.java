/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import jp.co.intra_mart.system.service.provider.platform.AdministrationFile;
import jp.co.nttdata.intra_mart.solr.exception.SearchException;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Biz用のSolr設定ファイル管理クラス
 */
public class BizIntegralSolrConfig {

    /** biz-solr-config.xmlのパス */
    public static final String CONFIG_FILE_NAME = "conf/biz-solr-config.xml";
    /** solrコア変更フラグ */
    public static final String XPATH_USE_SOLR_CHANGE =
        "/biz-solr-config/use-solr-change[1]";
    /** group内のdefaultコアのXPATH */
    public static final String XPATH_GROUP_DEFAULT =
        "/biz-solr-config/solr-url/group[@id=''{0}'']/default";
    /** group内の会社ごとのコアのXPATH */
    public static final String XPATH_GROUP_COMPANY =
        "/biz-solr-config/solr-url/group[@id=''{0}'']/company[@code=''{1}'']";
    /** 会社一覧のXPATH */
    public static final String XPATH_COMPANY_LIST =
        "/biz-solr-config/solr-url/group[@id=''{0}'']";

    /** solr-config-biz.xmlドキュメント */
    private Document document;

    /**
     * コンストラクタ<br>
     * biz-solr-config.xmlの読み込みを行います。
     * 
     * @throws SearchException
     */
    public BizIntegralSolrConfig() throws SearchException {
        AdministrationFile f = new AdministrationFile(CONFIG_FILE_NAME);
        InputStream is = null;
        document = null;
        try {
            is = f.getInputStream();
            document = parse(is);
        } catch (IOException e) {
            throw new SearchException(e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * Bizのsolrコアのurlを取得します。<br>
     * 
     * @param loginGroupId
     *            ログイングループID
     * @param companyCd
     *            会社コード
     * @return solrコアのurl
     * @throws SearchException
     */
    public String getSolrUrl(String loginGroupId, String companyCd)
        throws SearchException {
        if (getUseSolrChange()) {
            String xpath =
                MessageFormat.format(
                    XPATH_GROUP_COMPANY,
                    loginGroupId,
                    companyCd);
            Node url = getNode(xpath);

            if (url != null) {
                return url.getTextContent();
            }
        } else {
            String xpath =
                MessageFormat.format(XPATH_GROUP_DEFAULT, loginGroupId);
            Node url = getNode(xpath);

            if (url != null) {
                return url.getTextContent();
            }
        }

        return null;
    }

    /**
     * Bizのsolrコアを会社毎に変更するか判断します。
     * 
     * @return true:変更する false:変更しない
     * @throws SearchException
     */
    public boolean getUseSolrChange() throws SearchException {
        Node useSolrChange = getNode(XPATH_USE_SOLR_CHANGE);
        if (useSolrChange == null) {
            return false;
        }
        return toBoolean(useSolrChange.getTextContent());
    }

    /**
     * XPATHの値をbooleanで判定します。<br>
     * 「true」または「yes」の場合にtrueを返却します。
     * 
     * @param text
     *            XPATHの値
     * @return 判定結果
     */
    private boolean toBoolean(String text) {
        if (text == null)
            return false;
        text = text.toLowerCase();
        return text.equals("true") || text.equals("yes");
    }

    /**
     * InputStreamをDocumentとして読み込みます。
     * 
     * @param is
     *            InputStream
     * @return Document
     * @throws SearchException
     */
    private Document parse(InputStream is) throws SearchException {
        try {
            DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(is);
        } catch (Exception e) {
            throw new SearchException(e);
        }
    }

    /**
     * XPATHに応じてXMLドキュメントのノードを取得します。
     * 
     * @param expression
     *            XPATH
     * @return ノード
     * @throws SearchException
     */
    private Node getNode(String expression) throws SearchException {
        try {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            return (Node) xpath.evaluate(
                expression,
                document,
                XPathConstants.NODE);
        } catch (Exception e) {
            throw new SearchException(e);
        }
    }

    /**
     * 会社コードのリストを取得します。<br>
     * 
     * @param loginGroupId
     *            ログイングループID
     * @return 会社コードのリスト
     * @throws SearchException
     */
    public List<String> getCompanyList(String loginGroupId)
        throws SearchException {
        Node parentNode =
            getNode(MessageFormat.format(XPATH_COMPANY_LIST, loginGroupId));
        NodeList nodeList = parentNode.getChildNodes();

        List<String> companyList = new ArrayList<String>();
        for (int i = 0, length = nodeList.getLength(); i < length; i++) {
            Node node = nodeList.item(i);
            if ("company".equalsIgnoreCase(node.getLocalName())) {
                companyList.add(node
                    .getAttributes()
                    .getNamedItem("code")
                    .getTextContent());
            }
        }
        return companyList;
    }
}