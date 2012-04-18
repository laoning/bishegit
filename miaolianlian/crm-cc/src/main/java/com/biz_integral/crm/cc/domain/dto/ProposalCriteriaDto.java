/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * アカウント登録/更新画面の案件一覧ページャー条件Dtoです。
 */
public final class ProposalCriteriaDto extends PagerSupport {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * 案件コード
     */
    public String proposalCd;

    /**
     * 案件名
     */
    public String proposalName;

    /**
     * 担当者コード
     */
    public String chargeUserCd;

    /**
     * 担当者名
     */
    public String chargeUserName;

    /**
     * 品目コード
     */
    public String itemCategoryCd;

    /**
     * 品目名
     */
    public String itemCategoryName;

    /**
     * 確度
     */
    public String proposalLevel;

    /**
     * 案件ステージ
     */
    public String proposalStage;

    /**
     * 競合先
     */
    public String competitionName;

    /**
     * 予定年月日（自）
     */
    public Date planDateFrom;

    /**
     * 予定年月日（至）
     */
    public Date planDateTo;

    /**
     * 品目カテゴリセットコード
     */
    public String itemCategorySetCd;

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
