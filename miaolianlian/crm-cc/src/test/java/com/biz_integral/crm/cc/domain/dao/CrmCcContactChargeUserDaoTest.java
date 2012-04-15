/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeUser;
import com.biz_integral.crm.sa.domain.dto.SalesActCheckDto;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcContactChargeUserDaoクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcContactChargeUserDaoTest {

    /**
     * テスト対象
     */
    private CrmCcContactChargeUserDao crmCcContactChargeUserDao;

    /**
     * CRMコンタクト担当者を一覧検索のテスト<br>
     */
    @Test
    public void testGetEntityList() {
        CrmCcContactChargeUserCriteriaDto criteriaDto =
            new CrmCcContactChargeUserCriteriaDto();
        criteriaDto.companyCd = "0001";
        criteriaDto.crmContactCd = "0001";
        criteriaDto.localeId = "ja";
        List<CrmCcContactChargeUserResultDto> resultList =
            crmCcContactChargeUserDao.getEntityList(criteriaDto);
        assertEquals(15, resultList.size());
    }

    /**
     * 登録前CRMコンタクト担当者を一覧検索のテスト<br>
     */
    @Test
    public void testGetCreateEntityList() {
        CrmCcContactChargeUserCriteriaDto criteriaDto =
            new CrmCcContactChargeUserCriteriaDto();
        criteriaDto.companyCd = "0001";
        criteriaDto.crmContactCd = "0001";
        criteriaDto.localeId = "ja";
        criteriaDto.userCd = "hx0603";
        List<CrmCcContactChargeUser> resultList =
            crmCcContactChargeUserDao.getCreateEntityList(criteriaDto);
        assertEquals(2, resultList.size());
    }

    /**
     * ユーザーの件数を取得のテスト<br>
     */
    @Test
    public void testGetCountOfUser() {
        SalesActCheckDto checkDto = new SalesActCheckDto();
        checkDto.companyCd = "0001";
        checkDto.crmContactCd = "0001";
        checkDto.salesActivityStartDate = new Date();
        assertEquals(2, crmCcContactChargeUserDao.getCountOfUser(checkDto));
    }

    /**
     * 有効なユーザーの件数を取得のテスト<br>
     */
    @Test
    public void testGetCountOfEnabledUser() {
        SalesActCheckDto checkDto = new SalesActCheckDto();
        checkDto.companyCd = "0001";
        checkDto.crmContactCd = "0001";
        checkDto.recordUserCd = "aoyagi";
        checkDto.salesActivityStartDate = new Date();
        assertEquals(1, crmCcContactChargeUserDao
            .getCountOfEnabledUser(checkDto));
    }
}
