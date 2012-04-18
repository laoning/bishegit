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
 * パラメータ詳細
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_parameter_dtl")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:02"
)
public abstract class AbstractCrmCcParameterDtl implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcParameterDtl() {
    }

    /** 会社コード */
    @Id
    @Column(name = "company_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String companyCd;

    /** パラメータコード */
    @Id
    @Column(name = "parameter_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String parameterCd;

    /** 期間コード */
    @Id
    @Column(name = "term_cd",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String termCd;

    /** ロケールID */
    @Id
    @Column(name = "locale_id",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String localeId;

    /** パラメータ名 */
    @Column(name = "parameter_name",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String parameterName;

    /** 備考 */
    @Column(name = "notes",
            columnDefinition = "varchar2(4000)", 
            nullable = true, unique = false)
    private String notes;

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
     * パラメータコードを返します。
     * 
     * @return パラメータコード
     */
    public String getParameterCd() {
        return parameterCd;
    }

    /**
     * パラメータコードを設定します。
     * 
     * @param parameterCd パラメータコード
     */
    public void setParameterCd(String parameterCd) {
        this.parameterCd = parameterCd;
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
     * パラメータ名を返します。
     * 
     * @return パラメータ名
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * パラメータ名を設定します。
     * 
     * @param parameterName パラメータ名
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
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
        builder.append(parameterCd);
        builder.append(termCd);
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
    public <T extends AbstractCrmCcParameterDtl> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}