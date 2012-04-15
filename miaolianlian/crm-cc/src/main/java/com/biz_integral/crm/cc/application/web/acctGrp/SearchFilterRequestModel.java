/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.PagingRequest;
import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * アカウントグループ一覧検索条件です。
 */
public final class SearchFilterRequestModel extends PagingRequest {

    /** アカウントグループコード */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountGroupCd", arg0 = @Arg(key = "CRM.CC.crmAccountGroupCd"))
    public String crmAccountGroupCd;

    /** アカウントグループ名 */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountGroupName", arg0 = @Arg(key = "CRM.CC.crmAccountGroupName"))
    public String crmAccountGroupName;

    /** アカウントグループ検索名 */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountGroupSearchName", arg0 = @Arg(key = "CRM.CC.crmAccountGroupSearchName"))
    public String crmAccountGroupSearchName;

    /** ロケールID */
    public String localeId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
