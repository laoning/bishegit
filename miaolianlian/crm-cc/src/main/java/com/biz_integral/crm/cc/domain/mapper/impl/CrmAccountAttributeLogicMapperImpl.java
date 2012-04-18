/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AccountAttributeCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountModule;
import com.biz_integral.crm.cc.domain.mapper.CrmAccountAttributeLogicMapper;
import com.biz_integral.crm.cc.domain.types.CrmAccountStatus;
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
public class CrmAccountAttributeLogicMapperImpl implements
        CrmAccountAttributeLogicMapper {

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
    public List<CrmCcAccountAttr> convertDtoList(String companyCd,
            List<AccountAttributeCsvReaderModelDto> dtoList) {
        List<CrmCcAccountAttr> entityList = new ArrayList<CrmCcAccountAttr>();
        for (AccountAttributeCsvReaderModelDto dto : dtoList) {
            // ドメインチェックを行う
            dto.checkCrmAccountClassCd();
            // コードチェックを行う
            dto.checkDealClass();
            dto.checkCrmAccountStatus();
            dto.checkMaintenanceTargetFlag();
            dto.checkIfUpdateWay();

            // 入力値編集
            CrmCcAccountAttr entity =
                beanMapper.map(dto, CrmCcAccountAttr.class);
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
    public PagingResult<AccountAttributeSearchResultDto> convert(
            PagingResult<AccountAttributeSearchResultDto> searchResult) {

        Locale locale =
            new Locale(contextContainer.getUserContext().getLocaleID());

        MessageEnumItemResource messageEnumItemResource =
            new DefaultMessageEnumItemResource();

        PagingResult<AccountAttributeSearchResultDto> result =
            new PagingResult<AccountAttributeSearchResultDto>();
        AccountAttributeSearchResultDto model;

        for (AccountAttributeSearchResultDto search : searchResult) {
            model =
                beanMapper.map(search, AccountAttributeSearchResultDto.class);

            model.dealClassCd = model.dealClass;

            if (DealClass.getEnum(model.dealClassCd) != null) {
                model.dealClass =
                    messageEnumItemResource.getAsString(DealClass
                        .getEnum(model.dealClassCd), locale);
            }

            model.crmAccountStatusCd = model.crmAccountStatus;

            if (CrmAccountStatus.getEnum(model.crmAccountStatusCd) != null) {
                model.crmAccountStatus =
                    messageEnumItemResource.getAsString(CrmAccountStatus
                        .getEnum(model.crmAccountStatusCd), locale);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountModule convert(CrmCcAccountAttr crmCcAccountAttr) {

        CrmCcAccountModule crmCcAccountModule =
            beanMapper.map(crmCcAccountAttr, CrmCcAccountModule.class);

        crmCcAccountModule.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());

        return crmCcAccountModule;
    }

}
