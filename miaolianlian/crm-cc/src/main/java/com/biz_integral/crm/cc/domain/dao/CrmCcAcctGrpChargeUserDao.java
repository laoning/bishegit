/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeUser;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * {@link CrmCcAcctGrpChargeUser}のDaoクラスです。
 * 
 */
public class CrmCcAcctGrpChargeUserDao extends
        AbstractCrmCcAcctGrpChargeUserDao<CrmCcAcctGrpChargeUser> {
    /**
     * 登録前CRMアカウントグループコードの重複をチェックします。
     * 
     * @param crmCcAccountDto
     *            CRMアカウントグループDTO
     * @return CRMアカウントグループ
     */
    public List<CrmCcAcctGrpChargeUser> getEntityList(
            CrmCcAcctGrpChargeUser entity) {
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
        if (entity.getUserCd() != null) {
            str.append("user_cd = ? and ");
            values.add(entity.getUserCd());
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

        List<CrmCcAcctGrpChargeUser> resultList =
            jdbcManager.from(CrmCcAcctGrpChargeUser.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;

    }

    /**
     * ユーザ名を取得
     * 
     * @param crmCcAccountDto
     *            CRMアカウントグループ所属Entity
     * 
     * @return CRMアカウントグループ担当所属Entityリスト
     */
    public String getUserName(String userCd, String localeId, Date baseDate) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        str.append("user_cd = ? and ");
        values.add(userCd);
        str.append("locale_id = ? and ");
        values.add(localeId);
        str.append("start_date <= ? and ");
        values.add(baseDate);
        str.append("end_date > ? and ");
        values.add(baseDate);
        str.append("delete_flag = '0'");

        List<CrmCcUserCmn> resultList =
            jdbcManager.from(CrmCcUserCmn.class).where(
                str.toString(),
                values.toArray()).getResultList();
        // ユーザ名を返す
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0).getUserName();
        }
        return null;
    }

    // システム日付
    private Date getBaseDate() {
        return DateUtil.parse(
            DateUtil.nowDateString(DateUtil.DATE_FORMAT),
            DateUtil.DATE_FORMAT);
    }

}