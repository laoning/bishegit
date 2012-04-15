/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.AccountContactEntryDialogMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.logic.AccountContactLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * アカウントコンタクト登録/更新ダイアログ検索画面のコントローラのテストです。
 */
@RunWith(BizIntegralTest.class)
public class AccountContactEntryDialogControllerTest {

    /**
     * テスト対象。
     */
    protected AccountContactEntryDialogController accountContactEntryDialogController;

    /**
     * {@link AccountContactEntryDialogMapper}
     */
    @MockitoComponent
    protected AccountContactEntryDialogMapper accountContactEntryDialogMapper;

    /**
     * {@link AccountContactLogic}
     */
    @MockitoComponent
    protected AccountContactLogic accountContactLogic;

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
     * {@link AccountContactEntryDialogInitializeRequestModel}
     */
    private AccountContactEntryDialogInitializeRequestModel request =
        new AccountContactEntryDialogInitializeRequestModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        CrmCcAccountContactDto dto = new CrmCcAccountContactDto();

        when(accountContactEntryDialogMapper.convert(request)).thenReturn(dto);

        CrmCcAccountContact crmCcAccountContact = new CrmCcAccountContact();

        when(accountContactLogic.getEntity(dto))
            .thenReturn(crmCcAccountContact);

        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();

        when(accountContactEntryDialogMapper.convertToCrmCcAccountDto(request))
            .thenReturn(crmCcAccountDto);

        CrmCcAccount crmCcAccount = new CrmCcAccount();

        when(crmAccountLogic.getEntity(crmCcAccountDto)).thenReturn(
            crmCcAccount);

        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();

        when(accountContactEntryDialogMapper.convertToCrmCcContactDto(request))
            .thenReturn(crmCcContactDto);

        CrmCcContact crmCcContact = new CrmCcContact();

        when(crmContactLogic.getEntity(crmCcContactDto)).thenReturn(
            crmCcContact);

        AccountContactEntryDialogInitializeResponseModel model =
            new AccountContactEntryDialogInitializeResponseModel();
        model.accountContactAddInfoShowFlag = "1";
        model.createDate = "2010/01/01";
        model.createUserCd = "createUserCd";
        model.crmAccountVersionNo = "2";
        model.crmContactVersionNo = "3";
        model.customField1 = "customField1";
        model.customField2 = "customField2";
        model.customField3 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField4 = "customField4";
        model.customField5 = "customField5";
        model.customField6 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField7 = "customField7";
        model.customField8 = "customField8";
        model.customField9 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField10 = "customField10";
        model.customField11 = "customField11";
        model.customField12 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField13 = "customField13";
        model.customField14 = "customField14";
        model.customField15 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField16 = "customField16";
        model.customField17 = "customField17";
        model.customField18 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField19 = "customField19";
        model.customField20 = "customField20";
        model.customField21 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField22 = "customField22";
        model.customField23 = "customField23";
        model.customField24 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField25 = "customField25";
        model.customField26 = "customField26";
        model.customField27 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField28 = "customField28";
        model.customField29 = "customField29";
        model.customField30 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField31 = "customField31";
        model.customField32 = "customField32";
        model.customField33 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField34 = "customField34";
        model.customField35 = "customField35";
        model.customField36 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField37 = "customField37";
        model.customField38 = "customField38";
        model.customField39 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField40 = "customField40";
        model.customField41 = "customField41";
        model.customField42 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField43 = "customField43";
        model.customField44 = "customField44";
        model.customField45 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField46 = "customField46";
        model.customField47 = "customField47";
        model.customField48 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField49 = "customField49";
        model.customField50 = "customField50";
        model.customField51 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField52 = "customField52";
        model.customField53 = "customField53";
        model.customField54 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField55 = "customField55";
        model.customField56 = "customField56";
        model.customField57 = DateUtil.parse("20100101", "yyyyMMdd");
        model.customField58 = "customField58";
        model.customField59 = "customField59";
        model.customField60 = DateUtil.parse("20100101", "yyyyMMdd");
        model.effectiveTermFrom = DateUtil.parse("20100101", "yyyyMMdd");
        model.effectiveTermTo = DateUtil.parse("20100101", "yyyyMMdd");
        model.notes = "notes";
        model.sortKey = "0";
        model.versionNo = "1";

        when(
            accountContactEntryDialogMapper.convert(
                crmCcAccountContact,
                crmCcAccount,
                crmCcContact)).thenReturn(model);
    }

    /**
     * 初期化のテスト。
     */
    @Test
    public void testInitialize() {

        AccountContactEntryDialogInitializeResponseModel model =
            accountContactEntryDialogController.initialize(request);

        assertEquals("1", model.accountContactAddInfoShowFlag);
        assertEquals("2010/01/01", model.createDate);
        assertEquals("createUserCd", model.createUserCd);
        assertEquals("2", model.crmAccountVersionNo);
        assertEquals("3", model.crmContactVersionNo);
        assertEquals("customField1", model.customField1);
        assertEquals("customField2", model.customField2);
        assertEquals(DateUtil.parse("20100101", "yyyyMMdd"), model.customField3);
        assertEquals("customField4", model.customField4);
        assertEquals("customField5", model.customField5);
        assertEquals(DateUtil.parse("20100101", "yyyyMMdd"), model.customField6);
        assertEquals("customField7", model.customField7);
        assertEquals("customField8", model.customField8);
        assertEquals(DateUtil.parse("20100101", "yyyyMMdd"), model.customField9);
        assertEquals("customField10", model.customField10);
        assertEquals("customField11", model.customField11);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField12);
        assertEquals("customField13", model.customField13);
        assertEquals("customField14", model.customField14);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField15);
        assertEquals("customField16", model.customField16);
        assertEquals("customField17", model.customField17);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField18);
        assertEquals("customField19", model.customField19);
        assertEquals("customField20", model.customField20);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField21);
        assertEquals("customField22", model.customField22);
        assertEquals("customField23", model.customField23);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField24);
        assertEquals("customField25", model.customField25);
        assertEquals("customField26", model.customField26);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField27);
        assertEquals("customField28", model.customField28);
        assertEquals("customField29", model.customField29);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField30);
        assertEquals("customField31", model.customField31);
        assertEquals("customField32", model.customField32);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField33);
        assertEquals("customField34", model.customField34);
        assertEquals("customField35", model.customField35);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField36);
        assertEquals("customField37", model.customField37);
        assertEquals("customField38", model.customField38);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField39);
        assertEquals("customField40", model.customField40);
        assertEquals("customField41", model.customField41);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField42);
        assertEquals("customField43", model.customField43);
        assertEquals("customField44", model.customField44);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField45);
        assertEquals("customField46", model.customField46);
        assertEquals("customField47", model.customField47);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField48);
        assertEquals("customField49", model.customField49);
        assertEquals("customField50", model.customField50);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField51);
        assertEquals("customField52", model.customField52);
        assertEquals("customField53", model.customField53);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField54);
        assertEquals("customField55", model.customField55);
        assertEquals("customField56", model.customField56);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField57);
        assertEquals("customField58", model.customField58);
        assertEquals("customField59", model.customField59);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.customField60);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.effectiveTermFrom);
        assertEquals(
            DateUtil.parse("20100101", "yyyyMMdd"),
            model.effectiveTermTo);
        assertEquals("notes", model.notes);
        assertEquals("0", model.sortKey);
        assertEquals("1", model.versionNo);
    }

    /**
     * 初期化のテスト。
     */
    @Test
    public void testUpdate() {

        AccountContactEntryDialogUpdateRequestModel request =
            new AccountContactEntryDialogUpdateRequestModel();

        request.accountContactAddInfoShowFlag = "1";

        accountContactEntryDialogController.update(request);

        assertEquals("1", request.accountContactAddInfoShowFlag);
    }
}
