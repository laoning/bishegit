/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.CrmCcContactMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * CRMコンタクトの一件取得のテストクラスです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcContactControllerTest {

    /**
     * テスト対象
     */
    private CrmCcContactController crmCcContactController;

    /**
     * CRMコンタクトロジック
     */
    @MockitoComponent
    protected CrmContactLogic crmContactLogic;

    /**
     * CRMコンタクトマッパー
     */
    @MockitoComponent
    protected CrmCcContactMapper crmCcContactMapper;

    /**
     * CRMコンタクト検索条件モデル
     */
    protected CrmCcContactRequestModel model = new CrmCcContactRequestModel();

    /**
     * CRMコンタクトモデル
     */
    protected CrmCcContactDto criteria = new CrmCcContactDto();

    /**
     * CrmCcContactエンティティ
     */
    protected CrmCcContact crmCcContact = new CrmCcContact();

    /**
     * CRMコンタクトの検索結果モデル
     */
    protected CrmCcContactResponseModel resultmodel =
        new CrmCcContactResponseModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        resultmodel.crmContactName = "コンタクト";
        when(crmCcContactMapper.convert(model)).thenReturn(criteria);
        when(crmContactLogic.getEntity(criteria)).thenReturn(crmCcContact);
        when(crmCcContactMapper.convert(crmCcContact)).thenReturn(resultmodel);
    }

    /**
     * CRMコンタクトの一件取得テストです。
     */
    @Test
    public void testGetContactName() {
        CrmCcContactResponseModel result =
            crmCcContactController.getContactName(model);

        assertEquals("コンタクト", result.crmContactName);
    }
}
