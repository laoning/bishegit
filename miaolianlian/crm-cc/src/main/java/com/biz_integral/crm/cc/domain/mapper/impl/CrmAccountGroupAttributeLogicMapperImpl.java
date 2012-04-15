/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.mapper.CrmAccountGroupAttributeLogicMapper;
import com.biz_integral.crm.cc.domain.types.DealClass;
import com.biz_integral.crm.cc.domain.types.IfUpdateWay;
import com.biz_integral.crm.cc.domain.types.MaintenanceTargetFlag;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.message.MessageEnumItemResource;
import com.biz_integral.foundation.core.message.impl.DefaultMessageEnumItemResource;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMアカウント分類ロジックの実装で利用するマッパーです。
 */
public class CrmAccountGroupAttributeLogicMapperImpl implements
        CrmAccountGroupAttributeLogicMapper {

    /**
     * {@link ContextContainer コンテキストコンテナ}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountGrpAttr> convertDtoList(String companyCd,
            List<AccountGroupAttributeCsvReaderModelDto> dtoList) {
        List<CrmCcAccountGrpAttr> entityList =
            new ArrayList<CrmCcAccountGrpAttr>();
        for (AccountGroupAttributeCsvReaderModelDto dto : dtoList) {
            // ドメインチェックを行う
            dto.checkCrmAccountClassCd();
            // コードチェックを行う
            dto.checkDealClass();
            dto.checkMaintenanceTargetFlag();
            dto.checkIfUpdateWay();

            // 入力値編集
            CrmCcAccountGrpAttr entity =
                beanMapper.map(dto, CrmCcAccountGrpAttr.class);
            entity.setCompanyCd(companyCd);
            entity.setDeleteFlag(false);
            entity.setSortKey(0L);
            entityList.add(entity);
        }
        return entityList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AccountGroupAttributeSearchResultDto> convert(
            PagingResult<AccountGroupAttributeSearchResultDto> searchResult) {

        Locale locale =
            new Locale(contextContainer.getUserContext().getLocaleID());
        // String companyCd =
        // contextContainer.getCurrentFeatureContext().getCompanyCode();
        MessageEnumItemResource messageEnumItemResource =
            new DefaultMessageEnumItemResource();

        PagingResult<AccountGroupAttributeSearchResultDto> result =
            new PagingResult<AccountGroupAttributeSearchResultDto>();
        AccountGroupAttributeSearchResultDto model;

        for (AccountGroupAttributeSearchResultDto search : searchResult) {
            model =
                beanMapper.map(
                    search,
                    AccountGroupAttributeSearchResultDto.class);

            model.dealClassCd = model.dealClass;

            if (DealClass.getEnum(model.dealClassCd) != null) {
                model.dealClass =
                    messageEnumItemResource.getAsString(DealClass
                        .getEnum(model.dealClassCd), locale);
            }

            if (MaintenanceTargetFlag.getEnum(model.maintenanceTargetFlag) != null) {
                model.maintenanceTargetFlag =
                    messageEnumItemResource.getAsString(MaintenanceTargetFlag
                        .getEnum(model.maintenanceTargetFlag), locale);
            }

            if (IfUpdateWay.getEnum(model.ifUpdateWay) != null) {
                model.ifUpdateWay =
                    messageEnumItemResource.getAsString(IfUpdateWay
                        .getEnum(model.ifUpdateWay), locale);
            }
            result.add(model);
        }
        result.setAllRecordCount(searchResult.getAllRecordCount());
        result.setLimit(searchResult.getLimit());
        result.setOffset(searchResult.getOffset());

        return result;
    }
}
