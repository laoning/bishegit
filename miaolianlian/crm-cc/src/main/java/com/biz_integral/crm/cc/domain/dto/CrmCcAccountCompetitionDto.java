/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * CRMアカウント競合のDtoです。
 */
public final class CrmCcAccountCompetitionDto extends PagerSupport {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * CRMアカウントコード
     */
    public String crmAccountCd;

    /**
     * CRMアカウントコードバージョン番号
     */
    public String crmAccountVersionNo;

    /**
     * 削除フラグ
     */
    public String deleteFlag;

    /**
     * 競合ID
     */
    public String competitionId;

    /**
     * ユーザコード
     */
    public String userCd;

    /**
     * 競合先名
     */
    public String competitionName;

    /**
     * 競合度
     */
    public String competitionLevel;

    /**
     * 獲得案件事例
     */
    public String acquirementProposalCase;

    /**
     * 概要
     */
    public String overview;

    /**
     * 備考
     */
    public String notes;

    /**
     * 最終更新者
     */
    public String recordUserCd;

    /**
     * 最終更新日
     */
    public Date recordDate;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 開始日
     */
    public Date startDate;

    /**
     * 終了日
     */
    public Date endDate;

    /**
     * 作成者
     */
    public String createUserCd;

    /**
     * 作成日
     */
    public Date createDate;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * ソートキー
     */
    public String sortKey;

    /**
     * ソート順
     */
    public String sort;

    /**
     * ソート項目
     */
    public String sortItem;

    /**
     * システム日付
     */
    public Date systemDate = DateUtil.today();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
