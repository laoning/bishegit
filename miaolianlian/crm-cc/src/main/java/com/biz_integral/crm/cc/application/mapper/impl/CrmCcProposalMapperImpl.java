/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.CrmCcProposalRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcProposalResponseModel;
import com.biz_integral.crm.cc.application.mapper.CrmCcProposalMapper;
import com.biz_integral.crm.cc.domain.dto.CrmPmProposalDto;
import com.biz_integral.crm.pm.domain.entity.CrmPmProposal;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;

/**
 * 案件コードの一件取得で利用するマッパーの実装です。
 */
public class CrmCcProposalMapperImpl implements CrmCcProposalMapper {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}の実装です。
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmPmProposalDto convert(CrmCcProposalRequestModel model) {
        CrmPmProposalDto crmPmProposal =
            beanMapper.map(model, CrmPmProposalDto.class);

        crmPmProposal.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        return crmPmProposal;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcProposalResponseModel convert(CrmPmProposal crmPmProposal) {

        CrmCcProposalResponseModel model = new CrmCcProposalResponseModel();
        if (crmPmProposal != null) {
            model =
                beanMapper.map(crmPmProposal, CrmCcProposalResponseModel.class);
        }

        return model;
    }

}
