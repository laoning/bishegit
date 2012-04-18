/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * アカウント登録/更新画面の活動一覧ページャー条件モデルです。
 */
public final class EntrySalesActivityFilterRequestModel extends PagerSupport {

    /**
     * アカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountCd", arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    public String crmAccountCd;

    /**
     * 担当者_担当者コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "userCd", arg0 = @Arg(key = "CRM.CC.chargeUserCd"))
    public String chargeUserCd;

    /**
     * 担当者_担当者名
     */
    @DomainConstraint(namespace = "crm.cc", type = "userName", arg0 = @Arg(key = "CRM.CC.chargeUserName"))
    public String chargeUserName;

    /**
     * 活動_活動日（自）
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.SA.salesActivityDateFrom"))
    public String salesActivityDateFrom;

    /**
     * 活動_活動日（至）
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.SA.salesActivityDateTo"))
    public String salesActivityDateTo;

    /**
     * 活動_共有活動のみ表示フラグ
     */
    public String salesActivityShareInfromFlag;

    /**
     * 活動_活動タイプ
     */
    public String salesActivityType;

    /**
     * 活動_カテゴリ
     */
    public String informCategory;

    /**
     * 活動_状況
     */
    public String salesActivityStatus;

    /**
     * 品目コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "itemCd", arg0 = @Arg(key = "CRM.CC.itemCategoryCd"))
    public String salesActivityItemCategoryCd;

    /**
     * 顧客_コンタクトコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactCd", arg0 = @Arg(key = "CRM.CC.crmContactCd"))
    public String crmContactCd;

    /**
     * 顧客_コンタクト名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactName", arg0 = @Arg(key = "CRM.CC.crmContactName"))
    public String crmContactName;

    /**
     * 顧客_重要度
     */
    public String importantLevel;

    /**
     * 案件_案件コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "proposalCd", arg0 = @Arg(key = "CRM.CC.proposalCd"))
    public String proposalCd;

    /**
     * 案件_案件名
     */
    @DomainConstraint(namespace = "crm.cc", type = "proposalName", arg0 = @Arg(key = "CRM.CC.proposalName"))
    public String proposalName;

    /**
     * 案件_確度
     */
    public String proposalLevel;

    /**
     * 案件_予定年月日（自）
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.planDateFrom"))
    public String planDateFrom;

    /**
     * 案件_予定年月日（至）
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.planDateTo"))
    public String planDateTo;

    /**
     * 案件_品目コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "itemCd", arg0 = @Arg(key = "CRM.CC.itemCategoryCd"))
    public String proposalItemCategoryCd;

    /**
     * ソート順
     */
    public String sort;

    /**
     * ソート項目
     */
    public String sortItem;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
