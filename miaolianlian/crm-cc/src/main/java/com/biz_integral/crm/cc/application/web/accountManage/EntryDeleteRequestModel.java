/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウント登録/更新画面の削除条件モデルです。
 */
public final class EntryDeleteRequestModel {

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * アカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountCd", arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    public String crmAccountCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
