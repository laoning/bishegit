/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dao.CrmCcUserCmnDao;
import com.biz_integral.crm.cc.domain.dto.UserDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.UserDialogReslutDto;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

@RunWith(BizIntegralTest.class)
public class UserSelectDialogLogicImplTest {

    /**
     * UserSetDialogLogicImpl
     */
    private UserSelectDialogLogicImpl userSelectDialogLogicImpl;

    /**
     * Mockitoを利用し、生成したモックオブジェクトです。<br>
     * 
     */
    @MockitoComponent(componentName = "crmCcUserCmnDao")
    private CrmCcUserCmnDao dao;

    /**
     * UserDialogCriteriaDto
     */
    UserDialogCriteriaDto dto = new UserDialogCriteriaDto();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        PagingResult<UserDialogReslutDto> searchList =
            new PagingResult<UserDialogReslutDto>();

        for (int i = 0; i < 10; i++) {
            UserDialogReslutDto userResult = new UserDialogReslutDto();
            userResult.setDepartmentCd("departmentCd" + i);
            userResult.setDepartmentName("departmentName" + i);
            userResult.setUserCd("userCd" + i);
            userResult.setUserName("userName" + i);
            userResult.setStartDate(DateUtil.parse("20100501" + i, "yyyyMMdd"));
            userResult.setEndDate(DateUtil.parse("20140501" + i, "yyyyMMdd"));
            searchList.add(userResult);
        }
        when(dao.findByUserDialogCriteriaDto(dto)).thenReturn(searchList);
    }

    /**
     * モックがロジッククラスに設定されているか否かのテスト
     * 
     */
    @Test
    public void testGetEntityList() {
        PagingResult<UserDialogReslutDto> result =
            this.userSelectDialogLogicImpl.getEntityList(dto);

        // メソッド呼び出しの確認
        verify(dao).findByUserDialogCriteriaDto(dto);
        for (int i = 0; i < 10; i++) {
            UserDialogReslutDto userDialogReslutDto =
                result.getResultList().get(i);

            assertEquals("departmentCd" + i, userDialogReslutDto
                .getDepartmentCd());

            assertEquals("departmentName" + i, userDialogReslutDto
                .getDepartmentName());
            assertEquals("userCd" + i, userDialogReslutDto.getUserCd());
            assertEquals("userName" + i, userDialogReslutDto.getUserName());
            assertEquals(
                DateUtil.parse("20100501" + i, "yyyyMMdd"),
                userDialogReslutDto.getStartDate());
            assertEquals(
                DateUtil.parse("20140501" + i, "yyyyMMdd"),
                userDialogReslutDto.getEndDate());
        }

    }

    /**
     * メソッドコールをしなかった場合の例<br> {@link WantedButNotInvoked}が発生します。
     */
    @Test
    public void testFindAllForNotMethodCall() {
        try {
            verify(dao).findAll();
            fail();
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }
}
