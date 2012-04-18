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
 * AbstractImmDepartmentエンティティクラス
 * 
 */
@MappedSuperclass
@Table(name = "crm_cc_department_cmn")
@Generated(value = {
    "S2JDBC-Gen unknown",
    "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl" }, date = "2010/07/23 9:20:32")
public abstract class AbstractImmDepartment implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;

    /**
     * デフォルト・コンストラクタ
     */
    public AbstractImmDepartment() {
    }

    /** companyCdプロパティ */
    @Id
    @Column(name = "company_cd", columnDefinition = "varchar2(100)", nullable = false, unique = false)
    private String companyCd;

    /** departmentSetCdプロパティ */
    @Id
    @Column(name = "department_set_cd", columnDefinition = "varchar2(100)", nullable = false, unique = false)
    private String departmentSetCd;

    /** departmentCdプロパティ */
    @Id
    @Column(name = "department_cd", columnDefinition = "varchar2(100)", nullable = false, unique = false)
    private String departmentCd;

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

    /** departmentNameプロパティ */
    @Column(name = "department_name", columnDefinition = "varchar2(1000)", nullable = false, unique = false)
    private String departmentName;

    /** departmentShortNameプロパティ */
    @Column(name = "department_short_name", columnDefinition = "varchar2(1000)", nullable = true, unique = false)
    private String departmentShortName;

    /** departmentSearchNameプロパティ */
    @Column(name = "department_search_name", columnDefinition = "varchar2(1000)", nullable = true, unique = false)
    private String departmentSearchName;

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

    /** emailAddress1プロパティ */
    @Column(name = "email_address1", columnDefinition = "varchar2(256)", nullable = true, unique = false)
    private String emailAddress1;

    /** emailAddress2プロパティ */
    @Column(name = "email_address2", columnDefinition = "varchar2(256)", nullable = true, unique = false)
    private String emailAddress2;

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
     * companyCdを返します。
     * 
     * @return companyCd
     */
    public String getCompanyCd() {
        return companyCd;
    }

    /**
     * companyCdを設定します。
     * 
     * @param companyCd
     *            companyCd
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    /**
     * departmentSetCdを返します。
     * 
     * @return departmentSetCd
     */
    public String getDepartmentSetCd() {
        return departmentSetCd;
    }

    /**
     * departmentSetCdを設定します。
     * 
     * @param departmentSetCd
     *            departmentSetCd
     */
    public void setDepartmentSetCd(String departmentSetCd) {
        this.departmentSetCd = departmentSetCd;
    }

    /**
     * departmentCdを返します。
     * 
     * @return departmentCd
     */
    public String getDepartmentCd() {
        return departmentCd;
    }

    /**
     * departmentCdを設定します。
     * 
     * @param departmentCd
     *            departmentCd
     */
    public void setDepartmentCd(String departmentCd) {
        this.departmentCd = departmentCd;
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
     * departmentNameを返します。
     * 
     * @return departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * departmentNameを設定します。
     * 
     * @param departmentName
     *            departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * departmentShortNameを返します。
     * 
     * @return departmentShortName
     */
    public String getDepartmentShortName() {
        return departmentShortName;
    }

    /**
     * departmentShortNameを設定します。
     * 
     * @param departmentShortName
     *            departmentShortName
     */
    public void setDepartmentShortName(String departmentShortName) {
        this.departmentShortName = departmentShortName;
    }

    /**
     * departmentSearchNameを返します。
     * 
     * @return departmentSearchName
     */
    public String getDepartmentSearchName() {
        return departmentSearchName;
    }

    /**
     * departmentSearchNameを設定します。
     * 
     * @param departmentSearchName
     *            departmentSearchName
     */
    public void setDepartmentSearchName(String departmentSearchName) {
        this.departmentSearchName = departmentSearchName;
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
        builder.append(companyCd);
        builder.append(departmentSetCd);
        builder.append(departmentCd);
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
    public <T extends AbstractImmDepartment> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}