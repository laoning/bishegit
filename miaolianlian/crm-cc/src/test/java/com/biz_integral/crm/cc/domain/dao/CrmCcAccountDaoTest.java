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

import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.SaAccountSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.pm.domain.dto.ProposalSearchDto;
import com.biz_integral.crm.sa.domain.dto.AccountContactDto;
import com.biz_integral.crm.sa.domain.dto.CustomerSearchInfoCriteriaDto;
import com.biz_integral.crm.sa.domain.dto.CustomerSearchResultCriteriaDto;
import com.biz_integral.crm.sa.domain.dto.SalesActCheckDto;
import com.biz_integral.crm.sp.domain.dto.CustomerSelectSalesPlanGetCustomerResultListDto;
import com.biz_integral.crm.sp.domain.dto.CustomerSelectSalesPlanSearchCriteraDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcAccountDaoTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcAccountDaoTest {

    /**
     * テスト対象
     */
    private CrmCcAccountDao crmCcAccountDao;

    /**
     * 住所選択ダイアログ一覧の処理のテスト<br>
     */
    @Test
    public void testFindByAccountSearchCriteria() {
        AccountSearchCriteriaDto dto = new AccountSearchCriteriaDto();
        dto.localeID = "ja";
        dto.companyCd = "0001";
        dto.crmDomainCd = "domainCd";
        dto.departmentSetCd = "0001";
        dto.userCode = "aoyagi";
        dto.easySearchMode = "0";
        dto.deleteFlag = "0";
        dto.hasPrivilegeFlag = "0";
        dto.hasAceessCustomerFlag = "0";
        dto.setLimit(10);
        dto.setOffset(0);

        PagingResult<AccountSearchResultDto> result =
            crmCcAccountDao.findByAccountSearchCriteria(dto);
        assertEquals(10, result.size());
    }

    /**
     * 顧客一覧検索のテスト<br>
     */
    @Test
    public void testGetCustomer() {
        CustomerSelectSalesPlanSearchCriteraDto searchCriteraDto =
            new CustomerSelectSalesPlanSearchCriteraDto();

        searchCriteraDto.companyCd = "0001";
        searchCriteraDto.localeId = "ja";
        searchCriteraDto.searchBaseDate =
            DateUtil.parse("20100906", "yyyyMMdd");
        searchCriteraDto.salesPlanDefId = "00001";
        searchCriteraDto.registeredFlag = "1";
        searchCriteraDto.setLimit(10);
        searchCriteraDto.setOffset(0);

        PagingResult<CustomerSelectSalesPlanGetCustomerResultListDto> result =
            crmCcAccountDao
                .findByCustomerSelectSalesPlanGetCustomerResultListDto(searchCriteraDto);
        assertEquals(10, result.size());
        assertEquals(11, result.getAllRecordCount());
    }

    /**
     * CRMアカウントの一件取得のテスト<br>
     */
    @Test
    public void testGetAccountEntity() {
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();
        crmCcAccountDto.companyCd = "0001";
        crmCcAccountDto.crmAccountCd = "0001";
        crmCcAccountDto.localeId = "ja";
        crmCcAccountDto.startDate = DateUtil.nowDate();
        crmCcAccountDto.endDate = DateUtil.nowDate();
        CrmCcAccount result = crmCcAccountDao.getAccountEntity(crmCcAccountDto);
        assertEquals("いち", result.getCrmAccountName());

    }

    /**
     * 登録前CRMアカウントコードの重複をチェックのテスト<br>
     */
    @Test
    public void testGetEntityForCheck() {
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();
        crmCcAccountDto.companyCd = "0001";
        crmCcAccountDto.crmAccountCd = "0001";
        crmCcAccountDto.localeId = "ja";
        crmCcAccountDto.startDate = DateUtil.nowDate();
        crmCcAccountDto.endDate = DateUtil.nowDate();
        List<CrmCcAccount> resultList =
            crmCcAccountDao.getEntityForCheck(crmCcAccountDto);
        assertEquals(3, resultList.size());

    }

    /**
     * CRMアカウントの担当判定の場合、CRMアカウントDaoを一覧取得のテスト<br>
     */
    @Test
    public void testFindByCrmAccountCheckChargeDto() {
        CrmAccountCheckChargeDto crmAccountCheckChargeDto =
            new CrmAccountCheckChargeDto();
        crmAccountCheckChargeDto.companyCd = "0001";
        crmAccountCheckChargeDto.crmAccountCdList.add("0001");
        crmAccountCheckChargeDto.localeId = "ja";
        crmAccountCheckChargeDto.dataAccessCustomerFlag = "0";

        List<CrmCcAccount> resultList =
            crmCcAccountDao
                .findByCrmAccountCheckChargeDto(crmAccountCheckChargeDto);
        assertEquals(8, resultList.size());
    }

    /**
     * 一件取得のテスト<br>
     */
    @Test
    public void testGetEntity() {

        CrmCcAccountDto dto = new CrmCcAccountDto();
        dto.crmAccountCd = "030";
        dto.crmDomainCd = "1";
        SaAccountSearchResultDto result = crmCcAccountDao.getEntity(dto);
        assertEquals("a1", result.crmAccountName);
    }

    /**
     * CRMアカウント有効チェックのテスト<br>
     */
    @Test
    public void testCrmAccountEnableCheck() {
        SalesActCheckDto checkDto = new SalesActCheckDto();
        checkDto.companyCd = "hx8054001";
        checkDto.crmAccountCd = "5018";
        checkDto.salesActivityStartDate = new Date();
        assertTrue(crmCcAccountDao.crmAccountEnableCheck(checkDto));

        checkDto.crmAccountCd = "9988";
        assertFalse(crmCcAccountDao.crmAccountEnableCheck(checkDto));
    }

    /**
     * 担当アカウントコンタクト一覧検索のテスト<br>
     */
    @Test
    public void testFindByCustomerSearchRequestDto() {
        CustomerSearchInfoCriteriaDto customeSearchRequestDto =
            new CustomerSearchInfoCriteriaDto();
        customeSearchRequestDto.companyCd = "0001";
        customeSearchRequestDto.localeId = "ja";
        customeSearchRequestDto.baseDate =
            DateUtil.parse("20101020", "yyyyMMdd");
        customeSearchRequestDto.userCd = "aoyagi";
        customeSearchRequestDto.crmDomainCd = "CrmDomain001";
        List<AccountContactDto> resultList =
            crmCcAccountDao
                .findByCustomerSearchRequestDto(customeSearchRequestDto);
        assertEquals(5, resultList.size());
    }

    /**
     * 顧客アカウントコンタクト一覧検索のテスト<br>
     */
    @Test
    public void testFindByCustomerSearchRequest() {
        CustomerSearchInfoCriteriaDto customeSearchRequestDto =
            new CustomerSearchInfoCriteriaDto();
        customeSearchRequestDto.companyCd = "0001";
        customeSearchRequestDto.crmAccountCd = "ACCOUNT001";
        customeSearchRequestDto.localeId = "ja";
        List<CustomerSearchResultCriteriaDto> resultList =
            crmCcAccountDao
                .findByCustomerSearchRequest(customeSearchRequestDto);
        assertEquals(1, resultList.size());
    }

    /**
     * データ妥当性チェックのアカウント有効チェックのテスト<br>
     */
    @Test
    public void testValidationAccount() {
        ProposalSearchDto dto = new ProposalSearchDto();
        dto.companyCd = "0001";
        dto.localeId = "ja";
        dto.crmAccountCd = "ACCOUNT001";
        dto.baseDate = DateUtil.parse("20101020", "yyyyMMdd");
        long result = crmCcAccountDao.validationAccount(dto);
        assertEquals(1, result);
    }

    /**
     * CRMアカウントを一覧検索のテスト<br>
     */
    @Test
    public void testGetAccountList() {
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();
        crmCcAccountDto.companyCd = "0001";
        crmCcAccountDto.crmAccountCd = "ACCOUNT001";
        crmCcAccountDto.localeId = "ja";
        List<CrmCcAccount> resultList =
            crmCcAccountDao.getAccountList(crmCcAccountDto);
        assertEquals(1, resultList.size());
    }
}
