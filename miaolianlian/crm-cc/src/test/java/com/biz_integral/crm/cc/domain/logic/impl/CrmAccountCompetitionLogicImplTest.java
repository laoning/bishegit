/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountCompetitionDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.DeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.exception.OptimisticLockRuntimeException;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 競合登録／更新ダイアログロジックのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class CrmAccountCompetitionLogicImplTest {

    /**
     * CRMアカウントに関するDAO
     */
    @MockitoComponent()
    protected CrmCcAccountDao crmCcAccountDao;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent()
    protected ContextContainer contextContainer;

    /**
     * {@link CrmAccountLogic}
     */
    @MockitoComponent()
    protected CrmAccountLogic crmAccountLogic;

    /**
     * {@link BeanMapper}です
     */
    @MockitoComponent()
    protected BeanMapper beanMapper;

    /**
     * {@link CrmCcAccountCompetitionDao}
     */
    @MockitoComponent(componentName = "crmCcAccountCompetitionDao")
    protected CrmCcAccountCompetitionDao crmCcAccountCompetitionDao;

    /**
     * {@link CrmAccountCompetitionLogicImpl}
     */
    private CrmAccountCompetitionLogicImpl crmAccountCompetitionLogicImpl;

    /**
     * {@link CrmCcAccountCompetitionDto}
     */
    CrmCcAccountCompetitionDto dto = new CrmCcAccountCompetitionDto();

    /**
     * {@link CrmCcAccountDto}
     */
    CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();

    /**
     * {@link CrmCcAccountCompetition}
     */
    CrmCcAccountCompetition crmCcAccountCompetitionCreate =
        new CrmCcAccountCompetition();

    /**
     * {@link CrmCcAccountCompetition}
     */
    CrmCcAccountCompetition crmCcAccountCompetitionUpdate =
        new CrmCcAccountCompetition();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        CrmCcAccountCompetition result = new CrmCcAccountCompetition();
        result.setCompanyCd("0001");
        result.setCompetitionId("678");
        result.setCrmAccountCd("2222");
        result.setDeleteFlag(false);
        result.setTermCd("123456");
        result.setAcquirementProposalCase("");
        result.setCompetitionName("CompetitionName");
        result.setNotes("Notes");
        result.setOverview("Overview");
        when(crmCcAccountCompetitionDao.getEntity(dto)).thenReturn(result);

        crmCcAccountCompetitionCreate.setCompanyCd("0001");
        crmCcAccountCompetitionCreate.setCompetitionId("678");
        crmCcAccountCompetitionCreate.setCrmAccountCd("2222");
        crmCcAccountCompetitionCreate.setDeleteFlag(false);
        crmCcAccountCompetitionCreate
            .setAcquirementProposalCase("ProposalCase");
        crmCcAccountCompetitionCreate.setCompetitionLevel("1");
        crmCcAccountCompetitionCreate.setCompetitionName("CompetitionName");
        crmCcAccountCompetitionCreate.setNotes("Notes");
        crmCcAccountCompetitionCreate.setOverview("Overview");
        crmCcAccountCompetitionCreate.setVersionNo(0L);
        when(
            beanMapper.map(
                crmCcAccountCompetitionCreate,
                CrmCcAccountCompetition.class)).thenReturn(
            crmCcAccountCompetitionCreate);

        crmCcAccountCompetitionUpdate.setCompanyCd("0001");
        crmCcAccountCompetitionUpdate.setCompetitionId("678");
        crmCcAccountCompetitionUpdate.setCrmAccountCd("2222");
        crmCcAccountCompetitionUpdate.setDeleteFlag(false);
        crmCcAccountCompetitionUpdate
            .setAcquirementProposalCase("ProposalCase2");
        crmCcAccountCompetitionUpdate.setCompetitionLevel("3");
        crmCcAccountCompetitionUpdate.setCompetitionName("CompetitionName2");
        crmCcAccountCompetitionUpdate.setNotes("Notes2");
        crmCcAccountCompetitionUpdate.setOverview("Overview2");
        crmCcAccountCompetitionUpdate.setVersionNo(1L);
        when(
            beanMapper.map(
                crmCcAccountCompetitionUpdate,
                CrmCcAccountCompetition.class)).thenReturn(
            crmCcAccountCompetitionUpdate);

        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setLocaleID("ja");
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);
    }

    /**
     * コンタクト選択ダイアログ一件を取得するのテスト。
     */
    @Test
    public void testGetEntity() {
        dto.companyCd = "0001";
        dto.competitionId = "678";
        dto.crmAccountCd = "2222";
        dto.deleteFlag = "0";
        dto.termCd = "123456";
        dto.startDate = DateUtil.parse("20100101", "yyyyMMdd");
        dto.endDate = DateUtil.parse("20100103", "yyyyMMdd");

        CrmCcAccountCompetition result =
            crmAccountCompetitionLogicImpl.getEntity(dto);
        // メソッド呼び出しの確認
        verify(crmCcAccountCompetitionDao).getEntity(dto);
        assertEquals("678", result.getCompetitionId());
        assertEquals("2222", result.getCrmAccountCd());
        CrmCcAccountCompetitionDto dto = new CrmCcAccountCompetitionDto();
        try {
            crmAccountCompetitionLogicImpl.getEntity(dto);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * 競合登録／更新ダイアログの登録の処理のテスト<br>
     */
    @Test
    public void testCreate_OptimisticLockRuntimeException() {
        CrmCcAccount crmCcAccount = new CrmCcAccount();
        when(crmAccountLogic.getEntity(crmCcAccountDto)).thenReturn(
            crmCcAccount);
        dto.crmAccountVersionNo = "1";
        crmCcAccount.setVersionNo(2L);
        try {
            crmAccountCompetitionLogicImpl.create(dto, crmCcAccountDto);
            fail();
        } catch (OptimisticLockRuntimeException e) {
            assertTrue(true);
        }
    }

    /**
     * 競合登録／更新ダイアログの更新の処理のテスト<br>
     */
    @Test
    public void testUpdate_OptimisticLockRuntimeException() {
        CrmCcAccount crmCcAccount = new CrmCcAccount();
        when(crmAccountLogic.getEntity(crmCcAccountDto)).thenReturn(
            crmCcAccount);
        dto.crmAccountVersionNo = "1";
        crmCcAccount.setVersionNo(2L);
        try {
            crmAccountCompetitionLogicImpl.update(dto, crmCcAccountDto);
            fail();
        } catch (OptimisticLockRuntimeException e) {
            assertTrue(true);
        }
    }

    /**
     * CRMアカウント競合一覧の取得crmAccountCdチェックテストです。
     */
    @Test
    public void testGetEntityList_IllegalArgumentException() {
        CrmCcAccountCompetitionDto dto = new CrmCcAccountCompetitionDto();
        try {
            crmAccountCompetitionLogicImpl.getEntityList(dto);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * CRMアカウント競合一覧の取得テストです。
     */
    @Test
    public void testGetEntityList() {
        CrmCcAccountCompetitionDto dto = new CrmCcAccountCompetitionDto();
        dto.crmAccountCd = "0001";
        PagingResult<CrmCcAccountCompetition> resultList =
            new PagingResult<CrmCcAccountCompetition>();
        CrmCcAccountCompetition entity = new CrmCcAccountCompetition();
        entity.setCompetitionId("0001");
        resultList.add(entity);
        when(crmCcAccountCompetitionDao.getEntityList(dto)).thenReturn(
            resultList);
        crmAccountCompetitionLogicImpl.getEntityList(dto);
        verify(crmCcAccountCompetitionDao).getEntityList(dto);
        assertEquals(1, resultList.size());
        assertEquals("0001", resultList.get(0).getCompetitionId());

    }

    /**
     * CRMアカウント競合の削除のOptimisticLockRuntimeExceptionテストです。
     */
    @Test
    public void testDelete_OptimisticLockRuntimeException() {
        DeleteCompetitionCriteriaDto dto = new DeleteCompetitionCriteriaDto();
        dto.crmAccountVersionNo = "1";
        CrmCcAccount crmCcAccount = new CrmCcAccount();
        crmCcAccount.setVersionNo(2L);
        when(
            crmCcAccountDao.getByPkNoException(
                dto.companyCd,
                dto.crmAccountCd,
                contextContainer.getUserContext().getLocaleID(),
                dto.crmAccountTermCd)).thenReturn(crmCcAccount);
        try {
            crmAccountCompetitionLogicImpl.delete(dto);
            fail();
        } catch (OptimisticLockRuntimeException e) {
            assertTrue(true);
        }
    }

    /**
     * CRMアカウント競合の削除のテストです。
     */
    @Test
    public void testDelete() {
        DeleteCompetitionCriteriaDto dto = new DeleteCompetitionCriteriaDto();
        dto.crmAccountVersionNo = "1";
        CrmCcAccount crmCcAccount = new CrmCcAccount();
        crmCcAccount.setVersionNo(1L);
        when(
            crmCcAccountDao.getByPkNoException(
                dto.companyCd,
                dto.crmAccountCd,
                contextContainer.getUserContext().getLocaleID(),
                dto.crmAccountTermCd)).thenReturn(crmCcAccount);
        try {
            crmAccountCompetitionLogicImpl.delete(dto);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }
}
