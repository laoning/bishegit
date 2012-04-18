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

/**
 * ユーザ_共通
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_user_cmn")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:03"
)
public abstract class AbstractCrmCcUserCmn implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcUserCmn() {
    }

    /** ユーザコード */
    @Id
    @Column(name = "user_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String userCd;

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

    /** 性別 */
    @Column(name = "sex",
            columnDefinition = "varchar2(1)", 
            nullable = true, unique = false)
    private String sex;

    /** ユーザ名 */
    @Column(name = "user_name",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String userName;

    /** ユーザ検索名 */
    @Column(name = "user_search_name",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String userSearchName;

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

    /** 携帯電話番号 */
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
     * ユーザコードを返します。
     * 
     * @return ユーザコード
     */
    public String getUserCd() {
        return userCd;
    }

    /**
     * ユーザコードを設定します。
     * 
     * @param userCd ユーザコード
     */
    public void setUserCd(String userCd) {
        this.userCd = userCd;
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
     * ユーザ名を返します。
     * 
     * @return ユーザ名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザ名を設定します。
     * 
     * @param userName ユーザ名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * ユーザ検索名を返します。
     * 
     * @return ユーザ検索名
     */
    public String getUserSearchName() {
        return userSearchName;
    }

    /**
     * ユーザ検索名を設定します。
     * 
     * @param userSearchName ユーザ検索名
     */
    public void setUserSearchName(String userSearchName) {
        this.userSearchName = userSearchName;
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
     * 携帯電話番号を返します。
     * 
     * @return 携帯電話番号
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * 携帯電話番号を設定します。
     * 
     * @param mobileNumber 携帯電話番号
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
        builder.append(userCd);
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
    public <T extends AbstractCrmCcUserCmn> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}