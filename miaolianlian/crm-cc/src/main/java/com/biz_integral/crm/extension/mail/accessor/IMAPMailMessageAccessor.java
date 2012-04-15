/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.accessor;

import javax.mail.Session;

import com.biz_integral.crm.extension.mail.common.Accessor;
import com.biz_integral.crm.extension.mail.common.exception.AccessorException;
import com.biz_integral.crm.extension.mail.model.MailFolder;
import com.biz_integral.crm.extension.mail.model.MailMessage;
import com.biz_integral.crm.extension.mail.model.MailSettings;

/**
 * IMAPメールメッセージのアクセサインタフェース
 * 
 */
public abstract interface IMAPMailMessageAccessor extends Accessor {

    /**
     * 初期化
     * 
     * @param session
     *            メールセッション
     * @param serverInfo
     *            メールサーバ設定
     */
    public void init(Session session, MailSettings serverInfo);

    /**
     * メール一覧を取得します。<br>
     * 
     * @param folder
     *            フォルダ
     * @param uid_arr
     *            UID配列
     * @return メッセージ配列
     * @throws AccessorException
     */
    public abstract MailMessage[] getUIDMessages(MailFolder folder,
            String[] uid_arr) throws AccessorException;

    /**
     * 指定したメールを取得します。<br>
     * 
     * @param folder
     *            フォルダ
     * @param uids
     *            UID
     * @param charset
     *            文字コード、NULL可
     * @return メッセージ
     * @throws AccessorException
     */
    public abstract MailMessage getUIDMessage(MailFolder folder, String uids,
            String charset) throws AccessorException;
}
