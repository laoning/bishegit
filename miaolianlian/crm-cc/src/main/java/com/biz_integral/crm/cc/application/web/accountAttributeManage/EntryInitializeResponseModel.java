/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;

/**
 * アカウント属性登録／更新画面初期化レスポンスモデル
 */
public final class EntryInitializeResponseModel {

    /**
     * 取引種別リスト
     */
    public List<KeyValueBean> dealClassList = new ArrayList<KeyValueBean>();

    /**
     * 状況リスト
     */
    public List<KeyValueBean> crmAccountStatusList =
        new ArrayList<KeyValueBean>();

    /**
     * 画面メンテ対象リスト
     */
    public List<KeyValueBean> maintTargetList = new ArrayList<KeyValueBean>();

    /**
     * IF更新方法リスト
     */
    public List<KeyValueBean> ifUpdateWayList = new ArrayList<KeyValueBean>();

    /**
     * 画面メンテ対象フラグ
     */
    public String maintenanceTargetFlag;

    /**
     * IF更新方法
     */
    public String ifUpdateWay;

    /**
     * バージョン番号
     */
    public long versionNo;

    /**
     * モジュール一覧
     */
    public List<EntryAccountModuleModel> moduleList =
        new ArrayList<EntryAccountModuleModel>();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
