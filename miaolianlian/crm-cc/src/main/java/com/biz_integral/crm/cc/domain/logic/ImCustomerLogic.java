/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.CustomerSelectCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 取引先選択ダイアログロジックです。
 */
public interface ImCustomerLogic {

    /**
     * 取引先選択ダイアログを一覧検索する。
     * 
     * @param criteriaDto
     *            取引先選択ダイアログ一覧検索条件モデル
     * 
     * @return 取引先選択ダイアログを一覧
     */
    public PagingResult<CrmCcCustomerCmn> getEntityList(
            CustomerSelectDialogCriteriaDto criteriaDto);

    /**
     * 取引先の一件を取得します。
     * 
     * @param criteriadto
     *            取引先DTO
     * @return 取引先
     */
    public abstract CrmCcCustomerCmn getEntity(
            CustomerSelectCriteriaDto criteriadto);
}
