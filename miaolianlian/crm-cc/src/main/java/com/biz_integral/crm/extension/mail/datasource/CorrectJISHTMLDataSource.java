/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.datasource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.UnknownServiceException;
import java.util.Locale;

import com.biz_integral.crm.extension.mail.model.MailSysInfo;
import com.biz_integral.crm.extension.mail.util.Utility;

/**
 * Correct JIS　HTMLデータソース
 * 
 */
public class CorrectJISHTMLDataSource implements javax.activation.DataSource {

    byte[] bytes;
    String contentType, name;
    String charset;

    /**
     * コンストラクタ
     * 
     * @param str
     *            文字列
     * @param locale
     *            ローカル
     * @param encoding
     *            　エンコード
     */
    public CorrectJISHTMLDataSource(String str, Locale locale, String encoding) {
        this.charset = Utility.getCharSet(locale);

        if (encoding != null && !encoding.equals("")) {
            this.charset = MailSysInfo.getMailCharset(encoding);
            if (this.charset == null) {
                this.charset = encoding;
            }
        }
        if (charset.equalsIgnoreCase("iso-2022-jp")) {
            // 日本語の時
            try {
                byte[] sjisBytes = str.getBytes("Windows-31J");
                ByteArrayInputStream bis = new ByteArrayInputStream(sjisBytes);
                this.bytes = Utility.changeSJIStoJIS(bis);
            } catch (UnsupportedEncodingException e) {
                // ほとんどありえないけど。。。
                this.bytes = str.getBytes();
            }
        } else {
            // 外の時
            try {
                this.bytes = str.getBytes(this.charset);
            } catch (UnsupportedEncodingException e) {
                // ほとんどありえないけど。。。
                this.bytes = str.getBytes();
            }
        }

    }

    /**
     * コンテントタイプを取得する
     * 
     * @return コンテントタイプ
     */
    public String getContentType() {
        return "text/html;\r\n\tcharset=\"" + this.charset + "\"";
    }

    /**
     * Input Streamを取得する
     * 
     * @return Input Stream
     */
    public InputStream getInputStream() {
        return new ByteArrayInputStream(bytes);
    }

    /**
     * データソース名を取得する
     * 
     * @return データソース名
     */
    public String getName() {
        return "";
    }

    /**
     * Output Streamを取得する
     * 
     * @return Output Stream
     */
    public OutputStream getOutputStream() throws IOException {
        throw new UnknownServiceException();
    }
}
