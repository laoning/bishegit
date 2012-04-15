/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 初期表示submitモデルです。
 */
public final class CompeteEntryDialogInitializeRequestModel {

    /**
     * 処理モード
     */
    public String processMode;

    /**
     * 処理対象
     */
    public String processTarget;

    /**
     *CRMアカウントコード
     */
    public String crmAccountCd;

    /**
     * CRMコンタクトコード
     */
    public String crmContactCd;

    /**
     * 競合ID
     */
    public String competitionId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
