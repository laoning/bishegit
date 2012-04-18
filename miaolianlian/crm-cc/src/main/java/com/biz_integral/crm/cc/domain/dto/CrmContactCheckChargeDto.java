/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * CRMコンタクト担当チェックDto
 */
public final class CrmContactCheckChargeDto {

    /**
     * Listの全組織コードをOR条件
     */
    public String[] deptCdList;

    /**
     * CRMコンタクトコードをOR条件
     */
    public String[] crmContactCdArray;

    /**
     * ユーザコード
     */
    public String userCd;

    /**
     * CRMコンタクトコードリスト
     */
    public List<String> crmContactCdList = new ArrayList<String>();

    /**
     * 基準日
     */
    public Date baseDate;

    /**
     * データアクセス制御
     */
    public String dataAccessCustomerFlag;

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
