/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dao;

import com.biz_integral.crm.cc.domain.entity.CrmCcCost;
import com.biz_integral.crm.sa.domain.dto.ExpApplySetDialogSearchCriteriaDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link CrmCcCost}のDaoクラスです。<br/>
 * 
 */
public class CrmCcCostDao extends AbstractCrmCcCostDao<CrmCcCost> {

    /**
     * CRM経費一覧取得用SQL
     */
    private static final String FIND_BY_SEARCH_CRITERIA_SQL =
        "CrmCcCostDao_findBySearchCriteria.sql";

    /**
     * CRM経費一覧の取得です。<br/>
     * 
     * @param searchCriteria
     *            CRM経費検索条件モデル
     * 
     * @return CrmCcCostエンティティ
     */
    public PagingResult<CrmCcCost> findBySearchCriteria(
            ExpApplySetDialogSearchCriteriaDto searchCriteria) {
        ExpApplySetDialogSearchCriteriaDto condition =
            convertLikeEspace(searchCriteria);
        condition.addDefaultOrderBySpec("costCd");
        PagingResult<CrmCcCost> result =
            super.findPagingBySqlFile(
                CrmCcCost.class,
                FIND_BY_SEARCH_CRITERIA_SQL,
                condition);
        for (CrmCcCost cost : result.getResultList()) {
            if (cost.getEndDate() != null) {
                cost.setEndDate(DateUtil
                    .getCalculator(cost.getEndDate())
                    .addDay(-1)
                    .asDate());
            }
        }
        return result;
    }

    /**
     * 元検索条件を前方一致と部分一致検索条件にします。<br/>
     * 
     * @param criteria
     *            元検索条件
     * @return 変換された検索条件
     */
    private ExpApplySetDialogSearchCriteriaDto convertLikeEspace(
            ExpApplySetDialogSearchCriteriaDto criteria) {
        ExpApplySetDialogSearchCriteriaDto cloned =
            criteria.cloneInstance(ExpApplySetDialogSearchCriteriaDto.class);
        cloned.costCd = likePrefix(criteria.costCd);
        cloned.costName = likeContain(criteria.costName);
        return cloned;
    }
}