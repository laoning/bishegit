/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.connectCompanySet;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;

/**
 * 接続会社設定画面の画面初期化レスポンスです。
 */
public class ConnectCompanySetInitializeResponseModel {

    /**
     * 処理会社リスト
     */
    public List<KeyValueBean> handleCompanies = newArrayList();

    /**
     * 所属組織リスト
     */
    public List<KeyValueBean> affiliationDepartments = newArrayList();

    /**
     * 処理会社
     */
    public String handleCompany;

    /**
     * 所属組織
     */
    public String mainDepartment;

}
