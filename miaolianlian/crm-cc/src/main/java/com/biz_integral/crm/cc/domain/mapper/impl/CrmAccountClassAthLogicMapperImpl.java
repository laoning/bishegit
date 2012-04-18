/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;
import com.biz_integral.crm.cc.domain.mapper.CrmAccountClassAthLogicMapper;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * アカウント分類所属ロジックの実装で利用するマッパーです。
 */
public class CrmAccountClassAthLogicMapperImpl implements
        CrmAccountClassAthLogicMapper {

    /**
     * {@link ContextContainer コンテキストコンテナ}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountClassAth> convertDtlList(
            List<CrmCcAccountClassAthCsvReaderModelDto> dtoList) {

        String companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        List<CrmCcAccountClassAth> entityList =
            new ArrayList<CrmCcAccountClassAth>();

        for (CrmCcAccountClassAthCsvReaderModelDto dto : dtoList) {

            // ドメインチェックを行う
            dto.checkCrmAccountClassCd();
            dto.checkCrmAccountCd();

            CrmCcAccountClassAth entity = new CrmCcAccountClassAth();
            entity.setCompanyCd(companyCd);
            entity.setCrmAccountClassCd(dto.crmAccountClassCd);
            entity.setCrmAccountCd(dto.crmAccountCd);
            entityList.add(entity);
        }
        return entityList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountDto convertCrmCcAccount(CrmCcAccountClassAth entity) {
        CrmCcAccountDto dto = new CrmCcAccountDto();
        dto.crmAccountCd = entity.getCrmAccountCd();
        return dto;
    }

}
