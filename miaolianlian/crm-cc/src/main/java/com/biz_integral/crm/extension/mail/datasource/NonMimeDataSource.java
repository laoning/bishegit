/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.ContentType;
import javax.mail.internet.ParseException;

/**
 * Non　Mimeデータソース
 */
public class NonMimeDataSource implements DataSource {
    private DataSource source;
    private String charset;
    private boolean enforce;

    /**
     * コンストラクタ
     */
    public NonMimeDataSource() {
    }

    /**
     * コンストラクタ
     * 
     * @param dataSource
     *            データソース
     * @param defaultCharset
     *            文字コード
     * @param mode
     *            モード
     */
    public NonMimeDataSource(DataSource dataSource, String defaultCharset,
            boolean mode) {
        setDataSource(dataSource);
        setDefaultCharset(defaultCharset);
        this.enforce = mode;
    }

    /**
     * コンストラクタ
     * 
     * @param part
     *            メールパート
     * @param defaultCharset
     *            文字コード
     * @param mode
     *            モード
     */
    public NonMimeDataSource(Part part, String defaultCharset, boolean mode)
        throws MessagingException {
        setDataSource(part.getDataHandler().getDataSource());
        setDefaultCharset(defaultCharset);
        this.enforce = mode;
    }

    /**
     * コンストラクタ
     * 
     * @param part
     *            メールパート
     */
    public NonMimeDataSource(Part part) throws MessagingException {
        setDataSource(part.getDataHandler().getDataSource());
    }

    /**
     * データソースを設定する
     * 
     * @param newDS
     *            データソース
     */
    public void setDataSource(DataSource newDS) {
        source = newDS;
    }

    /**
     * 文字コードを設定する
     * 
     * @param defaultCharset
     *            文字コード
     */
    public void setDefaultCharset(String defaultCharset) {
        charset = defaultCharset;
    }

    /**
     * コンテントタイプを取得する
     * 
     * @return コンテントタイプ
     */
    public String getContentType() {
        ContentType contentType = null;
        String specifiedCharset;

        try {
            contentType = new ContentType(source.getContentType());
        } catch (ParseException e) {
            return "text/plain; charset=" + charset;
        }

        specifiedCharset = contentType.getParameter("charset");

        if (enforce
            || specifiedCharset == null
            || specifiedCharset.toLowerCase().equals("us-ascii")) {
            contentType.setParameter("charset", charset);
        }

        return contentType.toString();
    }

    /**
     * データソース名を取得する
     * 
     * @return データソース名
     */
    public String getName() {
        return source.getName();
    }

    /**
     * Input Streamを取得する
     * 
     * @return Input Stream
     */
    public InputStream getInputStream() throws IOException {
        return source.getInputStream();
    }

    /**
     * Output Streamを取得する
     * 
     * @return Output Stream
     */
    public OutputStream getOutputStream() throws IOException {
        return source.getOutputStream();
    }
}
