/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.CrmCcProposalMapper;
import com.biz_integral.crm.cc.domain.dto.CrmPmProposalDto;
import com.biz_integral.crm.cc.domain.logic.ProposalLogic;
import com.biz_integral.crm.pm.domain.entity.CrmPmProposal;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 案件コードの一件取得のテストクラスです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcProposalControllerTest {

    /**
     * テスト対象
     */
    private CrmCcProposalController crmCcProposalController;

    /**
     * 案件ロジック
     */
    @MockitoComponent
    protected ProposalLogic proposalLogic;

    /**
     * 案件マッパー
     */
    @MockitoComponent
    protected CrmCcProposalMapper crmPmProposalMapper;

    /**
     * 案件コードの検索条件モデル
     */
    protected CrmCcProposalRequestModel model = new CrmCcProposalRequestModel();

    /**
     * 案件モデル
     */
    protected CrmPmProposalDto criteria = new CrmPmProposalDto();

    /**
     * CrmPmProposalエンティティ
     */
    protected CrmPmProposal crmPmProposal = new CrmPmProposal();

    /**
     * 案件コードの検索結果モデル
     */
    protected CrmCcProposalResponseModel resultmodel =
        new CrmCcProposalResponseModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        resultmodel.proposalName = "案件";
        when(crmPmProposalMapper.convert(model)).thenReturn(criteria);
        when(proposalLogic.getEntity(criteria)).thenReturn(crmPmProposal);
        when(crmPmProposalMapper.convert(crmPmProposal))
            .thenReturn(resultmodel);
    }

    /**
     * 案件コードの一件取得テストです。
     */
    @Test
    public void testGetProposalName() {
        CrmCcProposalResponseModel result =
            crmCcProposalController.getProposalName(model);

        assertEquals("案件", result.proposalName);
    }
}
