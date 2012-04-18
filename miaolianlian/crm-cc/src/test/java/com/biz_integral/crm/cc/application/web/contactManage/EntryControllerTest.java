/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

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
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.crm.cc.domain.logic.AccountContactLogic;
import com.biz_integral.crm.cc.domain.logic.CampaignLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmChargeDepartmentListLogic;
import com.biz_integral.crm.cc.domain.logic.CrmChargeUserListLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactChargeDepartmentLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactChargeUserLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactCompetitionLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.logic.SalesActivityLogic;
import com.biz_integral.crm.cc.domain.types.ImportantLevelType;
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
 * コンタクト登録/更新画面のコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class EntryControllerTest {

    /**
     * テスト対象です
     */
    protected EntryController controller;

    /**
     * 区分に関する名称管理API
     */
    @MockitoComponent
    protected EnumNamesRegistry enumNamesRegistry;

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
     * CRMアカウントロジック
     */
    @MockitoComponent
    protected CrmAccountLogic crmAccountLogic;

    /**
     * CRMコンタクト担当者ロジック
     */
    @MockitoComponent
    protected CrmContactChargeUserLogic crmContactChargeUserLogic;

    /**
     * CRMコンタクト担当組織ロジック
     */
    @MockitoComponent
    protected CrmContactChargeDepartmentLogic crmContactChargeDepartmentLogic;

    /**
     * CRMコンタクト競合ロジック
     */
    @MockitoComponent
    protected CrmContactCompetitionLogic crmContactCompetitionLogic;

    /**
     * 営業活動ロジック
     */
    @MockitoComponent
    protected SalesActivityLogic salesActivityLogic;

    /**
     * キャンペーンのロジック
     */
    @MockitoComponent
    protected CampaignLogic campaignLogic;

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
     * CRMアカウントコンタクトロジック
     */
    @MockitoComponent
    protected AccountContactLogic accountContactLogic;

    /**
     * コンタクト登録/更新で利用するマッビー
     */
    @MockitoComponent
    protected EntryMapper entryMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent
    protected ContextContainer contextContainer;

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setLocaleID("ja");
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);
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
        when(parameterLogic.getEntity("CRMCC0002")).thenReturn("1");
        when(parameterLogic.getEntity("CRMCC0006")).thenReturn(UseType.USE);
        when(parameterLogic.getEntity("CRMCC0007")).thenReturn(UseType.USE);
        when(parameterLogic.getEntity("CRMCC0009")).thenReturn(UseType.USE);
    }

    /**
     * 初期化のテスト。
     */
    @Test
    public void testInitialize() {
        EntryInitializeResponseModel response = controller.initialize();
        assertEquals(6, response.importantLevelTypeList.size());
        assertEquals(null, response.importantLevelTypeList.get(0).key);
        assertEquals("5", response.importantLevelTypeList.get(1).key);
        assertEquals("4", response.importantLevelTypeList.get(2).key);
        assertEquals("3", response.importantLevelTypeList.get(3).key);
        assertEquals("2", response.importantLevelTypeList.get(4).key);
        assertEquals("1", response.importantLevelTypeList.get(5).key);
    }

    /**
     * 更新モード初期化のテスト。
     */
    @Test
    public void testUpdateInitialize() {
        EntryInitializeRequestModel request = new EntryInitializeRequestModel();
        request.processModeType = "update";

        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();

        when(entryMapper.convertToCrmCcContactDto(request)).thenReturn(
            crmCcContactDto);
        CrmCcContact crmCcContact = new CrmCcContact();
        when(crmContactLogic.getEntity(crmCcContactDto)).thenReturn(
            crmCcContact);
        CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto =
            new CrmCcContactChargeUserCriteriaDto();
        when(entryMapper.convertToCrmCcContactChargeUserCriteriaDto(request))
            .thenReturn(chargeUserCriteriaDto);

        List<CrmCcContactChargeUserResultDto> chargeUserDtoList =
            new ArrayList<CrmCcContactChargeUserResultDto>();
        CrmCcContactChargeUserResultDto chargeUserDto =
            new CrmCcContactChargeUserResultDto();
        chargeUserDtoList.add(chargeUserDto);
        when(crmContactChargeUserLogic.getEntityList(chargeUserCriteriaDto))
            .thenReturn(chargeUserDtoList);

        EntryContactChargeUserResponseModel chargeUserResponse =
            new EntryContactChargeUserResponseModel();
        chargeUserResponse.userCd = "0001";
        chargeUserResponse.userName = "name";
        when(entryMapper.convertToChargeUserResponseModel(chargeUserDto))
            .thenReturn(chargeUserResponse);
        CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto =
            new CrmCcContactChargeDeptCriteriaDto();
        when(entryMapper.convertToCrmCcContactChargeDeptCriteriaDto(request))
            .thenReturn(chargeDeptCriteriaDto);
        EntryInitializeResponseModel response =
            controller.updateInitialize(request);
        assertEquals(
            "0001",
            response.contactChargeUserResponseList.get(0).userCd);
        assertEquals(
            "name",
            response.contactChargeUserResponseList.get(0).userName);
    }

    /**
     * 保存のテスト。
     */
    @Test
    public void testSave() {
        EntrySaveRequestModel request = new EntrySaveRequestModel();
        request.crmContactCd = "0001";
        EntryCreateDeptRequestModel contactChargeDept =
            new EntryCreateDeptRequestModel();
        contactChargeDept.departmentCd = "1";
        List<EntryCreateDeptRequestModel> contactChargeDeptList =
            new ArrayList<EntryCreateDeptRequestModel>();
        contactChargeDeptList.add(contactChargeDept);
        request.contactChargeDeptList = contactChargeDeptList;

        List<EntryCreateUserRequestModel> contactChargeUserList =
            new ArrayList<EntryCreateUserRequestModel>();
        EntryCreateUserRequestModel contactChargeUser =
            new EntryCreateUserRequestModel();
        contactChargeUserList.add(contactChargeUser);
        request.contactChargeUserList = contactChargeUserList;
        CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto =
            new CrmCcContactChargeDeptCriteriaDto();
        when(
            entryMapper
                .convertToCrmCcContactChargeDeptCriteriaDto(contactChargeDept))
            .thenReturn(chargeDeptCriteriaDto);

        CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto =
            new CrmCcContactChargeUserCriteriaDto();
        when(
            entryMapper
                .convertToCrmCcContactChargeUserCriteriaDto(contactChargeUser))
            .thenReturn(chargeUserCriteriaDto);
        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();
        when(entryMapper.convertToCrmCcContactDto(request)).thenReturn(
            crmCcContactDto);
        CrmCcContact crmCcContact = new CrmCcContact();
        crmCcContact.setVersionNo(1L);
        crmCcContact.setTermCd("001");
        when(crmContactLogic.create(crmCcContactDto)).thenReturn(crmCcContact);
        EntrySaveResponseModel response = controller.save(request);
        assertEquals("1", response.versionNo);
        assertEquals("001", response.termCd);
    }

    /**
     * 競合一覧ページャーのテスト。
     */
    @Test
    public void testCompetitionFilter() {
        EntryCompetitionFilterRequestModel request =
            new EntryCompetitionFilterRequestModel();

        CrmCcContactCompetitionCriteriaDto dto =
            new CrmCcContactCompetitionCriteriaDto();
        when(entryMapper.convertToCrmCcContactCompetitionCriteriaDto(request))
            .thenReturn(dto);
        PagingResult<CrmCcContactCompetition> pagingResult =
            new PagingResult<CrmCcContactCompetition>();
        when(crmContactCompetitionLogic.getEntityList(dto)).thenReturn(
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
        List<EntryContactChargeDeptResponseModel> contactChargeDeptResponseList =
            new ArrayList<EntryContactChargeDeptResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryContactChargeDeptResponseModel model =
                new EntryContactChargeDeptResponseModel();
            contactChargeDeptResponseList.add(model);
        }
        when(entryMapper.convertToContactChargeDeptResponseModel(resultDto))
            .thenReturn(contactChargeDeptResponseList);
        EntrySetDepartmentListResponseModel response =
            controller.setDepartmentList(request);
        assertEquals(10, response.contactChargeDeptResponseList.size());
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
        List<EntryContactChargeUserResponseModel> contactChargeUserResponseList =
            new ArrayList<EntryContactChargeUserResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryContactChargeUserResponseModel model =
                new EntryContactChargeUserResponseModel();
            contactChargeUserResponseList.add(model);
        }
        when(entryMapper.convertToContactChargeUserResponseModel(resultDto))
            .thenReturn(contactChargeUserResponseList);
        EntrySetUserListResponseModel response =
            controller.setUserList(request);
        assertEquals(10, response.contactChargeUserResponseList.size());
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
        List<EntryContactChargeDeptResponseModel> contactChargeDeptResponseList =
            new ArrayList<EntryContactChargeDeptResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryContactChargeDeptResponseModel model =
                new EntryContactChargeDeptResponseModel();
            contactChargeDeptResponseList.add(model);
        }
        when(entryMapper.convertToContactChargeDeptResponseModel(resultDto))
            .thenReturn(contactChargeDeptResponseList);
        EntrySetMainChargeDepartmentResponseModel response =
            controller.setMainChargeDepartment(request);
        assertEquals(10, response.contactChargeDeptResponseList.size());
    }

    /**
     * 主選択セル(担当者)のテスト。
     */
    @Test
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
        List<EntryContactChargeUserResponseModel> contactChargeUserResponseList =
            new ArrayList<EntryContactChargeUserResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryContactChargeUserResponseModel model =
                new EntryContactChargeUserResponseModel();
            contactChargeUserResponseList.add(model);
        }
        when(entryMapper.convertToContactChargeUserResponseModel(resultDto))
            .thenReturn(contactChargeUserResponseList);
        EntrySetMainChargeUserResponseModel response =
            controller.setMainChargeUser(request);
        assertEquals(10, response.contactChargeUserResponseList.size());
    }

    /**
     * アカウント一覧ページャーのテスト。
     */
    @Test
    public void testAccountFilter() {

        EntryAccountFilterRequestModel request =
            new EntryAccountFilterRequestModel();
        PagingResult<EntryAccountFilterResponseModel> pagingList =
            new PagingResult<EntryAccountFilterResponseModel>();
        for (int i = 0; i < 10; i++) {
            EntryAccountFilterResponseModel response =
                new EntryAccountFilterResponseModel();
            pagingList.add(response);
        }
        CrmCcAccountContactCriteriaDto criteriadto =
            new CrmCcAccountContactCriteriaDto();
        when(entryMapper.convertToCrmCcAccountContactCriteriaDto(request))
            .thenReturn(criteriadto);
        PagingResult<CrmCcAccountContactResultDto> pagingResult =
            new PagingResult<CrmCcAccountContactResultDto>();
        when(accountContactLogic.getAccountEntityList(criteriadto)).thenReturn(
            pagingResult);
        when(entryMapper.convertToAccountFilterResponseModel(pagingResult))
            .thenReturn(pagingList);

        PagingResponse<EntryAccountFilterResponseModel> result =
            controller.accountFilter(request);
        assertEquals(10, result.getRows().size());
    }
}
