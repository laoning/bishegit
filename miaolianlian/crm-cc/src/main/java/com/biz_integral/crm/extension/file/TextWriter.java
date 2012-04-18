/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import com.biz_integral.foundation.core.exception.IORuntimeException;
import com.biz_integral.foundation.core.util.CharacterEncoding;
import com.biz_integral.foundation.core.util.CloseableUtil;

/**
 * Stringの内容をTextファイルに書き出します。
 * 
 */
public class TextWriter {

    /**
     * 書き出し時の出力ストリーム
     */
    private BufferedWriter bw;

    /**
     * 改行コード
     */
    private String lineSeparator;

    /**
     * <p>
     * コンストラクタです。
     * </p>
     */
    public TextWriter() {
        lineSeparator = System.getProperty("line.separator");
    }

    /**
     * {@inheritDoc}
     * 
     * @param path
     *            生成するファイルパス
     * @param code
     *            文字コード
     * @param list
     *            書き出すStringのリスト
     * @param overwrite
     *            同名のファイルが存在した場合に上書き
     * @return 生成したファイル
     * @throws IllegalArgumentException
     */
    public File write(String path, CharacterEncoding code, List<String> list,
            boolean overwrite) throws IllegalArgumentException {
        if (list == null || list.size() == 0) {
            return null;
        }
        File file = null;
        this.bw = null;
        try {
            file = createFile(path, overwrite);
            createBufferedWriter(file, code, false);
            for (String str : list) {
                writeLine(bw, str);
            }
            return file;
        } finally {
            CloseableUtil.close(bw);
        }
    }

    /**
     * 出力ストリームを解放します。
     * 
     */
    public void close() {
        CloseableUtil.close(this.bw);
    }

    /**
     * 引数beanの内容を引数brにCSV形式で書き出します。
     * 
     * @param bw
     *            書き出し対象のBufferedWriter
     * @param bean
     *            JavaBean
     */
    private void writeLine(BufferedWriter bw, String line) {

        try {
            bw.write(line.concat(lineSeparator));
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * ファイルパスを指定して{@link File}を生成して返します。
     * 
     * @param path
     *            生成するファイルパス
     * @param overwrite
     *            同名のファイルが存在した場合に上書き
     * @return 生成したファイル
     * @throws IllegalArgumentException
     *             既にファイルが存在した場合
     */
    private File createFile(String path, boolean overwrite) {

        File file = new File(path);
        if (file.exists()) {
            if (!overwrite) {
                file.renameTo(new File(path.concat(".bak")));
            }
        } else {
            makeDirectory(file.getParent());
        }
        return file;
    }

    /**
     * {@link BufferedWriter}を生成します。
     * 
     * @param file
     *            書き出し先のファイル
     * @param code
     *            文字コード
     * @param append
     *            追記指定オプション 追記する場合は<code>true</code>
     * @throws IORuntimeException
     *             ファイル生成に失敗した場合
     */
    private void createBufferedWriter(File file, CharacterEncoding code,
            boolean append) {
        try {
            this.bw =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    file,
                    append), code.getCode()));

        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    private void makeDirectory(String path) {

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
