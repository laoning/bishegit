/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * CRMアカウント一覧検索結果モデルです。
 */
public final class SearchFilterResponseModel {

    /**
     * アカウント名
     */
    public String crmAccountName;

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
    public String address;

    /**
     * 電話番号
     */
    public String telephoneNumber;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * 主担当者名
     */
    public String userName;

    /**
     * 主担当組織
     */
    public String departmentName;

    /**
     * 商談開始
     */
    public Date businessStartDate;

    /**
     * 契約開始
     */
    public String contractStartDate;

    /**
     * アカウント名（カナ）
     */
    public String crmAccountNameKana;

    /**
     * 主担当者コード
     */
    public String userCd;

    /**
     * 主担当者組織コード
     */
    public String departmentCd;

    /**
     * 削除フラグ
     */
    public String deleteFlag;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
