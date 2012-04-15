/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.model;

/**
 * メールの設定を持つクラス
 * 
 */
public class MailSettings {
    /**
     * ホスト名
     */
    private String host;

    /**
     * ポート番号
     */
    private int port;

    /**
     * ユーザ名
     */
    private String userName;

    /**
     * パスワード
     */
    private String password;

    /**
     * SMTPAuthを使うかどうか
     */
    private boolean needAuth;

    /**
     * メールアドレス
     */
    private String fromAddr;

    /**
     * メールアドレスの名前
     */
    private String fromName;

    /**
     * サーバ種類
     */
    private String protocol;

    /**
     * SMTP用ホスト名
     */
    private String SMTPHost;

    /**
     * SMTP用ポート番号
     */
    private int SMTPPort;

    /**
     * SMTP用ユーザ名
     */
    private String SMTPUserName;

    /**
     * SMTP用パスワード
     */
    private String SMTPPassword;

    /**
     * デフォルトコンストラクタ
     */
    public MailSettings() {
        this.host = null;
        this.port = 0;
        this.userName = null;
        this.password = null;
        this.needAuth = false;
        this.fromAddr = null;
        this.fromName = null;
        this.protocol = null;
        this.SMTPHost = null;
        this.SMTPPort = 0;
        this.SMTPUserName = null;
        this.SMTPPassword = null;
    }

    /**
     * メールアドレスを取得します。
     * 
     * @return メールアドレス
     */
    public String getFromAddr() {
        return fromAddr;
    }

    /**
     * メールアドレスをセット
     * 
     * @param fromAddr
     *            メールアドレス
     */
    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    /**
     * メールアドレスの名前を取得します。
     * 
     * @return メールアドレスの名前
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * メールアドレスの名前をセット
     * 
     * @param fromName
     *            メールアドレスの名前
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     * SMTPAuthを取得します。
     * 
     * @return SMTPAuth
     */
    public boolean isNeedAuth() {
        return needAuth;
    }

    /**
     * SMTPAuthをセット
     * 
     * @param needAuth
     *            SMTPAuth
     */
    public void setNeedAuth(boolean needAuth) {
        this.needAuth = needAuth;
    }

    /**
     * パスワードを取得します。
     * 
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードをセット
     * 
     * @param password
     *            パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * サーバ種類を取得します。
     * 
     * @return サーバ種類
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * サーバ種類をセット
     * 
     * @param protocol
     *            サーバ種類
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * ユーザ名を取得します。
     * 
     * @return ユーザ名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザ名をセット
     * 
     * @param userName
     *            ユーザ名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * メールの識別名を取得します。
     * 
     * @return メールの識別名
     */
    public String getAddress() {
        return userName + "@" + host + ":" + port;
    }

    /**
     * ホスト名を取得します。
     * 
     * @return ホスト名
     */
    public String getHost() {
        return host;
    }

    /**
     * ポート番号を取得します。
     * 
     * @return ポート番号
     */
    public int getPort() {
        return port;
    }

    /**
     * ホスト名をセット
     * 
     * @param host
     *            ホスト名
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * ポート番号をセット
     * 
     * @param port
     *            ポート番号
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * SMTPAuthクラスを取得します
     * 
     * @return SMTPAuthクラス
     */
    public SMTPAuthenticator getSMTPAuth() {
        if (needAuth) {
            return new SMTPAuthenticator(SMTPUserName, SMTPPassword);
        } else {
            return null;
        }
    }

    /**
     * SMTP用ホスト名を取得します。
     * 
     * @return SMTP用ホスト名
     */
    public String getSMTPHost() {
        return SMTPHost;
    }

    /**
     * SMTP用ホスト名をセット
     * 
     * @param host
     *            SMTP用ホスト名
     */
    public void setSMTPHost(String host) {
        SMTPHost = host;
    }

    /**
     * SMTP用パスワードを取得します。
     * 
     * @return SMTP用パスワード
     */
    public String getSMTPPassword() {
        return SMTPPassword;
    }

    /**
     * SMTP用パスワードをセット
     * 
     * @param password
     *            パスワード
     */
    public void setSMTPPassword(String password) {
        SMTPPassword = password;
    }

    /**
     * SMTP用ポート番号を取得します。
     * 
     * @return SMTP用ポート番号
     */
    public int getSMTPPort() {
        return SMTPPort;
    }

    /**
     * SMTP用ポート番号をセットします。
     * 
     * @param port
     *            SMTP用ポート番号
     */
    public void setSMTPPort(int port) {
        SMTPPort = port;
    }

    /**
     * SMTP用ユーザ名を取得します。
     * 
     * @return SMTP用ユーザ名
     */
    public String getSMTPUserName() {
        return SMTPUserName;
    }

    /**
     * SMTP用ユーザ名をセットします。
     * 
     * @param userName
     *            SMTP用ユーザ名
     */
    public void setSMTPUserName(String userName) {
        SMTPUserName = userName;
    }

}
