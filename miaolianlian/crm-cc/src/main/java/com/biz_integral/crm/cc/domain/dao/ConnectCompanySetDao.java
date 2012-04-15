/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.ConnectCompanySetDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;

/**
 * {@link CrmCcDepartmentCmn}のDaoクラスです。
 * 
 */
public class ConnectCompanySetDao extends
        AbstractCrmCcDepartmentCmnDao<CrmCcDepartmentCmn> {

    /**
     * 所属組織一覧取得用SQL
     */
    private static final String GET_AFFILIATE_DEPARTMENT_LIST =
        "ConnectCompanySetDao_getAffiliateDepartmentList.sql";

    /**
     * 主所属組織取得用SQL
     */
    private static final String GET_MAIN_DEPARTMENT =
        "ConnectCompanySetDao_getMainDepartment.sql";

    /**
     * 所属組織一覧取得を行います。
     * 
     * @param criteria
     *            　接続会社設定モデル
     * @return　所属組織リスト
     */
    public List<ConnectCompanySetDto> getAffiliationDepartmentList(
            ConnectCompanySetDto criteria) {
        List<ConnectCompanySetDto> resultList =
            super.findBySqlFile(
                ConnectCompanySetDto.class,
                GET_AFFILIATE_DEPARTMENT_LIST,
                criteria);
        return resultList;
    }

    /**
     * 主所属組織取得を行います。
     * 
     * @param criteria
     *            　接続会社設定モデル
     * @return　所属組織Entity
     */
    public ConnectCompanySetDto getMainDepartment(ConnectCompanySetDto criteria) {
        ConnectCompanySetDto result = null;
        List<ConnectCompanySetDto> resultList =
            super.findBySqlFile(
                ConnectCompanySetDto.class,
                GET_MAIN_DEPARTMENT,
                criteria);
        if (resultList.size() > 0) {
            result = resultList.get(0);
        }

        return result;
    }

}