/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcAccount;

/**
 * CRMアカウント一覧検索結果モデルです。
 */
public final class AccountSearchResultDto extends AbstractCrmCcAccount {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = -3303311512679839957L;

    /**
     * 主担当者名
     */
    public String userName;

    /**
     * 主担当者コード
     */
    public String userCd;

    /**
     * 主担当組織
     */
    public String departmentName;

    /**
     * 商談開始
     */
    public Date businessStartDate;

    /**
     * 契約開始
     */
    public Date contractStartDate;

    /**
     * 主担当者組織コード
     */
    public String departmentCd;

    /**
     * 住所１
     */
    public String address;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
