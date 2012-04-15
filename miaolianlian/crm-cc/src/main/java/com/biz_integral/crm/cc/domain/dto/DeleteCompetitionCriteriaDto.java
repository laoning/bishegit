/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面の削除セル(競合)押下条件Dtoです。
 */
public final class DeleteCompetitionCriteriaDto {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * CRMアカウントコード
     */
    public String crmAccountCd;

    /**
     * CRMアカウント期間コード
     */
    public String crmAccountTermCd;

    /**
     * CRMアカウントバージョン番号
     */
    public String crmAccountVersionNo;

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
