/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.CustomerSelectDialogMapper;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.crm.cc.domain.logic.ImCustomerLogic;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 取引先選択ダイアログコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class CustomerSelectDialogControllerTest {

    /**
     * テスト対象です
     */
    protected CustomerSelectDialogController controller;

    /**
     * 取引先選択ダイアログ検索画面で利用するマッパー
     */
    @MockitoComponent
    protected CustomerSelectDialogMapper mapper;

    /**
     * 取引先選択ダイアログロジック
     */
    @MockitoComponent
    protected ImCustomerLogic logic;

    /**
     * {@link CustomerSelectDialogFilterRequestModel}
     */
    CustomerSelectDialogFilterRequestModel request =
        new CustomerSelectDialogFilterRequestModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        CustomerSelectDialogCriteriaDto criteria =
            new CustomerSelectDialogCriteriaDto();
        criteria.searchBaseDate = DateUtil.nowDate();
        criteria.companyCd = "02";
        criteria.customerCd = "0002";
        criteria.customerName = "取引先名";
        criteria.customerSearchName = "取引先検索名";
        criteria.customerShortName = "取引先略称";

        when(mapper.convert(request)).thenReturn(criteria);

        PagingResult<CrmCcCustomerCmn> result =
            new PagingResult<CrmCcCustomerCmn>();

        for (int i = 0; i < 10; i++) {
            CrmCcCustomerCmn entity = new CrmCcCustomerCmn();

            entity.setEndDate(DateUtil.parse("20990101" + i, "yyyyMMdd"));
            entity.setStartDate(DateUtil.parse("20100101" + i, "yyyyMMdd"));
            entity.setCompanyCd("0001" + i);
            entity.setCustomerCd("00000" + i);
            entity.setCustomerName("CustomerName" + i);
            result.add(entity);
        }

        when(logic.getEntityList(criteria)).thenReturn(result);

        PagingResult<CustomerSelectDialogFilterResponseModel> pagingResult =
            new PagingResult<CustomerSelectDialogFilterResponseModel>();
        for (int i = 0; i < 5; i++) {
            CustomerSelectDialogFilterResponseModel responseModel =
                new CustomerSelectDialogFilterResponseModel();
            responseModel.customerCd = "0001" + i;
            responseModel.customerName = "0002" + i;
            responseModel.endDate = DateUtil.parse("30100101" + i, "yyyyMMdd");
            responseModel.startDate =
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

        CustomerSelectDialogInitializeResponseModel model =
            controller.initialize();

        assertEquals(DateUtil.nowDate(), model.searchBaseDate);

    }

    /**
     * 検索結果一覧ページャー押下のテスト。
     */
    @Test
    public void testFilter() {

        PagingResponse<CustomerSelectDialogFilterResponseModel> result =
            controller.filter(request);

        assertEquals(5, result.getRows().size());

        assertEquals(10, result.getTotal());

    }
}
