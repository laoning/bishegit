/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;

/**
 * CRMアカウント担当者検索のDtoです。
 */
public class CrmCcAccountChargeUserCriteriaDto {

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
     * ユーザコード
     */
    public String userCd;

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
