/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountCompetitionDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.DeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.crm.cc.domain.logic.CrmAccountCompetitionLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.exception.OptimisticLockRuntimeException;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CrmAccountCompetitionLogicロジックの実装です。
 */
public class CrmAccountCompetitionLogicImpl implements
        CrmAccountCompetitionLogic {

    /**
     * CRMアカウント競合に関するDAO
     */
    @Resource
    protected CrmCcAccountCompetitionDao crmCcAccountCompetitionDao;

    /**
     * CRMアカウントに関するDAO
     */
    @Resource
    protected CrmCcAccountDao crmCcAccountDao;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CrmCcAccountCompetition> getEntityList(
            CrmCcAccountCompetitionDto dto) {
        if (dto.crmAccountCd == null) {
            throw new IllegalArgumentException();
        }
        return crmCcAccountCompetitionDao.getEntityList(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(DeleteCompetitionCriteriaDto dto) {
        crmCcAccountCompetitionDao.updateDeleteCompetitionCriteria(dto);
        CrmCcAccount crmCcAccount =
            crmCcAccountDao.getByPkNoException(
                dto.companyCd,
                dto.crmAccountCd,
                contextContainer.getUserContext().getLocaleID(),
                dto.crmAccountTermCd);
        if (!dto.crmAccountVersionNo.equals(crmCcAccount
            .getVersionNo()
            .toString())) {
            throw new OptimisticLockRuntimeException(null);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @param crmCcAccountCompetitionDto
     *            crmCcAccountDto
     */
    @Override
    public void create(CrmCcAccountCompetitionDto crmCcAccountCompetitionDto,
            CrmCcAccountDto crmCcAccountDto) {
        crmCcAccountCompetitionDao.create(crmCcAccountCompetitionDto);
        // CRMアカウントの一件取得します。
        crmCcAccountDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcAccountDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcAccountDto.startDate = DateUtil.today();
        crmCcAccountDto.endDate = DateUtil.today();

        CrmCcAccount crmCcAccount =
            crmCcAccountDao.getAccountEntityWithoutDeleteFlag(crmCcAccountDto);
        // CRMアカウントの更新をチェックします。
        if (crmCcAccount != null
            && !crmCcAccountCompetitionDto.crmAccountVersionNo
                .equals(crmCcAccount.getVersionNo().toString())) {
            throw new OptimisticLockRuntimeException(null);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @param crmCcAccountCompetitionDto
     * @return crmCcAccountCompetition
     */
    @Override
    public CrmCcAccountCompetition getEntity(
            CrmCcAccountCompetitionDto crmCcAccountCompetitionDto) {

        // 競合IDの必須チェック
        if (crmCcAccountCompetitionDto.competitionId == null) {
            throw new IllegalArgumentException();
        }
        // CRMアカウント競合一件を取得します。
        CrmCcAccountCompetition crmCcAccountCompetition =
            crmCcAccountCompetitionDao.getEntity(crmCcAccountCompetitionDto);

        return crmCcAccountCompetition;
    }

    /**
     * {@inheritDoc}
     * 
     * @param crmCcAccountCompetitionDto
     *            crmCcAccountDto
     */
    @Override
    public void update(CrmCcAccountCompetitionDto crmCcAccountCompetitionDto,
            CrmCcAccountDto crmCcAccountDto) {

        crmCcAccountCompetitionDao.update(crmCcAccountCompetitionDto);
        // CRMアカウントの一件取得します。
        crmCcAccountDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcAccountDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcAccountDto.startDate = DateUtil.today();
        crmCcAccountDto.endDate = DateUtil.today();

        CrmCcAccount crmCcAccount =
            crmCcAccountDao.getAccountEntityWithoutDeleteFlag(crmCcAccountDto);
        // CRMアカウントの更新をチェックします。
        if (crmCcAccount != null
            && !crmCcAccountCompetitionDto.crmAccountVersionNo
                .equals(crmCcAccount.getVersionNo().toString())) {
            throw new OptimisticLockRuntimeException(null);
        }
    }

}
