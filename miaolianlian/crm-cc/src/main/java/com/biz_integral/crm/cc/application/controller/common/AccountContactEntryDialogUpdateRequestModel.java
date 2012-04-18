/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.extension.validation.annotation.Arg;
import com.biz_integral.extension.validation.annotation.DomainConstraint;
import com.biz_integral.extension.validation.annotation.Required;

/**
 * アカウントコンタクト登録/更新の更新submitモデルです。
 */
public final class AccountContactEntryDialogUpdateRequestModel {

    /**
     * CRMアカウントコード
     */
    public String crmAccountCd;

    /**
     * CRMコンタクトコード
     */
    public String crmContactCd;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * 期間（自）
     */
    public String startDate;

    /**
     * 期間（至）
     */
    public String endDate;

    /**
     * 有効期間（自）
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.effectiveTermFrom"))
    @Required(arg0 = @Arg(key = "CRM.CC.effectiveTermFrom"))
    public String effectiveTermFrom;

    /**
     * 有効期間（至）
     */
    @DomainConstraint(namespace = "crm.cc", type = "standardDate", arg0 = @Arg(key = "CRM.CC.effectiveTermTo"))
    @Required(arg0 = @Arg(key = "CRM.CC.effectiveTermTo"))
    public String effectiveTermTo;

    /**
     * 備考
     */
    @DomainConstraint(namespace = "crm.cc", type = "notes", arg0 = @Arg(key = "CRM.CC.notes"))
    public String notes;

    /**
     * 予備1（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField1"))
    public String customField1;

    /**
     * 予備1（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm1"))
    public String customField2;

    /**
     * 予備1（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt1"))
    public String customField3;

    /**
     * 予備2（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField2"))
    public String customField4;

    /**
     * 予備2（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm2"))
    public String customField5;

    /**
     * 予備2（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt2"))
    public String customField6;

    /**
     * 予備3（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField3"))
    public String customField7;

    /**
     * 予備3（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm3"))
    public String customField8;

    /**
     * 予備3（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt3"))
    public String customField9;

    /**
     * 予備4（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField4"))
    public String customField10;

    /**
     * 予備4（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm4"))
    public String customField11;

    /**
     * 予備4（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt4"))
    public String customField12;

    /**
     * 予備5（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField5"))
    public String customField13;

    /**
     * 予備5（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm5"))
    public String customField14;

    /**
     * 予備5（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt5"))
    public String customField15;

    /**
     * 予備6（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField6"))
    public String customField16;

    /**
     * 予備6（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm6"))
    public String customField17;

    /**
     * 予備6（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt6"))
    public String customField18;

    /**
     * 予備7（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField7"))
    public String customField19;

    /**
     * 予備7（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm7"))
    public String customField20;

    /**
     * 予備7（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt7"))
    public String customField21;

    /**
     * 予備8（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField8"))
    public String customField22;

    /**
     * 予備8（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm8"))
    public String customField23;

    /**
     * 予備8（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt8"))
    public String customField24;

    /**
     * 予備9（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField9"))
    public String customField25;

    /**
     * 予備9（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm9"))
    public String customField26;

    /**
     * 予備9（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt9"))
    public String customField27;

    /**
     * 予備10（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField10"))
    public String customField28;

    /**
     * 予備10（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm10"))
    public String customField29;

    /**
     * 予備10（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt10"))
    public String customField30;

    /**
     * 予備11（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField11"))
    public String customField31;

    /**
     * 予備11（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm11"))
    public String customField32;

    /**
     * 予備11（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt11"))
    public String customField33;

    /**
     * 予備12（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField12"))
    public String customField34;

    /**
     * 予備12（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm12"))
    public String customField35;

    /**
     * 予備12（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt12"))
    public String customField36;

    /**
     * 予備13（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField13"))
    public String customField37;

    /**
     * 予備13（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm13"))
    public String customField38;

    /**
     * 予備13（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt13"))
    public String customField39;

    /**
     * 予備14（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField14"))
    public String customField40;

    /**
     * 予備14（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm14"))
    public String customField41;

    /**
     * 予備14（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt14"))
    public String customField42;

    /**
     * 予備15（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField15"))
    public String customField43;

    /**
     * 予備15（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm15"))
    public String customField44;

    /**
     * 予備15（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt15"))
    public String customField45;

    /**
     * 予備16（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField16"))
    public String customField46;

    /**
     * 予備16（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm16"))
    public String customField47;

    /**
     * 予備16（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt16"))
    public String customField48;

    /**
     * 予備17（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField17"))
    public String customField49;

    /**
     * 予備17（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm17"))
    public String customField50;

    /**
     * 予備17（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt17"))
    public String customField51;

    /**
     * 予備18（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField18"))
    public String customField52;

    /**
     * 予備18（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm18"))
    public String customField53;

    /**
     * 予備18（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt18"))
    public String customField54;

    /**
     * 予備19（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField19"))
    public String customField55;

    /**
     * 予備19（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm19"))
    public String customField56;

    /**
     * 予備19（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt19"))
    public String customField57;

    /**
     * 予備20（テキスト）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldTxt", arg0 = @Arg(key = "CRM.CC.accountContactCustomField20"))
    public String customField58;

    /**
     * 予備20（数値）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldNm", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldNm20"))
    public String customField59;

    /**
     * 予備20（日付）
     */
    @DomainConstraint(namespace = "crm.cc", type = "customFieldDt", arg0 = @Arg(key = "CRM.CC.accountContactCustomFieldDt20"))
    public String customField60;

    /**
     * ソートキー
     */
    public String sortKey;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 作成者
     */
    public String createUserCd;

    /**
     * 作成日
     */
    public String createDate;

    /**
     * CRMアカウントバージョン番号
     */
    public String crmAccountVersionNo;

    /**
     * CRMコンタクトバージョン番号
     */
    public String crmContactVersionNo;

    /**
     * アカウントコンタクト追加情報表示フラグ
     */
    public String accountContactAddInfoShowFlag;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
