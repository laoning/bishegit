/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.jdbc.OrderByItem;
import org.seasar.extension.jdbc.Where;
import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttrNames;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcAccountGrpAttr}のDaoクラスです。
 * 
 */
public class CrmCcAccountGrpAttrDao extends
        AbstractCrmCcAccountGrpAttrDao<CrmCcAccountGrpAttr> {

    /**
     * CRMアカウントグループ属性一覧を取得
     * 
     * @param entity
     *            CRMアカウントグループ属性Entity
     * 
     * @return CRMアカウントグループ属性Entityリスト
     */
    public List<CrmCcAccountGrpAttr> getEntityList(CrmCcAccountGrpAttr entity) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        if (entity.getCompanyCd() != null) {
            str.append("company_cd = ? and ");
            values.add(entity.getCompanyCd());
        }
        if (entity.getCrmAccountClassCd() != null) {
            str.append("crm_account_class_cd = ? and ");
            values.add(entity.getCrmAccountClassCd());
        }
        if (entity.getDealClass() != null) {
            str.append("deal_class = ? and ");
            values.add(entity.getDealClass());
        }
        if (entity.getVersionNo() != null) {
            str.append("version_no = ? and ");
            values.add(entity.getVersionNo());
        }
        str.append("delete_flag = '0'");

        List<CrmCcAccountGrpAttr> resultList =
            jdbcManager.from(CrmCcAccountGrpAttr.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;
    }

    /**
     * アカウント属性一覧の取得用SQL文です。
     */
    private static final String GET_ACCOUNT_GROUP_ATTRIBUTE_SEARCH_ENTITY_LIST_SQL =
        "CrmCcAccountGrpAttrDao_findByAccountGroupAttributeSearchCriteriaDto.sql";

    /**
     * アカウント属性一覧検索の取得です。
     * 
     * @param dto
     *            検索条件
     * @return アカウント属性一覧
     */
    public PagingResult<AccountGroupAttributeSearchResultDto> findByAccountGroupAttributeSearchCriteriaDto(
            AccountGroupAttributeSearchCriteriaDto dto) {

        AccountGroupAttributeSearchCriteriaDto condition =
            convertLikeEspace(dto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        orderByItemList.add(new OrderByItem(CrmCcAccountGrpAttrNames
            .crmAccountClassCd()));
        orderByItemList.add(new OrderByItem(CrmCcAccountGrpAttrNames
            .dealClass()));

        condition.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<AccountGroupAttributeSearchResultDto> resultList =
            super.findPagingBySqlFile(
                AccountGroupAttributeSearchResultDto.class,
                GET_ACCOUNT_GROUP_ATTRIBUTE_SEARCH_ENTITY_LIST_SQL,
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
    private AccountGroupAttributeSearchCriteriaDto convertLikeEspace(
            AccountGroupAttributeSearchCriteriaDto criteria) {

        AccountGroupAttributeSearchCriteriaDto cloned =
            criteria
                .cloneInstance(AccountGroupAttributeSearchCriteriaDto.class);
        cloned.crmAccountClassCd = likePrefix(criteria.crmAccountClassCd);
        cloned.crmAccountClassName = likeContain(criteria.crmAccountClassName);

        return cloned;
    }

    /**
     * アカウント属性を検索します。 条件に会社コードを利用します。
     * 
     * @param companyCd
     *            会社コード
     * @param logicalDelete
     *            削除フラグ
     * @return 検索結果リスト
     */
    public List<CrmCcAccountGrpAttr> findByCompanyCd(String companyCd,
            LogicalDelete logicalDelete) {

        return jdbcManager
            .from(CrmCcAccountGrpAttr.class)
            .where(createFindByCompanyCdWhere(companyCd, logicalDelete))
            .getResultList();
    }

    /**
     * 会社コードとロケールIDの条件を生成します。
     * 
     * @param companyCd
     *            会社コード
     * @param logicalDelete
     *            削除フラグ
     * @return 検索条件
     */
    private Where createFindByCompanyCdWhere(String companyCd,
            LogicalDelete logicalDelete) {

        SimpleWhere where =
            new SimpleWhere().eq(
                CrmCcAccountGrpAttrNames.companyCd(),
                companyCd);
        if (logicalDelete != LogicalDelete.NO_RESTRICTION) {
            where =
                where.eq(CrmCcAccountGrpAttrNames.deleteFlag(), logicalDelete
                    .getValue());
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
     * @return CRMアカウントグループ属性リスト
     */
    public List<CrmCcAccountGrpAttr> findByCrmCcAccountClass(String companyCd,
            String crmAccountClassCd) {
        SimpleWhere where = new SimpleWhere();
        where.eq(CrmCcAccountGrpAttrNames.companyCd(), companyCd);
        where.eq(
            CrmCcAccountGrpAttrNames.crmAccountClassCd(),
            crmAccountClassCd);
        return jdbcManager
            .from(CrmCcAccountGrpAttr.class)
            .where(where)
            .getResultList();

    }

    /**
     * CRMアカウントグループ属性一覧を取得
     * 
     * @param entity
     *            CRMアカウントグループ属性Entity
     * 
     * @return CRMアカウントグループ属性Entity
     */
    public CrmCcAccountGrpAttr getEntityByPk(CrmCcAccountGrpAttr entity) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        str.append("company_cd = ? and ");
        values.add(entity.getCompanyCd());
        str.append("crm_account_class_cd = ? and ");
        values.add(entity.getCrmAccountClassCd());
        str.append("deal_class = ? ");
        values.add(entity.getDealClass());

        CrmCcAccountGrpAttr result =
            jdbcManager.from(CrmCcAccountGrpAttr.class).where(
                str.toString(),
                values.toArray()).getSingleResult();
        return result;
    }
}