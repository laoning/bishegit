/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.GenericDao;
import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcCountryCmn;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.factory.AutoSelectFactory;
import com.biz_integral.service.persistence.factory.impl.LogicalDeleteAutoSelectFactoryImpl;
import java.util.List;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.exception.SNoResultException;
import org.seasar.framework.container.annotation.tiger.InitMethod;

import static com.biz_integral.crm.cc.domain.entity.CrmCcCountryCmnNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractCrmCcCountryCmn}の抽象Daoクラスです。
 * 
 */
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:17"
)
public abstract class AbstractCrmCcCountryCmnDao<T extends AbstractCrmCcCountryCmn> 
        extends GenericDao<T> {
    /** 削除フラグ対応のAutoSelectFactory */
    private LogicalDeleteAutoSelectFactoryImpl<T> selectFactory;

    /**
     * {@inheritDoc}
     */
    @InitMethod
    @Override
    public void prepare() {
        selectFactory =
            new LogicalDeleteAutoSelectFactoryImpl<T>(jdbcManager, entityClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AutoSelectFactory<T> getAutoSelectFactory() {
        return this.selectFactory;
    }
    
    /**
     * PK設定済AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String companyCd, String countryCd, String localeId) {
        return createSelect().id(companyCd, countryCd, localeId);
    }
    
    /**
     * １件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String companyCd, String countryCd, String localeId) throws SNoResultException {
        return createPkSelect(companyCd, countryCd, localeId)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @return エンティティ
     */
    public T getByPkNoException(String companyCd, String countryCd, String localeId) {
        return createPkSelect(companyCd, countryCd, localeId)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String companyCd, String countryCd, String localeId) throws SNoResultException {
        return createPkSelect(companyCd, countryCd, localeId)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * ロックあり例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String companyCd, String countryCd, String localeId) {
        return createPkSelect(companyCd, countryCd, localeId)
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
        if (getByPkNoException(entity.getCompanyCd(), entity.getCountryCd(), entity.getLocaleId()) == null) {
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
        if (getByPkWithLockNoException(entity.getCompanyCd(), entity.getCountryCd(), entity.getLocaleId()) == null) {
            return insert(entity);
        }
        return update(entity);
    }
    /**
     * 削除フラグ・PK設定済AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String companyCd, String countryCd, String localeId, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).id(companyCd, countryCd, localeId);
    }
    
    /**
     * 削除フラグ指定１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String companyCd, String countryCd, String localeId, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, countryCd, localeId, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 削除フラグ指定例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkNoException(String companyCd, String countryCd, String localeId, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, countryCd, localeId, logicalDelete)
            .getSingleResult();
    }

    /**
     * 削除フラグ指定ロックあり１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String companyCd, String countryCd, String localeId, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, countryCd, localeId, logicalDelete)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定ロックあり例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String companyCd, String countryCd, String localeId, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, countryCd, localeId, logicalDelete)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * バージョン指定１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @param versionNo
     *            バージョン番号
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkVersion(String companyCd, String countryCd, String localeId, Long versionNo) throws SNoResultException {
        return createPkSelect(companyCd, countryCd, localeId)
            .disallowNoResult()
            .version(versionNo)
            .getSingleResult();
    }
    
    /**
     * バージョン指定例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @param versionNo
     *            バージョン番号
     * @return エンティティ
     */
    public T getByPkVersionNoException(String companyCd, String countryCd, String localeId, Long versionNo) {
        return createPkSelect(companyCd, countryCd, localeId)
            .version(versionNo)
            .getSingleResult();
    }

    /**
     * 削除フラグ・バージョン指定１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @param versionNo
     *            バージョン番号
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkVersion(String companyCd, String countryCd, String localeId, Long versionNo, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, countryCd, localeId, logicalDelete)
            .disallowNoResult()
            .version(versionNo)
            .getSingleResult();
    }
    
    /**
     * 削除フラグ・バージョン指定例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param countryCd
     *            国コード
     * @param localeId
     *            ロケールID
     * @param versionNo
     *            バージョン番号
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkVersionNoException(String companyCd, String countryCd, String localeId, Long versionNo, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, countryCd, localeId, logicalDelete)
            .version(versionNo)
            .getSingleResult();
    }
    /**
     * 全件取得。
     * 
     * @return エンティティのリスト
     */
    public List<T> findAll() {
        return createSelect().orderBy(asc(companyCd()), asc(countryCd()), asc(localeId())).getResultList();
    }

    /**
     * 削除フラグ指定全件取得。
     * 
     * @param logicalDelete 削除フラグ
     * @return エンティティのリスト
     */
    public List<T> findAll(LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).orderBy(asc(companyCd()), asc(countryCd()), asc(localeId())).getResultList();
    }
}