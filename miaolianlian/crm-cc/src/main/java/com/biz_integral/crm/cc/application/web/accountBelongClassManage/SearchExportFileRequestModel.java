/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage;

import com.biz_integral.extension.mvc.maskat.beans.PagingRequest;
import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * アカウント分類所属ファイルのエクスポート処理リクエストモデルです。
 */
public class SearchExportFileRequestModel extends PagingRequest {

    /**
     * アカウント分類コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountClassCd", arg0 = @Arg(key = "CRM.CC.crmAccountClassCd"))
    public String crmAccountClassCd;

    /**
     * アカウント分類名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountClassName", arg0 = @Arg(key = "CRM.CC.crmAccountClassName"))
    public String crmAccountClassName;

    /**
     * アカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountCd", arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    public String crmAccountCd;

    /**
     * アカウント名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountName", arg0 = @Arg(key = "CRM.CC.crmAccountName"))
    public String crmAccountName;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * ファイル種類
     */
    public String fileKind;

    /**
     * 確認方法
     */
    public String confirmWay;

    /**
     * ファイル名
     */
    public String fileName;
}
