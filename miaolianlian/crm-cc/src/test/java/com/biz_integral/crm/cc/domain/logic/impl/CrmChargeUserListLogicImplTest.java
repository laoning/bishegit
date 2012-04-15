/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeResultDto;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.domain.master.BizConfigurationProvider;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * CRM担当者一覧ロジックのテストクラスです。
 */
@RunWith(BizIntegralTest.class)
public class CrmChargeUserListLogicImplTest {

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
    List<ChargeUserListChangeAfterDto> changeAfterList =
        new ArrayList<ChargeUserListChangeAfterDto>();

    /**
     * {@link List<ChargeUserListChangeBeforeDto>}
     */
    List<ChargeUserListChangeBeforeDto> changeBeforeList =
        new ArrayList<ChargeUserListChangeBeforeDto>();

    /**
     * {@link List<ChargeUserListChangeResultDto>}
     */
    List<ChargeUserListChangeResultDto> resultList =
        new ArrayList<ChargeUserListChangeResultDto>();

    /**
     * {@link CrmChargeUserListLogicImpl}
     */
    private CrmChargeUserListLogicImpl crmChargeUserListLogicImpl;

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
     * CRM担当者一覧の変更前後をマージのテストです。
     */
    @Test
    public void testSetUserList_merge() {

        ChargeUserListChangeBeforeDto changeBefore =
            new ChargeUserListChangeBeforeDto();
        changeBefore.userCd = "0001";
        changeBefore.mainChargeFlag = "1";
        changeBefore.startDate = "20100101";
        changeBefore.endDate = "20110101";
        changeBeforeList.add(changeBefore);
        ChargeUserListChangeAfterDto changeAfter =
            new ChargeUserListChangeAfterDto();
        changeAfter.userCd = "0001";
        changeAfter.userName = "username";
        changeAfter.effectiveTermStart = "20101201";
        changeAfter.effectiveTermEnd = "20111201";
        changeAfterList.add(changeAfter);
        List<ChargeUserListChangeResultDto> resultList =
            crmChargeUserListLogicImpl.setUserList(
                changeAfterList,
                changeBeforeList);

        assertEquals("1", resultList.get(0).mainChargeFlag);
        assertEquals("20100101", resultList.get(0).startDate);
        assertEquals("20110101", resultList.get(0).endDate);
    }

    /**
     * CRM担当者一覧の追加のテストです。
     */
    @Test
    public void testSetUserList_create() {
        Date date = DateUtil.nowDate();
        when(bizConfigurationProvider.getEndDate()).thenReturn(date);
        ChargeUserListChangeBeforeDto changeBefore =
            new ChargeUserListChangeBeforeDto();
        changeBefore.userCd = "0001";
        changeBefore.mainChargeFlag = "1";
        changeBefore.startDate = "20100101";
        changeBefore.endDate = "20110101";
        changeBeforeList.add(changeBefore);
        ChargeUserListChangeAfterDto changeAfter =
            new ChargeUserListChangeAfterDto();
        changeAfter.userCd = "0002";
        changeAfter.userName = "username";
        changeAfter.effectiveTermStart = "20101201";
        changeAfter.effectiveTermEnd = "20111201";
        changeAfterList.add(changeAfter);
        List<ChargeUserListChangeResultDto> resultList =
            crmChargeUserListLogicImpl.setUserList(
                changeAfterList,
                changeBeforeList);
        assertEquals("0", resultList.get(0).mainChargeFlag);
        assertEquals("0002", resultList.get(0).userCd);
    }

    /**
     * CRM担当者主担当者設定の設定のテストです。
     */
    @Test
    public void testSetMainChargeUser_set() {

        String userCd = "0001";
        ChargeUserListChangeBeforeDto changeBefore =
            new ChargeUserListChangeBeforeDto();
        changeBefore.userCd = "0001";
        changeBefore.mainChargeFlag = "1";
        changeBeforeList.add(changeBefore);
        ChargeUserListChangeResultDto result =
            new ChargeUserListChangeResultDto();
        when(beanMapper.map(changeBefore, ChargeUserListChangeResultDto.class))
            .thenReturn(result);
        List<ChargeUserListChangeResultDto> resultList =
            crmChargeUserListLogicImpl.setMainChargeUser(
                userCd,
                changeBeforeList);
        assertEquals("1", resultList.get(0).mainChargeFlag);
    }
}
