/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面の競合結果一覧モデルです。
 */
public final class EntryCompetitionFilterResponseModel {

    /**
     * 競合ID
     */
    public String competitionId;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * 競合先名
     */
    public String competitionName;

    /**
     * 競合先名（カナ）
     */
    public String competitionNameKana;

    /**
     * 概要
     */
    public String overview;

    /**
     * 獲得案件事例
     */
    public String acquirementProposalCase;

    /**
     * 競合度
     */
    public String competitionLevel;

    /**
     * 備考
     */
    public String notes;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
