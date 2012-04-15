/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 担当組織一覧の結果モデルです。
 */
public final class EntryAccountChargeDeptResponseModel {

    /**
     * 組織名
     */
    public String departmentName;

    /**
     * 主担当
     */
    public String mainCharge;

    /**
     * 期間（自）
     */
    public String startDate;

    /**
     * 期間（至）
     */
    public String endDate;

    /**
     * 組織コード
     */
    public String departmentCd;

    /**
     * 主担当フラグ
     */
    public String mainChargeFlag;

    /**
     * 有効期間開始日
     */
    public String deptCmnStartDate;

    /**
     * 有効期間終了日
     */
    public String deptCmnEndDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
