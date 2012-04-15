/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;

import com.biz_integral.crm.cc.application.controller.common.AccountSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.AccountSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.application.mapper.AccountDialogMapper;
import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.CrmAccountStatus;
import com.biz_integral.crm.cc.domain.types.CrmAccountType;
import com.biz_integral.crm.cc.domain.types.DeleteFlg;
import com.biz_integral.crm.cc.domain.types.ImportantLevelType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link AccountDialogMapper}の実装です。
 */
public class AccountDialogMapperImpl implements AccountDialogMapper {

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
     * {@link EnumNamesRegistry 区分に関する名称管理}
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link ParameterLogic}です
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountSearchCriteriaDto convert(
            AccountSelectDialogFilterRequestModel model) {
        AccountSearchCriteriaDto criteriaDto =
            beanMapper.map(model, AccountSearchCriteriaDto.class);

        criteriaDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteriaDto.userCode = contextContainer.getUserContext().getUserID();
        criteriaDto.localeID = contextContainer.getUserContext().getLocaleID();

        // パラメータ.CRM用組織セットコードの取得
        criteriaDto.departmentSetCd =
            parameterLogic.getEntity("CRMCC0004").toString();

        // パラメータ.CRM領域コードの取得
        criteriaDto.crmDomainCd =
            parameterLogic.getEntity("CRMCC0002").toString();

        // 簡易検索モード
        criteriaDto.easySearchMode = "1";

        String sortBy = model.sortBy;
        if (StringUtil.isNotEmpty(sortBy)) {
            boolean asc = BooleanConversionUtil.toBoolean(model.isAsc);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }

            criteriaDto.setFirstOrderBySpec(sortBy, spec);
        }

        return criteriaDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AccountSelectDialogFilterResponseModel> convert(
            PagingResult<AccountSearchResultDto> crmCcAccount) {

        PagingResult<AccountSelectDialogFilterResponseModel> pagingList =
            new PagingResult<AccountSelectDialogFilterResponseModel>();

        for (AccountSearchResultDto account : crmCcAccount.getResultList()) {
            AccountSelectDialogFilterResponseModel model =
                beanMapper.map(
                    account,
                    AccountSelectDialogFilterResponseModel.class);

            String crmAccountStatus = account.getCrmAccountStatus();
            if (crmAccountStatus != null) {
                model.crmAccountStatus =
                    enumNamesRegistry.getName(CrmAccountStatus
                        .getEnum(crmAccountStatus), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }

            String importantLevel = account.getImportantLevel();
            if (importantLevel != null) {
                model.importantLevel =
                    enumNamesRegistry.getName(ImportantLevelType
                        .getEnum(importantLevel), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }

            String crmAccountType = account.getCrmAccountType();
            if (crmAccountType != null) {
                model.crmAccountType =
                    enumNamesRegistry.getName(CrmAccountType
                        .getEnum(crmAccountType), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }

            Boolean deleteFlag = account.isDeleteFlag();
            if (deleteFlag != null) {
                if (deleteFlag) {
                    model.deleteFlag =
                        enumNamesRegistry.getName(
                            DeleteFlg.getEnum("1"),
                            LocaleUtil.toLocale(contextContainer
                                .getUserContext()
                                .getLocaleID()));
                } else {
                    model.deleteFlag =
                        enumNamesRegistry.getName(
                            DeleteFlg.getEnum("0"),
                            LocaleUtil.toLocale(contextContainer
                                .getUserContext()
                                .getLocaleID()));
                }
            }

            pagingList.add(model);
        }
        pagingList.setAllRecordCount(crmCcAccount.getAllRecordCount());

        return pagingList;
    }
}
