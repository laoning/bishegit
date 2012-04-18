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

import com.biz_integral.crm.cc.domain.dao.CrmCcDepartmentCmnDao;
import com.biz_integral.crm.cc.domain.dto.DepartmentDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 組織ダイアログ一覧ロジックのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class DepartmentDialogLogicImplTest {

    /**
     * DepartmentDialogLogicImpl
     */
    private DepartmentDialogLogicImpl departmentDialogLogicImpl;

    /**
     * Mockitoを利用し、生成したモックオブジェクトです。
     */
    @MockitoComponent(componentName = "crmCcDepartmentCmnDao")
    private CrmCcDepartmentCmnDao crmCcDepartmentCmnDao;

    /**
     * DepartmentDialogCriteriaDto
     */
    DepartmentDialogCriteriaDto dto = new DepartmentDialogCriteriaDto();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        PagingResult<CrmCcDepartmentCmn> result =
            new PagingResult<CrmCcDepartmentCmn>();

        for (int i = 0; i < 10; i++) {
            dto.localeId = "ja";
            CrmCcDepartmentCmn immDepartment = new CrmCcDepartmentCmn();

            immDepartment
                .setEndDate(DateUtil.parse("20990101" + i, "yyyyMMdd"));
            immDepartment.setStartDate(DateUtil.parse(
                "20120101" + i,
                "yyyyMMdd"));
            immDepartment.setCompanyCd("0001" + i);
            immDepartment.setDepartmentCd("00000" + i);
            immDepartment.setDepartmentName("departmentName" + i);
            result.add(immDepartment);
        }

        when(crmCcDepartmentCmnDao.findByDepartmentSelectDialogCriteria(dto))
            .thenReturn(result);
        when(crmCcDepartmentCmnDao.findByDepartmentSetDialogCriteria(dto))
            .thenReturn(result);
    }

    /**
     * 組織選択ダイアログ一件を取得するのテスト。
     */
    @Test
    public void testGetEntityListTree() {

        PagingResult<CrmCcDepartmentCmn> result =
            departmentDialogLogicImpl.getEntityListTree(dto);

        // メソッド呼び出しの確認
        verify(crmCcDepartmentCmnDao).findByDepartmentSelectDialogCriteria(dto);

        for (int i = 0; i < 10; i++) {
            CrmCcDepartmentCmn immDepartment = result.getResultList().get(i);

            assertEquals("00000" + i, immDepartment.getDepartmentCd());

            assertEquals("departmentName" + i, immDepartment
                .getDepartmentName());

            assertEquals(
                DateUtil.parse("20120101" + i, "yyyyMMdd"),
                immDepartment.getStartDate());

            assertEquals(
                DateUtil.parse("20990101" + i, "yyyyMMdd"),
                immDepartment.getEndDate());
        }
    }

    /**
     * 組織設定ダイアログ一件を取得するのテスト。
     */
    @Test
    public void testGetEntityList() {

        PagingResult<CrmCcDepartmentCmn> result =
            departmentDialogLogicImpl.getEntityList(dto);

        // メソッド呼び出しの確認
        verify(crmCcDepartmentCmnDao).findByDepartmentSetDialogCriteria(dto);

        for (int i = 0; i < 10; i++) {
            CrmCcDepartmentCmn immDepartment = result.getResultList().get(i);

            assertEquals("00000" + i, immDepartment.getDepartmentCd());

            assertEquals("departmentName" + i, immDepartment
                .getDepartmentName());

            assertEquals(
                DateUtil.parse("20120101" + i, "yyyyMMdd"),
                immDepartment.getStartDate());

            assertEquals(
                DateUtil.parse("20990101" + i, "yyyyMMdd"),
                immDepartment.getEndDate());
        }
    }
}
