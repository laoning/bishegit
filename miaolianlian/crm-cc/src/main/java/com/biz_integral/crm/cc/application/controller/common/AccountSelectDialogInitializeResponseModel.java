/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * アカウント検索初期化結果です。
 */
public final class AccountSelectDialogInitializeResponseModel {

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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
