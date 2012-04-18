/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.GenericDao;
import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcItemCatSetCmn;
import java.util.List;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.exception.SNoResultException;

import static com.biz_integral.crm.cc.domain.entity.CrmCcItemCatSetCmnNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractCrmCcItemCatSetCmn}の抽象Daoクラスです。
 * 
 */
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:18"
)
public abstract class AbstractCrmCcItemCatSetCmnDao<T extends AbstractCrmCcItemCatSetCmn> 
        extends GenericDao<T> {
    
    /**
     * PK設定済AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String itemCategorySetCd) {
        return createSelect().id(itemCategorySetCd);
    }
    
    /**
     * １件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String itemCategorySetCd) throws SNoResultException {
        return createPkSelect(itemCategorySetCd)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 例外なし１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @return エンティティ
     */
    public T getByPkNoException(String itemCategorySetCd) {
        return createPkSelect(itemCategorySetCd)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String itemCategorySetCd) throws SNoResultException {
        return createPkSelect(itemCategorySetCd)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * ロックあり例外なし１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String itemCategorySetCd) {
        return createPkSelect(itemCategorySetCd)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 1件挿入または更新。
     * 
     * @param entity
     *            エンティティ
     * @return 挿入もしくは更新した行数
     */
    public int insertOrUpdate(T entity) {
        if (getByPkNoException(entity.getItemCategorySetCd()) == null) {
            return insert(entity);
        }
        return update(entity);
    }
    
    /**
     * ロックあり1件挿入または更新。
     * 
     * @param entity
     *            エンティティ
     * @return 挿入もしくは更新した行数
     */
    public int insertOrUpdateWithLock(T entity) {
        if (getByPkWithLockNoException(entity.getItemCategorySetCd()) == null) {
            return insert(entity);
        }
        return update(entity);
    }
    /**
     * 全件取得。
     * 
     * @return エンティティのリスト
     */
    public List<T> findAll() {
        return createSelect().orderBy(asc(itemCategorySetCd())).getResultList();
    }
}