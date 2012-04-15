/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.datasource;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import com.biz_integral.crm.extension.mail.util.Utility;

/**
 * バイトアレイデータソース
 * 
 */
public class ByteArrayDataSource implements javax.activation.DataSource {

    byte[] bytes;
    String contentType, name;
    String charset = "";
    String WordEncode = "";

    /**
     * コンストラクタ
     * 
     * @param bytes
     *            バイトアレイ
     * @param name
     *            データソース名
     * @param locale
     *            ローカル
     * @param encoding
     *            文字コード
     */
    public ByteArrayDataSource(byte[] bytes, String name, Locale locale,
            String encoding) {
        this.bytes = bytes;

        this.name = Utility.getEncodedWord(name, locale, encoding);

        if (name.endsWith(".txt")) {
            this.contentType = "text/plain";
        } else if (name.endsWith(".gif")) {
            this.contentType = "image/gif";
        } else if (name.endsWith(".jpg")) {
            this.contentType = "image/jpeg";
        } else if (name.endsWith(".eml")) {
            this.contentType = "message/rfc822";
        } else {
            this.contentType = "application/octet-stream";
        }
    }

    /**
     * コンテントタイプを取得する
     * 
     * @return コンテントタイプ
     */
    public String getContentType() {
        return contentType;
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
        return name;
    }

    /**
     * Output Streamを取得する
     * 
     * @return Output Stream
     */
    public OutputStream getOutputStream() throws IOException {
        throw new FileNotFoundException();
    }
}
