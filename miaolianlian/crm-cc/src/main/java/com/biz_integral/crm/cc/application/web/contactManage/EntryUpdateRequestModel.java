/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.BeanCollection;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * コンタクト登録/更新画面の更新条件モデルです。
 */
public final class EntryUpdateRequestModel {

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * コンタクト名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactName", arg0 = @Arg(key = "CRM.CC.crmContactName"))
    public String crmContactName;

    /**
     * コンタクト名（カナ）
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactNameKana", arg0 = @Arg(key = "CRM.CC.crmContactNameKana"))
    public String crmContactNameKana;

    /**
     * コンタクト略称
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactShortName", arg0 = @Arg(key = "CRM.CC.crmContactShortName"))
    public String crmContactShortName;

    /**
     * コンタクト検索名
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactSearchName", arg0 = @Arg(key = "CRM.CC.crmContactSearchName"))
    public String crmContactSearchName;

    /**
     * コンタクトコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactCd", arg0 = @Arg(key = "CRM.CC.crmContactCd"))
    @Required(arg0 = @Arg(key = "CRM.CC.crmContactCd"))
    public String crmContactCd;

    /**
     * 新アカウントコード
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactCd", arg0 = @Arg(key = "CRM.CC.crmNewContactCd"))
    public String newCrmContactCd;

    /**
     * タイプ
     */
    @DomainConstraint(namespace = "crm.cc", type = "crmContactType", arg0 = @Arg(key = "CRM.CC.crmContactType"))
    public String crmContactType;

    /**
     * 生年月日
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.birthDate"))
    public String birthDate;

    /**
     * 性別
     */
    public String sex;

    /**
     * キーパーソン
     */
    @DomainConstraint(namespace = "crm.cc", type = "keyPerson", arg0 = @Arg(key = "CRM.CC.keyPerson"))
    public String keyPerson;

    /**
     * 重要度
     */
    public String importantLevel;

    /**
     * 所属
     */
    @DomainConstraint(namespace = "crm.cc", type = "belong", arg0 = @Arg(key = "CRM.CC.belong"))
    public String belong;

    /**
     * 役職
     */
    @DomainConstraint(namespace = "crm.cc", type = "post", arg0 = @Arg(key = "CRM.CC.post"))
    public String post;

    /**
     * 出身地
     */
    @DomainConstraint(namespace = "crm.cc", type = "homeTown", arg0 = @Arg(key = "CRM.CC.hometown"))
    public String hometown;

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
    @DomainConstraint(namespace = "crm.cc", type = "address1", arg0 = @Arg(key = "CRM.CC.address1"))
    public String address1;

    /**
     * 住所2
     */
    @DomainConstraint(namespace = "crm.cc", type = "address2", arg0 = @Arg(key = "CRM.CC.address2"))
    public String address2;

    /**
     * 住所3
     */
    @DomainConstraint(namespace = "crm.cc", type = "address3", arg0 = @Arg(key = "CRM.CC.address3"))
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
     * 担当組織一覧
     */
    @BeanCollection
    public List<EntryCreateDeptRequestModel> contactChargeDeptList =
        newArrayList();

    /**
     * 担当者一覧
     */
    @BeanCollection
    public List<EntryCreateUserRequestModel> contactChargeUserList =
        newArrayList();

    /**
     * 予備1（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField1"))
    public String customField1;

    /**
     * 予備1（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm1"))
    public String customField2;

    /**
     * 予備1（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt1"))
    public String customField3;

    /**
     * 予備2（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField2"))
    public String customField4;

    /**
     * 予備2 (数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm2"))
    public String customField5;

    /**
     * 予備2（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt2"))
    public String customField6;

    /**
     * 予備3（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField3"))
    public String customField7;

    /**
     * 予備3（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm3"))
    public String customField8;

    /**
     * 予備3 (日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt3"))
    public String customField9;

    /**
     * 予備4（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField4"))
    public String customField10;

    /**
     * 予備4（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm4"))
    public String customField11;

    /**
     * 予備4（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt4"))
    public String customField12;

    /**
     * 予備5（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField5"))
    public String customField13;

    /**
     * 予備5（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm5"))
    public String customField14;

    /**
     * 予備5（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt5"))
    public String customField15;

    /**
     * 予備6（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField6"))
    public String customField16;

    /**
     * 予備6（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm6"))
    public String customField17;

    /**
     * 予備6（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt6"))
    public String customField18;

    /**
     * 予備7（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField7"))
    public String customField19;

    /**
     * 予備7（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm7"))
    public String customField20;

    /**
     * 予備7（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt7"))
    public String customField21;

    /**
     * 予備8（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField8"))
    public String customField22;

    /**
     * 予備8（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm8"))
    public String customField23;

    /**
     * 予備8（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt8"))
    public String customField24;

    /**
     * 予備9（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField9"))
    public String customField25;

    /**
     * 予備9（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm9"))
    public String customField26;

    /**
     * 予備9（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt9"))
    public String customField27;

    /**
     * 予備10（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField10"))
    public String customField28;

    /**
     * 予備10（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm10"))
    public String customField29;

    /**
     * 予備10（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt10"))
    public String customField30;

    /**
     * 予備11（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField11"))
    public String customField31;

    /**
     * 予備11（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm11"))
    public String customField32;

    /**
     * 予備11（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt11"))
    public String customField33;

    /**
     * 予備12（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField12"))
    public String customField34;

    /**
     * 予備12（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm12"))
    public String customField35;

    /**
     * 予備12（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt12"))
    public String customField36;

    /**
     * 予備13（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField13"))
    public String customField37;

    /**
     * 予備13（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm13"))
    public String customField38;

    /**
     * 予備13（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt13"))
    public String customField39;

    /**
     * 予備14（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField14"))
    public String customField40;

    /**
     * 予備14（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm14"))
    public String customField41;

    /**
     * 予備14（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt14"))
    public String customField42;

    /**
     * 予備15（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField15"))
    public String customField43;

    /**
     * 予備15（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm15"))
    public String customField44;

    /**
     * 予備15（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt15"))
    public String customField45;

    /**
     * 予備16（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField16"))
    public String customField46;

    /**
     * 予備16（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm16"))
    public String customField47;

    /**
     * 予備16（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt16"))
    public String customField48;

    /**
     * 予備17（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField17"))
    public String customField49;

    /**
     * 予備17（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm17"))
    public String customField50;

    /**
     * 予備17（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt17"))
    public String customField51;

    /**
     * 予備18（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField18"))
    public String customField52;

    /**
     * 予備18（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm18"))
    public String customField53;

    /**
     * 予備18（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt18"))
    public String customField54;

    /**
     * 予備19（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField19"))
    public String customField55;

    /**
     * 予備19（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm19"))
    public String customField56;

    /**
     * 予備19（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt19"))
    public String customField57;

    /**
     * 予備20（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.crmContactCustomField20"))
    public String customField58;

    /**
     * 予備20（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldNm20"))
    public String customField59;

    /**
     * 予備20（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.crmContactCustomFieldDt20"))
    public String customField60;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
