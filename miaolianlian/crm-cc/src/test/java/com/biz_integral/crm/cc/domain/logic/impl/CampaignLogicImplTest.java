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

import com.biz_integral.crm.cc.domain.dto.CampaignCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CampaignResultDto;
import com.biz_integral.crm.sc.domain.dao.CrmScCampaignDao;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * キャンペーンのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class CampaignLogicImplTest {

    /**
     * キャンペーンに関するDAO
     */
    @MockitoComponent()
    protected CrmScCampaignDao crmScCampaignDao;

    /**
     * {@link campaignLogicImpl}
     */
    private CampaignLogicImpl campaignLogicImpl;

    /**
     * {@link CampaignCriteriaDto}
     */
    CampaignCriteriaDto criteriadto = new CampaignCriteriaDto();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        PagingResult<CampaignResultDto> result =
            new PagingResult<CampaignResultDto>();

        for (int i = 0; i < 10; i++) {
            CampaignResultDto resultDto = new CampaignResultDto();

            resultDto.campaignCd = "0001" + i;
            resultDto.campaignContent = "content" + i;
            resultDto.campaignId = "1" + i;
            resultDto.campaignName = "name" + i;
            resultDto.crmAccountCd = "001" + i;
            result.add(resultDto);
        }

        when(crmScCampaignDao.getEntityList(criteriadto)).thenReturn(result);
    }

    /**
     * キャンペーンの一覧の取得のテストです。
     */
    @Test
    public void testGetEntityList() {
        PagingResult<CampaignResultDto> resultList =
            campaignLogicImpl.getEntityList(criteriadto);
        verify(crmScCampaignDao).getEntityList(criteriadto);
        assertEquals(10, resultList.size());
        for (int i = 0; i < 10; i++) {
            CampaignResultDto result = resultList.get(i);
            assertEquals("0001" + i, result.campaignCd);
            assertEquals("content" + i, result.campaignContent);
            assertEquals("1" + i, result.campaignId);
            assertEquals("name" + i, result.campaignName);
            assertEquals("001" + i, result.crmAccountCd);
        }
    }
}
