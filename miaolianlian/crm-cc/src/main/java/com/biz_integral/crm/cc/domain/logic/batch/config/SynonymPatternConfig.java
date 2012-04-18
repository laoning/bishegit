/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.config;

import java.util.List;

/**
 * 類義語パターンの動作設定情報を返却するインターフェースです。
 */
public interface SynonymPatternConfig {

    /**
     * 対象の会社コードリストを返却します。
     * 
     * @return 会社コードリスト @
     */
    public List<String> getCompanyList();

    /**
     * 出力先フォルダを返却します。
     * 
     * @param companyCd
     *            会社コード
     * @return 設定内容 @
     */
    public String getFolder(String companyCd);

    /**
     * 出力ファイル名を返却します。
     * 
     * @param companyCd
     *            会社コード
     * @return 設定内容 @
     */
    public String getFile(String companyCd);

    /**
     * 会社ごとに出力するかの設定を返却します。
     * 
     * @param companyCd
     *            会社コード
     * @return 設定内容 @
     */
    public boolean getCompanyMode(String companyCd);

    /**
     * ロケールごとに出力するかの設定を返却します。
     * 
     * @param companyCd
     *            会社コード
     * @return 設定内容 @
     */
    public boolean getLocaleMode(String companyCd);

    /**
     * ファイルの文字コードを返却します。
     * 
     * @param companyCd
     *            会社コード
     * @return 設定内容 @
     */
    public String getEncoding(String companyCd);
}
