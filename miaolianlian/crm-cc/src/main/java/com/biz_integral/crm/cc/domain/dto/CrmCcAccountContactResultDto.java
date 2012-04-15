/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面のコンタクト結果Dtoです。
 */
public final class CrmCcAccountContactResultDto {

    /**
     * アカウント名
     */
    public String crmAccountName;

    /**
     * 状況
     */
    public String status;

    /**
     * 重要度
     */
    public String importantLevel;

    /**
     * 区分
     */
    public String type;

    /**
     * 住所
     */
    public String address1;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * CRMコンタクト名
     */
    public String crmContactName;

    /**
     * 所属
     */
    public String belong;

    /**
     * 性別
     */
    public String sex;

    /**
     * 役職
     */
    public String post;

    /**
     * キーパーソン
     */
    public String keyPerson;

    /**
     * CRMコンタクトコード
     */
    public String crmContactCd;

    /**
     * 開始日
     */
    public Date startDate;

    /**
     * 終了日
     */
    public Date endDate;

    /**
     * 主担当者名
     */
    public String userName;

    /**
     * 主担当者コード
     */
    public String userCd;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 削除フラグ
     */
    public String deleteFlag;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
