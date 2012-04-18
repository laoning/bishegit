/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dto.CampaignCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CampaignResultDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeResultDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ProposalCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ProposalResultDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.crm.cc.domain.logic.AccountContactLogic;
import com.biz_integral.crm.cc.domain.logic.CampaignLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountChargeDepartmentLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountChargeUserLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountCompetitionLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmChargeDepartmentListLogic;
import com.biz_integral.crm.cc.domain.logic.CrmChargeUserListLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.crm.cc.domain.logic.ImCustomerLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.logic.ProposalLogic;
import com.biz_integral.crm.cc.domain.logic.SalesActivityLogic;
import com.biz_integral.crm.cc.domain.types.CrmAccountStatus;
import com.biz_integral.crm.cc.domain.types.UseType;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * アカウント登録/更新画面のコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class EntryControllerTest {

    /**
     * 区分に関する名称管理API
     */
    @MockitoComponent
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * CRMアカウントロジック
     */
    @MockitoComponent
    protected CrmAccountLogic crmAccountLogic;

    /**
     * CRMアカウント担当組織ロジック
     */
    @MockitoComponent
    protected CrmAccountChargeDepartmentLogic crmAccountChargeDepartmentLogic;

    /**
     * CRMアカウント担当者ロジック
     */
    @MockitoComponent
    protected CrmAccountChargeUserLogic crmAccountChargeUserLogic;

    /**
     * CRMアカウントコンタクトロジック
     */
    @MockitoComponent
    protected AccountContactLogic accountContactLogic;

    /**
     * CRMアカウント競合ロジック
     */
    @MockitoComponent
    protected CrmAccountCompetitionLogic crmAccountCompetitionLogic;

    /**
     * CRMアカウント案件のロジック
     */
    @MockitoComponent
    protected ProposalLogic proposalLogic;

    /**
     * CRMアカウントキャンペーンのロジック
     */
    @MockitoComponent
    protected CampaignLogic campaignLogic;

    /**
     * 営業活動ロジック
     */
    @MockitoComponent
    protected SalesActivityLogic salesActivityLogic;

    /**
     * CRM担当組織一覧のロジック
     */
    @MockitoComponent
    protected CrmChargeDepartmentListLogic crmChargeDepartmentListLogic;

    /**
     * CRM担当者一覧のロジック
     */
    @MockitoComponent
    protected CrmChargeUserListLogic crmChargeUserListLogic;

    /**
     * パラメータロジック
     */
    @MockitoComponent
    protected ParameterLogic parameterLogic;

    /**
     * CRMコンタクトロジック
     */
    @MockitoComponent
    protected CrmContactLogic crmContactLogic;

    /**
     * 取引先_共通用ロジック
     */
    @MockitoComponent
    protected ImCustomerLogic imCustomerLogic;

    /**
     * アカウント登録/更新で利用するマッビー
     */
    @MockitoComponent
    protected EntryMapper entryMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent
    protected ContextContainer contextContainer;

    /**
     * テスト対象です
     */
    protected EntryController controller;

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setLocaleID("ja");
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);
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
        when(parameterLogic.getEntity("CRMCC0002")).thenReturn("1");
        when(parameterLogic.getEntity("CRMCC0005")).thenReturn("1");
        when(parameterLogic.getEntity("CRMCC0006")).thenReturn(UseType.USE);
        when(parameterLogic.getEntity("CRMCC0007")).thenReturn(UseType.USE);
        when(parameterLogic.getEntity("CRMCC0008")).thenReturn(UseType.USE);

    }

    /**
     * 初期化のテスト。
     */
    @Test
    public void testInitialize() {

        EntryInitializeResponseModel response = controller.initialize();
        assertEquals(6, response.crmAccountStatusList.size());
        assertEquals(null, response.crmAccountStatusList.get(0).key);
        assertEquals("仮登録", response.crmAccountStatusList.get(1).key);
        assertEquals("見込み客", response.crmAccountStatusList.get(2).key);
        assertEquals("取引中", response.crmAccountStatusList.get(3).key);
        assertEquals("取引停止", response.crmAccountStatusList.get(4).key);
        assertEquals("重複末梢", response.crmAccountStatusList.get(5).key);
    }

    /**
     * 更新モード初期化のテスト。
     */
    @Test
    public void testUpdateInitialize() {
        EntryInitializeRequestModel request = new EntryInitializeRequestModel();
        request.processModeType = "update";

        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();

        when(entryMapper.convertToCrmCcAccountDto(request)).thenReturn(
            crmCcAccountDto);
        CrmCcAccount crmCcAccount = new CrmCcAccount();
        when(crmAccountLogic.getEntity(crmCcAccountDto)).thenReturn(
            crmCcAccount);
        CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto =
            new CrmCcAccountChargeUserCriteriaDto();
        when(entryMapper.convertToCrmCcAccountChargeUserCriteriaDto(request))
            .thenReturn(chargeUserCriteriaDto);

        List<CrmCcAccountChargeUserResultDto> chargeUserDtoList =
            new ArrayList<CrmCcAccountChargeUserResultDto>();
        CrmCcAccountChargeUserResultDto chargeUserDto =
            new CrmCcAccountChargeUserResultDto();
        chargeUserDtoList.add(chargeUserDto);
        when(crmAccountChargeUserLogic.getEntityList(chargeUserCriteriaDto))
            .thenReturn(chargeUserDtoList);

        EntryAccountChargeUserResponseModel chargeUserResponse =
            new EntryAccountChargeUserResponseModel();
        chargeUserResponse.userCd = "0001";
        chargeUserResponse.userName = "name";
        when(entryMapper.convertToChargeUserResponseModel(chargeUserDto))
            .thenReturn(chargeUserResponse);
        CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto =
            new CrmCcAccountChargeDeptCriteriaDto();
        when(entryMapper.convertToCrmCcAccountChargeDeptCriteriaDto(request))
            .thenReturn(chargeDeptCriteriaDto);
        EntryInitializeResponseModel response =
            controller.updateInitialize(request);
        assertEquals(
            "0001",
            response.accountChargeUserResponseList.get(0).userCd);
        assertEquals(
            "name",
            response.accountChargeUserResponseList.get(0).userName);
    }

    /**
     * 保存のテスト。
     */
    @Test
    public void testSave() {

        EntrySaveRequestModel request = new EntrySaveRequestModel();
        request.crmAccountCd = "0001";
        EntryCreateDeptRequestModel accountChargeDept =
            new EntryCreateDeptRequestModel();
        accountChargeDept.departmentCd = "1";
        List<EntryCreateDeptRequestModel> accountChargeDeptList =
            new ArrayList<EntryCreateDeptRequestModel>();
        accountChargeDeptList.add(accountChargeDept);
        request.accountChargeDeptList = accountChargeDeptList;

        List<EntryCreateUserRequestModel> accountChargeUserList =
            new ArrayList<EntryCreateUserRequestModel>();
        EntryCreateUserRequestModel accountChargeUser =
            new EntryCreateUserRequestModel();
        accountChargeUserList.add(accountChargeUser);
        request.accountChargeUserList = accountChargeUserList;
        CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto =
            new CrmCcAccountChargeDeptCriteriaDto();
        when(
            entryMapper
                .convertToCrmCcAccountChargeDeptCriteriaDto(accountChargeDept))
            .thenReturn(chargeDeptCriteriaDto);

        CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto =
            new CrmCcAccountChargeUserCriteriaDto();
        when(
            entryMapper
                .convertToCrmCcAccountChargeUserCriteriaDto(accountChargeUser))
            .thenReturn(chargeUserCriteriaDto);
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();
        when(entryMapper.convertToCrmCcAccountDto(request)).thenReturn(
            crmCcAccountDto);
        CrmCcAccount crmCcAccount = new CrmCcAccount();
        crmCcAccount.setVersionNo(1L);
        crmCcAccount.setTermCd("001");
        when(crmAccountLogic.create(crmCcAccountDto)).thenReturn(crmCcAccount);
        EntrySaveResponseModel response = controller.save(request);
        assertEquals("1", response.versionNo);
        assertEquals("001", response.termCd);
    }

    /**
     * コンタクト一覧ページャーのテスト。
     */
    @Test
    public void testContactFilter() {
        EntryContactFilterRequestModel request =
            new EntryContactFilterRequestModel();
        PagingResult<EntryContactFilterResponseModel> pagingList =
            new PagingResult<EntryContactFilterResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryContactFilterResponseModel response =
                new EntryContactFilterResponseModel();
            pagingList.add(response);
        }
        CrmCcAccountContactCriteriaDto criteriadto =
            new CrmCcAccountContactCriteriaDto();
        when(entryMapper.convertToCrmCcAccountContactCriteriaDto(request))
            .thenReturn(criteriadto);
        PagingResult<CrmCcAccountContactResultDto> pagingResult =
            new PagingResult<CrmCcAccountContactResultDto>();
        when(accountContactLogic.getContactEntityList(criteriadto)).thenReturn(
            pagingResult);
        when(entryMapper.convertToContactResponseModel(pagingResult))
            .thenReturn(pagingList);

        PagingResponse<EntryContactFilterResponseModel> result =
            controller.contactFilter(request);
        assertEquals(10, result.getRows().size());
    }

    /**
     * 競合一覧ページャーのテスト。
     */
    @Test
    public void testCompetitionFilter() {
        EntryCompetitionFilterRequestModel request =
            new EntryCompetitionFilterRequestModel();

        CrmCcAccountCompetitionDto dto = new CrmCcAccountCompetitionDto();
        when(entryMapper.convertToCrmCcAccountCompetitionDto(request))
            .thenReturn(dto);
        PagingResult<CrmCcAccountCompetition> pagingResult =
            new PagingResult<CrmCcAccountCompetition>();
        when(crmAccountCompetitionLogic.getEntityList(dto)).thenReturn(
            pagingResult);
        PagingResult<EntryCompetitionFilterResponseModel> pagingList =
            new PagingResult<EntryCompetitionFilterResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryCompetitionFilterResponseModel model =
                new EntryCompetitionFilterResponseModel();
            pagingList.add(model);
        }
        when(entryMapper.convertToCompetitionResponseModel(pagingResult))
            .thenReturn(pagingList);

        PagingResponse<EntryCompetitionFilterResponseModel> result =
            controller.competitionFilter(request);
        assertEquals(10, result.getRows().size());
    }

    /**
     * 活動一覧ページャーのテスト。
     */
    @Test
    public void testSalesActivityFilter() {

        EntrySalesActivityFilterRequestModel request =
            new EntrySalesActivityFilterRequestModel();

        SalesActivityCriteriaDto salesActivityCriteriaDto =
            new SalesActivityCriteriaDto();
        when(entryMapper.converToCrmSaSalesAct(request)).thenReturn(
            salesActivityCriteriaDto);
        PagingResult<SalesActivityResultDto> pagingResult =
            new PagingResult<SalesActivityResultDto>();
        when(salesActivityLogic.getEntityList(salesActivityCriteriaDto))
            .thenReturn(pagingResult);
        PagingResult<EntrySalesActivityFilterResponseModel> pagingList =
            new PagingResult<EntrySalesActivityFilterResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntrySalesActivityFilterResponseModel model =
                new EntrySalesActivityFilterResponseModel();
            pagingList.add(model);
        }
        when(entryMapper.convertSalesActivityResponseModel(pagingResult))
            .thenReturn(pagingList);

        PagingResponse<EntrySalesActivityFilterResponseModel> result =
            controller.salesActivityFilter(request);
        assertEquals(10, result.getRows().size());
    }

    /**
     * 案件一覧ページャーのテスト。
     */
    @Test
    public void testProposalFilter() {
        EntryProposalFilterRequestModel request =
            new EntryProposalFilterRequestModel();

        ProposalCriteriaDto dto = new ProposalCriteriaDto();
        when(entryMapper.convertToProposalCriteriaDto(request)).thenReturn(dto);
        PagingResult<ProposalResultDto> pagingResult =
            new PagingResult<ProposalResultDto>();
        when(proposalLogic.getEntityList(dto)).thenReturn(pagingResult);
        PagingResult<EntryProposalFilterResponseModel> pagingList =
            new PagingResult<EntryProposalFilterResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryProposalFilterResponseModel model =
                new EntryProposalFilterResponseModel();
            pagingList.add(model);
        }
        when(entryMapper.convertToProposalResponseModel(pagingResult))
            .thenReturn(pagingList);

        PagingResponse<EntryProposalFilterResponseModel> result =
            controller.proposalFilter(request);
        assertEquals(10, result.getRows().size());
    }

    /**
     * キャンペーン一覧ページャーのテスト。
     */
    @Test
    public void testCampaignFilter() {
        EntryCampaignFilterRequestModel request =
            new EntryCampaignFilterRequestModel();

        CampaignCriteriaDto dto = new CampaignCriteriaDto();
        when(entryMapper.convertToCampaignCriteriaDto(request)).thenReturn(dto);
        PagingResult<CampaignResultDto> pagingResult =
            new PagingResult<CampaignResultDto>();
        when(campaignLogic.getEntityList(dto)).thenReturn(pagingResult);
        PagingResult<EntryCampaignFilterResponseModel> pagingList =
            new PagingResult<EntryCampaignFilterResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryCampaignFilterResponseModel model =
                new EntryCampaignFilterResponseModel();
            pagingList.add(model);
        }
        when(entryMapper.convertToCampaignResponseModel(pagingResult))
            .thenReturn(pagingList);

        PagingResponse<EntryCampaignFilterResponseModel> result =
            controller.campaignFilter(request);
        assertEquals(10, result.getRows().size());
    }

    /**
     * 取引先選択のテスト。
     */
    public void testCustomerSelect() {
        EntryCustomerSelectRequestModel request =
            new EntryCustomerSelectRequestModel();
        CustomerSelectCriteriaDto dto = new CustomerSelectCriteriaDto();
        when(entryMapper.convertToCustomerSelectCriteriaDto(request))
            .thenReturn(dto);
        CrmCcCustomerCmn crmCcCustomerCmn = new CrmCcCustomerCmn();
        when(imCustomerLogic.getEntity(dto)).thenReturn(crmCcCustomerCmn);
        EntryCustomerSelectResponseModel model =
            new EntryCustomerSelectResponseModel();
        model.crmAccountName = "name";
        model.crmAccountSearchName = "searchname";
        model.crmAccountShortName = "shortname";
        when(entryMapper.convertToCustomerSelectResponseModel(crmCcCustomerCmn))
            .thenReturn(model);
        EntryCustomerSelectResponseModel result =
            controller.customerSelect(request);
        assertEquals("name", result.crmAccountName);
        assertEquals("searchname", result.crmAccountSearchName);
        assertEquals("shortname", result.crmAccountShortName);
    }

    /**
     * 担当組織設定のテスト。
     */
    @Test
    public void testSetDepartmentList() {
        EntrySetDepartmentListRequestModel request =
            new EntrySetDepartmentListRequestModel();
        List<ChargeDeptListChangeAfterDto> changeAfterList =
            new ArrayList<ChargeDeptListChangeAfterDto>();
        when(
            entryMapper
                .convertToChargeDeptListChangeAfterDto(request.fixChargeDeptList))
            .thenReturn(changeAfterList);
        List<ChargeDeptListChangeBeforeDto> changeBeforeList =
            new ArrayList<ChargeDeptListChangeBeforeDto>();
        when(
            entryMapper
                .convertToChargeDeptListChangeBeforeDto(request.gridChargeDeptList))
            .thenReturn(changeBeforeList);
        List<ChargeDeptListChangeResultDto> resultDto =
            new ArrayList<ChargeDeptListChangeResultDto>();
        when(
            crmChargeDepartmentListLogic.setDepartmentList(
                changeAfterList,
                changeBeforeList)).thenReturn(resultDto);
        List<EntryAccountChargeDeptResponseModel> accountChargeDeptResponseList =
            new ArrayList<EntryAccountChargeDeptResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryAccountChargeDeptResponseModel model =
                new EntryAccountChargeDeptResponseModel();
            accountChargeDeptResponseList.add(model);
        }
        when(entryMapper.convertToAccountChargeDeptResponseModel(resultDto))
            .thenReturn(accountChargeDeptResponseList);
        EntrySetDepartmentListResponseModel response =
            controller.setDepartmentList(request);
        assertEquals(10, response.accountChargeDeptResponseList.size());
    }

    /**
     * 主選択セル(組織)のテスト。
     */
    @Test
    public void testSetMainChargeDepartment() {
        EntrySetMainChargeDepartmentRequestModel request =
            new EntrySetMainChargeDepartmentRequestModel();
        List<ChargeDeptListChangeBeforeDto> changeBeforeList =
            new ArrayList<ChargeDeptListChangeBeforeDto>();
        when(
            entryMapper
                .convertToChargeDeptListChangeBeforeDto(request.gridChargeDeptList))
            .thenReturn(changeBeforeList);
        List<ChargeDeptListChangeResultDto> resultDto =
            new ArrayList<ChargeDeptListChangeResultDto>();
        when(
            crmChargeDepartmentListLogic.setMainChargeDepartment(
                request.selectedDepartmentCd,
                changeBeforeList)).thenReturn(resultDto);
        List<EntryAccountChargeDeptResponseModel> accountChargeDeptResponseList =
            new ArrayList<EntryAccountChargeDeptResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryAccountChargeDeptResponseModel model =
                new EntryAccountChargeDeptResponseModel();
            accountChargeDeptResponseList.add(model);
        }
        when(entryMapper.convertToAccountChargeDeptResponseModel(resultDto))
            .thenReturn(accountChargeDeptResponseList);
        EntrySetMainChargeDepartmentResponseModel response =
            controller.setMainChargeDepartment(request);
        assertEquals(10, response.accountChargeDeptResponseList.size());
    }

    /**
     * 担当者設定のテスト。
     */
    @Test
    public void testSetUserList() {
        EntrySetUserListRequestModel request =
            new EntrySetUserListRequestModel();
        List<ChargeUserListChangeAfterDto> changeAfterList =
            new ArrayList<ChargeUserListChangeAfterDto>();
        when(
            entryMapper
                .convertToChargeUserListChangeAfterDto(request.fixChargeUserList))
            .thenReturn(changeAfterList);
        List<ChargeUserListChangeBeforeDto> changeBeforeList =
            new ArrayList<ChargeUserListChangeBeforeDto>();
        when(
            entryMapper
                .convertToChargeUserListChangeBeforeDto(request.gridChargeUserList))
            .thenReturn(changeBeforeList);
        List<ChargeUserListChangeResultDto> resultDto =
            new ArrayList<ChargeUserListChangeResultDto>();
        when(
            crmChargeUserListLogic.setUserList(
                changeAfterList,
                changeBeforeList)).thenReturn(resultDto);
        List<EntryAccountChargeUserResponseModel> accountChargeUserResponseList =
            new ArrayList<EntryAccountChargeUserResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryAccountChargeUserResponseModel model =
                new EntryAccountChargeUserResponseModel();
            accountChargeUserResponseList.add(model);
        }
        when(entryMapper.convertToAccountChargeUserResponseModel(resultDto))
            .thenReturn(accountChargeUserResponseList);
        EntrySetUserListResponseModel response =
            controller.setUserList(request);
        assertEquals(10, response.accountChargeUserResponseList.size());
    }

    /**
     * 主選択セル(担当者)のテスト。
     */
    public void testSetMainChargeUser() {
        EntrySetMainChargeUserRequestModel request =
            new EntrySetMainChargeUserRequestModel();
        List<ChargeUserListChangeBeforeDto> changeBeforeList =
            new ArrayList<ChargeUserListChangeBeforeDto>();
        when(
            entryMapper
                .convertToChargeUserListChangeBeforeDto(request.gridChargeUserList))
            .thenReturn(changeBeforeList);
        List<ChargeUserListChangeResultDto> resultDto =
            new ArrayList<ChargeUserListChangeResultDto>();
        when(
            crmChargeUserListLogic.setMainChargeUser(
                request.selectedUserCd,
                changeBeforeList)).thenReturn(resultDto);
        List<EntryAccountChargeUserResponseModel> accountChargeUserResponseList =
            new ArrayList<EntryAccountChargeUserResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryAccountChargeUserResponseModel model =
                new EntryAccountChargeUserResponseModel();
            accountChargeUserResponseList.add(model);
        }
        when(entryMapper.convertToAccountChargeUserResponseModel(resultDto))
            .thenReturn(accountChargeUserResponseList);
        EntrySetMainChargeUserResponseModel response =
            controller.setMainChargeUser(request);
        assertEquals(10, response.accountChargeUserResponseList.size());
    }
}
