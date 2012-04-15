/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * {@link CrmCcAcctGrpChargeDept}のDaoクラスです。
 * 
 */
public class CrmCcAcctGrpChargeDeptDao extends
        AbstractCrmCcAcctGrpChargeDeptDao<CrmCcAcctGrpChargeDept> {
    /**
     * CRMアカウントグループ担当組織を取得
     * 
     * @param crmCcAccountDto
     *            CRMアカウントグループDTO
     * @return CRMアカウントグループ
     */
    public List<CrmCcAcctGrpChargeDept> getEntityList(
            CrmCcAcctGrpChargeDept entity) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        if (entity.getCompanyCd() != null) {
            str.append("company_cd = ? and ");
            values.add(entity.getCompanyCd());
        }
        if (entity.getCrmAccountGroupCd() != null) {
            str.append("crm_account_group_cd = ? and ");
            values.add(entity.getCrmAccountGroupCd());
        }
        if (entity.getDepartmentCd() != null) {
            str.append("department_cd = ? and ");
            values.add(entity.getDepartmentCd());
        }
        if (entity.getCrmDomainCd() != null) {
            str.append("crm_domain_cd = ? and ");
            values.add(entity.getCrmDomainCd());
        }
        if (entity.getTermCd() != null) {
            str.append("term_cd = ? and ");
            values.add(entity.getTermCd());
        }
        str.append("start_date <= ? and ");
        values.add(getBaseDate());
        str.append("end_date > ? and ");
        values.add(getBaseDate());
        str.append("delete_flag = ?");
        values.add("0");

        List<CrmCcAcctGrpChargeDept> resultList =
            jdbcManager.from(CrmCcAcctGrpChargeDept.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;

    }

    /**
     * CRM組織名を取得
     * 
     * @param crmCcAccountDto
     *            CRMアカウントグループ所属Entity
     * 
     * @return CRMアカウントグループ担当所属Entityリスト
     */
    public String getDeptName(String companyCd, String departmentSetCd,
            String localeId, String departmentCd, Date baseDate) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        str.append("company_cd = ? and ");
        values.add(companyCd);
        str.append("department_set_cd = ? and ");
        values.add(departmentSetCd);
        str.append("locale_id = ? and ");
        values.add(localeId);
        str.append("departmentCd = ? and ");
        values.add(departmentCd);
        str.append("start_date <= ? and ");
        values.add(baseDate);
        str.append("end_date > ? and ");
        values.add(baseDate);
        str.append("delete_flag = '0'");

        List<CrmCcDepartmentCmn> resultList =
            jdbcManager.from(CrmCcDepartmentCmn.class).where(
                str.toString(),
                values.toArray()).getResultList();
        // 組織名返す
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0).getDepartmentName();
        }
        return null;
    }

    private Date getBaseDate() {
        // システム日付
        return DateUtil.parse(
            DateUtil.nowDateString(DateUtil.DATE_FORMAT),
            DateUtil.DATE_FORMAT);
    }

}