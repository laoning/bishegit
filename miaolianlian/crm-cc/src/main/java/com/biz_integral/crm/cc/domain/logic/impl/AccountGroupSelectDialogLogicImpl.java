/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcAcctGrpDao;
import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogResultDto;
import com.biz_integral.crm.cc.domain.logic.AccountGroupSelectDialogLogic;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウントグループ選択ダイアログのロジックの実装です。
 */
public class AccountGroupSelectDialogLogicImpl implements
        AccountGroupSelectDialogLogic {

    /**
     * アカウントグループDAO
     */
    @Resource
    protected CrmCcAcctGrpDao crmCcAcctGrpDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AccountGroupSelectDialogResultDto> getAccountGroupList(
            AccountGroupSelectDialogCriteriaDto dto) {

        return crmCcAcctGrpDao.findByAccountGroupSelectDialogCriteriaDto(dto);
    }

}
