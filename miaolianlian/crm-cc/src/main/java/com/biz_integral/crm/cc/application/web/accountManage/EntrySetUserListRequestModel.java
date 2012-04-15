/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.BeanCollection;

/**
 * アカウント登録/更新画面の担当者設定ボタン条件モデルです。
 */
public final class EntrySetUserListRequestModel {

    /**
     * 確定担当者リスト
     */
    @BeanCollection
    public List<EntryFixChargeUserListRequestModel> fixChargeUserList =
        newArrayList();

    /**
     * 担当者一覧
     */
    @BeanCollection
    public List<EntryGridChargeUserListRequestModel> gridChargeUserList =
        newArrayList();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
