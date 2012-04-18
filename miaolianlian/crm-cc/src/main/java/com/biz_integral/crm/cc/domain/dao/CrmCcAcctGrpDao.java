/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.extension.jdbc.OrderByItem;
import org.seasar.extension.jdbc.where.ComplexWhere;

import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogResultDto;
import com.biz_integral.crm.cc.domain.dto.AcctGrpDto;
import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrp;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcAcctGrp}のDaoクラスです。
 * 
 */
public class CrmCcAcctGrpDao extends AbstractCrmCcAcctGrpDao<CrmCcAcctGrp> {

    /**
     * アカウントグループ一覧の取得用SQL文です。
     */
    private static final String GET_ACCT_GRP_ENTITY_LIST_SQL =
        "CrmCcAcctGrpDao_findByAccountGroupSelectDialogCriteriaDto.sql";

    /**
     * アカウントグループ一覧の取得用SQL文です。
     */
    private static final String GET_ACCT_GRP_SEARCH_ENTITY_LIST_SQL =
        "CrmCcAcctGrpDao_findByAcctGrpSearchCriteriaDto.sql";

    /**
     * アカウントグループ一覧検索の取得です。
     * 
     * @param dto
     *            検索条件
     * @return アカウントグループ一覧
     */
    public PagingResult<AccountGroupSelectDialogResultDto> findByAccountGroupSelectDialogCriteriaDto(
            AccountGroupSelectDialogCriteriaDto dto) {

        AccountGroupSelectDialogCriteriaDto condition = convertLikeEspace(dto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        orderByItemList.add(new OrderByItem("crmAccountGroupCd"));
        condition.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<AccountGroupSelectDialogResultDto> resultList =
            super.findPagingBySqlFile(
                AccountGroupSelectDialogResultDto.class,
                GET_ACCT_GRP_ENTITY_LIST_SQL,
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
    private AccountGroupSelectDialogCriteriaDto convertLikeEspace(
            AccountGroupSelectDialogCriteriaDto criteria) {

        AccountGroupSelectDialogCriteriaDto cloned =
            criteria.cloneInstance(AccountGroupSelectDialogCriteriaDto.class);
        cloned.crmAccountGroupCd = likePrefix(criteria.crmAccountGroupCd);
        cloned.crmAccountGroupName = likeContain(criteria.crmAccountGroupName);
        cloned.crmAccountGroupSearchName =
            likeContain(criteria.crmAccountGroupSearchName);

        return cloned;
    }

    /**
     * アカウントグループ一覧検索の取得です。
     * 
     * @param dto
     *            検索条件
     * @return アカウントグループ一覧
     */
    public PagingResult<AcctGrpSearchResultDto> findByAcctGrpSearchCriteriaDto(
            AcctGrpSearchCriteriaDto dto) {

        AcctGrpSearchCriteriaDto condition = convertLikeEspace(dto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        orderByItemList.add(new OrderByItem("crmAccountGroupCd"));
        condition.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<AcctGrpSearchResultDto> resultList =
            super.findPagingBySqlFile(
                AcctGrpSearchResultDto.class,
                GET_ACCT_GRP_SEARCH_ENTITY_LIST_SQL,
                condition);

        return resultList;
    }

    /**
     * アカウントグループ所属一覧検索です。
     * 
     * @param dto
     *            検索条件
     * @return アカウントモデル一覧
     */
    public PagingResult<AcctGrpDto> findAcctGrpAthList(AcctGrpDto dto) {

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        orderByItemList.add(new OrderByItem("crmAccountCd"));
        dto.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<AcctGrpDto> result =
            super.findPagingBySqlFile(
                AcctGrpDto.class,
                "CrmCcAcctGrpDao_findAcctGrpAthList.sql",
                dto);
        return result;
    }

    /**
     * 
     * 移動されるアカウントグループ一覧取得.
     * <p>
     * 引数の条件に従ってアカウントグループ階層一覧取得.を検索し、検索結果を返却します。
     * </p>
     * 
     * @param String
     *            <ul>
     *            <li>引数の会社コード
     *            <li>引数のロケールID
     *            <li>引数のCRMアカウントグループコード
     *            </ul>
     * @return リスト＜アカウントグループ階層＞
     */
    public Long getCountByGroupCd(String companyCd, String localeId,
            String crmAccountGroupCd) {
        ComplexWhere where = new ComplexWhere();
        where.eq("companyCd", companyCd);
        where.eq("localeId", localeId);
        where.eq("crmAccountGroupCd", crmAccountGroupCd);
        where.eq("deleteFlag", false);
        Long cnt = super.getCountByWhere(where);
        return cnt;
    }

    /**
     * 登録前CRMアカウントグループ取得
     * 
     * @param crmCcAccountDto
     *            CRMアカウントグループDTO
     * @return CRMアカウントグループ
     */
    public List<CrmCcAcctGrp> getEntity(CrmCcAcctGrp entity) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        getSqlWhere(entity, str, values);
        List<CrmCcAcctGrp> resultList =
            jdbcManager.from(CrmCcAcctGrp.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;

    }

    /**
     * 登録前CRMアカウントグループ取得
     * 
     * @param crmCcAccountDto
     *            CRMアカウントグループDTO
     * @return CRMアカウントグループ
     */
    public CrmCcAcctGrp getEntity(String company_cd,
            String crm_account_group_cd, String locale_id, String delete_flag,
            String version_no) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        if (!StringUtils.isEmpty(company_cd)) {
            str.append("company_cd = ? and ");
            values.add(company_cd);
        }
        if (!StringUtils.isEmpty(crm_account_group_cd)) {
            str.append("crm_account_group_cd = ? and ");
            values.add(crm_account_group_cd);
        }
        if (!StringUtils.isEmpty(locale_id)) {
            str.append("locale_id = ? ");
            values.add(locale_id);
        }
        if (!StringUtils.isEmpty(delete_flag)) {
            str.append(" and delete_flag = ?  ");
            values.add(delete_flag);
        }
        if (!StringUtils.isEmpty(version_no)) {
            str.append(" and version_no = ?  ");
            values.add(version_no);
        }
        CrmCcAcctGrp result =
            jdbcManager.from(CrmCcAcctGrp.class).where(
                str.toString(),
                values.toArray()).getSingleResult();
        return result;

    }

    /**
     * CRMアカウントグループのSQLの設定です。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントグループDTO
     * @param str
     *            SQL
     * @param values
     *            SQLパラメータ
     * @return SQL
     */
    private StringBuilder getSqlWhere(CrmCcAcctGrp crmCcAcctGrp,
            StringBuilder str, List<Object> values) {
        boolean found = false;
        if (crmCcAcctGrp.getCompanyCd() != null) {
            str.append("company_cd = ?");
            values.add(crmCcAcctGrp.getCompanyCd());
            found = true;
        }
        if (crmCcAcctGrp.getCrmAccountGroupCd() != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("crm_account_group_cd = ?");
            values.add(crmCcAcctGrp.getCrmAccountGroupCd());
            found = true;
        }
        if (crmCcAcctGrp.getLocaleId() != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("locale_id = ?");
            values.add(crmCcAcctGrp.getLocaleId());
            found = true;
        }
        if (found) {
            str.append(" and ");
        }
        str.append("start_date <= ?");
        values.add(getBaseDate());
        found = true;
        if (found) {
            str.append(" and ");
        }
        str.append("end_date > ?");
        values.add(getBaseDate());
        found = true;
        if (found) {
            str.append(" and ");
        }
        str.append("delete_flag = 0");
        return str;
    }

    /**
     * システム日付を取得
     * 
     * @return システム日付
     */
    private Date getBaseDate() {
        return DateUtil.parse(
            DateUtil.nowDateString(DateUtil.DATE_FORMAT),
            DateUtil.DATE_FORMAT);
    }

    /**
     * 前方一致検索条件をSQLエスケープします。
     * 
     * @param criteria
     *            検索条件
     * @return 前方一致検索条件をSQLエスケープした検索条件
     */
    private AcctGrpSearchCriteriaDto convertLikeEspace(
            AcctGrpSearchCriteriaDto criteria) {

        AcctGrpSearchCriteriaDto cloned =
            criteria.cloneInstance(AcctGrpSearchCriteriaDto.class);
        cloned.crmAccountGroupCd = likePrefix(criteria.crmAccountGroupCd);
        cloned.crmAccountGroupName = likeContain(criteria.crmAccountGroupName);
        cloned.crmAccountGroupSearchName =
            likeContain(criteria.crmAccountGroupSearchName);

        return cloned;
    }
}