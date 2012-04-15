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

import com.biz_integral.crm.cc.domain.dao.CrmCcContactCompetitionDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcContactDao;
import com.biz_integral.crm.cc.domain.dto.ContactDeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
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
public class CrmContactCompetitionLogicImplTest {
    /**
     * CRMコンタクトに関するDAO
     */
    @MockitoComponent()
    protected CrmCcContactDao crmCcContactDao;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent()
    protected ContextContainer contextContainer;

    /**
     * {@link CrmContactLogic}
     */
    @MockitoComponent()
    protected CrmContactLogic crmContactLogic;

    /**
     * {@link BeanMapper}です
     */
    @MockitoComponent()
    protected BeanMapper beanMapper;

    /**
     * {@link CrmCcContactCompetitionDao}
     */
    @MockitoComponent(componentName = "crmCcContactCompetitionDao")
    protected CrmCcContactCompetitionDao crmCcContactCompetitionDao;

    /**
     * {@link CrmContactCompetitionLogicImpl}
     */
    private CrmContactCompetitionLogicImpl crmContactCompetitionLogicImpl;

    /**
     * {@link CrmCcContactCompetitionDto}
     */
    CrmCcContactCompetitionDto dto = new CrmCcContactCompetitionDto();

    /**
     * {@link CrmCcContactDto}
     */
    CrmCcContactDto crmCcContactDto = new CrmCcContactDto();

    /**
     * {@link CrmCcContactCompetition}
     */
    CrmCcContactCompetition crmCcContactCompetitionCreate =
        new CrmCcContactCompetition();

    /**
     * {@link CrmCcContactCompetition}
     */
    CrmCcContactCompetition crmCcContactCompetitionUpdate =
        new CrmCcContactCompetition();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        CrmCcContactCompetition result = new CrmCcContactCompetition();
        result.setCompanyCd("0001");
        result.setCompetitionId("678");
        result.setCrmContactCd("1111");
        result.setDeleteFlag(false);
        result.setTermCd("123456");
        result.setAcquirementProposalCase("");
        result.setCompetitionName("CompetitionName");
        result.setNotes("Notes");
        result.setOverview("Overview");
        when(crmCcContactCompetitionDao.getEntity(dto)).thenReturn(result);

        crmCcContactCompetitionCreate.setCompanyCd("0001");
        crmCcContactCompetitionCreate.setCompetitionId("678");
        crmCcContactCompetitionCreate.setCrmContactCd("2222");
        crmCcContactCompetitionCreate.setDeleteFlag(false);
        crmCcContactCompetitionCreate
            .setAcquirementProposalCase("ProposalCase");
        crmCcContactCompetitionCreate.setCompetitionLevel("1");
        crmCcContactCompetitionCreate.setCompetitionName("CompetitionName");
        crmCcContactCompetitionCreate.setNotes("Notes");
        crmCcContactCompetitionCreate.setOverview("Overview");
        crmCcContactCompetitionCreate.setVersionNo(0L);
        when(
            beanMapper.map(
                crmCcContactCompetitionCreate,
                CrmCcContactCompetition.class)).thenReturn(
            crmCcContactCompetitionCreate);

        crmCcContactCompetitionUpdate.setCompanyCd("0001");
        crmCcContactCompetitionUpdate.setCompetitionId("678");
        crmCcContactCompetitionUpdate.setCrmContactCd("2222");
        crmCcContactCompetitionUpdate.setDeleteFlag(false);
        crmCcContactCompetitionUpdate
            .setAcquirementProposalCase("ProposalCase2");
        crmCcContactCompetitionUpdate.setCompetitionLevel("3");
        crmCcContactCompetitionUpdate.setCompetitionName("CompetitionName2");
        crmCcContactCompetitionUpdate.setNotes("Notes2");
        crmCcContactCompetitionUpdate.setOverview("Overview2");
        crmCcContactCompetitionUpdate.setVersionNo(1L);
        when(
            beanMapper.map(
                crmCcContactCompetitionUpdate,
                CrmCcContactCompetition.class)).thenReturn(
            crmCcContactCompetitionUpdate);

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
        dto.crmContactCd = "1111";
        dto.deleteFlag = "0";
        dto.termCd = "123456";
        dto.startDate = DateUtil.parse("20100101", "yyyyMMdd");
        dto.endDate = DateUtil.parse("20100103", "yyyyMMdd");

        CrmCcContactCompetition result =
            crmContactCompetitionLogicImpl.getEntity(dto);
        // メソッド呼び出しの確認
        verify(crmCcContactCompetitionDao).getEntity(dto);
        assertEquals("678", result.getCompetitionId());
        assertEquals("1111", result.getCrmContactCd());
        CrmCcContactCompetitionDto dto = new CrmCcContactCompetitionDto();
        try {
            crmContactCompetitionLogicImpl.getEntity(dto);
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
        CrmCcContact crmCcContact = new CrmCcContact();
        when(crmContactLogic.getEntity(crmCcContactDto)).thenReturn(
            crmCcContact);
        dto.crmContactVersionNo = "1";
        crmCcContact.setVersionNo(2L);
        try {
            crmContactCompetitionLogicImpl.create(dto, crmCcContactDto);
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
        CrmCcContact crmCcContact = new CrmCcContact();
        when(crmContactLogic.getEntity(crmCcContactDto)).thenReturn(
            crmCcContact);
        dto.crmContactVersionNo = "1";
        crmCcContact.setVersionNo(2L);
        try {
            crmContactCompetitionLogicImpl.update(dto, crmCcContactDto);
            fail();
        } catch (OptimisticLockRuntimeException e) {
            assertTrue(true);
        }
    }

    /**
     * CRMコンタクト競合一覧の取得crmAccountCdチェックテストです。
     */
    @Test
    public void testGetEntityList_IllegalArgumentException() {
        CrmCcContactCompetitionCriteriaDto dto =
            new CrmCcContactCompetitionCriteriaDto();
        try {
            crmContactCompetitionLogicImpl.getEntityList(dto);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * CRMコンタクト競合一覧の取得テストです。
     */
    @Test
    public void testGetEntityList() {
        CrmCcContactCompetitionCriteriaDto dto =
            new CrmCcContactCompetitionCriteriaDto();
        dto.crmContactCd = "0001";
        PagingResult<CrmCcContactCompetition> resultList =
            new PagingResult<CrmCcContactCompetition>();
        CrmCcContactCompetition entity = new CrmCcContactCompetition();
        entity.setCompetitionId("0001");
        resultList.add(entity);
        when(crmCcContactCompetitionDao.getEntityList(dto)).thenReturn(
            resultList);
        crmContactCompetitionLogicImpl.getEntityList(dto);
        verify(crmCcContactCompetitionDao).getEntityList(dto);
        assertEquals(1, resultList.size());
        assertEquals("0001", resultList.get(0).getCompetitionId());

    }

    /**
     * CRMコンタクト競合の削除のOptimisticLockRuntimeExceptionテストです。
     */
    @Test
    public void testDelete_OptimisticLockRuntimeException() {
        ContactDeleteCompetitionCriteriaDto dto =
            new ContactDeleteCompetitionCriteriaDto();
        dto.crmContactVersionNo = "1";
        CrmCcContact crmCcContact = new CrmCcContact();
        crmCcContact.setVersionNo(2L);
        when(
            crmCcContactDao.getByPkNoException(
                dto.companyCd,
                dto.crmContactCd,
                contextContainer.getUserContext().getLocaleID(),
                dto.crmContactTermCd)).thenReturn(crmCcContact);
        try {
            crmContactCompetitionLogicImpl.delete(dto);
            fail();
        } catch (OptimisticLockRuntimeException e) {
            assertTrue(true);
        }
    }

    /**
     * CRMコンタクト競合の削除のテストです。
     */
    @Test
    public void testDelete() {
        ContactDeleteCompetitionCriteriaDto dto =
            new ContactDeleteCompetitionCriteriaDto();
        dto.crmContactVersionNo = "1";
        CrmCcContact crmCcContact = new CrmCcContact();
        crmCcContact.setVersionNo(1L);
        when(
            crmCcContactDao.getByPkNoException(
                dto.companyCd,
                dto.crmContactCd,
                contextContainer.getUserContext().getLocaleID(),
                dto.crmContactTermCd)).thenReturn(crmCcContact);
        try {
            crmContactCompetitionLogicImpl.delete(dto);
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }
}
