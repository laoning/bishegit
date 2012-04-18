/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * CRMアカウントコンタクトのDtoです。
 */
public final class CrmCcAccountContactCriteriaDto extends PagerSupport {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * アカウントコード
     */
    public String crmAccountCd;

    /**
     * コンタクトコード
     */
    public String crmContactCd;

    /**
     * CRM領域コード
     */
    public String crmDomainCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * ソート順
     */
    public String sort;

    /**
     * ソート項目
     */
    public String sortItem;

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
