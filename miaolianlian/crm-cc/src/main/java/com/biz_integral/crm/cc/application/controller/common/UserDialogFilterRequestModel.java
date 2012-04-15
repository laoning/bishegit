/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.PagingRequest;
import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * ユーザダイアログ検索用リクエストです。
 */
public final class UserDialogFilterRequestModel extends PagingRequest {

    /**
     * 検索基準日
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.searchBaseDate"))
    public String searchBaseDate;

    /**
     * ユーザコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "userCd", arg0 = @Arg(key = "CRM.CC.userCd"))
    public String userCd;

    /**
     * ユーザ名
     */
    @DomainConstraint(namespace = "crm.cc", type = "userName", arg0 = @Arg(key = "CRM.CC.userName"))
    public String userName;

    /**
     * ユーザ検索名
     */
    @DomainConstraint(namespace = "crm.cc", type = "userSearchName", arg0 = @Arg(key = "CRM.CC.userSearchName"))
    public String userShortName;

    /**
     * 組織コード
     */
    public String departmentCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
