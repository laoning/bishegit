/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * ユーザ設定ダイアログ一覧検索条件モデルです。
 * 
 */
public final class UserDialogCriteriaDto extends PagerSupport {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * 検索基準日
     */
    public Date searchBaseDate;

    /**
     * ユーザコード
     */
    public String userCd;

    /**
     * ユーザ名
     */
    public String userName;

    /**
     * ユーザ検索名
     */
    public String userShortName;

    /**
     * 組織コード
     */
    public String departmentCd;

    /**
     * 削除フラグ
     */
    public String deleteFlag;

    /**
     * 会社組織セットコード
     */
    public String companyDepartmentSetCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
