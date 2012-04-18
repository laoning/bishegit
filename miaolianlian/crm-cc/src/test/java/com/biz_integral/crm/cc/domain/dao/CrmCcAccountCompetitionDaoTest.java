/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * CrmCcAccountCompetitionDaoTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcAccountCompetitionDaoTest {
    /**
     * テスト対象
     */
    private CrmCcAccountCompetitionDao crmCcAccountCompetitionDao;

    /**
     * {@link BeanMapper}の実装
     */
    @MockitoComponent()
    protected BeanMapper beanMapper;

    /**
     * 競合登録／更新ダイアログのCRMアカウント競合一件取得の処理のテスト<br>
     */
    @Test
    public void testGetEntity() {
        CrmCcAccountCompetitionDto dto = new CrmCcAccountCompetitionDto();
        dto.companyCd = "0001";
        dto.competitionId = "678";
        dto.crmAccountCd = "2222";
        dto.deleteFlag = "0";
        dto.termCd = "123456";
        dto.startDate = DateUtil.parse("20100101", "yyyyMMdd");
        dto.endDate = DateUtil.parse("20100103", "yyyyMMdd");

        CrmCcAccountCompetition result =
            crmCcAccountCompetitionDao.getEntity(dto);
        assertEquals("678", result.getCompetitionId());
        assertEquals("2222", result.getCrmAccountCd());
        assertEquals("p", result.getCompetitionName());
        assertEquals("p", result.getAcquirementProposalCase());
        assertEquals("1", result.getCompetitionLevel());
        assertEquals("p", result.getNotes());
        assertEquals("p", result.getOverview());
        assertEquals(1235356, result.getVersionNo().longValue());
    }

    /**
     * CRMアカウント競合を一覧検索の処理のテスト<br>
     */
    @Test
    public void testGetEntityList() {
        CrmCcAccountCompetitionDto dto = new CrmCcAccountCompetitionDto();
        dto.companyCd = "0001";
        dto.crmAccountCd = "0001";
        PagingResult<CrmCcAccountCompetition> resultList =
            crmCcAccountCompetitionDao.getEntityList(dto);
        assertEquals(3, resultList.size());
    }
}
