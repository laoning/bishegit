/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.UserDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.UserDialogReslutDto;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;

@RunWith(BizIntegralTest.class)
public class CrmCcUserCmnDaoTest {

    /**
     * テスト対象
     */
    private CrmCcUserCmnDao crmCcUserCmnDao = null;

    /**
     * 入力対象
     */
    private UserDialogCriteriaDto criteriaDto = null;

    /**
     * 
     * TODO Javadoc
     */
    @Before
    public void setup() {
        criteriaDto = new UserDialogCriteriaDto();
    }

    /**
     * ユーザ設定一覧を取得するのテスト。<br>
     * ユーザ設定一覧入力Dto。
     */
    @Test
    public void testFindByCriteria() {
        criteriaDto.localeId = "ja";
        criteriaDto.deleteFlag = "0";
        criteriaDto.companyCd = "0001";
        PagingResult<UserDialogReslutDto> actual =
            crmCcUserCmnDao.findByUserDialogCriteriaDto(criteriaDto);
        assertEquals(8, actual.size());
        assertEquals(8L, actual.getAllRecordCount());
    }

    /**
     * ユーザ設定一覧を取得するのテスト。<br>
     * ユーザ設定一覧入力Dto。
     */
    @Test
    public void testFindByCriteriaCriteriaForLikePrefix() {
        criteriaDto.localeId = "ja";
        criteriaDto.deleteFlag = "0";
        criteriaDto.companyCd = "0001";
        criteriaDto.userCd = "hx0";
        PagingResult<UserDialogReslutDto> actual =
            crmCcUserCmnDao.findByUserDialogCriteriaDto(criteriaDto);
        assertEquals(4, actual.size());
        assertEquals(4L, actual.getAllRecordCount());
    }

    /**
     * ユーザ設定一覧を取得するのテスト。<br>
     * ユーザ設定一覧入力Dto。
     */
    @Test
    public void testFindByCriteriaCriteriaForUserNameLikeContain() {
        criteriaDto.localeId = "ja";
        criteriaDto.deleteFlag = "0";
        criteriaDto.companyCd = "0001";
        criteriaDto.userName = "da";
        PagingResult<UserDialogReslutDto> actual =
            crmCcUserCmnDao.findByUserDialogCriteriaDto(criteriaDto);
        assertEquals(4, actual.size());
        assertEquals(4L, actual.getAllRecordCount());
    }

    /**
     * ユーザ設定一覧を取得するのテスト。<br>
     * ユーザ設定一覧入力Dto。
     */
    @Test
    public void testFindByCriteriaCriteriaForUserShortNameLikeContain() {
        criteriaDto.localeId = "ja";
        criteriaDto.deleteFlag = "0";
        criteriaDto.companyCd = "0001";
        criteriaDto.userShortName = "ch";
        PagingResult<UserDialogReslutDto> actual =
            crmCcUserCmnDao.findByUserDialogCriteriaDto(criteriaDto);
        assertEquals(4, actual.size());
        assertEquals(4L, actual.getAllRecordCount());
    }
}
