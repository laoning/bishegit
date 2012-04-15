/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.model;

import java.util.Date;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

import com.biz_integral.crm.extension.mail.common.Model;
import com.biz_integral.crm.extension.mail.util.MailLog;
import com.sun.mail.imap.IMAPFolder;

/**
 * メールフォルダモデルインタフェース
 * 
 */
public class MailFolder implements Model {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -7732442822666934635L;

    /**
     * フォルダ設定コード
     */
    private String folderCd;
    /**
     * フォルダタイプ
     */
    private String folderType;
    // メールフォルダ
    private Folder folder = null;
    // 書けるフラグ
    private int openMode = Folder.READ_ONLY;
    // 書けるフラグ
    private MailFolder[] childFolders = null;

    /**
     * 登録ユーザコード
     */
    private String registUserCd;

    /**
     * 登録ユーザ名
     */
    private String registUserName;

    /**
     * 登録日
     */
    private Date registDate;

    /**
     * 更新ユーザコード
     */
    private String updateUserCd;

    /**
     * 更新ユーザ名
     */
    private String updateUserName;

    /**
     * 更新日
     */
    private Date updateDate;

    /**
     * 登録ユーザコード
     */
    private String recordUserCd;

    /**
     * フォルダのインスタンスを生成します。
     * 
     * @param folder
     *            JavaMailのフォルダ
     */
    public MailFolder(Folder folder) {
        this.folder = folder;
    }

    /**
     * 書けるフラグを設定します。
     * 
     * @param mode
     *            書けるフラグ
     */
    public void setWritable(boolean mode) {
        if (mode) {
            openMode = Folder.READ_WRITE;
        } else {
            openMode = Folder.READ_ONLY;
        }
    }

    /**
     * 書けるフラグを取得します。
     * 
     * @return 書けるフラグ
     */
    public int getWritable() {
        return openMode;
    }

    /**
     * フォルダ名を取得します。
     * 
     * @return フォルダ名
     */
    public String getName() {
        if (folder != null) {
            return folder.getName();
        } else {
            return "";
        }
    }

    /**
     * すべてのフォルダ名を取得します。
     * 
     * @return フォルダ名
     */
    public String getFullName() {
        if (folder != null) {
            Store store = folder.getStore();
            char newSep = getFolderSeparator(store);
            return folder.getFullName().replace(newSep, '.');
        } else {
            return "";
        }
    }

    /**
     * フォルダを取得します。
     * 
     * @return フォルダ
     */
    public Folder getFolder() {
        return folder;
    }

    /**
     * IMAPフォルダを取得します。
     * 
     * @return IMAPフォルダ
     */
    public IMAPFolder getIMAPFolder() {
        if (folder != null) {
            if (folder instanceof IMAPFolder) {
                return (IMAPFolder) folder;
            }
        }
        return null;
    }

    /**
     * メッセージ数を取得します。
     * 
     * @return メッセージ数
     */
    public int getMessageCount() {
        if (folder == null)
            return -1;
        int num;

        try {
            num = folder.getMessageCount();
        } catch (MessagingException me) {
            new MailLog().write(me);
            return -1;
        } catch (Exception e) {
            new MailLog().write(e);
            return -1;
        }

        return num;
    }

    /**
     * 未読メッセージ数を取得します。
     * 
     * @return 未読メッセージ数
     */
    public int getUnreadMessageCount() {
        if (folder == null)
            return -1;
        int num;

        try {
            num = folder.getUnreadMessageCount();
        } catch (MessagingException me) {
            new MailLog().write(me);
            return -1;
        } catch (Exception e) {
            new MailLog().write(e);
            return -1;
        }

        return num;
    }

    /**
     * ネームスペースを取得します。
     * 
     * @param store
     *            メールStore
     * @return ネームスペース
     */
    public static String getNamespace(Store store) {
        String namespace = "";
        if (store != null && store.isConnected()) {
            // サーバにコネクトする場合、ネームスペースを取得
            Folder[] fols;

            try {
                fols = store.getPersonalNamespaces();

                for (int i = 0; i < fols.length; i++) {
                    String folName = fols[i].getName();
                    if (folName != null
                        && !folName.startsWith("#")
                        && !folName.equals("")) {
                        namespace += folName + fols[i].getSeparator();
                    }
                }
            } catch (MessagingException e) {
                new MailLog().write(e);
                return "";
            }
        }
        return namespace;
    }

    /**
     * NameSpace付けるフォルダ名を取得します。
     * 
     * @param namespace
     *            ネームスペース
     * @param folderName
     *            フォルダ名
     * @return NameSpace付けるフォルダ名
     */
    public static String getNameSpaceFolderName(String namespace,
            String folderName, Store store) {

        char newSep = getFolderSeparator(store);
        folderName = folderName.replace('/', newSep);
        folderName = folderName.replace('.', newSep);
        namespace = namespace.replace('/', newSep);
        namespace = namespace.replace('.', newSep);

        if (namespace == null || namespace.equals("")) {
            // ネームスペースが空場合
            return folderName;
        } else {
            if (folderName.equalsIgnoreCase(namespace.substring(0, namespace
                .length() - 1))) {
                return folderName;
            } else {
                return namespace + folderName;
            }
        }
    }

    /**
     * フォルダ名のSeparatorを取得します。
     * 
     * @param store
     *            メールStore
     * @return String フォルダ名ののSeparatorの文字
     */
    private static char getFolderSeparator(Store store) {
        char charSeparater = '/';
        try {
            charSeparater = store.getDefaultFolder().getSeparator();
        } catch (MessagingException e) {
            new MailLog().write(e);
            return '/';
        }
        return charSeparater;
    }

    /**
     * 子フォルダ一覧を取得します
     * 
     * @return 子フォルダ配列
     */
    public MailFolder[] getChildFolders() {
        return childFolders;
    }

    /**
     * 子フォルダを設定します。
     * 
     * @param mailFolders
     *            親フォルダ名も配列
     */
    public void setChildFolders(MailFolder[] mailFolders) {
        childFolders = mailFolders;
    }

    /**
     * フォルダタイプを設定します。
     * 
     * @param folderType
     *            フォルダタイプ
     */
    public void setFolderType(String folderType) {
        this.folderType = folderType;
    }

    /**
     * フォルダタイプを取得します。
     */
    public String getFolderType() {
        if (folderType != null) {
            return folderType;
        } else {
            return "";
        }

    }

    /**
     * フォルダを取得します。
     */
    public String getFolderCd() {
        if (folderCd != null) {
            return folderCd;
        }
        return "";
    }

    /**
     * フォルダを設定します。
     * 
     * @param folderCd
     *            フォルダ名
     */
    public void setFolderCd(String folderCd) {
        this.folderCd = folderCd;
    }

    /**
     * @return recordUserCd
     */
    public String getRecordUserCd() {
        return recordUserCd;
    }

    /**
     * @param recordUserCd
     *            設定する recordUserCd
     */
    public void setRecordUserCd(String recordUserCd) {
        this.recordUserCd = recordUserCd;
    }

    /**
     * @return registDate
     */
    public Date getRegistDate() {
        return registDate;
    }

    /**
     * @param registDate
     *            設定する registDate
     */
    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    /**
     * @return registUserCd
     */
    public String getRegistUserCd() {
        return registUserCd;
    }

    /**
     * @param registUserCd
     *            設定する registUserCd
     */
    public void setRegistUserCd(String registUserCd) {
        this.registUserCd = registUserCd;
    }

    /**
     * @return registUserName
     */
    public String getRegistUserName() {
        return registUserName;
    }

    /**
     * @param registUserName
     *            設定する registUserName
     */
    public void setRegistUserName(String registUserName) {
        this.registUserName = registUserName;
    }

    /**
     * @return updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     *            設定する updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return updateUserCd
     */
    public String getUpdateUserCd() {
        return updateUserCd;
    }

    /**
     * @param updateUserCd
     *            設定する updateUserCd
     */
    public void setUpdateUserCd(String updateUserCd) {
        this.updateUserCd = updateUserCd;
    }

    /**
     * @return updateUserName
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * @param updateUserName
     *            設定する updateUserName
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
