/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * CRMマネージャインタフェース
 * 
 */
public abstract class CRMManager {

    /**
     * ログイングループＩＤ
     */
    private String loginGroupId = null;

    // /**
    // * accessorノード名
    // */
    // private static final String NODE_ACCESSOR = "accessor";

    // /**
    // * アクセサの設定ファイルのパス情報
    // */
    // public static final String MAPPING_FILE_PATH =
    // "/com.biz_integral.crm.api/default-isp-config.xml";

    /**
     * 新しいマネージャインスタンスを生成します。
     * 
     * @param groupId
     *            ログイングループＩＤ
     */
    public CRMManager(String groupId) {
        this.loginGroupId = groupId;
    }

    /**
     * ログイングループ名を取得します。
     * 
     * @return ログイングループ名
     */
    public String getLoginGroupId() {
        return this.loginGroupId;
    }

    // /**
    // * accessorNodeを取得します。
    // *
    // * @return accessorNode
    // */
    // private static XmlNode accessorNode = null;
    //
    // /**
    // * XmlNodeを取得します。
    // *
    // * @return XmlNode
    // */
    // private static XmlNode getAccessorNode() {
    // if (accessorNode == null) {
    // InputStream stream =
    // CRMManager.class.getResourceAsStream(MAPPING_FILE_PATH);
    // try {
    // DOMInfo domInfo = DOMBuilder.newInstance(stream);
    // accessorNode =
    // new XmlNode(domInfo.getRootNode()).select(NODE_ACCESSOR)[0];
    // } catch (IOException e) {
    // e.printStackTrace();
    // } catch (SAXException e) {
    // e.printStackTrace();
    // } catch (Exception e) {
    // e.printStackTrace();
    // } finally {
    // try {
    // stream.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // }
    // return accessorNode;
    // }

    private static final Map<String, Map<String, String>> newAccessorClassMap =
        new HashMap<String, Map<String, String>>();
    private static final Map<String, String> mailMap =
        new HashMap<String, String>();
    static {
        mailMap
            .put(
                "mail-message-class",
                "com.biz_integral.crm.extension.mail.accessor.impl.MailMessageAccessorImpl");
        mailMap
            .put(
                "imap-mail-message-class",
                "com.biz_integral.crm.extension.mail.accessor.impl.IMAPMailMessageAccessorImpl");
        mailMap
            .put(
                "mail-folder-class",
                "com.biz_integral.crm.extension.mail.accessor.impl.MailFolderAccessorImpl");

        newAccessorClassMap.put("mail", mailMap);

    }

    /**
     * アクセサを取得します。
     * 
     * @param appNodeName
     *            ノード名
     * @return アクセサマップ
     */
    protected Map<String, Accessor> getAccessors(String appNodeName) {
        Map<String, Accessor> accessorMap = new HashMap<String, Accessor>();
        String groupId = this.getLoginGroupId();

        Map<String, String> map = newAccessorClassMap.get(appNodeName);
        Iterator<Map.Entry<String, String>> iterator =
            map.entrySet().iterator();
        try {
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String keyName = entry.getKey();
                String accessorClassName = entry.getValue();
                Accessor accessor =
                    (Accessor) (Class.forName(accessorClassName)).newInstance();
                accessor.setLoginGroupId(groupId);
                accessorMap.put(keyName, accessor);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return accessorMap;

    }
}
