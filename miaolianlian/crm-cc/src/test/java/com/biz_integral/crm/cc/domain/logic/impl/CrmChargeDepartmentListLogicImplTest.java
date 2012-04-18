/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeResultDto;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.domain.master.BizConfigurationProvider;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * CRM担当組織一覧ロジックのテストクラスです。
 */
@RunWith(BizIntegralTest.class)
public class CrmChargeDepartmentListLogicImplTest {

    /**
     * {@link BizConfigurationProvider コンテキストコンテナー}
     */
    @MockitoComponent()
    protected BizConfigurationProvider bizConfigurationProvider;

    /**
     * 区分に関する名称管理API
     */
    @MockitoComponent()
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link BeanMapper}
     */
    @MockitoComponent()
    protected BeanMapper beanMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent()
    protected ContextContainer contextContainer;

    /**
     * {@link List<ChargeDeptListChangeAfterDto>}
     */
    List<ChargeDeptListChangeAfterDto> changeAfterList =
        new ArrayList<ChargeDeptListChangeAfterDto>();

    /**
     * {@link List<ChargeDeptListChangeBeforeDto>}
     */
    List<ChargeDeptListChangeBeforeDto> changeBeforeList =
        new ArrayList<ChargeDeptListChangeBeforeDto>();

    /**
     * {@link List<ChargeDeptListChangeResultDto>}
     */
    List<ChargeDeptListChangeResultDto> resultList =
        new ArrayList<ChargeDeptListChangeResultDto>();

    /**
     * {@link CrmChargeDepartmentListLogicImpl}
     */
    private CrmChargeDepartmentListLogicImpl crmChargeDepartmentListLogicImpl;

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setLocaleID("ja");
        userContextImpl.setUserID("aoyagi");
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);
    }

    /**
     * CRM担当組織一覧の変更前後をマージのテストです。
     */
    @Test
    public void testSetDepartmentList_merge() {

        ChargeDeptListChangeBeforeDto changeBefore =
            new ChargeDeptListChangeBeforeDto();
        changeBefore.departmentCd = "0001";
        changeBefore.mainChargeFlag = "1";
        changeBefore.startDate = "20100101";
        changeBefore.endDate = "20110101";
        changeBeforeList.add(changeBefore);
        ChargeDeptListChangeAfterDto changeAfter =
            new ChargeDeptListChangeAfterDto();
        changeAfter.departmentCd = "0001";
        changeAfter.departmentName = "deptname";
        changeAfter.effectiveTermStart = "20101201";
        changeAfter.effectiveTermEnd = "20111201";
        changeAfterList.add(changeAfter);
        List<ChargeDeptListChangeResultDto> resultList =
            crmChargeDepartmentListLogicImpl.setDepartmentList(
                changeAfterList,
                changeBeforeList);

        assertEquals("1", resultList.get(0).mainChargeFlag);
        assertEquals("20100101", resultList.get(0).startDate);
        assertEquals("20110101", resultList.get(0).endDate);
    }

    /**
     * CRM担当組織一覧の追加のテストです。
     */
    @Test
    public void testSetDepartmentList_create() {
        Date date = DateUtil.nowDate();
        when(bizConfigurationProvider.getEndDate()).thenReturn(date);
        ChargeDeptListChangeBeforeDto changeBefore =
            new ChargeDeptListChangeBeforeDto();
        changeBefore.departmentCd = "0001";
        changeBefore.mainChargeFlag = "1";
        changeBefore.startDate = "20100101";
        changeBefore.endDate = "20110101";
        changeBeforeList.add(changeBefore);
        ChargeDeptListChangeAfterDto changeAfter =
            new ChargeDeptListChangeAfterDto();
        changeAfter.departmentCd = "0002";
        changeAfter.departmentName = "deptname";
        changeAfter.effectiveTermStart = "20101201";
        changeAfter.effectiveTermEnd = "20111201";
        changeAfterList.add(changeAfter);
        List<ChargeDeptListChangeResultDto> resultList =
            crmChargeDepartmentListLogicImpl.setDepartmentList(
                changeAfterList,
                changeBeforeList);
        assertEquals("0", resultList.get(0).mainChargeFlag);
        assertEquals("0002", resultList.get(0).departmentCd);
    }

    /**
     * CRM担当組織主担当者設定の設定のテストです。
     */
    @Test
    public void testSetMainChargeDepartment_set() {

        String departmentCd = "0001";
        ChargeDeptListChangeBeforeDto changeBefore =
            new ChargeDeptListChangeBeforeDto();
        changeBefore.departmentCd = "0001";
        changeBefore.mainChargeFlag = "1";
        changeBefore.effectiveTermStart = "20100101";
        changeBefore.effectiveTermEnd = "20110101";
        changeBeforeList.add(changeBefore);
        ChargeDeptListChangeResultDto result =
            new ChargeDeptListChangeResultDto();
        when(beanMapper.map(changeBefore, ChargeDeptListChangeResultDto.class))
            .thenReturn(result);
        List<ChargeDeptListChangeResultDto> resultList =
            crmChargeDepartmentListLogicImpl.setMainChargeDepartment(
                departmentCd,
                changeBeforeList);
        assertEquals("1", resultList.get(0).mainChargeFlag);
    }

    /**
     * CRM担当組織主担当者設定の設定のテストです。
     */
    @Test
    public void testSetMainChargeDepartment_cancel() {

        String departmentCd = "0001";
        ChargeDeptListChangeBeforeDto changeBefore =
            new ChargeDeptListChangeBeforeDto();
        changeBefore.departmentCd = "0002";
        changeBefore.mainChargeFlag = "1";
        changeBefore.effectiveTermStart = "20100101";
        changeBefore.effectiveTermEnd = "20110101";
        changeBeforeList.add(changeBefore);
        ChargeDeptListChangeResultDto result =
            new ChargeDeptListChangeResultDto();
        when(beanMapper.map(changeBefore, ChargeDeptListChangeResultDto.class))
            .thenReturn(result);
        try {
            crmChargeDepartmentListLogicImpl.setMainChargeDepartment(
                departmentCd,
                changeBeforeList);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}
