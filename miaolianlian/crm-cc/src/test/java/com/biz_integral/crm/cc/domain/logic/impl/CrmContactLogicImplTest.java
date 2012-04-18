/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.seasar.extension.jdbc.name.PropertyName;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dao.CrmCcContactDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcCountryCmnDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcParameterDao;
import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.foundation.core.context.ApplicationContext;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.impl.FeatureContextImpl;
import com.biz_integral.foundation.core.context.impl.UserContextImpl;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.service.security.authorization.department.DepartmentAuthorizationEvaluator;
import com.biz_integral.service.security.authorization.functional.FunctionalAuthorization;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * コンタクト選択ダイアログ一覧ロジックのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class CrmContactLogicImplTest {

    /**
     * {@link FunctionalAuthorization 機能アクセス権限}
     */
    @MockitoComponent
    protected FunctionalAuthorization functionalAuthorization;

    /**
     * CRMパラメタに関するDAO
     */
    @MockitoComponent()
    protected CrmCcParameterDao crmCcParameterDao;

    /**
     * コンテキストコンテナ。
     */
    @MockitoComponent()
    protected ContextContainer contextContainer;

    /**
     * {@link ApplicationContext}
     */
    @MockitoComponent(componentName = "applicationContext")
    protected ApplicationContext applicationContext;

    /**
     * 国に関するDAO
     */
    @MockitoComponent()
    protected CrmCcCountryCmnDao crmCcCountryCmnDao;

    /**
     * CrmContactLogicImpl
     */
    private CrmContactLogicImpl crmContactLogicImpl;

    /**
     * {@link BeanMapper}です
     */
    @MockitoComponent()
    protected BeanMapper beanMapper;

    /**
     * Mockitoを利用し、生成したモックオブジェクトです。
     */
    @MockitoComponent(componentName = "crmCcContactDao")
    private CrmCcContactDao crmCcContactDao;

    /**
     * {@link departmentAuthorizationEvaluator 機能アクセス権限}
     */
    @MockitoComponent()
    protected DepartmentAuthorizationEvaluator departmentAuthorizationEvaluator;

    /**
     * パラメータロジック
     */
    @MockitoComponent
    protected ParameterLogic parameterLogic;

    /**
     * ContactSearchCriteriaDto
     */
    ContactSearchCriteriaDto dto = new ContactSearchCriteriaDto();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        PagingResult<ContactSearchResultDto> result =
            new PagingResult<ContactSearchResultDto>();

        for (int i = 0; i < 10; i++) {
            ContactSearchResultDto contactSearchResultDto =
                new ContactSearchResultDto();

            contactSearchResultDto.setEndDate(DateUtil.parse(
                "20100921",
                "yyyyMMdd"));
            contactSearchResultDto.setStartDate(DateUtil.parse(
                "20100805",
                "yyyyMMdd"));
            contactSearchResultDto.setCompanyCd("0001" + i);
            contactSearchResultDto.setCrmContactCd("1111" + i);
            contactSearchResultDto.setLocaleId("ja" + i);
            result.add(contactSearchResultDto);
        }

        when(crmCcContactDao.findByContactSearchCriteria(dto)).thenReturn(
            result);
        when(parameterLogic.getEntity("CRMCC0004")).thenReturn("0001");

        FeatureContextImpl featureContextImpl = new FeatureContextImpl();
        featureContextImpl.setCompanyCode("0001");
        when(contextContainer.getApplicationContext()).thenReturn(
            applicationContext);

        when(applicationContext.getFeatureContext()).thenReturn(
            featureContextImpl);

        UserContextImpl userContextImpl = new UserContextImpl();
        userContextImpl.setLocaleID("ja");
        userContextImpl.setUserID("aoyagi");
        when(contextContainer.getUserContext()).thenReturn(userContextImpl);
    }

    /**
     * コンタクト選択ダイアログ一件を取得するのテスト。
     */
    @Test
    public void testGetEntityList() {

        PagingResult<ContactSearchResultDto> result =
            crmContactLogicImpl.getEntityList(dto);

        // メソッド呼び出しの確認
        verify(crmCcContactDao).findByContactSearchCriteria(dto);

        for (int i = 0; i < 10; i++) {
            ContactSearchResultDto contactSearchResultDto =
                result.getResultList().get(i);

            assertEquals(
                DateUtil.parse("20100805", "yyyyMMdd"),
                contactSearchResultDto.getStartDate());
            assertEquals(
                DateUtil.parse("20100921", "yyyyMMdd"),
                contactSearchResultDto.getEndDate());
            assertEquals("1111" + i, contactSearchResultDto.getCrmContactCd());
            assertEquals("0001" + i, contactSearchResultDto.getCompanyCd());
            assertEquals("ja" + i, contactSearchResultDto.getLocaleId());
        }
    }

    /**
     * CRMコンタクトの担当判定のテストです。
     */
    @Test
    public void testCheckCharge() {
        CrmContactCheckChargeDto crmContactCheckChargeDto =
            new CrmContactCheckChargeDto();
        crmContactCheckChargeDto.crmContactCdList.add("0001");
        List<String> resultList =
            crmContactLogicImpl.checkCharge(crmContactCheckChargeDto);
        List<String> str = new ArrayList<String>();
        str.add("0001");
        assertEquals(str, resultList);
    }

    /**
     * CRMコンタクトの担当判定のテストです。
     */
    @Test
    public void testIsCharge() {
        CrmContactCheckChargeDto crmContactCheckChargeDto =
            new CrmContactCheckChargeDto();
        crmContactCheckChargeDto.crmContactCdList.add("0001");
        try {
            crmContactLogicImpl.isCharge(crmContactCheckChargeDto);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    /**
     * コンタクトの一件取得（担当判定あり）のテスト。
     */
    @Test
    public void testGetEntityByDto() {
        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();
        crmCcContactDto.crmContactCd = "0001";
        CrmCcContact crmCcContact = new CrmCcContact();
        crmCcContact.setCrmContactName("name");
        crmCcContact.setCrmContactNameKana("namekana");
        crmCcContact.setCrmContactSearchName("searchname");
        when(crmCcContactDao.getEntityByDto(crmCcContactDto)).thenReturn(
            crmCcContact);
        CrmCcContact entity = crmContactLogicImpl.getEntity(crmCcContactDto);
        assertEquals("name", entity.getCrmContactName());
        assertEquals("namekana", entity.getCrmContactNameKana());
        assertEquals("searchname", entity.getCrmContactSearchName());
    }

    /**
     * コンタクトの一件取得のテスト。
     */
    @Test
    public void testGetEntity() {
        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();
        crmCcContactDto.crmContactCd = "0001";
        CrmCcContact crmCcContact = new CrmCcContact();
        crmCcContact.setCrmContactName("name");
        crmCcContact.setCrmContactNameKana("namekana");
        crmCcContact.setCrmContactSearchName("searchname");
        when(crmCcContactDao.getEntityByDto(crmCcContactDto)).thenReturn(
            crmCcContact);
        CrmCcContact entity = crmContactLogicImpl.getEntity(crmCcContactDto);
        verify(crmCcContactDao).getEntityByDto(crmCcContactDto);
        assertEquals("name", entity.getCrmContactName());
        assertEquals("namekana", entity.getCrmContactNameKana());
        assertEquals("searchname", entity.getCrmContactSearchName());
    }

    /**
     * CRMコンタクトを登録のテスト。
     */
    @Test
    public void testCreate() {
        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();
        crmCcContactDto.crmContactCd = "0001";
        crmCcContactDto.companyCd = "0001";
        crmCcContactDto.localeId = "ja";
        crmCcContactDto.termCd = "0001";
        crmCcContactDto.newCrmContactCd = "0002";
        CrmCcContact entity = new CrmCcContact();
        when(beanMapper.map(crmCcContactDto, CrmCcContact.class)).thenReturn(
            entity);
        List<CrmCcContact> crmCcContactList = new ArrayList<CrmCcContact>();
        entity.setNewCrmContactCd("0002");
        crmCcContactList.add(entity);
        when(crmCcContactDao.findAll(LogicalDelete.AVAILABLE)).thenReturn(
            crmCcContactList);

        List<CrmCcContact> crmCcContactBefore = new ArrayList<CrmCcContact>();
        when(crmCcContactDao.getEntityForCheck(crmCcContactDto)).thenReturn(
            crmCcContactBefore);
        CrmCcContact crmCcContact = crmContactLogicImpl.create(crmCcContactDto);
        verify(crmCcContactDao).insert(entity);
        assertEquals(null, crmCcContact.getCrmContactCd());
    }

    /**
     * CRMコンタクトを更新のテスト。
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testUpdate() {
        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();
        crmCcContactDto.crmContactCd = "0001";
        crmCcContactDto.newCrmContactCd = "0002";
        CrmCcContact entity = new CrmCcContact();

        List<CrmCcContact> crmCcContactList = new ArrayList<CrmCcContact>();
        entity.setNewCrmContactCd("0002");
        crmCcContactList.add(entity);
        when(crmCcContactDao.findAll(LogicalDelete.AVAILABLE)).thenReturn(
            crmCcContactList);
        crmContactLogicImpl.update(crmCcContactDto);
        try {
            verify(crmCcContactDao).updateExcludes(
                beanMapper.map(crmCcContactDto, CrmCcContact.class),
                new PropertyName<String>("startDate"),
                new PropertyName<String>("endDate"),
                new PropertyName<String>("crmAccountAttrCd"),
                new PropertyName<String>("paymentCd"),
                new PropertyName<String>("deleteFlag"),
                new PropertyName<String>("sortKey"),
                new PropertyName<String>("createUserCd"),
                new PropertyName<String>("createDate"));
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }

    /**
     * CRMコンタクトを削除のテスト。
     */
    @Test
    public void testDelete() {
        CrmCcContactDto crmCcContactDto = new CrmCcContactDto();
        crmContactLogicImpl.delete(crmCcContactDto);
        try {
            verify(crmCcContactDao).updateIncludes(
                beanMapper.map(crmCcContactDto, CrmCcContact.class),
                new PropertyName<String>("deleteFlag"),
                new PropertyName<String>("versionNo"),
                new PropertyName<String>("recordUserCd"),
                new PropertyName<String>("recordDate"));
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }
}
