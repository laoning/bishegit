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
 * 市区町村_共通
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_skcs_cmn")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:02"
)
public abstract class AbstractCrmCcSkcsCmn implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcSkcsCmn() {
    }

    /** 都道府県コード */
    @Id
    @Column(name = "tdfk_cd",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String tdfkCd;

    /** 市区町村コード */
    @Id
    @Column(name = "skcs_cd",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String skcsCd;

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

    /** 政令指定都市フラグ */
    @Column(name = "major_city_flag",
            columnDefinition = "varchar2(1) default '0' ", 
            nullable = false, unique = false)
    private boolean majorCityFlag;

    /** 都道府県名 */
    @Column(name = "tdfk_address",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String tdfkAddress;

    /** 都道府県名カナ */
    @Column(name = "tdfk_address_kana",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String tdfkAddressKana;

    /** 市区町村名 */
    @Column(name = "skcs_address",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String skcsAddress;

    /** 市区町村名カナ */
    @Column(name = "skcs_address_kana",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String skcsAddressKana;

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
     * 都道府県コードを返します。
     * 
     * @return 都道府県コード
     */
    public String getTdfkCd() {
        return tdfkCd;
    }

    /**
     * 都道府県コードを設定します。
     * 
     * @param tdfkCd 都道府県コード
     */
    public void setTdfkCd(String tdfkCd) {
        this.tdfkCd = tdfkCd;
    }

    /**
     * 市区町村コードを返します。
     * 
     * @return 市区町村コード
     */
    public String getSkcsCd() {
        return skcsCd;
    }

    /**
     * 市区町村コードを設定します。
     * 
     * @param skcsCd 市区町村コード
     */
    public void setSkcsCd(String skcsCd) {
        this.skcsCd = skcsCd;
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
     * 政令指定都市フラグを返します。
     * 
     * @return 政令指定都市フラグ
     */
    public boolean isMajorCityFlag() {
        return majorCityFlag;
    }

    /**
     * 政令指定都市フラグを設定します。
     * 
     * @param majorCityFlag 政令指定都市フラグ
     */
    public void setMajorCityFlag(boolean majorCityFlag) {
        this.majorCityFlag = majorCityFlag;
    }

    /**
     * 都道府県名を返します。
     * 
     * @return 都道府県名
     */
    public String getTdfkAddress() {
        return tdfkAddress;
    }

    /**
     * 都道府県名を設定します。
     * 
     * @param tdfkAddress 都道府県名
     */
    public void setTdfkAddress(String tdfkAddress) {
        this.tdfkAddress = tdfkAddress;
    }

    /**
     * 都道府県名カナを返します。
     * 
     * @return 都道府県名カナ
     */
    public String getTdfkAddressKana() {
        return tdfkAddressKana;
    }

    /**
     * 都道府県名カナを設定します。
     * 
     * @param tdfkAddressKana 都道府県名カナ
     */
    public void setTdfkAddressKana(String tdfkAddressKana) {
        this.tdfkAddressKana = tdfkAddressKana;
    }

    /**
     * 市区町村名を返します。
     * 
     * @return 市区町村名
     */
    public String getSkcsAddress() {
        return skcsAddress;
    }

    /**
     * 市区町村名を設定します。
     * 
     * @param skcsAddress 市区町村名
     */
    public void setSkcsAddress(String skcsAddress) {
        this.skcsAddress = skcsAddress;
    }

    /**
     * 市区町村名カナを返します。
     * 
     * @return 市区町村名カナ
     */
    public String getSkcsAddressKana() {
        return skcsAddressKana;
    }

    /**
     * 市区町村名カナを設定します。
     * 
     * @param skcsAddressKana 市区町村名カナ
     */
    public void setSkcsAddressKana(String skcsAddressKana) {
        this.skcsAddressKana = skcsAddressKana;
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
        builder.append(tdfkCd);
        builder.append(skcsCd);
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
    public <T extends AbstractCrmCcSkcsCmn> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}