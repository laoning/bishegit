/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import com.biz_integral.crm.extension.mail.util.Utility;

/**
 * メールシステムの設定
 * 
 */
public class MailSysInfo {

    private static String charset;
    private static String wordEncode;
    private static String version;
    private static String errorBody;
    private static String errorSubject;
    private static String pop3SocketTimeout;
    private static String imapSocketTimeout;
    private static String pop3ConnectTimeout;
    private static String imapConnectTimeout;
    private static String defaultExt;
    private static HashMap<String, Hashtable<String, String>> encodingList;
    private static ArrayList<Hashtable<String, String>> encodingArray;
    private static boolean yieldFlg = false;
    private static int yieldCount = 1048576;
    private static String CONFIG_FILE = "conf/biz-mail-config-crm.xml";

    static {
        // Propertyから設定を取得する
        charset = Utility.getWebMailProps("DEFAULT_CHARSET");
        wordEncode = Utility.getWebMailProps("WORD_ENCODE");
        version = Utility.getWebMailProps("MAJOR_VERSION");
        errorSubject = Utility.getWebMailProps("ERROR_SUBJECT");
        errorBody = Utility.getWebMailProps("ERROR_BODY");

        // XML config
        MailConfig config = new MailConfig(CONFIG_FILE);
        encodingArray = config.loadEncodingList();
        encodingList = new HashMap<String, Hashtable<String, String>>();
        Hashtable<String, String> encode;
        String key;
        for (int i = 0; i < encodingArray.size(); i++) {
            encode = (Hashtable<String, String>) encodingArray.get(i);
            key = (String) encode.get("encodingName");
            encodingList.put(key, encode);
        }
        pop3SocketTimeout = config.getSocketTimeOut("pop3");
        imapSocketTimeout = config.getSocketTimeOut("imap");
        pop3ConnectTimeout = config.getConnectionTimeOut("pop3");
        imapConnectTimeout = config.getConnectionTimeOut("imap");

        defaultExt = config.getDefaultExt();
        yieldFlg = config.getAttachmentYieldEnable();
        yieldCount = config.getAttachmentYieldSize();

    }

    /**
     * Yieldカウントを取得します。
     * 
     * @return Yieldカウント
     */
    public static int getYieldCount() {
        return yieldCount;
    }

    /**
     * Yieldフラグを取得します。
     * 
     * @return Yieldフラグ
     */
    public static boolean getYieldFlag() {
        return yieldFlg;
    }

    /**
     * デーフォルトCharsetを取得します。
     * 
     * @return デーフォルトCharset
     */
    public static String getCharset() {
        return charset;
    }

    /**
     * Socket Timeoutを取得します。
     * 
     * @param protocol
     *            サーバ種類
     * @return Socket Timeout
     */
    public static String getSocketTimeout(String protocol) {
        if ("pop3".equalsIgnoreCase(protocol)) {
            return pop3SocketTimeout;
        } else {
            return imapSocketTimeout;
        }
    }

    /**
     * Connect Timeoutを取得します。
     * 
     * @param protocol
     *            サーバ種類
     * @return Connect Timeout
     */
    public static String getConnectTimeout(String protocol) {
        if ("pop3".equalsIgnoreCase(protocol)) {
            return pop3ConnectTimeout;
        } else {
            return imapConnectTimeout;
        }
    }

    /**
     * Errorメールメッセージを取得します。
     * 
     * @return Errorメールメッセージ
     */
    public static String getErrorBody() {
        return errorBody;
    }

    /**
     * Errorメール件名を取得します。
     * 
     * @return Errorメール件名
     */
    public static String getErrorSubject() {
        return errorSubject;
    }

    /**
     * バージョンを取得します。
     * 
     * @return バージョン
     */
    public static String getVersion() {
        return version;
    }

    /**
     * デーフォルトEncodeを取得します。
     * 
     * @return デーフォルトEncode
     */
    public static String getWordEncode() {
        return wordEncode;
    }

    /**
     * デーフォルトEXTを取得します。
     * 
     * @return デーフォルトEXT
     */
    public static String getDefaultExt() {
        return defaultExt;
    }

    /**
     * Encoding配列を取得します。
     * 
     * @return Encoding配列
     */
    public static ArrayList<Hashtable<String, String>> getMailEncodingList() {
        return encodingArray;
    }

    /**
     * Charsetを取得します。
     * 
     * @return Charset
     */
    public static String getMailCharset(String encodingName) {
        Hashtable<String, String> enc =
            (Hashtable<String, String>) encodingList.get(encodingName);
        if (enc != null) {
            return (String) enc.get("charset");
        }
        return null;
    }

    /**
     * Mime encodingを取得します。
     * 
     * @return Mime encoding
     */
    public static String getMailMimeEncoding(String encodingName) {
        Hashtable<String, String> enc =
            (Hashtable<String, String>) encodingList.get(encodingName);
        if (enc != null) {
            return (String) enc.get("mimeEncoding");
        }
        return null;
    }
}
