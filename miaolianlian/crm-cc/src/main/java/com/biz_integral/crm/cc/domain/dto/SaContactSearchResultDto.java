/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 活動コンタクトコード検索結果モデルです。<br>
 */
public class SaContactSearchResultDto {

    /**
     * CRMコンタクトコード
     */
    public String crmContactCd;

    /**
     * CRMコンタクト名
     */
    public String crmContactName;

    /**
     * CRMコンタクト名（カナ）
     */
    public String crmContactNameKana;

    /**
     * 性別
     */
    public String sex;

    /**
     * 役職
     */
    public String post;

    /**
     * 所属
     */
    public String belong;

    /**
     * キーパーソン
     */
    public String keyPerson;

    /**
     * 出身地
     */
    public String hometown;

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
