/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.datasource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;

/**
 * MDNデータソース
 * 
 */
public class MDNDataSource implements DataSource {
    InternetHeaders type;
    String content = "";

    /**
     * コンストラクタ
     * 
     * @param ih
     *            ヘッダ
     */
    public MDNDataSource(InternetHeaders ih) {
        Enumeration e;

        e = ih.getAllHeaderLines();

        while (e.hasMoreElements()) {
            content = content + e.nextElement() + "\r\n";
        }
    }

    /**
     * コンテントタイプを取得する
     * 
     * @return コンテントタイプ
     */
    public String getContentType() {
        return "message/disposition-notification";
    }

    /**
     * データソース名を取得する
     * 
     * @return データソース名
     */
    public String getName() {
        return "MDN";
    }

    /**
     * Input Streamを取得する
     * 
     * @return Input Stream
     */
    public InputStream getInputStream() {
        String str = new String(content);
        byte[] ba;
        ByteArrayInputStream bis;

        ba = str.getBytes();

        bis = new ByteArrayInputStream(ba);

        return (InputStream) bis;
    }

    /**
     * Output Streamを取得する
     * 
     * @return Output Stream
     */
    public OutputStream getOutputStream() throws IOException {
        throw new IOException("cannot do this");
    }
}
