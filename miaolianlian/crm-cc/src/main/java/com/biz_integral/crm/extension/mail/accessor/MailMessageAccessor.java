/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.accessor;

import java.util.Date;

import javax.mail.Session;
import javax.mail.Store;

import com.biz_integral.crm.extension.mail.common.Accessor;
import com.biz_integral.crm.extension.mail.common.exception.AccessorException;
import com.biz_integral.crm.extension.mail.model.MailFolder;
import com.biz_integral.crm.extension.mail.model.MailMessage;
import com.biz_integral.crm.extension.mail.model.MailSettings;

/**
 * メールメッセージのアクセサインタフェース
 * 
 */
public abstract interface MailMessageAccessor extends Accessor {

    /**
     * 初期化
     * 
     * @param session
     *            メールセッション
     * @param serverInfo
     *            メールサーバ設定
     */
    public abstract void init(Session session, MailSettings serverInfo);

    /**
     * メールを送ります。saveMessageはtrueの場合送ったメールが指定フォルダに保存します。<br>
     * 
     * @param message
     *            メッセージ
     * @param store
     *            メールStore
     * @param debug
     *            デバッグモード
     * @param folderName
     *            メールフォルダ
     * @return 送った状態
     * @throws AccessorException
     */
    public abstract boolean sendMail(MailMessage message, Store store,
            boolean debug, String folderName) throws AccessorException;

    /**
     * エラー通知メールを送ります。<br>
     * 
     * @param message
     *            メッセージ
     * @return 送った状態
     * @throws AccessorException
     */
    public abstract boolean sendErrorMail(MailMessage message)
        throws AccessorException;

    /**
     * エラー通知メールを作成します。<br>
     * 
     * @param orgMessage
     *            メッセージ
     * @param errorMessage
     *            エラーメッセージ
     * @param debug
     *            デバッグモード
     * @return 作成したメッセージ
     * @throws AccessorException
     */
    public abstract MailMessage createErrorMessage(MailMessage orgMessage,
            String errorMessage, boolean debug) throws AccessorException;

    /**
     * メッセージを検索します。<br>
     * 
     * @param folder
     *            フォルダ
     * @param startDate
     *            開始日付
     * @param endDate
     *            終了日付
     * @param mode
     *            検索モード
     * @param searchTerms
     *            検索条件
     * @return メッセージ配列
     * @throws AccessorException
     */
    public abstract MailMessage[] searchMessage(MailFolder folder,
            Date startDate, Date endDate, String mode, String[] searchTerms)
        throws AccessorException;

    /**
     * メール一覧を取得します。<br>
     * 
     * @param folder
     *            フォルダ
     * @param start
     *            開始のメッセージ順番
     * @param end
     *            終了のメッセージ順番
     * @return メッセージ配列
     * @throws AccessorException
     */
    public abstract MailMessage[] getMessages(MailFolder folder, int start,
            int end) throws AccessorException;

    /**
     * 指定したメールを取得します。<br>
     * 
     * @param folder
     *            フォルダ
     * @param msgno
     *            メッセージNo
     * @param charset
     *            文字コード、NULL可
     * @return メッセージ
     * @throws AccessorException
     */
    public abstract MailMessage getMessage(MailFolder folder, int msgno,
            String charset) throws AccessorException;

    /**
     * メッセージ数を取得します。<br>
     * 
     * @param folder
     *            フォルダ
     * @return メッセージ数
     * @throws AccessorException
     */
    public abstract int getMessageCount(MailFolder folder)
        throws AccessorException;

    /**
     * メッセージを削除します。<br>
     * 
     * @param message
     *            メッセージ
     * @param destFolder
     *            ゴミ箱フォルダ
     * @throws AccessorException
     */
    public abstract void remove(MailMessage message, String destFolder)
        throws AccessorException;
}
