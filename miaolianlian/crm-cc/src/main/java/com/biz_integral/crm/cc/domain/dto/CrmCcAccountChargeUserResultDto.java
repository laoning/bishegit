/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 担当者の結果Dtoです。
 */
public class CrmCcAccountChargeUserResultDto {

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
    public Date startDate;

    /**
     * 期間（至）
     */
    public Date endDate;

    /**
     * 主担当フラグ
     */
    public String mainChargeFlag;

    /**
     * 有効期間開始日
     */
    public Date userCmnStartDate;

    /**
     * 有効期間終了日
     */
    public Date userCmnEndDate;

    /**
     * ユーザコード
     */
    public String userCd;

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
