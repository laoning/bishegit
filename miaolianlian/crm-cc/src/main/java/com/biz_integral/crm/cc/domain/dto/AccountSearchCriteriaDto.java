/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * CRMアカウント一覧検索条件モデルです。
 */
public final class AccountSearchCriteriaDto extends PagerSupport {

    /**
     * Listの全組織コードをOR条件
     */
    public String[] updepartmentCdListToArray;

    /**
     * 「データアクセス制御：顧客データ参照」を持ちフラグ
     */
    public String hasAceessCustomerFlag;

    /**
     * 「データアクセス制御：特権」を持ちフラグ
     */
    public String hasPrivilegeFlag;

    /**
     * ユーザコード
     */
    public String userCode;

    /**
     * CRM用組織セットコード
     */
    public String departmentSetCd;

    /**
     * CRM領域コード
     */
    public String crmDomainCd;

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localeID;

    /**
     * 簡易検索モード
     */
    public String easySearchMode;

    /**
     * アカウント名
     */
    public String crmAccountName;

    /**
     * アカウント略称
     */
    public String crmAccountShortName;

    /**
     * アカウント名（カナ）
     */
    public String crmAccountKanaName;

    /**
     * アカウント検索名
     */
    public String crmAccountSearchName;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * 取引先コード
     */
    public String customerCd;

    /**
     * アカウント状況
     */
    public String crmAccountStatus;

    /**
     * 重要度
     */
    public String importantLevel;

    /**
     * アカウント区分
     */
    public String crmAccountType;

    /**
     * 住所
     */
    public String address;

    /**
     * 電話番号
     */
    public String telephoneNumber;

    /**
     * 担当者コード
     */
    public String userCd;

    /**
     * 担当者名
     */
    public String userName;

    /**
     * 削除フラグ
     */
    public String deleteFlag;

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
     * CRMアカウント分類コードリスト
     */
    public List<CrmCcAccountClassAth> crmAccountCatCdList;

    /**
     * ListのCRMアカウント分類コードリストをOR条件
     */
    public String[] crmAccountCatCdListToArray;

    /**
     * ListのCRMアカウント分類コードリストをOR条件
     */
    public void crmAccountCatCdListToArray() {
        if (crmAccountCatCdList != null && !crmAccountCatCdList.isEmpty()) {
            crmAccountCatCdListToArray = new String[crmAccountCatCdList.size()];
            int size = 0;
            for (CrmCcAccountClassAth ath : crmAccountCatCdList) {
                crmAccountCatCdListToArray[size++] = ath.getCrmAccountClassCd();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
