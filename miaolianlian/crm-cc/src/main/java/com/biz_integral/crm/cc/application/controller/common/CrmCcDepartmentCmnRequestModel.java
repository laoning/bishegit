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
 * 組織コード検索条件モデルです。
 */
public class CrmCcDepartmentCmnRequestModel extends PagerSupport {

    /** 組織コード */
    @DomainConstraint(namespace = "crm.cc", type = "departmentCd", arg0 = @Arg(key = "CRM.CC.departmentCd"))
    public String departmentCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
