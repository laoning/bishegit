/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面の担当者設定ボタンの確定担当組織リストDtoです。
 */
public class ChargeUserListChangeAfterDto {

    /**
     * ユーザ名
     */
    public String userName;

    /**
     * ユーザコード
     */
    public String userCd;

    /**
     * 有効期間開始日
     */
    public String effectiveTermStart;

    /**
     * 有効期間終了日
     */
    public String effectiveTermEnd;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
