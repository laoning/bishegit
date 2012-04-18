/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.ContactDialogMapper;
import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * コンタクト選択ダイアログコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class ContactSelectDialogControllerTest {

    /**
     * テスト対象です
     */
    protected ContactSelectDialogController contactSelectDialogController;

    /**
     * {@link ContactDialogMapper 検索Mapper}
     */
    @MockitoComponent
    protected ContactDialogMapper contactDialogMapper;

    /**
     * {@link CrmContactLogic コンタクト検索Logic}
     */
    @MockitoComponent
    protected CrmContactLogic crmContactLogic;

    /**
     * {@link EnumNamesRegistry 区分に関する名称管理}
     */
    @MockitoComponent
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent
    protected ContextContainer contextContainer;

    /**
     * {@link ContactSelectDialogFilterRequestModel}
     */
    ContactSelectDialogFilterRequestModel request =
        new ContactSelectDialogFilterRequestModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setLocaleID("ja");
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);
        ContactSearchCriteriaDto criteria = new ContactSearchCriteriaDto();
        criteria.crmContactName = "ContactName";
        criteria.companyCd = "0001";
        criteria.localeID = "ja";
        criteria.crmContactCd = "1111";
        criteria.crmContactSearchName = "SearchName";
        criteria.crmContactShortName = "ShortName";

        when(contactDialogMapper.convert(request)).thenReturn(criteria);

        PagingResult<ContactSearchResultDto> result =
            new PagingResult<ContactSearchResultDto>();

        for (int i = 0; i < 10; i++) {
            ContactSearchResultDto entity = new ContactSearchResultDto();
            entity.setCrmContactName("ContactName" + i);
            entity.setKeyPerson("Person1" + i);
            entity.setPost("post1" + i);
            result.add(entity);

        }

        when(crmContactLogic.getEntityList(criteria)).thenReturn(result);

        PagingResult<ContactSelectDialogFilterResponseModel> pagingResult =
            new PagingResult<ContactSelectDialogFilterResponseModel>();

        for (int i = 0; i < 5; i++) {
            ContactSelectDialogFilterResponseModel responseModel =
                new ContactSelectDialogFilterResponseModel();
            responseModel.crmContactCd = "1111" + i;
            responseModel.keyPerson = "Person" + i;
            responseModel.post = "post1" + i;
            pagingResult.add(responseModel);
        }
        pagingResult.setAllRecordCount(10);
        when(contactDialogMapper.convert(result)).thenReturn(pagingResult);

    }

    /**
     * 検索結果一覧ページャー押下のテスト。
     */
    @Test
    public void testFilter() {

        PagingResponse<ContactSelectDialogFilterResponseModel> result =
            contactSelectDialogController.filter(request);

        assertEquals(5, result.getRows().size());

        assertEquals(10, result.getTotal());

    }
}
