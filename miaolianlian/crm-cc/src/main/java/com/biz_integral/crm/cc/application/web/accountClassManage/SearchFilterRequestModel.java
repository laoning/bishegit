/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountClassManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.PagingRequest;
import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウント分類一覧検索条件です。
 */
public final class SearchFilterRequestModel extends PagingRequest {

    /** アカウント分類コード */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountClassCd", arg0 = @Arg(key = "CRM.CC.crmAccountClassCd"))
    public String crmAccountClassCd;

    /** アカウント分類名 */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountClassName", arg0 = @Arg(key = "CRM.CC.crmAccountClassName"))
    public String crmAccountClassName;

    /** ロケールID */
    @Required(arg0 = @Arg(key = "CRM.CC.localeId"))
    public String localeId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
