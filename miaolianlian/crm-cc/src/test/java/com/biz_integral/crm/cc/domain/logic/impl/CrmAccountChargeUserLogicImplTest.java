/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountChargeUserDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcUserCmnDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeUser;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeUserNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.foundation.core.context.ApplicationContext;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.FeatureContextImpl;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.service.domain.master.BizConfigurationProvider;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * CrmAccountChargeUserLogicImplクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmAccountChargeUserLogicImplTest {

    /**
     * CrmAccountChargeDepartmentLogicImpl
     */
    private CrmAccountChargeUserLogicImpl crmAccountChargeUserLogicImpl;

    /**
     * Mockitoを利用し、生成したモックオブジェクトです。
     */
    @MockitoComponent(componentName = "crmCcAccountChargeUserDao")
    protected CrmCcAccountChargeUserDao dao;

    /**
     * CRMパラメタに関するDAO
     */
    @MockitoComponent
    protected ParameterLogic parameterLogic;

    /**
     * ユーザ_共通に関するDAO
     */
    @MockitoComponent
    protected CrmCcUserCmnDao crmCcUserCmnDao;

    /**
     * {@link BizConfigurationProvider}
     */
    @MockitoComponent
    protected BizConfigurationProvider bizConfigurationProvider;

    /**
     * コンテキストコンテナ。
     */
    @MockitoComponent
    protected ContextContainer contextContainer;

    /**
     * {@link ApplicationContext}
     */
    @MockitoComponent(componentName = "applicationContext")
    protected ApplicationContext applicationContext;

    /**
     * {@link BeanMapper}
     */
    @MockitoComponent
    protected BeanMapper beanMapper;

    @MockitoComponent
    protected UniqueIdGenerator uniqueIdGenerator;

    /**
     * CrmCcAccountChargeUserCriteriaDto
     */
    CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto =
        new CrmCcAccountChargeUserCriteriaDto();

    /**
     * List<CrmCcAccountChargeUserCriteriaDto>
     */
    List<CrmCcAccountChargeUserCriteriaDto> chargeUserCriteriaDtoList =
        new ArrayList<CrmCcAccountChargeUserCriteriaDto>();

    /**
     * CrmCcAccountChargeUser
     */
    CrmCcAccountChargeUser entity = new CrmCcAccountChargeUser();

    /**
     * List<CrmCcAccountChargeUserResultDto>
     */
    List<CrmCcAccountChargeUserResultDto> resultDto =
        new ArrayList<CrmCcAccountChargeUserResultDto>();
    List<CrmCcAccountChargeUser> entityList =
        new ArrayList<CrmCcAccountChargeUser>();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @SuppressWarnings("static-access")
    @PostBindFields
    public void postBindFields() {
        chargeUserCriteriaDto.companyCd = "0001";

        chargeUserCriteriaDto.crmAccountCd = "0001";
        chargeUserCriteriaDto.crmDomainCd = "0001";
        chargeUserCriteriaDto.localeId = "ja";
        chargeUserCriteriaDto.userCd = "0001";
        chargeUserCriteriaDto.startDate = DateUtil.parse("2010071", "yyyyMMdd");
        chargeUserCriteriaDto.endDate = DateUtil.parse("2011071", "yyyyMMdd");
        chargeUserCriteriaDtoList.add(chargeUserCriteriaDto);
        CrmCcAccountChargeUserResultDto result =
            new CrmCcAccountChargeUserResultDto();
        result.userName = "userName1";
        result.mainChargeFlag = "0";
        result.startDate = DateUtil.parse("2010071", "yyyyMMdd");
        result.endDate = DateUtil.parse("2011071", "yyyyMMdd");
        result.userCd = "0";
        result.mainCharge = "0";
        result.userCmnStartDate = DateUtil.parse("2010081", "yyyyMMdd");
        result.userCmnEndDate = DateUtil.parse("2011081", "yyyyMMdd");
        resultDto.add(result);
        when(beanMapper.map(result, CrmCcAccountChargeUser.class)).thenReturn(
            entity);

        FeatureContextImpl featureContextImpl = new FeatureContextImpl();
        featureContextImpl.setCompanyCode("0001");
        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setUserID("0001");
        userContextImpl.setLocaleID("ja");
        when(contextContainer.getApplicationContext()).thenReturn(
            applicationContext);
        when(applicationContext.getFeatureContext()).thenReturn(
            featureContextImpl);
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);
        when(uniqueIdGenerator.getInstance().create()).thenReturn("0001");
        when(bizConfigurationProvider.getStartDate()).thenReturn(
            DateUtil.parse("19000101", "yyyyMMdd"));
        when(bizConfigurationProvider.getEndDate()).thenReturn(
            DateUtil.parse("30000101", "yyyyMMdd"));
        when(dao.getEntityList(chargeUserCriteriaDto)).thenReturn(resultDto);

        List<CrmCcUserCmn> crmCcUserCmnList = new ArrayList<CrmCcUserCmn>();
        CrmCcUserCmn crmCcUserCmn = new CrmCcUserCmn();
        crmCcUserCmn.setUserCd(chargeUserCriteriaDto.userCd);
        crmCcUserCmnList.add(crmCcUserCmn);
        when(crmCcUserCmnDao.findAll()).thenReturn(crmCcUserCmnList);

        when(dao.getCreateEntityList(chargeUserCriteriaDtoList.get(0)))
            .thenReturn(entityList);

        when(parameterLogic.getEntity("CRMCC0002")).thenReturn("1");

    }

    /**
     * CRMアカウント担当者を一覧検索するのテストです。
     */
    @Test
    public void testGetEntityList() {
        List<CrmCcAccountChargeUserResultDto> resultDto =
            this.crmAccountChargeUserLogicImpl
                .getEntityList(chargeUserCriteriaDto);
        verify(dao).getEntityList(chargeUserCriteriaDto);
        assertEquals(1, resultDto.size());
    }

    /**
     * CRMアカウント担当者の前期間を登録します。
     */
    @Test
    public void testUpdateBeforeTerm_create() {
        entity = null;
        chargeUserCriteriaDto.startDate =
            DateUtil.parse("20100901", "yyyyMMdd");
        chargeUserCriteriaDto.endDate = chargeUserCriteriaDto.startDate;
        CrmCcAccountChargeUser crmCcAccountChargeUser =
            new CrmCcAccountChargeUser();
        crmCcAccountChargeUser.setCompanyCd("0001");
        crmCcAccountChargeUser.setCrmAccountCd("0001");
        crmCcAccountChargeUser.setUserCd("0001");
        crmCcAccountChargeUser.setCreateUserCd("0001");
        crmCcAccountChargeUser.setCreateDate(DateUtil.nowDate());
        crmCcAccountChargeUser.setTermCd("0001");
        crmCcAccountChargeUser.setEndDate(chargeUserCriteriaDto.endDate);
        crmCcAccountChargeUser.setStartDate(DateUtil.parse(
            "19000101",
            "yyyyMMdd"));
        crmCcAccountChargeUser.setMainChargeFlag(false);
        crmCcAccountChargeUser.setDeleteFlag(true);
        crmCcAccountChargeUser.setSortKey(0L);
        crmCcAccountChargeUser.setCrmDomainCd("1");
        this.crmAccountChargeUserLogicImpl.updateBeforeTerm(
            chargeUserCriteriaDto,
            entity);
        try {
            verify(dao).insert(crmCcAccountChargeUser);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * CRMアカウント担当者の前期間を物理削除します。
     */
    @Test
    public void testUpdateBeforeTerm_delete() {
        chargeUserCriteriaDto.startDate =
            DateUtil.parse("19000101", "yyyyMMdd");
        this.crmAccountChargeUserLogicImpl.updateBeforeTerm(
            chargeUserCriteriaDto,
            entity);
        try {
            verify(dao).delete(entity);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * CRMアカウント担当者の前期間を更新します。
     */
    @Test
    public void testUpdateBeforeTerm_update() {
        chargeUserCriteriaDto.startDate =
            DateUtil.parse("20100908", "yyyyMMdd");
        entity.setEndDate(DateUtil.parse("20110908", "yyyyMMdd"));
        this.crmAccountChargeUserLogicImpl.updateBeforeTerm(
            chargeUserCriteriaDto,
            entity);
        try {
            verify(dao).updateIncludes(
                entity,
                CrmCcAccountChargeUserNames.startDate(),
                CrmCcAccountChargeUserNames.endDate(),
                CrmCcAccountChargeUserNames.mainChargeFlag(),
                CrmCcAccountChargeUserNames.deleteFlag(),
                CrmCcAccountChargeUserNames.recordUserCd(),
                CrmCcAccountChargeUserNames.recordDate());
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当者の中期間を登録します。
     */
    @Test
    public void testUpdateBetweenTerm_create() {
        entity = null;
        chargeUserCriteriaDto.startDate =
            DateUtil.parse("20100901", "yyyyMMdd");
        chargeUserCriteriaDto.endDate = chargeUserCriteriaDto.startDate;
        chargeUserCriteriaDto.mainChargeFlag = "0";
        CrmCcAccountChargeUser crmCcAccountChargeUser =
            new CrmCcAccountChargeUser();
        crmCcAccountChargeUser.setCompanyCd("0001");
        crmCcAccountChargeUser.setCrmAccountCd("0001");
        crmCcAccountChargeUser.setUserCd("0001");
        crmCcAccountChargeUser.setTermCd("0001");
        crmCcAccountChargeUser.setEndDate(DateUtil
            .parse("20100902", "yyyyMMdd"));
        crmCcAccountChargeUser.setStartDate(chargeUserCriteriaDto.startDate);
        crmCcAccountChargeUser.setMainChargeFlag(false);
        crmCcAccountChargeUser.setDeleteFlag(false);
        crmCcAccountChargeUser.setSortKey(0L);
        crmCcAccountChargeUser.setCrmDomainCd("1");
        this.crmAccountChargeUserLogicImpl.updateBetweenTerm(
            chargeUserCriteriaDto,
            entity);
        try {
            verify(dao).insert(crmCcAccountChargeUser);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当者の中期間を更新します。
     */
    @Test
    public void testUpdateBetweenTerm_update() {

        this.crmAccountChargeUserLogicImpl.updateBetweenTerm(
            chargeUserCriteriaDto,
            entity);
        try {
            verify(dao).updateIncludes(
                entity,
                CrmCcAccountChargeUserNames.startDate(),
                CrmCcAccountChargeUserNames.endDate(),
                CrmCcAccountChargeUserNames.mainChargeFlag(),
                CrmCcAccountChargeUserNames.deleteFlag(),
                CrmCcAccountChargeUserNames.recordUserCd(),
                CrmCcAccountChargeUserNames.recordDate());
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当者の後期間を登録します。
     */
    @Test
    public void testUpdateAfterTerm_create() {

        entity = null;
        chargeUserCriteriaDto.endDate = DateUtil.parse("20100902", "yyyyMMdd");
        CrmCcAccountChargeUser crmCcAccountChargeUser =
            new CrmCcAccountChargeUser();
        crmCcAccountChargeUser.setCompanyCd("0001");
        crmCcAccountChargeUser.setCrmAccountCd("0001");
        crmCcAccountChargeUser.setUserCd("0001");
        crmCcAccountChargeUser.setTermCd("0001");

        crmCcAccountChargeUser.setStartDate(DateUtil.parse(
            "20100903",
            "yyyyMMdd"));
        crmCcAccountChargeUser.setEndDate(DateUtil
            .parse("30000101", "yyyyMMdd"));
        crmCcAccountChargeUser.setMainChargeFlag(false);
        crmCcAccountChargeUser.setDeleteFlag(true);
        crmCcAccountChargeUser.setSortKey(0L);
        crmCcAccountChargeUser.setCrmDomainCd("1");
        this.crmAccountChargeUserLogicImpl.updateAfterTerm(
            chargeUserCriteriaDto,
            entity);
        try {
            verify(dao).insert(crmCcAccountChargeUser);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当者の後期間を物理削除します。
     */
    @Test
    public void testUpdateAfterTerm_delete() {
        chargeUserCriteriaDto.endDate = DateUtil.parse("29991231", "yyyyMMdd");
        this.crmAccountChargeUserLogicImpl.updateAfterTerm(
            chargeUserCriteriaDto,
            entity);
        try {
            verify(dao).delete(entity);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当者の後期間を更新します。
     */
    @Test
    public void testUpdateAfterTerm_update() {
        entity.setStartDate(DateUtil.parse("20100702", "yyyyMMdd"));
        this.crmAccountChargeUserLogicImpl.updateAfterTerm(
            chargeUserCriteriaDto,
            entity);
        try {
            verify(dao).updateIncludes(
                entity,
                CrmCcAccountChargeUserNames.startDate(),
                CrmCcAccountChargeUserNames.endDate(),
                CrmCcAccountChargeUserNames.mainChargeFlag(),
                CrmCcAccountChargeUserNames.deleteFlag(),
                CrmCcAccountChargeUserNames.recordUserCd(),
                CrmCcAccountChargeUserNames.recordDate());
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当者の登録の論理削除です。
     */
    @Test
    public void testCreate_delete() {
        chargeUserCriteriaDtoList.get(0).mainChargeFlag = "1";
        this.crmAccountChargeUserLogicImpl.create(chargeUserCriteriaDtoList);
        for (int i = 0; i < 10; i++) {
            try {
                verify(dao).updateIncludes(
                    entity,
                    CrmCcAccountChargeUserNames.deleteFlag(),
                    CrmCcAccountChargeUserNames.recordUserCd(),
                    CrmCcAccountChargeUserNames.recordDate());
            } catch (WantedButNotInvoked e) {
                assertTrue(true);
            }
        }
    }

    /**
     * 
     * CRMアカウント担当者の登録の1件処理です。
     */
    @Test
    public void testCreate_1() {
        chargeUserCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeUserCriteriaDtoList.get(0).userCd = "0001";
        List<CrmCcAccountChargeUser> entityLis =
            new ArrayList<CrmCcAccountChargeUser>();
        entityLis.add(entity);
        when(
            dao.findByPkIgnoreTerm(
                chargeUserCriteriaDto.companyCd,
                chargeUserCriteriaDto.crmAccountCd,
                chargeUserCriteriaDto.userCd,
                chargeUserCriteriaDto.crmDomainCd)).thenReturn(entityLis);
        this.crmAccountChargeUserLogicImpl.create(chargeUserCriteriaDtoList);
    }

    /**
     * 
     * CRMアカウント担当者の登録の２件処理です。
     */
    @Test
    public void testCreate_2False() {
        chargeUserCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeUserCriteriaDtoList.get(0).userCd = "0001";
        List<CrmCcAccountChargeUser> entityLis =
            new ArrayList<CrmCcAccountChargeUser>();
        CrmCcAccountChargeUser entity1 = new CrmCcAccountChargeUser();
        CrmCcAccountChargeUser entity2 = new CrmCcAccountChargeUser();
        entity1 = entity;
        entity1.setStartDate(DateUtil.parse("1900-01-01", "yyyy-MM-dd"));
        entity1.setDeleteFlag(false);
        entity2 = entity;
        entity2.setDeleteFlag(false);
        entityLis.add(entity1);
        entityLis.add(entity2);
        when(
            dao.findByPkIgnoreTerm(
                chargeUserCriteriaDto.companyCd,
                chargeUserCriteriaDto.crmAccountCd,
                chargeUserCriteriaDto.userCd,
                chargeUserCriteriaDto.crmDomainCd)).thenReturn(entityLis);
        this.crmAccountChargeUserLogicImpl.create(chargeUserCriteriaDtoList);
    }

    /**
     * 
     * CRMアカウント担当者の登録の２件処理です。
     */
    @Test
    public void testCreate_2True() {
        chargeUserCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeUserCriteriaDtoList.get(0).userCd = "0001";
        List<CrmCcAccountChargeUser> entityLis =
            new ArrayList<CrmCcAccountChargeUser>();
        CrmCcAccountChargeUser entity1 = new CrmCcAccountChargeUser();
        CrmCcAccountChargeUser entity2 = new CrmCcAccountChargeUser();
        entity1.setStartDate(DateUtil.parse("1900-01-01", "yyyy-MM-dd"));
        entity1.setDeleteFlag(true);
        entity1.setEndDate(DateUtil.parse("3000-01-01", "yyyy-MM-dd"));
        entity2.setDeleteFlag(true);

        entityLis.add(entity1);
        entityLis.add(entity2);
        when(
            dao.findByPkIgnoreTerm(
                chargeUserCriteriaDto.companyCd,
                chargeUserCriteriaDto.crmAccountCd,
                chargeUserCriteriaDto.userCd,
                chargeUserCriteriaDto.crmDomainCd)).thenReturn(entityLis);
        this.crmAccountChargeUserLogicImpl.create(chargeUserCriteriaDtoList);
    }

    /**
     * 
     * CRMアカウント担当者の登録の3件処理です。
     */
    @Test
    public void testCreate_3() {
        chargeUserCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeUserCriteriaDtoList.get(0).userCd = "0001";
        List<CrmCcAccountChargeUser> entityLis =
            new ArrayList<CrmCcAccountChargeUser>();
        CrmCcAccountChargeUser entity1 = new CrmCcAccountChargeUser();
        CrmCcAccountChargeUser entity2 = new CrmCcAccountChargeUser();
        CrmCcAccountChargeUser entity3 = new CrmCcAccountChargeUser();
        entity1.setStartDate(DateUtil.parse("1900-01-01", "yyyy-MM-dd"));
        entity1.setEndDate(DateUtil.parse("2010-01-01", "yyyy-MM-dd"));
        entity2.setStartDate(DateUtil.parse("2010-01-01", "yyyy-MM-dd"));
        entity2.setEndDate(DateUtil.parse("2011-01-01", "yyyy-MM-dd"));
        entity3.setStartDate(DateUtil.parse("2011-01-01", "yyyy-MM-dd"));
        entity3.setEndDate(DateUtil.parse("3000-01-01", "yyyy-MM-dd"));
        entityLis.add(entity1);
        entityLis.add(entity2);
        entityLis.add(entity3);
        when(
            dao.findByPkIgnoreTerm(
                chargeUserCriteriaDto.companyCd,
                chargeUserCriteriaDto.crmAccountCd,
                chargeUserCriteriaDto.userCd,
                chargeUserCriteriaDto.crmDomainCd)).thenReturn(entityLis);
        this.crmAccountChargeUserLogicImpl.create(chargeUserCriteriaDtoList);
    }

}
