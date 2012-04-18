/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * CRMコンタクト競合のDtoです。
 */
public final class CrmCcContactCompetitionCriteriaDto extends PagerSupport {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * コンタクトコード
     */
    public String crmContactCd;

    /**
     * 削除フラグ
     */
    public String deleteFlag;

    /**
     * ソート順
     */
    public String sort;

    /**
     * ソート項目
     */
    public String sortItem;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
