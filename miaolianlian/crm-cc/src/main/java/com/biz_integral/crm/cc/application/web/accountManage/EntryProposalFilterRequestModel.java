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
 * アカウント登録/更新画面の案件一覧ページャー条件モデルです。
 */
public final class EntryProposalFilterRequestModel extends PagerSupport {

    /**
     * アカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountCd", arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    public String crmAccountCd;

    /**
     * 案件コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "proposalCd", arg0 = @Arg(key = "CRM.CC.proposalCd"))
    public String proposalCd;

    /**
     * 案件名
     */
    @DomainConstraint(namespace = "crm.cc", type = "proposalName", arg0 = @Arg(key = "CRM.CC.proposalName"))
    public String proposalName;

    /**
     * 担当者コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "userCd", arg0 = @Arg(key = "CRM.CC.chargeUserCd"))
    public String chargeUserCd;

    /**
     * 担当者名
     */
    @DomainConstraint(namespace = "crm.cc", type = "userName", arg0 = @Arg(key = "CRM.CC.chargeUserName"))
    public String chargeUserName;

    /**
     * 品目コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "itemCd", arg0 = @Arg(key = "CRM.CC.itemCategoryCd"))
    public String itemCategoryCd;

    /**
     * 品目名
     */
    @DomainConstraint(namespace = "crm.cc", type = "itemName", arg0 = @Arg(key = "CRM.CC.itemCategoryName"))
    public String itemCategoryName;

    /**
     * 確度
     */
    public String proposalLevel;

    /**
     * 案件ステージ
     */
    public String proposalStage;

    /**
     * 競合先
     */
    @DomainConstraint(namespace = "crm.cc", type = "competitionName", arg0 = @Arg(key = "CRM.CC.competitionName"))
    public String competitionName;

    /**
     * 予定年月日（自）
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.planDateFrom"))
    public String planDateFrom;

    /**
     * 予定年月日（至）
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.planDateTo"))
    public String planDateTo;

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
