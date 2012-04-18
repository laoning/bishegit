/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * アカウントグループ選択ダイアログ一覧検索条件モデルです。
 * 
 */
public final class AccountGroupSelectDialogCriteriaDto extends PagerSupport {

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * アカウントグループコード
     */
    public String crmAccountGroupCd;

    /**
     * アカウントグループ名
     */
    public String crmAccountGroupName;

    /**
     * アカウントグループ検索名
     */
    public String crmAccountGroupSearchName;

    /**
     * システム日付
     */
    public Date systemDate = DateUtil.today();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
