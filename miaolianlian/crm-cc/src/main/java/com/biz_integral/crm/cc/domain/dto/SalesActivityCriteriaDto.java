/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * 営業活動一覧検索条件Dtoです。<br/>
 */
public final class SalesActivityCriteriaDto extends PagerSupport {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localId;

    /**
     * 顧客_アカウントコード
     */
    public String crmAccountCd;

    /**
     * 前方一致顧客_アカウントコード
     */
    public String likeCrmAccountCd;

    /**
     * 顧客_アカウント名
     */
    public String crmAccountName;

    /**
     * 担当者_担当者コード
     */
    public String chargeUserCd;

    /**
     * 担当者_担当者名
     */
    public String chargeUserName;

    /**
     * 活動_活動日（自）
     */
    public Date salesActivityDateFrom;

    /**
     * 活動_活動日（至）
     */
    public Date salesActivityDateTo;

    /**
     * 活動_共有活動のみ表示フラグ
     */
    public String salesActivityShareInfromFlag;

    /**
     * 活動_活動タイプ
     */
    public String salesActivityType;

    /**
     * 活動_カテゴリ
     */
    public String informCategory;

    /**
     * 活動_状況
     */
    public String salesActivityStatus;

    /**
     * 品目コード
     */
    public String salesActivityItemCategoryCd;

    /**
     * 顧客_コンタクトコード
     */
    public String crmContactCd;

    /**
     * 前方一致顧客_コンタクトコード
     */
    public String likeCrmContactCd;

    /**
     * 顧客_コンタクト名
     */
    public String crmContactName;

    /**
     * 顧客_重要度
     */
    public String importantLevel;

    /**
     * 案件_案件コード
     */
    public String proposalCd;

    /**
     * 案件_案件名
     */
    public String proposalName;

    /**
     * 案件_確度
     */
    public String proposalLevel;

    /**
     * 案件_予定年月日（自）
     */
    public Date planDateFrom;

    /**
     * 案件_予定年月日（至）
     */
    public Date planDateTo;

    /**
     * 案件_品目コード
     */
    public String proposalItemCategoryCd;

    /**
     * パラメータ.コンタクト利用フラグ
     */
    public String contactFlag;

    /**
     * アカウント固定フラグ
     */
    public String accountFixedFlag;

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
