/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

import com.biz_integral.crm.cc.application.web.accountManage.EntryAccountChargeDeptResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryAccountChargeUserResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryCampaignFilterRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryCampaignFilterResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryCompetitionFilterRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryCompetitionFilterResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryContactAddRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryContactCheckRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryContactDeleteRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryContactFilterRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryContactFilterResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryCreateDeptRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryCreateUserRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryCustomerEntryRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryCustomerSelectRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryCustomerSelectResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryDeleteCompetitionRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryDeleteRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryFixChargeDeptListRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryFixChargeUserListRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryGridChargeDeptListRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryGridChargeUserListRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryInitializeRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryInitializeResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryMapper;
import com.biz_integral.crm.cc.application.web.accountManage.EntryProposalFilterRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryProposalFilterResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntrySalesActivityFilterRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntrySalesActivityFilterResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntrySaveRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryUpdateRequestModel;
import com.biz_integral.crm.cc.domain.dto.AccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CampaignCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CampaignResultDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeResultDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.DeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ProposalCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.ProposalCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ProposalResultDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.crm.cc.domain.types.CampaignStatusType;
import com.biz_integral.crm.cc.domain.types.CompetitionType;
import com.biz_integral.crm.cc.domain.types.DeleteFlg;
import com.biz_integral.crm.cc.domain.types.ExecutionType;
import com.biz_integral.crm.cc.domain.types.SexType;
import com.biz_integral.crm.pm.domain.types.ProposalLevelType;
import com.biz_integral.crm.pm.domain.types.ProposalStageType;
import com.biz_integral.crm.sa.domain.types.InformcategoryType;
import com.biz_integral.crm.sa.domain.types.SalesactivitystatusType;
import com.biz_integral.crm.sa.domain.types.SalesactivitytypeType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.service.domain.master.BizConfigurationProvider;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link EntryMapper}の実装です。
 */
public class EntryMapperImpl implements EntryMapper {

    /**
     * {@link BeanMapper}の実装
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BizConfigurationProvider コンテキストコンテナー}
     */
    @Resource
    protected BizConfigurationProvider bizConfigurationProvider;

    /**
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountDto convertToCrmCcAccountDto(
            EntryInitializeRequestModel model) {

        CrmCcAccountDto crmCcAccountDto =
            beanMapper.map(model, CrmCcAccountDto.class);
        crmCcAccountDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcAccountDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcAccountDto.startDate = DateUtil.nowDate();
        crmCcAccountDto.endDate = DateUtil.nowDate();
        crmCcAccountDto.deleteFlag = false;
        return crmCcAccountDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void convert(CrmCcAccount crmCcAccount,
            EntryInitializeResponseModel response) {
        if (crmCcAccount != null) {
            beanMapper.map(crmCcAccount, response);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountChargeUserCriteriaDto convertToCrmCcAccountChargeUserCriteriaDto(
            EntryInitializeRequestModel request) {
        CrmCcAccountChargeUserCriteriaDto crmCCAccountChargeUserDto =
            beanMapper.map(request, CrmCcAccountChargeUserCriteriaDto.class);
        crmCCAccountChargeUserDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCCAccountChargeUserDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        return crmCCAccountChargeUserDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountChargeDeptCriteriaDto convertToCrmCcAccountChargeDeptCriteriaDto(
            EntryInitializeRequestModel request) {
        CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto =
            beanMapper.map(request, CrmCcAccountChargeDeptCriteriaDto.class);
        chargeDeptCriteriaDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        chargeDeptCriteriaDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        return chargeDeptCriteriaDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountDto convertToCrmCcAccountDto(
            EntrySaveRequestModel request) {

        CrmCcAccountDto crmCcAccountDto =
            beanMapper.map(request, CrmCcAccountDto.class);
        crmCcAccountDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcAccountDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcAccountDto.termCd = UniqueIdGenerator.getInstance().create();
        crmCcAccountDto.startDate = bizConfigurationProvider.getStartDate();
        crmCcAccountDto.endDate = bizConfigurationProvider.getEndDate();
        crmCcAccountDto.sortKey = 0L;
        crmCcAccountDto.createUserCd =
            contextContainer.getUserContext().getUserID();
        crmCcAccountDto.recordUserCd =
            contextContainer.getUserContext().getUserID();
        return crmCcAccountDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntryAccountChargeDeptResponseModel convertToChargeDeptResponseModel(
            CrmCcAccountChargeDeptResultDto dto) {
        EntryAccountChargeDeptResponseModel response =
            beanMapper.map(dto, EntryAccountChargeDeptResponseModel.class);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntryAccountChargeUserResponseModel convertToChargeUserResponseModel(
            CrmCcAccountChargeUserResultDto dto) {
        EntryAccountChargeUserResponseModel response =
            beanMapper.map(dto, EntryAccountChargeUserResponseModel.class);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountContactCriteriaDto convertToCrmCcAccountContactCriteriaDto(
            EntryContactFilterRequestModel request) {
        CrmCcAccountContactCriteriaDto dto =
            beanMapper.map(request, CrmCcAccountContactCriteriaDto.class);
        if (StringUtil.isNotEmpty(request.sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(request.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }
            dto.setFirstOrderBySpec(request.sortItem, spec);
        }
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
            EntryCompetitionFilterRequestModel request) {
        CrmCcAccountCompetitionDto dto =
            beanMapper.map(request, CrmCcAccountCompetitionDto.class);

        if (StringUtil.isNotEmpty(request.sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(request.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }
            dto.setFirstOrderBySpec(request.sortItem, spec);
        }
        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.deleteFlag = "0";
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProposalCriteriaDto convertToProposalCriteriaDto(
            EntryProposalFilterRequestModel request) {
        ProposalCriteriaDto dto =
            beanMapper.map(request, ProposalCriteriaDto.class);
        if (StringUtil.isNotEmpty(request.sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(request.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }
            dto.setFirstOrderBySpec(request.sortItem, spec);
        }
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
    public CampaignCriteriaDto convertToCampaignCriteriaDto(
            EntryCampaignFilterRequestModel request) {
        CampaignCriteriaDto dto =
            beanMapper.map(request, CampaignCriteriaDto.class);
        String sortItem = request.sortItem;
        if (StringUtil.isNotEmpty(sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(request.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }
            if ("campaignDate".equals(sortItem)) {
                sortItem = "campaignName";
            } else if ("startDate".equals(sortItem)) {
                sortItem = "periodDateFrom";
            } else if ("endDate".equals(sortItem)) {
                sortItem = "periodDateTo";
            } else if ("latestCampaignStatus".equals(sortItem)) {
                sortItem = "lateststatus";
            }
            dto.setFirstOrderBySpec(sortItem, spec);
        }
        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.localeId = contextContainer.getUserContext().getLocaleID();
        dto.accountFixedFlag = "true";
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<EntryContactFilterResponseModel> convertToContactResponseModel(
            PagingResult<CrmCcAccountContactResultDto> resultDto) {
        PagingResult<EntryContactFilterResponseModel> pagingList =
            new PagingResult<EntryContactFilterResponseModel>();
        for (CrmCcAccountContactResultDto contactResultDto : resultDto
            .getResultList()) {
            if (contactResultDto.endDate != null) {
                contactResultDto.endDate =
                    DateUtil
                        .getCalculator(contactResultDto.endDate)
                        .addDay(-1)
                        .asDate();
            }
            EntryContactFilterResponseModel model =
                beanMapper.map(
                    contactResultDto,
                    EntryContactFilterResponseModel.class);
            if (contactResultDto.sex != null) {
                model.sex =
                    this.enumNamesRegistry.getName(SexType
                        .getEnum(contactResultDto.sex), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            if (contactResultDto.deleteFlag != null) {
                model.deleteFlag =
                    this.enumNamesRegistry.getName(DeleteFlg
                        .getEnum(contactResultDto.deleteFlag), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(resultDto.getAllRecordCount());

        return pagingList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<EntryCampaignFilterResponseModel> convertToCampaignResponseModel(
            PagingResult<CampaignResultDto> resultDto) {
        PagingResult<EntryCampaignFilterResponseModel> pagingList =
            new PagingResult<EntryCampaignFilterResponseModel>();
        for (CampaignResultDto campaignResultDto : resultDto.getResultList()) {
            EntryCampaignFilterResponseModel model =
                beanMapper.map(
                    campaignResultDto,
                    EntryCampaignFilterResponseModel.class);
            if (campaignResultDto.executionType != null) {
                model.executionType =
                    this.enumNamesRegistry.getName(ExecutionType
                        .getEnum(campaignResultDto.executionType), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            if (campaignResultDto.lateststatus != null) {
                model.latestCampaignStatus =
                    this.enumNamesRegistry.getName(CampaignStatusType
                        .getEnum(campaignResultDto.lateststatus), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(resultDto.getAllRecordCount());
        return pagingList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<EntryCompetitionFilterResponseModel> convertToCompetitionResponseModel(
            PagingResult<CrmCcAccountCompetition> crmCcAccountCompetition) {
        PagingResult<EntryCompetitionFilterResponseModel> pagingList =
            new PagingResult<EntryCompetitionFilterResponseModel>();

        for (CrmCcAccountCompetition competition : crmCcAccountCompetition
            .getResultList()) {
            EntryCompetitionFilterResponseModel model =
                beanMapper.map(
                    competition,
                    EntryCompetitionFilterResponseModel.class);
            if (competition.getCompetitionLevel() != null) {
                model.competitionLevel =
                    this.enumNamesRegistry.getName(CompetitionType
                        .getEnum(competition.getCompetitionLevel()), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(crmCcAccountCompetition
            .getAllRecordCount());
        return pagingList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SalesActivityCriteriaDto converToCrmSaSalesAct(
            EntrySalesActivityFilterRequestModel request) {
        SalesActivityCriteriaDto salesActivityCriteriaDto =
            beanMapper.map(request, SalesActivityCriteriaDto.class);
        String sortItem = request.sortItem;
        if (StringUtil.isNotEmpty(sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(request.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }
            if ("salesActivityDate".equals(sortItem)) {
                sortItem = "salesActivityStartDate";
            }
            salesActivityCriteriaDto.setFirstOrderBySpec(sortItem, spec);
        }
        salesActivityCriteriaDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        salesActivityCriteriaDto.localId =
            contextContainer.getUserContext().getLocaleID();
        if ("on".equals(salesActivityCriteriaDto.salesActivityShareInfromFlag)) {
            salesActivityCriteriaDto.salesActivityShareInfromFlag = "1";
        }
        salesActivityCriteriaDto.accountFixedFlag = "true";

        return salesActivityCriteriaDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<EntrySalesActivityFilterResponseModel> convertSalesActivityResponseModel(
            PagingResult<SalesActivityResultDto> pagingResult) {

        PagingResult<EntrySalesActivityFilterResponseModel> pagingList =
            new PagingResult<EntrySalesActivityFilterResponseModel>();
        for (SalesActivityResultDto salesActivityResultDto : pagingResult) {
            EntrySalesActivityFilterResponseModel model =
                beanMapper.map(
                    salesActivityResultDto,
                    EntrySalesActivityFilterResponseModel.class);
            if (salesActivityResultDto.salesActivityStatus != null) {
                model.salesActivityStatus =
                    this.enumNamesRegistry
                        .getName(
                            SalesactivitystatusType
                                .getEnum(salesActivityResultDto.salesActivityStatus),
                            LocaleUtil.toLocale(contextContainer
                                .getUserContext()
                                .getLocaleID()));
            }
            if (salesActivityResultDto.informCategory != null) {
                model.informCategory =
                    this.enumNamesRegistry.getName(
                        InformcategoryType
                            .getEnum(salesActivityResultDto.informCategory),
                        LocaleUtil.toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            if (salesActivityResultDto.salesActivityType != null) {
                model.salesActivityType =
                    this.enumNamesRegistry.getName(
                        SalesactivitytypeType
                            .getEnum(salesActivityResultDto.salesActivityType),
                        LocaleUtil.toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(pagingResult.getAllRecordCount());
        return pagingList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<EntryProposalFilterResponseModel> convertToProposalResponseModel(
            PagingResult<ProposalResultDto> resultDto) {
        PagingResult<EntryProposalFilterResponseModel> pagingList =
            new PagingResult<EntryProposalFilterResponseModel>();
        for (ProposalResultDto proposalResultDto : resultDto) {
            EntryProposalFilterResponseModel model =
                beanMapper.map(
                    proposalResultDto,
                    EntryProposalFilterResponseModel.class);
            if (proposalResultDto.proposalLevel != null) {
                model.proposalLevel =
                    this.enumNamesRegistry.getName(ProposalLevelType
                        .getEnum(proposalResultDto.proposalLevel), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            if (proposalResultDto.proposalStage != null) {
                model.proposalStage =
                    this.enumNamesRegistry.getName(ProposalStageType
                        .getEnum(proposalResultDto.proposalStage), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(resultDto.getAllRecordCount());
        return pagingList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountChargeDeptCriteriaDto convertToCrmCcAccountChargeDeptCriteriaDto(
            EntryCreateDeptRequestModel request) {
        CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto =
            beanMapper.map(request, CrmCcAccountChargeDeptCriteriaDto.class);
        chargeDeptCriteriaDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        return chargeDeptCriteriaDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountChargeUserCriteriaDto convertToCrmCcAccountChargeUserCriteriaDto(
            EntryCreateUserRequestModel request) {
        CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto =
            beanMapper.map(request, CrmCcAccountChargeUserCriteriaDto.class);
        chargeUserCriteriaDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        return chargeUserCriteriaDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountDto convertToCrmCcAccountDto(
            EntryUpdateRequestModel request) {
        CrmCcAccountDto crmCcAccountDto =
            beanMapper.map(request, CrmCcAccountDto.class);
        crmCcAccountDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcAccountDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcAccountDto.recordUserCd =
            contextContainer.getUserContext().getUserID();
        return crmCcAccountDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountDto convertToCrmCcAccountDto(
            EntryDeleteRequestModel request) {
        CrmCcAccountDto crmCcAccountDto =
            beanMapper.map(request, CrmCcAccountDto.class);
        crmCcAccountDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcAccountDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcAccountDto.deleteFlag = true;
        crmCcAccountDto.recordUserCd =
            contextContainer.getUserContext().getUserID();
        crmCcAccountDto.recordDate = DateUtil.nowDate();
        return crmCcAccountDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountDto convertToCrmCcAccountDto(
            EntryCustomerEntryRequestModel request) {
        CrmCcAccountDto crmCcAccountDto =
            beanMapper.map(request, CrmCcAccountDto.class);
        crmCcAccountDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcAccountDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcAccountDto.recordUserCd =
            contextContainer.getUserContext().getUserID();
        return crmCcAccountDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountContactCriteriaDto> convertToAccountContactCriteriaDtoList(
            EntryContactAddRequestModel request) {

        List<AccountContactCriteriaDto> result =
            new ArrayList<AccountContactCriteriaDto>();
        for (EntryContactCheckRequestModel contactCd : request.contactCdList) {
            AccountContactCriteriaDto accountContactCriteriaDto =
                beanMapper.map(request, AccountContactCriteriaDto.class);
            accountContactCriteriaDto.companyCd =
                contextContainer
                    .getApplicationContext()
                    .getFeatureContext()
                    .getCompanyCode();
            accountContactCriteriaDto.crmContactCd = contactCd.crmContactCd;
            result.add(accountContactCriteriaDto);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerSelectCriteriaDto convertToCustomerSelectCriteriaDto(
            EntryCustomerSelectRequestModel request) {
        CustomerSelectCriteriaDto result =
            beanMapper.map(request, CustomerSelectCriteriaDto.class);
        result.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        result.localeId = contextContainer.getUserContext().getLocaleID();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntryCustomerSelectResponseModel convertToCustomerSelectResponseModel(
            CrmCcCustomerCmn entity) {
        if (entity == null) {
            return new EntryCustomerSelectResponseModel();
        }
        EntryCustomerSelectResponseModel result =
            beanMapper.map(entity, EntryCustomerSelectResponseModel.class);
        result.crmAccountName = entity.getCustomerName();
        result.crmAccountShortName = entity.getCustomerShortName();
        result.crmAccountSearchName = entity.getCustomerSearchName();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountContactCriteriaDto convertToAccountContactCriteriaDto(
            EntryContactDeleteRequestModel request) {
        AccountContactCriteriaDto criteriaDto =
            beanMapper.map(request, AccountContactCriteriaDto.class);
        criteriaDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteriaDto.recordUserCd =
            contextContainer.getUserContext().getUserID();
        return criteriaDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeleteCompetitionCriteriaDto convertToDeleteCompetitionCriteriaDto(
            EntryDeleteCompetitionRequestModel request) {
        DeleteCompetitionCriteriaDto criteriaDto =
            beanMapper.map(request, DeleteCompetitionCriteriaDto.class);
        criteriaDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteriaDto.recordUserCd =
            contextContainer.getUserContext().getUserID();
        return criteriaDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChargeDeptListChangeAfterDto> convertToChargeDeptListChangeAfterDto(
            List<EntryFixChargeDeptListRequestModel> modelList) {
        List<ChargeDeptListChangeAfterDto> resultList =
            new ArrayList<ChargeDeptListChangeAfterDto>();
        for (EntryFixChargeDeptListRequestModel model : modelList) {
            ChargeDeptListChangeAfterDto result =
                beanMapper.map(model, ChargeDeptListChangeAfterDto.class);
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChargeDeptListChangeBeforeDto> convertToChargeDeptListChangeBeforeDto(
            List<EntryGridChargeDeptListRequestModel> modelList) {
        List<ChargeDeptListChangeBeforeDto> resultList =
            new ArrayList<ChargeDeptListChangeBeforeDto>();
        for (EntryGridChargeDeptListRequestModel model : modelList) {
            ChargeDeptListChangeBeforeDto result =
                beanMapper.map(model, ChargeDeptListChangeBeforeDto.class);
            result.effectiveTermStart = model.deptEffectiveTermStart;
            result.effectiveTermEnd = model.deptEffectiveTermEnd;
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EntryAccountChargeDeptResponseModel> convertToAccountChargeDeptResponseModel(
            List<ChargeDeptListChangeResultDto> resultList) {
        List<EntryAccountChargeDeptResponseModel> modelList =
            new ArrayList<EntryAccountChargeDeptResponseModel>();

        for (ChargeDeptListChangeResultDto result : resultList) {
            EntryAccountChargeDeptResponseModel model =
                beanMapper.map(
                    result,
                    EntryAccountChargeDeptResponseModel.class);
            modelList.add(model);
        }
        return modelList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChargeUserListChangeAfterDto> convertToChargeUserListChangeAfterDto(
            List<EntryFixChargeUserListRequestModel> modelList) {
        List<ChargeUserListChangeAfterDto> resultList =
            new ArrayList<ChargeUserListChangeAfterDto>();
        for (EntryFixChargeUserListRequestModel model : modelList) {
            ChargeUserListChangeAfterDto result =
                beanMapper.map(model, ChargeUserListChangeAfterDto.class);
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChargeUserListChangeBeforeDto> convertToChargeUserListChangeBeforeDto(
            List<EntryGridChargeUserListRequestModel> modelList) {
        List<ChargeUserListChangeBeforeDto> resultList =
            new ArrayList<ChargeUserListChangeBeforeDto>();
        for (EntryGridChargeUserListRequestModel model : modelList) {
            ChargeUserListChangeBeforeDto result =
                beanMapper.map(model, ChargeUserListChangeBeforeDto.class);
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EntryAccountChargeUserResponseModel> convertToAccountChargeUserResponseModel(
            List<ChargeUserListChangeResultDto> resultList) {
        List<EntryAccountChargeUserResponseModel> modelList =
            new ArrayList<EntryAccountChargeUserResponseModel>();

        for (ChargeUserListChangeResultDto result : resultList) {
            EntryAccountChargeUserResponseModel model =
                beanMapper.map(
                    result,
                    EntryAccountChargeUserResponseModel.class);
            modelList.add(model);
        }
        return modelList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmAccountCheckChargeDto convertToCrmAccountCheckChargeDto(
            String crmAccountCd, String crmDomainCd) {
        CrmAccountCheckChargeDto crmAccountCheckChargeDto =
            new CrmAccountCheckChargeDto();
        crmAccountCheckChargeDto.userCd =
            contextContainer.getUserContext().getUserID();
        crmAccountCheckChargeDto.crmAccountCdList.add(crmAccountCd);
        crmAccountCheckChargeDto.crmDomainCd = crmDomainCd;
        crmAccountCheckChargeDto.baseDate = DateUtil.nowDate();
        return crmAccountCheckChargeDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmContactCheckChargeDto convertToCrmContactCheckChargeDto(
            String crmContactCd) {
        CrmContactCheckChargeDto crmContactCheckChargeDto =
            new CrmContactCheckChargeDto();
        crmContactCheckChargeDto.userCd =
            contextContainer.getUserContext().getUserID();
        crmContactCheckChargeDto.crmContactCdList.add(crmContactCd);
        crmContactCheckChargeDto.baseDate = DateUtil.nowDate();
        return crmContactCheckChargeDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProposalCheckChargeDto convertToProposalCheckChargeDto(
            String proposalCd) {
        ProposalCheckChargeDto proposalCheckChargeDto =
            new ProposalCheckChargeDto();
        proposalCheckChargeDto.crmProposalCdList.add(proposalCd);
        proposalCheckChargeDto.userCd =
            contextContainer.getUserContext().getUserID();
        proposalCheckChargeDto.baseDate = DateUtil.nowDate();
        return proposalCheckChargeDto;
    }
}
