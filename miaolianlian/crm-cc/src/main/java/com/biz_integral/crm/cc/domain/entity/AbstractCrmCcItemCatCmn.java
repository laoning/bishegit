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
 * 品目カテゴリ_共通
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_item_cat_cmn")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:02"
)
public abstract class AbstractCrmCcItemCatCmn implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcItemCatCmn() {
    }

    /** 品目カテゴリセットコード */
    @Id
    @Column(name = "item_category_set_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String itemCategorySetCd;

    /** 品目カテゴリコード */
    @Id
    @Column(name = "item_category_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String itemCategoryCd;

    /** ロケールID */
    @Id
    @Column(name = "locale_id",
            columnDefinition = "varchar2(50)", 
            nullable = false, unique = false)
    private String localeId;

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

    /** 品目カテゴリ名 */
    @Column(name = "item_category_name",
            columnDefinition = "varchar2(1000)", 
            nullable = false, unique = false)
    private String itemCategoryName;

    /** 品目カテゴリ略称 */
    @Column(name = "item_category_short_name",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String itemCategoryShortName;

    /** 品目カテゴリ検索名 */
    @Column(name = "item_category_search_name",
            columnDefinition = "varchar2(1000)", 
            nullable = true, unique = false)
    private String itemCategorySearchName;

    /** 備考 */
    @Column(name = "notes",
            columnDefinition = "varchar2(4000)", 
            nullable = true, unique = false)
    private String notes;

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
     * 品目カテゴリセットコードを返します。
     * 
     * @return 品目カテゴリセットコード
     */
    public String getItemCategorySetCd() {
        return itemCategorySetCd;
    }

    /**
     * 品目カテゴリセットコードを設定します。
     * 
     * @param itemCategorySetCd 品目カテゴリセットコード
     */
    public void setItemCategorySetCd(String itemCategorySetCd) {
        this.itemCategorySetCd = itemCategorySetCd;
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
     * 品目カテゴリ名を返します。
     * 
     * @return 品目カテゴリ名
     */
    public String getItemCategoryName() {
        return itemCategoryName;
    }

    /**
     * 品目カテゴリ名を設定します。
     * 
     * @param itemCategoryName 品目カテゴリ名
     */
    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    /**
     * 品目カテゴリ略称を返します。
     * 
     * @return 品目カテゴリ略称
     */
    public String getItemCategoryShortName() {
        return itemCategoryShortName;
    }

    /**
     * 品目カテゴリ略称を設定します。
     * 
     * @param itemCategoryShortName 品目カテゴリ略称
     */
    public void setItemCategoryShortName(String itemCategoryShortName) {
        this.itemCategoryShortName = itemCategoryShortName;
    }

    /**
     * 品目カテゴリ検索名を返します。
     * 
     * @return 品目カテゴリ検索名
     */
    public String getItemCategorySearchName() {
        return itemCategorySearchName;
    }

    /**
     * 品目カテゴリ検索名を設定します。
     * 
     * @param itemCategorySearchName 品目カテゴリ検索名
     */
    public void setItemCategorySearchName(String itemCategorySearchName) {
        this.itemCategorySearchName = itemCategorySearchName;
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
        builder.append(itemCategorySetCd);
        builder.append(itemCategoryCd);
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
    public <T extends AbstractCrmCcItemCatCmn> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}