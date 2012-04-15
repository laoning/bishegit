/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import java.util.ArrayList;
import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CustomerSelectCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcCustomerCmn}のDaoクラスです。
 * 
 */
public class CrmCcCustomerCmnDao extends
        AbstractCrmCcCustomerCmnDao<CrmCcCustomerCmn> {

    /**
     * 取引先選択ダイアログ対象一覧取得用SQL
     */
    private static final String CRMCCCUSTOMERCMNDAO_FINDBYCUSTOMERSELECTDIALOGCRITERIA_SQL =
        "CrmCcCustomerCmnDao_findByCustomerSelectDialogCriteria.sql";

    /**
     * 取引先を一件取得します。
     * 
     * @param criteriadto
     *            取引先Dto
     * @return 取引先エンティティ
     */
    public CrmCcCustomerCmn getEntity(CustomerSelectCriteriaDto criteriadto) {
        StringBuilder str = new StringBuilder();
        List<Object> values = new ArrayList<Object>();
        boolean found = false;
        if (criteriadto.companyCd != null) {
            str.append("company_cd = ?");
            values.add(criteriadto.companyCd);
            found = true;
        }
        if (criteriadto.localeId != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("locale_id = ?");
            values.add(criteriadto.localeId);
            found = true;
        }
        if (criteriadto.customerCd != null) {
            if (found) {
                str.append(" and ");
            }
            str.append("customer_cd = ?");
            values.add(criteriadto.customerCd);
            found = true;
        }
        if (found) {
            str.append(" and ");
        }
        str.append("start_date <= ?");
        values.add(DateUtil.nowDate());
        str.append(" and ");
        str.append("end_date > ?");
        values.add(DateUtil.nowDate());

        return jdbcManager.from(CrmCcCustomerCmn.class).where(
            str.toString(),
            values.toArray()).getSingleResult();
    }

    /**
     * 取引先選択ダイアログ対象一覧の取得の処理です。
     * 
     * @param dto
     *            検索条件
     * 
     * @return 取引先選択ダイアログ一覧
     */
    public PagingResult<CrmCcCustomerCmn> findByCustomerSelectDialogCriteria(
            CustomerSelectDialogCriteriaDto dto) {

        CustomerSelectDialogCriteriaDto criteria = convertLikeEspace(dto);
        criteria.addDefaultOrderBySpec("customerCd");
        PagingResult<CrmCcCustomerCmn> result =
            super.findPagingBySqlFile(
                CrmCcCustomerCmn.class,
                CRMCCCUSTOMERCMNDAO_FINDBYCUSTOMERSELECTDIALOGCRITERIA_SQL,
                criteria);
        for (CrmCcCustomerCmn cmn : result.getResultList()) {
            if (cmn.getEndDate() != null) {
                cmn.setEndDate(DateUtil.getCalculator(cmn.getEndDate()).addDay(
                    -1).asDate());
            }
        }
        return result;
    }

    /**
     * 前方一致検索条件をSQLエスケープします。
     * 
     * @param dto
     *            検索条件
     * @return 前方一致検索条件をSQLエスケープした検索条件
     */
    private CustomerSelectDialogCriteriaDto convertLikeEspace(
            CustomerSelectDialogCriteriaDto dto) {

        CustomerSelectDialogCriteriaDto cloned =
            dto.cloneInstance(CustomerSelectDialogCriteriaDto.class);

        cloned.customerCd = likePrefix(dto.customerCd);
        cloned.customerName = likeContain(dto.customerName);
        cloned.customerShortName = likeContain(dto.customerShortName);
        cloned.customerSearchName = likeContain(dto.customerSearchName);
        return cloned;
    }

}