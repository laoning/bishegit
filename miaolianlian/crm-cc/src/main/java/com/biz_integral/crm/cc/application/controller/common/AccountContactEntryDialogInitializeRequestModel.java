/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウントコンタクト登録/更新の初期表示submitモデルです。
 */
public final class AccountContactEntryDialogInitializeRequestModel {

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
     * 期間（自）
     */
    public String startDate;

    /**
     * 期間（至）
     */
    public String endDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
