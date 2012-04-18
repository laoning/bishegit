/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.PagingRequest;
import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * アカウント分類選択ダイアログ検索用リクエストです。
 */
public final class AccountClassSelectDialogFilterRequestModel extends
        PagingRequest {

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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
