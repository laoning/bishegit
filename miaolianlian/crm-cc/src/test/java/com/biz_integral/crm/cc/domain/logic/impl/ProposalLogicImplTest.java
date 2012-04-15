/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dao.CrmCcParameterDao;
import com.biz_integral.crm.cc.domain.dto.ProposalCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.ProposalCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ProposalResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmCcDepartmentAthCmnLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.pm.domain.dao.CrmPmProposalDao;
import com.biz_integral.foundation.core.context.ApplicationContext;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.FeatureContextImpl;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.service.security.authorization.department.DepartmentAuthorizationEvaluator;
import com.biz_integral.service.security.authorization.functional.FunctionalAuthorization;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * ProposalLogicImplクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class ProposalLogicImplTest {

    /**
     * {@link crmPmProposalDao}
     */
    @MockitoComponent()
    protected CrmPmProposalDao crmPmProposalDao;

    /**
     * {@link departmentAuthorizationEvaluator}
     */
    @MockitoComponent()
    protected DepartmentAuthorizationEvaluator departmentAuthorizationEvaluator;

    /**
     * {@link FunctionalAuthorization 機能アクセス権限}
     */
    @MockitoComponent()
    protected FunctionalAuthorization functionalAuthorization;

    /**
     * {@link crmCcDepartmentAthCmnLogic 組織所属_共通Logic}
     */
    @MockitoComponent()
    protected CrmCcDepartmentAthCmnLogic crmCcDepartmentAthCmnLogic;

    /**
     * {@link contextContainer}
     */
    @MockitoComponent()
    protected ContextContainer contextContainer;

    /**
     * {@link ApplicationContext}
     */
    @MockitoComponent(componentName = "applicationContext")
    protected ApplicationContext applicationContext;

    /**
     * {@link proposalLogicImpl}
     */
    private ProposalLogicImpl proposalLogicImpl;

    /**
     * CRMパラメタに関するDAO
     */
    @MockitoComponent()
    protected CrmCcParameterDao crmCcParameterDao;

    /**
     * パラメータロジック
     */
    @MockitoComponent
    protected ParameterLogic parameterLogic;

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        when(parameterLogic.getEntity("CRMCC0004")).thenReturn("0001");
        when(contextContainer.getApplicationContext()).thenReturn(
            applicationContext);
        FeatureContextImpl featureContextImpl = new FeatureContextImpl();
        featureContextImpl.setCompanyCode("0001");
        when(applicationContext.getFeatureContext()).thenReturn(
            featureContextImpl);
    }

    /**
     * CRMアカウント案件の一覧の取得のテストです。
     */
    @Test
    public void testGetEntityList() {
        ProposalCriteriaDto criteriadto = new ProposalCriteriaDto();
        PagingResult<ProposalResultDto> resultList =
            new PagingResult<ProposalResultDto>();
        ProposalResultDto proposalResultDto = new ProposalResultDto();
        proposalResultDto.competitionName = "name";
        proposalResultDto.itemCategoryCd = "0001";
        resultList.add(proposalResultDto);
        when(crmPmProposalDao.getEntityList(criteriadto))
            .thenReturn(resultList);
        PagingResult<ProposalResultDto> pagingList =
            proposalLogicImpl.getEntityList(criteriadto);
        assertEquals("name", pagingList.get(0).competitionName);
        assertEquals("0001", pagingList.get(0).itemCategoryCd);
    }

    /**
     * CRMアカウント案件の担当チェックのテストです。
     */
    @Test
    public void testCheckCharge() {
        ProposalCheckChargeDto proposalCheckChargeDto =
            new ProposalCheckChargeDto();
        proposalCheckChargeDto.crmProposalCdList.add("0001");
        List<String> resultList =
            proposalLogicImpl.checkCharge(proposalCheckChargeDto);
        List<String> str = new ArrayList<String>();
        str.add("0001");
        assertEquals(str, resultList);
    }

    /**
     * CRMアカウント案件の担当判定のテストです。
     */
    @Test
    public void testIsCharge() {
        ProposalCheckChargeDto proposalCheckChargeDto =
            new ProposalCheckChargeDto();
        proposalLogicImpl.isCharge(proposalCheckChargeDto);
    }
}
