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
 * 品目カテゴリセット_共通
 * 
 */
@MappedSuperclass
@Table(
    name = "crm_cc_item_cat_set_cmn")
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.AbstEntityModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:02"
)
public abstract class AbstractCrmCcItemCatSetCmn implements Serializable {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * デフォルト・コンストラクタ
     */
    public AbstractCrmCcItemCatSetCmn() {
    }

    /** 品目カテゴリセットコード */
    @Id
    @Column(name = "item_category_set_cd",
            columnDefinition = "varchar2(100)", 
            nullable = false, unique = true)
    private String itemCategorySetCd;

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
    public <T extends AbstractCrmCcItemCatSetCmn> boolean isTheSame(T entity)
        throws UnsupportedOperationException {

        return EntityUtil.isTheSamePK(this, entity);
    }
}