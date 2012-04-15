/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * CRMアカウント一覧検索条件です。
 */
public final class SearchFilterRequestModel extends PagerSupport {

    /**
     * アカウント名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountName", arg0 = @Arg(key = "CRM.CC.crmAccountName"))
    public String crmAccountName;

    /**
     * アカウント略称
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountShortName", arg0 = @Arg(key = "CRM.CC.crmAccountShortName"))
    public String crmAccountShortName;

    /**
     * アカウント名（カナ）
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountNameKana", arg0 = @Arg(key = "CRM.CC.crmAccountNameKana"))
    public String crmAccountKanaName;

    /**
     * アカウント検索名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountSearchName", arg0 = @Arg(key = "CRM.CC.crmAccountSearchName"))
    public String crmAccountSearchName;

    /**
     * アカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountCd", arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    public String crmAccountCd;

    /**
     * 取引先コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "customerCd", arg0 = @Arg(key = "CRM.CC.customerCd"))
    public String customerCd;

    /**
     * アカウント状況
     */
    public String crmAccountStatus;

    /**
     * 重要度
     */
    public String importantLevel;

    /**
     * アカウント区分
     */
    public String crmAccountType;

    /**
     * 住所
     */
    @DomainConstraint(namespace = "crm.cc", type = "address", arg0 = @Arg(key = "CRM.CC.address"))
    public String address;

    /**
     * 電話番号
     */
    @DomainConstraint(namespace = "crm.cc", type = "telephoneNumber", arg0 = @Arg(key = "CRM.CC.telephoneNumber"))
    public String telephoneNumber;

    /**
     * 担当者コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "userCd", arg0 = @Arg(key = "CRM.CC.userCd"))
    public String userCd;

    /**
     * 担当者名
     */
    public String userName;

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
