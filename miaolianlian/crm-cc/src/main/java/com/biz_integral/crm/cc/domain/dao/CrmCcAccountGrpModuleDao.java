/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpModule;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpModuleNames;

/**
 * {@link CrmCcAccountGrpModule}のDaoクラスです。
 * 
 */
public class CrmCcAccountGrpModuleDao extends
        AbstractCrmCcAccountGrpModuleDao<CrmCcAccountGrpModule> {

    /**
     * CRMアカウントグループ属性一覧を取得
     * 
     * @param entity
     *            CRMアカウントグループ属性Entity
     * 
     * @return CRMアカウントグループ属性Entityリスト
     */
    public List<CrmCcAccountGrpModule> getEntityList(
            CrmCcAccountGrpModule entity) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        if (entity.getCompanyCd() != null) {
            str.append("company_cd = ? and ");
            values.add(entity.getCompanyCd());
        }
        if (entity.getCrmAccountClassCd() != null) {
            str.append("crm_account_class_cd = ? and ");
            values.add(entity.getCrmAccountClassCd());
        }
        if (entity.getDealClass() != null) {
            str.append("deal_class = ? and ");
            values.add(entity.getDealClass());
        }
        if (entity.getModuleId() != null) {
            str.append("module_id = ? and ");
            values.add(entity.getModuleId());
        }
        if (entity.getVersionNo() != null) {
            str.append("version_no = ? and ");
            values.add(entity.getVersionNo());
        }
        str.append("delete_flag = '0'");

        List<CrmCcAccountGrpModule> resultList =
            jdbcManager.from(CrmCcAccountGrpModule.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;
    }

    /**
     * CRMアカウントグループ利用モジュールEntityを取得
     * 
     * @param companyCd
     *            会社コード
     * @param dealClass
     *            取引種別
     * @param crmAccountClassCd
     *            CRMアカウント分類コード
     * @param moduleId
     *            モジュールID
     * @param deletedFlg
     *            削除フラグ
     * 
     * @return CRMアカウントグループ利用モジュールEntity
     */
    public CrmCcAccountGrpModule getEntity(String companyCd, String dealClass,
            String crmAccountClassCd, String moduleId, String deletedFlg) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        boolean flg = false;
        if (!StringUtils.isEmpty(crmAccountClassCd)) {
            str.append("crm_account_class_cd = ? and ");
            values.add(crmAccountClassCd);
        }
        if (!StringUtils.isEmpty(dealClass)) {
            str.append("deal_class = ? and ");
            values.add(dealClass);
        }
        if (!StringUtils.isEmpty(moduleId)) {
            str.append("module_id = ? and ");
            values.add(moduleId);
        }
        if (!StringUtils.isEmpty(deletedFlg)) {
            flg = true;
            str.append("delete_flag = ? ");
            values.add(deletedFlg);
        }
        if (!StringUtils.isEmpty(companyCd)) {
            if (flg) {
                str.append(" and ");
            }
            str.append("company_cd = ? ");
            values.add(companyCd);
        }
        CrmCcAccountGrpModule result =
            jdbcManager.from(CrmCcAccountGrpModule.class).where(
                str.toString(),
                values.toArray()).getSingleResult();
        return result;
    }

    /**
     * CRMアカウントグループ属性クラスのPKで一覧を検索します。
     * 
     * @param companyCd
     *            会社コード
     * @param dealClass
     *            取引種別
     * @param crmAccountClassCd
     *            アカウント分類コード
     * @return CRMアカウントグループ利用モジュール設定リスト
     */
    public List<CrmCcAccountGrpModule> findByCrmCcAccountGrpAttr(
            String companyCd, String dealClass, String crmAccountClassCd) {
        SimpleWhere where = new SimpleWhere();
        where.eq(CrmCcAccountGrpModuleNames.companyCd(), companyCd);
        where.eq(CrmCcAccountGrpModuleNames.dealClass(), dealClass);
        where.eq(
            CrmCcAccountGrpModuleNames.crmAccountClassCd(),
            crmAccountClassCd);
        return jdbcManager
            .from(CrmCcAccountGrpModule.class)
            .where(where)
            .getResultList();

    }
}