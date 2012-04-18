/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.CrmCcProposalMapper;
import com.biz_integral.crm.cc.domain.dto.CrmPmProposalDto;
import com.biz_integral.crm.cc.domain.logic.ProposalLogic;
import com.biz_integral.crm.pm.domain.entity.CrmPmProposal;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * 
 * 案件コードの一件取得です。
 */
public class CrmCcProposalController {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ProposalLogic proposalLogic;

    /**
     * 案件コードマップー。
     */
    @Resource
    protected CrmCcProposalMapper crmPmProposalMapper;

    /**
     * 
     * 案件コードの一件取得です。
     * 
     * @param model
     *            案件コードの検索結果のモデル
     * @return {@link CrmCcProposalResponseModel}
     */
    @Execute
    @Validation
    public CrmCcProposalResponseModel getProposalName(
            CrmCcProposalRequestModel model) {
        if (model.proposalCd == null || model.proposalCd.length() == 0) {
            return new CrmCcProposalResponseModel();
        }

        CrmPmProposalDto criteria = crmPmProposalMapper.convert(model);

        CrmPmProposal crmCcItemCmnResult = proposalLogic.getEntity(criteria);

        CrmCcProposalResponseModel resultmodel =
            crmPmProposalMapper.convert(crmCcItemCmnResult);

        return resultmodel;

    }
}
