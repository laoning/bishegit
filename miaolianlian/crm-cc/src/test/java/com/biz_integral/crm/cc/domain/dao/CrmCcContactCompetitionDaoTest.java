/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * CrmCcContactCompetitionDaoTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcContactCompetitionDaoTest {
    /**
     * テスト対象
     */
    private CrmCcContactCompetitionDao crmCcContactCompetitionDao;

    /**
     * {@link BeanMapper}の実装
     */
    @MockitoComponent()
    protected BeanMapper beanMapper;

    /**
     * 競合登録／更新ダイアログのCRMコンタクト競合の処理のテスト<br>
     */
    @Test
    public void testGetEntity() {
        CrmCcContactCompetitionDto dto = new CrmCcContactCompetitionDto();
        dto.companyCd = "0001";
        dto.competitionId = "678";
        dto.crmContactCd = "1111";
        dto.deleteFlag = "0";
        dto.termCd = "123456";
        dto.startDate = DateUtil.parse("20100101", "yyyyMMdd");
        dto.endDate = DateUtil.parse("20100103", "yyyyMMdd");

        CrmCcContactCompetition result =
            crmCcContactCompetitionDao.getEntity(dto);
        assertEquals("678", result.getCompetitionId());
        assertEquals("1111", result.getCrmContactCd());
        assertEquals("名前c", result.getCompetitionName());
        assertEquals("ertyuui", result.getAcquirementProposalCase());
        assertEquals("2", result.getCompetitionLevel());
        assertEquals("dfghu", result.getNotes());
        assertEquals("2345678", result.getOverview());
        assertEquals(678, result.getVersionNo().longValue());
    }

    /**
     * CRMコンタクト競合を一覧検索の処理のテスト<br>
     */
    @Test
    public void testGetEntityList() {
        CrmCcContactCompetitionCriteriaDto criteriadto =
            new CrmCcContactCompetitionCriteriaDto();
        criteriadto.companyCd = "0001";
        criteriadto.crmContactCd = "0001";
        PagingResult<CrmCcContactCompetition> resultList =
            crmCcContactCompetitionDao.getEntityList(criteriadto);
        assertEquals(3, resultList.size());
    }
}
