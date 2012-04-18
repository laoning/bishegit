/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AccountClassCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.mapper.CrmAccountClassLogicMapper;
import com.biz_integral.foundation.core.mapper.BeanMapper;

/**
 * CRMアカウント分類ロジックの実装で利用するマッパーです。
 */
public class CrmAccountClassLogicMapperImpl implements
        CrmAccountClassLogicMapper {

    /**
     * {@link BeanMapper}
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountClass> convertDtoList(String companyCd,
            String localeId, List<AccountClassCsvReaderModelDto> dtoList) {
        List<CrmCcAccountClass> entityList = new ArrayList<CrmCcAccountClass>();
        for (AccountClassCsvReaderModelDto dto : dtoList) {
            // ドメインチェックを行う
            dto.checkCrmAccountClassCd();
            dto.checkCrmAccountClassName();

            // 入力値編集
            CrmCcAccountClass entity =
                beanMapper.map(dto, CrmCcAccountClass.class);
            entity.setCompanyCd(companyCd);
            entity.setLocaleId(localeId);
            entity.setDeleteFlag(false);
            entity.setSortKey(0L);
            // entity.setCreateDate(DateUtil.nowDate());
            // entity.setCreateUserCd(contextContainer.getUserContext().getUserID());
            // entity.setRecordDate(DateUtil.nowDate());
            // entity.setRecordUserCd(contextContainer.getUserContext().getUserID());
            entityList.add(entity);
        }
        return entityList;
    }
}
