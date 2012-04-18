/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.CrmCcAccountMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * CRMアカウントマの一件取得のテストクラス
 */
@RunWith(BizIntegralTest.class)
public class CrmCcAccountControllerTest {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @MockitoComponent
    protected CrmAccountLogic crmAccountLogic;

    /**
     * CRMアカウントマッパー。
     */
    @MockitoComponent
    protected CrmCcAccountMapper crmCcAccountMapper;

    /**
     * CRMアカウント検索条件モデル
     */
    protected CrmCcAccountRequestModel model = new CrmCcAccountRequestModel();

    /**
     * CRMアカウントモデル
     */
    protected CrmCcAccountDto criteria = new CrmCcAccountDto();

    /**
     * CrmCcAccountエンティティクラス
     */
    protected CrmCcAccount crmCcAccountResult = new CrmCcAccount();

    /**
     * CRMアカウント検索結果モデル
     */
    protected CrmCcAccountResponseModel resultmodel =
        new CrmCcAccountResponseModel();

    /**
     * CRMアカウントマの一件取得
     */
    protected CrmCcAccountController crmCcAccountController;

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        when(crmCcAccountMapper.convert(model)).thenReturn(criteria);

        when(crmAccountLogic.getEntity(criteria))
            .thenReturn(crmCcAccountResult);

        resultmodel.crmAccountName = "a";
        when(crmCcAccountMapper.convert(crmCcAccountResult)).thenReturn(
            resultmodel);
    }

    /**
     * 
     * RMアカウントの一件取得の処理のテスト
     */
    @Test
    public void testGetAccountName() {
        CrmCcAccountResponseModel result =
            crmCcAccountController.getAccountName(model);

        assertEquals("a", result.crmAccountName);
    }
}
