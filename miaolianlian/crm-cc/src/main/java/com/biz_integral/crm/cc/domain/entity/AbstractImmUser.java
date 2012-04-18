/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.service.persistence.util.EntityUtil;

/**
 * AbstractImmUserエンティティクラス
 * 
 */
@MappedSuperclass
@Table(name = "crm_cc_user_cmn")
@Generated(value = {
    "S2JDBC-Gen unknown",
    "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl" }, date = "2010/08/03 13:58:36")
public abstract class AbstractImmUser implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;

    /**
     * デフォルト・コンストラクタ
     */
    public AbstractImmUser() {
    }

    /** userCdプロパティ */
    @Id
    @Column(name = "user_cd", columnDefinition = "varchar2(100)", nullable = false, unique = false)
    private String userCd;

    /** localeIdプロパティ */
    @Id
    @Column(name = "locale_id", columnDefinition = "varchar2(50)", nullable = false, unique = false)
    private String localeId;

    /** termCdプロパティ */
    @Id
    @Column(name = "term_cd", columnDefinition = "varchar2(50)", nullable = false, unique = false)
    private String termCd;

    /** startDateプロパティ */
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", columnDefinition = "date", nullable = false, unique = false)
    private Date startDate;

    /** endDateプロパティ */
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", columnDefinition = "date", nullable = false, unique = false)
    private Date endDate;

    /** sexプロパティ */
    @Column(name = "sex", columnDefinition = "varchar2(1)", nullable = true, unique = false)
    private String sex;

    /** userNameプロパティ */
    @Column(name = "user_name", columnDefinition = "varchar2(1000)", nullable = false, unique = false)
    private String userName;

    /** userSearchNameプロパティ */
    @Column(name = "user_search_name", columnDefinition = "varchar2(1000)", nullable = true, unique = false)
    private String userSearchName;

    /** countryCdプロパティ */
    @Column(name = "country_cd", columnDefinition = "varchar2(100)", nullable = true, unique = false)
    private String countryCd;

    /** zipCodeプロパティ */
    @Column(name = "zip_code", columnDefinition = "varchar2(50)", nullable = true, unique = false)
    private String zipCode;

    /** address1プロパティ */
    @Column(name = "address1", columnDefinition = "varchar2(1000)", nullable = true, unique = false)
    private String address1;

    /** address2プロパティ */
    @Column(name = "address2", columnDefinition = "varchar2(1000)", nullable = true, unique = false)
    private String address2;

    /** address3プロパティ */
    @Column(name = "address3", columnDefinition = "varchar2(1000)", nullable = true, unique = false)
    private String address3;

    /** telephoneNumberプロパティ */
    @Column(name = "telephone_number", columnDefinition = "varchar2(50)", nullable = true, unique = false)
    private String telephoneNumber;

    /** extensionNumberプロパティ */
    @Column(name = "extension_number", columnDefinition = "varchar2(50)", nullable = true, unique = false)
    private String extensionNumber;

    /** faxNumberプロパティ */
    @Column(name = "fax_number", columnDefinition = "varchar2(50)", nullable = true, unique = false)
    private String faxNumber;

    /** extensionFaxNumberプロパティ */
    @Column(name = "extension_fax_number", columnDefinition = "varchar2(50)", nullable = true, unique = false)
    private String extensionFaxNumber;

    /** mobileNumberプロパティ */
    @Column(name = "mobile_number", columnDefinition = "varchar2(50)", nullable = true, unique = false)
    private String mobileNumber;

    /** emailAddress1プロパティ */
    @Column(name = "email_address1", columnDefinition = "varchar2(256)", nullable = true, unique = false)
    private String emailAddress1;

    /** emailAddress2プロパティ */
    @Column(name = "email_address2", columnDefinition = "varchar2(256)", nullable = true, unique = false)
    private String emailAddress2;

    /** mobileEmailAddressプロパティ */
    @Column(name = "mobile_email_address", columnDefinition = "varchar2(256)", nullable = true, unique = false)
    private String mobileEmailAddress;

    /** urlプロパティ */
    @Column(name = "url", columnDefinition = "varchar2(1000)", nullable = true, unique = false)
    private String url;

    /** notesプロパティ */
    @Column(name = "notes", columnDefinition = "varchar2(4000)", nullable = true, unique = false)
    private String notes;

    /** deleteFlagプロパティ */
    @Column(name = "delete_flag", columnDefinition = "varchar2(1)", nullable = false, unique = false)
    private boolean deleteFlag;

    /** sortKeyプロパティ */
    @Column(name = "sort_key", columnDefinition = "number(15,0)", nullable = false, unique = false)
    private Long sortKey;

    /** createUserCdプロパティ */
    @Column(name = "create_user_cd", columnDefinition = "varchar2(100)", nullable = false, unique = false)
    private String createUserCd;

    /** createDateプロパティ */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", columnDefinition = "timestamp(6)", nullable = false, unique = false)
    private Date createDate;

    /** recordUserCdプロパティ */
    @Column(name = "record_user_cd", columnDefinition = "varchar2(100)", nullable = false, unique = false)
    private String recordUserCd;

    /** recordDateプロパティ */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "record_date", columnDefinition = "timestamp(6)", nullable = false, unique = false)
    private Date recordDate;

    /**
     * userCdを返します。
     * 
     * @return userCd
     */
    public String getUserCd() {
        return userCd;
    }

    /**
     * userCdを設定します。
     * 
     * @param userCd
     *            userCd
     */
    public void setUserCd(String userCd) {
        this.userCd = userCd;
    }

    /**
     * localeIdを返します。
     * 
     * @return localeId
     */
    public String getLocaleId() {
        return localeId;
    }

    /**
     * localeIdを設定します。
     * 
     * @param localeId
     *            localeId
     */
    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    /**
     * termCdを返します。
     * 
     * @return termCd
     */
    public String getTermCd() {
        return termCd;
    }

    /**
     * termCdを設定します。
     * 
     * @param termCd
     *            termCd
     */
    public void setTermCd(String termCd) {
        this.termCd = termCd;
    }

    /**
     * startDateを返します。
     * 
     * @return startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * startDateを設定します。
     * 
     * @param startDate
     *            startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * endDateを返します。
     * 
     * @return endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * endDateを設定します。
     * 
     * @param endDate
     *            endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * sexを返します。
     * 
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * sexを設定します。
     * 
     * @param sex
     *            sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * userNameを返します。
     * 
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * userNameを設定します。
     * 
     * @param userName
     *            userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * userSearchNameを返します。
     * 
     * @return userSearchName
     */
    public String getUserSearchName() {
        return userSearchName;
    }

    /**
     * userSearchNameを設定します。
     * 
     * @param userSearchName
     *            userSearchName
     */
    public void setUserSearchName(String userSearchName) {
        this.userSearchName = userSearchName;
    }

    /**
     * countryCdを返します。
     * 
     * @return countryCd
     */
    public String getCountryCd() {
        return countryCd;
    }

    /**
     * countryCdを設定します。
     * 
     * @param countryCd
     *            countryCd
     */
    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

    /**
     * zipCodeを返します。
     * 
     * @return zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * zipCodeを設定します。
     * 
     * @param zipCode
     *            zipCode
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * address1を返します。
     * 
     * @return address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * address1を設定します。
     * 
     * @param address1
     *            address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * address2を返します。
     * 
     * @return address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * address2を設定します。
     * 
     * @param address2
     *            address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * address3を返します。
     * 
     * @return address3
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * address3を設定します。
     * 
     * @param address3
     *            address3
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * telephoneNumberを返します。
     * 
     * @return telephoneNumber
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * telephoneNumberを設定します。
     * 
     * @param telephoneNumber
     *            telephoneNumber
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * extensionNumberを返します。
     * 
     * @return extensionNumber
     */
    public String getExtensionNumber() {
        return extensionNumber;
    }

    /**
     * extensionNumberを設定します。
     * 
     * @param extensionNumber
     *            extensionNumber
     */
    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    /**
     * faxNumberを返します。
     * 
     * @return faxNumber
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * faxNumberを設定します。
     * 
     * @param faxNumber
     *            faxNumber
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * extensionFaxNumberを返します。
     * 
     * @return extensionFaxNumber
     */
    public String getExtensionFaxNumber() {
        return extensionFaxNumber;
    }

    /**
     * extensionFaxNumberを設定します。
     * 
     * @param extensionFaxNumber
     *            extensionFaxNumber
     */
    public void setExtensionFaxNumber(String extensionFaxNumber) {
        this.extensionFaxNumber = extensionFaxNumber;
    }

    /**
     * mobileNumberを返します。
     * 
     * @return mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * mobileNumberを設定します。
     * 
     * @param mobileNumber
     *            mobileNumber
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * emailAddress1を返します。
     * 
     * @return emailAddress1
     */
    public String getEmailAddress1() {
        return emailAddress1;
    }

    /**
     * emailAddress1を設定します。
     * 
     * @param emailAddress1
     *            emailAddress1
     */
    public void setEmailAddress1(String emailAddress1) {
        this.emailAddress1 = emailAddress1;
    }

    /**
     * emailAddress2を返します。
     * 
     * @return emailAddress2
     */
    public String getEmailAddress2() {
        return emailAddress2;
    }

    /**
     * emailAddress2を設定します。
     * 
     * @param emailAddress2
     *            emailAddress2
     */
    public void setEmailAddress2(String emailAddress2) {
        this.emailAddress2 = emailAddress2;
    }

    /**
     * mobileEmailAddressを返します。
     * 
     * @return mobileEmailAddress
     */
    public String getMobileEmailAddress() {
        return mobileEmailAddress;
    }

    /**
     * mobileEmailAddressを設定します。
     * 
     * @param mobileEmailAddress
     *            mobileEmailAddress
     */
    public void setMobileEmailAddress(String mobileEmailAddress) {
        this.mobileEmailAddress = mobileEmailAddress;
    }

    /**
     * urlを返します。
     * 
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * urlを設定します。
     * 
     * @param url
     *            url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * notesを返します。
     * 
     * @return notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * notesを設定します。
     * 
     * @param notes
     *            notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * deleteFlagを返します。
     * 
     * @return deleteFlag
     */
    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    /**
     * deleteFlagを設定します。
     * 
     * @param deleteFlag
     *            deleteFlag
     */
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * sortKeyを返します。
     * 
     * @return sortKey
     */
    public Long getSortKey() {
        return sortKey;
    }

    /**
     * sortKeyを設定します。
     * 
     * @param sortKey
     *            sortKey
     */
    public void setSortKey(Long sortKey) {
        this.sortKey = sortKey;
    }

    /**
     * createUserCdを返します。
     * 
     * @return createUserCd
     */
    public String getCreateUserCd() {
        return createUserCd;
    }

    /**
     * createUserCdを設定します。
     * 
     * @param createUserCd
     *            createUserCd
     */
    public void setCreateUserCd(String createUserCd) {
        this.createUserCd = createUserCd;
    }

    /**
     * createDateを返します。
     * 
     * @return createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * createDateを設定します。
     * 
     * @param createDate
     *            createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * recordUserCdを返します。
     * 
     * @return recordUserCd
     */
    public String getRecordUserCd() {
        return recordUserCd;
    }

    /**
     * recordUserCdを設定します。
     * 
     * @param recordUserCd
     *            recordUserCd
     */
    public void setRecordUserCd(String recordUserCd) {
        this.recordUserCd = recordUserCd;
    }

    /**
     * recordDateを返します。
     * 
     * @return recordDate
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * recordDateを設定します。
     * 
     * @param recordDate
     *            recordDate
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
    public <T extends AbstractImmUser> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}