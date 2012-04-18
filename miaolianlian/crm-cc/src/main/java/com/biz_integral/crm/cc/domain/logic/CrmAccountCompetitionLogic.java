/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.DeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMアカウント競合のロジックです。
 */
public interface CrmAccountCompetitionLogic {

    /**
     * CRMアカウント競合の一覧を取得します。
     * 
     * @param dto
     *            CRMアカウント競合DTO
     * @return CRMアカウント競合リスト
     */
    public abstract PagingResult<CrmCcAccountCompetition> getEntityList(
            CrmCcAccountCompetitionDto dto);

    /**
     * CRMアカウント競合の削除です。
     * 
     * @param dto
     *            CRMアカウント競合削除DTO
     */
    public abstract void delete(DeleteCompetitionCriteriaDto dto);

    /**
     * CRMアカウント競合の一件取得です。
     * 
     * @param crmCcAccountCompetitionDto
     *            CRMアカウント競合DTO
     * @return CRMアカウント競合
     */
    public abstract CrmCcAccountCompetition getEntity(
            CrmCcAccountCompetitionDto crmCcAccountCompetitionDto);

    /**
     * CRMアカウント競合を登録します。
     * 
     * @param crmCcAccountCompetitionDto
     * @param crmCcAccountDto
     *            CRMアカウント競合DTO CRMアカウントDTO
     */
    public abstract void create(
            CrmCcAccountCompetitionDto crmCcAccountCompetitionDto,
            CrmCcAccountDto crmCcAccountDto);

    /**
     * CRMアカウント競合を更新します。
     * 
     * @param crmCcAccountCompetitionDto
     * @param crmCcAccountDto
     *            CRMアカウント競合DTO CRMアカウントDTO
     */
    public abstract void update(
            CrmCcAccountCompetitionDto crmCcAccountCompetitionDto,
            CrmCcAccountDto crmCcAccountDto);

}
