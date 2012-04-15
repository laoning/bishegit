/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.types.CrmAccountStatus;
import com.biz_integral.crm.cc.domain.types.CrmAccountType;
import com.biz_integral.crm.cc.domain.types.ImportantLevelType;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * アカウント検索画面のコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class SearchControllerTest {

    /**
     * 区分に関する名称管理API
     */
    @MockitoComponent
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link SearchMapper アカウント検索Mapper}
     */
    @MockitoComponent
    protected SearchMapper searchMapper;

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

        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setLocaleID("ja");
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);

        when(
            enumNamesRegistry.getName(CrmAccountType.LARGE, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("大");

        when(
            enumNamesRegistry.getName(CrmAccountType.MEDIUM, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("中");

        when(
            enumNamesRegistry.getName(CrmAccountType.SMALL, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("小");

        when(
            enumNamesRegistry.getName(ImportantLevelType.HIGH, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("5");
        when(
            enumNamesRegistry.getName(
                ImportantLevelType.MIDDLE_HIGH,
                LocaleUtil.toLocale(contextContainer
                    .getUserContext()
                    .getLocaleID()))).thenReturn("4");
        when(
            enumNamesRegistry.getName(ImportantLevelType.MIDDLE, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("3");

        when(
            enumNamesRegistry.getName(ImportantLevelType.MIDDLE_LOW, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("2");
        when(
            enumNamesRegistry.getName(ImportantLevelType.LOW, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("1");

        when(
            enumNamesRegistry.getName(CrmAccountStatus.TEMPENTRY, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("仮登録");
        when(
            enumNamesRegistry.getName(
                CrmAccountStatus.CONTEMPLATIONCUSTOMER,
                LocaleUtil.toLocale(contextContainer
                    .getUserContext()
                    .getLocaleID()))).thenReturn("見込み客");
        when(
            enumNamesRegistry.getName(CrmAccountStatus.TRADING, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("取引中");

        when(
            enumNamesRegistry.getName(CrmAccountStatus.TRADINGSTOP, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("取引停止");
        when(
            enumNamesRegistry.getName(
                CrmAccountStatus.DUPLICATIONCANCEL,
                LocaleUtil.toLocale(contextContainer
                    .getUserContext()
                    .getLocaleID()))).thenReturn("重複末梢");

        AccountSearchCriteriaDto criteria = new AccountSearchCriteriaDto();

        when(searchMapper.convert(request)).thenReturn(criteria);

        PagingResult<AccountSearchResultDto> pagingResult =
            new PagingResult<AccountSearchResultDto>();

        when(crmAccountLogic.getEntityList(criteria)).thenReturn(pagingResult);

        PagingResult<SearchFilterResponseModel> pagingList =
            new PagingResult<SearchFilterResponseModel>();

        for (int i = 0; i < 10; i++) {

            SearchFilterResponseModel model = new SearchFilterResponseModel();
            model.address = "address" + i;
            model.businessStartDate = DateUtil.parse("2010071" + i, "yyyyMMdd");
            model.contractStartDate = "2010081" + i;
            model.crmAccountCd = "crmAccountCd" + i;
            model.crmAccountName = "crmAccountName" + i;
            model.crmAccountNameKana = "crmAccountNameKana" + i;
            model.crmAccountStatus = "crmAccountStatus" + i;
            model.crmAccountType = "crmAccountType" + i;
            model.deleteFlag = "deleteFlag" + i;
            model.departmentCd = "departmentCd" + i;
            model.departmentName = "departmentName" + i;
            model.importantLevel = "importantLevel" + i;
            model.telephoneNumber = "telephoneNumber" + i;
            model.userCd = "userCd" + i;
            model.userName = "userName" + i;
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(10);

        when(searchMapper.convert(pagingResult)).thenReturn(pagingList);

    }

    /**
     * 初期化を行うメソッドをテストします。
     */
    @Test
    public void testInitialize() {

        SearchInitializeResponseModel model = searchController.initialize();

        assertEquals(4, model.crmAccountTypes.size());
        assertEquals(null, model.crmAccountTypes.get(0).key);
        assertEquals("大", model.crmAccountTypes.get(1).key);
        assertEquals("中", model.crmAccountTypes.get(2).key);
        assertEquals("小", model.crmAccountTypes.get(3).key);
        assertEquals(6, model.importantLevelTypes.size());
        assertEquals(null, model.importantLevelTypes.get(0).key);
        assertEquals("5", model.importantLevelTypes.get(1).key);
        assertEquals("4", model.importantLevelTypes.get(2).key);
        assertEquals("3", model.importantLevelTypes.get(3).key);
        assertEquals("2", model.importantLevelTypes.get(4).key);
        assertEquals("1", model.importantLevelTypes.get(5).key);
        assertEquals(6, model.crmAccountStatusList.size());
        assertEquals(null, model.crmAccountStatusList.get(0).key);
        assertEquals("仮登録", model.crmAccountStatusList.get(1).key);
        assertEquals("見込み客", model.crmAccountStatusList.get(2).key);
        assertEquals("取引中", model.crmAccountStatusList.get(3).key);
        assertEquals("取引停止", model.crmAccountStatusList.get(4).key);
        assertEquals("重複末梢", model.crmAccountStatusList.get(5).key);
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

            assertEquals("address" + i, model.address);
            assertEquals(
                DateUtil.parse("2010071" + i, "yyyyMMdd"),
                model.businessStartDate);
            assertEquals("2010081" + i, model.contractStartDate);
            assertEquals("crmAccountCd" + i, model.crmAccountCd);
            assertEquals("crmAccountName" + i, model.crmAccountName);
            assertEquals("crmAccountNameKana" + i, model.crmAccountNameKana);
            assertEquals("crmAccountStatus" + i, model.crmAccountStatus);
            assertEquals("crmAccountType" + i, model.crmAccountType);
            assertEquals("deleteFlag" + i, model.deleteFlag);
            assertEquals("departmentCd" + i, model.departmentCd);
            assertEquals("departmentName" + i, model.departmentName);
            assertEquals("importantLevel" + i, model.importantLevel);
            assertEquals("telephoneNumber" + i, model.telephoneNumber);
            assertEquals("userCd" + i, model.userCd);
            assertEquals("userName" + i, model.userName);
        }
    }
}
