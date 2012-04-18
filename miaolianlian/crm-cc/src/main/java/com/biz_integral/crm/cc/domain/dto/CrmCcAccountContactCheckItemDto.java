/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcAccountContact;

/**
 * アカウントコンタクト（チェック項目あり）モデルです。
 */
public final class CrmCcAccountContactCheckItemDto extends
        AbstractCrmCcAccountContact {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = -7338330429356372130L;

    /**
     * チェック期間（自）
     */
    public Date checkStartDate;

    /**
     * チェック期間（至）
     */
    public Date checkEndDate;

    /**
     * CRMアカウントバージョン番号
     */
    public String crmAccountVersionNo;

    /**
     * CRMコンタクトバージョン番号
     */
    public String crmContactVersionNo;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
