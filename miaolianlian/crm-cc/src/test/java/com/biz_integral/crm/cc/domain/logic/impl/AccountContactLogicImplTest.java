/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountContactDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcContactDao;
import com.biz_integral.crm.cc.domain.dto.AccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCheckItemDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountContact;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.FeatureContextImpl;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.service.domain.master.BizConfigurationProvider;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * AccountContactLogicImplTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class AccountContactLogicImplTest {

    /**
     * {@link bizConfigurationProvider}
     */
    @MockitoComponent()
    protected BizConfigurationProvider bizConfigurationProvider;

    /**
     * コンテキストコンテナ。
     */
    @MockitoComponent()
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}です
     */
    @MockitoComponent()
    protected BeanMapper beanMapper;

    /**
     * CrmAccountLogicロジック
     */
    @MockitoComponent()
    protected CrmAccountLogic crmAccountLogic;

    /**
     * CRMコンタクトロジックです 。
     */
    @MockitoComponent()
    protected CrmContactLogic crmContactLogic;

    /**
     * CRMアカウントに関するDAO
     */
    @MockitoComponent()
    protected CrmCcAccountDao crmCcAccountDao;

    /**
     * CRMコンタクトに関するDAO
     */
    @MockitoComponent()
    protected CrmCcContactDao crmCcContactDao;

    /**
     * {@link AccountContactLogicImpl}
     */
    private AccountContactLogicImpl accountContactLogicImpl;

    /**
     * {@link crmCcAccountContactDao}
     */
    @MockitoComponent(componentName = "crmCcAccountContactDao")
    protected CrmCcAccountContactDao crmCcAccountContactDao;

    /**
     * {@link UniqueIdGenerator}
     */
    @MockitoComponent
    protected UniqueIdGenerator uniqueIdGenerator;

    /**
     * {@link CrmCcAccountContactDto}
     */
    CrmCcAccountContactDto dto = new CrmCcAccountContactDto();

    /**
     * {@link CrmCcAccountContact}
     */
    CrmCcAccountContact crmCcAccountContactInsert = new CrmCcAccountContact();

    /**
     * {@link CrmCcAccountContact}
     */
    CrmCcAccountContact crmCcAccountContactUpdate = new CrmCcAccountContact();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @SuppressWarnings("static-access")
    @PostBindFields
    public void postBindFields() {

        CrmCcAccountContact result = new CrmCcAccountContact();

        result.setCompanyCd("0001");
        result.setCrmAccountCd("1111");
        result.setCrmContactCd("2222");
        result.setTermCd("3333");
        result.setStartDate(DateUtil.parse("20100101", "yyyyMMdd"));
        result.setEndDate(DateUtil.parse("20100103", "yyyyMMdd"));
        result.setNotes("Notes");
        result.setCustomField1("1");
        result.setCustomField2("2");
        result.setCustomField3("3");
        result.setCustomField4("4");
        result.setCustomField5("5");
        result.setCustomField6("6");
        result.setCustomField7("7");
        result.setCustomField8("8");
        result.setCustomField9("9");
        result.setCustomField10("10");
        result.setCustomField11("11");
        result.setCustomField12("12");
        result.setCustomField13("13");
        result.setCustomField14("14");
        result.setCustomField15("15");
        result.setCustomField16("16");
        result.setCustomField17("17");
        result.setCustomField18("18");
        result.setCustomField19("19");
        result.setCustomField20("20");
        result.setCustomField21("21");
        result.setCustomField22("22");
        result.setCustomField23("23");
        result.setCustomField24("24");
        result.setCustomField25("25");
        result.setCustomField26("26");
        result.setCustomField27("27");
        result.setCustomField28("28");
        result.setCustomField29("29");
        result.setCustomField30("30");
        result.setCustomField31("31");
        result.setCustomField32("32");
        result.setCustomField33("33");
        result.setCustomField34("34");
        result.setCustomField35("35");
        result.setCustomField36("36");
        result.setCustomField37("37");
        result.setCustomField38("38");
        result.setCustomField39("39");
        result.setCustomField40("40");
        result.setCustomField41("41");
        result.setCustomField42("42");
        result.setCustomField43("43");
        result.setCustomField44("44");
        result.setCustomField45("45");
        result.setCustomField46("46");
        result.setCustomField47("47");
        result.setCustomField48("48");
        result.setCustomField49("49");
        result.setCustomField50("50");
        result.setCustomField51("51");
        result.setCustomField52("52");
        result.setCustomField53("53");
        result.setCustomField54("54");
        result.setCustomField55("55");
        result.setCustomField56("56");
        result.setCustomField57("57");
        result.setCustomField58("58");
        result.setCustomField59("59");
        result.setCustomField60("60");
        result.setDeleteFlag(false);
        result.setSortKey(1L);
        result.setVersionNo(0L);
        result.setCreateUserCd("xyz");
        result.setCreateDate(DateUtil.parse("20100901", "yyyyMMdd"));
        result.setRecordUserCd("zyx");
        result.setRecordDate(DateUtil.parse("20100902", "yyyyMMdd"));

        when(crmCcAccountContactDao.getEntity(dto)).thenReturn(result);
        when(bizConfigurationProvider.getStartDate()).thenReturn(
            DateUtil.parse("19000101", "yyyyMMdd"));
        when(bizConfigurationProvider.getEndDate()).thenReturn(
            DateUtil.parse("30000101", "yyyyMMdd"));

        crmCcAccountContactInsert.setCompanyCd("0001");
        crmCcAccountContactInsert.setCrmAccountCd("1111");
        crmCcAccountContactInsert.setCrmContactCd("8888");
        crmCcAccountContactInsert.setTermCd("3333");
        crmCcAccountContactInsert.setStartDate(DateUtil.parse(
            "20100101",
            "yyyyMMdd"));
        crmCcAccountContactInsert.setEndDate(DateUtil.parse(
            "20100102",
            "yyyyMMdd"));
        crmCcAccountContactInsert.setNotes("Notes");
        crmCcAccountContactInsert.setCustomField1("1");
        crmCcAccountContactInsert.setCustomField2("2");
        crmCcAccountContactInsert.setCustomField3("3");
        crmCcAccountContactInsert.setCustomField4("4");
        crmCcAccountContactInsert.setCustomField5("5");
        crmCcAccountContactInsert.setCustomField6("6");
        crmCcAccountContactInsert.setCustomField7("7");
        crmCcAccountContactInsert.setCustomField8("8");
        crmCcAccountContactInsert.setCustomField9("9");
        crmCcAccountContactInsert.setCustomField10("10");
        crmCcAccountContactInsert.setCustomField11("11");
        crmCcAccountContactInsert.setCustomField12("12");
        crmCcAccountContactInsert.setCustomField13("13");
        crmCcAccountContactInsert.setCustomField14("14");
        crmCcAccountContactInsert.setCustomField15("15");
        crmCcAccountContactInsert.setCustomField16("16");
        crmCcAccountContactInsert.setCustomField17("17");
        crmCcAccountContactInsert.setCustomField18("18");
        crmCcAccountContactInsert.setCustomField19("19");
        crmCcAccountContactInsert.setCustomField20("20");
        crmCcAccountContactInsert.setCustomField21("21");
        crmCcAccountContactInsert.setCustomField22("22");
        crmCcAccountContactInsert.setCustomField23("23");
        crmCcAccountContactInsert.setCustomField24("24");
        crmCcAccountContactInsert.setCustomField25("25");
        crmCcAccountContactInsert.setCustomField26("26");
        crmCcAccountContactInsert.setCustomField27("27");
        crmCcAccountContactInsert.setCustomField28("28");
        crmCcAccountContactInsert.setCustomField29("29");
        crmCcAccountContactInsert.setCustomField30("30");
        crmCcAccountContactInsert.setCustomField31("31");
        crmCcAccountContactInsert.setCustomField32("32");
        crmCcAccountContactInsert.setCustomField33("33");
        crmCcAccountContactInsert.setCustomField34("34");
        crmCcAccountContactInsert.setCustomField35("35");
        crmCcAccountContactInsert.setCustomField36("36");
        crmCcAccountContactInsert.setCustomField37("37");
        crmCcAccountContactInsert.setCustomField38("38");
        crmCcAccountContactInsert.setCustomField39("39");
        crmCcAccountContactInsert.setCustomField40("40");
        crmCcAccountContactInsert.setCustomField41("41");
        crmCcAccountContactInsert.setCustomField42("42");
        crmCcAccountContactInsert.setCustomField43("43");
        crmCcAccountContactInsert.setCustomField44("44");
        crmCcAccountContactInsert.setCustomField45("45");
        crmCcAccountContactInsert.setCustomField46("46");
        crmCcAccountContactInsert.setCustomField47("47");
        crmCcAccountContactInsert.setCustomField48("48");
        crmCcAccountContactInsert.setCustomField49("49");
        crmCcAccountContactInsert.setCustomField50("50");
        crmCcAccountContactInsert.setCustomField51("51");
        crmCcAccountContactInsert.setCustomField52("52");
        crmCcAccountContactInsert.setCustomField53("53");
        crmCcAccountContactInsert.setCustomField54("54");
        crmCcAccountContactInsert.setCustomField55("55");
        crmCcAccountContactInsert.setCustomField56("56");
        crmCcAccountContactInsert.setCustomField57("57");
        crmCcAccountContactInsert.setCustomField58("58");
        crmCcAccountContactInsert.setCustomField59("59");
        crmCcAccountContactInsert.setCustomField60("60");
        crmCcAccountContactInsert.setDeleteFlag(false);
        crmCcAccountContactInsert.setSortKey(1L);
        crmCcAccountContactInsert.setVersionNo(0L);
        crmCcAccountContactInsert.setCreateUserCd("xyz");
        crmCcAccountContactInsert.setCreateDate(DateUtil.parse(
            "20100901",
            "yyyyMMdd"));
        crmCcAccountContactInsert.setRecordUserCd("zyx");
        crmCcAccountContactInsert.setRecordDate(DateUtil.parse(
            "20100902",
            "yyyyMMdd"));

        when(
            beanMapper
                .map(crmCcAccountContactInsert, CrmCcAccountContact.class))
            .thenReturn(crmCcAccountContactInsert);

        crmCcAccountContactUpdate.setCompanyCd("0001");
        crmCcAccountContactUpdate.setCrmAccountCd("1111");
        crmCcAccountContactUpdate.setCrmContactCd("4444");
        crmCcAccountContactUpdate.setTermCd("3333");
        crmCcAccountContactUpdate.setStartDate(DateUtil.parse(
            "20100101",
            "yyyyMMdd"));
        crmCcAccountContactUpdate.setEndDate(DateUtil.parse(
            "20100102",
            "yyyyMMdd"));
        crmCcAccountContactUpdate.setNotes("Notes");
        crmCcAccountContactUpdate.setCustomField1("1");
        crmCcAccountContactUpdate.setCustomField2("2");
        crmCcAccountContactUpdate.setCustomField3("3");
        crmCcAccountContactUpdate.setCustomField4("4");
        crmCcAccountContactUpdate.setCustomField5("5");
        crmCcAccountContactUpdate.setCustomField6("6");
        crmCcAccountContactUpdate.setCustomField7("7");
        crmCcAccountContactUpdate.setCustomField8("8");
        crmCcAccountContactUpdate.setCustomField9("9");
        crmCcAccountContactUpdate.setCustomField10("10");
        crmCcAccountContactUpdate.setCustomField11("11");
        crmCcAccountContactUpdate.setCustomField12("12");
        crmCcAccountContactUpdate.setCustomField13("13");
        crmCcAccountContactUpdate.setCustomField14("14");
        crmCcAccountContactUpdate.setCustomField15("15");
        crmCcAccountContactUpdate.setCustomField16("16");
        crmCcAccountContactUpdate.setCustomField17("17");
        crmCcAccountContactUpdate.setCustomField18("18");
        crmCcAccountContactUpdate.setCustomField19("19");
        crmCcAccountContactUpdate.setCustomField20("20");
        crmCcAccountContactUpdate.setCustomField21("21");
        crmCcAccountContactUpdate.setCustomField22("22");
        crmCcAccountContactUpdate.setCustomField23("23");
        crmCcAccountContactUpdate.setCustomField24("24");
        crmCcAccountContactUpdate.setCustomField25("25");
        crmCcAccountContactUpdate.setCustomField26("26");
        crmCcAccountContactUpdate.setCustomField27("27");
        crmCcAccountContactUpdate.setCustomField28("28");
        crmCcAccountContactUpdate.setCustomField29("29");
        crmCcAccountContactUpdate.setCustomField30("30");
        crmCcAccountContactUpdate.setCustomField31("31");
        crmCcAccountContactUpdate.setCustomField32("32");
        crmCcAccountContactUpdate.setCustomField33("33");
        crmCcAccountContactUpdate.setCustomField34("34");
        crmCcAccountContactUpdate.setCustomField35("35");
        crmCcAccountContactUpdate.setCustomField36("36");
        crmCcAccountContactUpdate.setCustomField37("37");
        crmCcAccountContactUpdate.setCustomField38("38");
        crmCcAccountContactUpdate.setCustomField39("39");
        crmCcAccountContactUpdate.setCustomField40("40");
        crmCcAccountContactUpdate.setCustomField41("41");
        crmCcAccountContactUpdate.setCustomField42("42");
        crmCcAccountContactUpdate.setCustomField43("43");
        crmCcAccountContactUpdate.setCustomField44("44");
        crmCcAccountContactUpdate.setCustomField45("45");
        crmCcAccountContactUpdate.setCustomField46("46");
        crmCcAccountContactUpdate.setCustomField47("47");
        crmCcAccountContactUpdate.setCustomField48("48");
        crmCcAccountContactUpdate.setCustomField49("49");
        crmCcAccountContactUpdate.setCustomField50("50");
        crmCcAccountContactUpdate.setCustomField51("51");
        crmCcAccountContactUpdate.setCustomField52("52");
        crmCcAccountContactUpdate.setCustomField53("53");
        crmCcAccountContactUpdate.setCustomField54("54");
        crmCcAccountContactUpdate.setCustomField55("55");
        crmCcAccountContactUpdate.setCustomField56("56");
        crmCcAccountContactUpdate.setCustomField57("57");
        crmCcAccountContactUpdate.setCustomField58("58");
        crmCcAccountContactUpdate.setCustomField59("59");
        crmCcAccountContactUpdate.setCustomField60("60");
        crmCcAccountContactUpdate.setDeleteFlag(false);
        crmCcAccountContactUpdate.setSortKey(1L);
        crmCcAccountContactUpdate.setVersionNo(0L);
        crmCcAccountContactUpdate.setCreateUserCd("xyz");
        crmCcAccountContactUpdate.setCreateDate(DateUtil.parse(
            "20100901",
            "yyyyMMdd"));
        crmCcAccountContactUpdate.setRecordUserCd("zyx");
        crmCcAccountContactUpdate.setRecordDate(DateUtil.parse(
            "20100902",
            "yyyyMMdd"));

        when(
            beanMapper
                .map(crmCcAccountContactUpdate, CrmCcAccountContact.class))
            .thenReturn(crmCcAccountContactUpdate);

        FeatureContextImpl featureContextImpl = new FeatureContextImpl();
        featureContextImpl.setCompanyCode("0001");
        when(contextContainer.getFeatureContext()).thenReturn(
            featureContextImpl);

        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setUserID("0001");
        userContextImpl.setLocaleID("ja");
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);
        when(uniqueIdGenerator.getInstance().create()).thenReturn("0001");
    }

    /**
     * アカウントコンタクト登録/更新ダイアログ一件を取得するのテスト。
     */
    @Test
    public void testGetEntity() {

        dto.crmAccountCd = "";
        dto.crmContactCd = "";
        dto.startDate = DateUtil.nowDate();
        dto.endDate = DateUtil.nowDate();

        CrmCcAccountContact result = accountContactLogicImpl.getEntity(dto);

        // メソッド呼び出しの確認
        verify(crmCcAccountContactDao).getEntity(dto);

        assertEquals("0001", result.getCompanyCd());
        assertEquals("1111", result.getCrmAccountCd());
        assertEquals("2222", result.getCrmContactCd());
        assertEquals("3333", result.getTermCd());
        assertEquals(DateUtil.parse("20100101", "yyyyMMdd"), result
            .getStartDate());
        assertEquals(DateUtil.parse("20100102", "yyyyMMdd"), result
            .getEndDate());
        assertEquals("Notes", result.getNotes());
        assertEquals("1", result.getCustomField1());
        assertEquals("2", result.getCustomField2());
        assertEquals("3", result.getCustomField3());
        assertEquals("4", result.getCustomField4());
        assertEquals("5", result.getCustomField5());
        assertEquals("6", result.getCustomField6());
        assertEquals("7", result.getCustomField7());
        assertEquals("8", result.getCustomField8());
        assertEquals("9", result.getCustomField9());
        assertEquals("10", result.getCustomField10());
        assertEquals("11", result.getCustomField11());
        assertEquals("12", result.getCustomField12());
        assertEquals("13", result.getCustomField13());
        assertEquals("14", result.getCustomField14());
        assertEquals("15", result.getCustomField15());
        assertEquals("16", result.getCustomField16());
        assertEquals("17", result.getCustomField17());
        assertEquals("18", result.getCustomField18());
        assertEquals("19", result.getCustomField19());
        assertEquals("20", result.getCustomField20());
        assertEquals("21", result.getCustomField21());
        assertEquals("22", result.getCustomField22());
        assertEquals("23", result.getCustomField23());
        assertEquals("24", result.getCustomField24());
        assertEquals("25", result.getCustomField25());
        assertEquals("26", result.getCustomField26());
        assertEquals("27", result.getCustomField27());
        assertEquals("28", result.getCustomField28());
        assertEquals("29", result.getCustomField29());
        assertEquals("30", result.getCustomField30());
        assertEquals("31", result.getCustomField31());
        assertEquals("32", result.getCustomField32());
        assertEquals("33", result.getCustomField33());
        assertEquals("34", result.getCustomField34());
        assertEquals("35", result.getCustomField35());
        assertEquals("36", result.getCustomField36());
        assertEquals("37", result.getCustomField37());
        assertEquals("38", result.getCustomField38());
        assertEquals("39", result.getCustomField39());
        assertEquals("40", result.getCustomField40());
        assertEquals("41", result.getCustomField41());
        assertEquals("42", result.getCustomField42());
        assertEquals("43", result.getCustomField43());
        assertEquals("44", result.getCustomField44());
        assertEquals("45", result.getCustomField45());
        assertEquals("46", result.getCustomField46());
        assertEquals("47", result.getCustomField47());
        assertEquals("48", result.getCustomField48());
        assertEquals("49", result.getCustomField49());
        assertEquals("50", result.getCustomField50());
        assertEquals("51", result.getCustomField51());
        assertEquals("52", result.getCustomField52());
        assertEquals("53", result.getCustomField53());
        assertEquals("54", result.getCustomField54());
        assertEquals("55", result.getCustomField55());
        assertEquals("56", result.getCustomField56());
        assertEquals("57", result.getCustomField57());
        assertEquals("58", result.getCustomField58());
        assertEquals("59", result.getCustomField59());
        assertEquals("60", result.getCustomField60());
        assertEquals(false, result.isDeleteFlag());
        assertEquals(1, result.getSortKey().intValue());
        assertEquals(0, result.getVersionNo().intValue());
        assertEquals("xyz", result.getCreateUserCd());
        assertEquals(DateUtil.parse("20100901", "yyyyMMdd"), result
            .getCreateDate());
        assertEquals("zyx", result.getRecordUserCd());
        assertEquals(DateUtil.parse("20100902", "yyyyMMdd"), result
            .getRecordDate());
    }

    /**
     * 期間化更新判定をテストします。
     */
    @Test
    public void testTermUpdateCheck() {

        CrmCcAccountContactCheckItemDto itemDto =
            new CrmCcAccountContactCheckItemDto();

        itemDto.checkStartDate = DateUtil.parse("20100101", "yyyyMMdd");
        itemDto.checkEndDate = DateUtil.parse("20100102", "yyyyMMdd");
        itemDto.crmAccountVersionNo = "2";
        itemDto.crmContactVersionNo = "3";
        itemDto.setCompanyCd("0001");
        itemDto.setCrmAccountCd("1111");
        itemDto.setCrmContactCd("2222");
        itemDto.setTermCd("3333");
        itemDto.setStartDate(DateUtil.parse("20100101", "yyyyMMdd"));
        itemDto.setEndDate(DateUtil.parse("20100102", "yyyyMMdd"));
        itemDto.setNotes("Notes");
        itemDto.setCustomField1("1");
        itemDto.setCustomField2("2");
        itemDto.setCustomField3("3");
        itemDto.setCustomField4("4");
        itemDto.setCustomField5("5");
        itemDto.setCustomField6("6");
        itemDto.setCustomField7("7");
        itemDto.setCustomField8("8");
        itemDto.setCustomField9("9");
        itemDto.setCustomField10("10");
        itemDto.setCustomField11("11");
        itemDto.setCustomField12("12");
        itemDto.setCustomField13("13");
        itemDto.setCustomField14("14");
        itemDto.setCustomField15("15");
        itemDto.setCustomField16("16");
        itemDto.setCustomField17("17");
        itemDto.setCustomField18("18");
        itemDto.setCustomField19("19");
        itemDto.setCustomField20("20");
        itemDto.setCustomField21("21");
        itemDto.setCustomField22("22");
        itemDto.setCustomField23("23");
        itemDto.setCustomField24("24");
        itemDto.setCustomField25("25");
        itemDto.setCustomField26("26");
        itemDto.setCustomField27("27");
        itemDto.setCustomField28("28");
        itemDto.setCustomField29("29");
        itemDto.setCustomField30("30");
        itemDto.setCustomField31("31");
        itemDto.setCustomField32("32");
        itemDto.setCustomField33("33");
        itemDto.setCustomField34("34");
        itemDto.setCustomField35("35");
        itemDto.setCustomField36("36");
        itemDto.setCustomField37("37");
        itemDto.setCustomField38("38");
        itemDto.setCustomField39("39");
        itemDto.setCustomField40("40");
        itemDto.setCustomField41("41");
        itemDto.setCustomField42("42");
        itemDto.setCustomField43("43");
        itemDto.setCustomField44("44");
        itemDto.setCustomField45("45");
        itemDto.setCustomField46("46");
        itemDto.setCustomField47("47");
        itemDto.setCustomField48("48");
        itemDto.setCustomField49("49");
        itemDto.setCustomField50("50");
        itemDto.setCustomField51("51");
        itemDto.setCustomField52("52");
        itemDto.setCustomField53("53");
        itemDto.setCustomField54("54");
        itemDto.setCustomField55("55");
        itemDto.setCustomField56("56");
        itemDto.setCustomField57("57");
        itemDto.setCustomField58("58");
        itemDto.setCustomField59("59");
        itemDto.setCustomField60("60");
        itemDto.setDeleteFlag(false);
        itemDto.setSortKey(1L);
        itemDto.setVersionNo(0L);
        itemDto.setCreateUserCd("xyz");
        itemDto.setCreateDate(DateUtil.parse("20100901", "yyyyMMdd"));
        itemDto.setRecordUserCd("zyx");
        itemDto.setRecordDate(DateUtil.parse("20100902", "yyyyMMdd"));
    }

    /**
     * 期間化更新をテストします。
     */
    @Test
    public void testTermUpdate() {

        accountContactLogicImpl.termUpdate(crmCcAccountContactUpdate);
    }

    /**
     * 期間化登録をテストします。
     */
    @Test
    public void testTermCreate() {

        accountContactLogicImpl.termCreate(crmCcAccountContactInsert);
    }

    /**
     * コンタクト一覧取得のcrmAccountCdチェックテストします。
     */
    @Test
    public void testGetContactEntityList_IllegalArgumentException() {

        CrmCcAccountContactCriteriaDto criteriadto =
            new CrmCcAccountContactCriteriaDto();
        try {
            accountContactLogicImpl.getContactEntityList(criteriadto);
            fail();

        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * コンタクト一覧取得のテストします。
     */
    @Test
    public void testGetContactEntityList() {
        CrmCcAccountContactCriteriaDto criteriadto =
            new CrmCcAccountContactCriteriaDto();
        criteriadto.crmAccountCd = "0001";
        PagingResult<CrmCcAccountContactResultDto> resultList =
            new PagingResult<CrmCcAccountContactResultDto>();
        CrmCcAccountContactResultDto result =
            new CrmCcAccountContactResultDto();
        resultList.add(result);
        when(crmCcAccountContactDao.getContactEntityList(criteriadto))
            .thenReturn(resultList);
        PagingResult<CrmCcAccountContactResultDto> resultDtoList =
            accountContactLogicImpl.getContactEntityList(criteriadto);
        verify(crmCcAccountContactDao).getContactEntityList(criteriadto);
        assertEquals(1, resultDtoList.size());
    }

    /**
     * アカウント一覧取得のcrmContactCdチェックテストします。
     */
    @Test
    public void testGetAccountEntityList_IllegalArgumentException() {

        CrmCcAccountContactCriteriaDto criteriadto =
            new CrmCcAccountContactCriteriaDto();
        try {
            accountContactLogicImpl.getAccountEntityList(criteriadto);
            fail();

        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * アカウント一覧取得のテストします。
     */
    @Test
    public void testGetAccountEntityList() {
        CrmCcAccountContactCriteriaDto criteriadto =
            new CrmCcAccountContactCriteriaDto();
        criteriadto.crmContactCd = "0001";
        PagingResult<CrmCcAccountContactResultDto> resultList =
            new PagingResult<CrmCcAccountContactResultDto>();
        CrmCcAccountContactResultDto result =
            new CrmCcAccountContactResultDto();
        resultList.add(result);
        when(crmCcAccountContactDao.getAccountEntityList(criteriadto))
            .thenReturn(resultList);
        PagingResult<CrmCcAccountContactResultDto> resultDtoList =
            accountContactLogicImpl.getAccountEntityList(criteriadto);
        verify(crmCcAccountContactDao).getAccountEntityList(criteriadto);
        assertEquals(1, resultDtoList.size());
    }

    /**
     * コンタクト登録ValidationExceptionのテストします。
     */
    @Test
    public void testCreateContact_ValidationException() {
        List<AccountContactCriteriaDto> accountContactCriteriaDtoList =
            new ArrayList<AccountContactCriteriaDto>();
        String crmDomainCd = "crmDomainCd";
        try {
            accountContactLogicImpl.createContact(
                accountContactCriteriaDtoList,
                crmDomainCd);
            fail();

        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    /**
     * コンタクト登録のテストします。
     */
    @Test
    public void testCreateContact() {
        List<AccountContactCriteriaDto> accountContactCriteriaDtoList =
            new ArrayList<AccountContactCriteriaDto>();
        AccountContactCriteriaDto criteriaDto = new AccountContactCriteriaDto();
        criteriaDto.crmAccountCd = "0001";
        accountContactCriteriaDtoList.add(criteriaDto);
        String crmDomainCd = "crmDomainCd";
        List<CrmCcAccountContact> resultList =
            new ArrayList<CrmCcAccountContact>();
        CrmCcAccountContact entity = new CrmCcAccountContact();
        entity.setDeleteFlag(false);
        resultList.add(entity);
        when(crmCcAccountContactDao.getCreateEntityList(criteriaDto))
            .thenReturn(resultList);
        try {
            accountContactLogicImpl.createContact(
                accountContactCriteriaDtoList,
                crmDomainCd);
            fail();

        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    /**
     * アカウント登録ValidationExceptionのテストします。
     */
    @Test
    public void testCreateAccount_ValidationException() {
        List<AccountContactCriteriaDto> accountContactCriteriaDtoList =
            new ArrayList<AccountContactCriteriaDto>();
        String crmDomainCd = "crmDomainCd";
        try {
            accountContactLogicImpl.createAccount(
                accountContactCriteriaDtoList,
                crmDomainCd);
            fail();

        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    /**
     * アカウント登録のテストします。
     */
    @Test
    public void testCreateAccount() {
        List<AccountContactCriteriaDto> accountContactCriteriaDtoList =
            new ArrayList<AccountContactCriteriaDto>();
        AccountContactCriteriaDto criteriaDto = new AccountContactCriteriaDto();
        criteriaDto.crmAccountCd = "0001";
        accountContactCriteriaDtoList.add(criteriaDto);
        String crmDomainCd = "crmDomainCd";
        List<CrmCcAccountContact> resultList =
            new ArrayList<CrmCcAccountContact>();
        CrmCcAccountContact entity = new CrmCcAccountContact();
        entity.setDeleteFlag(false);
        resultList.add(entity);
        when(crmCcAccountContactDao.getCreateEntityList(criteriaDto))
            .thenReturn(resultList);
        try {
            accountContactLogicImpl.createAccount(
                accountContactCriteriaDtoList,
                crmDomainCd);
            fail();

        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    /**
     * 登録のテストします。
     */
    @Test
    public void testCreate_create() {
        List<AccountContactCriteriaDto> accountContactCriteriaDtoList =
            new ArrayList<AccountContactCriteriaDto>();
        AccountContactCriteriaDto criteriaDto = new AccountContactCriteriaDto();
        criteriaDto.crmAccountCd = "0001";
        accountContactCriteriaDtoList.add(criteriaDto);
        String crmDomainCd = "crmDomainCd";
        List<CrmCcAccountContact> resultList =
            new ArrayList<CrmCcAccountContact>();
        CrmCcAccountContact entity = new CrmCcAccountContact();

        when(crmCcAccountContactDao.getCreateEntityList(criteriaDto))
            .thenReturn(resultList);
        when(beanMapper.map(criteriaDto, CrmCcAccountContact.class))
            .thenReturn(entity);

        try {
            accountContactLogicImpl.create(
                accountContactCriteriaDtoList,
                crmDomainCd);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    /**
     * 登録と更新のテストします。
     */
    @Test
    public void testCreate_update() {
        List<AccountContactCriteriaDto> accountContactCriteriaDtoList =
            new ArrayList<AccountContactCriteriaDto>();
        AccountContactCriteriaDto criteriaDto = new AccountContactCriteriaDto();
        criteriaDto.crmAccountCd = "0001";
        accountContactCriteriaDtoList.add(criteriaDto);
        String crmDomainCd = "crmDomainCd";
        List<CrmCcAccountContact> resultList =
            new ArrayList<CrmCcAccountContact>();
        CrmCcAccountContact entity = new CrmCcAccountContact();
        entity.setDeleteFlag(true);
        entity.setStartDate(DateUtil.parse("2010/01/01", "yyyy/MM/dd"));
        resultList.add(entity);
        when(crmCcAccountContactDao.getCreateEntityList(criteriaDto))
            .thenReturn(resultList);
        try {
            accountContactLogicImpl.create(
                accountContactCriteriaDtoList,
                crmDomainCd);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    /**
     * 登録と論理削除取消のテストします。
     */
    @Test
    public void testCreate_delete() {
        List<AccountContactCriteriaDto> accountContactCriteriaDtoList =
            new ArrayList<AccountContactCriteriaDto>();
        AccountContactCriteriaDto criteriaDto = new AccountContactCriteriaDto();
        criteriaDto.crmAccountCd = "0001";
        accountContactCriteriaDtoList.add(criteriaDto);
        String crmDomainCd = "crmDomainCd";
        List<CrmCcAccountContact> resultList =
            new ArrayList<CrmCcAccountContact>();
        CrmCcAccountContact entity = new CrmCcAccountContact();
        entity.setDeleteFlag(true);
        entity.setStartDate(DateUtil.nowDate());
        resultList.add(entity);
        when(crmCcAccountContactDao.getCreateEntityList(criteriaDto))
            .thenReturn(resultList);
        try {
            accountContactLogicImpl.create(
                accountContactCriteriaDtoList,
                crmDomainCd);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    /**
     * アカウントコンタクトのテストします。
     */
    @Test
    public void testDelete() {
        AccountContactCriteriaDto criteriaDto = new AccountContactCriteriaDto();
        criteriaDto.crmAccountCd = "0001";
        criteriaDto.crmContactCd = "0001";
        List<String> crmAccountCdList = new ArrayList<String>();
        crmAccountCdList.add("0001");
        CrmAccountCheckChargeDto crmAccountCheckChargeDto =
            new CrmAccountCheckChargeDto();
        crmAccountCheckChargeDto.crmAccountCdList = crmAccountCdList;
        List<String> crmContactCdList = new ArrayList<String>();
        CrmContactCheckChargeDto crmContactCheckChargeDto =
            new CrmContactCheckChargeDto();
        crmContactCheckChargeDto.crmContactCdList = crmContactCdList;
        List<String> accountCdResultList = new ArrayList<String>();
        List<String> contactCdResultList = new ArrayList<String>();
        accountCdResultList.add("1");
        contactCdResultList.add("2");
        when(crmAccountLogic.checkCharge(crmAccountCheckChargeDto)).thenReturn(
            accountCdResultList);
        when(crmContactLogic.checkCharge(crmContactCheckChargeDto)).thenReturn(
            contactCdResultList);
        String crmDomainCd = "crmDomainCd";
        try {
            accountContactLogicImpl.delete(criteriaDto, crmDomainCd);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }
}
