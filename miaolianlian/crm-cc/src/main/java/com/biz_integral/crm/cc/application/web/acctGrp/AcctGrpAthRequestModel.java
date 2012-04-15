/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.PagingRequest;
import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウント所属検索条件です。
 */
public class AcctGrpAthRequestModel extends PagingRequest {

    /**
     * アカウントグループコード
     */
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountGroupCd"))
    public String crmAccountGroupCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
