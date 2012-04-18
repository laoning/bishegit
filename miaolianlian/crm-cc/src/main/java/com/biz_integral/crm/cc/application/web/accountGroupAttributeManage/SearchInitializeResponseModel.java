/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountGroupAttributeManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;

/**
 * アカウントグループ属性検索初期化モデルです。
 */
public final class SearchInitializeResponseModel {

    /** モジュールID：コンボボックス */
    public List<KeyValueBean> moduleIdList = new ArrayList<KeyValueBean>();

    /** 取引種別：コンボボックス */
    public List<KeyValueBean> dealClassList = new ArrayList<KeyValueBean>();

    /** ファイル名 */
    public String fileName;

    /** アカウントグループ属性新規フラグ */
    public boolean accountGroupAttributeNewFlag;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
