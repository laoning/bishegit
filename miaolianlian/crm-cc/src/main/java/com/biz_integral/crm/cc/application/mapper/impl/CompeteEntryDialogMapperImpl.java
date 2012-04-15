/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.CompeteEntryDialogInitializeRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CompeteEntryDialogInitializeResponseModel;
import com.biz_integral.crm.cc.application.controller.common.CompeteEntryDialogRegistrationRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CompeteEntryDialogUpdateRequestModel;
import com.biz_integral.crm.cc.application.mapper.CompeteEntryDialogMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.service.domain.master.BizConfigurationProvider;

/**
 * {@link CompeteEntryDialogMapper}の実装です。
 */
public class CompeteEntryDialogMapperImpl implements CompeteEntryDialogMapper {

    /**
     * {@link BeanMapper}の実装です。
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BizConfigurationProvider}のDaoクラスです。
     */
    @Resource
    protected BizConfigurationProvider bizConfigurationProvider;

    /**
     * {@inheritDoc}
     */
    @Override
    public CompeteEntryDialogInitializeResponseModel convertToCrmCcAccountCompetitionDto(
            CrmCcAccountCompetition crmCcAccountCompetition,
            CompeteEntryDialogInitializeResponseModel model) {
        model.acquirementProposalCase =
            crmCcAccountCompetition.getAcquirementProposalCase();
        model.competitionLevel = crmCcAccountCompetition.getCompetitionLevel();
        model.competitionName = crmCcAccountCompetition.getCompetitionName();
        model.notes = crmCcAccountCompetition.getNotes();
        model.overview = crmCcAccountCompetition.getOverview();
        model.versionNo = crmCcAccountCompetition.getVersionNo().toString();
        model.termCd = crmCcAccountCompetition.getTermCd();
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompeteEntryDialogInitializeResponseModel convertToCrmCcContactCompetitionDto(
            CrmCcContactCompetition crmCcContactCompetition,
            CompeteEntryDialogInitializeResponseModel model) {
        model.acquirementProposalCase =
            crmCcContactCompetition.getAcquirementProposalCase();
        model.competitionLevel = crmCcContactCompetition.getCompetitionLevel();
        model.competitionName = crmCcContactCompetition.getCompetitionName();
        model.notes = crmCcContactCompetition.getNotes();
        model.overview = crmCcContactCompetition.getOverview();
        model.versionNo = crmCcContactCompetition.getVersionNo().toString();
        model.termCd = crmCcContactCompetition.getTermCd();
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompeteEntryDialogInitializeResponseModel convertToCrmCcAccountDto(
            CrmCcAccount crmCcAccount,
            CompeteEntryDialogInitializeResponseModel model) {
        // CRMアカウントバージョン番号を設定します。
        if (crmCcAccount != null) {
            model.crmAccountVersionNo =
                String.valueOf(crmCcAccount.getVersionNo());
        }
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompeteEntryDialogInitializeResponseModel convertToCrmCcContactDto(
            CrmCcContact crmCcContact,
            CompeteEntryDialogInitializeResponseModel model) {
        // CRMコンタクトバージョン番号を設定します。
        if (crmCcContact != null) {
            model.crmContactVersionNo =
                String.valueOf(crmCcContact.getVersionNo());
        }
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountDto convertToCrmCcAccountDto(
            CompeteEntryDialogInitializeRequestModel model) {
        CrmCcAccountDto dto = beanMapper.map(model, CrmCcAccountDto.class);

        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.localeId = contextContainer.getUserContext().getLocaleID();
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactDto convertToCrmCcContactDto(
            CompeteEntryDialogInitializeRequestModel model) {

        CrmCcContactDto dto = beanMapper.map(model, CrmCcContactDto.class);

        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.localeId = contextContainer.getUserContext().getLocaleID();
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountCompetitionDto convertToCrmCcAccountCompetitionDto(
            CompeteEntryDialogInitializeRequestModel model) {

        CrmCcAccountCompetitionDto dto =
            beanMapper.map(model, CrmCcAccountCompetitionDto.class);
        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.userCd = contextContainer.getUserContext().getUserID();
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactCompetitionDto convertToCrmCcContactCompetitionDto(
            CompeteEntryDialogInitializeRequestModel model) {

        CrmCcContactCompetitionDto dto =
            beanMapper.map(model, CrmCcContactCompetitionDto.class);
        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.userCd = contextContainer.getUserContext().getUserID();
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountCompetitionDto convertToCrmCcAccountCompetitionDto(
            CompeteEntryDialogRegistrationRequestModel model) {
        CrmCcAccountCompetitionDto dto =
            beanMapper.map(model, CrmCcAccountCompetitionDto.class);
        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.crmAccountCd = model.crmAccountCd;
        dto.startDate = bizConfigurationProvider.getStartDate();
        dto.endDate = bizConfigurationProvider.getEndDate();
        dto.competitionName = model.competitionName;
        dto.overview = model.overview;
        dto.acquirementProposalCase = model.acquirementProposalCase;
        dto.competitionLevel = model.competitionLevel;
        dto.notes = model.notes;
        dto.deleteFlag = "0";
        dto.sortKey = "0";
        dto.competitionId = UniqueIdGenerator.getInstance().create();
        dto.termCd = UniqueIdGenerator.getInstance().create();
        dto.createUserCd = contextContainer.getUserContext().getUserID();
        dto.createDate = DateUtil.nowDate();
        dto.recordUserCd = contextContainer.getUserContext().getUserID();
        dto.recordDate = DateUtil.nowDate();
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountCompetitionDto convertToCrmCcAccountCompetitionDto(
            CompeteEntryDialogUpdateRequestModel model) {
        CrmCcAccountCompetitionDto dto =
            beanMapper.map(model, CrmCcAccountCompetitionDto.class);

        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.competitionName = model.competitionName;
        dto.overview = model.overview;
        dto.acquirementProposalCase = model.acquirementProposalCase;
        dto.competitionLevel = model.competitionLevel;
        dto.notes = model.notes;
        dto.recordUserCd = contextContainer.getUserContext().getUserID();
        dto.recordDate = DateUtil.nowDate();
        dto.termCd = model.termCd;
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountDto convertToCrmCcAccountDto(
            CompeteEntryDialogRegistrationRequestModel model) {
        CrmCcAccountDto dto = beanMapper.map(model, CrmCcAccountDto.class);
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountDto convertToCrmCcAccountDto(
            CompeteEntryDialogUpdateRequestModel model) {
        CrmCcAccountDto dto = beanMapper.map(model, CrmCcAccountDto.class);
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactCompetitionDto convertToCrmCcContactCompetitionDto(
            CompeteEntryDialogRegistrationRequestModel model) {
        CrmCcContactCompetitionDto dto =
            beanMapper.map(model, CrmCcContactCompetitionDto.class);
        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.crmContactCd = model.crmContactCd;
        dto.startDate = bizConfigurationProvider.getStartDate();
        dto.endDate = bizConfigurationProvider.getEndDate();
        dto.competitionName = model.competitionName;
        dto.overview = model.overview;
        dto.acquirementProposalCase = model.acquirementProposalCase;
        dto.competitionLevel = model.competitionLevel;
        dto.notes = model.notes;
        dto.deleteFlag = "0";
        dto.sortKey = "0";
        dto.competitionId = UniqueIdGenerator.getInstance().create();
        dto.termCd = UniqueIdGenerator.getInstance().create();
        dto.createUserCd = contextContainer.getUserContext().getUserID();
        dto.createDate = DateUtil.nowDate();
        dto.recordUserCd = contextContainer.getUserContext().getUserID();
        dto.recordDate = DateUtil.nowDate();
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactCompetitionDto convertToCrmCcContactCompetitionDto(
            CompeteEntryDialogUpdateRequestModel model) {
        CrmCcContactCompetitionDto dto =
            beanMapper.map(model, CrmCcContactCompetitionDto.class);

        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.competitionName = model.competitionName;
        dto.overview = model.overview;
        dto.acquirementProposalCase = model.acquirementProposalCase;
        dto.competitionLevel = model.competitionLevel;
        dto.notes = model.notes;
        dto.recordUserCd = contextContainer.getUserContext().getUserID();
        dto.recordDate = DateUtil.nowDate();
        dto.termCd = model.termCd;
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactDto convertToCrmCcContactDto(
            CompeteEntryDialogRegistrationRequestModel model) {
        CrmCcContactDto dto = beanMapper.map(model, CrmCcContactDto.class);
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactDto convertToCrmCcContactDto(
            CompeteEntryDialogUpdateRequestModel model) {
        CrmCcContactDto dto = beanMapper.map(model, CrmCcContactDto.class);
        return dto;
    }

}
