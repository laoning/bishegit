/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcContactCompetitionDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcContactDao;
import com.biz_integral.crm.cc.domain.dto.ContactDeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.crm.cc.domain.logic.CrmContactCompetitionLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.exception.OptimisticLockRuntimeException;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CrmContactCompetitionLogicロジックの実装です。
 */
public class CrmContactCompetitionLogicImpl implements
        CrmContactCompetitionLogic {

    /**
     * CRMコンタクト競合に関するDAO
     */
    @Resource
    protected CrmCcContactCompetitionDao crmCcContactCompetitionDao;

    /**
     * CRMコンタクトに関するDAO
     */
    @Resource
    protected CrmCcContactDao crmCcContactDao;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CrmCcContactCompetition> getEntityList(
            CrmCcContactCompetitionCriteriaDto criteriadto) {
        if (criteriadto.crmContactCd == null) {
            throw new IllegalArgumentException();
        }
        return crmCcContactCompetitionDao.getEntityList(criteriadto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(ContactDeleteCompetitionCriteriaDto dto) {
        crmCcContactCompetitionDao.updateDeleteCompetitionCriteria(dto);
        CrmCcContact crmCcContact =
            crmCcContactDao.getByPkNoException(
                dto.companyCd,
                dto.crmContactCd,
                contextContainer.getUserContext().getLocaleID(),
                dto.crmContactTermCd);
        if (!dto.crmContactVersionNo.equals(crmCcContact
            .getVersionNo()
            .toString())) {
            throw new OptimisticLockRuntimeException(null);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @param crmCcContactCompetitionDto
     *            crmCcContactDto
     */
    @Override
    public void create(CrmCcContactCompetitionDto crmCcContactCompetitionDto,
            CrmCcContactDto crmCcContactDto) {

        crmCcContactCompetitionDao.create(crmCcContactCompetitionDto);
        // CRMコンタクトの一件取得します。
        crmCcContactDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcContactDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcContactDto.startDate = DateUtil.today();
        crmCcContactDto.endDate = DateUtil.today();
        CrmCcContact crmCcContact =
            crmCcContactDao.getEntityByDtoWithoutDeleteFlag(crmCcContactDto);
        // CRMコンタクトの更新をチェックします。
        if (crmCcContact != null
            && !crmCcContactCompetitionDto.crmContactVersionNo
                .equals(crmCcContact.getVersionNo().toString())) {
            throw new OptimisticLockRuntimeException(null);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @param crmCcContactCompetitionDto
     * @return crmCcContactCompetition
     */
    @Override
    public CrmCcContactCompetition getEntity(
            CrmCcContactCompetitionDto crmCcContactCompetitionDto) {

        // 競合IDの必須チェック
        if (crmCcContactCompetitionDto.competitionId == null) {
            throw new IllegalArgumentException();
        }
        // CRMコンタクト競合一件を取得します。
        CrmCcContactCompetition crmCcContactCompetition =
            crmCcContactCompetitionDao.getEntity(crmCcContactCompetitionDto);

        return crmCcContactCompetition;
    }

    /**
     * {@inheritDoc}
     * 
     * @param crmCcContactCompetitionDto
     *            crmCcContactDto
     */
    @Override
    public void update(CrmCcContactCompetitionDto crmCcContactCompetitionDto,
            CrmCcContactDto crmCcContactDto) {

        crmCcContactCompetitionDao.update(crmCcContactCompetitionDto);
        // CRMコンタクトの一件取得します。
        crmCcContactDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcContactDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcContactDto.startDate = DateUtil.today();
        crmCcContactDto.endDate = DateUtil.today();
        CrmCcContact crmCcContact =
            crmCcContactDao.getEntityByDtoWithoutDeleteFlag(crmCcContactDto);
        // CRMコンタクトの更新をチェックします。
        if (crmCcContact != null
            && !crmCcContactCompetitionDto.crmContactVersionNo
                .equals(crmCcContact.getVersionNo().toString())) {
            throw new OptimisticLockRuntimeException(null);

        }
    }

}
