/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * CRMコンタクト一覧検索条件です。
 */
public final class SearchFilterRequestModel extends PagerSupport {

    /**
     * コンタクト名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactName", arg0 = @Arg(key = "CRM.CC.crmContactName"))
    public String crmContactName;

    /**
     * コンタクト略称
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactShortName", arg0 = @Arg(key = "CRM.CC.crmContactShortName"))
    public String crmContactShortName;

    /**
     * コンタクト名（カナ）
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactNameKana", arg0 = @Arg(key = "CRM.CC.crmContactNameKana"))
    public String crmContactNameKana;

    /**
     * コンタクト検索名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactSearchName", arg0 = @Arg(key = "CRM.CC.crmContactSearchName"))
    public String crmContactSearchName;

    /**
     * コンタクトコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactCd", arg0 = @Arg(key = "CRM.CC.crmContactCd"))
    public String crmContactCd;

    /**
     * タイブ
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactType", arg0 = @Arg(key = "CRM.CC.crmContactType"))
    public String crmContactType;

    /**
     * 所属
     */
    @DomainConstraint(namespace = "crm.cc", type = "belong", arg0 = @Arg(key = "CRM.CC.belong"))
    public String belong;

    /**
     * 役職
     */
    @DomainConstraint(namespace = "crm.cc", type = "post", arg0 = @Arg(key = "CRM.CC.post"))
    public String post;

    /**
     * 電話番号
     */
    @DomainConstraint(namespace = "crm.cc", type = "telephoneNumber", arg0 = @Arg(key = "CRM.CC.telephoneNumber"))
    public String telephoneNumber;

    /**
     * キーパーソン
     */
    @DomainConstraint(namespace = "crm.cc", type = "keyPerson", arg0 = @Arg(key = "CRM.CC.keyPerson"))
    public String keyPerson;

    /**
     * メールアドレス
     */
    @DomainConstraint(namespace = "crm.cc", type = "emailAddress", arg0 = @Arg(key = "CRM.CC.emailAddress"))
    public String emailAddress;

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
