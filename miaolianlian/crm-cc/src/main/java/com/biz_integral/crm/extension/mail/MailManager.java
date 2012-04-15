/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.biz_integral.crm.extension.mail.accessor.IMAPMailMessageAccessor;
import com.biz_integral.crm.extension.mail.accessor.MailFolderAccessor;
import com.biz_integral.crm.extension.mail.accessor.MailMessageAccessor;
import com.biz_integral.crm.extension.mail.common.Accessor;
import com.biz_integral.crm.extension.mail.common.CRMManager;
import com.biz_integral.crm.extension.mail.common.exception.AccessorException;
import com.biz_integral.crm.extension.mail.common.exception.CRMManagerException;
import com.biz_integral.crm.extension.mail.model.MailFolder;
import com.biz_integral.crm.extension.mail.model.MailMessage;
import com.biz_integral.crm.extension.mail.model.MailSettings;
import com.biz_integral.crm.extension.mail.model.MailSysInfo;
import com.biz_integral.crm.extension.mail.util.MailLog;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * メール管理マネージャクラス
 * 
 */
public class MailManager extends CRMManager {

    /**
     * {@link ContextContainer}
     */
    @Resource
    protected ContextContainer contextContainer;

    // デバッグモード
    private boolean debug = false;

    // メールサーバの設定
    private MailSettings serverInfo = null;

    // 接続フラグ
    private boolean isInternalConnect = false;

    // メールストー
    private Store store;

    // メールセッション
    private Session session;

    // メールノード
    private static final String NODE_MAIL = "mail";

    // メールメッセージノード
    private static final String NODE_MAIL_MESSAGE_CLASS = "mail-message-class";

    // IMAPメールメッセージノード
    private static final String NODE_IMAP_MAIL_MESSAGE_CLASS =
        "imap-mail-message-class";

    // メールフォルダノード
    private static final String NODE_MAIL_FOLDER_CLASS = "mail-folder-class";

    // メールメッセージのアクセサ
    private MailMessageAccessor mailMessageAccessor;

    // IMAPメールメッセージのアクセサ
    private IMAPMailMessageAccessor IMAPMailMessageAccessor;

    // メールフォルダ管理情報アクセサ
    private MailFolderAccessor mailFolderAccessor;

    // オープンしたフォルダ
    private MailFolder curFolder = null;

    /**
     * メール管理マネージャのインスタンスを生成します。
     * 
     * @param groupId
     *            ログイングループＩＤ
     */
    public MailManager(String groupId) {
        super(groupId);
        Map<String, Accessor> accessorsMap = this.getAccessors(NODE_MAIL);

        this.mailMessageAccessor =
            (MailMessageAccessor) accessorsMap.get(NODE_MAIL_MESSAGE_CLASS);
        this.IMAPMailMessageAccessor =
            (IMAPMailMessageAccessor) accessorsMap
                .get(NODE_IMAP_MAIL_MESSAGE_CLASS);
        this.mailFolderAccessor =
            (MailFolderAccessor) accessorsMap.get(NODE_MAIL_FOLDER_CLASS);
    }

    /**
     * メール管理マネージャのインスタンスを生成します。
     */
    public MailManager() {
        this(null);
    }

    /**
     * 初期化と接続メソッド。<br>
     * 
     * @param serverInfo
     *            メールサーバ設定
     * @param debug
     *            デバッグモード <br>
     *            trueの時デバッギングを有効にする、falseの時デバッギングを無効にする。
     * @param connect
     *            接続モード　<br>
     *            trueの時に、接続と初期化。falseの時に、初期化だけ。
     * @return 接続状態
     * @throws CRMManagerException
     */
    public synchronized boolean initConnect(MailSettings serverInfo,
            boolean debug, boolean connect) throws CRMManagerException {
        try {
            this.serverInfo = serverInfo;
            this.debug = debug;
            if (serverInfo == null) {
                return false;
            }

            if (connect) {
                // サーバへ接続する場合
                String protocolStr = "";

                if (serverInfo.getProtocol().equalsIgnoreCase("pop")
                    || serverInfo.getProtocol().equalsIgnoreCase("pop3")) {
                    protocolStr = "pop3";
                } else if (serverInfo.getProtocol().equalsIgnoreCase("imap")) {
                    protocolStr = "imap";
                }

                try {
                    Properties props = new Properties();
                    String socketTimeout =
                        MailSysInfo.getSocketTimeout(protocolStr);
                    String connectionTimeout =
                        MailSysInfo.getConnectTimeout(protocolStr);

                    if (socketTimeout != null) {
                        props.setProperty("mail."
                            + protocolStr.toLowerCase()
                            + ".timeout", socketTimeout);
                    }
                    if (connectionTimeout != null) {
                        props.setProperty("mail."
                            + protocolStr.toLowerCase()
                            + ".connectiontimeout", connectionTimeout);
                    }

                    session = Session.getInstance(props, null);
                    session.setDebug(debug);
                } catch (RuntimeException re) {
                    throw AccessorException.throwAccessorException(
                        "E.CRM.CC.00092",
                        re);
                } catch (Exception e) {
                    throw AccessorException.throwAccessorException(
                        "E.CRM.CC.00092",
                        e);
                }

                try {
                    store = session.getStore(serverInfo.getProtocol());
                } catch (NoSuchProviderException nspe) {
                    throw AccessorException.throwAccessorException(
                        "E.CRM.CC.00092",
                        nspe);
                } catch (Exception e) {
                    throw AccessorException.throwAccessorException(
                        "E.CRM.CC.00092",
                        e);
                }

                try {
                    store.connect(
                        serverInfo.getHost(),
                        serverInfo.getPort(),
                        serverInfo.getUserName(),
                        serverInfo.getPassword());
                } catch (AuthenticationFailedException afe) {
                    throw AccessorException.throwAccessorException(
                        "E.CRM.CC.00092",
                        afe);
                } catch (MessagingException me) {
                    throw AccessorException.throwAccessorException(
                        "E.CRM.CC.00092",
                        me);
                }

                isInternalConnect = true;
            }

            mailMessageAccessor.init(session, serverInfo);
            IMAPMailMessageAccessor.init(session, serverInfo);

            return true;
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        }
    }

    /**
     * 切断メソッド。<br>
     * 
     * @return 切断状態
     */
    public synchronized boolean disconnect() {
        if (isInternalConnect) {
            // コネクトがある場合
            try {
                store.close();
            } catch (Exception e) {
                new MailLog().write(e, serverInfo);
                return false;
            } finally {
                isInternalConnect = false;
            }
        }
        return true;
    }

    /**
     * 接続状態確認メソッド。<br>
     * 
     * @return 接続状態
     */
    public boolean isConnect() {
        return store.isConnected();
    }

    /**
     * フォルダをオープンします。<br>
     * 
     * @param folderName
     *            フォルダ名
     * @throws CRMManagerException
     */
    public void openCurFolder(String folderName) throws CRMManagerException {
        try {
            curFolder = mailFolderAccessor.open(store, folderName, true);
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        }
    }

    /**
     * フォルダをクローズします。<br>
     * 
     * @throws CRMManagerException
     */
    public void closeCurFolder() throws CRMManagerException {
        try {
            if (curFolder == null) {
                return;
            }
            mailFolderAccessor.close(curFolder);
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        }
    }

    /**
     * メールを送信します。<br>
     * 
     * @param message
     *            メッセージ
     * @param folderName
     *            メールフォルダ
     * @return 送った状態
     * @throws CRMManagerException
     */
    public boolean sendMail(MailMessage message, String folderName)
        throws CRMManagerException {
        try {
            return mailMessageAccessor.sendMail(
                message,
                store,
                debug,
                folderName);
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        }
    }

    /**
     * エラー通知メールを送信します。<br>
     * 
     * @param orgMessage
     *            メッセージ
     * @param errorMessage
     *            エラーメッセージ
     * @return 送信状態
     * @throws CRMManagerException
     */
    public boolean sendErrorMail(MailMessage orgMessage, String errorMessage)
        throws CRMManagerException {
        try {
            MailMessage message =
                mailMessageAccessor.createErrorMessage(
                    orgMessage,
                    errorMessage,
                    debug);
            return mailMessageAccessor.sendErrorMail(message);
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        }
    }

    /**
     * メール一覧を取得します。<br>
     * 
     * @param folderName
     *            フォルダ名
     * @param start
     *            開始のメッセージ順番
     * @param end
     *            終了のメッセージ順番
     * @return メッセージ配列
     * @throws CRMManagerException
     */
    public MailMessage[] getMessages(String folderName, int start, int end)
        throws CRMManagerException {
        boolean openfolder = false;
        MailFolder folder = curFolder;
        try {
            if (curFolder == null
                || !folderName.equalsIgnoreCase(curFolder.getName())) {
                // フォルダをオープンする。
                folder = mailFolderAccessor.open(store, folderName, true);
                openfolder = true;
            }
            MailMessage[] messages =
                mailMessageAccessor.getMessages(folder, start, end);
            return messages;
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        } finally {
            if (openfolder) {
                try {
                    mailFolderAccessor.close(folder);
                } catch (AccessorException e) {
                    throw new CRMManagerException(e);
                }
            }
        }
    }

    /**
     * メッセージ数を取得します。<br>
     * 
     * @param folderName
     *            フォルダ名
     * @return メッセージ数
     * @throws CRMManagerException
     */
    public int getMessageCount(String folderName) throws CRMManagerException {
        boolean openfolder = false;
        MailFolder folder = curFolder;
        try {
            if (curFolder == null
                || !folderName.equalsIgnoreCase(curFolder.getName())) {
                // フォルダをオープンする。
                folder = mailFolderAccessor.open(store, folderName, true);
                openfolder = true;
            }
            int count = mailMessageAccessor.getMessageCount(folder);
            return count;
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        } finally {
            if (openfolder) {
                try {
                    mailFolderAccessor.close(folder);
                } catch (AccessorException e) {
                    throw new CRMManagerException(e);
                }
            }
        }
    }

    /**
     * メッセージを削除します。<br>
     * 
     * @param folderName
     *            フォルダ名
     * @param msgno
     *            メッセージNo
     * @throws CRMManagerException
     */
    public void removeMessage(String folderName, int msgno)
        throws CRMManagerException {
        boolean openfolder = false;
        MailFolder folder = curFolder;
        try {
            if (curFolder == null
                || !folderName.equalsIgnoreCase(curFolder.getName())) {
                // フォルダをオープンする。
                folder = mailFolderAccessor.open(store, folderName, true);
                openfolder = true;
            }
            MailMessage message =
                mailMessageAccessor.getMessage(folder, msgno, null);
            // DELフラグまたは戻るフォルダフラグを設定する。
            mailMessageAccessor.remove(message, folderName);
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        } finally {
            if (openfolder) {
                try {
                    mailFolderAccessor.close(folder);
                } catch (AccessorException e) {
                    throw new CRMManagerException(e);
                }
            }
        }

    }

    /**
     * POP3時、メッセージを削除します。<br>
     * 
     * @param folderName
     *            フォルダ名
     * @param msgno
     *            メッセージNo
     * @param uid
     *            メッセージUID
     * @throws CRMManagerException
     */
    public void removePOPMessage(String folderName, int msgno, String uid)
        throws CRMManagerException {
        boolean openfolder = false;
        MailFolder folder = curFolder;
        try {
            if (curFolder == null
                || !folderName.equalsIgnoreCase(curFolder.getName())) {
                // フォルダをオープンする。
                folder = mailFolderAccessor.open(store, folderName, true);
                openfolder = true;
            }
            MailMessage message =
                mailMessageAccessor.getMessage(folder, msgno, null);

            if (!uid.equalsIgnoreCase(message.getUID())) {
                throw new CRMManagerException(AccessorException
                    .throwAccessorException("E.CRM.CC.00098"));
            }
            // DELフラグまたは戻るフォルダフラグを設定する。
            mailMessageAccessor.remove(message, folderName);
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        } finally {
            if (openfolder) {
                try {
                    mailFolderAccessor.close(folder);
                } catch (AccessorException e) {
                    throw new CRMManagerException(e);
                }
            }
        }

    }

    /**
     * フォルダの存在を判断します。<br>
     * 
     * @param folderName
     *            フォルダ名
     * @return 存在かどうか
     * @throws CRMManagerException
     */
    public boolean isExistFolder(String folderName) throws CRMManagerException {
        boolean isExists;
        try {
            isExists = mailFolderAccessor.exists(store, folderName);
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        }
        return isExists;
    }

    /**
     * メッセージを検索します。<br>
     * 
     * @param folderName
     *            フォルダ名
     * @param startDate
     *            開始日付
     * @param endDate
     *            終了日付
     * @param mode
     *            検索モード
     * @param searchTerms
     *            検索条件
     * @return メッセージ配列
     * @throws CRMManagerException
     */
    public MailMessage[] searchMessage(String folderName, Date startDate,
            Date endDate, String mode, String[] searchTerms)
        throws CRMManagerException {

        MailFolder folder = curFolder;

        try {
            if (curFolder == null
                || !folderName.equalsIgnoreCase(curFolder.getName())) {
                // フォルダをオープンする。
                folder = mailFolderAccessor.open(store, folderName, true);
            }
            return mailMessageAccessor.searchMessage(
                folder,
                startDate,
                endDate,
                mode,
                searchTerms);
        } catch (AccessorException e) {
            throw new CRMManagerException(e);
        }
    }

    /**
     * デバッグモードを取得します。
     * 
     * @return　デバッグモード
     */
    public boolean getDebug() {
        return debug;
    }

    /**
     * デバッグモードを設定します。
     * 
     * @param debug
     *            　デバッグモード
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * サーバ設定を取得します。
     * 
     * @return　サーバ設定
     */
    public MailSettings getServerInfo() {
        return serverInfo;
    }

    /**
     * サーバ設定を設定します。
     * 
     * @param serverInfo
     *            　サーバ設定
     */
    public void setServerInfo(MailSettings serverInfo) {
        this.serverInfo = serverInfo;
    }
}
