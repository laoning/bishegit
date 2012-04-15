/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.AccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountContact;
import com.biz_integral.crm.sa.domain.dto.AccountContactDto;
import com.biz_integral.crm.sa.domain.dto.CustomerSearchInfoCriteriaDto;
import com.biz_integral.crm.sa.domain.dto.CustomerSearchResultCriteriaDto;
import com.biz_integral.crm.sa.domain.dto.SalesActCheckDto;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * CrmCcAccountContactDaoTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcAccountContactDaoTest {

    /**
     * {@link BeanMapper}の実装
     */
    @MockitoComponent
    protected BeanMapper beanMapper;

    /**
     * {@link CrmCcAccountContactDao}
     */
    private CrmCcAccountContactDao crmCcAccountContactDao;

    /**
     * アカウントコンタクト登録/更新ダイアログ一件取得の処理のテスト<br>
     */
    @Test
    public void testGetEntity() {
        CrmCcAccountContactDto dto = new CrmCcAccountContactDto();
        dto.companyCd = "0001";
        dto.crmAccountCd = "1111";
        dto.crmContactCd = "2222";
        dto.deleteFlag = "0";
        dto.termCd = "3333";
        dto.startDate = DateUtil.parse("20100101", "yyyyMMdd");
        dto.endDate = DateUtil.parse("20100103", "yyyyMMdd");

        CrmCcAccountContact result = crmCcAccountContactDao.getEntity(dto);

        assertEquals("0001", result.getCompanyCd());
        assertEquals("1111", result.getCrmAccountCd());
        assertEquals("2222", result.getCrmContactCd());
        assertEquals("3333", result.getTermCd());
        assertEquals(DateUtil.parse("20100101", "yyyyMMdd"), result
            .getStartDate());
        assertEquals(DateUtil.parse("20100103", "yyyyMMdd"), result
            .getEndDate());
        assertEquals("備考", result.getNotes());
        assertEquals("20100827", result.getCustomField1());
        assertEquals("20100827", result.getCustomField2());
        assertEquals("2010-08-27", result.getCustomField3());
        assertEquals("20100827", result.getCustomField4());
        assertEquals("20100827", result.getCustomField5());
        assertEquals("2010-08-27", result.getCustomField6());
        assertEquals("20100827", result.getCustomField7());
        assertEquals("20100827", result.getCustomField8());
        assertEquals("2010-08-27", result.getCustomField9());
        assertEquals("20100827", result.getCustomField10());
        assertEquals("20100827", result.getCustomField11());
        assertEquals("2010-08-27", result.getCustomField12());
        assertEquals("20100827", result.getCustomField13());
        assertEquals("20100827", result.getCustomField14());
        assertEquals("2010-08-27", result.getCustomField15());
        assertEquals("20100827", result.getCustomField16());
        assertEquals("20100827", result.getCustomField17());
        assertEquals("2010-08-27", result.getCustomField18());
        assertEquals("20100827", result.getCustomField19());
        assertEquals("20100827", result.getCustomField20());
        assertEquals("2010-08-27", result.getCustomField21());
        assertEquals("20100827", result.getCustomField22());
        assertEquals("20100827", result.getCustomField23());
        assertEquals("2010-08-27", result.getCustomField24());
        assertEquals("20100827", result.getCustomField25());
        assertEquals("20100827", result.getCustomField26());
        assertEquals("2010-08-27", result.getCustomField27());
        assertEquals("20100827", result.getCustomField28());
        assertEquals("20100827", result.getCustomField29());
        assertEquals("2010-08-27", result.getCustomField30());
        assertEquals("20100827", result.getCustomField31());
        assertEquals("20100827", result.getCustomField32());
        assertEquals("2010-08-27", result.getCustomField33());
        assertEquals("20100827", result.getCustomField34());
        assertEquals("20100827", result.getCustomField35());
        assertEquals("2010-08-27", result.getCustomField36());
        assertEquals("20100827", result.getCustomField37());
        assertEquals("20100827", result.getCustomField38());
        assertEquals("2010-08-27", result.getCustomField39());
        assertEquals("20100827", result.getCustomField40());
        assertEquals("20100827", result.getCustomField41());
        assertEquals("2010-08-27", result.getCustomField42());
        assertEquals("20100827", result.getCustomField43());
        assertEquals("20100827", result.getCustomField44());
        assertEquals("2010-08-27", result.getCustomField45());
        assertEquals("20100827", result.getCustomField46());
        assertEquals("20100827", result.getCustomField47());
        assertEquals("2010-08-27", result.getCustomField48());
        assertEquals("20100827", result.getCustomField49());
        assertEquals("20100827", result.getCustomField50());
        assertEquals("2010-08-27", result.getCustomField51());
        assertEquals("20100827", result.getCustomField52());
        assertEquals("20100827", result.getCustomField53());
        assertEquals("2010-08-27", result.getCustomField54());
        assertEquals("20100827", result.getCustomField55());
        assertEquals("20100827", result.getCustomField56());
        assertEquals("2010-08-27", result.getCustomField57());
        assertEquals("20100827", result.getCustomField58());
        assertEquals("20100827", result.getCustomField59());
        assertEquals("2010-08-27", result.getCustomField60());
        assertEquals(false, result.isDeleteFlag());
        assertEquals(1, result.getSortKey().intValue());
        assertEquals(9, result.getVersionNo().intValue());
        assertEquals("1", result.getCreateUserCd());
        assertEquals(DateUtil.parse("20100827", "yyyyMMdd"), result
            .getCreateDate());
        assertEquals("aoyagi", result.getRecordUserCd());
        assertEquals(DateUtil.parse("20100901", "yyyyMMdd"), result
            .getRecordDate());
    }

    /**
     * コンタクト一覧取得のテスト<br>
     */
    @Test
    public void testGetContactEntityList() {
        CrmCcAccountContactCriteriaDto criteriadto =
            new CrmCcAccountContactCriteriaDto();
        criteriadto.companyCd = "0001";
        criteriadto.crmAccountCd = "0001";
        criteriadto.localeId = "ja";

        PagingResult<CrmCcAccountContactResultDto> resultList =
            crmCcAccountContactDao.getContactEntityList(criteriadto);
        assertEquals(4, resultList.size());
    }

    /**
     * アカウント一覧取得のテスト<br>
     */
    @Test
    public void testGetAccountEntityList() {
        CrmCcAccountContactCriteriaDto criteriadto =
            new CrmCcAccountContactCriteriaDto();
        criteriadto.companyCd = "0001";
        criteriadto.crmContactCd = "0001";
        criteriadto.localeId = "ja";
        PagingResult<CrmCcAccountContactResultDto> resultList =
            crmCcAccountContactDao.getAccountEntityList(criteriadto);
        assertEquals(2, resultList.size());
    }

    /**
     * アカウントコンタクトの登録のテスト<br>
     */
    @Test
    public void testGetCreateEntityList() {
        AccountContactCriteriaDto accountContactCriteriaDto =
            new AccountContactCriteriaDto();
        accountContactCriteriaDto.companyCd = "0001";
        accountContactCriteriaDto.crmAccountCd = "0001";
        accountContactCriteriaDto.crmContactCd = "0001";
        List<CrmCcAccountContact> resultList =
            crmCcAccountContactDao
                .getCreateEntityList(accountContactCriteriaDto);
        assertEquals(2, resultList.size());
    }

    /**
     * アカウントコンタクト有効チェック（営業活動日）のテスト<br>
     */
    @Test
    public void testAccountContactEnabledCheck() {
        SalesActCheckDto checkDto = new SalesActCheckDto();
        checkDto.companyCd = "0001";
        checkDto.crmAccountCd = "ACCOUNT001";
        checkDto.crmContactCd = "CONTACT001";
        checkDto.salesActivityStartDate =
            DateUtil.parse("20101020", "yyyyMMdd");
        boolean result =
            crmCcAccountContactDao.accountContactEnabledCheck(checkDto);
        assertEquals(true, result);
    }

    /**
     * 担当顧客アカウントコンタクト一覧検索のテスト<br>
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
            crmCcAccountContactDao
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
        customeSearchRequestDto.crmContactCd = "CONTACT001";
        customeSearchRequestDto.baseDate =
            DateUtil.parse("20101020", "yyyyMMdd");
        customeSearchRequestDto.localeId = "ja";
        List<CustomerSearchResultCriteriaDto> resultList =
            crmCcAccountContactDao
                .findByCustomerSearchRequest(customeSearchRequestDto);
        assertEquals(1, resultList.size());
    }
}
