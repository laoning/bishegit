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

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcCountryCmnDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcCustomerCmnDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcParameterDao;
import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
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
 * アカウント検索一覧ロジックのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class CrmAccountLogicImplTest {

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
     * 国に関するDAO
     */
    @MockitoComponent()
    protected CrmCcCountryCmnDao crmCcCountryCmnDao;

    /**
     * 取引先に関するDAO
     */
    @MockitoComponent()
    protected CrmCcCustomerCmnDao crmCcCustomerCmnDao;

    /**
     * {@link BeanMapper}です
     */
    @MockitoComponent()
    protected BeanMapper beanMapper;

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
     * {@link departmentAuthorizationEvaluator 機能アクセス権限}
     */
    @MockitoComponent()
    protected DepartmentAuthorizationEvaluator departmentAuthorizationEvaluator;

    /**
     * {@link CrmAccountLogicImpl}
     */
    private CrmAccountLogicImpl crmAccountLogicImpl;

    /**
     * Mockitoを利用し、生成したモックオブジェクトです。
     */
    @MockitoComponent()
    private CrmCcAccountDao crmCcAccountDao;

    /**
     * パラメータロジック
     */
    @MockitoComponent
    protected ParameterLogic parameterLogic;

    /**
     * {@link AccountSearchCriteriaDto}
     */
    AccountSearchCriteriaDto dto = new AccountSearchCriteriaDto();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        PagingResult<AccountSearchResultDto> result =
            new PagingResult<AccountSearchResultDto>();

        for (int i = 0; i < 10; i++) {
            AccountSearchResultDto resultDto = new AccountSearchResultDto();

            resultDto.setCrmAccountName("CRMアカウント名" + i);
            resultDto.setCrmAccountStatus("1" + i);
            resultDto.setImportantLevel("2" + i);
            resultDto.setCrmAccountType("3" + i);
            resultDto.address = String.valueOf("住所" + i);
            resultDto.setTelephoneNumber("888888888888" + i);
            resultDto.setCrmAccountCd("0001" + i);
            resultDto.userName = String.valueOf("主担当者名" + i);
            resultDto.departmentName = String.valueOf("主担当組織" + i);
            resultDto.businessStartDate =
                DateUtil.parse("20990101" + i, "yyyyMMdd");
            resultDto.contractStartDate =
                DateUtil.parse("20100101" + i, "yyyyMMdd");
            resultDto.setCrmAccountNameKana("アカウント名（カナ）" + i);
            resultDto.userCd = String.valueOf("0003" + i);
            resultDto.departmentCd = String.valueOf("0002" + i);
            resultDto.setDeleteFlag(false);
            result.add(resultDto);
        }

        when(crmCcAccountDao.findByAccountSearchCriteria(dto)).thenReturn(
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
     * アカウント検索一覧を取得するのテスト。
     */
    @Test
    public void testGetEntityList() {

        PagingResult<AccountSearchResultDto> result =
            crmAccountLogicImpl.getEntityList(dto);

        // メソッド呼び出しの確認
        verify(crmCcAccountDao).findByAccountSearchCriteria(dto);

        for (int i = 0; i < 10; i++) {
            AccountSearchResultDto resultDto = result.getResultList().get(i);

            assertEquals("CRMアカウント名" + i, resultDto.getCrmAccountName());

            assertEquals("1" + i, resultDto.getCrmAccountStatus());

            assertEquals("2" + i, resultDto.getImportantLevel());

            assertEquals("3" + i, resultDto.getCrmAccountType());

            assertEquals("住所" + i, resultDto.address);

            assertEquals("888888888888" + i, resultDto.getTelephoneNumber());

            assertEquals("0001" + i, resultDto.getCrmAccountCd());

            assertEquals("主担当者名" + i, resultDto.userName);

            assertEquals("主担当組織" + i, resultDto.departmentName);

            assertEquals(
                DateUtil.parse("20990101" + i, "yyyyMMdd"),
                resultDto.businessStartDate);

            assertEquals(
                DateUtil.parse("20100101" + i, "yyyyMMdd"),
                resultDto.contractStartDate);

            assertEquals("アカウント名（カナ）" + i, resultDto.getCrmAccountNameKana());

            assertEquals("0003" + i, resultDto.userCd);

            assertEquals("0002" + i, resultDto.departmentCd);

            assertEquals(false, resultDto.isDeleteFlag());
        }
    }

    /**
     * アカウントの一件取得のテスト。
     */
    @Test
    public void testGetEntity() {
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();
        crmCcAccountDto.crmAccountCd = "0001";
        CrmCcAccount crmCcAccount = new CrmCcAccount();
        crmCcAccount.setCrmAccountName("name");
        crmCcAccount.setCrmAccountNameKana("namekana");
        crmCcAccount.setCrmAccountSearchName("searchname");
        when(crmCcAccountDao.getAccountEntity(crmCcAccountDto)).thenReturn(
            crmCcAccount);
        CrmCcAccount entity = crmAccountLogicImpl.getEntity(crmCcAccountDto);
        verify(crmCcAccountDao).getAccountEntity(crmCcAccountDto);
        assertEquals("name", entity.getCrmAccountName());
        assertEquals("namekana", entity.getCrmAccountNameKana());
        assertEquals("searchname", entity.getCrmAccountSearchName());
    }

    /**
     * CRMアカウントの担当判定のテスト。
     */
    @Test
    public void testCheckCharge() {

        CrmAccountCheckChargeDto crmAccountCheckChargeDto =
            new CrmAccountCheckChargeDto();
        crmAccountCheckChargeDto.crmAccountCdList.add("0001");
        List<String> resultList =
            crmAccountLogicImpl.checkCharge(crmAccountCheckChargeDto);
        List<String> str = new ArrayList<String>();
        str.add("0001");
        assertEquals(str, resultList);
    }

    /**
     * CRMアカウントの担当判定のテスト。
     */
    @Test
    public void testIsCharge() {
        CrmAccountCheckChargeDto crmAccountCheckChargeDto =
            new CrmAccountCheckChargeDto();
        crmAccountCheckChargeDto.crmAccountCdList.add("0001");
        try {
            crmAccountLogicImpl.isCharge(crmAccountCheckChargeDto);
            fail();
        } catch (ValidationException e) {
            assertTrue(true);
        }

    }

    /**
     * CRMアカウントを登録のテスト。
     */
    @Test
    public void testCreate() {
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();
        crmCcAccountDto.crmAccountCd = "0001";
        crmCcAccountDto.companyCd = "0001";
        crmCcAccountDto.localeId = "ja";
        crmCcAccountDto.termCd = "0001";
        crmCcAccountDto.customerCd = "1";
        crmCcAccountDto.newCrmAccountCd = "0002";
        CrmCcAccount entity = new CrmCcAccount();
        List<CrmCcCustomerCmn> crmCcCustomerCmnList =
            new ArrayList<CrmCcCustomerCmn>();
        CrmCcCustomerCmn crmCcCustomerCmn = new CrmCcCustomerCmn();
        crmCcCustomerCmn.setCustomerCd("1");
        crmCcCustomerCmnList.add(crmCcCustomerCmn);
        when(beanMapper.map(crmCcAccountDto, CrmCcAccount.class)).thenReturn(
            entity);
        when(crmCcCustomerCmnDao.findAll(LogicalDelete.AVAILABLE)).thenReturn(
            crmCcCustomerCmnList);
        List<CrmCcAccount> crmCcAccountList = new ArrayList<CrmCcAccount>();
        entity.setNewCrmAccountCd("0002");
        crmCcAccountList.add(entity);
        when(crmCcAccountDao.findAll(LogicalDelete.AVAILABLE)).thenReturn(
            crmCcAccountList);

        List<CrmCcAccount> crmCcAccountBefore = new ArrayList<CrmCcAccount>();
        when(crmCcAccountDao.getEntityForCheck(crmCcAccountDto)).thenReturn(
            crmCcAccountBefore);
        CrmCcAccount crmCcAccount = crmAccountLogicImpl.create(crmCcAccountDto);
        verify(crmCcAccountDao).insert(entity);
        assertEquals(null, crmCcAccount.getCrmAccountCd());
    }

    /**
     * CRMアカウントを更新のテスト。
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testUpdate() {
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();
        crmCcAccountDto.crmAccountCd = "0001";
        crmCcAccountDto.newCrmAccountCd = "0002";
        crmCcAccountDto.customerCd = "1";
        CrmCcAccount entity = new CrmCcAccount();
        List<CrmCcCustomerCmn> crmCcCustomerCmnList =
            new ArrayList<CrmCcCustomerCmn>();
        CrmCcCustomerCmn crmCcCustomerCmn = new CrmCcCustomerCmn();
        crmCcCustomerCmn.setCustomerCd("1");
        crmCcCustomerCmnList.add(crmCcCustomerCmn);
        when(crmCcCustomerCmnDao.findAll(LogicalDelete.AVAILABLE)).thenReturn(
            crmCcCustomerCmnList);

        List<CrmCcAccount> crmCcAccountList = new ArrayList<CrmCcAccount>();
        entity.setNewCrmAccountCd("0002");
        crmCcAccountList.add(entity);
        when(crmCcAccountDao.findAll(LogicalDelete.AVAILABLE)).thenReturn(
            crmCcAccountList);
        crmAccountLogicImpl.update(crmCcAccountDto);
        try {
            verify(crmCcAccountDao).updateExcludes(
                beanMapper.map(crmCcAccountDto, CrmCcAccount.class),
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
     * CRMアカウントを削除のテスト。
     */
    @Test
    public void testDelete() {
        CrmCcAccountDto crmCcAccountDto = new CrmCcAccountDto();
        crmAccountLogicImpl.delete(crmCcAccountDto);
        try {
            verify(crmCcAccountDao).updateIncludes(
                beanMapper.map(crmCcAccountDto, CrmCcAccount.class),
                new PropertyName<String>("deleteFlag"),
                new PropertyName<String>("versionNo"),
                new PropertyName<String>("recordUserCd"),
                new PropertyName<String>("recordDate"));
        } catch (WantedButNotInvoked e) {
            assertTrue(true);
        }
    }
}
