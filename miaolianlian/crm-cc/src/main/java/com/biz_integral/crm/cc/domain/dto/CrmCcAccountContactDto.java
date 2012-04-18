/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * CRMアカウントコンタクトモデルです。
 */
public final class CrmCcAccountContactDto {

    /**
     * アプリケーションコンテキスト.会社コード
     */
    public String companyCd;

    /**
     * CRMアカウントコード
     */
    public String crmAccountCd;

    /**
     * CRMコンタクトコード
     */
    public String crmContactCd;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * 開始日
     */
    public Date startDate;

    /**
     * 終了日
     */
    public Date endDate;

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
