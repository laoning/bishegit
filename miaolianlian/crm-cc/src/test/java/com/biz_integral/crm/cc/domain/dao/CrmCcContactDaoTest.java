/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.SaContactSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.sa.domain.dto.SalesActCheckDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcContactDaoTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcContactDaoTest {
    /**
     * テスト対象
     */
    private CrmCcContactDao crmCcContactDao;

    /**
     * コンタクト選択ダイアログ一覧の処理のテスト<br>
     */
    @Test
    public void testFindByContactSearchCriteria() {
        ContactSearchCriteriaDto dto = new ContactSearchCriteriaDto();
        dto.companyCd = "0001";
        dto.userCode = "aoyagi";
        dto.localeID = "ja";
        dto.setLimit(10);
        dto.setOffset(0);

        PagingResult<ContactSearchResultDto> result =
            crmCcContactDao.findByContactSearchCriteria(dto);
        assertEquals(10, result.size());
    }

    /**
     * CRMコンタクトの一件取得（担当判定あり）のテスト<br>
     */
    @Test
    public void testGetEntityByDto() {
        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();
        crmCcContactDto.companyCd = "0001";
        crmCcContactDto.crmContactCd = "0603";
        crmCcContactDto.localeId = "ja";
        crmCcContactDto.startDate = DateUtil.nowDate();
        crmCcContactDto.endDate = DateUtil.nowDate();
        CrmCcContact result = crmCcContactDao.getEntityByDto(crmCcContactDto);
        assertEquals("name", result.getCrmContactName());
    }

    /**
     * 登録前CRMコンタクトの重複をチェックのテスト<br>
     */
    @Test
    public void testGetEntityForCheck() {
        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();
        crmCcContactDto.companyCd = "0001";
        crmCcContactDto.crmContactCd = "0603";
        crmCcContactDto.localeId = "ja";
        crmCcContactDto.startDate = DateUtil.nowDate();
        crmCcContactDto.endDate = DateUtil.nowDate();
        List<CrmCcContact> resultList =
            crmCcContactDao.getEntityForCheck(crmCcContactDto);
        assertEquals(3, resultList.size());
    }

    /**
     * CRMコンタクトの担当判定の場合、CRMコンタクトDaoを一覧取得のテスト<br>
     */
    @Test
    public void testFindCrmContactCheckChargeDto() {
        CrmContactCheckChargeDto crmContactCheckChargeDto =
            new CrmContactCheckChargeDto();
        crmContactCheckChargeDto.companyCd = "0001";
        crmContactCheckChargeDto.crmContactCdList.add("0001");
        crmContactCheckChargeDto.localeId = "ja";
        crmContactCheckChargeDto.dataAccessCustomerFlag = "0";
        crmContactCheckChargeDto.userCd = "0603";
        List<CrmCcContact> resultList =
            crmCcContactDao
                .findByCrmContactCheckChargeDto(crmContactCheckChargeDto);
        assertEquals(3, resultList.size());
    }

    /**
     * 一件取得のテスト<br>
     */
    @Test
    public void testGetEntity() {

        CrmCcContactDto dto = new CrmCcContactDto();
        dto.crmContactCd = "2014";
        SaContactSearchResultDto result = crmCcContactDao.getEntity(dto);
        assertEquals("コンタクト名5", result.crmContactName);
    }

    /**
     * CRMコンタクト有効チェックのテスト<br>
     */
    @Test
    public void testCrmCountEnabledCheck() {
        SalesActCheckDto checkDto = new SalesActCheckDto();
        checkDto.companyCd = "0254";
        checkDto.crmContactCd = "wxhx0254";
        checkDto.salesActivityStartDate = new Date();
        assertTrue(crmCcContactDao.crmCountEnabledCheck(checkDto));

        checkDto.crmContactCd = "9988";
        assertFalse(crmCcContactDao.crmCountEnabledCheck(checkDto));
    }
}
