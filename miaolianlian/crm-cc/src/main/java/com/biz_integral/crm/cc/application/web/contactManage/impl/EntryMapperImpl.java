/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

import com.biz_integral.crm.cc.application.web.contactManage.EntryAccountAddRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryAccountCheckRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryAccountDeleteRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryAccountFilterRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryAccountFilterResponseModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryCampaignFilterRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryCampaignFilterResponseModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryCompetitionFilterRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryCompetitionFilterResponseModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryContactChargeDeptResponseModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryContactChargeUserResponseModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryCreateDeptRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryCreateUserRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryDeleteCompetitionRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryDeleteRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryFixChargeDeptListRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryFixChargeUserListRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryGridChargeDeptListRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryGridChargeUserListRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryInitializeRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryInitializeResponseModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryMapper;
import com.biz_integral.crm.cc.application.web.contactManage.EntrySalesActivityFilterRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntrySalesActivityFilterResponseModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntrySaveRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.EntryUpdateRequestModel;
import com.biz_integral.crm.cc.domain.dto.AccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CampaignCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CampaignResultDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeResultDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeResultDto;
import com.biz_integral.crm.cc.domain.dto.ContactDeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.crm.cc.domain.types.CampaignStatusType;
import com.biz_integral.crm.cc.domain.types.CompetitionType;
import com.biz_integral.crm.cc.domain.types.CrmAccountStatus;
import com.biz_integral.crm.cc.domain.types.CrmAccountType;
import com.biz_integral.crm.cc.domain.types.DeleteFlg;
import com.biz_integral.crm.cc.domain.types.ExecutionType;
import com.biz_integral.crm.cc.domain.types.ImportantLevelType;
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
    public CrmCcContactDto convertToCrmCcContactDto(
            EntryInitializeRequestModel request) {
        CrmCcContactDto crmCcContactDto =
            beanMapper.map(request, CrmCcContactDto.class);
        crmCcContactDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcContactDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcContactDto.startDate = DateUtil.nowDate();
        crmCcContactDto.endDate = DateUtil.nowDate();
        crmCcContactDto.deleteFlag = false;
        return crmCcContactDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void convertToInitResponseModel(CrmCcContact crmCcContact,
            EntryInitializeResponseModel response) {
        if (crmCcContact != null) {
            beanMapper.map(crmCcContact, response);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactChargeUserCriteriaDto convertToCrmCcContactChargeUserCriteriaDto(
            EntryInitializeRequestModel request) {
        CrmCcContactChargeUserCriteriaDto crmCcContactChargeUserDto =
            beanMapper.map(request, CrmCcContactChargeUserCriteriaDto.class);
        crmCcContactChargeUserDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcContactChargeUserDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        return crmCcContactChargeUserDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntryContactChargeUserResponseModel convertToChargeUserResponseModel(
            CrmCcContactChargeUserResultDto dto) {
        EntryContactChargeUserResponseModel response =
            beanMapper.map(dto, EntryContactChargeUserResponseModel.class);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactChargeDeptCriteriaDto convertToCrmCcContactChargeDeptCriteriaDto(
            EntryInitializeRequestModel request) {
        CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto =
            beanMapper.map(request, CrmCcContactChargeDeptCriteriaDto.class);
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
    public EntryContactChargeDeptResponseModel convertToChargeDeptResponseModel(
            CrmCcContactChargeDeptResultDto dto) {
        EntryContactChargeDeptResponseModel response =
            beanMapper.map(dto, EntryContactChargeDeptResponseModel.class);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactChargeDeptCriteriaDto convertToCrmCcContactChargeDeptCriteriaDto(
            EntryCreateDeptRequestModel request) {
        CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto =
            beanMapper.map(request, CrmCcContactChargeDeptCriteriaDto.class);
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
    public CrmCcContactChargeUserCriteriaDto convertToCrmCcContactChargeUserCriteriaDto(
            EntryCreateUserRequestModel request) {
        CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto =
            beanMapper.map(request, CrmCcContactChargeUserCriteriaDto.class);
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
    public CrmCcContactDto convertToCrmCcContactDto(
            EntrySaveRequestModel request) {
        CrmCcContactDto crmCcContactDto =
            beanMapper.map(request, CrmCcContactDto.class);
        crmCcContactDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcContactDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcContactDto.termCd = UniqueIdGenerator.getInstance().create();
        crmCcContactDto.startDate = bizConfigurationProvider.getStartDate();
        crmCcContactDto.endDate = bizConfigurationProvider.getEndDate();
        crmCcContactDto.sortKey = 0L;
        crmCcContactDto.createUserCd =
            contextContainer.getUserContext().getUserID();
        crmCcContactDto.recordUserCd =
            contextContainer.getUserContext().getUserID();
        return crmCcContactDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactDto convertToUpdateCrmCcContactDto(
            EntryUpdateRequestModel request) {
        CrmCcContactDto crmCcContactDto =
            beanMapper.map(request, CrmCcContactDto.class);
        crmCcContactDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcContactDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcContactDto.recordUserCd =
            contextContainer.getUserContext().getUserID();
        return crmCcContactDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactDto convertToDeleteCrmCcContactDto(
            EntryDeleteRequestModel request) {
        CrmCcContactDto crmCcContactDto =
            beanMapper.map(request, CrmCcContactDto.class);
        crmCcContactDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        crmCcContactDto.localeId =
            contextContainer.getUserContext().getLocaleID();
        crmCcContactDto.deleteFlag = true;
        crmCcContactDto.recordUserCd =
            contextContainer.getUserContext().getUserID();
        crmCcContactDto.recordDate = DateUtil.nowDate();
        return crmCcContactDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactCompetitionCriteriaDto convertToCrmCcContactCompetitionCriteriaDto(
            EntryCompetitionFilterRequestModel request) {
        CrmCcContactCompetitionCriteriaDto dto =
            beanMapper.map(request, CrmCcContactCompetitionCriteriaDto.class);
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
    public PagingResult<EntryCompetitionFilterResponseModel> convertToCompetitionResponseModel(
            PagingResult<CrmCcContactCompetition> crmCcContactCompetition) {
        PagingResult<EntryCompetitionFilterResponseModel> pagingList =
            new PagingResult<EntryCompetitionFilterResponseModel>();

        for (CrmCcContactCompetition competition : crmCcContactCompetition
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
        pagingList.setAllRecordCount(crmCcContactCompetition
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
        salesActivityCriteriaDto.accountFixedFlag = "false";

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
            if ("startDate".equals(sortItem)) {
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
        dto.accountFixedFlag = "false";
        return dto;
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
    public List<EntryContactChargeDeptResponseModel> convertToContactChargeDeptResponseModel(
            List<ChargeDeptListChangeResultDto> resultList) {
        List<EntryContactChargeDeptResponseModel> modelList =
            new ArrayList<EntryContactChargeDeptResponseModel>();

        for (ChargeDeptListChangeResultDto result : resultList) {
            EntryContactChargeDeptResponseModel model =
                beanMapper.map(
                    result,
                    EntryContactChargeDeptResponseModel.class);
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
    public List<EntryContactChargeUserResponseModel> convertToContactChargeUserResponseModel(
            List<ChargeUserListChangeResultDto> resultList) {
        List<EntryContactChargeUserResponseModel> modelList =
            new ArrayList<EntryContactChargeUserResponseModel>();

        for (ChargeUserListChangeResultDto result : resultList) {
            EntryContactChargeUserResponseModel model =
                beanMapper.map(
                    result,
                    EntryContactChargeUserResponseModel.class);
            modelList.add(model);
        }
        return modelList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountContactCriteriaDto> convertToAccountContactCriteriaDtoList(
            EntryAccountAddRequestModel request) {

        List<AccountContactCriteriaDto> result =
            new ArrayList<AccountContactCriteriaDto>();
        for (EntryAccountCheckRequestModel accountCd : request.accountCdList) {
            AccountContactCriteriaDto accountContactAddCriteriaDto =
                beanMapper.map(request, AccountContactCriteriaDto.class);
            accountContactAddCriteriaDto.companyCd =
                contextContainer
                    .getApplicationContext()
                    .getFeatureContext()
                    .getCompanyCode();
            accountContactAddCriteriaDto.crmAccountCd = accountCd.crmAccountCd;
            result.add(accountContactAddCriteriaDto);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountContactCriteriaDto convertToCrmCcAccountContactCriteriaDto(
            EntryAccountFilterRequestModel request) {
        CrmCcAccountContactCriteriaDto dto =
            beanMapper.map(request, CrmCcAccountContactCriteriaDto.class);
        String sortItem = request.sortItem;
        if (StringUtil.isNotEmpty(sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(request.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }
            if ("address".equals(sortItem)) {
                sortItem = "address1";
            }

            dto.setFirstOrderBySpec(sortItem, spec);
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
    public PagingResult<EntryAccountFilterResponseModel> convertToAccountFilterResponseModel(
            PagingResult<CrmCcAccountContactResultDto> resultDto) {
        PagingResult<EntryAccountFilterResponseModel> pagingList =
            new PagingResult<EntryAccountFilterResponseModel>();
        for (CrmCcAccountContactResultDto accountResultDto : resultDto
            .getResultList()) {
            if (accountResultDto.endDate != null) {
                accountResultDto.endDate =
                    DateUtil
                        .getCalculator(accountResultDto.endDate)
                        .addDay(-1)
                        .asDate();
            }
            EntryAccountFilterResponseModel model =
                beanMapper.map(
                    accountResultDto,
                    EntryAccountFilterResponseModel.class);
            if (accountResultDto.status != null) {
                model.status =
                    this.enumNamesRegistry.getName(CrmAccountStatus
                        .getEnum(accountResultDto.status), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            if (accountResultDto.importantLevel != null) {
                model.importantLevel =
                    this.enumNamesRegistry.getName(ImportantLevelType
                        .getEnum(accountResultDto.importantLevel), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            if (accountResultDto.type != null) {
                model.type =
                    this.enumNamesRegistry.getName(CrmAccountType
                        .getEnum(accountResultDto.type), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            if (accountResultDto.deleteFlag != null) {
                model.deleteFlag =
                    this.enumNamesRegistry.getName(DeleteFlg
                        .getEnum(accountResultDto.deleteFlag), LocaleUtil
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
    public AccountContactCriteriaDto convertToAccountContactCriteriaDto(
            EntryAccountDeleteRequestModel request) {
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
    public ContactDeleteCompetitionCriteriaDto convertToContactDeleteCompetitionCriteriaDto(
            EntryDeleteCompetitionRequestModel request) {
        ContactDeleteCompetitionCriteriaDto criteriaDto =
            beanMapper.map(request, ContactDeleteCompetitionCriteriaDto.class);
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
    public CrmAccountCheckChargeDto convertToCrmAccountCheckChargeDto(
            EntryAccountCheckRequestModel request) {
        CrmAccountCheckChargeDto crmAccountCheckChargeDto =
            new CrmAccountCheckChargeDto();
        crmAccountCheckChargeDto.userCd =
            contextContainer.getUserContext().getUserID();
        crmAccountCheckChargeDto.crmAccountCdList.add(request.crmAccountCd);
        crmAccountCheckChargeDto.crmDomainCd = request.crmDomainCd;
        crmAccountCheckChargeDto.baseDate = DateUtil.nowDate();
        return crmAccountCheckChargeDto;
    }

}
