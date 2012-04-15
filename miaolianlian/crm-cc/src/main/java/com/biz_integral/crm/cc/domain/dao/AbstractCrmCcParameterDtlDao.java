/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.service.persistence.dao.GenericDao;
import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcParameterDtl;
import java.util.List;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.exception.SNoResultException;

import static com.biz_integral.crm.cc.domain.entity.CrmCcParameterDtlNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link AbstractCrmCcParameterDtl}の抽象Daoクラスです。
 * 
 */
@Generated(
    value = {
        "S2JDBC-Gen unknown", 
        "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"
        }, 
    date = "2010/09/02 12:34:18"
)
public abstract class AbstractCrmCcParameterDtlDao<T extends AbstractCrmCcParameterDtl> 
        extends GenericDao<T> {
    
    /**
     * PK設定済AutoSelect取得。
     * 
     * @param companyCd
     *            会社コード
     * @param parameterCd
     *            パラメータコード
     * @param termCd
     *            期間コード
     * @param localeId
     *            ロケールID
     * @return 検索条件
     */
    protected final AutoSelect<T> createPkSelect(String companyCd, String parameterCd, String termCd, String localeId) {
        return createSelect().id(companyCd, parameterCd, termCd, localeId);
    }
    
    /**
     * １件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param parameterCd
     *            パラメータコード
     * @param termCd
     *            期間コード
     * @param localeId
     *            ロケールID
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPk(String companyCd, String parameterCd, String termCd, String localeId) throws SNoResultException {
        return createPkSelect(companyCd, parameterCd, termCd, localeId)
            .disallowNoResult()
            .getSingleResult();
    }

    /**
     * 例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param parameterCd
     *            パラメータコード
     * @param termCd
     *            期間コード
     * @param localeId
     *            ロケールID
     * @return エンティティ
     */
    public T getByPkNoException(String companyCd, String parameterCd, String termCd, String localeId) {
        return createPkSelect(companyCd, parameterCd, termCd, localeId)
            .getSingleResult();
    }

    /**
     * ロックあり１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param parameterCd
     *            パラメータコード
     * @param termCd
     *            期間コード
     * @param localeId
     *            ロケールID
     * @return エンティティ
     * @throws SNoResultException
     *             結果が1件もない場合
     */
    public T getByPkWithLock(String companyCd, String parameterCd, String termCd, String localeId) throws SNoResultException {
        return createPkSelect(companyCd, parameterCd, termCd, localeId)
            .disallowNoResult()
            .forUpdateNowait()
            .getSingleResult();
    }
    
    /**
     * ロックあり例外なし１件取得。
     * 
     * @param companyCd
     *            会社コード
     * @param parameterCd
     *            パラメータコード
     * @param termCd
     *            期間コード
     * @param localeId
     *            ロケールID
     * @return エンティティ
     */
    public T getByPkWithLockNoException(String companyCd, String parameterCd, String termCd, String localeId) {
        return createPkSelect(companyCd, parameterCd, termCd, localeId)
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
        if (getByPkNoException(entity.getCompanyCd(), entity.getParameterCd(), entity.getTermCd(), entity.getLocaleId()) == null) {
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
        if (getByPkWithLockNoException(entity.getCompanyCd(), entity.getParameterCd(), entity.getTermCd(), entity.getLocaleId()) == null) {
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
        return createSelect().orderBy(asc(companyCd()), asc(parameterCd()), asc(termCd()), asc(localeId())).getResultList();
    }
}