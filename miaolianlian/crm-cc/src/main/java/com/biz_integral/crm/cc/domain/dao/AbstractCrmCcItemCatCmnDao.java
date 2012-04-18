/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.TermI18NSupportDao;
import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcItemCatCmn;
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

import static com.biz_integral.crm.cc.domain.entity.CrmCcItemCatCmnNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractCrmCcItemCatCmn}の抽象Daoクラスです。
 * 
 */
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:18"
)
public abstract class AbstractCrmCcItemCatCmnDao<T extends AbstractCrmCcItemCatCmn> 
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
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String itemCategorySetCd, String itemCategoryCd, String localeId, String termCd) {
        return createSelect().id(itemCategorySetCd, itemCategoryCd, localeId, termCd);
    }
    
    /**
     * １件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String itemCategorySetCd, String itemCategoryCd, String localeId, String termCd) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, localeId, termCd)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 例外なし１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkNoException(String itemCategorySetCd, String itemCategoryCd, String localeId, String termCd) {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, localeId, termCd)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String itemCategorySetCd, String itemCategoryCd, String localeId, String termCd) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, localeId, termCd)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * ロックあり例外なし１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String itemCategorySetCd, String itemCategoryCd, String localeId, String termCd) {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, localeId, termCd)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 期間・ロケール無視AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String itemCategorySetCd, String itemCategoryCd) {

        return addOrderBy(getAutoSelectFactory().normalSelect().where(
            new SimpleWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                            .eq(itemCategoryCd(), itemCategoryCd)
                                    ));
    }

    /**
     * 期間・ロケール無視リスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTerm(String itemCategorySetCd, String itemCategoryCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, itemCategoryCd)
            .getResultList();
    }
    
    /**
     * 期間・ロケール無視ロックありリスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTermWithLock(String itemCategorySetCd, String itemCategoryCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, itemCategoryCd)
            .forUpdateNowait()
            .getResultList();
    }
    

    /**
     * 削除フラグ指定かつ期間・ロケール無視AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String itemCategorySetCd, String itemCategoryCd, LogicalDelete logicalDelete) {

        return addOrderBy(this.selectFactory.select(logicalDelete).where(
            new SimpleWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                            .eq(itemCategoryCd(), itemCategoryCd)
                                    ));
    }

    /**
     * 削除フラグ指定かつ期間・ロケール無視リスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTerm(String itemCategorySetCd, String itemCategoryCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, itemCategoryCd, logicalDelete)
            .getResultList();
    }

    /**
     * 削除フラグ指定かつ期間・ロケール無視ロックありリスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTermWithLock(String itemCategorySetCd, String itemCategoryCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, itemCategoryCd, logicalDelete)
            .forUpdateNowait()
            .getResultList();
    }
    /**
     * 期間無視AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreTerm(String itemCategorySetCd, String itemCategoryCd, String localeId) {

        return addOrderBy(getAutoSelectFactory().normalSelect().where(
            new SimpleWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                            .eq(itemCategoryCd(), itemCategoryCd)
                            .eq(localeId(), localeId)
                        ));
    }

    /**
     * 期間無視リスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String itemCategorySetCd, String itemCategoryCd, String localeId) throws SNoResultException {
        return createPkSelectIgnoreTerm(itemCategorySetCd, itemCategoryCd, localeId)
            .getResultList();
    }

    /**
     * 期間無視ロックありリスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String itemCategorySetCd, String itemCategoryCd, String localeId) throws SNoResultException {
        return createPkSelectIgnoreTerm(itemCategorySetCd, itemCategoryCd, localeId)
            .forUpdateNowait()
            .getResultList();
    }
    
    /**
     * 削除フラグ指定かつ期間無視AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreTerm(String itemCategorySetCd, String itemCategoryCd, String localeId, LogicalDelete logicalDelete) {

        return addOrderBy(this.selectFactory.select(logicalDelete).where(
            new SimpleWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                            .eq(itemCategoryCd(), itemCategoryCd)
                            .eq(localeId(), localeId)
                        ));
    }

    /**
     * 削除フラグ指定かつ期間無視リスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String itemCategorySetCd, String itemCategoryCd, String localeId, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreTerm(itemCategorySetCd, itemCategoryCd, localeId, logicalDelete)
            .getResultList();
    }

    /**
     * 削除フラグ指定かつ期間無視ロックありリスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String itemCategorySetCd, String itemCategoryCd, String localeId, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreTerm(itemCategorySetCd, itemCategoryCd, localeId, logicalDelete)
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
        if (getByPkNoException(entity.getItemCategorySetCd(), entity.getItemCategoryCd(), entity.getLocaleId(), entity.getTermCd()) == null) {
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
        if (getByPkWithLockNoException(entity.getItemCategorySetCd(), entity.getItemCategoryCd(), entity.getLocaleId(), entity.getTermCd()) == null) {
            return insert(entity);
        }
        return update(entity);
    }
    /**
     * 削除フラグ・PK設定済AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String itemCategorySetCd, String itemCategoryCd, String localeId, String termCd, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).id(itemCategorySetCd, itemCategoryCd, localeId, termCd);
    }
    
    /**
     * 削除フラグ指定１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String itemCategorySetCd, String itemCategoryCd, String localeId, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, localeId, termCd, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 削除フラグ指定例外なし１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkNoException(String itemCategorySetCd, String itemCategoryCd, String localeId, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, localeId, termCd, logicalDelete)
            .getSingleResult();
    }

    /**
     * 削除フラグ指定ロックあり１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String itemCategorySetCd, String itemCategoryCd, String localeId, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, localeId, termCd, logicalDelete)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定ロックあり例外なし１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String itemCategorySetCd, String itemCategoryCd, String localeId, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, localeId, termCd, logicalDelete)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 全件取得。
     * 
     * @return エンティティのリスト
     */
    public List<T> findAll() {
        return createSelect().orderBy(asc(itemCategorySetCd()), asc(itemCategoryCd()), asc(localeId()), asc(termCd())).getResultList();
    }

    /**
     * 削除フラグ指定全件取得。
     * 
     * @param logicalDelete 削除フラグ
     * @return エンティティのリスト
     */
    public List<T> findAll(LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).orderBy(asc(itemCategorySetCd()), asc(itemCategoryCd()), asc(localeId()), asc(termCd())).getResultList();
    }
    /**
     * 期間・ロケール指定済AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermLocaleSelect(String itemCategorySetCd, String itemCategoryCd, String localeId, Date date) {

        return createSelect().where(
            new ComplexWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                .eq(itemCategoryCd(), itemCategoryCd)
                .eq(localeId(), localeId)
                .and(validTermWhere(date)));
    }

    /**
     * 有効期間内ロケール１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidLocaleEntity(String itemCategorySetCd, String itemCategoryCd, String localeId, Date date) throws SNoResultException {
        return createPkTermLocaleSelect(itemCategorySetCd, itemCategoryCd, localeId, date)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 例外なし有効期間内ロケール１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidLocaleEntityNoException(String itemCategorySetCd, String itemCategoryCd, String localeId, Date date) {
        return createPkTermLocaleSelect(itemCategorySetCd, itemCategoryCd, localeId, date)
            .getSingleResult();
    }

    /**
     * 削除フラグ・期間・ロケール指定済AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermLocaleSelect(String itemCategorySetCd, String itemCategoryCd, String localeId, Date date, LogicalDelete logicalDelete) {

        return this.selectFactory.select(logicalDelete).where(
            new ComplexWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                .eq(itemCategoryCd(), itemCategoryCd)
                .eq(localeId(), localeId)
                .and(validTermWhere(date)));
    }

    /**
     * 削除フラグ指定有効期間内ロケール１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidLocaleEntity(String itemCategorySetCd, String itemCategoryCd, String localeId, Date date, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkTermLocaleSelect(itemCategorySetCd, itemCategoryCd, localeId, date, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定例外なし有効期間内ロケール１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidLocaleEntityNoException(String itemCategorySetCd, String itemCategoryCd, String localeId, Date date, LogicalDelete logicalDelete) {
        return createPkTermLocaleSelect(itemCategorySetCd, itemCategoryCd, localeId, date, logicalDelete)
            .getSingleResult();
    }
}