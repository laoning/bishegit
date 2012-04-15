/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.TermSupportDao;
import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcContactCompetition;
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

import static com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetitionNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractCrmCcContactCompetition}の抽象Daoクラスです。
 * 
 */
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:17"
)
public abstract class AbstractCrmCcContactCompetitionDao<T extends AbstractCrmCcContactCompetition> 
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
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String companyCd, String crmContactCd, String competitionId, String termCd) {
        return createSelect().id(companyCd, crmContactCd, competitionId, termCd);
    }
    
    /**
     * １件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String companyCd, String crmContactCd, String competitionId, String termCd) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd)
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
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkNoException(String companyCd, String crmContactCd, String competitionId, String termCd) {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String companyCd, String crmContactCd, String competitionId, String termCd) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd)
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
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String companyCd, String crmContactCd, String competitionId, String termCd) {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 期間無視AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String companyCd, String crmContactCd, String competitionId) {

        return addOrderBy(getAutoSelectFactory().normalSelect().where(
            new SimpleWhere()
                .eq(companyCd(), companyCd)
                            .eq(crmContactCd(), crmContactCd)
                            .eq(competitionId(), competitionId)
                        ));
    }

    /**
     * 期間無視リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String companyCd, String crmContactCd, String competitionId) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmContactCd, competitionId)
            .getResultList();
    }
    
    /**
     * 期間無視ロックありリスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String companyCd, String crmContactCd, String competitionId) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmContactCd, competitionId)
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
     * @param competitionId
     *            競合ID
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String companyCd, String crmContactCd, String competitionId, LogicalDelete logicalDelete) {

        return addOrderBy(this.selectFactory.select(logicalDelete).where(
            new SimpleWhere()
                .eq(companyCd(), companyCd)
                            .eq(crmContactCd(), crmContactCd)
                            .eq(competitionId(), competitionId)
                        ));
    }

    /**
     * 削除フラグ指定かつ期間無視リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String companyCd, String crmContactCd, String competitionId, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmContactCd, competitionId, logicalDelete)
            .getResultList();
    }

    /**
     * 削除フラグ指定かつ期間無視ロックありリスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String companyCd, String crmContactCd, String competitionId, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmContactCd, competitionId, logicalDelete)
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
        if (getByPkNoException(entity.getCompanyCd(), entity.getCrmContactCd(), entity.getCompetitionId(), entity.getTermCd()) == null) {
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
        if (getByPkWithLockNoException(entity.getCompanyCd(), entity.getCrmContactCd(), entity.getCompetitionId(), entity.getTermCd()) == null) {
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
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String companyCd, String crmContactCd, String competitionId, String termCd, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).id(companyCd, crmContactCd, competitionId, termCd);
    }
    
    /**
     * 削除フラグ指定１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String companyCd, String crmContactCd, String competitionId, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd, logicalDelete)
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
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkNoException(String companyCd, String crmContactCd, String competitionId, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd, logicalDelete)
            .getSingleResult();
    }

    /**
     * 削除フラグ指定ロックあり１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String companyCd, String crmContactCd, String competitionId, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd, logicalDelete)
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
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String companyCd, String crmContactCd, String competitionId, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd, logicalDelete)
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
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkVersion(String companyCd, String crmContactCd, String competitionId, String termCd, Long versionNo) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd)
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
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @return エンティティ
     */
    public T getByPkVersionNoException(String companyCd, String crmContactCd, String competitionId, String termCd, Long versionNo) {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd)
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
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkVersion(String companyCd, String crmContactCd, String competitionId, String termCd, Long versionNo, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd, logicalDelete)
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
     * @param competitionId
     *            競合ID
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkVersionNoException(String companyCd, String crmContactCd, String competitionId, String termCd, Long versionNo, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, crmContactCd, competitionId, termCd, logicalDelete)
            .version(versionNo)
            .getSingleResult();
    }
    /**
     * 全件取得。
     * 
     * @return エンティティのリスト
     */
    public List<T> findAll() {
        return createSelect().orderBy(asc(companyCd()), asc(crmContactCd()), asc(competitionId()), asc(termCd())).getResultList();
    }

    /**
     * 削除フラグ指定全件取得。
     * 
     * @param logicalDelete 削除フラグ
     * @return エンティティのリスト
     */
    public List<T> findAll(LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).orderBy(asc(companyCd()), asc(crmContactCd()), asc(competitionId()), asc(termCd())).getResultList();
    }

    /**
     * バージョン指定期間指定リスト取得
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param date
     *            日付
     * @param versionNo
     *            バージョン
     * @return dateが有効期間内に含まれるエンティティ
     */
    public List<T> findValidEntitiesByVersion(String companyCd, String crmContactCd, String competitionId, Date date , Long versionNo) {
        return this.findValidEntitiesByVersion(companyCd, crmContactCd, competitionId, date , versionNo , LogicalDelete.AVAILABLE);
    }
    
    /**
     * バージョン指定削除フラグ指定有効期間内リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param date
     *            日付
     * @param versionNo
     *            バージョン
     * @param logicalDelete
     *            削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     */
    public List<T> findValidEntitiesByVersion(String companyCd, String crmContactCd, String competitionId, Date date , Long versionNo, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).where(
            new ComplexWhere()
                .eq(companyCd(), companyCd)
                .eq(crmContactCd(), crmContactCd)
                .eq(competitionId(), competitionId)
                .eq(versionNo() ,  versionNo)
                .and(validTermWhere(date)))
                .getResultList();
    }

    /**
     * 日付指定済AutoSelect取得。
     * 
         * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param date
     *            日付
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermSelect(String companyCd, String crmContactCd, String competitionId, Date date) {

        return createSelect().where(
            new ComplexWhere()
                .eq(companyCd(), companyCd)
                .eq(crmContactCd(), crmContactCd)
                .eq(competitionId(), competitionId)
                .and(validTermWhere(date)));
    }

    /**
     * 有効期間内１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidEntity(String companyCd, String crmContactCd, String competitionId, Date date) throws SNoResultException {
        return createPkTermSelect(companyCd, crmContactCd, competitionId, date)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 例外なし有効期間内１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidEntityNoException(String companyCd, String crmContactCd, String competitionId, Date date) {
        return createPkTermSelect(companyCd, crmContactCd, competitionId, date)
            .getSingleResult();
    }

    /**
     * 削除フラグ・日付指定済AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermSelect(String companyCd, String crmContactCd, String competitionId, Date date, LogicalDelete logicalDelete) {

        return this.selectFactory.select(logicalDelete).where(
            new ComplexWhere()
                .eq(companyCd(), companyCd)
                .eq(crmContactCd(), crmContactCd)
                .eq(competitionId(), competitionId)
                .and(validTermWhere(date)));
    }

    /**
     * 削除フラグ指定有効期間内１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidEntity(String companyCd, String crmContactCd, String competitionId, Date date, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkTermSelect(companyCd, crmContactCd, competitionId, date, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定例外なし有効期間内１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmContactCd
     *            CRMコンタクトコード
     * @param competitionId
     *            競合ID
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidEntityNoException(String companyCd, String crmContactCd, String competitionId, Date date, LogicalDelete logicalDelete) {
        return createPkTermSelect(companyCd, crmContactCd, competitionId, date, logicalDelete)
            .getSingleResult();
    }
}