/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;

/**
 * 登録submitモデルです。
 */
public final class CompeteEntryDialogRegistrationRequestModel {

    /**
     * 処理対象
     */
    public String processTarget;

    /**
     * CRMアカウントコード
     */
    public String crmAccountCd;

    /**
     * CRMアカウントバージョン番号
     */
    public String crmAccountVersionNo;

    /**
     * CRMコンタクトコード
     */
    public String crmContactCd;

    /**
     * CRMコンタクトバージョン番号
     */
    public String crmContactVersionNo;

    /**
     * 競合先名
     */
    @DomainConstraint(namespace = "crm.cc", type = "competitionName", arg0 = @Arg(key = "CRM.CC.competitionName"))
    public String competitionName;

    /**
     * 競合度
     */
    public String competitionLevel;

    /**
     * 獲得案件事例
     */
    @DomainConstraint(namespace = "crm.cc", type = "acquirementProposalCase", arg0 = @Arg(key = "CRM.CC.acquirementProposalCase"))
    public String acquirementProposalCase;

    /**
     * 概要
     */
    @DomainConstraint(namespace = "crm.cc", type = "overview", arg0 = @Arg(key = "CRM.CC.overview"))
    public String overview;

    /**
     * 備考
     */
    @DomainConstraint(namespace = "crm.cc", type = "notes", arg0 = @Arg(key = "CRM.CC.notes"))
    public String notes;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
