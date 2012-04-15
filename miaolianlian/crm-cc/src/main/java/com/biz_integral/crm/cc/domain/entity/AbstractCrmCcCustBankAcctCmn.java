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
 * 取引先口座_共通
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_cust_bank_acct_cmn")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:02"
)
public abstract class AbstractCrmCcCustBankAcctCmn implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcCustBankAcctCmn() {
    }

    /** 会社コード */
    @Id
    @Column(name = "company_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String companyCd;

    /** 取引先コード */
    @Id
    @Column(name = "customer_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String customerCd;

    /** 取引先口座ID */
    @Id
    @Column(name = "customer_bank_account_id",
            columnDefinition = "number(18,0)", 
            nullable = false, unique = false)
    private Long customerBankAccountId;

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

    /** 代表口座区分 */
    @Column(name = "main_bank_account_type",
            columnDefinition = "varchar2(100) default '0' ", 
            nullable = false, unique = false)
    private String mainBankAccountType;

    /** 仮想口座区分 */
    @Column(name = "virtual_bank_account_type",
            columnDefinition = "varchar2(100) default '0' ", 
            nullable = false, unique = false)
    private String virtualBankAccountType;

    /** 金融機関コード */
    @Column(name = "bank_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String bankCd;

    /** 店舗コード */
    @Column(name = "bank_branch_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String bankBranchCd;

    /** 預金種別 */
    @Column(name = "deposit_class",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String depositClass;

    /** 口座番号 */
    @Column(name = "bank_account_no",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String bankAccountNo;

    /** 口座名義人 */
    @Column(name = "bank_nominee_name",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String bankNomineeName;

    /** 通貨コード */
    @Column(name = "currency_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String currencyCd;

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
     * 取引先コードを返します。
     * 
     * @return 取引先コード
     */
    public String getCustomerCd() {
        return customerCd;
    }

    /**
     * 取引先コードを設定します。
     * 
     * @param customerCd 取引先コード
     */
    public void setCustomerCd(String customerCd) {
        this.customerCd = customerCd;
    }

    /**
     * 取引先口座IDを返します。
     * 
     * @return 取引先口座ID
     */
    public Long getCustomerBankAccountId() {
        return customerBankAccountId;
    }

    /**
     * 取引先口座IDを設定します。
     * 
     * @param customerBankAccountId 取引先口座ID
     */
    public void setCustomerBankAccountId(Long customerBankAccountId) {
        this.customerBankAccountId = customerBankAccountId;
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
     * 代表口座区分を返します。
     * 
     * @return 代表口座区分
     */
    public String getMainBankAccountType() {
        return mainBankAccountType;
    }

    /**
     * 代表口座区分を設定します。
     * 
     * @param mainBankAccountType 代表口座区分
     */
    public void setMainBankAccountType(String mainBankAccountType) {
        this.mainBankAccountType = mainBankAccountType;
    }

    /**
     * 仮想口座区分を返します。
     * 
     * @return 仮想口座区分
     */
    public String getVirtualBankAccountType() {
        return virtualBankAccountType;
    }

    /**
     * 仮想口座区分を設定します。
     * 
     * @param virtualBankAccountType 仮想口座区分
     */
    public void setVirtualBankAccountType(String virtualBankAccountType) {
        this.virtualBankAccountType = virtualBankAccountType;
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
     * 預金種別を返します。
     * 
     * @return 預金種別
     */
    public String getDepositClass() {
        return depositClass;
    }

    /**
     * 預金種別を設定します。
     * 
     * @param depositClass 預金種別
     */
    public void setDepositClass(String depositClass) {
        this.depositClass = depositClass;
    }

    /**
     * 口座番号を返します。
     * 
     * @return 口座番号
     */
    public String getBankAccountNo() {
        return bankAccountNo;
    }

    /**
     * 口座番号を設定します。
     * 
     * @param bankAccountNo 口座番号
     */
    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    /**
     * 口座名義人を返します。
     * 
     * @return 口座名義人
     */
    public String getBankNomineeName() {
        return bankNomineeName;
    }

    /**
     * 口座名義人を設定します。
     * 
     * @param bankNomineeName 口座名義人
     */
    public void setBankNomineeName(String bankNomineeName) {
        this.bankNomineeName = bankNomineeName;
    }

    /**
     * 通貨コードを返します。
     * 
     * @return 通貨コード
     */
    public String getCurrencyCd() {
        return currencyCd;
    }

    /**
     * 通貨コードを設定します。
     * 
     * @param currencyCd 通貨コード
     */
    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
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
        builder.append(customerCd);
        builder.append(customerBankAccountId);
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
    public <T extends AbstractCrmCcCustBankAcctCmn> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}