/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeDept;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcAccountChargeDeptDaoTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcAccountChargeDeptDaoTest {

    /**
     * テスト対象
     */
    private CrmCcAccountChargeDeptDao crmCcAccountChargeDeptDao;

    /**
     * CRMアカウント担当組織を一覧検索のテスト<br>
     */
    @Test
    public void testGetEntityList() {
        CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto =
            new CrmCcAccountChargeDeptCriteriaDto();
        chargeDeptCriteriaDto.companyCd = "0001";
        chargeDeptCriteriaDto.crmAccountCd = "0001";
        chargeDeptCriteriaDto.crmDomainCd = "1";
        chargeDeptCriteriaDto.localeId = "ja";
        List<CrmCcAccountChargeDeptResultDto> resultList =
            crmCcAccountChargeDeptDao.getEntityList(chargeDeptCriteriaDto);
        assertEquals(3, resultList.size());
    }

    /**
     * CRMアカウント担当組織を一覧検索のテスト<br>
     */
    @Test
    public void testGetCreateEntityList() {
        CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto =
            new CrmCcAccountChargeDeptCriteriaDto();
        chargeDeptCriteriaDto.companyCd = "0001";
        chargeDeptCriteriaDto.crmAccountCd = "0001";
        chargeDeptCriteriaDto.crmDomainCd = "1";
        chargeDeptCriteriaDto.localeId = "ja";
        chargeDeptCriteriaDto.departmentCd = "hx0603";
        List<CrmCcAccountChargeDept> resultList =
            crmCcAccountChargeDeptDao
                .getCreateEntityList(chargeDeptCriteriaDto);
        assertEquals(3, resultList.size());
    }

}
