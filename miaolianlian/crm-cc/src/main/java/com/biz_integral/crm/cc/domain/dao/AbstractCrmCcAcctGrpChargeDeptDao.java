/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.TermSupportDao;
import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcAcctGrpChargeDept;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.factory.AutoSelectFactory;
import com.biz_integral.service.persistence.factory.impl.LogicalDeleteAutoSelectFactoryImpl;
import java.util.Date;
import java.util.List;
import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.exception.SNoResultException;
import org.seasar.extension.jdbc.where.ComplexWhere;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.container.annotation.tiger.InitMethod;

import static com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeDeptNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractCrmCcAcctGrpChargeDept}の抽象Daoクラスです。
 * 
 */
public abstract class AbstractCrmCcAcctGrpChargeDeptDao<T extends AbstractCrmCcAcctGrpChargeDept> 
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
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd) {
        return createSelect().id(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd);
    }
    
    /**
     * １件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd) throws SNoResultException {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkNoException(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd) {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd) throws SNoResultException {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * ロックあり例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd) {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * 期間無視AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd) {

        return addOrderBy(getAutoSelectFactory().normalSelect().where(
            new SimpleWhere()
                .eq(companyCd(), companyCd)
                            .eq(crmAccountGroupCd(), crmAccountGroupCd)
                            .eq(departmentCd(), departmentCd)
                            .eq(crmDomainCd(), crmDomainCd)
                        ));
    }

    /**
     * 期間無視リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd)
            .getResultList();
    }
    
    /**
     * 期間無視ロックありリスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd)
            .forUpdateNowait()
            .getResultList();
    }
    

    /**
     * 削除フラグ指定かつ期間・ロケール無視AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelectIgnoreLocaleAndTerm(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, LogicalDelete logicalDelete) {

        return addOrderBy(this.selectFactory.select(logicalDelete).where(
            new SimpleWhere()
                .eq(companyCd(), companyCd)
                            .eq(crmAccountGroupCd(), crmAccountGroupCd)
                            .eq(departmentCd(), departmentCd)
                            .eq(crmDomainCd(), crmDomainCd)
                        ));
    }

    /**
     * 削除フラグ指定かつ期間無視リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTerm(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, logicalDelete)
            .getResultList();
    }

    /**
     * 削除フラグ指定かつ期間無視ロックありリスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param logicalDelete 削除フラグ
     * @return 検索結果リスト
     */
    public List<T> findByPkIgnoreTermWithLock(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelectIgnoreLocaleAndTerm(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, logicalDelete)
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
        if (getByPkNoException(entity.getCompanyCd(), entity.getCrmAccountGroupCd(), entity.getDepartmentCd(), entity.getCrmDomainCd(), entity.getTermCd()) == null) {
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
        if (getByPkWithLockNoException(entity.getCompanyCd(), entity.getCrmAccountGroupCd(), entity.getDepartmentCd(), entity.getCrmDomainCd(), entity.getTermCd()) == null) {
            return insert(entity);
        }
        return update(entity);
    }
    /**
     * 削除フラグ・PK設定済AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).id(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd);
    }
    
    /**
     * 削除フラグ指定１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 削除フラグ指定例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkNoException(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd, logicalDelete)
            .getSingleResult();
    }

    /**
     * 削除フラグ指定ロックあり１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd, logicalDelete)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定ロックあり例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd, logicalDelete)
            .forUpdateNowait()
            .getSingleResult();
    }
    /**
     * バージョン指定１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkVersion(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd, Long versionNo) throws SNoResultException {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd)
            .disallowNoResult()
            .version(versionNo)
            .getSingleResult();
    }
    
    /**
     * バージョン指定例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @return エンティティ
     */
    public T getByPkVersionNoException(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd, Long versionNo) {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd)
            .version(versionNo)
            .getSingleResult();
    }

    /**
     * 削除フラグ・バージョン指定１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkVersion(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd, Long versionNo, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd, logicalDelete)
            .disallowNoResult()
            .version(versionNo)
            .getSingleResult();
    }
    
    /**
     * 削除フラグ・バージョン指定例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param termCd
     *            期間コード
     * @param versionNo
     *            バージョン番号
     * @param logicalDelete 削除フラグ
     * @return エンティティ
     */
    public T getByPkVersionNoException(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, String termCd, Long versionNo, LogicalDelete logicalDelete) {
        return createPkSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, termCd, logicalDelete)
            .version(versionNo)
            .getSingleResult();
    }
    /**
     * 全件取得。
     * 
     * @return エンティティのリスト
     */
    public List<T> findAll() {
        return createSelect().orderBy(asc(companyCd()), asc(crmAccountGroupCd()), asc(departmentCd()), asc(crmDomainCd()), asc(termCd())).getResultList();
    }

    /**
     * 削除フラグ指定全件取得。
     * 
     * @param logicalDelete 削除フラグ
     * @return エンティティのリスト
     */
    public List<T> findAll(LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).orderBy(asc(companyCd()), asc(crmAccountGroupCd()), asc(departmentCd()), asc(crmDomainCd()), asc(termCd())).getResultList();
    }

    /**
     * バージョン指定期間指定リスト取得
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param date
     *            日付
     * @param versionNo
     *            バージョン
     * @return dateが有効期間内に含まれるエンティティ
     */
    public List<T> findValidEntitiesByVersion(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, Date date , Long versionNo) {
        return this.findValidEntitiesByVersion(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, date , versionNo , LogicalDelete.AVAILABLE);
    }
    
    /**
     * バージョン指定削除フラグ指定有効期間内リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param date
     *            日付
     * @param versionNo
     *            バージョン
     * @param logicalDelete
     *            削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     */
    public List<T> findValidEntitiesByVersion(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, Date date , Long versionNo, LogicalDelete logicalDelete) {
        return this.selectFactory.select(logicalDelete).where(
            new ComplexWhere()
                .eq(companyCd(), companyCd)
                .eq(crmAccountGroupCd(), crmAccountGroupCd)
                .eq(departmentCd(), departmentCd)
                .eq(crmDomainCd(), crmDomainCd)
                .eq(versionNo() ,  versionNo)
                .and(validTermWhere(date)))
                .getResultList();
    }

    /**
     * 日付指定済AutoSelect取得。
     * 
         * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param date
     *            日付
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermSelect(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, Date date) {

        return createSelect().where(
            new ComplexWhere()
                .eq(companyCd(), companyCd)
                .eq(crmAccountGroupCd(), crmAccountGroupCd)
                .eq(departmentCd(), departmentCd)
                .eq(crmDomainCd(), crmDomainCd)
                .and(validTermWhere(date)));
    }

    /**
     * 有効期間内１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidEntity(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, Date date) throws SNoResultException {
        return createPkTermSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, date)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 例外なし有効期間内１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param date
     *            日付
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidEntityNoException(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, Date date) {
        return createPkTermSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, date)
            .getSingleResult();
    }

    /**
     * 削除フラグ・日付指定済AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkTermSelect(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, Date date, LogicalDelete logicalDelete) {

        return this.selectFactory.select(logicalDelete).where(
            new ComplexWhere()
                .eq(companyCd(), companyCd)
                .eq(crmAccountGroupCd(), crmAccountGroupCd)
                .eq(departmentCd(), departmentCd)
                .eq(crmDomainCd(), crmDomainCd)
                .and(validTermWhere(date)));
    }

    /**
     * 削除フラグ指定有効期間内１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getValidEntity(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, Date date, LogicalDelete logicalDelete) throws SNoResultException {
        return createPkTermSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, date, logicalDelete)
            .disallowNoResult()
            .getSingleResult();
    }
    
    /**
     * 削除フラグ指定例外なし有効期間内１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountGroupCd
     *            CRMアカウントグループコード
     * @param departmentCd
     *            組織コード
     * @param crmDomainCd
     *            CRM領域コード
     * @param date
     *            日付
     * @param logicalDelete 削除フラグ
     * @return dateが有効期間内に含まれるエンティティ
     */
    public T getValidEntityNoException(String companyCd, String crmAccountGroupCd, String departmentCd, String crmDomainCd, Date date, LogicalDelete logicalDelete) {
        return createPkTermSelect(companyCd, crmAccountGroupCd, departmentCd, crmDomainCd, date, logicalDelete)
            .getSingleResult();
    }
}