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

import com.biz_integral.crm.cc.domain.dto.SalesActivityCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityResultDto;
import com.biz_integral.crm.sa.domain.dao.CrmSaSalesActDao;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 営業活動のテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class SalesActivityLogicImplTest {

    /**
     * {@link CrmSaSalesActDao 営業活動に関するDAO}
     */
    @MockitoComponent()
    protected CrmSaSalesActDao crmSaSalesActDao;

    /**
     * {@link campaignLogicImpl}
     */
    private SalesActivityLogicImpl salesActivityLogicImpl;

    /**
     * {@link SalesActivityCriteriaDto}
     */
    SalesActivityCriteriaDto salesActivityCriteriaDto;

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        PagingResult<SalesActivityResultDto> result =
            new PagingResult<SalesActivityResultDto>();
        for (int i = 0; i < 10; i++) {
            SalesActivityResultDto resultDto = new SalesActivityResultDto();
            resultDto.crmContactCd = "0001" + i;
            resultDto.crmContactName = "contactname" + i;
            resultDto.crmAccountCd = "001" + i;
            resultDto.crmAccountName = "accountname" + i;
            result.add(resultDto);
        }
        salesActivityCriteriaDto = new SalesActivityCriteriaDto();
        when(
            crmSaSalesActDao
                .getAccountContactEntityList(salesActivityCriteriaDto))
            .thenReturn(result);
    }

    /**
     * 営業活動の一覧の取得のテストです。
     */
    @Test
    public void testGetEntityList() {
        PagingResult<SalesActivityResultDto> resultList =
            salesActivityLogicImpl.getEntityList(salesActivityCriteriaDto);
        verify(crmSaSalesActDao).getAccountContactEntityList(
            salesActivityCriteriaDto);
        assertEquals(10, resultList.size());
        for (int i = 0; i < 10; i++) {
            SalesActivityResultDto result = resultList.get(i);
            assertEquals("0001" + i, result.crmContactCd);
            assertEquals("contactname" + i, result.crmContactName);
            assertEquals("001" + i, result.crmAccountCd);
            assertEquals("accountname" + i, result.crmAccountName);
        }
    }
}
