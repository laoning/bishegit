/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * 登録済ユーザ検索（表示条件指定）検索リクエストモデルです。
 */
public final class EntryUserSearchDialogFilterRequestModel {

    /**
     * ユーザーコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "userCd", arg0 = @Arg(key = "CRM.CC.userCd"))
    public String userCd;

    /**
     * ユーザー名
     */
    @DomainConstraint(namespace = "crm.cc", type = "userName", arg0 = @Arg(key = "CRM.CC.userName"))
    public String userName;

    /**
     * ユーザー検索名
     */
    @DomainConstraint(namespace = "crm.cc", type = "userSearchName", arg0 = @Arg(key = "CRM.CC.userSearchName"))
    public String userSearchName;

    /**
     * 組織コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentCd", arg0 = @Arg(key = "CRM.CC.departmentCd"))
    public String departmentCd;

    /**
     * 組織名
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentName", arg0 = @Arg(key = "CRM.CC.departmentName"))
    public String departmentName;

    /**
     * 組織略称
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentShortName", arg0 = @Arg(key = "CRM.CC.departmentShortName"))
    public String departmentShortName;

    /**
     * 組織検索名
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentSearchName", arg0 = @Arg(key = "CRM.CC.departmentSearchName"))
    public String departmentSearchName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
