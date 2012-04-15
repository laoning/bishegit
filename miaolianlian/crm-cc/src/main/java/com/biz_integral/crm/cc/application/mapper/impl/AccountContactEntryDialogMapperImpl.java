/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.AccountContactEntryDialogInitializeRequestModel;
import com.biz_integral.crm.cc.application.controller.common.AccountContactEntryDialogInitializeResponseModel;
import com.biz_integral.crm.cc.application.controller.common.AccountContactEntryDialogUpdateRequestModel;
import com.biz_integral.crm.cc.application.mapper.AccountContactEntryDialogMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCheckItemDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.UseType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.StringUtil;

/**
 * {@link AccountContactEntryDialogMapper}
 */
public class AccountContactEntryDialogMapperImpl implements
        AccountContactEntryDialogMapper {

    /**
     * {@link BeanMapper}
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link ParameterLogic}
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountContactDto convert(
            AccountContactEntryDialogInitializeRequestModel model) {

        CrmCcAccountContactDto dto =
            beanMapper.map(model, CrmCcAccountContactDto.class);

        // 会社コードと削除フラグを設定します。
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
    public AccountContactEntryDialogInitializeResponseModel convert(
            CrmCcAccountContact crmCcAccountContact, CrmCcAccount crmCcAccount,
            CrmCcContact crmCcContact) {

        AccountContactEntryDialogInitializeResponseModel model =
            beanMapper.map(
                crmCcAccountContact,
                AccountContactEntryDialogInitializeResponseModel.class);
        // 有効期間（自）と有効期間（至）を設定します。
        model.effectiveTermFrom = crmCcAccountContact.getStartDate();
        model.effectiveTermTo = crmCcAccountContact.getEndDate();

        // CRMアカウントバージョン番号を設定します。
        if (crmCcAccount != null) {
            model.crmAccountVersionNo =
                String.valueOf(crmCcAccount.getVersionNo());
        }
        // CRMコンタクトバージョン番号を設定します。
        if (crmCcContact != null) {
            model.crmContactVersionNo =
                String.valueOf(crmCcContact.getVersionNo());
        }
        // アカウントコンタクト追加情報表示フラグを設定します。
        model.accountContactAddInfoShowFlag =
            ((UseType) parameterLogic.getEntity("CRMCC0010")).value();

        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountDto convertToCrmCcAccountDto(
            AccountContactEntryDialogInitializeRequestModel model) {

        CrmCcAccountDto dto = beanMapper.map(model, CrmCcAccountDto.class);

        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.localeId = contextContainer.getUserContext().getLocaleID();
        dto.startDate = DateUtil.nowDate();
        dto.endDate = DateUtil.nowDate();

        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcContactDto convertToCrmCcContactDto(
            AccountContactEntryDialogInitializeRequestModel model) {

        CrmCcContactDto dto = beanMapper.map(model, CrmCcContactDto.class);

        dto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        dto.localeId = contextContainer.getUserContext().getLocaleID();
        dto.startDate = DateUtil.nowDate();
        dto.endDate = DateUtil.nowDate();

        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountContactCheckItemDto convert(
            AccountContactEntryDialogUpdateRequestModel model) {

        CrmCcAccountContactCheckItemDto dto =
            beanMapper.map(model, CrmCcAccountContactCheckItemDto.class);

        dto.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        dto.setDeleteFlag(false);
        dto.setRecordUserCd(contextContainer.getUserContext().getUserID());
        if (StringUtil.isNotNull(model.startDate)) {
            dto.checkStartDate =
                DateUtil.parse(model.startDate, DateUtil.DATE_FORMAT_HYPHEN);
        }
        if (StringUtil.isNotNull(model.endDate)) {
            dto.checkEndDate =
                DateUtil.parse(model.endDate, DateUtil.DATE_FORMAT_HYPHEN);
        }
        if (StringUtil.isNotNull(model.effectiveTermFrom)) {
            dto.setStartDate(DateUtil.parse(
                model.effectiveTermFrom,
                DateUtil.DATE_FORMAT_HYPHEN));
        }
        if (StringUtil.isNotNull(model.effectiveTermTo)) {
            dto.setEndDate(DateUtil.parse(
                model.effectiveTermTo,
                DateUtil.DATE_FORMAT_HYPHEN));
        }

        return dto;
    }

}
