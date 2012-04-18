/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeUser;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcAccountChargeUserDaoTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcAccountChargeUserDaoTest {

    /**
     * テスト対象
     */
    private CrmCcAccountChargeUserDao crmCcAccountChargeUserDao;

    /**
     * CRMアカウント担当者を一覧検索のテスト<br>
     */
    @Test
    public void testGetEntityList() {
        CrmCcAccountChargeUserCriteriaDto criteriaDto =
            new CrmCcAccountChargeUserCriteriaDto();
        criteriaDto.companyCd = "0001";
        criteriaDto.crmAccountCd = "0001";
        criteriaDto.crmDomainCd = "1";
        criteriaDto.localeId = "ja";
        List<CrmCcAccountChargeUserResultDto> resultList =
            crmCcAccountChargeUserDao.getEntityList(criteriaDto);
        assertEquals(3, resultList.size());
    }

    /**
     * 登録前CRMアカウント担当者を一覧検索のテスト<br>
     */
    @Test
    public void testGetCreateEntityList() {

        CrmCcAccountChargeUserCriteriaDto criteriaDto =
            new CrmCcAccountChargeUserCriteriaDto();
        criteriaDto.companyCd = "0001";
        criteriaDto.crmAccountCd = "0001";
        criteriaDto.crmDomainCd = "1";
        criteriaDto.localeId = "ja";
        criteriaDto.userCd = "hx0603";
        List<CrmCcAccountChargeUser> resultList =
            crmCcAccountChargeUserDao.getCreateEntityList(criteriaDto);
        assertEquals(3, resultList.size());
    }
}
