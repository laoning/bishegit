/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.util;

import com.biz_integral.crm.extension.mail.model.MailSettings;
import com.biz_integral.foundation.core.logging.BizLogger;
import com.biz_integral.foundation.core.logging.BizLoggerFactory;
import com.biz_integral.foundation.core.logging.LogLevel;

/**
 * mailのログ機能
 * 
 */
public class MailLog {

    /** BizLogger */
    protected BizLogger log = BizLoggerFactory.getLogger(this.getClass());

    /**
     * コンストラクタ
     */
    public MailLog() {
        super();
    }

    /**
     * ログを出力する
     * 
     * @param e
     *            出力する例外
     */
    public void write(Exception e) {
        e.printStackTrace();
        log.log(LogLevel.ERROR, e.getMessage().toString());
    }

    /**
     * ログを出力する
     * 
     * @param e
     *            出力する例外
     * @param serverInfo
     *            メールサーバ情報
     */
    public void write(Exception e, MailSettings serverInfo) {

        if (serverInfo == null) {
            write(e);
            return;
        }
        e.printStackTrace();

        String message;
        String error = e.getMessage();
        String protocol = serverInfo.getProtocol();
        String host = serverInfo.getHost();
        int port = serverInfo.getPort();
        String user = serverInfo.getUserName();

        message =
            error + "\t" + protocol + "\t" + host + "\t" + port + "\t" + user;

        log.log(LogLevel.ERROR, message);
    }

    /**
     * ログを出力する
     * 
     * @param e
     *            出力する例外
     * @param protocol
     *            メールサーバ種類
     * @param host
     *            メールサーバのアドレス
     * @param port
     *            メールサーバのポート
     * @param user
     *            ユーザ
     */
    public void write(Exception e, String protocol, String host, int port,
            String user) {
        String error = e.getMessage();
        String message;

        e.printStackTrace();

        message =
            error + "\t" + protocol + "\t" + host + "\t" + port + "\t" + user;

        log.log(LogLevel.ERROR, message);
    }
}
