/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウント登録/更新画面の取引先選択ボタンの条件モデルです。
 */
public final class EntryCustomerSelectRequestModel {

    /**
     * 取引先コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "customerCd", arg0 = @Arg(key = "CRM.CC.customerCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.customerCd"))
    public String customerCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
