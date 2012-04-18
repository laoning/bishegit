/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * アカウント属性一覧検索結果モデルです。
 */
public final class AccountAttributeSearchResultDto implements Serializable {

    /** デフォルト・シリアルバージョンID */
    private static final long serialVersionUID = -8989536058452837275L;

    /** アカウント分類コード */
    public String crmAccountClassCd;

    /** アカウント分類名 */
    public String crmAccountClassName;

    /** 取引情報値 */
    public String dealClassCd;

    /** 取引情報 */
    public String dealClass;

    /** アカウント状況値 */
    public String crmAccountStatusCd;

    /** アカウント状況 */
    public String crmAccountStatus;

    /** 画面メンテ対象 */
    public String maintenanceTargetFlag;

    /** IF更新方法 */
    public String ifUpdateWay;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
