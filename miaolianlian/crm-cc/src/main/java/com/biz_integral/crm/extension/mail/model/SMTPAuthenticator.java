/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * SMTPAuth用クラス JavaMail1.2では、PLAIN認証とログイン認証のみ
 * 
 */
public class SMTPAuthenticator extends Authenticator {

    private String account;
    private String password;

    /**
     * コンストラクタ
     * 
     * @param user
     *            認証アカウント名
     * @param password
     *            パスワード
     */
    public SMTPAuthenticator(String user, String password) {
        this.account = user;
        this.password = password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.mail.Authenticator#getPasswordAuthentication()
     */
    protected PasswordAuthentication getPasswordAuthentication() {
        // PasswordAuthenticatorを返却するだけ
        return new PasswordAuthentication(account, password);
    }
}
