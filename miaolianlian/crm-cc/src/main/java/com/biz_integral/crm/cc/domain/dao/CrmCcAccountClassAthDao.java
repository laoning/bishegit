/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAthNames.companyCd;
import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAthNames.crmAccountCd;
import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAthNames.crmAccountClassCd;
import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassNames.deleteFlag;

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.jdbc.OrderByItem;
import org.seasar.extension.jdbc.Where;
import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.dto.AccountClassAthSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAthNames;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcAccountClassAth}のDaoクラスです。
 * 
 */
public class CrmCcAccountClassAthDao extends
        AbstractCrmCcAccountClassAthDao<CrmCcAccountClassAth> {

    /**
     * アカウント分類所属一覧の取得用SQL文です。
     */
    private static final String GET_ACCOUNT_CLASS_ATH_LIST_SQL =
        "CrmCcAccountClassAthDao_findByAccountBelongClassSearchCriteriaDto.sql";

    /**
     * アカウント分類所属一覧の取得用SQL文です。（登録/更新用）
     */
    private static final String GET_ACCOUNT_CLASS_ATH_LIST_FOR_EDIT_SQL =
        "CrmCcAccountClassAthDao_findByAccountBelongClassSearchCriteriaDtoForEdit.sql";

    /**
     * アカウント分類所属を一覧検索します。
     * 
     * @param dto
     *            アカウント分類所属検索条件
     * @return アカウント分類所属検索結果一覧
     */
    public PagingResult<AccountClassAthSearchResultDto> findByAccountClassAthSearchCriteriaDto(
            CrmCcAccountClassAthSearchCriteriaDto dto) {

        CrmCcAccountClassAthSearchCriteriaDto condition =
            convertLikeEspace(dto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        orderByItemList.add(new OrderByItem("crmAccountClassCd"));
        condition.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<AccountClassAthSearchResultDto> resultList =
            super.findPagingBySqlFile(
                AccountClassAthSearchResultDto.class,
                GET_ACCOUNT_CLASS_ATH_LIST_SQL,
                condition);

        return resultList;
    }

    /**
     * アカウント分類所属を一覧検索します。（登録/更新用）
     * 
     * @param dto
     *            アカウント分類所属検索条件
     * @return アカウント分類所属検索結果一覧
     */
    public PagingResult<AccountClassAthSearchResultDto> findByAccountClassAthSearchCriteriaDtoForEdit(
            CrmCcAccountClassAthSearchCriteriaDto dto) {

        CrmCcAccountClassAthSearchCriteriaDto condition =
            convertLikeEspace(dto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        orderByItemList.add(new OrderByItem("crmAccountClassCd"));
        condition.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<AccountClassAthSearchResultDto> resultList =
            super.findPagingBySqlFile(
                AccountClassAthSearchResultDto.class,
                GET_ACCOUNT_CLASS_ATH_LIST_FOR_EDIT_SQL,
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
    private CrmCcAccountClassAthSearchCriteriaDto convertLikeEspace(
            CrmCcAccountClassAthSearchCriteriaDto criteria) {
        CrmCcAccountClassAthSearchCriteriaDto cloned =
            criteria.cloneInstance(CrmCcAccountClassAthSearchCriteriaDto.class);
        cloned.crmAccountCd = likePrefix(criteria.crmAccountCd);
        cloned.crmAccountName = likeContain(criteria.crmAccountName);
        cloned.crmAccountClassCd = likePrefix(criteria.crmAccountClassCd);
        cloned.crmAccountClassName = likeContain(criteria.crmAccountClassName);
        return cloned;
    }

    /**
     * アカウント分類所属を一覧検索します。
     * 
     * @param entity
     *            アカウント分類所属エンティティ
     * @return アカウント分類所属エンティティ
     */
    public CrmCcAccountClassAth getEntity(CrmCcAccountClassAth entity) {

        return jdbcManager
            .from(CrmCcAccountClassAth.class)
            .where(
                createGetCrmCcAccountClassAthWhere(
                    entity.getCompanyCd(),
                    entity.getCrmAccountClassCd(),
                    entity.getCrmAccountCd()))
            .getSingleResult();
    }

    /**
     * アカウント分類所属の一覧検索用の検索条件を作成して返します.
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountClassCd
     *            CRMアカウント分類コード
     * @param crmAccountCd
     *            CRMアカウントコード
     * @return 検索条件
     */
    private Where createGetCrmCcAccountClassAthWhere(String companyCd,
            String crmAccountClassCd, String crmAccountCd) {

        SimpleWhere where =
            new SimpleWhere().eq(companyCd(), companyCd).eq(
                crmAccountClassCd(),
                crmAccountClassCd).eq(crmAccountCd(), crmAccountCd);

        return where;
    }

    /**
     * アカウント分類所属を一覧検索します。
     * 
     * @param companyCd
     *            会社コード
     * @param logicalDelete
     *            削除フラグ
     * @return 検索結果リスト
     */
    public List<CrmCcAccountClassAth> findByCompanyCd(String companyCd,
            LogicalDelete logicalDelete) {

        return jdbcManager
            .from(CrmCcAccountClassAth.class)
            .where(createFindByCompanyCdWhere(companyCd, logicalDelete))
            .getResultList();
    }

    /**
     * アカウント分類所属の一覧検索用の検索条件を作成して返します.
     * 
     * @param companyCd
     *            会社コード
     * @param logicalDelete
     *            論理削除
     * @return 検索条件
     */
    private Where createFindByCompanyCdWhere(String companyCd,
            LogicalDelete logicalDelete) {

        SimpleWhere where = new SimpleWhere().eq(companyCd(), companyCd);
        if (logicalDelete != LogicalDelete.NO_RESTRICTION) {
            where = where.eq(deleteFlag(), logicalDelete.getValue());
        }
        return where;
    }

    /**
     * アカウント分類所属を一覧検索します。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountClassCd
     *            CRMアカウント分類コード
     * @param logicalDelete
     *            削除フラグ
     * @return 検索結果リスト
     */
    public List<CrmCcAccountClassAth> findByCompanyCdAndCrmAccountClassCd(
            String companyCd, String crmAccountClassCd,
            LogicalDelete logicalDelete) {

        return jdbcManager.from(CrmCcAccountClassAth.class).where(
            createFindByCompanyCdAndCrmAccountClassCdWhere(
                companyCd,
                crmAccountClassCd,
                logicalDelete)).getResultList();
    }

    /**
     * アカウント分類所属の一覧検索用の検索条件を作成して返します.
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountClassCd
     *            CRMアカウント分類コード
     * @param logicalDelete
     *            論理削除
     * @return 検索条件
     */
    private Where createFindByCompanyCdAndCrmAccountClassCdWhere(
            String companyCd, String crmAccountClassCd,
            LogicalDelete logicalDelete) {

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
     * CRMアカウント分類クラスのPKで一覧を検索します。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountClassCd
     *            アカウント分類コード
     * @return CRMアカウント分類所属リスト
     */
    public List<CrmCcAccountClassAth> findByCrmCcAccountClass(String companyCd,
            String crmAccountClassCd) {
        SimpleWhere where = new SimpleWhere();
        where.eq(CrmCcAccountClassAthNames.companyCd(), companyCd);
        where.eq(
            CrmCcAccountClassAthNames.crmAccountClassCd(),
            crmAccountClassCd);
        return jdbcManager
            .from(CrmCcAccountClassAth.class)
            .where(where)
            .getResultList();

    }
}