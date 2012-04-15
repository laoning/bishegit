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
 * 全銀_共通
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_bank_jp_cmn")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:02"
)
public abstract class AbstractCrmCcBankJpCmn implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcBankJpCmn() {
    }

    /** 会社コード */
    @Id
    @Column(name = "company_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String companyCd;

    /** 金融機関コード */
    @Id
    @Column(name = "bank_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String bankCd;

    /** 店舗コード */
    @Id
    @Column(name = "bank_branch_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String bankBranchCd;

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

    /** 金融機関名 */
    @Column(name = "bank_name",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String bankName;

    /** 金融機関名カナ */
    @Column(name = "bank_name_kana",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String bankNameKana;

    /** 店舗名 */
    @Column(name = "bank_branch_name",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String bankBranchName;

    /** 店舗名カナ */
    @Column(name = "bank_branch_name_kana",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String bankBranchNameKana;

    /** 郵便番号 */
    @Column(name = "zip_code",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String zipCode;

    /** 店舗所在地 */
    @Column(name = "address",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String address;

    /** 電話番号 */
    @Column(name = "telephone_number",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String telephoneNumber;

    /** 手形交換所番号 */
    @Column(name = "clearing_house_number",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String clearingHouseNumber;

    /** 並びコード */
    @Column(name = "sort_cd",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String sortCd;

    /** 内国為替制度加盟 */
    @Column(name = "is_bankers_association",
            columnDefinition = "varchar2(1) default '1' ", 
            nullable = false, unique = false)
    private boolean bankersAssociation;

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
     * 金融機関コードを返します。
     * 
     * @return 金融機関コード
     */
    public String getBankCd() {
        return bankCd;
    }

    /**
     * 金融機関コードを設定します。
     * 
     * @param bankCd 金融機関コード
     */
    public void setBankCd(String bankCd) {
        this.bankCd = bankCd;
    }

    /**
     * 店舗コードを返します。
     * 
     * @return 店舗コード
     */
    public String getBankBranchCd() {
        return bankBranchCd;
    }

    /**
     * 店舗コードを設定します。
     * 
     * @param bankBranchCd 店舗コード
     */
    public void setBankBranchCd(String bankBranchCd) {
        this.bankBranchCd = bankBranchCd;
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
     * 金融機関名を返します。
     * 
     * @return 金融機関名
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 金融機関名を設定します。
     * 
     * @param bankName 金融機関名
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * 金融機関名カナを返します。
     * 
     * @return 金融機関名カナ
     */
    public String getBankNameKana() {
        return bankNameKana;
    }

    /**
     * 金融機関名カナを設定します。
     * 
     * @param bankNameKana 金融機関名カナ
     */
    public void setBankNameKana(String bankNameKana) {
        this.bankNameKana = bankNameKana;
    }

    /**
     * 店舗名を返します。
     * 
     * @return 店舗名
     */
    public String getBankBranchName() {
        return bankBranchName;
    }

    /**
     * 店舗名を設定します。
     * 
     * @param bankBranchName 店舗名
     */
    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    /**
     * 店舗名カナを返します。
     * 
     * @return 店舗名カナ
     */
    public String getBankBranchNameKana() {
        return bankBranchNameKana;
    }

    /**
     * 店舗名カナを設定します。
     * 
     * @param bankBranchNameKana 店舗名カナ
     */
    public void setBankBranchNameKana(String bankBranchNameKana) {
        this.bankBranchNameKana = bankBranchNameKana;
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
     * 店舗所在地を返します。
     * 
     * @return 店舗所在地
     */
    public String getAddress() {
        return address;
    }

    /**
     * 店舗所在地を設定します。
     * 
     * @param address 店舗所在地
     */
    public void setAddress(String address) {
        this.address = address;
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
     * 手形交換所番号を返します。
     * 
     * @return 手形交換所番号
     */
    public String getClearingHouseNumber() {
        return clearingHouseNumber;
    }

    /**
     * 手形交換所番号を設定します。
     * 
     * @param clearingHouseNumber 手形交換所番号
     */
    public void setClearingHouseNumber(String clearingHouseNumber) {
        this.clearingHouseNumber = clearingHouseNumber;
    }

    /**
     * 並びコードを返します。
     * 
     * @return 並びコード
     */
    public String getSortCd() {
        return sortCd;
    }

    /**
     * 並びコードを設定します。
     * 
     * @param sortCd 並びコード
     */
    public void setSortCd(String sortCd) {
        this.sortCd = sortCd;
    }

    /**
     * 内国為替制度加盟を返します。
     * 
     * @return 内国為替制度加盟
     */
    public boolean isBankersAssociation() {
        return bankersAssociation;
    }

    /**
     * 内国為替制度加盟を設定します。
     * 
     * @param bankersAssociation 内国為替制度加盟
     */
    public void setBankersAssociation(boolean bankersAssociation) {
        this.bankersAssociation = bankersAssociation;
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
        builder.append(bankCd);
        builder.append(bankBranchCd);
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
    public <T extends AbstractCrmCcBankJpCmn> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}