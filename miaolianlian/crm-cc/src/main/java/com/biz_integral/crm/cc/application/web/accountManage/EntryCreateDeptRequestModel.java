/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * 担当組織の条件モデルです。
 */
public class EntryCreateDeptRequestModel {

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * 組織名
     */
    public String departmentName;

    /**
     * 組織コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "departmentCd", arg0 = @Arg(key = "CRM.CC.departmentCd"))
    public String departmentCd;

    /**
     * 主担当フラグ
     */
    @DomainConstraint(namespace = "crm.cc", type = "flag", arg0 = @Arg(key = "CRM.CC.mainChargeFlag"))
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
    public String effectiveTermStart;

    /**
     * 有効期間終了日
     */
    public String effectiveTermEnd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
