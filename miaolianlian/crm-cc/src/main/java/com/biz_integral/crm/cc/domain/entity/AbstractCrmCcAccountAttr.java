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
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * CRMアカウント属性
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_account_attr")
public abstract class AbstractCrmCcAccountAttr implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcAccountAttr() {
    }

    /** 会社コード */
    @Id
    @Column(name = "company_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String companyCd;

    /** 取引種別 */
    @Id
    @Column(name = "deal_class",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String dealClass;

    /** CRMアカウント分類コード */
    @Id
    @Column(name = "crm_account_class_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String crmAccountClassCd;

    /** 状況 */
    @Id
    @Column(name = "crm_account_status",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String crmAccountStatus;

    /** 画面メンテ対象 */
    @Column(name = "maintenance_target_flag",
            columnDefinition = "varchar2(1) default '0'", 
            nullable = true, unique = false)
    private boolean maintenanceTargetFlag;

    /** IF更新方法 */
    @Column(name = "if_update_way",
            columnDefinition = "varchar2(100)", 
            nullable = true, unique = false)
    private String ifUpdateWay;

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
     * 取引種別を返します。
     * 
     * @return 取引種別
     */
    public String getDealClass() {
        return dealClass;
    }

    /**
     * 取引種別を設定します。
     * 
     * @param dealClass 取引種別
     */
    public void setDealClass(String dealClass) {
        this.dealClass = dealClass;
    }

    /**
     * CRMアカウント分類コードを返します。
     * 
     * @return CRMアカウント分類コード
     */
    public String getCrmAccountClassCd() {
        return crmAccountClassCd;
    }

    /**
     * CRMアカウント分類コードを設定します。
     * 
     * @param crmAccountClassCd CRMアカウント分類コード
     */
    public void setCrmAccountClassCd(String crmAccountClassCd) {
        this.crmAccountClassCd = crmAccountClassCd;
    }

    /**
     * 状況を返します。
     * 
     * @return 状況
     */
    public String getCrmAccountStatus() {
        return crmAccountStatus;
    }

    /**
     * 状況を設定します。
     * 
     * @param crmAccountStatus 状況
     */
    public void setCrmAccountStatus(String crmAccountStatus) {
        this.crmAccountStatus = crmAccountStatus;
    }

    /**
     * 画面メンテ対象を返します。
     * 
     * @return 画面メンテ対象
     */
    public boolean isMaintenanceTargetFlag() {
        return maintenanceTargetFlag;
    }

    /**
     * 画面メンテ対象を設定します。
     * 
     * @param maintenanceTargetFlag 画面メンテ対象
     */
    public void setMaintenanceTargetFlag(boolean maintenanceTargetFlag) {
        this.maintenanceTargetFlag = maintenanceTargetFlag;
    }

    /**
     * IF更新方法を返します。
     * 
     * @return IF更新方法
     */
    public String getIfUpdateWay() {
        return ifUpdateWay;
    }

    /**
     * IF更新方法を設定します。
     * 
     * @param ifUpdateWay IF更新方法
     */
    public void setIfUpdateWay(String ifUpdateWay) {
        this.ifUpdateWay = ifUpdateWay;
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
        builder.append(dealClass);
        builder.append(crmAccountClassCd);
        builder.append(crmAccountStatus);
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
    public <T extends AbstractCrmCcAccountAttr> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}