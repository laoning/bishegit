/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * コンタクト登録/更新画面の削除セル(アカウント)押下条件モデルです。
 */
public final class EntryAccountDeleteRequestModel {

    /**
     * CRM領域コード
     */
    public String crmDomainCd;

    /**
     * コンタクトコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactCd", arg0 = @Arg(key = "CRM.CC.crmContactCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.crmContactCd"))
    public String crmContactCd;

    /**
     * CRMアカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountCd", arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    public String crmAccountCd;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
