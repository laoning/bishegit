/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.UserDialogControllerMapper;
import com.biz_integral.crm.cc.domain.dto.UserDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.UserDialogReslutDto;
import com.biz_integral.crm.cc.domain.logic.UserSelectDialogLogic;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * ユーザ設定ダイアログコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class UserSetDialogControllerTest {

    /**
     * テスト対象です
     */
    protected UserSetDialogController userSetDialogController;

    /**
     * ユーザ選択ダイアログで利用するマッビー
     */
    @MockitoComponent
    protected UserDialogControllerMapper userDialogControllerMapper;

    /**
     * ユーザ選択ダイアログ対象ロジック
     */
    @MockitoComponent
    protected UserSelectDialogLogic userSelectDialogLogic;

    /**
     * {@link UserDialogFilterRequestModel}
     */
    UserDialogFilterRequestModel request = new UserDialogFilterRequestModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        UserDialogCriteriaDto criteria = new UserDialogCriteriaDto();

        criteria.searchBaseDate = DateUtil.nowDate();
        criteria.companyCd = "02";
        criteria.departmentCd = "0002";
        criteria.userCd = "1234";
        criteria.userName = "userName";

        when(userDialogControllerMapper.searchConvert(request)).thenReturn(
            criteria);

        PagingResult<UserDialogReslutDto> result =
            new PagingResult<UserDialogReslutDto>();

        for (int i = 0; i < 10; i++) {
            UserDialogReslutDto entity = new UserDialogReslutDto();

            entity.setEndDate(DateUtil.parse("20990101" + i, "yyyyMMdd"));
            entity.setStartDate(DateUtil.parse("20100101" + i, "yyyyMMdd"));
            entity.setUserCd("1234");
            entity.setUserName("userName");
            entity.setUserSearchName("userSearchName");
            result.add(entity);

        }

        when(userSelectDialogLogic.getEntityList(criteria)).thenReturn(result);

        PagingResult<UserDialogFilterResponseModel> pagingResult =
            new PagingResult<UserDialogFilterResponseModel>();
        for (int i = 0; i < 5; i++) {
            UserDialogFilterResponseModel responseModel =
                new UserDialogFilterResponseModel();

            responseModel.userCd = "1234" + i;
            responseModel.userName = "userName";
            responseModel.departmentCd = "0001" + i;
            responseModel.departmentName = "departmentName" + i;
            responseModel.effectiveTermEnd =
                DateUtil.parse("30100101" + i, "yyyyMMdd");
            responseModel.effectiveTermStart =
                DateUtil.parse("20100101" + i, "yyyyMMdd");
            pagingResult.add(responseModel);
        }
        pagingResult.setAllRecordCount(54);
        when(userDialogControllerMapper.searchConvert(result)).thenReturn(
            pagingResult);

    }

    /**
     * 初期化のテスト。
     */
    @Test
    public void testInitialize() {

        UserDialogInitializeResponseModel model =
            userSetDialogController.initialize();

        assertEquals(DateUtil.nowDate(), model.systemDate);

    }

    /**
     * 検索結果一覧ページャー押下のテスト。
     */
    @Test
    public void testFilter() {

        PagingResponse<UserDialogFilterResponseModel> result =
            userSetDialogController.filter(request);

        assertEquals(5, result.getRows().size());

        assertEquals(54, result.getTotal());

    }
}
