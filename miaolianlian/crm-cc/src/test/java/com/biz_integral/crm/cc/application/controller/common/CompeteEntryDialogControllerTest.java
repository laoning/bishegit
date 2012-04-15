/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.CompeteEntryDialogMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.crm.cc.domain.logic.CrmAccountCompetitionLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactCompetitionLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.crm.cc.domain.types.CompetitionType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 組織選択ダイアログコントローラのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class CompeteEntryDialogControllerTest {

    /**
     * テスト対象です
     */
    protected CompeteEntryDialogController competeEntryDialogController;

    /**
     * {@link EnumNamesRegistry 区分に関する名称管理}
     */
    @MockitoComponent
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link CompeteEntryDialogMapper 検索Mapper}
     */
    @MockitoComponent
    protected CompeteEntryDialogMapper competeEntryDialogMapper;

    /**
     * {@link CrmAccountCompetitionLogic}
     */
    @MockitoComponent
    protected CrmAccountCompetitionLogic crmAccountCompetitionLogic;

    /**
     * {@link CrmContactCompetitionLogic}
     */
    @MockitoComponent
    protected CrmContactCompetitionLogic crmContactCompetitionLogic;

    /**
     * {@link CrmAccountLogic}
     */
    @MockitoComponent
    protected CrmAccountLogic crmAccountLogic;

    /**
     * {@link CrmContactLogic}
     */
    @MockitoComponent
    protected CrmContactLogic crmContactLogic;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent
    protected ContextContainer contextContainer;

    /**
     * 処理対象（アカウント、コンタクト）を制御します。
     */
    protected static final String ACCOUNT = "account";
    protected static final String CONTACT = "contact";

    /**
     * 処理モード（登録、更新）を制御します。
     */
    protected static final String ENTRY = "entry";
    protected static final String UPDATE = "update";

    /**
     * {@link DepartmentDialogFilterRequestModel}
     */
    CompeteEntryDialogInitializeRequestModel request =
        new CompeteEntryDialogInitializeRequestModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setLocaleID("ja");
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);
        when(
            enumNamesRegistry.getName(CompetitionType.A, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("A");
        when(
            enumNamesRegistry.getName(CompetitionType.B, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("B");
        when(
            enumNamesRegistry.getName(CompetitionType.C, LocaleUtil
                .toLocale(contextContainer.getUserContext().getLocaleID())))
            .thenReturn("C");
    }

    /**
     * 初期化のテスト。
     */
    @Test
    public void testInitialize() {

        CompeteEntryDialogInitializeResponseModel model =
            competeEntryDialogController.initialize(request);
        assertEquals(4, model.competitionType.size());
        assertEquals(null, model.competitionType.get(0).key);
        assertEquals("A", model.competitionType.get(1).key);
        assertEquals("B", model.competitionType.get(2).key);
        assertEquals("C", model.competitionType.get(3).key);
    }

    /**
     * アカウント更新モード初期化のテスト。
     */
    @Test
    public void testUpdateAccountTInitialize() {
        CompeteEntryDialogInitializeResponseModel model =
            competeEntryDialogController.initialize(request);

        request.processMode = "UPDATE";
        request.processTarget = "ACCOUNT";
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();
        when(competeEntryDialogMapper.convertToCrmCcAccountDto(request))
            .thenReturn(crmCcAccountDto);
        CrmCcAccount crmCcAccount = new CrmCcAccount();
        when(crmAccountLogic.getEntity(crmCcAccountDto)).thenReturn(
            crmCcAccount);
        when(
            competeEntryDialogMapper.convertToCrmCcAccountDto(
                crmCcAccount,
                model)).thenReturn(model);

        CrmCcAccountCompetitionDto crmCcAccountCompetitionDto =
            new CrmCcAccountCompetitionDto();
        when(
            competeEntryDialogMapper
                .convertToCrmCcAccountCompetitionDto(request)).thenReturn(
            crmCcAccountCompetitionDto);
        CrmCcAccountCompetition crmCcAccountCompetition =
            new CrmCcAccountCompetition();
        when(crmAccountCompetitionLogic.getEntity(crmCcAccountCompetitionDto))
            .thenReturn(crmCcAccountCompetition);
        when(
            competeEntryDialogMapper.convertToCrmCcAccountCompetitionDto(
                crmCcAccountCompetition,
                model)).thenReturn(model);
    }

    /**
     * コンタクト更新モード初期化のテスト。
     */
    @Test
    public void testUpdateContactTInitialize() {
        CompeteEntryDialogInitializeResponseModel model =
            competeEntryDialogController.initialize(request);

        request.processMode = "UPDATE";
        request.processTarget = "CONTACT";
        CrmCcContactCompetitionDto crmCcContactCompetitionDto =
            new CrmCcContactCompetitionDto();
        when(
            competeEntryDialogMapper
                .convertToCrmCcContactCompetitionDto(request)).thenReturn(
            crmCcContactCompetitionDto);
        CrmCcContactCompetition crmCcContactCompetition =
            new CrmCcContactCompetition();
        when(crmContactCompetitionLogic.getEntity(crmCcContactCompetitionDto))
            .thenReturn(crmCcContactCompetition);
        when(
            competeEntryDialogMapper.convertToCrmCcContactCompetitionDto(
                crmCcContactCompetition,
                model)).thenReturn(model);
    }

    /**
     * コンタクト登録モード初期化のテスト。
     */
    @Test
    public void testEntryContactTInitialize() {
        CompeteEntryDialogInitializeResponseModel model =
            competeEntryDialogController.initialize(request);

        request.processMode = "ENTRY";
        request.processTarget = "CONTACT";
        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();
        when(competeEntryDialogMapper.convertToCrmCcContactDto(request))
            .thenReturn(crmCcContactDto);
        CrmCcContact crmCcContact = new CrmCcContact();
        when(crmContactLogic.getEntity(crmCcContactDto)).thenReturn(
            crmCcContact);
        when(
            competeEntryDialogMapper.convertToCrmCcContactDto(
                crmCcContact,
                model)).thenReturn(model);

    }

    /**
     * アカウント登録更新のテスト。
     */
    @Test
    public void testAccountCompetition() {
        CrmCcAccountCompetitionDto crmCcAccountCompetitionDto =
            new CrmCcAccountCompetitionDto();
        when(
            competeEntryDialogMapper
                .convertToCrmCcAccountCompetitionDto(request)).thenReturn(
            crmCcAccountCompetitionDto);
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();
        when(competeEntryDialogMapper.convertToCrmCcAccountDto(request))
            .thenReturn(crmCcAccountDto);

        crmAccountCompetitionLogic.create(
            crmCcAccountCompetitionDto,
            crmCcAccountDto);
        crmAccountCompetitionLogic.update(
            crmCcAccountCompetitionDto,
            crmCcAccountDto);
    }

    /**
     * コンタクト登録更新のテスト。
     */
    @Test
    public void testContactCompetition() {
        CrmCcContactCompetitionDto crmCcContactCompetitionDto =
            new CrmCcContactCompetitionDto();
        when(
            competeEntryDialogMapper
                .convertToCrmCcContactCompetitionDto(request)).thenReturn(
            crmCcContactCompetitionDto);

        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();
        when(competeEntryDialogMapper.convertToCrmCcContactDto(request))
            .thenReturn(crmCcContactDto);

        crmContactCompetitionLogic.create(
            crmCcContactCompetitionDto,
            crmCcContactDto);

        crmContactCompetitionLogic.update(
            crmCcContactCompetitionDto,
            crmCcContactDto);
    }
}
