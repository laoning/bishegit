/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * 取引先選択ダイアログ一覧検索条件モデルです。
 * 
 */
public final class CustomerSelectDialogFilterRequestModel extends PagerSupport {

    /**
     * 検索基準日
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.searchBaseDate"))
    public String searchBaseDate;

    /**
     * 取引先コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "customerCd", arg0 = @Arg(key = "CRM.CC.customerCd"))
    public String customerCd;

    /**
     * 取引先名
     */
    @DomainConstraint(namespace = "crm.cc", type = "customerName", arg0 = @Arg(key = "CRM.CC.customerName"))
    public String customerName;

    /**
     * 取引先略称
     */
    @DomainConstraint(namespace = "crm.cc", type = "customerShortName", arg0 = @Arg(key = "CRM.CC.customerShortName"))
    public String customerShortName;

    /**
     * 取引先検索名
     */
    @DomainConstraint(namespace = "crm.cc", type = "customerSearchName", arg0 = @Arg(key = "CRM.CC.customerSearchName"))
    public String customerSearchName;

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
