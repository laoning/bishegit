/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.TermSupportDao;
import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcItemCatIncAthCmn;
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

import static com.biz_integral.crm.cc.domain.entity.CrmCcItemCatIncAthCmnNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractCrmCcItemCatIncAthCmn}の抽象Daoクラスです。
 * 
 */
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:18"
)
public abstract class AbstractCrmCcItemCatIncAthCmnDao<T extends AbstractCrmCcItemCatIncAthCmn> 
        extends TermSupportDao<T> {
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
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param termCd
     *            期間コード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, String termCd) {
        return createSelect().id(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, termCd);
    }
    
    /**
     * １件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, String termCd) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, termCd)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 例外なし１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkNoException(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, String termCd) {
        return createPkSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, termCd)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, String termCd) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, termCd)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * ロックあり例外なし１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, String termCd) {
        return createPkSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, termCd)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 期間無視AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd) {

        return addOrderBy(getAutoSelectFactory().normalSelect().where(
            new SimpleWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                            .eq(parentItemCategoryCd(), parentItemCategoryCd)
                            .eq(itemCategoryCd(), itemCategoryCd)
                        ));
    }

    /**
     * 期間無視リスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd)
            .getResultList();
    }
    
    /**
     * 期間無視ロックありリスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd)
            .forUpdateNowait()
            .getResultList();
    }
    

    /**
     * 削除フラグ指定かつ期間・ロケール無視AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, LogicalDelete logicalDelete) {

        return addOrderBy(this.selectFactory.select(logicalDelete).where(
            new SimpleWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                            .eq(parentItemCategoryCd(), parentItemCategoryCd)
                            .eq(itemCategoryCd(), itemCategoryCd)
                        ));
    }

    /**
     * 削除フラグ指定かつ期間無視リスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, logicalDelete)
            .getResultList();
    }

    /**
     * 削除フラグ指定かつ期間無視ロックありリスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, logicalDelete)
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
        if (getByPkNoException(entity.getItemCategorySetCd(), entity.getParentItemCategoryCd(), entity.getItemCategoryCd(), entity.getTermCd()) == null) {
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
        if (getByPkWithLockNoException(entity.getItemCategorySetCd(), entity.getParentItemCategoryCd(), entity.getItemCategoryCd(), entity.getTermCd()) == null) {
            return insert(entity);
        }
        return update(entity);
    }
    /**
     * 削除フラグ・PK設定済AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, String termCd, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).id(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, termCd);
    }
    
    /**
     * 削除フラグ指定１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, termCd, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 削除フラグ指定例外なし１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkNoException(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, termCd, logicalDelete)
            .getSingleResult();
    }

    /**
     * 削除フラグ指定ロックあり１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, termCd, logicalDelete)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定ロックあり例外なし１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, termCd, logicalDelete)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 全件取得。
     * 
     * @return エンティティのリスト
     */
    public List<T> findAll() {
        return createSelect().orderBy(asc(itemCategorySetCd()), asc(parentItemCategoryCd()), asc(itemCategoryCd()), asc(termCd())).getResultList();
    }

    /**
     * 削除フラグ指定全件取得。
     * 
     * @param logicalDelete 削除フラグ
     * @return エンティティのリスト
     */
    public List<T> findAll(LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).orderBy(asc(itemCategorySetCd()), asc(parentItemCategoryCd()), asc(itemCategoryCd()), asc(termCd())).getResultList();
    }

    /**
     * 日付指定済AutoSelect取得。
     * 
         * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param date
     *            日付
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermSelect(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, Date date) {

        return createSelect().where(
            new ComplexWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                .eq(parentItemCategoryCd(), parentItemCategoryCd)
                .eq(itemCategoryCd(), itemCategoryCd)
                .and(validTermWhere(date)));
    }

    /**
     * 有効期間内１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidEntity(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, Date date) throws SNoResultException {
        return createPkTermSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, date)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 例外なし有効期間内１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidEntityNoException(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, Date date) {
        return createPkTermSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, date)
            .getSingleResult();
    }

    /**
     * 削除フラグ・日付指定済AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermSelect(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, Date date, LogicalDelete logicalDelete) {

        return this.selectFactory.select(logicalDelete).where(
            new ComplexWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                .eq(parentItemCategoryCd(), parentItemCategoryCd)
                .eq(itemCategoryCd(), itemCategoryCd)
                .and(validTermWhere(date)));
    }

    /**
     * 削除フラグ指定有効期間内１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidEntity(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, Date date, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkTermSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, date, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定例外なし有効期間内１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param parentItemCategoryCd
     *            親品目カテゴリコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidEntityNoException(String itemCategorySetCd, String parentItemCategoryCd, String itemCategoryCd, Date date, LogicalDelete logicalDelete) {
        return createPkTermSelect(itemCategorySetCd, parentItemCategoryCd, itemCategoryCd, date, logicalDelete)
            .getSingleResult();
    }
}