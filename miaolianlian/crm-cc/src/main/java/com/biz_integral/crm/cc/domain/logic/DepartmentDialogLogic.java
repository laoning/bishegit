/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.DepartmentDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 組織ダイアログロジックです。
 */
public interface DepartmentDialogLogic {

    /**
     * 組織選択ダイアログを一覧検索する。
     * 
     * @param criteriaDto
     *            組織選択ダイアログ一覧検索条件モデル
     * 
     * @return 組織選択ダイアログを一覧
     */
    public PagingResult<CrmCcDepartmentCmn> getEntityListTree(
            DepartmentDialogCriteriaDto criteriaDto);

    /**
     * 組織設定ダイアログを一覧検索する。
     * 
     * @param criteriaDto
     *            組織設定ダイアログ一覧検索条件モデル
     * 
     * @return 組織設定ダイアログを一覧
     */
    public PagingResult<CrmCcDepartmentCmn> getEntityList(
            DepartmentDialogCriteriaDto criteriaDto);
}
