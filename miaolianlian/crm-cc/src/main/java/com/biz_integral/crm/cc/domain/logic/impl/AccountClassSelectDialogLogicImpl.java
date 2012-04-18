/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountClassDao;
import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogResultDto;
import com.biz_integral.crm.cc.domain.logic.AccountClassSelectDialogLogic;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類選択ダイアログのロジックの実装です。
 */
public class AccountClassSelectDialogLogicImpl implements
        AccountClassSelectDialogLogic {

    /**
     * アカウント分類DAO
     */
    @Resource
    protected CrmCcAccountClassDao crmCcAccountClassDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AccountClassSelectDialogResultDto> getAccountClassList(
            AccountClassSelectDialogCriteriaDto dto) {

        return crmCcAccountClassDao
            .findByAccountClassSelectDialogCriteriaDto(dto);
    }

}
