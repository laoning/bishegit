/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.dto.SameDepartmentUserDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentAthCmn;
import com.biz_integral.service.security.authorization.department.DepartmentAuthorizationEvaluator;

/**
 * {@link CrmCcDepartmentAthCmn}のDaoクラスです。
 * 
 */
public class CrmCcDepartmentAthCmnDao extends
        AbstractCrmCcDepartmentAthCmnDao<CrmCcDepartmentAthCmn> {
    /**
     * {@link DepartmentAuthorizationEvaluator 機能アクセス権限}
     */
    @Resource
    protected DepartmentAuthorizationEvaluator departmentAuthorizationEvaluator;

    /**
     * 
     * ユーザコード一覧を検索します。
     * 
     * @param departmentCdList
     *            　組織コードリスト
     * @param departmentSetCd
     *            組織セットコード
     * @return ユーザーコードリスト
     */

    public List<String> findByDepartmentCdList(List<String> departmentCdList,
            String departmentSetCd) {

        // 会社コードを取得します。
        String companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        // ユーザコード一覧を取得します。
        List<CrmCcDepartmentAthCmn> crmCcDepartmentAthCmnList =
            jdbcManager.from(CrmCcDepartmentAthCmn.class).where(
                new SimpleWhere().eq("companyCd", companyCd).eq(
                    "departmentSetCd",
                    departmentSetCd).in("departmentCd", departmentCdList).le(
                    "startDate",
                    Calendar.getInstance().getTime()).gt(
                    "endDate",
                    Calendar.getInstance().getTime()).eq(
                    "deleteFlag",
                    Boolean.valueOf("0"))).getResultList();

        List<String> userCdList = new ArrayList<String>();
        if (null != crmCcDepartmentAthCmnList
            && (0 < crmCcDepartmentAthCmnList.size())) {
            for (CrmCcDepartmentAthCmn crmCcDepartmentAthCmn : crmCcDepartmentAthCmnList) {
                userCdList.add(crmCcDepartmentAthCmn.getUserCd());
            }
        }
        return userCdList;
    }

    /**
     * 
     * 組織コード一覧を検索します。
     * 
     * @param userCd
     *            ユーザーコード
     * @param departmentSetCd
     *            組織セットコード
     * @return 組織コード一覧
     */
    public List<String> fingByUserCd(String userCd, String departmentSetCd) {
        // 会社コードを取得します。
        String companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        // 組織コード一覧を取得します。
        List<CrmCcDepartmentAthCmn> crmCcDepartmentAthCmnList =
            jdbcManager.from(CrmCcDepartmentAthCmn.class).where(
                new SimpleWhere().eq("companyCd", companyCd).eq(
                    "departmentSetCd",
                    departmentSetCd).eq("userCd", userCd).le(
                    "startDate",
                    Calendar.getInstance().getTime()).gt(
                    "endDate",
                    Calendar.getInstance().getTime()).eq(
                    "deleteFlag",
                    Boolean.valueOf("0"))).getResultList();

        List<String> departmentCdList = new ArrayList<String>();
        for (CrmCcDepartmentAthCmn crmCcDepartmentAthCmn : crmCcDepartmentAthCmnList) {
            departmentCdList.add(crmCcDepartmentAthCmn.getDepartmentCd());
        }

        return departmentCdList;
    }

    // solr用の赤坂追加分

    /** 対象ユーザの所属組織の組織所属一覧 */
    private static final String FIND_BY_DEPARTMENT_SQL =
        "CrmCcDepartmentAthCmnDao_findByDepartment.sql";

    /**
     * 対象ユーザの所属組織のデータを一覧取得します。
     * 
     * @param dto
     *            同一組織所属ユーザ検索条件
     * @return 組織所属一覧
     */
    public List<CrmCcDepartmentAthCmn> findByDepartment(
            SameDepartmentUserDto dto) {
        return findBySqlFile(
            CrmCcDepartmentAthCmn.class,
            FIND_BY_DEPARTMENT_SQL,
            dto);
    }
}