/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * 住所選択ダイアログ一覧検索条件モデルです。
 * 
 */
public final class AddressSelectDialogFilterRequestModel extends PagerSupport {

    /**
     * 検索基準日
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.searchBaseDate"))
    public String searchBaseDate;

    /**
     * 都道府県コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "tdfkCd", arg0 = @Arg(key = "CRM.CC.tdfkCd"))
    public String tdfkCd;

    /**
     * 都道府県名
     */
    @DomainConstraint(namespace = "crm.cc", type = "tdfkAddress", arg0 = @Arg(key = "CRM.CC.tdfkAddress"))
    public String tdfkAddress;

    /**
     * 市区町村コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "skcsCd", arg0 = @Arg(key = "CRM.CC.skcsCd"))
    public String skcsCd;

    /**
     * 市区町村名
     */
    @DomainConstraint(namespace = "crm.cc", type = "skcsAddress", arg0 = @Arg(key = "CRM.CC.skcsAddress"))
    public String skcsAddress;

    /**
     * ソート順
     */
    public String sort;

    /**
     * ソート項目
     */
    public String sortItem;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
