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
 * 品目コード検索条件モデルです。
 */
public class CrmCcItemCatCmnRequestModel extends PagerSupport {

    /** 品目コード */
    @DomainConstraint(namespace = "crm.cc", type = "itemCategoryCd", arg0 = @Arg(key = "CRM.CC.itemCategoryCd"))
    public String itemCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
