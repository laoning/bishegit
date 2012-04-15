/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dao.CrmCcCostDao;
import com.biz_integral.crm.cc.domain.entity.CrmCcCost;
import com.biz_integral.crm.sa.domain.dto.ExpApplySetDialogSearchCriteriaDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * CRM経費ロジックのテストケースです。<br>
 */
@RunWith(BizIntegralTest.class)
public class CrmCcCostLogicImplTest {

    /**
     * {@link CrmCcCostLogicImpl}
     */
    private CrmCcCostLogicImpl crmCcCostLogicImpl;

    /**
     * Mockitoを利用し、生成したモックオブジェクト
     */
    @MockitoComponent(componentName = "crmCcCostDao")
    private CrmCcCostDao crmCcCostDao;

    /**
     * {@link ExpApplySetDialogSearchCriteriaDto}
     */
    ExpApplySetDialogSearchCriteriaDto searchCriteriaDto =
        new ExpApplySetDialogSearchCriteriaDto();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。<br>
     */
    @PostBindFields
    public void postBindFields() {

        // 期待値のリストです。
        PagingResult<CrmCcCost> result = new PagingResult<CrmCcCost>();

        for (int i = 0; i < 10; i++) {
            CrmCcCost crmCcCost = new CrmCcCost();

            crmCcCost.setCostCd("0" + i);
            crmCcCost.setCostName("経費" + i);
            crmCcCost.setStartDate(DateUtil.parse("20100101", "yyyyMMdd"));
            crmCcCost.setEndDate(DateUtil.parse("20990101", "yyyyMMdd"));

            result.add(crmCcCost);
        }

        when(crmCcCostDao.findBySearchCriteria(searchCriteriaDto)).thenReturn(
            result);
    }

    /**
     * CRM経費一覧を取得するテストです。<br>
     */
    @Test
    public void testGetEntityList() {

        // 結果のリストです。
        PagingResult<CrmCcCost> result =
            this.crmCcCostLogicImpl.getEntityList(searchCriteriaDto);

        // メソッド呼び出しの確認
        verify(crmCcCostDao).findBySearchCriteria(searchCriteriaDto);

        for (int i = 0; i < 10; i++) {
            CrmCcCost crmCcCost = result.getResultList().get(i);

            assertEquals("0" + i, crmCcCost.getCostCd());
            assertEquals("経費" + i, crmCcCost.getCostName());
            assertEquals(DateUtil.parse("20100101", "yyyyMMdd"), crmCcCost
                .getStartDate());
            assertEquals(DateUtil.parse("20990101", "yyyyMMdd"), crmCcCost
                .getEndDate());
        }
    }
}
