/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeDept;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcContactChargeDeptDaoクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcContactChargeDeptDaoTest {

    /**
     * テスト対象
     */
    private CrmCcContactChargeDeptDao crmCcContactChargeDeptDao;

    /**
     * CRMコンタクト担当組織を一覧検索のテスト<br>
     */
    @Test
    public void testGetEntityList() {
        CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto =
            new CrmCcContactChargeDeptCriteriaDto();
        chargeDeptCriteriaDto.companyCd = "0001";
        chargeDeptCriteriaDto.crmContactCd = "0001";
        chargeDeptCriteriaDto.localeId = "ja";
        List<CrmCcContactChargeDeptResultDto> resultList =
            crmCcContactChargeDeptDao.getEntityList(chargeDeptCriteriaDto);
        assertEquals(3, resultList.size());
    }

    /**
     * 登録前CRMコンタクト担当組織を一覧検索のテスト<br>
     */
    @Test
    public void testGetCreateEntityList() {
        CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto =
            new CrmCcContactChargeDeptCriteriaDto();
        chargeDeptCriteriaDto.companyCd = "0001";
        chargeDeptCriteriaDto.crmContactCd = "0001";
        chargeDeptCriteriaDto.localeId = "ja";
        chargeDeptCriteriaDto.departmentCd = "hx0603";
        List<CrmCcContactChargeDept> resultList =
            crmCcContactChargeDeptDao
                .getCreateEntityList(chargeDeptCriteriaDto);
        assertEquals(3, resultList.size());
    }

}
