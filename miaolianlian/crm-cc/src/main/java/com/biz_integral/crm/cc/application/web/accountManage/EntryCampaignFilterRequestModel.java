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
 * アカウント登録/更新画面のキャンペーン一覧ページャー条件モデルです。
 */
public final class EntryCampaignFilterRequestModel extends PagerSupport {

    /**
     * キャンペーンコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "campaignCd", arg0 = @Arg(key = "CRM.CC.campaignCd"))
    public String campaignCd;

    /**
     * キャンペーン名
     */
    @DomainConstraint(namespace = "crm.cc", type = "campaignName", arg0 = @Arg(key = "CRM.CC.campaignName"))
    public String campaignName;

    /**
     * 実行タイプ
     */
    public String executionType;

    /**
     * 状況
     */
    public String campaignStatus;

    /**
     * 期間（自）
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.periodFrom"))
    public String periodDateFrom;

    /**
     * 期間（至）
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.periodTo"))
    public String periodDateTo;

    /**
     * アカウント固定フラグ
     */
    public String accountFixedFlag;

    /**
     * アカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountCd", arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    public String crmAccountCd;

    /**
     * アカウント名
     */
    public String crmAccountName;

    /**
     * コンタクトコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactCd", arg0 = @Arg(key = "CRM.CC.crmContactCd"))
    public String crmContactCd;

    /**
     * コンタクト名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactName", arg0 = @Arg(key = "CRM.CC.crmContactName"))
    public String crmContactName;

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
