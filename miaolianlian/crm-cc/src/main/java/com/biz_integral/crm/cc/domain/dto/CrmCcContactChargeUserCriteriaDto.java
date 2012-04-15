/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;

/**
 * CRMコンタクト担当者検索のDtoです。
 */
public final class CrmCcContactChargeUserCriteriaDto {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * CRMコンタクトコード
     */
    public String crmContactCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * ユーザコード
     */
    public String userCd;

    /**
     * 主担当フラグ
     */
    public String mainChargeFlag;

    /**
     * 期間（自）
     */
    public Date startDate;

    /**
     * 期間（至）
     */
    public Date endDate;

    /**
     * システム日付
     */
    public Date systemDate = DateUtil.today();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
