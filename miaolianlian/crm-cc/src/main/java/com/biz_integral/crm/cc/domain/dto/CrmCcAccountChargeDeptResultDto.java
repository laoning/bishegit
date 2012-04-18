/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 担当組織一覧の結果Dtoです。
 */
public final class CrmCcAccountChargeDeptResultDto {

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
     * 期間コード
     */
    public String termCd;

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
    public Date deptCmnStartDate;

    /**
     * 有効期間終了日
     */
    public Date deptCmnEndDate;

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
