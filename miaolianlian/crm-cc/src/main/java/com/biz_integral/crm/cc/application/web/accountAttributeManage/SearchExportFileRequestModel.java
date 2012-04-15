/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * CRMアカウント属性のエクスポート処理リクエストモデルです。
 */
public final class SearchExportFileRequestModel {

    /** アカウント分類コード */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountClassCd", arg0 = @Arg(key = "CRM.CC.crmAccountClassCd"))
    public String crmAccountClassCd;

    /** アカウント分類名 */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountClassName", arg0 = @Arg(key = "CRM.CC.crmAccountClassName"))
    public String crmAccountClassName;

    /** 取引情報 */
    public String dealClass;

    /** アカウント状況 */
    public String crmAccountStatus;

    /** モジュールID */
    public String moduleId;

    /** ロケールID */
    public String localeId;

    /** ファイル種類 */
    public String fileKind;

    /** 確認方法 */
    public String confirmWay;

    /** ファイル名 */
    public String fileName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
