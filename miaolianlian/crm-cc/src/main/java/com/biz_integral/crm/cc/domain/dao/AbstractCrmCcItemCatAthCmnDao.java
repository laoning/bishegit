/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.TermSupportDao;
import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcItemCatAthCmn;
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

import static com.biz_integral.crm.cc.domain.entity.CrmCcItemCatAthCmnNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractCrmCcItemCatAthCmn}の抽象Daoクラスです。
 * 
 */
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:18"
)
public abstract class AbstractCrmCcItemCatAthCmnDao<T extends AbstractCrmCcItemCatAthCmn> 
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
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param termCd
     *            期間コード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, String termCd) {
        return createSelect().id(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, termCd);
    }
    
    /**
     * １件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, String termCd) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, termCd)
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
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkNoException(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, String termCd) {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, termCd)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, String termCd) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, termCd)
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
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, String termCd) {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, termCd)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 期間無視AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd) {

        return addOrderBy(getAutoSelectFactory().normalSelect().where(
            new SimpleWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                            .eq(itemCategoryCd(), itemCategoryCd)
                            .eq(companyCd(), companyCd)
                            .eq(itemCd(), itemCd)
                        ));
    }

    /**
     * 期間無視リスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, itemCategoryCd, companyCd, itemCd)
            .getResultList();
    }
    
    /**
     * 期間無視ロックありリスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, itemCategoryCd, companyCd, itemCd)
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
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, LogicalDelete logicalDelete) {

        return addOrderBy(this.selectFactory.select(logicalDelete).where(
            new SimpleWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                            .eq(itemCategoryCd(), itemCategoryCd)
                            .eq(companyCd(), companyCd)
                            .eq(itemCd(), itemCd)
                        ));
    }

    /**
     * 削除フラグ指定かつ期間無視リスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, logicalDelete)
            .getResultList();
    }

    /**
     * 削除フラグ指定かつ期間無視ロックありリスト取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, logicalDelete)
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
        if (getByPkNoException(entity.getItemCategorySetCd(), entity.getItemCategoryCd(), entity.getCompanyCd(), entity.getItemCd(), entity.getTermCd()) == null) {
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
        if (getByPkWithLockNoException(entity.getItemCategorySetCd(), entity.getItemCategoryCd(), entity.getCompanyCd(), entity.getItemCd(), entity.getTermCd()) == null) {
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
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, String termCd, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).id(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, termCd);
    }
    
    /**
     * 削除フラグ指定１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, termCd, logicalDelete)
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
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkNoException(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, termCd, logicalDelete)
            .getSingleResult();
    }

    /**
     * 削除フラグ指定ロックあり１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, termCd, logicalDelete)
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
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, termCd, logicalDelete)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 全件取得。
     * 
     * @return エンティティのリスト
     */
    public List<T> findAll() {
        return createSelect().orderBy(asc(itemCategorySetCd()), asc(itemCategoryCd()), asc(companyCd()), asc(itemCd()), asc(termCd())).getResultList();
    }

    /**
     * 削除フラグ指定全件取得。
     * 
     * @param logicalDelete 削除フラグ
     * @return エンティティのリスト
     */
    public List<T> findAll(LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).orderBy(asc(itemCategorySetCd()), asc(itemCategoryCd()), asc(companyCd()), asc(itemCd()), asc(termCd())).getResultList();
    }

    /**
     * 日付指定済AutoSelect取得。
     * 
         * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param date
     *            日付
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermSelect(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, Date date) {

        return createSelect().where(
            new ComplexWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                .eq(itemCategoryCd(), itemCategoryCd)
                .eq(companyCd(), companyCd)
                .eq(itemCd(), itemCd)
                .and(validTermWhere(date)));
    }

    /**
     * 有効期間内１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidEntity(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, Date date) throws SNoResultException {
        return createPkTermSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, date)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 例外なし有効期間内１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidEntityNoException(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, Date date) {
        return createPkTermSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, date)
            .getSingleResult();
    }

    /**
     * 削除フラグ・日付指定済AutoSelect取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermSelect(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, Date date, LogicalDelete logicalDelete) {

        return this.selectFactory.select(logicalDelete).where(
            new ComplexWhere()
                .eq(itemCategorySetCd(), itemCategorySetCd)
                .eq(itemCategoryCd(), itemCategoryCd)
                .eq(companyCd(), companyCd)
                .eq(itemCd(), itemCd)
                .and(validTermWhere(date)));
    }

    /**
     * 削除フラグ指定有効期間内１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidEntity(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, Date date, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkTermSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, date, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定例外なし有効期間内１件取得。
     * 
     * @param itemCategorySetCd
     *            品目カテゴリセットコード
     * @param itemCategoryCd
     *            品目カテゴリコード
     * @param companyCd
     *            会社コード
     * @param itemCd
     *            品目コード
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidEntityNoException(String itemCategorySetCd, String itemCategoryCd, String companyCd, String itemCd, Date date, LogicalDelete logicalDelete) {
        return createPkTermSelect(itemCategorySetCd, itemCategoryCd, companyCd, itemCd, date, logicalDelete)
            .getSingleResult();
    }
}