/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.biz_integral.service.persistence.util.EntityUtil;
import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * 類義語パターン
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_synonym_pattern")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/10/25 16:14:44"
)
public abstract class AbstractCrmCcSynonymPattern implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcSynonymPattern() {
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

    /** ロケールID */
    @Column(name = "locale_id",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String localeId;

    /** 類義語区分 */
    @Column(name = "synonym_type",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String synonymType;

    /** コード */
    @Column(name = "cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String cd;

    /** 名称 */
    @Column(name = "name",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String name;

    /** 類義語名称 */
    @Column(name = "synonym_name",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String synonymName;

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
     * 類義語区分を返します。
     * 
     * @return 類義語区分
     */
    public String getSynonymType() {
        return synonymType;
    }

    /**
     * 類義語区分を設定します。
     * 
     * @param synonymType 類義語区分
     */
    public void setSynonymType(String synonymType) {
        this.synonymType = synonymType;
    }

    /**
     * コードを返します。
     * 
     * @return コード
     */
    public String getCd() {
        return cd;
    }

    /**
     * コードを設定します。
     * 
     * @param cd コード
     */
    public void setCd(String cd) {
        this.cd = cd;
    }

    /**
     * 名称を返します。
     * 
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称を設定します。
     * 
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 類義語名称を返します。
     * 
     * @return 類義語名称
     */
    public String getSynonymName() {
        return synonymName;
    }

    /**
     * 類義語名称を設定します。
     * 
     * @param synonymName 類義語名称
     */
    public void setSynonymName(String synonymName) {
        this.synonymName = synonymName;
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
    public <T extends AbstractCrmCcSynonymPattern> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}