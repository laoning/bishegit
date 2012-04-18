/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * コンタクト登録/更新画面のキャンペーン一覧ページャー結果モデルです。
 */
public final class EntryCampaignFilterResponseModel {

    /**
     * キャンペーン名
     */
    public String campaignName;

    /**
     * CRMアカウント名
     */
    public String crmAccountName;

    /**
     * CRMコンタクト名
     */
    public String crmContactName;

    /**
     * 期間（自）
     */
    public String periodDateFrom;

    /**
     * 期間（至）
     */
    public String periodDateTo;

    /**
     * 実行タイプ
     */
    public String executionType;

    /**
     * 最新状況
     */
    public String latestCampaignStatus;

    /**
     * キャンペーン内容
     */
    public String campaignContent;

    /**
     * キャンペーンコード
     */
    public String campaignCd;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * コンタクトコード
     */
    public String crmContactCd;

    /**
     * キャンペーンID
     */
    public String campaignId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
