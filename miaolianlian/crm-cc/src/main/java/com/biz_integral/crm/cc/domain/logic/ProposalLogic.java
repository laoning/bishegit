/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmPmProposalDto;
import com.biz_integral.crm.cc.domain.dto.ProposalCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.ProposalCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ProposalResultDto;
import com.biz_integral.crm.pm.domain.entity.CrmPmProposal;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMアカウント案件のロジックです。
 */
public interface ProposalLogic {

    /**
     * CRMアカウント案件の一覧を取得します。
     * 
     * @param criteriadto
     *            CRMアカウント案件DTO
     * @return CRMアカウント案件リスト
     */
    public abstract PagingResult<ProposalResultDto> getEntityList(
            ProposalCriteriaDto criteriadto);

    /**
     * CRMアカウント案件の担当判定です。
     * 
     * @param proposalCheckChargeDto
     *            案件担当チェックDto
     */
    public abstract void isCharge(ProposalCheckChargeDto proposalCheckChargeDto);

    /**
     * CRMアカウント案件の担当チェックです。
     * 
     * @param proposalCheckChargeDto
     *            案件担当チェックDto
     * @return List<案件コード>
     */
    public abstract List<String> checkCharge(
            ProposalCheckChargeDto proposalCheckChargeDto);

    /**
     * 案件コード
     * 
     * @param dto
     *            案件コード一件取得条件
     * @return {@link CrmPmProposal}
     */
    public CrmPmProposal getEntity(CrmPmProposalDto dto);
}
