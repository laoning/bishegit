/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面のコンタク一覧ページャー結果モデルです。
 */
public final class EntryContactFilterResponseModel {

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
    public String startDate;

    /**
     * 終了日
     */
    public String endDate;

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
