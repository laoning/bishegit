/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * 組織ダイアログの検索条件です。
 */
public final class DepartmentDialogCriteriaDto extends PagerSupport {

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * 検索基準日
     */
    public Date searchBaseDate;

    /**
     * 会社組織セットコード
     */
    public String departmentSetCd;

    /**
     * 組織コード
     */
    public String departmentCd;

    /**
     * 組織名
     */
    public String departmentName;

    /**
     * 組織略称
     */
    public String departmentShortName;

    /**
     * 組織検索名
     */
    public String departmentSearchName;

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
