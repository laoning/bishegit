/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.AddressSelectDialogMapper;
import com.biz_integral.crm.cc.domain.dto.AddressSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcSkcsCmn;
import com.biz_integral.crm.cc.domain.logic.BizSkcsLogic;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 住所選択ダイアログコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class AddressSelectDialogControllerTest {

    /**
     * テスト対象です
     */
    protected AddressSelectDialogController addressSelectDialogController;

    /**
     * 住所選択ダイアログ検索画面で利用するマッパー
     */
    @MockitoComponent
    protected AddressSelectDialogMapper addressSelectDialogMapper;

    /**
     * 住所選択ダイアログロジック
     */
    @MockitoComponent
    protected BizSkcsLogic bizSkcsLogic;

    /**
     * {@link AddressSelectDialogFilterRequestModel}
     */
    AddressSelectDialogFilterRequestModel request =
        new AddressSelectDialogFilterRequestModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        AddressSelectDialogCriteriaDto criteria =
            new AddressSelectDialogCriteriaDto();
        criteria.searchBaseDate = DateUtil.nowDate();
        criteria.skcsCd = "02";
        criteria.skcsAddress = "aadasdad";
        criteria.tdfkCd = "11";
        criteria.tdfkAddress = "423435352324";

        when(addressSelectDialogMapper.convert(request)).thenReturn(criteria);

        PagingResult<CrmCcSkcsCmn> result = new PagingResult<CrmCcSkcsCmn>();

        for (int i = 0; i < 10; i++) {
            CrmCcSkcsCmn bizSkcs = new CrmCcSkcsCmn();

            bizSkcs.setEndDate(DateUtil.parse("20990101" + i, "yyyyMMdd"));
            bizSkcs.setStartDate(DateUtil.parse("20100101" + i, "yyyyMMdd"));
            bizSkcs.setTdfkCd("0" + i);
            bizSkcs.setTdfkAddress("北海道" + i);
            bizSkcs.setSkcsCd("00" + i);
            bizSkcs.setSkcsAddress("川上郡標茶町" + i);
            result.add(bizSkcs);
        }

        when(bizSkcsLogic.getEntityList(criteria)).thenReturn(result);

        PagingResult<AddressSelectDialogFilterResponseModel> pagingResult =
            new PagingResult<AddressSelectDialogFilterResponseModel>();
        for (int i = 0; i < 5; i++) {
            AddressSelectDialogFilterResponseModel responseModel =
                new AddressSelectDialogFilterResponseModel();
            responseModel.tdfkAddress = "大阪府" + i;
            responseModel.tdfkCd = "0002" + i;
            responseModel.skcsAddress = "藤井寺市" + i;
            responseModel.skcsCd = "0001" + i;
            responseModel.endDate = DateUtil.parse("30100101" + i, "yyyyMMdd");
            responseModel.startDate =
                DateUtil.parse("20100101" + i, "yyyyMMdd");
            pagingResult.add(responseModel);
        }
        pagingResult.setAllRecordCount(10);
        when(addressSelectDialogMapper.convert(result))
            .thenReturn(pagingResult);

    }

    /**
     * 初期化のテスト。
     */
    @Test
    public void testInitialize() {

        AddressSelectDialogInitializeResponseModel model =
            addressSelectDialogController.initialize();

        assertEquals(DateUtil.nowDate(), model.searchBaseDate);

    }

    /**
     * 検索結果一覧ページャー押下のテスト。
     */
    @Test
    public void testFilter() {

        PagingResponse<AddressSelectDialogFilterResponseModel> result =
            addressSelectDialogController.filter(request);

        assertEquals(5, result.getRows().size());

        assertEquals(10, result.getTotal());

    }
}
