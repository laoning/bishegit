/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.jdbc.OrderByItem;
import org.seasar.extension.jdbc.where.SimpleWhere;

import com.biz_integral.crm.cc.domain.entity.CrmCcModule;
import com.biz_integral.crm.cc.domain.entity.CrmCcModuleNames;
import com.biz_integral.service.persistence.LogicalDelete;

/**
 * {@link CrmCcModule}のDaoクラスです。
 * 
 */
public class CrmCcModuleDao extends AbstractCrmCcModuleDao<CrmCcModule> {
    /**
     * 
     * 利用モジュール設定一覧を取得
     * <p>
     * 引数の条件に従って利用モジュール設定一覧取得.を検索し、検索結果を返却します。
     * </p>
     * 
     * @param companyCd
     *            <ul>
     *            <li>引数の会社コード
     *            </ul>
     * @return リスト＜利用モジュール設定Entity＞
     */
    public List<CrmCcModule> getEntityListByCompanyCd(String companyCd) {

        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        str.append("company_cd = ? and ");
        values.add(companyCd);
        str.append("delete_flag = 0");

        List<CrmCcModule> resultList =
            jdbcManager.from(CrmCcModule.class).where(
                str.toString(),
                values.toArray()).getResultList();
        return resultList;
    }

    /**
     * 利用モジュール設定を取得します。<br>
     * 会社コードと削除フラグの指定を元に検索を行います。<br>
     * 順序はソートキーとモジュールIDの昇順に設定されます。
     * 
     * @param companyCd
     *            会社コード
     * @param logicalDelete
     *            論理削除の列挙体
     * @return 利用モジュール設定エンティティリスト
     */
    public List<CrmCcModule> getEntityListByCompanyCd(String companyCd,
            LogicalDelete logicalDelete) {
        SimpleWhere where = new SimpleWhere();
        where.eq(CrmCcModuleNames.companyCd(), companyCd);
        if (LogicalDelete.NO_RESTRICTION != logicalDelete) {
            where.eq(
                CrmCcModuleNames.deleteFlag(),
                LogicalDelete.DELETED == logicalDelete);
        }

        OrderByItem sortKeyOrder = new OrderByItem(CrmCcModuleNames.sortKey());
        OrderByItem moduleOrder = new OrderByItem(CrmCcModuleNames.moduleId());

        return jdbcManager.from(CrmCcModule.class).where(where).orderBy(
            sortKeyOrder).orderBy(moduleOrder).getResultList();
    }
}