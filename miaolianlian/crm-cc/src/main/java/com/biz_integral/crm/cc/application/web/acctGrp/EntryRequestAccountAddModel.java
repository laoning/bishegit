/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウントグループ登録/更新画面のアカウント登録モデルです。
 */
public final class EntryRequestAccountAddModel {

    /**
     * アカウントグループコード
     */
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountGroupCd"))
    public String crmAccountGroupCd;

    /**
     * アカウント一覧
     */
    public List<EntryAccountModel> entryAccountList = newArrayList();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
