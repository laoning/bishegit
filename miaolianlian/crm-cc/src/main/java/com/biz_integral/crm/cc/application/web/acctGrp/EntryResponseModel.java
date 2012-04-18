/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.crm.cc.application.web.accountManage.EntryAccountChargeDeptResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryAccountChargeUserResponseModel;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * アカウント登録/更新画面の初期化結果モデルです。
 */
public final class EntryResponseModel {

    /**
     * アカウントグループコード
     */
    public String crmAccountGroupCd;

    /**
     * アカウントグループ名
     */
    public String crmAccountGroupName;

    /**
     * アカウントグループ略称
     */
    public String crmAccountGroupShortName;

    /**
     * アカウントグループ検索名
     */
    public String crmAccountGroupSearchName;

    /**
     * アカウント分類コード
     */
    public String crmAccountClassCd;

    /**
     * 備考
     */
    public String notes;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 担当組織一覧
     */
    public List<EntryAccountChargeDeptResponseModel> accountChargeDeptResponseList =
        new ArrayList<EntryAccountChargeDeptResponseModel>();

    /**
     * 担当者一覧
     */
    public List<EntryAccountChargeUserResponseModel> accountChargeUserResponseList =
        new ArrayList<EntryAccountChargeUserResponseModel>();

    /**
     * アカウント一覧
     */
    public List<EntryAccountModel> accountList;

    /**
     * ロケールIDの一覧
     */
    @XStreamAlias("localeIdList")
    public List<KeyValueBean> localeIdList;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
