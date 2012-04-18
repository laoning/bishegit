/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.DepartmentDialogMapper;
import com.biz_integral.crm.cc.domain.dto.DepartmentDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.cc.domain.logic.DepartmentDialogLogic;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 組織選択ダイアログコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class DepartmentSelectDialogControllerTest {

    /**
     * テスト対象です
     */
    protected DepartmentSelectDialogController controller;

    /**
     * 組織選択ダイアログ検索画面で利用するマッパー
     */
    @MockitoComponent
    protected DepartmentDialogMapper mapper;

    /**
     * 組織選択ダイアログロジック
     */
    @MockitoComponent
    protected DepartmentDialogLogic logic;

    /**
     * {@link DepartmentDialogFilterRequestModel}
     */
    DepartmentDialogFilterRequestModel request =
        new DepartmentDialogFilterRequestModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        DepartmentDialogCriteriaDto criteria =
            new DepartmentDialogCriteriaDto();
        criteria.searchBaseDate = DateUtil.nowDate();
        criteria.companyCd = "02";
        criteria.departmentCd = "0002";
        criteria.departmentName = "組織名";
        criteria.departmentSearchName = "組織検索名";
        criteria.departmentShortName = "組織略称";

        when(mapper.convert(request)).thenReturn(criteria);

        PagingResult<CrmCcDepartmentCmn> result =
            new PagingResult<CrmCcDepartmentCmn>();

        for (int i = 0; i < 10; i++) {
            CrmCcDepartmentCmn entity = new CrmCcDepartmentCmn();

            entity.setEndDate(DateUtil.parse("20990101" + i, "yyyyMMdd"));
            entity.setStartDate(DateUtil.parse("20100101" + i, "yyyyMMdd"));
            entity.setCompanyCd("0001" + i);
            entity.setDepartmentCd("00000" + i);
            entity.setDepartmentName("DepartmentName" + i);
            result.add(entity);

        }

        when(logic.getEntityListTree(criteria)).thenReturn(result);

        PagingResult<DepartmentDialogFilterResponseModel> pagingResult =
            new PagingResult<DepartmentDialogFilterResponseModel>();
        for (int i = 0; i < 5; i++) {
            DepartmentDialogFilterResponseModel responseModel =
                new DepartmentDialogFilterResponseModel();
            responseModel.departmentCd = "0001" + i;
            responseModel.departmentName = "0002" + i;
            responseModel.effectiveTermEnd =
                DateUtil.parse("30100101" + i, "yyyyMMdd");
            responseModel.effectiveTermStart =
                DateUtil.parse("20100101" + i, "yyyyMMdd");
            pagingResult.add(responseModel);
        }
        pagingResult.setAllRecordCount(10);
        when(mapper.convert(result)).thenReturn(pagingResult);

    }

    /**
     * 初期化のテスト。
     */
    @Test
    public void testInitialize() {

        DepartmentSelectDialogInitializeResponseModel model =
            controller.initialize();

        assertEquals(DateUtil.nowDate(), model.searchBaseDate);

    }

    /**
     * 検索結果一覧ページャー押下のテスト。
     */
    @Test
    public void testFilter() {

        PagingResponse<DepartmentDialogFilterResponseModel> result =
            controller.filter(request);

        assertEquals(5, result.getRows().size());

        assertEquals(10, result.getTotal());

    }
}
