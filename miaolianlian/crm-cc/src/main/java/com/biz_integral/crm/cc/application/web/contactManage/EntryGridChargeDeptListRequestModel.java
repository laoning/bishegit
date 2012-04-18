/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * 担当組織の条件モデルです。
 */
public final class EntryGridChargeDeptListRequestModel {

    /**
     * コンタクトコード
     */
    public String crmContactCd;

    /**
     * 組織名
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentName", arg0 = @Arg(key = "CRM.CC.departmentName"))
    public String departmentName;

    /**
     * 組織コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentCd", arg0 = @Arg(key = "CRM.CC.departmentCd"))
    public String departmentCd;

    /**
     * 主担当フラグ
     */
    public String mainChargeFlag;

    /**
     * 期間（自）
     */
    @Required(arg0 = @Arg(key = "CRM.CC.periodFrom"))
    @DomainConstraint(namespace = "crm.cc", type = "entyStandardDate", arg0 = @Arg(key = "CRM.CC.periodFrom"))
    public String startDate;

    /**
     * 期間（至）
     */
    @Required(arg0 = @Arg(key = "CRM.CC.periodTo"))
    @DomainConstraint(namespace = "crm.cc", type = "entyStandardDate", arg0 = @Arg(key = "CRM.CC.periodTo"))
    public String endDate;

    /**
     * 有効期間開始日
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.effectiveTermStart"))
    public String deptEffectiveTermStart;

    /**
     * 有効期間終了日
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.effectiveTermEnd"))
    public String deptEffectiveTermEnd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
