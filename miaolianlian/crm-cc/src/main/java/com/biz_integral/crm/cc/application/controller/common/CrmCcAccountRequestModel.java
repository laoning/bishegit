/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * 
 * CRMアカウント検索条件モデルです。
 */
public class CrmCcAccountRequestModel extends PagerSupport {

    /**
     * アカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountCd", arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    public String crmAccountCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
