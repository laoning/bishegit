/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * コンタクト登録/更新画面の削除セル(競合)押下条件Dtoです。
 */
public final class ContactDeleteCompetitionCriteriaDto {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * CRMコンタクトコード
     */
    public String crmContactCd;

    /**
     * CRMコンタクト期間コード
     */
    public String crmContactTermCd;

    /**
     * CRMコンタクトバージョン番号
     */
    public String crmContactVersionNo;

    /**
     * 競合ID
     */
    public String competitionId;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 最終更新者
     */
    public String recordUserCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
