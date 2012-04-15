/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.context;

/**
 * CRMアプリケーションで独自に実装するコンテキストです。<br>
 * <p>
 * 本サンプルでは主所属組織コードをコンテキストにセットします。
 * </p>
 * 
 * @author
 * 
 */
public interface CrmContext {

    /**
     * 主所属組織コードをコンテキストに設定します。
     * 
     * @param departmentCd
     *            主所属組織コード
     */
    public abstract void setMainDepartmentCd(String departmentCd);

    /**
     * 主所属組織コードを返します。
     * 
     * @return 主所属組織コード
     */
    public abstract String getMainDepartmentCd();

    /**
     * 所属会社コードを返します。
     * 
     * @return 所属会社コード
     */
    public String getCompanyCd();

    /**
     * ユーザコードを返します
     * 
     * @return ユーザコード
     */
    public String getUserCd();

    /**
     * ロケールIDを返します
     * 
     * @return ロケールIDコード
     */
    public String getLocaleId();

    /**
     * 識別IDを取得します。
     * 
     * @return the identifier
     */
    public String getIdentifier();

    /**
     * 識別IDを設定します。
     *<p>
     * 識別IDがすでに設定されている場合、処理を行いません。
     *</p>
     * 
     * 
     */
    public void setIdentifier();

    /**
     * セッション情報を変更します。
     * <p>
     * 処理基準日は、時間を消します。
     * </p>
     * 
     * @param companyCd
     *            会社コード
     * @param departmentCd
     *            組織コード
     */
    public void switchSession(String companyCd, String departmentCd);

}