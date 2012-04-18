/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.AccountDialogMapper;
import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * アカウント選択ダイアログコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class AccountSelectDialogControllerTest {

    /**
     * テスト対象です
     */
    protected AccountSelectDialogController accountSelectDialogController;

    /**
     * 区分に関する名称管理API
     */
    @MockitoComponent
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link AccountDialogMapper アカウント検索Mapper}
     */
    @MockitoComponent
    protected AccountDialogMapper accountDialogMapper;

    /**
     * {@link CrmAccountLogic アカウント検索Logic}
     */
    @MockitoComponent
    protected CrmAccountLogic crmAccountLogic;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent
    protected ContextContainer contextContainer;

    /**
     * {@link AccountSelectDialogFilterRequestModel}
     */
    AccountSelectDialogFilterRequestModel request =
        new AccountSelectDialogFilterRequestModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        AccountSearchCriteriaDto criteria = new AccountSearchCriteriaDto();
        when(accountDialogMapper.convert(request)).thenReturn(criteria);

        PagingResult<AccountSearchResultDto> pagingResult =
            new PagingResult<AccountSearchResultDto>();

        when(crmAccountLogic.getEntityList(criteria)).thenReturn(pagingResult);

        PagingResult<AccountSelectDialogFilterResponseModel> pagingList =
            new PagingResult<AccountSelectDialogFilterResponseModel>();
        for (int i = 0; i < 10; i++) {
            AccountSelectDialogFilterResponseModel response =
                new AccountSelectDialogFilterResponseModel();
            pagingList.add(response);
        }
        pagingList.setAllRecordCount(16);
        when(accountDialogMapper.convert(pagingResult)).thenReturn(pagingList);

        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setLocaleID("ja");
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);
    }

    /**
     * 初期化のテスト。
     */
    @Test
    public void testInitialize() {

        AccountSelectDialogInitializeResponseModel model =
            accountSelectDialogController.initialize();

        assertEquals(4, model.crmAccountTypes.size());
        assertEquals(6, model.importantLevelTypes.size());
        assertEquals(6, model.crmAccountStatusList.size());
    }

    /**
     * 検索結果一覧ページャー押下のテスト。
     */
    @Test
    public void testFilter() {

        PagingResponse<AccountSelectDialogFilterResponseModel> result =
            accountSelectDialogController.filter(request);

        assertEquals(16, result.getTotal());
        assertEquals(10, result.getRows().size());
    }
}
