/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage;

/**
 * CRMパラメータ検索画面 ページャー押下responseモデルです。
 */
public class GrdParameterListResponseModel {
	
	/**
	 * CRMパラメータ名
	 */
    public String parameterName;
    
    /**
     * CRMパラメータ値
     */
    public String parameterValue;
    
    /**
     * パラメータ備考
     */
    public String notes;
    
    /**
     * CRMパラメータコード
     */
    public String parameterCd;
    
    /**
     * 期間コード
     */
    public String termCd;
    
    /**
     * バージョン番号
     */
    public String versionNo;
}