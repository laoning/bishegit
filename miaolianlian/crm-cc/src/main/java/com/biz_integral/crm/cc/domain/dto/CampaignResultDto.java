/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * アカウント登録/更新画面のキャンペーン一覧ページャー結果Dtoです。
 */
public final class CampaignResultDto extends PagerSupport {

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
    public Date periodDateFrom;

    /**
     * 期間（至）
     */
    public Date periodDateTo;

    /**
     * 実行タイプ
     */
    public String executionType;

    /**
     * 最新キャンペーン状況
     */
    public String latestCampaignStatus;

    /**
     * 最新状況
     */
    public String lateststatus;

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
