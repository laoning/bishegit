/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.Date;
import java.util.List;

import org.seasar.extension.jdbc.OrderByItem;
import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.entity.CrmCcDeptIncAthCmn;
import com.biz_integral.crm.cc.domain.entity.CrmCcDeptIncAthCmnNames;

/**
 * {@link CrmCcDeptIncAthCmn}のDaoクラスです。
 * 
 */
public class CrmCcDeptIncAthCmnDao extends
        AbstractCrmCcDeptIncAthCmnDao<CrmCcDeptIncAthCmn> {

    /**
     * 組織コードリストの下位組織を取得します。
     * 
     * @param companyCd
     *            会社コード
     * @param departmentSetCd
     *            会社組織セットコード
     * @param nowDate
     *            現在日付
     * @param departmentCdList
     *            組織コードリスト
     * @return 組織所属内包一覧
     */
    public List<CrmCcDeptIncAthCmn> findByDepartmentGetSubDepartment(
            String companyCd, String departmentSetCd, Date nowDate,
            List<String> departmentCdList) {
        SimpleWhere where = new SimpleWhere();
        where.eq(CrmCcDeptIncAthCmnNames.companyCd(), companyCd);
        where.eq(CrmCcDeptIncAthCmnNames.departmentSetCd(), departmentSetCd);
        where
            .in(CrmCcDeptIncAthCmnNames.parentDepartmentCd(), departmentCdList);
        where.le(CrmCcDeptIncAthCmnNames.startDate(), nowDate);
        where.gt(CrmCcDeptIncAthCmnNames.endDate(), nowDate);
        where.eq(CrmCcDeptIncAthCmnNames.deleteFlag(), false);

        OrderByItem departmentOrder =
            new OrderByItem(CrmCcDeptIncAthCmnNames.departmentCd());
        OrderByItem depthOrder =
            new OrderByItem(CrmCcDeptIncAthCmnNames.depth());

        return jdbcManager.from(CrmCcDeptIncAthCmn.class).where(where).orderBy(
            departmentOrder).orderBy(depthOrder).getResultList();

    }
}