/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.application.mapper.CrmCcCampaignMapper;
import com.biz_integral.crm.cc.domain.logic.CampaignLogic;
import com.biz_integral.crm.sc.domain.dto.CrmScCampaignDto;
import com.biz_integral.crm.sc.domain.entity.CrmScCampaign;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * キャンペーンの一件取得のテストクラスです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcCampaignControllerTest {

    /**
     * テスト対象
     */
    private CrmCcCampaignController crmCcCampaignController;

    /**
     * キャンペーンロジック
     */
    @MockitoComponent
    protected CampaignLogic campaignLogic;

    /**
     * キャンペーンマッパー
     */
    @MockitoComponent
    protected CrmCcCampaignMapper crmScCampaignMapper;

    /**
     * キャンペーンコード の検索条件モデル
     */
    protected CrmCcCampaignRequestModel model = new CrmCcCampaignRequestModel();

    /**
     * キャンペーンモデル
     */
    protected CrmScCampaignDto criteria = new CrmScCampaignDto();

    /**
     * CrmScCampaignエンティティ
     */
    protected CrmScCampaign crmScCampaign = new CrmScCampaign();

    /**
     * キャンペーンコード の検索結果モデル
     */
    protected CrmCcCampaignResponseModel resultmodel =
        new CrmCcCampaignResponseModel();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {
        resultmodel.campaignName = "キャンペーン";
        when(crmScCampaignMapper.convert(model)).thenReturn(criteria);
        when(campaignLogic.getEntity(criteria)).thenReturn(crmScCampaign);
        when(crmScCampaignMapper.convert(crmScCampaign))
            .thenReturn(resultmodel);
    }

    /**
     * キャンペーンの一件取得テストです。
     */
    @Test
    public void testGetCampaignName() {
        CrmCcCampaignResponseModel result =
            crmCcCampaignController.getCampaignName(model);

        assertEquals("キャンペーン", result.campaignName);
    }
}
