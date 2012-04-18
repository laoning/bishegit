/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.DepartmentDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.DepartmentDialogFilterResponseModel;
import com.biz_integral.crm.cc.domain.dto.DepartmentDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 組織ダイアログで利用するマッパーです。
 */
public interface DepartmentDialogMapper {

    /**
     * 組織ダイアログ一覧で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            組織ダイアログ一覧で入力された検索条件
     * @return {@link DepartmentDialogCriteriaDto}
     */
    public DepartmentDialogCriteriaDto convert(
            DepartmentDialogFilterRequestModel model);

    /**
     * エンティティクラスを組織ダイアログ一覧検索条件モデルに変換します。
     * 
     * @param crmCcDepartmentCmn
     *            エンティティクラス
     * @return {@link DepartmentDialogFilterResponseModel}
     */
    public PagingResult<DepartmentDialogFilterResponseModel> convert(
            PagingResult<CrmCcDepartmentCmn> crmCcDepartmentCmn);

}
