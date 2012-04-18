/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.biz_integral.service.persistence.util.EntityUtil;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * CRMコンタクト
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_contact")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:02"
)
public abstract class AbstractCrmCcContact implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcContact() {
    }

    /** 会社コード */
    @Id
    @Column(name = "company_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String companyCd;

    /** CRMコンタクトコード */
    @Id
    @Column(name = "crm_contact_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String crmContactCd;

    /** ロケールID */
    @Id
    @Column(name = "locale_id",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String localeId;

    /** 期間コード */
    @Id
    @Column(name = "term_cd",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String termCd;

    /** 開始日 */
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date",
            columnDefinition = "date", 
            nullable = false, unique = false)
    private Date startDate;

    /** 終了日 */
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date",
            columnDefinition = "date", 
            nullable = false, unique = false)
    private Date endDate;

    /** CRMコンタクト名 */
    @Column(name = "crm_contact_name",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String crmContactName;

    /** CRMコンタクト名（カナ） */
    @Column(name = "crm_contact_name_kana",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String crmContactNameKana;

    /** CRMコンタクト略称 */
    @Column(name = "crm_contact_short_name",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String crmContactShortName;

    /** CRMコンタクト検索名 */
    @Column(name = "crm_contact_search_name",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String crmContactSearchName;

    /** 性別 */
    @Column(name = "sex",
            columnDefinition = "varchar2(1)", 
            nullable = true, unique = false)
    private String sex;

    /** タイプ */
    @Column(name = "crm_contact_type",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String crmContactType;

    /** 所属 */
    @Column(name = "belong",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String belong;

    /** 役職 */
    @Column(name = "post",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String post;

    /** キーパーソン */
    @Column(name = "key_person",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String keyPerson;

    /** 生年月日 */
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date",
            columnDefinition = "date", 
            nullable = true, unique = false)
    private Date birthDate;

    /** 出身地 */
    @Column(name = "hometown",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String hometown;

    /** 国コード */
    @Column(name = "country_cd",
            columnDefinition = "varchar2(100)", 
            nullable = true, unique = false)
    private String countryCd;

    /** 郵便番号 */
    @Column(name = "zip_code",
            columnDefinition = "varchar2(50)", 
            nullable = true, unique = false)
    private String zipCode;

    /** 住所１ */
    @Column(name = "address1",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String address1;

    /** 住所２ */
    @Column(name = "address2",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String address2;

    /** 住所３ */
    @Column(name = "address3",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String address3;

    /** 電話番号 */
    @Column(name = "telephone_number",
            columnDefinition = "varchar2(50)", 
            nullable = true, unique = false)
    private String telephoneNumber;

    /** 内線番号 */
    @Column(name = "extension_number",
            columnDefinition = "varchar2(50)", 
            nullable = true, unique = false)
    private String extensionNumber;

    /** FAX番号 */
    @Column(name = "fax_number",
            columnDefinition = "varchar2(50)", 
            nullable = true, unique = false)
    private String faxNumber;

    /** 内線FAX番号 */
    @Column(name = "extension_fax_number",
            columnDefinition = "varchar2(50)", 
            nullable = true, unique = false)
    private String extensionFaxNumber;

    /** 携帯番号 */
    @Column(name = "mobile_number",
            columnDefinition = "varchar2(50)", 
            nullable = true, unique = false)
    private String mobileNumber;

    /** メールアドレス１ */
    @Column(name = "email_address1",
            columnDefinition = "varchar2(256)", 
            nullable = true, unique = false)
    private String emailAddress1;

    /** メールアドレス２ */
    @Column(name = "email_address2",
            columnDefinition = "varchar2(256)", 
            nullable = true, unique = false)
    private String emailAddress2;

    /** 携帯メールアドレス */
    @Column(name = "mobile_email_address",
            columnDefinition = "varchar2(256)", 
            nullable = true, unique = false)
    private String mobileEmailAddress;

    /** URL */
    @Column(name = "url",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String url;

    /** 備考 */
    @Column(name = "notes",
            columnDefinition = "varchar2(4000)", 
            nullable = true, unique = false)
    private String notes;

    /** 重要度 */
    @Column(name = "important_level",
            columnDefinition = "varchar2(100)", 
            nullable = true, unique = false)
    private String importantLevel;

    /** 新CRMコンタクトコード */
    @Column(name = "new_crm_contact_cd",
            columnDefinition = "varchar2(100)", 
            nullable = true, unique = false)
    private String newCrmContactCd;

    /** ダミーフラグ */
    @Column(name = "dummy_flag",
            columnDefinition = "varchar2(1) default '0' ", 
            nullable = false, unique = false)
    private boolean dummyFlag;

    /** カスタムフィールド1 */
    @Column(name = "custom_field1",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField1;

    /** カスタムフィールド2 */
    @Column(name = "custom_field2",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField2;

    /** カスタムフィールド3 */
    @Column(name = "custom_field3",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField3;

    /** カスタムフィールド4 */
    @Column(name = "custom_field4",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField4;

    /** カスタムフィールド5 */
    @Column(name = "custom_field5",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField5;

    /** カスタムフィールド6 */
    @Column(name = "custom_field6",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField6;

    /** カスタムフィールド7 */
    @Column(name = "custom_field7",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField7;

    /** カスタムフィールド8 */
    @Column(name = "custom_field8",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField8;

    /** カスタムフィールド9 */
    @Column(name = "custom_field9",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField9;

    /** カスタムフィールド10 */
    @Column(name = "custom_field10",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField10;

    /** カスタムフィールド11 */
    @Column(name = "custom_field11",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField11;

    /** カスタムフィールド12 */
    @Column(name = "custom_field12",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField12;

    /** カスタムフィールド13 */
    @Column(name = "custom_field13",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField13;

    /** カスタムフィールド14 */
    @Column(name = "custom_field14",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField14;

    /** カスタムフィールド15 */
    @Column(name = "custom_field15",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField15;

    /** カスタムフィールド16 */
    @Column(name = "custom_field16",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField16;

    /** カスタムフィールド17 */
    @Column(name = "custom_field17",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField17;

    /** カスタムフィールド18 */
    @Column(name = "custom_field18",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField18;

    /** カスタムフィールド19 */
    @Column(name = "custom_field19",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField19;

    /** カスタムフィールド20 */
    @Column(name = "custom_field20",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField20;

    /** カスタムフィールド21 */
    @Column(name = "custom_field21",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField21;

    /** カスタムフィールド22 */
    @Column(name = "custom_field22",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField22;

    /** カスタムフィールド23 */
    @Column(name = "custom_field23",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField23;

    /** カスタムフィールド24 */
    @Column(name = "custom_field24",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField24;

    /** カスタムフィールド25 */
    @Column(name = "custom_field25",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField25;

    /** カスタムフィールド26 */
    @Column(name = "custom_field26",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField26;

    /** カスタムフィールド27 */
    @Column(name = "custom_field27",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField27;

    /** カスタムフィールド28 */
    @Column(name = "custom_field28",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField28;

    /** カスタムフィールド29 */
    @Column(name = "custom_field29",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField29;

    /** カスタムフィールド30 */
    @Column(name = "custom_field30",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField30;

    /** カスタムフィールド31 */
    @Column(name = "custom_field31",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField31;

    /** カスタムフィールド32 */
    @Column(name = "custom_field32",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField32;

    /** カスタムフィールド33 */
    @Column(name = "custom_field33",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField33;

    /** カスタムフィールド34 */
    @Column(name = "custom_field34",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField34;

    /** カスタムフィールド35 */
    @Column(name = "custom_field35",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField35;

    /** カスタムフィールド36 */
    @Column(name = "custom_field36",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField36;

    /** カスタムフィールド37 */
    @Column(name = "custom_field37",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField37;

    /** カスタムフィールド38 */
    @Column(name = "custom_field38",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField38;

    /** カスタムフィールド39 */
    @Column(name = "custom_field39",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField39;

    /** カスタムフィールド40 */
    @Column(name = "custom_field40",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField40;

    /** カスタムフィールド41 */
    @Column(name = "custom_field41",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField41;

    /** カスタムフィールド42 */
    @Column(name = "custom_field42",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField42;

    /** カスタムフィールド43 */
    @Column(name = "custom_field43",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField43;

    /** カスタムフィールド44 */
    @Column(name = "custom_field44",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField44;

    /** カスタムフィールド45 */
    @Column(name = "custom_field45",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField45;

    /** カスタムフィールド46 */
    @Column(name = "custom_field46",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField46;

    /** カスタムフィールド47 */
    @Column(name = "custom_field47",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField47;

    /** カスタムフィールド48 */
    @Column(name = "custom_field48",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField48;

    /** カスタムフィールド49 */
    @Column(name = "custom_field49",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField49;

    /** カスタムフィールド50 */
    @Column(name = "custom_field50",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField50;

    /** カスタムフィールド51 */
    @Column(name = "custom_field51",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField51;

    /** カスタムフィールド52 */
    @Column(name = "custom_field52",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField52;

    /** カスタムフィールド53 */
    @Column(name = "custom_field53",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField53;

    /** カスタムフィールド54 */
    @Column(name = "custom_field54",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField54;

    /** カスタムフィールド55 */
    @Column(name = "custom_field55",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField55;

    /** カスタムフィールド56 */
    @Column(name = "custom_field56",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField56;

    /** カスタムフィールド57 */
    @Column(name = "custom_field57",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField57;

    /** カスタムフィールド58 */
    @Column(name = "custom_field58",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField58;

    /** カスタムフィールド59 */
    @Column(name = "custom_field59",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField59;

    /** カスタムフィールド60 */
    @Column(name = "custom_field60",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String customField60;

    /** 削除フラグ */
    @Column(name = "delete_flag",
            columnDefinition = "varchar2(1) default '0' ", 
            nullable = false, unique = false)
    private boolean deleteFlag;

    /** ソートキー */
    @Column(name = "sort_key",
            columnDefinition = "number(15,0)", 
            nullable = false, unique = false)
    private Long sortKey;

    /** バージョン番号 */
    @Version
    @Column(name = "version_no",
            columnDefinition = "number(18,0) default 1 ", 
            nullable = false, unique = false)
    private Long versionNo;

    /** 作成者 */
    @Column(name = "create_user_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String createUserCd;

    /** 作成日 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",
            columnDefinition = "timestamp(6)", 
            nullable = false, unique = false)
    private Date createDate;

    /** 最終更新者 */
    @Column(name = "record_user_cd",
            columnDefinition = "varchar2(100)", 
            nullable = true, unique = false)
    private String recordUserCd;

    /** 最終更新日 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "record_date",
            columnDefinition = "timestamp(6)", 
            nullable = true, unique = false)
    private Date recordDate;

    /**
     * 会社コードを返します。
     * 
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }

    /**
     * 会社コードを設定します。
     * 
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    /**
     * CRMコンタクトコードを返します。
     * 
     * @return CRMコンタクトコード
     */
    public String getCrmContactCd() {
        return crmContactCd;
    }

    /**
     * CRMコンタクトコードを設定します。
     * 
     * @param crmContactCd CRMコンタクトコード
     */
    public void setCrmContactCd(String crmContactCd) {
        this.crmContactCd = crmContactCd;
    }

    /**
     * ロケールIDを返します。
     * 
     * @return ロケールID
     */
    public String getLocaleId() {
        return localeId;
    }

    /**
     * ロケールIDを設定します。
     * 
     * @param localeId ロケールID
     */
    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    /**
     * 期間コードを返します。
     * 
     * @return 期間コード
     */
    public String getTermCd() {
        return termCd;
    }

    /**
     * 期間コードを設定します。
     * 
     * @param termCd 期間コード
     */
    public void setTermCd(String termCd) {
        this.termCd = termCd;
    }

    /**
     * 開始日を返します。
     * 
     * @return 開始日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 開始日を設定します。
     * 
     * @param startDate 開始日
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 終了日を返します。
     * 
     * @return 終了日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 終了日を設定します。
     * 
     * @param endDate 終了日
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * CRMコンタクト名を返します。
     * 
     * @return CRMコンタクト名
     */
    public String getCrmContactName() {
        return crmContactName;
    }

    /**
     * CRMコンタクト名を設定します。
     * 
     * @param crmContactName CRMコンタクト名
     */
    public void setCrmContactName(String crmContactName) {
        this.crmContactName = crmContactName;
    }

    /**
     * CRMコンタクト名（カナ）を返します。
     * 
     * @return CRMコンタクト名（カナ）
     */
    public String getCrmContactNameKana() {
        return crmContactNameKana;
    }

    /**
     * CRMコンタクト名（カナ）を設定します。
     * 
     * @param crmContactNameKana CRMコンタクト名（カナ）
     */
    public void setCrmContactNameKana(String crmContactNameKana) {
        this.crmContactNameKana = crmContactNameKana;
    }

    /**
     * CRMコンタクト略称を返します。
     * 
     * @return CRMコンタクト略称
     */
    public String getCrmContactShortName() {
        return crmContactShortName;
    }

    /**
     * CRMコンタクト略称を設定します。
     * 
     * @param crmContactShortName CRMコンタクト略称
     */
    public void setCrmContactShortName(String crmContactShortName) {
        this.crmContactShortName = crmContactShortName;
    }

    /**
     * CRMコンタクト検索名を返します。
     * 
     * @return CRMコンタクト検索名
     */
    public String getCrmContactSearchName() {
        return crmContactSearchName;
    }

    /**
     * CRMコンタクト検索名を設定します。
     * 
     * @param crmContactSearchName CRMコンタクト検索名
     */
    public void setCrmContactSearchName(String crmContactSearchName) {
        this.crmContactSearchName = crmContactSearchName;
    }

    /**
     * 性別を返します。
     * 
     * @return 性別
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性別を設定します。
     * 
     * @param sex 性別
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * タイプを返します。
     * 
     * @return タイプ
     */
    public String getCrmContactType() {
        return crmContactType;
    }

    /**
     * タイプを設定します。
     * 
     * @param crmContactType タイプ
     */
    public void setCrmContactType(String crmContactType) {
        this.crmContactType = crmContactType;
    }

    /**
     * 所属を返します。
     * 
     * @return 所属
     */
    public String getBelong() {
        return belong;
    }

    /**
     * 所属を設定します。
     * 
     * @param belong 所属
     */
    public void setBelong(String belong) {
        this.belong = belong;
    }

    /**
     * 役職を返します。
     * 
     * @return 役職
     */
    public String getPost() {
        return post;
    }

    /**
     * 役職を設定します。
     * 
     * @param post 役職
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * キーパーソンを返します。
     * 
     * @return キーパーソン
     */
    public String getKeyPerson() {
        return keyPerson;
    }

    /**
     * キーパーソンを設定します。
     * 
     * @param keyPerson キーパーソン
     */
    public void setKeyPerson(String keyPerson) {
        this.keyPerson = keyPerson;
    }

    /**
     * 生年月日を返します。
     * 
     * @return 生年月日
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * 生年月日を設定します。
     * 
     * @param birthDate 生年月日
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 出身地を返します。
     * 
     * @return 出身地
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * 出身地を設定します。
     * 
     * @param hometown 出身地
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     * 国コードを返します。
     * 
     * @return 国コード
     */
    public String getCountryCd() {
        return countryCd;
    }

    /**
     * 国コードを設定します。
     * 
     * @param countryCd 国コード
     */
    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

    /**
     * 郵便番号を返します。
     * 
     * @return 郵便番号
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 郵便番号を設定します。
     * 
     * @param zipCode 郵便番号
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 住所１を返します。
     * 
     * @return 住所１
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * 住所１を設定します。
     * 
     * @param address1 住所１
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * 住所２を返します。
     * 
     * @return 住所２
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * 住所２を設定します。
     * 
     * @param address2 住所２
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * 住所３を返します。
     * 
     * @return 住所３
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * 住所３を設定します。
     * 
     * @param address3 住所３
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * 電話番号を返します。
     * 
     * @return 電話番号
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * 電話番号を設定します。
     * 
     * @param telephoneNumber 電話番号
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * 内線番号を返します。
     * 
     * @return 内線番号
     */
    public String getExtensionNumber() {
        return extensionNumber;
    }

    /**
     * 内線番号を設定します。
     * 
     * @param extensionNumber 内線番号
     */
    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    /**
     * FAX番号を返します。
     * 
     * @return FAX番号
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * FAX番号を設定します。
     * 
     * @param faxNumber FAX番号
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * 内線FAX番号を返します。
     * 
     * @return 内線FAX番号
     */
    public String getExtensionFaxNumber() {
        return extensionFaxNumber;
    }

    /**
     * 内線FAX番号を設定します。
     * 
     * @param extensionFaxNumber 内線FAX番号
     */
    public void setExtensionFaxNumber(String extensionFaxNumber) {
        this.extensionFaxNumber = extensionFaxNumber;
    }

    /**
     * 携帯番号を返します。
     * 
     * @return 携帯番号
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * 携帯番号を設定します。
     * 
     * @param mobileNumber 携帯番号
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * メールアドレス１を返します。
     * 
     * @return メールアドレス１
     */
    public String getEmailAddress1() {
        return emailAddress1;
    }

    /**
     * メールアドレス１を設定します。
     * 
     * @param emailAddress1 メールアドレス１
     */
    public void setEmailAddress1(String emailAddress1) {
        this.emailAddress1 = emailAddress1;
    }

    /**
     * メールアドレス２を返します。
     * 
     * @return メールアドレス２
     */
    public String getEmailAddress2() {
        return emailAddress2;
    }

    /**
     * メールアドレス２を設定します。
     * 
     * @param emailAddress2 メールアドレス２
     */
    public void setEmailAddress2(String emailAddress2) {
        this.emailAddress2 = emailAddress2;
    }

    /**
     * 携帯メールアドレスを返します。
     * 
     * @return 携帯メールアドレス
     */
    public String getMobileEmailAddress() {
        return mobileEmailAddress;
    }

    /**
     * 携帯メールアドレスを設定します。
     * 
     * @param mobileEmailAddress 携帯メールアドレス
     */
    public void setMobileEmailAddress(String mobileEmailAddress) {
        this.mobileEmailAddress = mobileEmailAddress;
    }

    /**
     * URLを返します。
     * 
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * URLを設定します。
     * 
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 備考を返します。
     * 
     * @return 備考
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 備考を設定します。
     * 
     * @param notes 備考
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * 重要度を返します。
     * 
     * @return 重要度
     */
    public String getImportantLevel() {
        return importantLevel;
    }

    /**
     * 重要度を設定します。
     * 
     * @param importantLevel 重要度
     */
    public void setImportantLevel(String importantLevel) {
        this.importantLevel = importantLevel;
    }

    /**
     * 新CRMコンタクトコードを返します。
     * 
     * @return 新CRMコンタクトコード
     */
    public String getNewCrmContactCd() {
        return newCrmContactCd;
    }

    /**
     * 新CRMコンタクトコードを設定します。
     * 
     * @param newCrmContactCd 新CRMコンタクトコード
     */
    public void setNewCrmContactCd(String newCrmContactCd) {
        this.newCrmContactCd = newCrmContactCd;
    }

    /**
     * ダミーフラグを返します。
     * 
     * @return ダミーフラグ
     */
    public boolean isDummyFlag() {
        return dummyFlag;
    }

    /**
     * ダミーフラグを設定します。
     * 
     * @param dummyFlag ダミーフラグ
     */
    public void setDummyFlag(boolean dummyFlag) {
        this.dummyFlag = dummyFlag;
    }

    /**
     * カスタムフィールド1を返します。
     * 
     * @return カスタムフィールド1
     */
    public String getCustomField1() {
        return customField1;
    }

    /**
     * カスタムフィールド1を設定します。
     * 
     * @param customField1 カスタムフィールド1
     */
    public void setCustomField1(String customField1) {
        this.customField1 = customField1;
    }

    /**
     * カスタムフィールド2を返します。
     * 
     * @return カスタムフィールド2
     */
    public String getCustomField2() {
        return customField2;
    }

    /**
     * カスタムフィールド2を設定します。
     * 
     * @param customField2 カスタムフィールド2
     */
    public void setCustomField2(String customField2) {
        this.customField2 = customField2;
    }

    /**
     * カスタムフィールド3を返します。
     * 
     * @return カスタムフィールド3
     */
    public String getCustomField3() {
        return customField3;
    }

    /**
     * カスタムフィールド3を設定します。
     * 
     * @param customField3 カスタムフィールド3
     */
    public void setCustomField3(String customField3) {
        this.customField3 = customField3;
    }

    /**
     * カスタムフィールド4を返します。
     * 
     * @return カスタムフィールド4
     */
    public String getCustomField4() {
        return customField4;
    }

    /**
     * カスタムフィールド4を設定します。
     * 
     * @param customField4 カスタムフィールド4
     */
    public void setCustomField4(String customField4) {
        this.customField4 = customField4;
    }

    /**
     * カスタムフィールド5を返します。
     * 
     * @return カスタムフィールド5
     */
    public String getCustomField5() {
        return customField5;
    }

    /**
     * カスタムフィールド5を設定します。
     * 
     * @param customField5 カスタムフィールド5
     */
    public void setCustomField5(String customField5) {
        this.customField5 = customField5;
    }

    /**
     * カスタムフィールド6を返します。
     * 
     * @return カスタムフィールド6
     */
    public String getCustomField6() {
        return customField6;
    }

    /**
     * カスタムフィールド6を設定します。
     * 
     * @param customField6 カスタムフィールド6
     */
    public void setCustomField6(String customField6) {
        this.customField6 = customField6;
    }

    /**
     * カスタムフィールド7を返します。
     * 
     * @return カスタムフィールド7
     */
    public String getCustomField7() {
        return customField7;
    }

    /**
     * カスタムフィールド7を設定します。
     * 
     * @param customField7 カスタムフィールド7
     */
    public void setCustomField7(String customField7) {
        this.customField7 = customField7;
    }

    /**
     * カスタムフィールド8を返します。
     * 
     * @return カスタムフィールド8
     */
    public String getCustomField8() {
        return customField8;
    }

    /**
     * カスタムフィールド8を設定します。
     * 
     * @param customField8 カスタムフィールド8
     */
    public void setCustomField8(String customField8) {
        this.customField8 = customField8;
    }

    /**
     * カスタムフィールド9を返します。
     * 
     * @return カスタムフィールド9
     */
    public String getCustomField9() {
        return customField9;
    }

    /**
     * カスタムフィールド9を設定します。
     * 
     * @param customField9 カスタムフィールド9
     */
    public void setCustomField9(String customField9) {
        this.customField9 = customField9;
    }

    /**
     * カスタムフィールド10を返します。
     * 
     * @return カスタムフィールド10
     */
    public String getCustomField10() {
        return customField10;
    }

    /**
     * カスタムフィールド10を設定します。
     * 
     * @param customField10 カスタムフィールド10
     */
    public void setCustomField10(String customField10) {
        this.customField10 = customField10;
    }

    /**
     * カスタムフィールド11を返します。
     * 
     * @return カスタムフィールド11
     */
    public String getCustomField11() {
        return customField11;
    }

    /**
     * カスタムフィールド11を設定します。
     * 
     * @param customField11 カスタムフィールド11
     */
    public void setCustomField11(String customField11) {
        this.customField11 = customField11;
    }

    /**
     * カスタムフィールド12を返します。
     * 
     * @return カスタムフィールド12
     */
    public String getCustomField12() {
        return customField12;
    }

    /**
     * カスタムフィールド12を設定します。
     * 
     * @param customField12 カスタムフィールド12
     */
    public void setCustomField12(String customField12) {
        this.customField12 = customField12;
    }

    /**
     * カスタムフィールド13を返します。
     * 
     * @return カスタムフィールド13
     */
    public String getCustomField13() {
        return customField13;
    }

    /**
     * カスタムフィールド13を設定します。
     * 
     * @param customField13 カスタムフィールド13
     */
    public void setCustomField13(String customField13) {
        this.customField13 = customField13;
    }

    /**
     * カスタムフィールド14を返します。
     * 
     * @return カスタムフィールド14
     */
    public String getCustomField14() {
        return customField14;
    }

    /**
     * カスタムフィールド14を設定します。
     * 
     * @param customField14 カスタムフィールド14
     */
    public void setCustomField14(String customField14) {
        this.customField14 = customField14;
    }

    /**
     * カスタムフィールド15を返します。
     * 
     * @return カスタムフィールド15
     */
    public String getCustomField15() {
        return customField15;
    }

    /**
     * カスタムフィールド15を設定します。
     * 
     * @param customField15 カスタムフィールド15
     */
    public void setCustomField15(String customField15) {
        this.customField15 = customField15;
    }

    /**
     * カスタムフィールド16を返します。
     * 
     * @return カスタムフィールド16
     */
    public String getCustomField16() {
        return customField16;
    }

    /**
     * カスタムフィールド16を設定します。
     * 
     * @param customField16 カスタムフィールド16
     */
    public void setCustomField16(String customField16) {
        this.customField16 = customField16;
    }

    /**
     * カスタムフィールド17を返します。
     * 
     * @return カスタムフィールド17
     */
    public String getCustomField17() {
        return customField17;
    }

    /**
     * カスタムフィールド17を設定します。
     * 
     * @param customField17 カスタムフィールド17
     */
    public void setCustomField17(String customField17) {
        this.customField17 = customField17;
    }

    /**
     * カスタムフィールド18を返します。
     * 
     * @return カスタムフィールド18
     */
    public String getCustomField18() {
        return customField18;
    }

    /**
     * カスタムフィールド18を設定します。
     * 
     * @param customField18 カスタムフィールド18
     */
    public void setCustomField18(String customField18) {
        this.customField18 = customField18;
    }

    /**
     * カスタムフィールド19を返します。
     * 
     * @return カスタムフィールド19
     */
    public String getCustomField19() {
        return customField19;
    }

    /**
     * カスタムフィールド19を設定します。
     * 
     * @param customField19 カスタムフィールド19
     */
    public void setCustomField19(String customField19) {
        this.customField19 = customField19;
    }

    /**
     * カスタムフィールド20を返します。
     * 
     * @return カスタムフィールド20
     */
    public String getCustomField20() {
        return customField20;
    }

    /**
     * カスタムフィールド20を設定します。
     * 
     * @param customField20 カスタムフィールド20
     */
    public void setCustomField20(String customField20) {
        this.customField20 = customField20;
    }

    /**
     * カスタムフィールド21を返します。
     * 
     * @return カスタムフィールド21
     */
    public String getCustomField21() {
        return customField21;
    }

    /**
     * カスタムフィールド21を設定します。
     * 
     * @param customField21 カスタムフィールド21
     */
    public void setCustomField21(String customField21) {
        this.customField21 = customField21;
    }

    /**
     * カスタムフィールド22を返します。
     * 
     * @return カスタムフィールド22
     */
    public String getCustomField22() {
        return customField22;
    }

    /**
     * カスタムフィールド22を設定します。
     * 
     * @param customField22 カスタムフィールド22
     */
    public void setCustomField22(String customField22) {
        this.customField22 = customField22;
    }

    /**
     * カスタムフィールド23を返します。
     * 
     * @return カスタムフィールド23
     */
    public String getCustomField23() {
        return customField23;
    }

    /**
     * カスタムフィールド23を設定します。
     * 
     * @param customField23 カスタムフィールド23
     */
    public void setCustomField23(String customField23) {
        this.customField23 = customField23;
    }

    /**
     * カスタムフィールド24を返します。
     * 
     * @return カスタムフィールド24
     */
    public String getCustomField24() {
        return customField24;
    }

    /**
     * カスタムフィールド24を設定します。
     * 
     * @param customField24 カスタムフィールド24
     */
    public void setCustomField24(String customField24) {
        this.customField24 = customField24;
    }

    /**
     * カスタムフィールド25を返します。
     * 
     * @return カスタムフィールド25
     */
    public String getCustomField25() {
        return customField25;
    }

    /**
     * カスタムフィールド25を設定します。
     * 
     * @param customField25 カスタムフィールド25
     */
    public void setCustomField25(String customField25) {
        this.customField25 = customField25;
    }

    /**
     * カスタムフィールド26を返します。
     * 
     * @return カスタムフィールド26
     */
    public String getCustomField26() {
        return customField26;
    }

    /**
     * カスタムフィールド26を設定します。
     * 
     * @param customField26 カスタムフィールド26
     */
    public void setCustomField26(String customField26) {
        this.customField26 = customField26;
    }

    /**
     * カスタムフィールド27を返します。
     * 
     * @return カスタムフィールド27
     */
    public String getCustomField27() {
        return customField27;
    }

    /**
     * カスタムフィールド27を設定します。
     * 
     * @param customField27 カスタムフィールド27
     */
    public void setCustomField27(String customField27) {
        this.customField27 = customField27;
    }

    /**
     * カスタムフィールド28を返します。
     * 
     * @return カスタムフィールド28
     */
    public String getCustomField28() {
        return customField28;
    }

    /**
     * カスタムフィールド28を設定します。
     * 
     * @param customField28 カスタムフィールド28
     */
    public void setCustomField28(String customField28) {
        this.customField28 = customField28;
    }

    /**
     * カスタムフィールド29を返します。
     * 
     * @return カスタムフィールド29
     */
    public String getCustomField29() {
        return customField29;
    }

    /**
     * カスタムフィールド29を設定します。
     * 
     * @param customField29 カスタムフィールド29
     */
    public void setCustomField29(String customField29) {
        this.customField29 = customField29;
    }

    /**
     * カスタムフィールド30を返します。
     * 
     * @return カスタムフィールド30
     */
    public String getCustomField30() {
        return customField30;
    }

    /**
     * カスタムフィールド30を設定します。
     * 
     * @param customField30 カスタムフィールド30
     */
    public void setCustomField30(String customField30) {
        this.customField30 = customField30;
    }

    /**
     * カスタムフィールド31を返します。
     * 
     * @return カスタムフィールド31
     */
    public String getCustomField31() {
        return customField31;
    }

    /**
     * カスタムフィールド31を設定します。
     * 
     * @param customField31 カスタムフィールド31
     */
    public void setCustomField31(String customField31) {
        this.customField31 = customField31;
    }

    /**
     * カスタムフィールド32を返します。
     * 
     * @return カスタムフィールド32
     */
    public String getCustomField32() {
        return customField32;
    }

    /**
     * カスタムフィールド32を設定します。
     * 
     * @param customField32 カスタムフィールド32
     */
    public void setCustomField32(String customField32) {
        this.customField32 = customField32;
    }

    /**
     * カスタムフィールド33を返します。
     * 
     * @return カスタムフィールド33
     */
    public String getCustomField33() {
        return customField33;
    }

    /**
     * カスタムフィールド33を設定します。
     * 
     * @param customField33 カスタムフィールド33
     */
    public void setCustomField33(String customField33) {
        this.customField33 = customField33;
    }

    /**
     * カスタムフィールド34を返します。
     * 
     * @return カスタムフィールド34
     */
    public String getCustomField34() {
        return customField34;
    }

    /**
     * カスタムフィールド34を設定します。
     * 
     * @param customField34 カスタムフィールド34
     */
    public void setCustomField34(String customField34) {
        this.customField34 = customField34;
    }

    /**
     * カスタムフィールド35を返します。
     * 
     * @return カスタムフィールド35
     */
    public String getCustomField35() {
        return customField35;
    }

    /**
     * カスタムフィールド35を設定します。
     * 
     * @param customField35 カスタムフィールド35
     */
    public void setCustomField35(String customField35) {
        this.customField35 = customField35;
    }

    /**
     * カスタムフィールド36を返します。
     * 
     * @return カスタムフィールド36
     */
    public String getCustomField36() {
        return customField36;
    }

    /**
     * カスタムフィールド36を設定します。
     * 
     * @param customField36 カスタムフィールド36
     */
    public void setCustomField36(String customField36) {
        this.customField36 = customField36;
    }

    /**
     * カスタムフィールド37を返します。
     * 
     * @return カスタムフィールド37
     */
    public String getCustomField37() {
        return customField37;
    }

    /**
     * カスタムフィールド37を設定します。
     * 
     * @param customField37 カスタムフィールド37
     */
    public void setCustomField37(String customField37) {
        this.customField37 = customField37;
    }

    /**
     * カスタムフィールド38を返します。
     * 
     * @return カスタムフィールド38
     */
    public String getCustomField38() {
        return customField38;
    }

    /**
     * カスタムフィールド38を設定します。
     * 
     * @param customField38 カスタムフィールド38
     */
    public void setCustomField38(String customField38) {
        this.customField38 = customField38;
    }

    /**
     * カスタムフィールド39を返します。
     * 
     * @return カスタムフィールド39
     */
    public String getCustomField39() {
        return customField39;
    }

    /**
     * カスタムフィールド39を設定します。
     * 
     * @param customField39 カスタムフィールド39
     */
    public void setCustomField39(String customField39) {
        this.customField39 = customField39;
    }

    /**
     * カスタムフィールド40を返します。
     * 
     * @return カスタムフィールド40
     */
    public String getCustomField40() {
        return customField40;
    }

    /**
     * カスタムフィールド40を設定します。
     * 
     * @param customField40 カスタムフィールド40
     */
    public void setCustomField40(String customField40) {
        this.customField40 = customField40;
    }

    /**
     * カスタムフィールド41を返します。
     * 
     * @return カスタムフィールド41
     */
    public String getCustomField41() {
        return customField41;
    }

    /**
     * カスタムフィールド41を設定します。
     * 
     * @param customField41 カスタムフィールド41
     */
    public void setCustomField41(String customField41) {
        this.customField41 = customField41;
    }

    /**
     * カスタムフィールド42を返します。
     * 
     * @return カスタムフィールド42
     */
    public String getCustomField42() {
        return customField42;
    }

    /**
     * カスタムフィールド42を設定します。
     * 
     * @param customField42 カスタムフィールド42
     */
    public void setCustomField42(String customField42) {
        this.customField42 = customField42;
    }

    /**
     * カスタムフィールド43を返します。
     * 
     * @return カスタムフィールド43
     */
    public String getCustomField43() {
        return customField43;
    }

    /**
     * カスタムフィールド43を設定します。
     * 
     * @param customField43 カスタムフィールド43
     */
    public void setCustomField43(String customField43) {
        this.customField43 = customField43;
    }

    /**
     * カスタムフィールド44を返します。
     * 
     * @return カスタムフィールド44
     */
    public String getCustomField44() {
        return customField44;
    }

    /**
     * カスタムフィールド44を設定します。
     * 
     * @param customField44 カスタムフィールド44
     */
    public void setCustomField44(String customField44) {
        this.customField44 = customField44;
    }

    /**
     * カスタムフィールド45を返します。
     * 
     * @return カスタムフィールド45
     */
    public String getCustomField45() {
        return customField45;
    }

    /**
     * カスタムフィールド45を設定します。
     * 
     * @param customField45 カスタムフィールド45
     */
    public void setCustomField45(String customField45) {
        this.customField45 = customField45;
    }

    /**
     * カスタムフィールド46を返します。
     * 
     * @return カスタムフィールド46
     */
    public String getCustomField46() {
        return customField46;
    }

    /**
     * カスタムフィールド46を設定します。
     * 
     * @param customField46 カスタムフィールド46
     */
    public void setCustomField46(String customField46) {
        this.customField46 = customField46;
    }

    /**
     * カスタムフィールド47を返します。
     * 
     * @return カスタムフィールド47
     */
    public String getCustomField47() {
        return customField47;
    }

    /**
     * カスタムフィールド47を設定します。
     * 
     * @param customField47 カスタムフィールド47
     */
    public void setCustomField47(String customField47) {
        this.customField47 = customField47;
    }

    /**
     * カスタムフィールド48を返します。
     * 
     * @return カスタムフィールド48
     */
    public String getCustomField48() {
        return customField48;
    }

    /**
     * カスタムフィールド48を設定します。
     * 
     * @param customField48 カスタムフィールド48
     */
    public void setCustomField48(String customField48) {
        this.customField48 = customField48;
    }

    /**
     * カスタムフィールド49を返します。
     * 
     * @return カスタムフィールド49
     */
    public String getCustomField49() {
        return customField49;
    }

    /**
     * カスタムフィールド49を設定します。
     * 
     * @param customField49 カスタムフィールド49
     */
    public void setCustomField49(String customField49) {
        this.customField49 = customField49;
    }

    /**
     * カスタムフィールド50を返します。
     * 
     * @return カスタムフィールド50
     */
    public String getCustomField50() {
        return customField50;
    }

    /**
     * カスタムフィールド50を設定します。
     * 
     * @param customField50 カスタムフィールド50
     */
    public void setCustomField50(String customField50) {
        this.customField50 = customField50;
    }

    /**
     * カスタムフィールド51を返します。
     * 
     * @return カスタムフィールド51
     */
    public String getCustomField51() {
        return customField51;
    }

    /**
     * カスタムフィールド51を設定します。
     * 
     * @param customField51 カスタムフィールド51
     */
    public void setCustomField51(String customField51) {
        this.customField51 = customField51;
    }

    /**
     * カスタムフィールド52を返します。
     * 
     * @return カスタムフィールド52
     */
    public String getCustomField52() {
        return customField52;
    }

    /**
     * カスタムフィールド52を設定します。
     * 
     * @param customField52 カスタムフィールド52
     */
    public void setCustomField52(String customField52) {
        this.customField52 = customField52;
    }

    /**
     * カスタムフィールド53を返します。
     * 
     * @return カスタムフィールド53
     */
    public String getCustomField53() {
        return customField53;
    }

    /**
     * カスタムフィールド53を設定します。
     * 
     * @param customField53 カスタムフィールド53
     */
    public void setCustomField53(String customField53) {
        this.customField53 = customField53;
    }

    /**
     * カスタムフィールド54を返します。
     * 
     * @return カスタムフィールド54
     */
    public String getCustomField54() {
        return customField54;
    }

    /**
     * カスタムフィールド54を設定します。
     * 
     * @param customField54 カスタムフィールド54
     */
    public void setCustomField54(String customField54) {
        this.customField54 = customField54;
    }

    /**
     * カスタムフィールド55を返します。
     * 
     * @return カスタムフィールド55
     */
    public String getCustomField55() {
        return customField55;
    }

    /**
     * カスタムフィールド55を設定します。
     * 
     * @param customField55 カスタムフィールド55
     */
    public void setCustomField55(String customField55) {
        this.customField55 = customField55;
    }

    /**
     * カスタムフィールド56を返します。
     * 
     * @return カスタムフィールド56
     */
    public String getCustomField56() {
        return customField56;
    }

    /**
     * カスタムフィールド56を設定します。
     * 
     * @param customField56 カスタムフィールド56
     */
    public void setCustomField56(String customField56) {
        this.customField56 = customField56;
    }

    /**
     * カスタムフィールド57を返します。
     * 
     * @return カスタムフィールド57
     */
    public String getCustomField57() {
        return customField57;
    }

    /**
     * カスタムフィールド57を設定します。
     * 
     * @param customField57 カスタムフィールド57
     */
    public void setCustomField57(String customField57) {
        this.customField57 = customField57;
    }

    /**
     * カスタムフィールド58を返します。
     * 
     * @return カスタムフィールド58
     */
    public String getCustomField58() {
        return customField58;
    }

    /**
     * カスタムフィールド58を設定します。
     * 
     * @param customField58 カスタムフィールド58
     */
    public void setCustomField58(String customField58) {
        this.customField58 = customField58;
    }

    /**
     * カスタムフィールド59を返します。
     * 
     * @return カスタムフィールド59
     */
    public String getCustomField59() {
        return customField59;
    }

    /**
     * カスタムフィールド59を設定します。
     * 
     * @param customField59 カスタムフィールド59
     */
    public void setCustomField59(String customField59) {
        this.customField59 = customField59;
    }

    /**
     * カスタムフィールド60を返します。
     * 
     * @return カスタムフィールド60
     */
    public String getCustomField60() {
        return customField60;
    }

    /**
     * カスタムフィールド60を設定します。
     * 
     * @param customField60 カスタムフィールド60
     */
    public void setCustomField60(String customField60) {
        this.customField60 = customField60;
    }

    /**
     * 削除フラグを返します。
     * 
     * @return 削除フラグ
     */
    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 削除フラグを設定します。
     * 
     * @param deleteFlag 削除フラグ
     */
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * ソートキーを返します。
     * 
     * @return ソートキー
     */
    public Long getSortKey() {
        return sortKey;
    }

    /**
     * ソートキーを設定します。
     * 
     * @param sortKey ソートキー
     */
    public void setSortKey(Long sortKey) {
        this.sortKey = sortKey;
    }

    /**
     * バージョン番号を返します。
     * 
     * @return バージョン番号
     */
    public Long getVersionNo() {
        return versionNo;
    }

    /**
     * バージョン番号を設定します。
     * 
     * @param versionNo バージョン番号
     */
    public void setVersionNo(Long versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * 作成者を返します。
     * 
     * @return 作成者
     */
    public String getCreateUserCd() {
        return createUserCd;
    }

    /**
     * 作成者を設定します。
     * 
     * @param createUserCd 作成者
     */
    public void setCreateUserCd(String createUserCd) {
        this.createUserCd = createUserCd;
    }

    /**
     * 作成日を返します。
     * 
     * @return 作成日
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 作成日を設定します。
     * 
     * @param createDate 作成日
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 最終更新者を返します。
     * 
     * @return 最終更新者
     */
    public String getRecordUserCd() {
        return recordUserCd;
    }

    /**
     * 最終更新者を設定します。
     * 
     * @param recordUserCd 最終更新者
     */
    public void setRecordUserCd(String recordUserCd) {
        this.recordUserCd = recordUserCd;
    }

    /**
     * 最終更新日を返します。
     * 
     * @return 最終更新日
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * 最終更新日を設定します。
     * 
     * @param recordDate 最終更新日
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
    
    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(companyCd);
        builder.append(crmContactCd);
        builder.append(localeId);
        builder.append(termCd);
        return builder.toHashCode();
    }
    
    /**
     * 比較対象のエンティティとPKが同値であるか判定して返します。
     * 
     * @param <T>
     *            エンティティ
     * @param entity
     *            比較対象のエンティティ
     * @return 全てのPKが同値であれば{@code true}
     * @throws UnsupportedOperationException
     *             判定できなかった場合
     */
    public <T extends AbstractCrmCcContact> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}