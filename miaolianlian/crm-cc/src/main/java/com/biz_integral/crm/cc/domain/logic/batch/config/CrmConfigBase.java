/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import jp.co.intra_mart.system.service.provider.platform.AdministrationFile;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.biz_integral.crm.cc.domain.logic.batch.exception.BatchRuntimeException;
import com.biz_integral.foundation.core.exception.FileNotFoundRuntimeException;
import com.biz_integral.foundation.core.exception.IORuntimeException;
import com.biz_integral.foundation.core.message.MessageBuilder;

/**
 * XMLファイルの読込みを行います。
 */
public class CrmConfigBase {

    /** 設定ファイルのパス */
    protected String fileName;
    /** xmlドキュメント */
    protected Document document;

    /**
     * コンストラクタ<br>
     * 引数のファイルを読み込みます。
     * 
     * @param fileName
     *            ファイル名
     * @throws FileNotFoundRuntimeException
     */
    protected CrmConfigBase(String fileName) {
        this.fileName = fileName;
        AdministrationFile f = new AdministrationFile(fileName);
        InputStream is = null;
        document = null;
        try {
            System.err.println(f.getPath());
            is = f.getInputStream();

            document = parse(is);
        } catch (IOException e) {
            throw new BatchRuntimeException(e, MessageBuilder.create(
                "E.CRM.CC.00088").addParameter(fileName).toMessage());

        } catch (Exception e) {

        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * InputStreamをDocumentとして読み込みます。
     * 
     * @param is
     *            InputStream
     * @return Document
     * @throws BatchRuntimeException
     */
    protected Document parse(InputStream is) {
        try {
            DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(is);
        } catch (ParserConfigurationException e) {
            throw new BatchRuntimeException(e, MessageBuilder.create(
                "E.CRM.CC.00088").addParameter(fileName).toMessage());
        } catch (SAXException e) {
            throw new BatchRuntimeException(e, MessageBuilder.create(
                "E.CRM.CC.00088").addParameter(fileName).toMessage());
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * XPATHに応じてXMLドキュメントのノードを取得します。
     * 
     * @param expression
     *            XPATH
     * @return ノード
     * @throws BatchRuntimeException
     */
    protected Node getNode(String expression) {
        try {
            XPath xpath = createXPath();
            return (Node) xpath.evaluate(
                expression,
                document,
                XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            throw new BatchRuntimeException(e, MessageBuilder.create(
                "E.CRM.CC.00088").addParameter(fileName).toMessage());
        }
    }

    /**
     * XPATHに応じてXMLドキュメントのノードリストを取得します。
     * 
     * @param expression
     *            XPATH
     * @return ノードリスト
     * @throws BatchRuntimeException
     */
    protected NodeList getNodeList(String expression)
        throws BatchRuntimeException {
        try {
            XPath xpath = createXPath();
            return (NodeList) xpath.evaluate(
                expression,
                document,
                XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new BatchRuntimeException(e, MessageBuilder.create(
                "E.CRM.CC.00088").addParameter(fileName).toMessage());
        }
    }

    /**
     * 
     * XPATHの値をbooleanで判定します。<br>
     * 「true」または「yes」の場合にtrueを返却します。
     * 
     * @param text
     *            XPathの値
     * @return 判定結果
     */
    protected boolean toBoolean(String text) {
        if (text == null)
            return false;
        text = text.toLowerCase();
        return text.equals("true") || text.equals("yes");
    }

    /**
     * XPATHを作成します。
     * 
     * @return XPath
     */
    private XPath createXPath() {
        XPathFactory factory = XPathFactory.newInstance();
        return factory.newXPath();
    }

    /**
     * ノードにエレメントを追加します。
     * 
     * @param node
     *            追加先ノード
     * @param tagName
     *            追加するエレメント名
     */
    protected void createElement(Node node, String tagName) {
        Element element = document.createElement(tagName);
        node.appendChild(element);
    }

    /**
     * ノードにエレメントを追加します。
     * 
     * @param node
     *            追加先ノード
     * @param tagName
     *            追加するエレメント名
     * @param attribute
     *            追加するエレメントの属性
     */
    protected void createElement(Node node, String tagName,
            Map<String, String> attribute) {
        Element element = document.createElement(tagName);
        if (attribute != null) {
            for (Map.Entry<String, String> entry : attribute.entrySet()) {
                element.setAttribute(entry.getKey(), entry.getValue());
            }
        }
        node.appendChild(element);
    }
}
