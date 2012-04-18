/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * CRMアカウント分類コード検索用リクエストです。
 */
public final class CrmCcAccountClassRequestModel {

    /**
     * CRMアカウント分類コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountClassCd", arg0 = @Arg(key = "CRM.CC.crmAccountClassCd"))
    public String crmAccountClassCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
