/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;

import com.biz_integral.crm.cc.application.web.accountManage.SearchFilterRequestModel;
import com.biz_integral.crm.cc.application.web.accountManage.SearchFilterResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.SearchMapper;
import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.CrmAccountStatus;
import com.biz_integral.crm.cc.domain.types.CrmAccountType;
import com.biz_integral.crm.cc.domain.types.ImportantLevelType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link SearchMapper}の実装です。
 */
public class SearchMapperImpl implements SearchMapper {

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
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountSearchCriteriaDto convert(SearchFilterRequestModel model) {
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
            (String) parameterLogic.getEntity("CRMCC0004");
        // パラメータ.CRM領域コードの取得
        criteriaDto.crmDomainCd =
            (String) parameterLogic.getEntity("CRMCC0002");
        // 簡易検索モード
        criteriaDto.easySearchMode = "0";
        // 削除フラグ
        criteriaDto.deleteFlag = "0";

        if (StringUtil.isNotEmpty(model.sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(model.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }
            if ("status".equals(model.sortItem)) {
                model.sortItem = "crmAccountStatus";
            } else if ("type".equals(model.sortItem)) {
                model.sortItem = "crmAccountType";
            }
            criteriaDto.setFirstOrderBySpec(model.sortItem, spec);
        }

        return criteriaDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<SearchFilterResponseModel> convert(
            PagingResult<AccountSearchResultDto> accountSearchResultDto) {

        PagingResult<SearchFilterResponseModel> pagingList =
            new PagingResult<SearchFilterResponseModel>();

        for (AccountSearchResultDto resultDto : accountSearchResultDto
            .getResultList()) {
            SearchFilterResponseModel model =
                beanMapper.map(resultDto, SearchFilterResponseModel.class);

            String address = "";
            if (StringUtil.isNotNull(resultDto.address)) {
                address = address + resultDto.address;
            }

            if (StringUtil.isNotNull(resultDto.getAddress2())) {
                address = address + resultDto.getAddress2();
            }

            if (StringUtil.isNotNull(resultDto.getAddress3())) {
                address = address + resultDto.getAddress3();
            }

            if (StringUtil.isNotEmpty(address)) {
                model.address = address;
            }

            // 区分を取得します。
            String crmAccountType = resultDto.getCrmAccountType();
            if (crmAccountType != null) {
                model.crmAccountType =
                    enumNamesRegistry.getName(CrmAccountType
                        .getEnum(crmAccountType), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }

            // 重要度を取得します。
            String importantLevel = resultDto.getImportantLevel();
            if (importantLevel != null) {
                model.importantLevel =
                    enumNamesRegistry.getName(ImportantLevelType
                        .getEnum(importantLevel), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }

            // 状況を取得します。
            String crmAccountStatus = resultDto.getCrmAccountStatus();
            if (crmAccountStatus != null) {
                model.crmAccountStatus =
                    enumNamesRegistry.getName(CrmAccountStatus
                        .getEnum(crmAccountStatus), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }

            pagingList.add(model);
        }
        pagingList
            .setAllRecordCount(accountSearchResultDto.getAllRecordCount());

        return pagingList;
    }
}
