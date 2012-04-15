/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 担当者の結果Dtoです。
 */
public class ChargeUserListChangeResultDto {

    /**
     * ユーザ名
     */
    public String userName;

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
     * 主担当フラグ
     */
    public String mainChargeFlag;

    /**
     * 有効期間開始日
     */
    public String userCmnStartDate;

    /**
     * 有効期間終了日
     */
    public String userCmnEndDate;

    /**
     * ユーザコード
     */
    public String userCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
