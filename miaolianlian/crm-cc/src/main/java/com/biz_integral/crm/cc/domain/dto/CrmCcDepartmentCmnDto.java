/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 組織コード取得用Dtoです。
 */
public class CrmCcDepartmentCmnDto {

    /** 会社コード */
    public String companyCd;

    /** ロケールID */
    public String localeId;

    /** 会社組織セットコード */
    public String departmentSetCd;

    /** 組織コード */
    public String departmentCd;

    /** 期間コード */
    public String termCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
