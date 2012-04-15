/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント一覧検索結果です。
 */
public final class AccountSelectDialogFilterResponseModel {

    /**
     * select
     */
    public String select;

    /**
     * アカウント名
     */
    public String crmAccountName;

    /**
     * 状況
     */
    public String crmAccountStatus;

    /**
     * 重要度
     */
    public String importantLevel;

    /**
     * 区分
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
     * アカウント名（カナ）
     */
    public String crmAccountNameKana;

    /**
     * 主担当者コード
     */
    public String userCd;

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
