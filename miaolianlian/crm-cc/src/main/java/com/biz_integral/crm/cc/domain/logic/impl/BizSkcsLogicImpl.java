/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcSkcsCmnDao;
import com.biz_integral.crm.cc.domain.dto.AddressSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcSkcsCmn;
import com.biz_integral.crm.cc.domain.logic.BizSkcsLogic;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 住所選択ダイアログロジックの実装です。
 */
public final class BizSkcsLogicImpl implements BizSkcsLogic {

    /**
     * 住所選択ダイアログ一覧取得Dao。
     */
    @Resource
    protected CrmCcSkcsCmnDao crmCcSkcsCmnDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<CrmCcSkcsCmn> getEntityList(
            AddressSelectDialogCriteriaDto dto) {

        // 住所選択ダイアログを一覧検索する。
        PagingResult<CrmCcSkcsCmn> result =
            crmCcSkcsCmnDao.findByAddSelDialogCriteria(dto);

        return result;
    }
}
