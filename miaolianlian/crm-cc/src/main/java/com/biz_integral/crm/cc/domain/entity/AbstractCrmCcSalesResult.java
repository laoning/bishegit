/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.biz_integral.service.persistence.util.EntityUtil;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * 販売実績
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_sales_result")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:02"
)
public abstract class AbstractCrmCcSalesResult implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcSalesResult() {
    }

    /** ID */
    @Id
    @Column(name = "id",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = true)
    private String id;

    /** 会社コード */
    @Column(name = "company_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String companyCd;

    /** CRMアカウントコード */
    @Column(name = "crm_account_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String crmAccountCd;

    /** 案件コード */
    @Column(name = "proposal_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String proposalCd;

    /** 品目カテゴリコード */
    @Column(name = "item_category_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String itemCategoryCd;

    /** 販売実績年月日 */
    @Temporal(TemporalType.DATE)
    @Column(name = "sales_result_date",
            columnDefinition = "date", 
            nullable = false, unique = false)
    private Date salesResultDate;

    /** 販売実績金額 */
    @Column(name = "sales_result_amt",
            columnDefinition = "number(20,5)", 
            nullable = false, unique = false)
    private BigDecimal salesResultAmt;

    /** ユーザコード */
    @Column(name = "user_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String userCd;

    /** CRM領域コード */
    @Column(name = "crm_domain_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String crmDomainCd;

    /** 削除フラグ */
    @Column(name = "delete_flag",
            columnDefinition = "varchar2(1) default '0' ", 
            nullable = false, unique = false)
    private boolean deleteFlag;

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
     * IDを返します。
     * 
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * IDを設定します。
     * 
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * CRMアカウントコードを返します。
     * 
     * @return CRMアカウントコード
     */
    public String getCrmAccountCd() {
        return crmAccountCd;
    }

    /**
     * CRMアカウントコードを設定します。
     * 
     * @param crmAccountCd CRMアカウントコード
     */
    public void setCrmAccountCd(String crmAccountCd) {
        this.crmAccountCd = crmAccountCd;
    }

    /**
     * 案件コードを返します。
     * 
     * @return 案件コード
     */
    public String getProposalCd() {
        return proposalCd;
    }

    /**
     * 案件コードを設定します。
     * 
     * @param proposalCd 案件コード
     */
    public void setProposalCd(String proposalCd) {
        this.proposalCd = proposalCd;
    }

    /**
     * 品目カテゴリコードを返します。
     * 
     * @return 品目カテゴリコード
     */
    public String getItemCategoryCd() {
        return itemCategoryCd;
    }

    /**
     * 品目カテゴリコードを設定します。
     * 
     * @param itemCategoryCd 品目カテゴリコード
     */
    public void setItemCategoryCd(String itemCategoryCd) {
        this.itemCategoryCd = itemCategoryCd;
    }

    /**
     * 販売実績年月日を返します。
     * 
     * @return 販売実績年月日
     */
    public Date getSalesResultDate() {
        return salesResultDate;
    }

    /**
     * 販売実績年月日を設定します。
     * 
     * @param salesResultDate 販売実績年月日
     */
    public void setSalesResultDate(Date salesResultDate) {
        this.salesResultDate = salesResultDate;
    }

    /**
     * 販売実績金額を返します。
     * 
     * @return 販売実績金額
     */
    public BigDecimal getSalesResultAmt() {
        return salesResultAmt;
    }

    /**
     * 販売実績金額を設定します。
     * 
     * @param salesResultAmt 販売実績金額
     */
    public void setSalesResultAmt(BigDecimal salesResultAmt) {
        this.salesResultAmt = salesResultAmt;
    }

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
     * CRM領域コードを返します。
     * 
     * @return CRM領域コード
     */
    public String getCrmDomainCd() {
        return crmDomainCd;
    }

    /**
     * CRM領域コードを設定します。
     * 
     * @param crmDomainCd CRM領域コード
     */
    public void setCrmDomainCd(String crmDomainCd) {
        this.crmDomainCd = crmDomainCd;
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
        builder.append(id);
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
    public <T extends AbstractCrmCcSalesResult> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}