/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント分類所属の削除モデルです。
 * 
 */
public final class EntryAccountDeleteRequestModel {

    /**
     * アカウント分類所属削除モデルの一覧
     */
    public List<EntryAccountDeleteModel> accountCdList = newArrayList();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
