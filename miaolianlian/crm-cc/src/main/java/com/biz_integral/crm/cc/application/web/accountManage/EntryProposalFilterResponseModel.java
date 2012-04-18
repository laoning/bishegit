/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面の取引先選択ボタンー結果モデルです。
 */
public final class EntryProposalFilterResponseModel {

    /**
     * 案件名
     */
    public String proposalName;

    /**
     * 主担当者名
     */
    public String userName;

    /**
     * 確度
     */
    public String proposalLevel;

    /**
     * 予定年月日
     */
    public String planDate;

    /**
     * 品目名
     */
    public String itemCategoryName;

    /**
     * ステージ
     */
    public String proposalStage;

    /**
     * 予定金額
     */
    public String planAmt;

    /**
     * 競合先
     */
    public String competitionName;

    /**
     * 案件概要
     */
    public String overview;

    /**
     * 案件コード
     */
    public String proposalCd;

    /**
     * 主担当者コード
     */
    public String userCd;

    /**
     * 品目コード
     */
    public String itemCategoryCd;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
