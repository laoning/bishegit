/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * アカウント登録/更新画面の担当者設定ボタンの確定担当者リストモデルです。
 */
public final class EntryFixChargeUserListRequestModel {

    /**
     * ユーザ名
     */
    @DomainConstraint(namespace = "crm.cc", type = "userName", arg0 = @Arg(key = "CRM.CC.userName"))
    public String userName;

    /**
     * ユーザコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "userCd", arg0 = @Arg(key = "CRM.CC.userCd"))
    public String userCd;

    /**
     * 有効期間開始日
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.effectiveTermStart"))
    public String effectiveTermStart;

    /**
     * 有効期間終了日
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.effectiveTermEnd"))
    public String effectiveTermEnd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
