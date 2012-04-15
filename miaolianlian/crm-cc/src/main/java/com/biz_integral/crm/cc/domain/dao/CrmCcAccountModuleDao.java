/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountModule;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountModuleNames;
import com.biz_integral.service.persistence.LogicalDelete;

/**
 * {@link CrmCcAccountModule}のDaoクラスです。
 * 
 */
public class CrmCcAccountModuleDao extends
        AbstractCrmCcAccountModuleDao<CrmCcAccountModule> {

    /**
     * CRMアカウント利用モジュール設定一覧を取得
     * 
     * @param entity
     *            CRMアカウントグループ属性Entity
     * @param logicalDelete
     *            論理削除の列挙型
     * 
     * @return CRMアカウントグループ属性Entityリスト
     */
    public List<CrmCcAccountModule> getEntityList(CrmCcAccountModule entity,
            LogicalDelete logicalDelete) {

        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        if (entity.getCompanyCd() != null) {
            str.append("company_cd = ?");
            values.add(entity.getCompanyCd());
        }
        if (entity.getDealClass() != null) {
            if (str.length() > 0)
                str = str.append(" and ");
            str.append("deal_class = ?");
            values.add(entity.getDealClass());
        }
        if (entity.getCrmAccountClassCd() != null) {
            if (str.length() > 0)
                str = str.append(" and ");
            str.append("crm_account_class_cd = ?");
            values.add(entity.getCrmAccountClassCd());
        }
        if (entity.getCrmAccountStatus() != null) {
            if (str.length() > 0)
                str = str.append(" and ");
            str.append("crm_account_status = ?");
            values.add(entity.getCrmAccountStatus());
        }
        if (!LogicalDelete.NO_RESTRICTION.equals(logicalDelete)) {
            if (str.length() > 0)
                str = str.append(" and ");
            str.append("delete_flag = ?");
            values.add(logicalDelete.getValue());
        }

        List<CrmCcAccountModule> resultList =
            jdbcManager.from(CrmCcAccountModule.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;
    }

    /**
     * CRMアカウント属性クラスのPKで一覧を検索します。
     * 
     * @param companyCd
     *            会社コード
     * @param dealClass
     *            取引種別
     * @param crmAccountClassCd
     *            アカウント分類コード
     * @param crmAccountStatus
     *            アカウント状況
     * @return CRMアカウント利用モジュール設定リスト
     */
    public List<CrmCcAccountModule> findByCrmCcAccountAttr(String companyCd,
            String dealClass, String crmAccountClassCd, String crmAccountStatus) {
        SimpleWhere where = new SimpleWhere();
        where.eq(CrmCcAccountModuleNames.companyCd(), companyCd);
        where.eq(CrmCcAccountModuleNames.dealClass(), dealClass);
        where
            .eq(CrmCcAccountModuleNames.crmAccountClassCd(), crmAccountClassCd);
        where.eq(CrmCcAccountModuleNames.crmAccountStatus(), crmAccountStatus);
        return jdbcManager
            .from(CrmCcAccountModule.class)
            .where(where)
            .getResultList();

    }
}