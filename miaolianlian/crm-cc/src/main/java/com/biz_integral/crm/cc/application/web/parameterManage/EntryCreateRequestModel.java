/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * パラメータ登録の条件モデルです。
 */
public class EntryCreateRequestModel {

    /**
     * パラメータ値
     */
    @DomainConstraint(namespace = "crm.cc", type = "parameterValue", arg0 = @Arg(key = "CRM.CC.parameterValue"))
    @Required(arg0 = @Arg(key = "CRM.CC.parameterValue"))
    public String parameterValue;

    /**
     * パラメータコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "parameterCd", arg0 = @Arg(key = "CRM.CC.parameterCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.parameterCd"))
    public String parameterCd;

    /**
     * 期間コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "termCd", arg0 = @Arg(key = "CRM.CC.termCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.termCd"))
    public String termCd;

    /**
     * バージョン番号
     */
    @DomainConstraint(namespace = "crm.cc", type = "versionNo", arg0 = @Arg(key = "CRM.CC.versionNo"))
    @Required(arg0 = @Arg(key = "CRM.CC.versionNo"))
    public String versionNo;
}