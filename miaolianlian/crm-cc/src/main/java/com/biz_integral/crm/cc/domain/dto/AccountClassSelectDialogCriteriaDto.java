/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * アカウント分類選択ダイアログ一覧検索条件モデルです。
 * 
 */
public final class AccountClassSelectDialogCriteriaDto extends PagerSupport {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * アカウント分類コード
     */
    public String crmAccountClassCd;

    /**
     * アカウント分類名
     */
    public String crmAccountClassName;

    /**
     * 削除フラグ
     */
    public String deleteFlag;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
