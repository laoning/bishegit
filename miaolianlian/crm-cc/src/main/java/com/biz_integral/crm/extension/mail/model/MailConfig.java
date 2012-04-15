/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.model;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jp.co.intra_mart.foundation.security.AccessSecurityManager;
import jp.co.intra_mart.foundation.security.LocaleInfo;
import jp.co.intra_mart.foundation.security.SystemManager;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.system.service.provider.platform.AdministrationFile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.biz_integral.crm.cc.domain.logic.batch.config.CrmConfigBase;
import com.biz_integral.crm.extension.mail.util.Utility;
import com.biz_integral.foundation.core.util.StringUtil;

/**
 * メールシステムのXML Configuration
 * 
 */
public class MailConfig extends CrmConfigBase {

    private Document domDocument;

    private static int ATTACHMENT_YIELD_SIZE = 1048576;

    private static String DEFAULT_EXT = "eml";

    private static final String XPATH_MAIL_CONFIG_SOCKET_TIMEOUT =
        "/biz-application-mail-config/timeout"
            + "/socket_timeout/protocol[@name=''{0}'']";

    private static final String XPATH_MAIL_CONFIG_CONNECTION_TIMEOUT =
        "/biz-application-mail-config/timeout"
            + "/connection_timeout/protocol[@name=''{0}'']";

    private static final String XPATH_MAIL_CONFIG_DEFAULT_EXT =
        "/biz-application-mail-config/default_ext";

    private static final String XPATH_MAIL_CONFIG_ATTACHMENT_YIELD_ENABLE =
        "/biz-application-mail-config/attachment_yield_enable";

    private static final String XPATH_MAIL_CONFIG_ATTACHMENT_YIELD_SIZE =
        "/biz-application-mail-config/attachment_yield_size";

    /**
     * コンストラクタ
     * 
     * @param path
     *            ファイルパース
     */
    public MailConfig(String path) {
        super(path);
        domDocument = createDOMDocument(path);
    }

    private Document createDOMDocument(String path) {

        try {

            AdministrationFile file = new AdministrationFile(path);
            if (file == null) {
                return null;
            }
            InputStream in = file.getInputStream();

            DocumentBuilderFactory docBuilderFactory =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            domDocument = docBuilder.parse(in);

        } catch (SAXException e) {
            domDocument = null;
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            domDocument = null;
            e.printStackTrace();
        } catch (Exception e) {
            domDocument = null;
        }

        return domDocument;
    }

    /**
     * 文字コードを取得する。<br>
     * 
     * @return 取得した文字コード
     */
    public ArrayList<Hashtable<String, String>> loadEncodingList() {
        // デイフォルトLocaleを取得
        Locale locale;
        try {
            AccessSecurityManager asm = AccessSecurityManager.getInstance();
            locale = asm.getSessionInfo().getLocale();
        } catch (AccessSecurityException e) {
            e.printStackTrace();
            locale = new Locale("jp");
        }

        String default_display_node = "display-name";
        String display_node =
            default_display_node + "-" + locale.toString().toLowerCase();
        String encoding_node = "encoding-name";
        String mime_node = "mime-encoding";
        String charset = "charset";

        ArrayList<Hashtable<String, String>> encodingList =
            new ArrayList<Hashtable<String, String>>();

        try {
            // mail-configがある場合
            if (domDocument != null) {
                // normalize text redivsentation
                domDocument.getDocumentElement().normalize();

                NodeList listOfEncodings =
                    domDocument.getElementsByTagName("encoding");
                int totalEncodings = listOfEncodings.getLength();

                for (int s = 0; s < totalEncodings; s++) {

                    // 一つノードを処理する
                    Node encodeNode = listOfEncodings.item(s);
                    if (encodeNode.getNodeType() == Node.ELEMENT_NODE) {

                        Hashtable<String, String> encoding =
                            new Hashtable<String, String>();
                        Element encodeElement = (Element) encodeNode;

                        // エンコード名前
                        NodeList encodeNameNode =
                            encodeElement.getElementsByTagName(encoding_node);
                        Element encodeNameElement =
                            (Element) encodeNameNode.item(0);

                        NodeList textENList = encodeNameElement.getChildNodes();

                        String value =
                            ((Node) textENList.item(0)).getNodeValue().trim();
                        encoding.put("encodingName", value);

                        // Charset
                        NodeList charsetNode =
                            encodeElement.getElementsByTagName(charset);
                        Element charsetElement = (Element) charsetNode.item(0);

                        NodeList textCSList = charsetElement.getChildNodes();

                        value =
                            ((Node) textCSList.item(0)).getNodeValue().trim();
                        encoding.put("charset", value);

                        // 表示用名前
                        NodeList displayNameList =
                            encodeElement.getElementsByTagName(display_node);
                        if (displayNameList == null
                            || displayNameList.getLength() == 0) {
                            displayNameList =
                                encodeElement
                                    .getElementsByTagName(default_display_node);
                        }

                        Element displayNameElement =
                            (Element) displayNameList.item(0);

                        NodeList textDNList =
                            displayNameElement.getChildNodes();
                        value =
                            ((Node) textDNList.item(0)).getNodeValue().trim();
                        encoding.put("displayName", value);

                        // MIMEコード
                        NodeList mimeList =
                            encodeElement.getElementsByTagName(mime_node);
                        if (mimeList == null || mimeList.getLength() == 0) {
                            value = "B";
                        } else {
                            Element mimeElement = (Element) mimeList.item(0);

                            NodeList textMimeList = mimeElement.getChildNodes();
                            if (textMimeList != null) {
                                value =
                                    ((Node) textMimeList.item(0))
                                        .getNodeValue()
                                        .trim();
                            }
                            if ("".equals(value)) {
                                value = "B";
                            }
                        }
                        encoding.put("mimeEncoding", value);

                        encodingList.add(encoding);
                    }

                }
            } else { // mail-configがない場合、system.xml中のencodingを取得
                LocaleInfo[] locales =
                    SystemManager.getInstance().getLocales(locale);
                for (int i = 0; i < locales.length; i++) {
                    Hashtable<String, String> encoding =
                        new Hashtable<String, String>();

                    String value = locales[i].getEncoding();
                    encoding.put("encodingName", value);

                    value = locales[i].getEncoding();
                    encoding.put("charset", value);

                    value = locales[i].getDisplayName();
                    encoding.put("displayName", value);

                    value = Utility.getMimeEncoding(locales[i].getLocale());
                    if (value == null) {
                        value = "";
                    }
                    encoding.put("mimeEncoding", value);

                    encodingList.add(encoding);

                }
            }
        } catch (AccessSecurityException e) {
            e.printStackTrace();
        }
        return encodingList;

    }

    /**
     * プロトコルに応じたソケットタイムアウトの秒数（ミリ秒）を取得します。
     * 
     * @param protocol
     *            プロトコル
     * @return SocketTimeOut
     */
    protected String getSocketTimeOut(String protocol) {

        Node node =
            getNode(MessageFormat.format(
                XPATH_MAIL_CONFIG_SOCKET_TIMEOUT,
                protocol));

        if (node != null) {
            String value = node.getTextContent();
            try {
                if (!StringUtil.isEmpty(value)) {
                    int socketTime = Integer.parseInt(value);
                    if (socketTime >= 1000) {
                        return value;
                    }
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * プロトコルに応じたコネクションタイムアウトの秒数（ミリ秒）を取得します。
     * 
     * @param protocol
     *            プロトコル
     * @return SocketTimeOut
     */
    protected String getConnectionTimeOut(String protocol) {

        Node node =
            getNode(MessageFormat.format(
                XPATH_MAIL_CONFIG_CONNECTION_TIMEOUT,
                protocol));

        if (node != null) {
            String value = node.getTextContent();
            try {
                if (!StringUtil.isEmpty(value)) {
                    int socketTime = Integer.parseInt(value);
                    if (socketTime >= 1000) {
                        return value;
                    }
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * メールのデフォルト拡張子を取得します。
     * 
     * @return デフォルト拡張子
     */
    protected String getDefaultExt() {

        Node node = getNode(XPATH_MAIL_CONFIG_DEFAULT_EXT);

        if (node != null) {
            String value = node.getTextContent();
            if (!StringUtil.isEmpty(value)) {
                return value;
            }
        }
        return DEFAULT_EXT;
    }

    /**
     * 添付ファイルの受信制限の有無を取得します。
     * 
     * @return 受信制限の有無
     */
    protected boolean getAttachmentYieldEnable() {

        Node node = getNode(XPATH_MAIL_CONFIG_ATTACHMENT_YIELD_ENABLE);

        if (node != null) {
            String value = node.getTextContent();
            if (!StringUtil.isEmpty(value)) {
                return toBoolean(value);
            }
        }
        return false;
    }

    /**
     * 添付ファイルの受信制限するファイルサイズを取得します。
     * 
     * @return ファイルサイズ
     */
    protected int getAttachmentYieldSize() {

        Node node = getNode(XPATH_MAIL_CONFIG_ATTACHMENT_YIELD_SIZE);

        if (node != null) {
            String value = node.getTextContent();
            try {
                if (!StringUtil.isEmpty(value)) {
                    return Integer.parseInt(node.getTextContent());
                }

            } catch (NumberFormatException e) {
            }
        }
        return ATTACHMENT_YIELD_SIZE;
    }
}
