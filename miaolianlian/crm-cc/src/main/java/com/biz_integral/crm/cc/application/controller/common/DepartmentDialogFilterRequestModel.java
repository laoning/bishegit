/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * 組織ダイアログ一覧検索条件モデルです。
 * 
 */
public final class DepartmentDialogFilterRequestModel extends PagerSupport {

    /**
     * hdn_検索基準日
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.searchBaseDate"))
    public String searchBaseDate;

    /**
     * hdn_組織コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentCd", arg0 = @Arg(key = "CRM.CC.departmentCd"))
    public String departmentCd;

    /**
     * hdn_組織名
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentName", arg0 = @Arg(key = "CRM.CC.departmentName"))
    public String departmentName;

    /**
     * hdn_組織略称
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentShortName", arg0 = @Arg(key = "CRM.CC.departmentShortName"))
    public String departmentShortName;

    /**
     * hdn_組織検索名
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentSearchName", arg0 = @Arg(key = "CRM.CC.departmentSearchName"))
    public String departmentSearchName;

    /**
     * ソート順
     */
    public String sort;

    /**
     * ソート項目
     */
    public String sortItem;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
