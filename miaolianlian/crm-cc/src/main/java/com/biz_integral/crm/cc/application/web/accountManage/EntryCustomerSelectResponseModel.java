/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * アカウント登録/更新画面の取引先選択ボタン結果モデルです。
 */
public final class EntryCustomerSelectResponseModel {

    /**
     * アカウント名
     */
    public String crmAccountName;

    /**
     * アカウント略称
     */
    public String crmAccountShortName;

    /**
     * アカウント検索名
     */
    public String crmAccountSearchName;

    /**
     * 取引先コード
     */
    public String customerCd;

    /**
     * 国コード
     */
    public String countryCd;

    /**
     * 郵便番号
     */
    public String zipCode;

    /**
     * 住所1
     */
    public String address1;

    /**
     * 住所2
     */
    public String address2;

    /**
     * 住所3
     */
    public String address3;

    /**
     * 電話番号
     */
    public String telephoneNumber;

    /**
     * 内線番号
     */
    public String extensionNumber;

    /**
     * FAX番号
     */
    public String faxNumber;

    /**
     * 内線FAX番号
     */
    public String extensionFaxNumber;

    /**
     * メールアドレス1
     */
    public String emailAddress1;

    /**
     * メールアドレス2
     */
    public String emailAddress2;

    /**
     * URL
     */
    public String url;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
