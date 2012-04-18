/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * アカウント検索初期化結果です。
 */
public final class SearchInitializeResponseModel {

    /**
     * 区分の一覧
     */
    @XStreamAlias("crmAccountTypes")
    public List<KeyValueBean> crmAccountTypes = newArrayList();

    /**
     * 状況の一覧
     */
    @XStreamAlias("crmAccountStatusList")
    public List<KeyValueBean> crmAccountStatusList = newArrayList();

    /**
     * 重要度の一覧
     */
    @XStreamAlias("importantLevelTypes")
    public List<KeyValueBean> importantLevelTypes = newArrayList();

    /**
     * アカウント検索遷移先ユースケースID
     */
    public String transferUseCaseID;

    /**
     * アカウント検索遷移先フィーチャーID
     */
    public String transferFeatureID;

    /**
     * アカウント検索遷移先アプリケーションID
     */
    public String transferApplicationID;

    /**
     * アカウント検索遷移先モジュールID
     */
    public String transferModuleID;

    /**
     * アカウント新規フラグ
     */
    public boolean accountNewFlag;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
