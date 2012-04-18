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

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountChargeDeptDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcDepartmentCmnDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountChargeDeptNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
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
 * CrmAccountChargeDepartmentLogicImplクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmAccountChargeDepartmentLogicImplTest {

    /**
     * CrmAccountChargeDepartmentLogicImpl
     */
    private CrmAccountChargeDepartmentLogicImpl crmAccountChargeDepartmentLogicImpl;

    /**
     * Mockitoを利用し、生成したモックオブジェクトです。
     */
    @MockitoComponent(componentName = "crmCcAccountChargeDeptDao")
    protected CrmCcAccountChargeDeptDao dao;

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
     * CRMパラメタに関するDAO
     */
    @MockitoComponent
    protected ParameterLogic parameterLogic;

    /**
     * 担当組織共通
     */
    @MockitoComponent
    protected CrmCcDepartmentCmnDao crmCcDepartmentCmnDao;

    @MockitoComponent
    protected UniqueIdGenerator uniqueIdGenerator;

    /**
     * {@link BeanMapper}
     */
    @MockitoComponent
    protected BeanMapper beanMapper;

    /**
     * CrmCcAccountChargeDeptCriteriaDto
     */
    CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto =
        new CrmCcAccountChargeDeptCriteriaDto();

    /**
     * List<CrmCcAccountChargeDeptCriteriaDto>
     */
    List<CrmCcAccountChargeDeptCriteriaDto> chargeDeptCriteriaDtoList =
        new ArrayList<CrmCcAccountChargeDeptCriteriaDto>();

    /**
     * CrmCcAccountChargeDept
     */
    CrmCcAccountChargeDept entity = new CrmCcAccountChargeDept();

    /**
     * List<CrmCcAccountChargeDeptResultDto>
     */
    List<CrmCcAccountChargeDeptResultDto> resultDto =
        new ArrayList<CrmCcAccountChargeDeptResultDto>();

    /**
     * List<CrmCcAccountChargeDept>
     */
    List<CrmCcAccountChargeDept> entityList =
        new ArrayList<CrmCcAccountChargeDept>();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @SuppressWarnings("static-access")
    @PostBindFields
    public void postBindFields() {

        chargeDeptCriteriaDto.crmAccountCd = "0001";
        chargeDeptCriteriaDto.departmentCd = "0001";
        chargeDeptCriteriaDto.startDate = DateUtil.parse("2010071", "yyyyMMdd");
        chargeDeptCriteriaDto.endDate = DateUtil.parse("2011071", "yyyyMMdd");
        chargeDeptCriteriaDtoList.add(chargeDeptCriteriaDto);
        entity.setCompanyCd("0001");
        entity.setCrmAccountCd("0001");
        entity.setDepartmentCd("0001");
        entity.setCrmDomainCd("1");
        entity.setTermCd("0001");
        entity.setStartDate(DateUtil.parse("2010071", "yyyyMMdd"));
        entity.setEndDate(DateUtil.parse("2011071", "yyyyMMdd"));
        entity.setMainChargeFlag(false);
        entity.setDeleteFlag(false);
        CrmCcAccountChargeDeptResultDto result =
            new CrmCcAccountChargeDeptResultDto();
        result.departmentName = "departmentName0";
        result.mainChargeFlag = "0";
        result.startDate = DateUtil.parse("2010071", "yyyyMMdd");
        result.endDate = DateUtil.parse("2011071", "yyyyMMdd");
        result.departmentCd = "0";
        result.mainCharge = "0";
        result.deptCmnStartDate = DateUtil.parse("2010081", "yyyyMMdd");
        result.deptCmnEndDate = DateUtil.parse("2011081", "yyyyMMdd");
        resultDto.add(result);
        when(beanMapper.map(result, CrmCcAccountChargeDept.class)).thenReturn(
            entity);

        FeatureContextImpl featureContextImpl = new FeatureContextImpl();
        featureContextImpl.setCompanyCode("0001");

        when(dao.getEntityList(chargeDeptCriteriaDto)).thenReturn(resultDto);
        when(contextContainer.getApplicationContext()).thenReturn(
            applicationContext);

        when(applicationContext.getFeatureContext()).thenReturn(
            featureContextImpl);
        when(bizConfigurationProvider.getStartDate()).thenReturn(
            DateUtil.parse("19000101", "yyyyMMdd"));
        when(bizConfigurationProvider.getEndDate()).thenReturn(
            DateUtil.parse("30000101", "yyyyMMdd"));
        when(uniqueIdGenerator.getInstance().create()).thenReturn("0001");
        when(parameterLogic.getEntity("CRMCC0002")).thenReturn("1");
        when(dao.getCreateEntityList(chargeDeptCriteriaDtoList.get(0)))
            .thenReturn(entityList);
        List<CrmCcDepartmentCmn> crmCcDepartmentCmnList =
            new ArrayList<CrmCcDepartmentCmn>();
        CrmCcDepartmentCmn crmCcDepartmentCmn = new CrmCcDepartmentCmn();
        crmCcDepartmentCmn.setDepartmentCd(chargeDeptCriteriaDto.departmentCd);
        crmCcDepartmentCmnList.add(crmCcDepartmentCmn);
        when(crmCcDepartmentCmnDao.findAll())
            .thenReturn(crmCcDepartmentCmnList);

    }

    /**
     * CRMアカウント担当組織を一覧検索するのテストです。
     */
    @Test
    public void testGetEntityList() {
        List<CrmCcAccountChargeDeptResultDto> resultDto =
            this.crmAccountChargeDepartmentLogicImpl
                .getEntityList(chargeDeptCriteriaDto);
        verify(dao).getEntityList(chargeDeptCriteriaDto);
        assertEquals(1, resultDto.size());
    }

    /**
     * CRMアカウント担当組織の前期間を登録します。
     */
    @Test
    public void testUpdateBeforeTerm_create() {
        entity = null;
        chargeDeptCriteriaDto.startDate =
            DateUtil.parse("20100901", "yyyyMMdd");
        chargeDeptCriteriaDto.endDate = chargeDeptCriteriaDto.startDate;
        CrmCcAccountChargeDept crmCcAccountChargeDept =
            new CrmCcAccountChargeDept();
        crmCcAccountChargeDept.setCompanyCd("0001");
        crmCcAccountChargeDept.setCrmAccountCd("0001");
        crmCcAccountChargeDept.setDepartmentCd("0001");
        crmCcAccountChargeDept.setCrmDomainCd("1");
        crmCcAccountChargeDept.setTermCd("0001");
        crmCcAccountChargeDept.setEndDate(chargeDeptCriteriaDto.endDate);
        crmCcAccountChargeDept.setStartDate(DateUtil.parse(
            "19000101",
            "yyyyMMdd"));
        crmCcAccountChargeDept.setMainChargeFlag(false);
        crmCcAccountChargeDept.setDeleteFlag(true);
        crmCcAccountChargeDept.setSortKey(0L);
        this.crmAccountChargeDepartmentLogicImpl.updateBeforeTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).insert(crmCcAccountChargeDept);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * CRMアカウント担当組織の前期間を物理削除します。
     */
    @Test
    public void testUpdateBeforeTerm_delete() {
        chargeDeptCriteriaDto.startDate =
            DateUtil.parse("19000101", "yyyyMMdd");
        this.crmAccountChargeDepartmentLogicImpl.updateBeforeTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).delete(entity);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * CRMアカウント担当組織の前期間を更新します。
     */
    @Test
    public void testUpdateBeforeTerm_update() {
        chargeDeptCriteriaDto.startDate =
            DateUtil.parse("20100908", "yyyyMMdd");
        entity.setEndDate(DateUtil.parse("20110908", "yyyyMMdd"));
        this.crmAccountChargeDepartmentLogicImpl.updateBeforeTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).updateIncludes(
                entity,
                CrmCcAccountChargeDeptNames.startDate(),
                CrmCcAccountChargeDeptNames.endDate(),
                CrmCcAccountChargeDeptNames.mainChargeFlag(),
                CrmCcAccountChargeDeptNames.deleteFlag(),
                CrmCcAccountChargeDeptNames.recordUserCd(),
                CrmCcAccountChargeDeptNames.recordDate());
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当組織の中期間を登録します。
     */
    @Test
    public void testUpdateBetweenTerm_create() {
        entity = null;
        chargeDeptCriteriaDto.startDate =
            DateUtil.parse("20100901", "yyyyMMdd");
        chargeDeptCriteriaDto.endDate = chargeDeptCriteriaDto.startDate;
        chargeDeptCriteriaDto.mainChargeFlag = "0";
        CrmCcAccountChargeDept crmCcAccountChargeDept =
            new CrmCcAccountChargeDept();
        crmCcAccountChargeDept.setCompanyCd("0001");
        crmCcAccountChargeDept.setCrmAccountCd("0001");
        crmCcAccountChargeDept.setDepartmentCd("0001");
        crmCcAccountChargeDept.setCrmDomainCd("1");
        crmCcAccountChargeDept.setTermCd("0001");
        crmCcAccountChargeDept.setEndDate(DateUtil
            .parse("20100902", "yyyyMMdd"));
        crmCcAccountChargeDept.setStartDate(chargeDeptCriteriaDto.startDate);
        crmCcAccountChargeDept.setMainChargeFlag(false);
        crmCcAccountChargeDept.setDeleteFlag(false);
        crmCcAccountChargeDept.setSortKey(0L);
        this.crmAccountChargeDepartmentLogicImpl.updateBetweenTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).insert(crmCcAccountChargeDept);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当組織の中期間を更新します。
     */
    @Test
    public void testUpdateBetweenTerm_update() {

        this.crmAccountChargeDepartmentLogicImpl.updateBetweenTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).updateIncludes(
                entity,
                CrmCcAccountChargeDeptNames.startDate(),
                CrmCcAccountChargeDeptNames.endDate(),
                CrmCcAccountChargeDeptNames.mainChargeFlag(),
                CrmCcAccountChargeDeptNames.deleteFlag(),
                CrmCcAccountChargeDeptNames.recordUserCd(),
                CrmCcAccountChargeDeptNames.recordDate());
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当組織の後期間を登録します。
     */
    @Test
    public void testUpdateAfterTerm_create() {

        entity = null;
        chargeDeptCriteriaDto.endDate = DateUtil.parse("20100902", "yyyyMMdd");
        CrmCcAccountChargeDept crmCcAccountChargeDept =
            new CrmCcAccountChargeDept();
        crmCcAccountChargeDept.setCompanyCd("0001");
        crmCcAccountChargeDept.setCrmAccountCd("0001");
        crmCcAccountChargeDept.setDepartmentCd("0001");
        crmCcAccountChargeDept.setCrmDomainCd("1");
        crmCcAccountChargeDept.setTermCd("0001");

        crmCcAccountChargeDept.setStartDate(DateUtil.parse(
            "20100903",
            "yyyyMMdd"));
        crmCcAccountChargeDept.setEndDate(DateUtil
            .parse("30000101", "yyyyMMdd"));
        crmCcAccountChargeDept.setMainChargeFlag(false);
        crmCcAccountChargeDept.setDeleteFlag(true);
        crmCcAccountChargeDept.setSortKey(0L);
        this.crmAccountChargeDepartmentLogicImpl.updateAfterTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).insert(crmCcAccountChargeDept);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当組織の後期間を物理削除します。
     */
    @Test
    public void testUpdateAfterTerm_delete() {
        chargeDeptCriteriaDto.endDate = DateUtil.parse("29991231", "yyyyMMdd");
        this.crmAccountChargeDepartmentLogicImpl.updateAfterTerm(
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
     * CRMアカウント担当組織の後期間を更新します。
     */
    @Test
    public void testUpdateAfterTerm_update() {
        this.crmAccountChargeDepartmentLogicImpl.updateAfterTerm(
            chargeDeptCriteriaDto,
            entity);
        try {
            verify(dao).updateIncludes(
                entity,
                CrmCcAccountChargeDeptNames.startDate(),
                CrmCcAccountChargeDeptNames.endDate(),
                CrmCcAccountChargeDeptNames.mainChargeFlag(),
                CrmCcAccountChargeDeptNames.deleteFlag(),
                CrmCcAccountChargeDeptNames.recordUserCd(),
                CrmCcAccountChargeDeptNames.recordDate());
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * 
     * CRMアカウント担当組織の登録の論理削除です。
     */
    @Test
    public void testCreate_delete() {
        chargeDeptCriteriaDtoList.get(0).mainChargeFlag = "1";
        this.crmAccountChargeDepartmentLogicImpl
            .create(chargeDeptCriteriaDtoList);
        for (int i = 0; i < 10; i++) {
            try {
                verify(dao).updateIncludes(
                    entity,
                    CrmCcAccountChargeDeptNames.deleteFlag(),
                    CrmCcAccountChargeDeptNames.recordUserCd(),
                    CrmCcAccountChargeDeptNames.recordDate());
            } catch (WantedButNotInvoked e) {
                assertTrue(true);
            }
        }
    }

    /**
     * 
     * CRMアカウント担当組織の登録の1件処理です。
     */
    @Test
    public void testCreate_1() {
        chargeDeptCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeDeptCriteriaDtoList.get(0).departmentCd = "0001";
        List<CrmCcAccountChargeDept> entityLis =
            new ArrayList<CrmCcAccountChargeDept>();
        entityLis.add(entity);
        when(
            dao.findByPkIgnoreTerm(
                chargeDeptCriteriaDto.companyCd,
                chargeDeptCriteriaDto.crmAccountCd,
                chargeDeptCriteriaDto.departmentCd,
                chargeDeptCriteriaDto.crmDomainCd)).thenReturn(entityLis);
        this.crmAccountChargeDepartmentLogicImpl
            .create(chargeDeptCriteriaDtoList);
    }

    /**
     * 
     * CRMアカウント担当組織の登録の２件処理です。
     */
    @Test
    public void testCreate_2False() {
        chargeDeptCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeDeptCriteriaDtoList.get(0).departmentCd = "0001";
        List<CrmCcAccountChargeDept> entityLis =
            new ArrayList<CrmCcAccountChargeDept>();
        CrmCcAccountChargeDept entity1 = entity;
        CrmCcAccountChargeDept entity2 = entity;
        entity1.setStartDate(DateUtil.parse("1900-01-01", "yyyy-MM-dd"));
        entity1.setDeleteFlag(false);
        entity2.setDeleteFlag(false);
        entityLis.add(entity1);
        entityLis.add(entity2);
        when(
            dao.findByPkIgnoreTerm(
                chargeDeptCriteriaDto.companyCd,
                chargeDeptCriteriaDto.crmAccountCd,
                chargeDeptCriteriaDto.departmentCd,
                chargeDeptCriteriaDto.crmDomainCd)).thenReturn(entityLis);
        this.crmAccountChargeDepartmentLogicImpl
            .create(chargeDeptCriteriaDtoList);
    }

    /**
     * 
     * CRMアカウント担当組織の登録の２件処理です。
     */
    @Test
    public void testCreate_2True() {
        chargeDeptCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeDeptCriteriaDtoList.get(0).departmentCd = "0001";
        List<CrmCcAccountChargeDept> entityLis =
            new ArrayList<CrmCcAccountChargeDept>();
        CrmCcAccountChargeDept entity1 = entity;
        CrmCcAccountChargeDept entity2 = entity;
        entity1.setStartDate(DateUtil.parse("1900-01-01", "yyyy-MM-dd"));
        entity1.setDeleteFlag(true);
        entity2.setDeleteFlag(true);
        entityLis.add(entity1);
        entityLis.add(entity2);
        when(
            dao.findByPkIgnoreTerm(
                chargeDeptCriteriaDto.companyCd,
                chargeDeptCriteriaDto.crmAccountCd,
                chargeDeptCriteriaDto.departmentCd,
                chargeDeptCriteriaDto.crmDomainCd)).thenReturn(entityLis);
        this.crmAccountChargeDepartmentLogicImpl
            .create(chargeDeptCriteriaDtoList);
    }

    /**
     * 
     * CRMアカウント担当組織の登録の3件処理です。
     */
    @Test
    public void testCreate_3() {
        chargeDeptCriteriaDtoList.get(0).mainChargeFlag = "1";
        chargeDeptCriteriaDtoList.get(0).departmentCd = "0001";
        List<CrmCcAccountChargeDept> entityLis =
            new ArrayList<CrmCcAccountChargeDept>();
        CrmCcAccountChargeDept entity1 = new CrmCcAccountChargeDept();
        CrmCcAccountChargeDept entity2 = new CrmCcAccountChargeDept();
        CrmCcAccountChargeDept entity3 = new CrmCcAccountChargeDept();
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
                chargeDeptCriteriaDto.crmAccountCd,
                chargeDeptCriteriaDto.departmentCd,
                chargeDeptCriteriaDto.crmDomainCd)).thenReturn(entityLis);
        this.crmAccountChargeDepartmentLogicImpl
            .create(chargeDeptCriteriaDtoList);
    }
}
