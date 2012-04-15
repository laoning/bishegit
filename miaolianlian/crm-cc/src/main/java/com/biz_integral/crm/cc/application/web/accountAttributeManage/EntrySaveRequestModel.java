/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.BeanCollection;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウント属性登録／更新画面の登録引数モデルです。
 */
public final class EntrySaveRequestModel {

    /**
     * アカウント分類コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountClassCd", arg0 = @Arg(key = "CRM.CC.crmAccountClassCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountClassCd"))
    public String crmAccountClassCd;

    /**
     * 取引種別
     */
    @Required(arg0 = @Arg(key = "CRM.CC.dealClass"))
    public String dealClass;

    /**
     * 状況
     */
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountStatus"))
    public String crmAccountStatus;

    /**
     * 画面メンテ対象
     */
    public String maintenanceTargetFlag;

    /**
     * IF更新方法
     */
    public String ifUpdateWay;

    /**
     * モジュール一覧
     */
    @BeanCollection
    public List<EntryModuleRequestModel> moduleList = newArrayList();

}
