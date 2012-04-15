/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.AddressSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcSkcsCmn;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 住所選択ダイアログロジックです。
 */
public interface BizSkcsLogic {

    /**
     * 住所選択ダイアログを一覧検索する。
     * 
     * @param criteriaDto
     *            住所選択ダイアログ一覧検索条件モデル
     * 
     * @return 住所選択ダイアログを一覧
     */
    public PagingResult<CrmCcSkcsCmn> getEntityList(
            AddressSelectDialogCriteriaDto criteriaDto);
}
