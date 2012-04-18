/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;

/**
 * CRMアカウント担当者組織検索のDtoです。
 */
public final class CrmCcAccountChargeDeptCriteriaDto {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * CRMアカウントコード
     */
    public String crmAccountCd;

    /**
     * CRM領域コード
     */
    public String crmDomainCd;

    /**
     * ロケールID
     */
    public String localeId;

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
    public Date startDate;

    /**
     * 期間（至）
     */
    public Date endDate;

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
     * 会社組織セットコード
     */
    public String departmentSetCd;

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
