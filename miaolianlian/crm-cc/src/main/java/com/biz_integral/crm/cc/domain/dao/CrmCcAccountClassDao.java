/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassNames.companyCd;
import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassNames.crmAccountClassCd;
import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassNames.deleteFlag;
import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassNames.localeId;

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.jdbc.OrderByItem;
import org.seasar.extension.jdbc.Where;
import org.seasar.extension.jdbc.exception.SNoResultException;
import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.dto.AccountClassSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassNames;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcAccountClass}のDaoクラスです。
 * 
 */
public class CrmCcAccountClassDao extends
        AbstractCrmCcAccountClassDao<CrmCcAccountClass> {

    /**
     * アカウント分類グループ一覧の取得用SQL文です。
     */
    private static final String GET_ACCOUNT_CLASS_ENTITY_LIST_SQL =
        "CrmCcAccountClassDao_findByAccountClassSelectDialogCriteriaDto.sql";

    /**
     * アカウント分類一覧の取得用SQL文です。
     */
    private static final String GET_ACCOUNT_CLASS_SEARCH_ENTITY_LIST_SQL =
        "CrmCcAccountClassDao_findByAccountClassSearchCriteriaDto.sql";

    /**
     * アカウント分類一覧の取得用SQL文です。
     */
    private static final String GET_COUNT_RELATION_TABLE =
        "CrmCcAccountClassDao_getCountRelationTable.sql";

    /**
     * アカウント分類一覧検索の取得です。
     * 
     * @param dto
     *            検索条件
     * @return アカウント分類一覧
     */
    public PagingResult<AccountClassSelectDialogResultDto> findByAccountClassSelectDialogCriteriaDto(
            AccountClassSelectDialogCriteriaDto dto) {

        AccountClassSelectDialogCriteriaDto condition = convertLikeEspace(dto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        orderByItemList.add(new OrderByItem("crmAccountClassCd"));
        condition.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<AccountClassSelectDialogResultDto> resultList =
            super.findPagingBySqlFile(
                AccountClassSelectDialogResultDto.class,
                GET_ACCOUNT_CLASS_ENTITY_LIST_SQL,
                condition);

        return resultList;
    }

    /**
     * 前方一致検索条件をSQLエスケープします。
     * 
     * @param criteria
     *            検索条件
     * @return 前方一致検索条件をSQLエスケープした検索条件
     */
    private AccountClassSelectDialogCriteriaDto convertLikeEspace(
            AccountClassSelectDialogCriteriaDto criteria) {

        AccountClassSelectDialogCriteriaDto cloned =
            criteria.cloneInstance(AccountClassSelectDialogCriteriaDto.class);
        cloned.crmAccountClassCd = likePrefix(criteria.crmAccountClassCd);
        cloned.crmAccountClassName = likeContain(criteria.crmAccountClassName);

        return cloned;
    }

    /**
     * ロケール無視リスト取得。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountClassCd
     *            CRMアカウント分類コード
     * @param logicalDelete
     *            削除フラグ
     * @return 検索結果リスト
     * @throws SNoResultException
     */
    public List<CrmCcAccountClass> findByPkIgnoreLocale(String companyCd,
            String crmAccountClassCd, LogicalDelete logicalDelete)
        throws SNoResultException {

        return addOrderBy(
            getAutoSelectFactory().normalSelect().where(
                createFindByPkIgnoreLocaleWhere(
                    companyCd,
                    crmAccountClassCd,
                    logicalDelete))).getResultList();
    }

    /**
     * 条件を生成します。<br>
     * 会社コードとアカウント状況を条件に設定します。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountClassCd
     *            アカウント状況
     * @param logicalDelete
     *            論理削除区分
     * @return 検索条件
     */
    private Where createFindByPkIgnoreLocaleWhere(String companyCd,
            String crmAccountClassCd, LogicalDelete logicalDelete) {

        SimpleWhere where =
            new SimpleWhere().eq(companyCd(), companyCd).eq(
                crmAccountClassCd(),
                crmAccountClassCd);
        if (logicalDelete != LogicalDelete.NO_RESTRICTION) {
            where = where.eq(deleteFlag(), logicalDelete.getValue());
        }
        return where;
    }

    /**
     * アカウント分類一覧検索の取得です。
     * 
     * @param dto
     *            検索条件
     * @return アカウント分類一覧
     */
    public PagingResult<AccountClassSearchResultDto> findByAccountClassSearchCriteriaDto(
            AccountClassSearchCriteriaDto dto) {

        AccountClassSearchCriteriaDto condition = convertLikeEspace(dto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        orderByItemList.add(new OrderByItem(CrmCcAccountClassNames
            .crmAccountClassCd()));
        condition.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<AccountClassSearchResultDto> resultList =
            super.findPagingBySqlFile(
                AccountClassSearchResultDto.class,
                GET_ACCOUNT_CLASS_SEARCH_ENTITY_LIST_SQL,
                condition);

        return resultList;
    }

    /**
     * 前方一致検索条件をSQLエスケープします。
     * 
     * @param criteria
     *            検索条件
     * @return 前方一致検索条件をSQLエスケープした検索条件
     */
    private AccountClassSearchCriteriaDto convertLikeEspace(
            AccountClassSearchCriteriaDto criteria) {

        AccountClassSearchCriteriaDto cloned =
            criteria.cloneInstance(AccountClassSearchCriteriaDto.class);
        cloned.crmAccountClassCd = likePrefix(criteria.crmAccountClassCd);
        cloned.crmAccountClassName = likeContain(criteria.crmAccountClassName);

        return cloned;
    }

    /**
     * アカウント分類を検索します。 条件に会社コードとロケールIDを利用します。
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param logicalDelete
     *            削除フラグ
     * @return 検索結果リスト
     */
    public List<CrmCcAccountClass> findByCompanyCdAndLocaleId(String companyCd,
            String localeId, LogicalDelete logicalDelete) {

        return jdbcManager.from(CrmCcAccountClass.class).where(
            createFindByCompanyCdAndLocaleIdWhere(
                companyCd,
                localeId,
                logicalDelete)).getResultList();
    }

    /**
     * 会社コードとロケールIDの条件を生成します。
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param logicalDelete
     * @return 検索条件
     */
    private Where createFindByCompanyCdAndLocaleIdWhere(String companyCd,
            String localeId, LogicalDelete logicalDelete) {

        SimpleWhere where =
            new SimpleWhere().eq(companyCd(), companyCd).eq(
                localeId(),
                localeId);
        if (logicalDelete != LogicalDelete.NO_RESTRICTION) {
            where = where.eq(deleteFlag(), logicalDelete.getValue());
        }
        return where;
    }

    /**
     * アカウント分類に関連するデータの件数を返却します。
     * 
     * @param entity
     * @return 関連データ件数
     */
    public long getCountRelationTable(CrmCcAccountClass entity) {
        return getCountBySqlFile(GET_COUNT_RELATION_TABLE, entity);
    }
}