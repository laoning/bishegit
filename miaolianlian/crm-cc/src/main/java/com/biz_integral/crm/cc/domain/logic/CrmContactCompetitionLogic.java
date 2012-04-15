/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.ContactDeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMコンタクト競合のロジックです。
 */
public interface CrmContactCompetitionLogic {

    /**
     * CRMコンタクト競合の一覧を取得します。
     * 
     * @param criteriadto
     *            CRMコンタクト競合DTO
     * @return CRMコンタクト競合リスト
     */
    public abstract PagingResult<CrmCcContactCompetition> getEntityList(
            CrmCcContactCompetitionCriteriaDto criteriadto);

    /**
     * CRMコンタクト競合の削除です。
     * 
     * @param dto
     *            CRMコンタクト競合削除DTO
     */
    public abstract void delete(ContactDeleteCompetitionCriteriaDto dto);

    /**
     * CRMアカウント競合の一件取得です。
     * 
     * @param crmCcContactCompetitionDto
     *            CRMアカウント競合DTO
     * @return CRMアカウント競合
     */
    public abstract CrmCcContactCompetition getEntity(
            CrmCcContactCompetitionDto crmCcContactCompetitionDto);

    /**
     * CRMコンタクト競合を登録します。
     * 
     * @param crmCcContactCompetitionDto
     * @param crmCcContactDto
     *            CRMアカウント競合DTO CRMアカウントDTO
     */
    public abstract void create(
            CrmCcContactCompetitionDto crmCcContactCompetitionDto,
            CrmCcContactDto crmCcContactDto);

    /**
     * CRMコンタクト競合を更新します。
     * 
     * @param crmCcContactCompetitionDto
     * @param crmCcContactDto
     *            CRMコンタクト競合DTO CRMコンタクトDTO
     */
    public abstract void update(
            CrmCcContactCompetitionDto crmCcContactCompetitionDto,
            CrmCcContactDto crmCcContactDto);

}
