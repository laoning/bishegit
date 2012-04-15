/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountGroupAttributeManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.crm.cc.domain.dto.ModuleDto;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;

/**
 * アカウントグループ属性登録/更新画面の初期化結果モデルです。
 */
public final class EntryResponseModel {

    /**
     * アカウント分類コード
     */
    public String crmAccountClassCd;

    /**
     * アカウント分類名
     */
    public String crmAccountClassName;

    /**
     * 取引種別
     */
    public String dealClass;

    /**
     * 画面メンテ対象
     */
    public String maintenanceTargetFlag = "0";

    /**
     * IF更新方法
     */
    public String ifUpdateWay;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 取引種別コンボボックス
     */
    public List<KeyValueBean> dealClassList = new ArrayList<KeyValueBean>();

    /**
     * 画面メンテ対象コンボボックス
     */
    public List<KeyValueBean> maintenanceTargetFlagList =
        new ArrayList<KeyValueBean>();

    /**
     * IF更新方法コンボボックス
     */
    public List<KeyValueBean> ifUpdateWayList = new ArrayList<KeyValueBean>();

    /**
     * 利用モジュールIDリスト
     */
    public List<ModuleDto> moduleList = new ArrayList<ModuleDto>();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
