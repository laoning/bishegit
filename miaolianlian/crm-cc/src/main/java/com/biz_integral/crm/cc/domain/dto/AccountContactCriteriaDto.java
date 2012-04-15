/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面のコンタクト追加ボタンの条件Dtoです。
 */
public final class AccountContactCriteriaDto {

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
     * バージョン番号
     */
    public String versionNo;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * 最終更新者
     */
    public String recordUserCd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
