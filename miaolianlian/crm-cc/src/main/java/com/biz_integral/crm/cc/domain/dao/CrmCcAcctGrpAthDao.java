/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpAth;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * {@link CrmCcAcctGrpAth}のDaoクラスです。
 * 
 */
public class CrmCcAcctGrpAthDao extends
        AbstractCrmCcAcctGrpAthDao<CrmCcAcctGrpAth> {

    /**
     * CRMアカウントグループ所属一覧を取得
     * 
     * @param entity
     *            CRMアカウントグループ所属Entity
     * 
     * @return CRMアカウントグループ所属Entityリスト
     */
    public List<CrmCcAcctGrpAth> getEntityList(CrmCcAcctGrpAth entity) {
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
        if (entity.getCrmAccountCd() != null) {
            str.append("crm_account_cd = ? and ");
            values.add(entity.getCrmAccountCd());
        }
        if (entity.getTermCd() != null) {
            str.append("term_cd = ? and ");
            values.add(entity.getTermCd());
        }
        str.append("start_date <= ? and ");
        values.add(getBaseDate());
        str.append("end_date > ? and ");
        values.add(getBaseDate());
        str.append("delete_flag = '0'");

        List<CrmCcAcctGrpAth> resultList =
            jdbcManager.from(CrmCcAcctGrpAth.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;
    }

    /**
     * CRMアカウント名を取得
     * 
     * @param companyCd
     *            会社コード
     * @param crmAccountCd
     *            CRMアカウントコード
     * @param localeId
     *            ロケールID
     * 
     * @return String CRMアカウント名
     */
    public String getAcctName(String companyCd, String crmAccountCd,
            String localeId) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        str.append("company_cd = ? and ");
        values.add(companyCd);
        str.append("locale_id = ? and ");
        values.add(localeId);
        str.append("crm_account_cd = ? and ");
        values.add(crmAccountCd);
        str.append("start_date <= ? and ");
        values.add(getBaseDate());
        str.append("end_date > ? and ");
        values.add(getBaseDate());
        str.append("delete_flag = '0' order by delete_flag");

        List<CrmCcAccount> resultList =
            jdbcManager.from(CrmCcAccount.class).where(
                str.toString(),
                values.toArray()).getResultList();
        // アカウント名返す
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0).getCrmAccountName();
        }
        return null;
    }

    /**
     * システム日付取得
     * 
     * @return Date
     */
    private Date getBaseDate() {
        return DateUtil.parse(
            DateUtil.nowDateString(DateUtil.DATE_FORMAT),
            DateUtil.DATE_FORMAT);
    }

}