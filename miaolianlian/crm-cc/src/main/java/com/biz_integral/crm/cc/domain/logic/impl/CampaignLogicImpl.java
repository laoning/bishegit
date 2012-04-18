/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.CampaignCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CampaignResultDto;
import com.biz_integral.crm.cc.domain.logic.CampaignLogic;
import com.biz_integral.crm.sc.domain.dao.CrmScCampaignDao;
import com.biz_integral.crm.sc.domain.dto.CrmScCampaignDto;
import com.biz_integral.crm.sc.domain.entity.CrmScCampaign;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CampaignLogicロジックの実装です。
 */
public class CampaignLogicImpl implements CampaignLogic {

    /**
     * キャンペーンに関するDAO
     */
    @Resource
    protected CrmScCampaignDao crmScCampaignDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CampaignResultDto> getEntityList(
            CampaignCriteriaDto criteriadto) {

        return crmScCampaignDao.getEntityList(criteriadto);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmScCampaign getEntity(CrmScCampaignDto dto) {

        if (dto.campaignCd == null) {
            throw new IllegalArgumentException();
        }

        if (dto.companyCd == null) {
            throw new IllegalArgumentException();
        }
        return crmScCampaignDao.getEntity(dto);
    }

}
