/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.jdbc.OrderByItem;
import org.seasar.extension.jdbc.Where;
import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttrNames;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcAccountAttr}のDaoクラスです。
 * 
 */
public class CrmCcAccountAttrDao extends
        AbstractCrmCcAccountAttrDao<CrmCcAccountAttr> {

    /**
     * アカウント属性一覧の取得用SQL文です。
     */
    private static final String GET_ACCOUNT_ATTRIBUTE_SEARCH_ENTITY_LIST_SQL =
        "CrmCcAccountAttrDao_findByAccountAttributeSearchCriteriaDto.sql";

    /**
     * アカウント属性一覧検索の取得です。
     * 
     * @param dto
     *            検索条件
     * @return アカウント属性一覧
     */
    public PagingResult<AccountAttributeSearchResultDto> findByAccountAttributeSearchCriteriaDto(
            AccountAttributeSearchCriteriaDto dto) {

        AccountAttributeSearchCriteriaDto condition = convertLikeEspace(dto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        orderByItemList.add(new OrderByItem(CrmCcAccountAttrNames
            .crmAccountClassCd()));
        orderByItemList.add(new OrderByItem(CrmCcAccountAttrNames.dealClass()));
        orderByItemList.add(new OrderByItem(CrmCcAccountAttrNames
            .crmAccountStatus()));
        condition.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<AccountAttributeSearchResultDto> resultList =
            super.findPagingBySqlFile(
                AccountAttributeSearchResultDto.class,
                GET_ACCOUNT_ATTRIBUTE_SEARCH_ENTITY_LIST_SQL,
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
    private AccountAttributeSearchCriteriaDto convertLikeEspace(
            AccountAttributeSearchCriteriaDto criteria) {

        AccountAttributeSearchCriteriaDto cloned =
            criteria.cloneInstance(AccountAttributeSearchCriteriaDto.class);
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
    public List<CrmCcAccountAttr> findByCompanyCd(String companyCd,
            LogicalDelete logicalDelete) {

        return jdbcManager
            .from(CrmCcAccountAttr.class)
            .where(createFindByCompanyCdWhere(companyCd, logicalDelete))
            .getResultList();
    }

    /**
     * 会社コードとロケールIDの条件を生成します。
     * 
     * @param companyCd
     *            会社コード
     * @param logicalDelete
     *            論理削除
     * @return 検索条件
     */
    private Where createFindByCompanyCdWhere(String companyCd,
            LogicalDelete logicalDelete) {

        SimpleWhere where =
            new SimpleWhere().eq(CrmCcAccountAttrNames.companyCd(), companyCd);
        if (logicalDelete != LogicalDelete.NO_RESTRICTION) {
            where =
                where.eq(CrmCcAccountAttrNames.deleteFlag(), logicalDelete
                    .getValue());
        }
        return where;
    }

    /**
     * CRMアカウント属性一覧を取得
     * 
     * @param entity
     *            CRMアカウント属性Entity
     * 
     * @return CRMアカウント属性Entityリスト
     */
    public List<CrmCcAccountAttr> getEntityList(CrmCcAccountAttr entity) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        if (entity.getCompanyCd() != null) {
            str.append("company_cd = ? and ");
            values.add(entity.getCompanyCd());
        }
        if (entity.getDealClass() != null) {
            str.append("deal_class = ? and ");
            values.add(entity.getDealClass());
        }
        if (entity.getCrmAccountClassCd() != null) {
            str.append("crm_account_class_cd = ? and ");
            values.add(entity.getCrmAccountClassCd());
        }
        if (entity.getCrmAccountStatus() != null) {
            str.append("crm_account_status = ? and ");
            values.add(entity.getCrmAccountStatus());
        }
        if (entity.getVersionNo() != null) {
            str.append("version_no = ? and ");
            values.add(entity.getVersionNo());
        }
        str.append("delete_flag = '0'");

        List<CrmCcAccountAttr> resultList =
            jdbcManager.from(CrmCcAccountAttr.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;
    }

    /**
     * CRMアカウント分類クラスのPKで一覧を検索します。
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountClassCd
     *            アカウント分類コード
     * @return CRMアカウントグループ分類所属リスト
     */
    public List<CrmCcAccountAttr> findByCrmCcAccountClass(String companyCd,
            String crmAccountClassCd) {
        SimpleWhere where = new SimpleWhere();
        where.eq(CrmCcAccountAttrNames.companyCd(), companyCd);
        where.eq(CrmCcAccountAttrNames.crmAccountClassCd(), crmAccountClassCd);
        return jdbcManager
            .from(CrmCcAccountAttr.class)
            .where(where)
            .getResultList();

    }
}