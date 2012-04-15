/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcCustomerCmnDao;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.crm.cc.domain.logic.ImCustomerLogic;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 取引先選択ダイアログロジックの実装です。
 */
public final class ImCustomerLogicImpl implements ImCustomerLogic {

    /**
     * 取引先選択ダイアログ一覧取得Dao。
     */
    @Resource
    protected CrmCcCustomerCmnDao crmCcCustomerCmnDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CrmCcCustomerCmn> getEntityList(
            CustomerSelectDialogCriteriaDto dto) {

        // 取引先選択ダイアログを一覧検索する。
        PagingResult<CrmCcCustomerCmn> result =
            crmCcCustomerCmnDao.findByCustomerSelectDialogCriteria(dto);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcCustomerCmn getEntity(CustomerSelectCriteriaDto criteriadto) {
        return crmCcCustomerCmnDao.getEntity(criteriadto);
    }
}
