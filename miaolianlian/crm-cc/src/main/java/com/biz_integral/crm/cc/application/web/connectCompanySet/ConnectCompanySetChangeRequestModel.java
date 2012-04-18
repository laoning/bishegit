/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.connectCompanySet;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * 接続会社設定画面の条件モデルです。
 */
public class ConnectCompanySetChangeRequestModel {

    /**
     * 処理会社コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "companyCd", arg0 = @Arg(key = "CRM.CC.handleCompany"))
    @Required(arg0 = @Arg(key = "CRM.CC.handleCompany"))
    public String handleCompany;

}
