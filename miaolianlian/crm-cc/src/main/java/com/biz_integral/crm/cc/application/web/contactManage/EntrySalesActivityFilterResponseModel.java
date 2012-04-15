/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * コンタクト登録/更新画面の活動一覧結果モデルです。
 */
public final class EntrySalesActivityFilterResponseModel {

    /**
     * 活動日
     */
    public String salesActivityDate;

    /**
     * コンタクト名
     */
    public String crmContactName;

    /**
     * アカウント名
     */
    public String crmAccountName;

    /**
     * 担当者名
     */
    public String userName;

    /**
     * 活動状況
     */
    public String salesActivityStatus;

    /**
     * 案件名
     */
    public String proposalName;

    /**
     * カテゴリ
     */
    public String informCategory;

    /**
     * 報告内容
     */
    public String informContent;

    /**
     * 活動タイプ
     */
    public String salesActivityType;

    /**
     * 開始時間
     */
    public String salesActivityStartDate;

    /**
     * 時間（分）
     */
    public String salesActivityTime;

    /**
     * コンタクトコード
     */
    public String crmContactCd;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * 担当者コード
     */
    public String userCd;

    /**
     * 案件コード
     */
    public String proposalCd;

    /**
     * 営業活動ID
     */
    public String salesActivityId;

    /**
     * 営業活動報告ID
     */
    public String salesActivityInformId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
