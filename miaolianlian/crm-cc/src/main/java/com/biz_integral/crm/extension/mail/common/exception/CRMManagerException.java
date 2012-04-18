/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.common.exception;

/**
 * CRMマネージャ例外クラス
 */
public class CRMManagerException extends Exception {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 2670237704812038443L;

    /** エラーID */
    private String errorId;

    /** エラーメッセージ */
    private String errorMessage;

    /**
     * コンストラクタ
     * 
     * @param e
     *            AccessorException
     */
    public CRMManagerException(AccessorException e) {
        super(e);
        this.setErrorId(e.getErrorId());
        this.errorMessage = e.getErrorMessage();
    }

    /**
     * エラーメッセージを取得します。
     * 
     * @return errorMessage エラーメッセージ
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * エラーIDを取得します。
     * 
     * @return errorId エラーID
     */
    public String getErrorId() {
        return this.errorId;
    }

    /**
     * エラーIDをセットします。
     * 
     * @param errorId
     *            エラーID
     */
    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }
}
