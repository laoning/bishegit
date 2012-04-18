/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.DepartmentDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.sa.domain.dto.SalesActInformHasCheckDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcDepartmentCmnDaoTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcDepartmentCmnDaoTest {

    /**
     * テスト対象
     */
    private CrmCcDepartmentCmnDao crmCcDepartmentCmnDao;

    /**
     * 有組織コード有効期間チェックのテストです。<br>
     */
    @Test
    public void testDeptCdEnableCheck() {

        SalesActInformHasCheckDto checkDto = new SalesActInformHasCheckDto();
        checkDto.companyCd = "0001";
        checkDto.departmentSetCd = "0001";
        checkDto.departmentCd = "0001";
        checkDto.localeId = "ja";
        checkDto.salesActivityStartDate = new Date();
        assertTrue(crmCcDepartmentCmnDao.deptCdEnableCheck(checkDto));

        checkDto.localeId = "cn";
        assertFalse(crmCcDepartmentCmnDao.deptCdEnableCheck(checkDto));
    }

    /**
     * 組織選択ダイアログ一覧の処理のテストです。<br>
     */
    @Test
    public void testEntityListTree() {
        DepartmentDialogCriteriaDto dto = new DepartmentDialogCriteriaDto();
        dto.searchBaseDate = DateUtil.parse("201008016", "yyyyMMdd");
        dto.companyCd = "0001";
        dto.departmentCd = "0001";
        dto.departmentSetCd = "0001";
        dto.localeId = "ja";
        dto.setLimit(10);
        dto.setOffset(0);

        PagingResult<CrmCcDepartmentCmn> result =
            crmCcDepartmentCmnDao.findByDepartmentSelectDialogCriteria(dto);
        assertEquals(4, result.size());
    }

    /**
     * 組織設定ダイアログ一覧の処理のテストです。<br>
     */
    @Test
    public void testEntityList() {
        DepartmentDialogCriteriaDto dto = new DepartmentDialogCriteriaDto();
        dto.companyCd = "0001";
        dto.departmentCd = "0001";
        dto.departmentSetCd = "0001";
        dto.localeId = "ja";
        dto.setLimit(10);
        dto.setOffset(0);

        PagingResult<CrmCcDepartmentCmn> result =
            crmCcDepartmentCmnDao.findByDepartmentSetDialogCriteria(dto);
        assertEquals(4, result.size());
    }
}
