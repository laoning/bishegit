/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 活動アカウント検索結果モデルです。<br>
 */
public class SaAccountSearchResultDto {

    /**
     * CRMアカウントコード
     */
    public String crmAccountCd;

    /**
     * CRMアカウント名
     */
    public String crmAccountName;

    /**
     * CRMアカウント名（カナ）
     */
    public String crmAccountNameKana;

    /**
     * 状況
     */
    public String crmAccountStatus;

    /**
     * 区分
     */
    public String crmAccountType;

    /**
     * 郵便番号
     */
    public String zipCode;

    /**
     * 住所１
     */
    public String address1;

    /**
     * 住所２
     */
    public String address2;

    /**
     * 住所３
     */
    public String address3;

    /**
     * 主担当者
     */
    public String mainChargeUserName;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
