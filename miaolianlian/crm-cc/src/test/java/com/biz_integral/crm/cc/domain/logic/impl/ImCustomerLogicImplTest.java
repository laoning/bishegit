/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dao.CrmCcCustomerCmnDao;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 取引先選択ダイアログ一覧ロジックのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class ImCustomerLogicImplTest {

    /**
     * ImCustomerLogicImpl
     */
    private ImCustomerLogicImpl imCustomerLogicImpl;

    /**
     * Mockitoを利用し、生成したモックオブジェクトです。
     */
    @MockitoComponent(componentName = "crmCcCustomerCmnDao")
    private CrmCcCustomerCmnDao crmCcCustomerCmnDao;

    /**
     * CustomerSelectDialogCriteriaDto
     */
    CustomerSelectDialogCriteriaDto dto = new CustomerSelectDialogCriteriaDto();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        PagingResult<CrmCcCustomerCmn> result =
            new PagingResult<CrmCcCustomerCmn>();

        for (int i = 0; i < 10; i++) {
            dto.localeId = "ja";
            CrmCcCustomerCmn immCustomer = new CrmCcCustomerCmn();

            immCustomer.setEndDate(DateUtil.parse("20990101" + i, "yyyyMMdd"));
            immCustomer
                .setStartDate(DateUtil.parse("20120101" + i, "yyyyMMdd"));
            immCustomer.setCompanyCd("0001" + i);
            immCustomer.setCustomerCd("00000" + i);
            immCustomer.setCustomerName("CustomerName" + i);
            result.add(immCustomer);
        }

        when(crmCcCustomerCmnDao.findByCustomerSelectDialogCriteria(dto))
            .thenReturn(result);
    }

    /**
     * 取引先選択ダイアログ一件を取得するのテスト。
     */
    @Test
    public void testGetEntityListTree() {

        PagingResult<CrmCcCustomerCmn> result =
            imCustomerLogicImpl.getEntityList(dto);

        // メソッド呼び出しの確認
        verify(crmCcCustomerCmnDao).findByCustomerSelectDialogCriteria(dto);

        for (int i = 0; i < 10; i++) {
            CrmCcCustomerCmn immCustomer = result.getResultList().get(i);

            assertEquals("00000" + i, immCustomer.getCustomerCd());

            assertEquals("CustomerName" + i, immCustomer.getCustomerName());

            assertEquals(
                DateUtil.parse("20120101" + i, "yyyyMMdd"),
                immCustomer.getStartDate());

            assertEquals(
                DateUtil.parse("20990101" + i, "yyyyMMdd"),
                immCustomer.getEndDate());
        }
    }

    /**
     * 取引先の一件を取得のテスト。
     */
    @Test
    public void testGetEntity() {
        CustomerSelectCriteriaDto criteriadto = new CustomerSelectCriteriaDto();
        CrmCcCustomerCmn entity = new CrmCcCustomerCmn();
        entity.setCustomerCd("0001");
        entity.setCustomerName("name");
        when(crmCcCustomerCmnDao.getEntity(criteriadto)).thenReturn(entity);
        CrmCcCustomerCmn result = imCustomerLogicImpl.getEntity(criteriadto);
        verify(crmCcCustomerCmnDao).getEntity(criteriadto);
        assertEquals("0001", result.getCustomerCd());
        assertEquals("name", result.getCustomerName());
    }
}
