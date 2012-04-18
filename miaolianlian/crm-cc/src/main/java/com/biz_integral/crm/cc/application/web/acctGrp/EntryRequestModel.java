/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeUser;
import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.BeanCollection;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウントグループ登録/更新画面の登録条件モデルです。
 */
public final class EntryRequestModel {

    /**
     * アカウントグループコード
     */
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountGroupCd"))
    public String crmAccountGroupCd;

    /**
     * アカウントグループ名
     */
    public String crmAccountGroupName;

    /**
     * アカウントグループ略称
     */
    public String crmAccountGroupShortName;

    /**
     * アカウントグループ検索名
     */
    public String crmAccountGroupSearchName;

    /**
     * アカウント分類コード
     */
    public String crmAccountClassCd;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * ロケールID
     */
    @Required(arg0 = @Arg(key = "CRM.CC.localeId"))
    public String localeId;

    /**
     * 備考
     */
    public String notes;

    /**
     * 担当組織一覧
     */
    @BeanCollection
    public List<CrmCcAcctGrpChargeDept> grpCharDeptList = newArrayList();

    /**
     * 担当者一覧
     */
    @BeanCollection
    public List<CrmCcAcctGrpChargeUser> grpCharUserList = newArrayList();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
