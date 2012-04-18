/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.CustomerSelectCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcCustomerCmnDaoTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcCustomerCmnDaoTest {
    /**
     * テスト対象
     */
    private CrmCcCustomerCmnDao crmCcCustomerCmnDao;

    /**
     * 取引先選択ダイアログ一覧の処理のテスト<br>
     */
    @Test
    public void testEntityListTree() {
        CustomerSelectDialogCriteriaDto dto =
            new CustomerSelectDialogCriteriaDto();

        dto.searchBaseDate = DateUtil.parse("201008016", "yyyyMMdd");
        dto.companyCd = "0001";
        dto.customerCd = "123123";
        dto.customerName = "SampleCustomerName1";
        dto.localeId = "ja";
        dto.setLimit(10);
        dto.setOffset(0);

        PagingResult<CrmCcCustomerCmn> result =
            crmCcCustomerCmnDao.findByCustomerSelectDialogCriteria(dto);
        assertEquals(4, result.size());
    }

    /**
     * 取引先を一件取得のテスト<br>
     */
    @Test
    public void testGetEntity() {
        CustomerSelectCriteriaDto criteriadto = new CustomerSelectCriteriaDto();
        criteriadto.companyCd = "0001";
        criteriadto.localeId = "ja";
        criteriadto.customerCd = "123123";
        CrmCcCustomerCmn result = crmCcCustomerCmnDao.getEntity(criteriadto);
        assertEquals("SampleCustomerName1", result.getCustomerName());
    }
}
