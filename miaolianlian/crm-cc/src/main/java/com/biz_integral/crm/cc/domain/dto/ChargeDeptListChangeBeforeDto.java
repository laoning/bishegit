/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 担当組織の条件モデルです。
 */
public class ChargeDeptListChangeBeforeDto {

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * 組織名
     */
    public String departmentName;

    /**
     * 組織コード
     */
    public String departmentCd;

    /**
     * 主担当フラグ
     */
    public String mainChargeFlag;

    /**
     * 期間（自）
     */
    public String startDate;

    /**
     * 期間（至）
     */
    public String endDate;

    /**
     * 有効期間開始日
     */
    public String effectiveTermStart;

    /**
     * 有効期間終了日
     */
    public String effectiveTermEnd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
