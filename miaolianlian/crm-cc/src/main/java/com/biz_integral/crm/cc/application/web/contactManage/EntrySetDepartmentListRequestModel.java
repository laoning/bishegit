/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.BeanCollection;

/**
 * コンタクト登録/更新画面の担当組織設定ボタン条件モデルです。
 */
public final class EntrySetDepartmentListRequestModel {

    /**
     * 確定担当組織リスト
     */
    @BeanCollection
    public List<EntryFixChargeDeptListRequestModel> fixChargeDeptList =
        newArrayList();

    /**
     * 担当組織一覧グリッド
     */
    @BeanCollection
    public List<EntryGridChargeDeptListRequestModel> gridChargeDeptList =
        newArrayList();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
