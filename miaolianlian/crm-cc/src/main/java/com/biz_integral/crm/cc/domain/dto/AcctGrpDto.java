/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * パラメータ管理モデルです。
 */
public class AcctGrpDto extends PagerSupport {

    /** 会社コード */
    public String companyCd;

    /** ロケールID */
    public String localeId;

    /** アカウントグループコード */
    public String crmAccountGroupCd;

    /** アカウントコード */
    public String crmAccountCd;

    /** アカウントコード */
    public String crmAccountName;

    /** 期間コード */
    public String termCd;

    /** 削除フラグ */
    public boolean deleteFlag;

    /** 検索基準日 */
    public Date baseDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}