/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.CrmCcUserCmnMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcUserCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;
import com.biz_integral.crm.cc.domain.logic.UserLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

@RunWith(BizIntegralTest.class)
public class CrmCcUserCmnControllerTest {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent
    protected UserLogic userLogic;

    /**
     * 担当者コード共通利用するマッパー
     */
    @MockitoComponent
    protected CrmCcUserCmnMapper crmCcUserCmnMapper;

    /**
     * 担当者コード検索用リクエスト
     */
    protected CrmCcUserCmnRequestModel model = new CrmCcUserCmnRequestModel();

    /**
     * 担当者コードの検索条件
     */
    protected CrmCcUserCmnDto criteria = new CrmCcUserCmnDto();

    /**
     * CrmCcUserCmnエンティティクラス
     */
    protected CrmCcUserCmn crmCcUserCmnResult = new CrmCcUserCmn();

    /**
     * 担当者コード共通検索用レスポンス
     */
    protected CrmCcUserCmnResponseModel resultmodel =
        new CrmCcUserCmnResponseModel();

    /**
     * 担当者コード共通
     */
    protected CrmCcUserCmnController crmCcUserCmnController;

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        when(crmCcUserCmnMapper.convert(model)).thenReturn(criteria);

        when(userLogic.getEntity(criteria)).thenReturn(crmCcUserCmnResult);
        resultmodel.chargeUserName = "a";
        when(crmCcUserCmnMapper.convert(crmCcUserCmnResult)).thenReturn(
            resultmodel);
    }

    /**
     * 
     * 担当者コードテキストボックスのテスト
     */
    @Test
    public void testGetUserName() {

        CrmCcUserCmnResponseModel result =
            crmCcUserCmnController.getUserName(model);
        assertEquals("a", result.chargeUserName);
    }
}
