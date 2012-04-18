/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcContact;

/**
 * CRMコンタクト一覧検索結果モデルです。
 */
public final class ContactSearchResultDto extends AbstractCrmCcContact {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = -8293579992758593351L;

    /**
     * 主担当者名
     */
    public String userName;

    /**
     * 主担当者コード
     */
    public String userCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
