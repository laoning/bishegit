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
 * 初期表示responseモデルです。
 */
public final class CompeteEntryDialogInitializeResponseModel {

    /**
     * 競合度の一覧
     */
    @XStreamAlias("competitionType")
    public List<KeyValueBean> competitionType = newArrayList();

    /**
     * 競合度
     */
    public String competitionLevel;

    /**
     * 競合先名
     */
    public String competitionName;

    /**
     * 獲得案件事例
     */
    public String acquirementProposalCase;

    /**
     * 概要
     */
    public String overview;

    /**
     * 備考
     */
    public String notes;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * CRMアカウントバージョン番号
     */
    public String crmAccountVersionNo;

    /**
     * CRMコンタクトバージョン番号
     */
    public String crmContactVersionNo;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
