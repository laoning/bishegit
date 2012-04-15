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

/**
 * Correct JISデータソース
 * 
 */
public class UTF8DataSource implements javax.activation.DataSource {
    byte[] bytes;
    String contentType, name;
    String charset;

    /**
     * コンストラクタ
     * 
     * @param str
     *            文字列
     */
    public UTF8DataSource(String str) {
        this.charset = "UTF-8";

        try {
            this.bytes = str.getBytes(this.charset);
        } catch (UnsupportedEncodingException e) {
            // ほとんどありえないけど。。。
            this.bytes = str.getBytes();
        }
    }

    /**
     * コンテントタイプを取得する
     * 
     * @return コンテントタイプ
     */
    public String getContentType() {
        return "text/plain;\r\n\tcharset=\"" + this.charset + "\"";
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
