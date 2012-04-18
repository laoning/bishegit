/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * CRMコンタクト一覧検索結果モデルです。
 */
public final class SearchFilterResponseModel {

    /**
     * アカウント名
     */
    public String crmContactName;

    /**
     * 所属
     */
    public String belong;

    /**
     * 性別
     */
    public String sex;

    /**
     * 役職
     */
    public String post;

    /**
     * キーパーソン
     */
    public String keyPerson;

    /**
     * コンタクトコード
     */
    public String crmContactCd;

    /**
     * メールアドレス1
     */
    public String emailAddress1;

    /**
     * 電話番号
     */
    public String telephoneNumber;

    /**
     * 主担当者名
     */
    public String userName;

    /**
     * コンタクト名（カナ）
     */
    public String crmContactNameKana;

    /**
     * 主担当者コード
     */
    public String userCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
