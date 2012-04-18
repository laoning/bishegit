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
 * 品目カテゴリ内包_共通
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_item_cat_inc_ath_cmn")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:02"
)
public abstract class AbstractCrmCcItemCatIncAthCmn implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcItemCatIncAthCmn() {
    }

    /** 品目カテゴリセットコード */
    @Id
    @Column(name = "item_category_set_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String itemCategorySetCd;

    /** 親品目カテゴリコード */
    @Id
    @Column(name = "parent_item_category_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String parentItemCategoryCd;

    /** 品目カテゴリコード */
    @Id
    @Column(name = "item_category_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = false)
    private String itemCategoryCd;

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

    /** 階層レベル */
    @Column(name = "depth",
            columnDefinition = "number(5,0)", 
            nullable = false, unique = false)
    private Integer depth;

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
     * 親品目カテゴリコードを返します。
     * 
     * @return 親品目カテゴリコード
     */
    public String getParentItemCategoryCd() {
        return parentItemCategoryCd;
    }

    /**
     * 親品目カテゴリコードを設定します。
     * 
     * @param parentItemCategoryCd 親品目カテゴリコード
     */
    public void setParentItemCategoryCd(String parentItemCategoryCd) {
        this.parentItemCategoryCd = parentItemCategoryCd;
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
     * 階層レベルを返します。
     * 
     * @return 階層レベル
     */
    public Integer getDepth() {
        return depth;
    }

    /**
     * 階層レベルを設定します。
     * 
     * @param depth 階層レベル
     */
    public void setDepth(Integer depth) {
        this.depth = depth;
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
        builder.append(parentItemCategoryCd);
        builder.append(itemCategoryCd);
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
    public <T extends AbstractCrmCcItemCatIncAthCmn> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}