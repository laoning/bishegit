/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.TermI18NSupportDao;
import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcContact;
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

import static com.biz_integral.crm.cc.domain.entity.CrmCcContactNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractCrmCcContact}の抽象Daoクラスです。
 * 
 */
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:17"
)
public abstract class AbstractCrmCcContactDao<T extends AbstractCrmCcContact> 
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
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String companyCd, String crmContactCd, String localeId, String termCd) {
        return createSelect().id(companyCd, crmContactCd, localeId, termCd);
    }
    
    /**
     * １件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String companyCd, String crmContactCd, String localeId, String termCd) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkNoException(String companyCd, String crmContactCd, String localeId, String termCd) {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String companyCd, String crmContactCd, String localeId, String termCd) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * ロックあり例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String companyCd, String crmContactCd, String localeId, String termCd) {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 期間・ロケール無視AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String companyCd, String crmContactCd) {

        return addOrderBy(getAutoSelectFactory().normalSelect().where(
            new SimpleWhere()
                .eq(companyCd(), companyCd)
                            .eq(crmContactCd(), crmContactCd)
                                    ));
    }

    /**
     * 期間・ロケール無視リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTerm(String companyCd, String crmContactCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmContactCd)
            .getResultList();
    }
    
    /**
     * 期間・ロケール無視ロックありリスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTermWithLock(String companyCd, String crmContactCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmContactCd)
            .forUpdateNowait()
            .getResultList();
    }
    

    /**
     * 削除フラグ指定かつ期間・ロケール無視AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String companyCd, String crmContactCd, LogicalDelete logicalDelete) {

        return addOrderBy(this.selectFactory.select(logicalDelete).where(
            new SimpleWhere()
                .eq(companyCd(), companyCd)
                            .eq(crmContactCd(), crmContactCd)
                                    ));
    }

    /**
     * 削除フラグ指定かつ期間・ロケール無視リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTerm(String companyCd, String crmContactCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmContactCd, logicalDelete)
            .getResultList();
    }

    /**
     * 削除フラグ指定かつ期間・ロケール無視ロックありリスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreLocaleAndTermWithLock(String companyCd, String crmContactCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmContactCd, logicalDelete)
            .forUpdateNowait()
            .getResultList();
    }
    /**
     * 期間無視AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreTerm(String companyCd, String crmContactCd, String localeId) {

        return addOrderBy(getAutoSelectFactory().normalSelect().where(
            new SimpleWhere()
                .eq(companyCd(), companyCd)
                            .eq(crmContactCd(), crmContactCd)
                            .eq(localeId(), localeId)
                        ));
    }

    /**
     * 期間無視リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String companyCd, String crmContactCd, String localeId) throws SNoResultException {
        return createPkSelectIgnoreTerm(companyCd, crmContactCd, localeId)
            .getResultList();
    }

    /**
     * 期間無視ロックありリスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String companyCd, String crmContactCd, String localeId) throws SNoResultException {
        return createPkSelectIgnoreTerm(companyCd, crmContactCd, localeId)
            .forUpdateNowait()
            .getResultList();
    }
    
    /**
     * 削除フラグ指定かつ期間無視AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreTerm(String companyCd, String crmContactCd, String localeId, LogicalDelete logicalDelete) {

        return addOrderBy(this.selectFactory.select(logicalDelete).where(
            new SimpleWhere()
                .eq(companyCd(), companyCd)
                            .eq(crmContactCd(), crmContactCd)
                            .eq(localeId(), localeId)
                        ));
    }

    /**
     * 削除フラグ指定かつ期間無視リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String companyCd, String crmContactCd, String localeId, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreTerm(companyCd, crmContactCd, localeId, logicalDelete)
            .getResultList();
    }

    /**
     * 削除フラグ指定かつ期間無視ロックありリスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String companyCd, String crmContactCd, String localeId, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreTerm(companyCd, crmContactCd, localeId, logicalDelete)
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
        if (getByPkNoException(entity.getCompanyCd(), entity.getCrmContactCd(), entity.getLocaleId(), entity.getTermCd()) == null) {
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
        if (getByPkWithLockNoException(entity.getCompanyCd(), entity.getCrmContactCd(), entity.getLocaleId(), entity.getTermCd()) == null) {
            return insert(entity);
        }
        return update(entity);
    }
    /**
     * 削除フラグ・PK設定済AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String companyCd, String crmContactCd, String localeId, String termCd, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).id(companyCd, crmContactCd, localeId, termCd);
    }
    
    /**
     * 削除フラグ指定１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String companyCd, String crmContactCd, String localeId, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 削除フラグ指定例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkNoException(String companyCd, String crmContactCd, String localeId, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd, logicalDelete)
            .getSingleResult();
    }

    /**
     * 削除フラグ指定ロックあり１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String companyCd, String crmContactCd, String localeId, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd, logicalDelete)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定ロックあり例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String companyCd, String crmContactCd, String localeId, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd, logicalDelete)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * バージョン指定１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkVersion(String companyCd, String crmContactCd, String localeId, String termCd, Long versionNo) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd)
            .disallowNoResult()
            .version(versionNo)
            .getSingleResult();
    }
    
    /**
     * バージョン指定例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @return エンティティ
     */
    public T getByPkVersionNoException(String companyCd, String crmContactCd, String localeId, String termCd, Long versionNo) {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd)
            .version(versionNo)
            .getSingleResult();
    }

    /**
     * 削除フラグ・バージョン指定１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkVersion(String companyCd, String crmContactCd, String localeId, String termCd, Long versionNo, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd, logicalDelete)
            .disallowNoResult()
            .version(versionNo)
            .getSingleResult();
    }
    
    /**
     * 削除フラグ・バージョン指定例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkVersionNoException(String companyCd, String crmContactCd, String localeId, String termCd, Long versionNo, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, crmContactCd, localeId, termCd, logicalDelete)
            .version(versionNo)
            .getSingleResult();
    }
    /**
     * 全件取得。
     * 
     * @return エンティティのリスト
     */
    public List<T> findAll() {
        return createSelect().orderBy(asc(companyCd()), asc(crmContactCd()), asc(localeId()), asc(termCd())).getResultList();
    }

    /**
     * 削除フラグ指定全件取得。
     * 
     * @param logicalDelete 削除フラグ
     * @return エンティティのリスト
     */
    public List<T> findAll(LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).orderBy(asc(companyCd()), asc(crmContactCd()), asc(localeId()), asc(termCd())).getResultList();
    }

    /**
     * バージョン指定期間指定リスト取得
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param date
     *            日付
     * @param versionNo
     *            バージョン
     * @return dateが有効期間内に含まれるエンティティ
     */
    public List<T> findValidEntitiesByVersion(String companyCd, String crmContactCd, Date date , Long versionNo) {
        return this.findValidEntitiesByVersion(companyCd, crmContactCd, date , versionNo , LogicalDelete.AVAILABLE);
    }
    
    /**
     * バージョン指定削除フラグ指定有効期間内リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param date
     *            日付
     * @param versionNo
     *            バージョン
     * @param logicalDelete
     *            削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     */
    public List<T> findValidEntitiesByVersion(String companyCd, String crmContactCd, Date date , Long versionNo, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).where(
            new ComplexWhere()
                .eq(companyCd(), companyCd)
                .eq(crmContactCd(), crmContactCd)
                .eq(versionNo() ,  versionNo)
                .and(validTermWhere(date)))
                .getResultList();
    }
    /**
     * 期間・ロケール指定済AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermLocaleSelect(String companyCd, String crmContactCd, String localeId, Date date) {

        return createSelect().where(
            new ComplexWhere()
                .eq(companyCd(), companyCd)
                .eq(crmContactCd(), crmContactCd)
                .eq(localeId(), localeId)
                .and(validTermWhere(date)));
    }

    /**
     * 有効期間内ロケール１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidLocaleEntity(String companyCd, String crmContactCd, String localeId, Date date) throws SNoResultException {
        return createPkTermLocaleSelect(companyCd, crmContactCd, localeId, date)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 例外なし有効期間内ロケール１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidLocaleEntityNoException(String companyCd, String crmContactCd, String localeId, Date date) {
        return createPkTermLocaleSelect(companyCd, crmContactCd, localeId, date)
            .getSingleResult();
    }

    /**
     * 削除フラグ・期間・ロケール指定済AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermLocaleSelect(String companyCd, String crmContactCd, String localeId, Date date, LogicalDelete logicalDelete) {

        return this.selectFactory.select(logicalDelete).where(
            new ComplexWhere()
                .eq(companyCd(), companyCd)
                .eq(crmContactCd(), crmContactCd)
                .eq(localeId(), localeId)
                .and(validTermWhere(date)));
    }

    /**
     * 削除フラグ指定有効期間内ロケール１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidLocaleEntity(String companyCd, String crmContactCd, String localeId, Date date, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkTermLocaleSelect(companyCd, crmContactCd, localeId, date, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定例外なし有効期間内ロケール１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param localeId
     *            ロケールID
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidLocaleEntityNoException(String companyCd, String crmContactCd, String localeId, Date date, LogicalDelete logicalDelete) {
        return createPkTermLocaleSelect(companyCd, crmContactCd, localeId, date, logicalDelete)
            .getSingleResult();
    }
}