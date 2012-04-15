/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.CampaignCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CampaignResultDto;
import com.biz_integral.crm.sc.domain.dto.CrmScCampaignDto;
import com.biz_integral.crm.sc.domain.entity.CrmScCampaign;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * キャンペーンのロジックです。
 */
public interface CampaignLogic {

    /**
     * キャンペーンの一覧を取得します。
     * 
     * @param criteriadto
     *            キャンペーンDTO
     * @return キャンペーンリスト
     */
    public abstract PagingResult<CampaignResultDto> getEntityList(
            CampaignCriteriaDto criteriadto);

    /**
     * 
     * キャンペーン
     * 
     * @param dto
     *            キャンペーン一件取得条件
     * @return {@link CrmScCampaign}
     */
    public CrmScCampaign getEntity(CrmScCampaignDto dto);

}
