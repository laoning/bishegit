/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpClassAth;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpClassAthNames;

/**
 * {@link CrmCcAcctGrpClassAth}のDaoクラスです。
 * 
 */
public class CrmCcAcctGrpClassAthDao extends
        AbstractCrmCcAcctGrpClassAthDao<CrmCcAcctGrpClassAth> {

    /**
     * CRMアカウントグループ分類所属を取得
     * 
     * @param String
     *            companyCd CRMアカウントグループ分類所属Entity
     * 
     * @return CRMアカウントグループ分類所属Entityリスト
     */
    public List<CrmCcAcctGrpClassAth> getEntityList(String companyCd,
            String crmAccountGroupCd, String crmAccountClassCd) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        str.append("company_cd = ? and ");
        values.add(companyCd);
        if (!StringUtils.isEmpty(crmAccountGroupCd)) {
            str.append("crm_account_group_cd = ? and ");
            values.add(crmAccountGroupCd);
        }
        if (!StringUtils.isEmpty(crmAccountClassCd)) {
            str.append("crm_account_class_cd = ? and ");
            values.add(crmAccountClassCd);
        }

        str.append("delete_flag = '0'");

        System.out.println(str.toString());
        System.out.println(values.toString());

        List<CrmCcAcctGrpClassAth> resultList =
            jdbcManager.from(CrmCcAcctGrpClassAth.class).where(
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
    public List<CrmCcAcctGrpClassAth> findByCrmCcAccountClass(String companyCd,
            String crmAccountClassCd) {
        SimpleWhere where = new SimpleWhere();
        where.eq(CrmCcAcctGrpClassAthNames.companyCd(), companyCd);
        where.eq(
            CrmCcAcctGrpClassAthNames.crmAccountClassCd(),
            crmAccountClassCd);
        return jdbcManager
            .from(CrmCcAcctGrpClassAth.class)
            .where(where)
            .getResultList();

    }

}