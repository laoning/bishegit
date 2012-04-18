/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem;

import com.biz_integral.crm.cc.domain.dto.UserDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.UserDialogReslutDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.td.domain.dto.AllTodoTargetImportCriteriaDto;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcUserCmn}のDaoクラスです。
 * 
 */
public class CrmCcUserCmnDao extends AbstractCrmCcUserCmnDao<CrmCcUserCmn> {

    /**
     * ユーザ一覧の取得用SQL文です。
     */
    private static final String GET_USER_ENTITY_LIST_SQL =
        "CrmCcUserCmnDao_findByUserDialogCriteriaDto.sql";

    /**
     * ユーザ_共通一覧の取得用SQL文です。
     */
    private static final String FIND_ENTITYLIST_BY_USERCODE_SQL =
        "CrmCcUserCmnDao_findUserCmnEntityList.sql";

    /**
     * {@link ContextContainer}です
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link ParameterLogic}です
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * ユーザ一覧検索の取得です。
     * 
     * @param dto
     *            検索条件
     * @return ユーザ一覧
     */
    public PagingResult<UserDialogReslutDto> findByUserDialogCriteriaDto(
            UserDialogCriteriaDto dto) {
        UserDialogCriteriaDto condition = convertLikeEspace(dto);

        List<OrderByItem> orderByItemList = new ArrayList<OrderByItem>();
        OrderByItem item1 = new OrderByItem("userCd");
        OrderByItem item2 = new OrderByItem("departmentCd");
        orderByItemList.add(item1);
        orderByItemList.add(item2);
        condition.setDefaultOrderBySpecs(orderByItemList);

        PagingResult<UserDialogReslutDto> resultList =
            super.findPagingBySqlFile(
                UserDialogReslutDto.class,
                GET_USER_ENTITY_LIST_SQL,
                condition);

        for (UserDialogReslutDto resultDto : resultList.getResultList()) {
            if (resultDto.getEndDate() != null) {
                resultDto.setEndDate(DateUtil.getCalculator(
                    resultDto.getEndDate()).addDay(-1).asDate());
            }
        }
        return resultList;
    }

    /**
     * 前方一致検索条件をSQLエスケープします。
     * 
     * @param criteria
     *            検索条件
     * @return 前方一致検索条件をSQLエスケープした検索条件
     */
    private UserDialogCriteriaDto convertLikeEspace(
            UserDialogCriteriaDto criteria) {
        UserDialogCriteriaDto cloned =
            criteria.cloneInstance(UserDialogCriteriaDto.class);
        cloned.userCd = likePrefix(criteria.userCd);
        cloned.userName = likeContain(criteria.userName);
        cloned.userShortName = likeContain(criteria.userShortName);
        return cloned;
    }

    /**
     * ユーザ_共通を一覧検索します。
     * 
     * @param targetUserList
     *            全社ToDo対象モデルリスト
     * @return ユーザ_共通一覧
     */
    public List<CrmCcUserCmn> findUserCmnList(
            List<AllTodoTargetImportCriteriaDto> targetUserList) {
        List<CrmCcUserCmn> resultList = new ArrayList<CrmCcUserCmn>();

        String companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        String departmentSetCd = (String) parameterLogic.getEntity("CRMCC0004");

        for (AllTodoTargetImportCriteriaDto criteriaDto : targetUserList) {
            criteriaDto.companyCd = companyCd;
            criteriaDto.departmentSetCd = departmentSetCd;
            List<CrmCcUserCmn> crmCcUserCmnList =
                super.findBySqlFile(
                    CrmCcUserCmn.class,
                    FIND_ENTITYLIST_BY_USERCODE_SQL,
                    criteriaDto);

            for (CrmCcUserCmn crmCcUserCmn : crmCcUserCmnList) {
                resultList.add(crmCcUserCmn);
            }
        }
        return resultList;
    }

    /**
     * ユーザ_共通の取得です。
     * 
     * @param crmCcUserCmn
     *            条件
     * @return ユーザ_共通
     */
    public CrmCcUserCmn getEntity(CrmCcUserCmn crmCcUserCmn) {

        StringBuffer sql = new StringBuffer();
        sql.append("user_cd = ? AND ");
        sql.append("locale_id = ? AND ");
        sql.append("start_date <= ? AND ");
        sql.append("end_date > ? AND ");
        sql.append("delete_flag = '0'");
        List<Object> data = new ArrayList<Object>();
        data.add(crmCcUserCmn.getUserCd());
        data.add(crmCcUserCmn.getLocaleId());
        data.add(crmCcUserCmn.getStartDate());
        data.add(crmCcUserCmn.getEndDate());

        return jdbcManager.from(CrmCcUserCmn.class).where(
            sql.toString(),
            data.toArray()).getSingleResult();
    }

    /**
     * ユーザ_共通の取得です。
     * 
     * @param crmCcUserCmn
     *            条件
     * @return List<ユーザ_共通>
     */
    public List<CrmCcUserCmn> getEntityList(CrmCcUserCmn crmCcUserCmn) {

        StringBuffer sql = new StringBuffer();
        sql.append("locale_id = ? AND ");
        sql.append("start_date <= ? AND ");
        sql.append("end_date > ? AND ");
        sql.append("(");
        sql.append("email_address1 = ? OR ");
        sql.append("email_address2 = ? OR ");
        sql.append("mobile_email_address = ? ");
        sql.append(") AND ");
        sql.append("delete_flag = '0'");

        List<Object> data = new ArrayList<Object>();
        data.add(crmCcUserCmn.getLocaleId());
        data.add(crmCcUserCmn.getStartDate());
        data.add(crmCcUserCmn.getEndDate());
        data.add(crmCcUserCmn.getEmailAddress1());
        data.add(crmCcUserCmn.getEmailAddress1());
        data.add(crmCcUserCmn.getEmailAddress1());

        return jdbcManager.from(CrmCcUserCmn.class).where(
            sql.toString(),
            data.toArray()).getResultList();
    }

}