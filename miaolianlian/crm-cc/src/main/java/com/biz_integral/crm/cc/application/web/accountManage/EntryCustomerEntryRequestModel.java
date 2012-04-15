/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.BeanCollection;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウント登録/更新画面の取引先登録条件モデルです。
 */
public final class EntryCustomerEntryRequestModel {

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * ダミーフラグ
     */
    public String dummyFlag;

    /**
     * アカウント名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountName", arg0 = @Arg(key = "CRM.CC.crmAccountName"))
    public String crmAccountName;

    /**
     * アカウント名（カナ）
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountNameKana", arg0 = @Arg(key = "CRM.CC.crmAccountNameKana"))
    public String crmAccountNameKana;

    /**
     * アカウント略称
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountShortName", arg0 = @Arg(key = "CRM.CC.crmAccountShortName"))
    public String crmAccountShortName;

    /**
     * アカウント検索名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountSearchName", arg0 = @Arg(key = "CRM.CC.crmAccountSearchName"))
    public String crmAccountSearchName;

    /**
     * アカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountCd", arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.crmAccountCd"))
    public String crmAccountCd;

    /**
     * 新アカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmAccountCd", arg0 = @Arg(key = "CRM.CC.crmNewAccountCd"))
    public String newCrmAccountCd;

    /**
     * 取引先コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "customerCd", arg0 = @Arg(key = "CRM.CC.customerCd"))
    public String customerCd;

    /**
     * 状況
     */
    public String crmAccountStatus;

    /**
     * 重要度
     */
    public String importantLevel;

    /**
     * 区分
     */
    public String crmAccountType;

    /**
     * 取引種別
     */
    public String dealClass;

    /**
     * 国コード
     */
    @DomainConstraint(namespace = "crm.cc", type = "countryCd", arg0 = @Arg(key = "CRM.CC.countryCd"))
    public String countryCd;

    /**
     * 郵便番号
     */
    @DomainConstraint(namespace = "crm.cc", type = "zipCode", arg0 = @Arg(key = "CRM.CC.zipCode"))
    public String zipCode;

    /**
     * 住所1
     */
    @DomainConstraint(namespace = "crm.cc", type = "address1", arg0 = @Arg(key = "CRM.CC.address"))
    public String address1;

    /**
     * 住所2
     */
    @DomainConstraint(namespace = "crm.cc", type = "address2", arg0 = @Arg(key = "CRM.CC.address"))
    public String address2;

    /**
     * 住所3
     */
    @DomainConstraint(namespace = "crm.cc", type = "address3", arg0 = @Arg(key = "CRM.CC.address"))
    public String address3;

    /**
     * 電話番号
     */
    @DomainConstraint(namespace = "crm.cc", type = "telephoneNumber", arg0 = @Arg(key = "CRM.CC.telephoneNumber"))
    public String telephoneNumber;

    /**
     * 内線番号
     */
    @DomainConstraint(namespace = "crm.cc", type = "extensionNumber", arg0 = @Arg(key = "CRM.CC.extensionNumber"))
    public String extensionNumber;

    /**
     * FAX番号
     */
    @DomainConstraint(namespace = "crm.cc", type = "faxNumber", arg0 = @Arg(key = "CRM.CC.faxNumber"))
    public String faxNumber;

    /**
     * 内線FAX番号
     */
    @DomainConstraint(namespace = "crm.cc", type = "extensionFaxNumber", arg0 = @Arg(key = "CRM.CC.extensionFaxNumber"))
    public String extensionFaxNumber;

    /**
     * 携帯番号
     */
    @DomainConstraint(namespace = "crm.cc", type = "telephoneNumber", arg0 = @Arg(key = "CRM.CC.mobileNumber"))
    public String mobileNumber;

    /**
     * メールアドレス1
     */
    @DomainConstraint(namespace = "crm.cc", type = "emailAddress", arg0 = @Arg(key = "CRM.CC.emailAddress1"))
    public String emailAddress1;

    /**
     * メールアドレス2
     */
    @DomainConstraint(namespace = "crm.cc", type = "emailAddress", arg0 = @Arg(key = "CRM.CC.emailAddress2"))
    public String emailAddress2;

    /**
     * 携帯メールアドレス
     */
    @DomainConstraint(namespace = "crm.cc", type = "emailAddress", arg0 = @Arg(key = "CRM.CC.mobileEmailAddress"))
    public String mobileEmailAddress;

    /**
     * URL
     */
    @DomainConstraint(namespace = "crm.cc", type = "url", arg0 = @Arg(key = "CRM.CC.url"))
    public String url;

    /**
     * 備考
     */
    @DomainConstraint(namespace = "crm.cc", type = "notes", arg0 = @Arg(key = "CRM.CC.notes"))
    public String notes;

    /**
     * 役職
     */
    @DomainConstraint(namespace = "crm.cc", type = "post", arg0 = @Arg(key = "CRM.CC.post"))
    public String post;

    /**
     * 性別
     */
    public String sex;

    /**
     * 生年月日
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.birthDate"))
    public String birthDate;

    /**
     * 担当組織一覧
     */
    @BeanCollection
    public List<EntryCreateDeptRequestModel> accountChargeDeptList =
        newArrayList();

    /**
     * 担当者一覧
     */
    @BeanCollection
    public List<EntryCreateUserRequestModel> accountChargeUserList =
        newArrayList();

    /**
     * 予備1（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField1"))
    public String customField1;

    /**
     * 予備1（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm1"))
    public String customField2;

    /**
     * 予備1（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt1"))
    public String customField3;

    /**
     * 予備2（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField2"))
    public String customField4;

    /**
     * 予備2 (数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm2"))
    public String customField5;

    /**
     * 予備2（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt2"))
    public String customField6;

    /**
     * 予備3（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField3"))
    public String customField7;

    /**
     * 予備3（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm3"))
    public String customField8;

    /**
     * 予備3 (日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt3"))
    public String customField9;

    /**
     * 予備4（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField4"))
    public String customField10;

    /**
     * 予備4（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm4"))
    public String customField11;

    /**
     * 予備4（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt4"))
    public String customField12;

    /**
     * 予備5（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField5"))
    public String customField13;

    /**
     * 予備5（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm5"))
    public String customField14;

    /**
     * 予備5（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt5"))
    public String customField15;

    /**
     * 予備6（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField6"))
    public String customField16;

    /**
     * 予備6（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm6"))
    public String customField17;

    /**
     * 予備6（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt6"))
    public String customField18;

    /**
     * 予備7（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField7"))
    public String customField19;

    /**
     * 予備7（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm7"))
    public String customField20;

    /**
     * 予備7（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt7"))
    public String customField21;

    /**
     * 予備8（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField8"))
    public String customField22;

    /**
     * 予備8（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm8"))
    public String customField23;

    /**
     * 予備8（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt8"))
    public String customField24;

    /**
     * 予備9（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField9"))
    public String customField25;

    /**
     * 予備9（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm9"))
    public String customField26;

    /**
     * 予備9（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt9"))
    public String customField27;

    /**
     * 予備10（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField10"))
    public String customField28;

    /**
     * 予備10（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm10"))
    public String customField29;

    /**
     * 予備10（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt10"))
    public String customField30;

    /**
     * 予備11（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField11"))
    public String customField31;

    /**
     * 予備11（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm11"))
    public String customField32;

    /**
     * 予備11（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt11"))
    public String customField33;

    /**
     * 予備12（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField12"))
    public String customField34;

    /**
     * 予備12（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm12"))
    public String customField35;

    /**
     * 予備12（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt12"))
    public String customField36;

    /**
     * 予備13（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField13"))
    public String customField37;

    /**
     * 予備13（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm13"))
    public String customField38;

    /**
     * 予備13（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt13"))
    public String customField39;

    /**
     * 予備14（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField14"))
    public String customField40;

    /**
     * 予備14（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm14"))
    public String customField41;

    /**
     * 予備14（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt14"))
    public String customField42;

    /**
     * 予備15（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField15"))
    public String customField43;

    /**
     * 予備15（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm15"))
    public String customField44;

    /**
     * 予備15（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt15"))
    public String customField45;

    /**
     * 予備16（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField16"))
    public String customField46;

    /**
     * 予備16（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm16"))
    public String customField47;

    /**
     * 予備16（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt16"))
    public String customField48;

    /**
     * 予備17（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField17"))
    public String customField49;

    /**
     * 予備17（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm17"))
    public String customField50;

    /**
     * 予備17（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt17"))
    public String customField51;

    /**
     * 予備18（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField18"))
    public String customField52;

    /**
     * 予備18（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm18"))
    public String customField53;

    /**
     * 予備18（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt18"))
    public String customField54;

    /**
     * 予備19（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField19"))
    public String customField55;

    /**
     * 予備19（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm19"))
    public String customField56;

    /**
     * 予備19（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt19"))
    public String customField57;

    /**
     * 予備20（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomField20"))
    public String customField58;

    /**
     * 予備20（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldNm20"))
    public String customField59;

    /**
     * 予備20（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmAccountCustomFieldDt20"))
    public String customField60;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
