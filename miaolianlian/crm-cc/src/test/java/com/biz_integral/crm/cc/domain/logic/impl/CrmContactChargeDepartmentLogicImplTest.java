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

import com.biz_integral.crm.cc.domain.dao.CrmCcContactChargeDeptDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcDepartmentCmnDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactChargeDeptNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.foundation.core.context.ApplicationContext;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.FeatureContextImpl;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.service.domain.master.BizConfigurationProvider;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * CrmContactChargeDepartmentLogicImplクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmContactChargeDepartmentLogicImplTest {

    /**
     * CrmContactChargeDepartmentLogicImpl
     */
    private CrmContactChargeDepartmentLogicImpl crmContactChargeDepartmentLogicImpl;

    /**
     * CRMコンタクト担当組織に関するDAO
     */
    @MockitoComponent(componentName = "crmCcContactChargeDeptDao")
    protected CrmCcContactChargeDeptDao dao;

    /**
     * アプリケーション共通マスタ
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

    /**
     * 担当組織共通
     */
    @MockitoComponent
    protected CrmCcDepartmentCmnDao crmCcDepartmentCmnDao;

    /**
     * UniqueIdGenerator
     */
    @MockitoComponent
    protected UniqueIdGenerator uniqueIdGenerator;

    /**
     * CrmCcContactChargeDeptCriteriaDto
     */
    CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto =
        new CrmCcContactChargeDeptCriteriaDto();

    /**
     * List<CrmCcContactChargeDeptResultDto>
     */
    List<CrmCcContactChargeDeptResultDto> resultDto =
        new ArrayList<CrmCcContactChargeDeptResultDto>();

    /**
     * List<CrmCcContactChargeDept>
     */
    List<CrmCcContactChargeDept> entityList =
        new ArrayList<CrmCcContactChargeDept>();

    /**
     * List<CrmCcContactChargeDeptCriteriaDto>
     */
    List<CrmCcContactChargeDeptCriteriaDto> chargeDeptCriteriaDtoList =
        new ArrayList<CrmCcContactChargeDeptCriteriaDto>();

    /**
     * CrmCcContactChargeDept
     */
    CrmCcContactChargeDept entity = new CrmCcContactChargeDept();

    /**
     * List<CrmCcContactChargeUserCriteriaDto>
     */
    List<CrmCcContactChargeUserCriteriaDto> chargeUserCriteriaDtoList =
        new ArrayList<CrmCcContactChargeUserCriteriaDto>();

    /**
     * CrmCcContactChargeUserCriteriaDto
     */
    CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto =
        new CrmCcContactChargeUserCriteriaDto();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @SuppressWarnings("static-access")
    @PostBindFields
    public void postBindFields() {
        entity.setCompanyCd("0001");
        entity.setCrmContactCd("0001");
        entity.setDepartmentCd("0001");
        entity.setTermCd("0001");
        entity.setStartDate(DateUtil.parse("2010071", "yyyyMMdd"));
        entity.setEndDate(DateUtil.parse("2011071", "yyyyMMdd"));
        entity.setMainChargeFlag(false);
        entity.setDeleteFlag(false);
        chargeDeptCriteriaDto.crmContactCd = "0001";
        chargeDeptCriteriaDto.departmentCd = "0001";
        chargeDeptCriteriaDto.startDate = DateUtil.parse("2010071", "yyyyMMdd");
        chargeDeptCriteriaDto.endDate = DateUtil.parse("2011071", "yyyyMMdd");
        chargeDeptCriteriaDtoList.add(chargeDeptCriteriaDto);
        CrmCcContactChargeDeptResultDto result =
            new CrmCcContactChargeDeptResultDto();
        result.departmentName = "departmentName";
        result.mainChargeFlag = "0";
        result.startDate = DateUtil.parse("2010071", "yyyyMMdd");
        result.endDate = DateUtil.parse("2011071", "yyyyMMdd");
        result.departmentCd = "0";
        result.mainCharge = "0";
        result.deptCmnStartDate = DateUtil.parse("2010081", "yyyyMMdd");
        result.deptCmnEndDate = DateUtil.parse("2011081", "yyyyMMdd");
        resultDto.add(result);
        when(beanMapper.map(result, CrmCcContactChargeDept.class)).thenReturn(
            entity);
        when(dao.getEntityList(chargeDeptCriteriaDto)).thenReturn(resultDto);

        when(bizConfigurationProvider.getStartDate()).thenReturn(
            DateUtil.parse("19000101", "yyyyMMdd"));
        when(bizConfigurationProvider.getEndDate()).thenReturn(
            DateUtil.parse("30000101", "yyyyMMdd"));

        FeatureContextImpl featureContextImpl = new FeatureContextImpl();
        featureContextImpl.setCompanyCode("0001");
        when(contextContainer.getApplicationContext()).thenReturn(
            applicationContext);
        when(applicationContext.getFeatureContext()).thenReturn(
            featureContextImpl);
        when(uniqueIdGenerator.getInstance().create()).thenReturn("0001");

        List<CrmCcDepartmentCmn> crmCcDepartmentCmnList =
            new ArrayList<CrmCcDepartmentCmn>();
        CrmCcDepartmentCmn crmCcDepartmentCmn = new CrmCcDepartmentCmn();
        crmCcDepartmentCmn.setDepartmentCd(chargeDeptCriteriaDto.departmentCd);
        crmCcDepartmentCmnList.add(crmCcDepartmentCmn);
        when(crmCcDepartmentCmnDao.findAll())
            .thenReturn(crmCcDepartmentCmnList);

        when(dao.getCreateEntityList(chargeDeptCriteriaDtoList.get(0)))
            .thenReturn(entityList);

        chargeUserCriteriaDto.companyCd = "0001";

        chargeUserCriteriaDto.crmContactCd = "0001";
        chargeUserCriteriaDto.localeId = "ja";
        chargeUserCriteriaDto.userCd = "0001";
        chargeUserCriteriaDto.startDate = DateUtil.parse("2010071", "yyyyMMdd");
        chargeUserCriteriaDto.endDate = DateUtil.parse("2011071", "yyyyMMdd");
        chargeUserCriteriaDtoList.add(chargeUserCriteriaDto);
    }

    /**
     * CRMコンタクト担当組織の一覧を取得のテストです。
     */
    @Test
    public void testGetEntityList() {
        List<CrmCcContactChargeDeptResultDto> resultDto =
            this.crmContactChargeDepartmentLogicImpl
                .getEntityList(chargeDeptCriteriaDto);
        verify(dao).getEntityList(chargeDeptCriteriaDto);
        assertEquals(1, resultDto.size());
    }

    /**
     * CRMコンタクト担当組織の前期間を登録します。
     */
    @Test
    public void testUpdateBeforeTerm_create() {
        entity = null;
        chargeDeptCriteriaDto.startDate =
            DateUtil.parse("20100901", "yyyyMMdd");
        chargeDeptCriteriaDto.endDate = chargeDeptCriteriaDto.startDate;
        CrmCcContactChargeDept crmCcContactChargeDept =
            new CrmCcContactChargeDept();
        crmCcContactChargeDept.setCompanyCd("0001");
        crmCcContactChargeDept.setCrmContactCd("0001");
        crmCcContactChargeDept.setDepartmentCd("0001");
        crmCcContactChargeDept.setTermCd("0001");
        crmCcContactChargeDept.setEndDate(chargeDeptCriteriaDto.endDate);
        crmCcContactChargeDept.setStartDate(DateUtil.parse(
            "19000101",
            "yyyyMMdd"));
        crmCcContactChargeDept.setMainChargeFlag(false);
        crmCcContactChargeDept.setDeleteFlag(true);
        crmCcContactChargeDept.setSortKey(0L);
        this.crmContactChargeDepartmentLogicImpl.updateBeforeTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).insert(crmCcContactChargeDept);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * CRMコンタクト担当組織の前期間を物理削除します。
     */
    @Test
    public void testUpdateBeforeTerm_delete() {
        chargeDeptCriteriaDto.startDate =
            DateUtil.parse("19000101", "yyyyMMdd");
        this.crmContactChargeDepartmentLogicImpl.updateBeforeTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).delete(entity);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * CRMコンタクト担当組織の前期間を更新します。
     */
    @Test
    public void testUpdateBeforeTerm_update() {
        chargeDeptCriteriaDto.startDate =
            DateUtil.parse("20100908", "yyyyMMdd");
        entity.setEndDate(DateUtil.parse("20110908", "yyyyMMdd"));
        this.crmContactChargeDepartmentLogicImpl.updateBeforeTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).updateIncludes(
                entity,
                CrmCcContactChargeDeptNames.startDate(),
                CrmCcContactChargeDeptNames.endDate(),
                CrmCcContactChargeDeptNames.mainChargeFlag(),
                CrmCcContactChargeDeptNames.deleteFlag(),
                CrmCcContactChargeDeptNames.recordUserCd(),
                CrmCcContactChargeDeptNames.recordDate());
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMコンタクト担当組織の中期間を登録します。
     */
    @Test
    public void testUpdateBetweenTerm_create() {
        entity = null;
        chargeDeptCriteriaDto.startDate =
            DateUtil.parse("20100901", "yyyyMMdd");
        chargeDeptCriteriaDto.endDate = chargeDeptCriteriaDto.startDate;
        chargeDeptCriteriaDto.mainChargeFlag = "0";
        CrmCcContactChargeDept crmCcContactChargeDept =
            new CrmCcContactChargeDept();
        crmCcContactChargeDept.setCompanyCd("0001");
        crmCcContactChargeDept.setCrmContactCd("0001");
        crmCcContactChargeDept.setDepartmentCd("0001");
        crmCcContactChargeDept.setTermCd("0001");
        crmCcContactChargeDept.setEndDate(DateUtil
            .parse("20100902", "yyyyMMdd"));
        crmCcContactChargeDept.setStartDate(chargeDeptCriteriaDto.startDate);
        crmCcContactChargeDept.setMainChargeFlag(false);
        crmCcContactChargeDept.setDeleteFlag(false);
        crmCcContactChargeDept.setSortKey(0L);
        this.crmContactChargeDepartmentLogicImpl.updateBetweenTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).insert(crmCcContactChargeDept);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMコンタクト担当組織の中期間を更新します。
     */
    @Test
    public void testUpdateBetweenTerm_update() {

        this.crmContactChargeDepartmentLogicImpl.updateBetweenTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).updateIncludes(
                entity,
                CrmCcContactChargeDeptNames.startDate(),
                CrmCcContactChargeDeptNames.endDate(),
                CrmCcContactChargeDeptNames.mainChargeFlag(),
                CrmCcContactChargeDeptNames.deleteFlag(),
                CrmCcContactChargeDeptNames.recordUserCd(),
                CrmCcContactChargeDeptNames.recordDate());
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMコンタクト担当組織の後期間を登録します。
     */
    @Test
    public void testUpdateAfterTerm_create() {

        entity = null;
        chargeDeptCriteriaDto.endDate = DateUtil.parse("20100902", "yyyyMMdd");
        CrmCcContactChargeDept crmCcContactChargeDept =
            new CrmCcContactChargeDept();
        crmCcContactChargeDept.setCompanyCd("0001");
        crmCcContactChargeDept.setCrmContactCd("0001");
        crmCcContactChargeDept.setDepartmentCd("0001");
        crmCcContactChargeDept.setTermCd("0001");

        crmCcContactChargeDept.setStartDate(DateUtil.parse(
            "20100903",
            "yyyyMMdd"));
        crmCcContactChargeDept.setEndDate(DateUtil
            .parse("30000101", "yyyyMMdd"));
        crmCcContactChargeDept.setMainChargeFlag(false);
        crmCcContactChargeDept.setDeleteFlag(true);
        crmCcContactChargeDept.setSortKey(0L);
        this.crmContactChargeDepartmentLogicImpl.updateAfterTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).insert(crmCcContactChargeDept);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMコンタクト担当組織の後期間を物理削除します。
     */
    @Test
    public void testUpdateAfterTerm_delete() {
        chargeDeptCriteriaDto.endDate = DateUtil.parse("29991231", "yyyyMMdd");
        this.crmContactChargeDepartmentLogicImpl.updateAfterTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).delete(entity);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMコンタクト担当組織の後期間を更新します。
     */
    @Test
    public void testUpdateAfterTerm_update() {
        this.crmContactChargeDepartmentLogicImpl.updateAfterTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).updateIncludes(
                entity,
                CrmCcContactChargeDeptNames.startDate(),
                CrmCcContactChargeDeptNames.endDate(),
                CrmCcContactChargeDeptNames.mainChargeFlag(),
                CrmCcContactChargeDeptNames.deleteFlag(),
                CrmCcContactChargeDeptNames.recordUserCd(),
                CrmCcContactChargeDeptNames.recordDate());
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMコンタクト担当組織の登録の論理削除です。
     */
    @Test
    public void testCreate_delete() {
        chargeDeptCriteriaDtoList.get(0).mainChargeFlag = "1";

        this.crmContactChargeDepartmentLogicImpl.create(
            chargeDeptCriteriaDtoList,
            chargeUserCriteriaDtoList,
            "");
        try {
            verify(dao).updateIncludes(
                entity,
                CrmCcContactChargeDeptNames.deleteFlag(),
                CrmCcContactChargeDeptNames.recordUserCd(),
                CrmCcContactChargeDeptNames.recordDate());
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMコンタクト担当組織の登録の1件処理です。
     */
    @Test
    public void testCreate_1() {
        chargeDeptCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeDeptCriteriaDtoList.get(0).departmentCd = "0001";
        List<CrmCcContactChargeDept> entityList =
            new ArrayList<CrmCcContactChargeDept>();
        entityList.add(entity);
        when(
            dao.findByPkIgnoreTerm(
                chargeDeptCriteriaDto.companyCd,
                chargeDeptCriteriaDto.crmContactCd,
                chargeDeptCriteriaDto.departmentCd)).thenReturn(entityList);
        this.crmContactChargeDepartmentLogicImpl.create(
            chargeDeptCriteriaDtoList,
            chargeUserCriteriaDtoList,
            "");
    }

    /**
     * 
     * CRMコンタクト担当組織の登録の２件処理です。
     */
    @Test
    public void testCreate_2False() {
        chargeDeptCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeDeptCriteriaDtoList.get(0).departmentCd = "0001";
        List<CrmCcContactChargeDept> entityLis =
            new ArrayList<CrmCcContactChargeDept>();
        CrmCcContactChargeDept entity1 = entity;
        CrmCcContactChargeDept entity2 = entity;
        entity1.setStartDate(DateUtil.parse("1900-01-01", "yyyy-MM-dd"));
        entity1.setDeleteFlag(false);
        entity2.setDeleteFlag(false);
        entityLis.add(entity1);
        entityLis.add(entity2);
        when(
            dao.findByPkIgnoreTerm(
                chargeDeptCriteriaDto.companyCd,
                chargeDeptCriteriaDto.crmContactCd,
                chargeDeptCriteriaDto.departmentCd)).thenReturn(entityLis);
        this.crmContactChargeDepartmentLogicImpl.create(
            chargeDeptCriteriaDtoList,
            chargeUserCriteriaDtoList,
            "");
    }

    /**
     * 
     * CRMコンタクト担当組織の登録の２件処理です。
     */
    @Test
    public void testCreate_2True() {
        chargeDeptCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeDeptCriteriaDtoList.get(0).departmentCd = "0001";
        List<CrmCcContactChargeDept> entityLis =
            new ArrayList<CrmCcContactChargeDept>();
        CrmCcContactChargeDept entity1 = entity;
        CrmCcContactChargeDept entity2 = entity;
        entity1.setStartDate(DateUtil.parse("1900-01-01", "yyyy-MM-dd"));
        entity1.setDeleteFlag(true);
        entity2.setDeleteFlag(true);
        entityLis.add(entity1);
        entityLis.add(entity2);
        when(
            dao.findByPkIgnoreTerm(
                chargeDeptCriteriaDto.companyCd,
                chargeDeptCriteriaDto.crmContactCd,
                chargeDeptCriteriaDto.departmentCd)).thenReturn(entityLis);
        this.crmContactChargeDepartmentLogicImpl.create(
            chargeDeptCriteriaDtoList,
            chargeUserCriteriaDtoList,
            "");
    }

    /**
     * 
     * CRMコンタクト担当組織の登録の3件処理です。
     */
    @Test
    public void testCreate_3() {
        chargeDeptCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeDeptCriteriaDtoList.get(0).departmentCd = "0001";
        List<CrmCcContactChargeDept> entityLis =
            new ArrayList<CrmCcContactChargeDept>();
        CrmCcContactChargeDept entity1 = new CrmCcContactChargeDept();
        CrmCcContactChargeDept entity2 = new CrmCcContactChargeDept();
        CrmCcContactChargeDept entity3 = new CrmCcContactChargeDept();
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
                chargeDeptCriteriaDto.companyCd,
                chargeDeptCriteriaDto.crmContactCd,
                chargeDeptCriteriaDto.departmentCd)).thenReturn(entityLis);
        this.crmContactChargeDepartmentLogicImpl.create(
            chargeDeptCriteriaDtoList,
            chargeUserCriteriaDtoList,
            "");
    }
}
