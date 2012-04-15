/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.GenericDao;
import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcSynonymPattern;
import java.util.List;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.exception.SNoResultException;

import static com.biz_integral.crm.cc.domain.entity.CrmCcSynonymPatternNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractCrmCcSynonymPattern}の抽象Daoクラスです。
 * 
 */
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"
        }, 
    date = "2010/10/25 16:14:54"
)
public abstract class AbstractCrmCcSynonymPatternDao<T extends AbstractCrmCcSynonymPattern> 
        extends GenericDao<T> {
    
    /**
     * PK設定済AutoSelect取得。
     * 
     * @param id
     *            ID
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String id) {
        return createSelect().id(id);
    }
    
    /**
     * １件取得。
     * 
     * @param id
     *            ID
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String id) throws SNoResultException {
        return createPkSelect(id)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 例外なし１件取得。
     * 
     * @param id
     *            ID
     * @return エンティティ
     */
    public T getByPkNoException(String id) {
        return createPkSelect(id)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param id
     *            ID
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String id) throws SNoResultException {
        return createPkSelect(id)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * ロックあり例外なし１件取得。
     * 
     * @param id
     *            ID
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String id) {
        return createPkSelect(id)
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
        if (getByPkNoException(entity.getId()) == null) {
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
        if (getByPkWithLockNoException(entity.getId()) == null) {
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
        return createSelect().orderBy(asc(id())).getResultList();
    }
}