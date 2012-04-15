/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * CRMアカウント担当チェックDto
 */
public final class CrmAccountCheckChargeDto {

    /**
     * Listの全組織コードをOR条件
     */
    public String[] deptCdList;

    /**
     * CRMアカウントコードをOR条件
     */
    public String[] crmAccountCdArray;

    /**
     * ユーザコード
     */
    public String userCd;

    /**
     * CRMアカウントコードリスト
     */
    public List<String> crmAccountCdList = new ArrayList<String>();

    /**
     * CRM領域コード
     */
    public String crmDomainCd;

    /**
     * 基準日
     */
    public Date baseDate;

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * データアクセス制御
     */
    public String dataAccessCustomerFlag;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
