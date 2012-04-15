/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * コンタクト検索画面のコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class SearchControllerTest {

    /**
     * {@link SearchMapper コンタクト検索Mapper}
     */
    @MockitoComponent
    protected SearchMapper searchMapper;

    /**
     * {@link CrmContactLogic コンタクト検索Logic}
     */
    @MockitoComponent
    protected CrmContactLogic crmContactLogic;

    /**
     * テスト対象です
     */
    protected SearchController searchController;

    /**
     * {@link SearchFilterRequestModel}
     */
    private SearchFilterRequestModel request;

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        ContactSearchCriteriaDto criteria = new ContactSearchCriteriaDto();

        when(searchMapper.convert(request)).thenReturn(criteria);

        PagingResult<ContactSearchResultDto> pagingResult =
            new PagingResult<ContactSearchResultDto>();

        when(crmContactLogic.getEntityList(criteria)).thenReturn(pagingResult);

        PagingResult<SearchFilterResponseModel> pagingList =
            new PagingResult<SearchFilterResponseModel>();

        for (int i = 0; i < 10; i++) {

            SearchFilterResponseModel model = new SearchFilterResponseModel();
            model.belong = "belong" + i;
            model.crmContactCd = "crmContactCd" + i;
            model.crmContactName = "crmContactName" + i;
            model.crmContactNameKana = "crmContactNameKana" + i;
            model.emailAddress1 = "emailAddress1" + i;
            model.keyPerson = "keyPerson" + i;
            model.post = "post" + i;
            model.sex = "sex" + i;
            model.telephoneNumber = "telephoneNumber" + i;
            model.userCd = "userCd" + i;
            model.userName = "userName" + i;
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(10);

        when(searchMapper.convert(pagingResult)).thenReturn(pagingList);

    }

    /**
     * 検索結果一覧ページャー押下の処理をテストします。
     */
    @Test
    public void testFilter() {

        PagingResponse<SearchFilterResponseModel> result =
            searchController.filter(request);

        assertEquals(10, result.getTotal());
        assertEquals(10, result.getRows().size());
        for (int i = 0; i < 10; i++) {
            SearchFilterResponseModel model = result.getRows().get(i);

            assertEquals("belong" + i, model.belong);
            assertEquals("crmContactCd" + i, model.crmContactCd);
            assertEquals("crmContactName" + i, model.crmContactName);
            assertEquals("crmContactNameKana" + i, model.crmContactNameKana);
            assertEquals("emailAddress1" + i, model.emailAddress1);
            assertEquals("keyPerson" + i, model.keyPerson);
            assertEquals("post" + i, model.post);
            assertEquals("sex" + i, model.sex);
            assertEquals("telephoneNumber" + i, model.telephoneNumber);
            assertEquals("userCd" + i, model.userCd);
            assertEquals("userName" + i, model.userName);
        }
    }
}
