/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountGroupAttributeManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.crm.cc.domain.dto.ModuleDto;
import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウントグループ属性登録/更新画面の初期化結果モデルです。
 */
public final class EntryRequestModel {

    /**
     * アカウント分類コード
     */
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountClassCd"))
    public String crmAccountClassCd;

    /**
     * アカウント分類名
     */
    public String crmAccountClassName;

    /**
     * 取引種別
     */
    @Required(arg0 = @Arg(key = "CRM.CC.dealClass"))
    public String dealClass;

    /**
     * 画面メンテ対象コンボボックス
     */
    public String maintenanceTargetFlag;

    /**
     * IF更新方法コンボボックス
     */
    public String ifUpdateWay;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 利用モジュールIDリスト
     */
    public List<ModuleDto> moduleList = newArrayList();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
