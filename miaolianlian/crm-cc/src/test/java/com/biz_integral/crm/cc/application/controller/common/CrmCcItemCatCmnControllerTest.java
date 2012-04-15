/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.CrmCcItemCatCmnMapper;
import com.biz_integral.crm.cc.domain.entity.CrmCcItemCatCmn;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.sa.domain.logic.ItemCatCmnLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 品目コードの一件取得のテストクラス
 */
@RunWith(BizIntegralTest.class)
public class CrmCcItemCatCmnControllerTest {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent
    protected ItemCatCmnLogic itemCatCmnLogic;

    /**
     * {@link ParameterLogic}です
     */
    @MockitoComponent
    protected ParameterLogic parameterLogic;

    /**
     * 品目コードマッパー。
     */
    @MockitoComponent
    protected CrmCcItemCatCmnMapper crmCcItemCmnMapper;

    /**
     * 品目コード一件取得条件
     */
    protected CrmCcItemCatCmn criteria = new CrmCcItemCatCmn();

    /**
     * 品目コード検索条件モデル
     */
    protected CrmCcItemCatCmnRequestModel model =
        new CrmCcItemCatCmnRequestModel();

    /**
     * CrmCcItemCatCmnエンティティクラス
     */
    protected CrmCcItemCatCmn crmCcItemCmnResult = new CrmCcItemCatCmn();

    /**
     * 品目コード検索結果モデル
     */
    protected CrmCcItemCatCmnResponseModel resultmodel =
        new CrmCcItemCatCmnResponseModel();

    /**
     * 品目コードの一件取得
     */
    protected CrmCcItemCatCmnController crmCcItemCatCmnController;

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        when(crmCcItemCmnMapper.convert(model)).thenReturn(criteria);

        when(parameterLogic.getEntity("CRMCC0005")).thenReturn("1");

        when(itemCatCmnLogic.getEntity(criteria))
            .thenReturn(crmCcItemCmnResult);
        resultmodel.itemCategoryName = "a";
        when(crmCcItemCmnMapper.convert(crmCcItemCmnResult)).thenReturn(
            resultmodel);
    }

    /**
     * 
     * 品目コードの一件取得のテスト
     */
    @Test
    public void testGetItemCategoryName() {
        CrmCcItemCatCmnResponseModel result =
            crmCcItemCatCmnController.getItemCategoryName(model);

        assertEquals("a", result.itemCategoryName);
    }
}
