package com.biz_integral.crm.cc.domain.logic;


/**
 * 最終クローラ―起動日時を管理するインターフェースです。
 */
public interface LastCrawlingDateLogic {

    /**
     * 最終クローラー起動日時を取得します。<br>
     * 設定ファイルが存在しない場合、nullを返却します。
     * 
     * @param documentId
     *            文書ID
     * @return 最終クローラー起動日時
     */
    public String getLastCrawlingDate(String documentId);

    /**
     * 最終クローラ起動日時を更新します。<br>
     * 
     * @param documentId
     *            文書ID
     * @param nowDate
     *            起動日時
     */
    public void updateLastCrawlingDate(String documentId, String nowDate);

    /**
     * 最終クローラー起動日時の登録ファイルパスを取得します。<br>
     * 
     * @param documentId
     *            文書ID
     * @return 最終クローラー起動日時の登録ファイルパス
     */
    public String getLastCrawlingDateFilePath(String documentId);

    /**
     * 最終クローラー起動日時の登録ファイル名を取得します。<br>
     * 
     * @param documentId
     *            文書ID
     * @return 最終クローラー起動日時の登録ファイル名
     */
    public String getLastCrawlingDateFileName(String documentId);

    // /**
    // * 最終クローラー起動日時を取得します。<br>
    // * 設定ファイルが存在しない場合、nullを返却します。
    // *
    // * @param contextContainer
    // * コンテキスト
    // * @param documentId
    // * 文書ID
    // * @return 最終クローラー起動日時
    // */
    // public String getLastCrawlingDate(ContextContainer contextContainer,
    // String documentId);
    //
    // /**
    // * 最終クローラ起動日時を更新します。<br>
    // *
    // * @param contextContainer
    // * コンテキスト
    // * @param documentId
    // * 文書ID
    // * @param nowDate
    // * 起動日時
    // */
    // public void updateLastCrawlingDate(ContextContainer contextContainer,
    // String documentId, String nowDate);
    //
    // /**
    // * 最終クローラー起動日時の登録ファイルパスを取得します。<br>
    // *
    // * @param contextContainer
    // * コンテキスト
    // * @param documentId
    // * 文書ID
    // * @return 最終クローラー起動日時の登録ファイルパス
    // */
    // public String getLastCrawlingDateFilePath(
    // ContextContainer contextContainer, String documentId);
    //
    // /**
    // * 最終クローラー起動日時の登録ファイル名を取得します。<br>
    // *
    // * @param contextContainer
    // * コンテキスト
    // * @param documentId
    // * 文書ID
    // * @return 最終クローラー起動日時の登録ファイル名
    // */
    // public String getLastCrawlingDateFileName(
    // ContextContainer contextContainer, String documentId);

}
