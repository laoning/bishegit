/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * アカウント登録/更新画面の詳細セル(案件)押下条件モデルです。
 */
public final class EntryProposalEditRequestModel {

    /**
     * 案件コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "proposalCd", arg0 = @Arg(key = "CRM.CC.proposalCd"))
    public String proposalCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
