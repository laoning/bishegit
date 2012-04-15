/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * コンタクト登録/更新画面のアカウント一覧ページャー結果モデルです。
 */
public final class EntryAccountFilterResponseModel {

    /**
     * アカウント名
     */
    public String crmAccountName;

    /**
     * 状況
     */
    public String status;

    /**
     * 重要度
     */
    public String importantLevel;

    /**
     * 区分
     */
    public String type;

    /**
     * 住所
     */
    public String address1;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * 開始日
     */
    public String startDate;

    /**
     * 終了日
     */
    public String endDate;

    /**
     * 主担当者名
     */
    public String userName;

    /**
     * 主担当者コード
     */
    public String userCd;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * バージョン番号
     */
    public String versionNo;

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
