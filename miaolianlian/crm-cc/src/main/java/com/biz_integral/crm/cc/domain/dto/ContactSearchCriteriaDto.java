/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * CRMコンタクト一覧検索条件モデルです。
 */
public final class ContactSearchCriteriaDto extends PagerSupport {

    /**
     * Listの全組織コードをOR条件
     */
    public String[] updepartmentCdListToArray;

    /**
     * 「データアクセス制御：顧客データ参照」を持ちフラグ
     */
    public String hasAceessCustomerFlag;

    /**
     * 「データアクセス制御：特権」を持ちフラグ
     */
    public String hasPrivilegeFlag;

    /**
     * ユーザコード
     */
    public String userCode;

    /**
     * CRM用組織セットコード
     */
    public String departmentSetCd;

    /**
     * パラメータ.会社コード
     */
    public String companyCdParam;

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localeID;

    /**
     * コンタクト名
     */
    public String crmContactName;

    /**
     * コンタクト略称
     */
    public String crmContactShortName;

    /**
     * コンタクト名（カナ）
     */
    public String crmContactNameKana;

    /**
     * コンタクト検索名
     */
    public String crmContactSearchName;

    /**
     * コンタクトコード
     */
    public String crmContactCd;

    /**
     * タイブ
     */
    public String crmContactType;

    /**
     * 所属
     */
    public String belong;

    /**
     * 役職
     */
    public String post;

    /**
     * 電話番号
     */
    public String telephoneNumber;

    /**
     * キーパーソン
     */
    public String keyPerson;

    /**
     * メールアドレス
     */
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
     * システム日付
     */
    public Date systemDate = DateUtil.today();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
