/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import javax.persistence.Version;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.foundation.core.util.DateUtil;

/**
 * CRMコンタクトモデルです。<br>
 */
public class CrmCcContactDto {

    /**
     * ユーザコード
     */
    public String userCd;

    /**
     * CRM領域コード
     */
    public String crmDomainCd;

    /**
     * 会社コード
     */
    public String companyCd;

    /**
     * CRMコンタクトード
     */
    public String crmContactCd;

    /**
     * ロケールID
     */
    public String localeId;

    /**
     * 期間コード
     */
    public String termCd;

    /**
     * 開始日
     */
    public Date startDate;

    /**
     * 終了日
     */
    public Date endDate;

    /**
     * CRMコンタクト名
     */
    public String crmContactName;

    /**
     * CRMコンタクト名（カナ）
     */
    public String crmContactNameKana;

    /**
     * CRMコンタクト略称
     */
    public String crmContactShortName;

    /**
     * CRMコンタクト検索名
     */
    public String crmContactSearchName;

    /**
     * 性別
     */
    public String sex;

    /**
     * タイプ
     */
    public String crmContactType;

    /**
     * 所属
     */
    public String belong;

    /**
     * 役職
     */
    public String post;

    /**
     * キーパーソン
     */
    public String keyPerson;

    /**
     * 生年月日
     */
    public Date birthDate;

    /**
     * 出身地
     */
    public String hometown;

    /**
     * 国コード
     */
    public String countryCd;

    /**
     * 郵便番号
     */
    public String zipCode;

    /**
     * 住所１
     */
    public String address1;

    /**
     * 住所２
     */
    public String address2;

    /**
     * 住所３
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
     * 携帯番号
     */
    public String mobileNumber;

    /**
     * メールアドレス１
     */
    public String emailAddress1;

    /**
     * メールアドレス２
     */
    public String emailAddress2;

    /**
     * 携帯メールアドレス
     */
    public String mobileEmailAddress;

    /**
     * URL
     */
    public String url;

    /**
     * 備考
     */
    public String notes;

    /**
     * 重要度
     */
    public String importantLevel;

    /**
     * 新CRMコンタクトコード
     */
    public String newCrmContactCd;

    /**
     * ダミーフラグ
     */
    public boolean dummyFlag;

    /**
     * カスタムフィールド1
     */
    public String customField1;

    /**
     * カスタムフィールド2
     */
    public String customField2;

    /**
     * カスタムフィールド3
     */
    public String customField3;

    /**
     * カスタムフィールド4
     */
    public String customField4;

    /**
     * カスタムフィールド5
     */
    public String customField5;

    /**
     * カスタムフィールド6
     */
    public String customField6;

    /**
     * カスタムフィールド7
     */
    public String customField7;

    /**
     * カスタムフィールド8
     */
    public String customField8;

    /**
     * カスタムフィールド9
     */
    public String customField9;

    /**
     * カスタムフィールド10
     */
    public String customField10;

    /**
     * カスタムフィールド11
     */
    public String customField11;

    /**
     * カスタムフィールド12
     */
    public String customField12;

    /**
     * カスタムフィールド13
     */
    public String customField13;

    /**
     * カスタムフィールド14
     */
    public String customField14;

    /**
     * カスタムフィールド15
     */
    public String customField15;

    /**
     * カスタムフィールド16
     */
    public String customField16;

    /**
     * カスタムフィールド17
     */
    public String customField17;

    /**
     * カスタムフィールド18
     */
    public String customField18;

    /**
     * カスタムフィールド19
     */
    public String customField19;

    /**
     * カスタムフィールド20
     */
    public String customField20;

    /**
     * カスタムフィールド21
     */
    public String customField21;

    /**
     * カスタムフィールド22
     */
    public String customField22;

    /**
     * カスタムフィールド23
     */
    public String customField23;

    /**
     * カスタムフィールド24
     */
    public String customField24;

    /**
     * カスタムフィールド25
     */
    public String customField25;

    /**
     * カスタムフィールド26
     */
    public String customField26;

    /**
     * カスタムフィールド27
     */
    public String customField27;

    /**
     * カスタムフィールド28
     */
    public String customField28;

    /**
     * カスタムフィールド29
     */
    public String customField29;

    /**
     * カスタムフィールド30
     */
    public String customField30;

    /**
     * カスタムフィールド31
     */
    public String customField31;

    /**
     * カスタムフィールド32
     */
    public String customField32;

    /**
     * カスタムフィールド33
     */
    public String customField33;

    /**
     * カスタムフィールド34
     */
    public String customField34;

    /**
     * カスタムフィールド35
     */
    public String customField35;

    /**
     * カスタムフィールド36
     */
    public String customField36;

    /**
     * カスタムフィールド37
     */
    public String customField37;

    /**
     * カスタムフィールド38
     */
    public String customField38;

    /**
     * カスタムフィールド39
     */
    public String customField39;

    /**
     * カスタムフィールド40
     */
    public String customField40;

    /**
     * カスタムフィールド41
     */
    public String customField41;

    /**
     * カスタムフィールド42
     */
    public String customField42;

    /**
     * カスタムフィールド43
     */
    public String customField43;

    /**
     * カスタムフィールド44
     */
    public String customField44;

    /**
     * カスタムフィールド45
     */
    public String customField45;

    /**
     * カスタムフィールド46
     */
    public String customField46;

    /**
     * カスタムフィールド47
     */
    public String customField47;

    /**
     * カスタムフィールド48
     */
    public String customField48;

    /**
     * カスタムフィールド49
     */
    public String customField49;

    /**
     * カスタムフィールド50
     */
    public String customField50;

    /**
     * カスタムフィールド51
     */
    public String customField51;

    /**
     * カスタムフィールド52
     */
    public String customField52;

    /**
     * カスタムフィールド53
     */
    public String customField53;

    /**
     * カスタムフィールド54
     */
    public String customField54;

    /**
     * カスタムフィールド55
     */
    public String customField55;

    /**
     * カスタムフィールド56
     */
    public String customField56;

    /**
     * カスタムフィールド57
     */
    public String customField57;

    /**
     * カスタムフィールド58
     */
    public String customField58;

    /**
     * カスタムフィールド59
     */
    public String customField59;

    /**
     * カスタムフィールド60
     */
    public String customField60;

    /**
     * 削除フラグ
     */
    public boolean deleteFlag;

    /**
     * ソートキー
     */
    public Long sortKey;

    /**
     * バージョン番号
     */
    @Version
    public Long versionNo;

    /**
     * 作成者
     */
    public String createUserCd;

    /**
     * 作成日
     */
    public Date createDate;

    /**
     * 最終更新者
     */
    public String recordUserCd;

    /**
     * 最終更新日
     */
    public Date recordDate;

    /**
     * システム日付
     */
    public Date systemDate = DateUtil.today();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
