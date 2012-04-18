/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dao.CrmCcCostDao;
import com.biz_integral.crm.cc.domain.entity.CrmCcCost;
import com.biz_integral.crm.sa.domain.dto.ExpApplySetDialogSearchCriteriaDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcCostDaoクラスのテストケースです。<br>
 */
@RunWith(BizIntegralTest.class)
public class CrmCcCostDaoTest {

    /**
     * テスト対象
     */
    private CrmCcCostDao crmCcCostDao;

    /**
     * CRM経費一覧を取得するテストです。<br>
     */
    @Test
    public void testFindBySearchCriteria() {
        ExpApplySetDialogSearchCriteriaDto dto =
            new ExpApplySetDialogSearchCriteriaDto();

        dto.companyCd = "0001";
        dto.localeId = "ja";
        dto.setLimit(10);
        dto.setOffset(0);

        PagingResult<CrmCcCost> result =
            this.crmCcCostDao.findBySearchCriteria(dto);

        assertEquals(9L, result.getAllRecordCount());
        assertEquals(9, result.size());
    }

    /**
     * CRM経費一覧を取得するテストです。<br>
     * 基準日
     */
    @Test
    public void testFindBySearchCriteria_ByBaseDate() {
        ExpApplySetDialogSearchCriteriaDto dto =
            new ExpApplySetDialogSearchCriteriaDto();

        dto.companyCd = "0001";
        dto.localeId = "ja";
        dto.baseDate = DateUtil.parse("20100701", "yyyyMMdd");
        dto.setLimit(10);
        dto.setOffset(0);

        PagingResult<CrmCcCost> result =
            this.crmCcCostDao.findBySearchCriteria(dto);

        assertEquals(1L, result.getAllRecordCount());
        assertEquals(1, result.size());
        assertEquals("cd03", result.get(0).getCostCd());
    }

    /**
     * CRM経費一覧を取得するテストです。<br>
     * 経費コード
     */
    @Test
    public void testFindBySearchCriteria_ByCostCd() {
        ExpApplySetDialogSearchCriteriaDto dto =
            new ExpApplySetDialogSearchCriteriaDto();

        dto.companyCd = "0001";
        dto.localeId = "ja";
        dto.costCd = "cd";
        dto.setLimit(10);
        dto.setOffset(0);

        PagingResult<CrmCcCost> result =
            this.crmCcCostDao.findBySearchCriteria(dto);

        assertEquals(4L, result.getAllRecordCount());
        assertEquals(4, result.size());
    }

    /**
     * CRM経費一覧を取得するテストです。<br>
     * 経費名
     */
    @Test
    public void testFindBySearchCriteria_ByCostName() {
        ExpApplySetDialogSearchCriteriaDto dto =
            new ExpApplySetDialogSearchCriteriaDto();

        dto.companyCd = "0001";
        dto.localeId = "ja";
        dto.costName = "name";
        dto.setLimit(10);
        dto.setOffset(0);

        PagingResult<CrmCcCost> result =
            this.crmCcCostDao.findBySearchCriteria(dto);

        assertEquals(9L, result.getAllRecordCount());
        assertEquals(9, result.size());
    }
}
