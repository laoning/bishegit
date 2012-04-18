/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.CrmCcDepartmentCmnMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcDepartmentCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.cc.domain.logic.DepartmentLogic;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 組織コードの一件取得のテストクラスです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcDepartmentCmnControllerTest {

    /**
     * テスト対象
     */
    private CrmCcDepartmentCmnController crmCcDepartmentCmnController;

    /**
     * 組織ロジック
     */
    @MockitoComponent
    protected DepartmentLogic departmentLogic;

    /**
     * 組織マッパー
     */
    @MockitoComponent
    protected CrmCcDepartmentCmnMapper crmCcDepartmentCmnMapper;

    /**
     * 組織コード検索条件モデル
     */
    protected CrmCcDepartmentCmnRequestModel model =
        new CrmCcDepartmentCmnRequestModel();

    /**
     * 組織モデル
     */
    protected CrmCcDepartmentCmnDto criteria = new CrmCcDepartmentCmnDto();

    /**
     * CrmCcDepartmentCmnエンティティ
     */
    protected CrmCcDepartmentCmn crmCcDepartmentCmn = new CrmCcDepartmentCmn();

    /**
     * 組織コード検索結果モデル
     */
    protected CrmCcDepartmentCmnResponseModel resultmodel =
        new CrmCcDepartmentCmnResponseModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        resultmodel.departmentName = "組織";
        when(crmCcDepartmentCmnMapper.convert(model)).thenReturn(criteria);
        when(departmentLogic.getEntity(criteria))
            .thenReturn(crmCcDepartmentCmn);
        when(crmCcDepartmentCmnMapper.convert(crmCcDepartmentCmn)).thenReturn(
            resultmodel);
    }

    /**
     * 組織コードの一件取得テストです。
     */
    @Test
    public void testGetDepartmentName() {
        CrmCcDepartmentCmnResponseModel result =
            crmCcDepartmentCmnController.getDepartmentName(model);

        assertEquals("組織", result.departmentName);
    }
}
