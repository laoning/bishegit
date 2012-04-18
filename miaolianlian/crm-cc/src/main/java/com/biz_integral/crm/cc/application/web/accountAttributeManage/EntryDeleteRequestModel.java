/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウント属性登録／更新画面の削除引数モデルです。
 */
public final class EntryDeleteRequestModel {

    /**
     * 取引種別
     */
    @Required(arg0 = @Arg(key = "CRM.CC.dealClass"))
    public String dealClass;

    /**
     * CRMアカウント分類コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountClassCd", arg0 = @Arg(key = "CRM.CC.crmAccountClassCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountClassCd"))
    public String crmAccountClassCd;

    /**
     * 状況
     */
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountStatus"))
    public String crmAccountStatus;

    /**
     * バージョン番号
     */
    public long versionNo;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
