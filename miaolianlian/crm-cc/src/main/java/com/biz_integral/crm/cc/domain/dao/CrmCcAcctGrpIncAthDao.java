/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.extension.jdbc.where.ComplexWhere;

import com.biz_integral.crm.cc.domain.dto.AcctGrpHierarchyDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpIncAth;

/**
 * {@link CrmCcAcctGrpIncAth}のDaoクラスです。
 * 
 */
public class CrmCcAcctGrpIncAthDao extends
        AbstractCrmCcAcctGrpIncAthDao<CrmCcAcctGrpIncAth> {

    /**
     * 
     * アカウントグループ階層一覧取得.
     * <p>
     * 引数の条件に従ってアカウントグループ階層一覧取得.を検索し、検索結果を返却します。
     * </p>
     * 
     * @param dto
     *            アカウントグループ階層検索初期化モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>引数dtoの会社コードが{@code null}でない、かつ空文字でない
     *            <li>引数dtoのロケールIDが{@code null}でない、かつ空文字でない
     *            <li>引数dtoの検索基準日が{@code null}でない、かつ空文字でない
     *            </ul>
     * @return リスト＜アカウントグループ階層DTO＞
     */
    public List<AcctGrpHierarchyDto> findAllByCriteria(AcctGrpHierarchyDto dto) {
        List<AcctGrpHierarchyDto> result =
            this.findBySqlFile(
                AcctGrpHierarchyDto.class,
                "CrmCcAcctGrpIncAthDao_findAllByCriteria.sql",
                dto);
        return result;
    }

    /**
     * 
     * 移動されるアカウントグループ一覧取得.
     * <p>
     * 引数の条件に従ってアカウントグループ階層一覧取得.を検索し、検索結果を返却します。
     * </p>
     * 
     * @param String
     *            <ul>
     *            <li>引数の会社コード
     *            <li>引数の親CRMアカウントグループコード
     *            </ul>
     * @return リスト＜アカウントグループ階層＞
     */
    public List<CrmCcAcctGrpIncAth> findMoveGroup(String companyCd,
            String parentCrmAccountGroupCd) {
        ComplexWhere where = new ComplexWhere();
        where.eq("companyCd", companyCd);
        where.eq("parentCrmAccountGroupCd", parentCrmAccountGroupCd);
        List<CrmCcAcctGrpIncAth> result =
            super.createSelect().where(where).getResultList();
        return result;
    }

    /**
     * CRMアカウントグループ内包Entityを取得
     * 
     * @param String
     *            companyCd
     * @param String
     *            parent_crm_account_group_cd
     * @param String
     *            crm_account_group_cd
     * @param String
     *            path
     * @param String
     *            deletedFlg
     * 
     * @return CRMアカウントグループ内包Entity
     */
    public CrmCcAcctGrpIncAth getEntity(String companyCd,
            String parent_crm_account_group_cd, String crm_account_group_cd,
            String path, String deletedFlg) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();

        boolean flg = false;
        if (!StringUtils.isEmpty(parent_crm_account_group_cd)) {
            str.append("parent_crm_account_group_cd = ? and ");
            values.add(parent_crm_account_group_cd);
        }
        if (!StringUtils.isEmpty(crm_account_group_cd)) {
            str.append("crm_account_group_cd = ? and ");
            values.add(crm_account_group_cd);
        }
        if (!StringUtils.isEmpty(path)) {
            str.append("path = ? and ");
            values.add(path);
        }
        if (!StringUtils.isEmpty(deletedFlg)) {
            flg = true;
            str.append("delete_flag = ? ");
            values.add(deletedFlg);
        }
        if (flg) {
            str.append(" and ");
        }
        str.append("company_cd = ? ");
        values.add(companyCd);

        CrmCcAcctGrpIncAth result =
            jdbcManager.from(CrmCcAcctGrpIncAth.class).where(
                str.toString(),
                values.toArray()).getSingleResult();

        return result;
    }

}
