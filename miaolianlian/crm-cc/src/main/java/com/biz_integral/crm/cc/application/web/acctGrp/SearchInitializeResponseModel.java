/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * アカウントグループ検索初期化結果です。
 */
public final class SearchInitializeResponseModel {

    /** ロケールIDの一覧 */
    @XStreamAlias("localeId")
    public List<KeyValueBean> localeId = newArrayList();

    /** 初期ロケールID */
    public String defaultLocaleId;

    /** アカウントグループ新規フラグ */
    public boolean accountGroupNewFlag;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
