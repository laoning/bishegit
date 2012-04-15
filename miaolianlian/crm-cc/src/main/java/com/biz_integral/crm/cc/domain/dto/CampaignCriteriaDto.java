/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * アカウント登録/更新画面のキャンペーン一覧ページャー条件Dtoです。
 */
public final class CampaignCriteriaDto extends PagerSupport {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * キャンペーンコード
     */
    public String campaignCd;

    /**
     * キャンペーン名
     */
    public String campaignName;

    /**
     * 実行タイプ
     */
    public String executionType;

    /**
     * 状況
     */
    public String campaignStatus;

    /**
     * 期間（自）
     */
    public Date periodDateFrom;

    /**
     * 期間（至）
     */
    public Date periodDateTo;

    /**
     * アカウント固定フラグ
     */
    public String accountFixedFlag;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * 前方一致アカウントコード
     */
    public String likeCrmAccountCd;

    /**
     * アカウント名
     */
    public String crmAccountName;

    /**
     * コンタクトコード
     */
    public String crmContactCd;

    /**
     * 前方一致コンタクトコード
     */
    public String likeCrmContactCd;

    /**
     * コンタクト名
     */
    public String crmContactName;

    /**
     * パラメータ.コンタクト利用フラグ
     */
    public String contactFlag;

    /**
     * ソート順
     */
    public String sort;

    /**
     * ソート項目
     */
    public String sortItem;

    /**
     * アカウントコードが必要になります
     */
    public boolean isAccountCdCertainly;

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
