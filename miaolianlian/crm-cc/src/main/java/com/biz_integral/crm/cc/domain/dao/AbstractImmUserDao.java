/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.TermI18NSupportDao;
import com.biz_integral.crm.cc.domain.entity.AbstractImmUser;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.factory.AutoSelectFactory;
import com.biz_integral.service.persistence.factory.impl.LogicalDeleteAutoSelectFactoryImpl;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.exception.SNoResultException;
import org.seasar.extension.jdbc.where.ComplexWhere;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.container.annotation.tiger.InitMethod;

import static com.biz_integral.crm.cc.domain.entity.ImmUserNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractImmUser}の抽象Daoクラスです。
 * 
 */
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"
        }, 
    date = "2010/08/03 13:58:53"
)
public abstract class AbstractImmUserDao<T extends AbstractImmUser> 
        extends TermI18NSupportDao<T> {
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
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param termCd
     *            termCdプロパティ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String userCd, String localeId, String termCd) {
        return createSelect().id(userCd, localeId, termCd);
    }
    
    /**
     * １件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param termCd
     *            termCdプロパティ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String userCd, String localeId, String termCd) throws SNoResultException {
        return createPkSelect(userCd, localeId, termCd)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 例外なし１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param termCd
     *            termCdプロパティ
     * @return エンティティ
     */
    public T getByPkNoException(String userCd, String localeId, String termCd) {
        return createPkSelect(userCd, localeId, termCd)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param termCd
     *            termCdプロパティ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String userCd, String localeId, String termCd) throws SNoResultException {
        return createPkSelect(userCd, localeId, termCd)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * ロックあり例外なし１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param termCd
     *            termCdプロパティ
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String userCd, String localeId, String termCd) {
        return createPkSelect(userCd, localeId, termCd)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 期間・ロケール無視AutoSelect取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String userCd) {

        return addOrderBy(getAutoSelectFactory().normalSelect().where(
            new SimpleWhere()
                .eq(userCd(), userCd)
                                    ));
    }

    /**
     * 期間・ロケール無視リスト取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTerm(String userCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(userCd)
            .getResultList();
    }
    
    /**
     * 期間・ロケール無視ロックありリスト取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTermWithLock(String userCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(userCd)
            .forUpdateNowait()
            .getResultList();
    }
    

    /**
     * 削除フラグ指定かつ期間・ロケール無視AutoSelect取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String userCd, LogicalDelete logicalDelete) {

        return addOrderBy(this.selectFactory.select(logicalDelete).where(
            new SimpleWhere()
                .eq(userCd(), userCd)
                                    ));
    }

    /**
     * 削除フラグ指定かつ期間・ロケール無視リスト取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTerm(String userCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(userCd, logicalDelete)
            .getResultList();
    }

    /**
     * 削除フラグ指定かつ期間・ロケール無視ロックありリスト取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTermWithLock(String userCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(userCd, logicalDelete)
            .forUpdateNowait()
            .getResultList();
    }
    /**
     * 期間無視AutoSelect取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreTerm(String userCd, String localeId) {

        return addOrderBy(getAutoSelectFactory().normalSelect().where(
            new SimpleWhere()
                .eq(userCd(), userCd)
                            .eq(localeId(), localeId)
                        ));
    }

    /**
     * 期間無視リスト取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String userCd, String localeId) throws SNoResultException {
        return createPkSelectIgnoreTerm(userCd, localeId)
            .getResultList();
    }

    /**
     * 期間無視ロックありリスト取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String userCd, String localeId) throws SNoResultException {
        return createPkSelectIgnoreTerm(userCd, localeId)
            .forUpdateNowait()
            .getResultList();
    }
    
    /**
     * 削除フラグ指定かつ期間無視AutoSelect取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreTerm(String userCd, String localeId, LogicalDelete logicalDelete) {

        return addOrderBy(this.selectFactory.select(logicalDelete).where(
            new SimpleWhere()
                .eq(userCd(), userCd)
                            .eq(localeId(), localeId)
                        ));
    }

    /**
     * 削除フラグ指定かつ期間無視リスト取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String userCd, String localeId, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreTerm(userCd, localeId, logicalDelete)
            .getResultList();
    }

    /**
     * 削除フラグ指定かつ期間無視ロックありリスト取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String userCd, String localeId, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreTerm(userCd, localeId, logicalDelete)
            .forUpdateNowait()
            .getResultList();
    }
    /**
     * 1件挿入または更新。
     * 
     * @param entity
     *            エンティティ
     * @return 挿入もしくは更新した行数
     */
    public int insertOrUpdate(T entity) {
        if (getByPkNoException(entity.getUserCd(), entity.getLocaleId(), entity.getTermCd()) == null) {
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
        if (getByPkWithLockNoException(entity.getUserCd(), entity.getLocaleId(), entity.getTermCd()) == null) {
            return insert(entity);
        }
        return update(entity);
    }
    /**
     * 削除フラグ・PK設定済AutoSelect取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param termCd
     *            termCdプロパティ
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String userCd, String localeId, String termCd, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).id(userCd, localeId, termCd);
    }
    
    /**
     * 削除フラグ指定１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param termCd
     *            termCdプロパティ
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String userCd, String localeId, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(userCd, localeId, termCd, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 削除フラグ指定例外なし１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param termCd
     *            termCdプロパティ
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkNoException(String userCd, String localeId, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(userCd, localeId, termCd, logicalDelete)
            .getSingleResult();
    }

    /**
     * 削除フラグ指定ロックあり１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param termCd
     *            termCdプロパティ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String userCd, String localeId, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(userCd, localeId, termCd, logicalDelete)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定ロックあり例外なし１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param termCd
     *            termCdプロパティ
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String userCd, String localeId, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(userCd, localeId, termCd, logicalDelete)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 全件取得。
     * 
     * @return エンティティのリスト
     */
    public List<T> findAll() {
        return createSelect().orderBy(asc(userCd()), asc(localeId()), asc(termCd())).getResultList();
    }

    /**
     * 削除フラグ指定全件取得。
     * 
     * @param logicalDelete 削除フラグ
     * @return エンティティのリスト
     */
    public List<T> findAll(LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).orderBy(asc(userCd()), asc(localeId()), asc(termCd())).getResultList();
    }
    /**
     * 期間・ロケール指定済AutoSelect取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param date
     *            日付
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermLocaleSelect(String userCd, String localeId, Date date) {

        return createSelect().where(
            new ComplexWhere()
                .eq(userCd(), userCd)
                .eq(localeId(), localeId)
                .and(validTermWhere(date)));
    }

    /**
     * 有効期間内ロケール１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidLocaleEntity(String userCd, String localeId, Date date) throws SNoResultException {
        return createPkTermLocaleSelect(userCd, localeId, date)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 例外なし有効期間内ロケール１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidLocaleEntityNoException(String userCd, String localeId, Date date) {
        return createPkTermLocaleSelect(userCd, localeId, date)
            .getSingleResult();
    }

    /**
     * 削除フラグ・期間・ロケール指定済AutoSelect取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermLocaleSelect(String userCd, String localeId, Date date, LogicalDelete logicalDelete) {

        return this.selectFactory.select(logicalDelete).where(
            new ComplexWhere()
                .eq(userCd(), userCd)
                .eq(localeId(), localeId)
                .and(validTermWhere(date)));
    }

    /**
     * 削除フラグ指定有効期間内ロケール１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidLocaleEntity(String userCd, String localeId, Date date, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkTermLocaleSelect(userCd, localeId, date, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定例外なし有効期間内ロケール１件取得。
     * 
     * @param userCd
     *            userCdプロパティ
     * @param localeId
     *            localeIdプロパティ
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidLocaleEntityNoException(String userCd, String localeId, Date date, LogicalDelete logicalDelete) {
        return createPkTermLocaleSelect(userCd, localeId, date, logicalDelete)
            .getSingleResult();
    }
}