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
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.biz_integral.service.persistence.util.EntityUtil;

/**
 * 国
 * 
 */
@MappedSuperclass
@Table(name = "biz_country")
@Generated(value = {
    "S2JDBC-Gen unknown",
    "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl" }, date = "2010/07/23 9:20:25")
public abstract class AbstractBizCountry implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;

    /**
     * デフォルト・コンストラクタ
     */
    public AbstractBizCountry() {
    }

    /** 会社コード */
    @Id
    @Column(name = "company_cd", columnDefinition = "varchar2(100)", nullable = false, unique = false)
    private String companyCd;

    /** 国コード */
    @Id
    @Column(name = "country_cd", columnDefinition = "varchar2(100)", nullable = false, unique = false)
    private String countryCd;

    /** ロケールID */
    @Id
    @Column(name = "locale_id", columnDefinition = "varchar2(50)", nullable = false, unique = false)
    private String localeId;

    /** 国名 */
    @Column(name = "country_name", columnDefinition = "varchar2(1000)", nullable = true, unique = false)
    private String countryName;

    /** 国略称 */
    @Column(name = "country_short_name", columnDefinition = "varchar2(1000)", nullable = true, unique = false)
    private String countryShortName;

    /** 国検索名 */
    @Column(name = "country_search_name", columnDefinition = "varchar2(1000)", nullable = true, unique = false)
    private String countrySearchName;

    /** 国文字コード2 */
    @Column(name = "country_alpha2", columnDefinition = "varchar2(100)", nullable = true, unique = false)
    private String countryAlpha2;

    /** 国文字コード3 */
    @Column(name = "country_alpha3", columnDefinition = "varchar2(100)", nullable = true, unique = false)
    private String countryAlpha3;

    /** 備考 */
    @Column(name = "notes", columnDefinition = "varchar2(4000)", nullable = true, unique = false)
    private String notes;

    /** 削除フラグ */
    @Column(name = "delete_flag", columnDefinition = "varchar2(1)", nullable = false, unique = false)
    private boolean deleteFlag;

    /** ソートキー */
    @Column(name = "sort_key", columnDefinition = "number(15,0)", nullable = false, unique = false)
    private Long sortKey;

    /** バージョン番号 */
    @Version
    @Column(name = "version_no", columnDefinition = "number(18,0) default 1 ", nullable = false, unique = false)
    private Long versionNo;

    /** 作成者 */
    @Column(name = "create_user_cd", columnDefinition = "varchar2(100)", nullable = false, unique = false)
    private String createUserCd;

    /** 作成日 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", columnDefinition = "timestamp(6)", nullable = false, unique = false)
    private Date createDate;

    /** 最終更新者 */
    @Column(name = "record_user_cd", columnDefinition = "varchar2(100)", nullable = true, unique = false)
    private String recordUserCd;

    /** 最終更新日 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "record_date", columnDefinition = "timestamp(6)", nullable = true, unique = false)
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
     * @param companyCd
     *            会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
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
     * @param countryCd
     *            国コード
     */
    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
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
     * @param localeId
     *            ロケールID
     */
    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    /**
     * 国名を返します。
     * 
     * @return 国名
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 国名を設定します。
     * 
     * @param countryName
     *            国名
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 国略称を返します。
     * 
     * @return 国略称
     */
    public String getCountryShortName() {
        return countryShortName;
    }

    /**
     * 国略称を設定します。
     * 
     * @param countryShortName
     *            国略称
     */
    public void setCountryShortName(String countryShortName) {
        this.countryShortName = countryShortName;
    }

    /**
     * 国検索名を返します。
     * 
     * @return 国検索名
     */
    public String getCountrySearchName() {
        return countrySearchName;
    }

    /**
     * 国検索名を設定します。
     * 
     * @param countrySearchName
     *            国検索名
     */
    public void setCountrySearchName(String countrySearchName) {
        this.countrySearchName = countrySearchName;
    }

    /**
     * 国文字コード2を返します。
     * 
     * @return 国文字コード2
     */
    public String getCountryAlpha2() {
        return countryAlpha2;
    }

    /**
     * 国文字コード2を設定します。
     * 
     * @param countryAlpha2
     *            国文字コード2
     */
    public void setCountryAlpha2(String countryAlpha2) {
        this.countryAlpha2 = countryAlpha2;
    }

    /**
     * 国文字コード3を返します。
     * 
     * @return 国文字コード3
     */
    public String getCountryAlpha3() {
        return countryAlpha3;
    }

    /**
     * 国文字コード3を設定します。
     * 
     * @param countryAlpha3
     *            国文字コード3
     */
    public void setCountryAlpha3(String countryAlpha3) {
        this.countryAlpha3 = countryAlpha3;
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
     * @param notes
     *            備考
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
     * @param deleteFlag
     *            削除フラグ
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
     * @param sortKey
     *            ソートキー
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
     * @param versionNo
     *            バージョン番号
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
     * @param createUserCd
     *            作成者
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
     * @param createDate
     *            作成日
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
     * @param recordUserCd
     *            最終更新者
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
     * @param recordDate
     *            最終更新日
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
        builder.append(countryCd);
        builder.append(localeId);
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
    public <T extends AbstractBizCountry> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}