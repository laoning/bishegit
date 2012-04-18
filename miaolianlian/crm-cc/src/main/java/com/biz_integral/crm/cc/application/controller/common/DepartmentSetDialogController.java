/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.DepartmentDialogMapper;
import com.biz_integral.crm.cc.domain.dto.DepartmentDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.cc.domain.logic.DepartmentDialogLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 組織設定ダイアログ検索画面のコントローラです。
 */
public class DepartmentSetDialogController {

    /**
     * 組織ダイアログロジック
     */
    @Resource
    protected DepartmentDialogLogic departmentDialogLogic;

    /**
     * 組織ダイアログ検索画面で利用するマッパー
     */
    @Resource
    protected DepartmentDialogMapper departmentDialogMapper;

    /**
     * 検索結果一覧ページャー押下の処理です。
     * 
     * @param request
     *            ToDo一覧検索条件モデル
     * @return 検索結果
     */
    @Execute
    @Validation
    public PagingResponse<DepartmentDialogFilterResponseModel> filter(
            DepartmentDialogFilterRequestModel request) {

        DepartmentDialogCriteriaDto criteria =
            departmentDialogMapper.convert(request);

        PagingResponse<DepartmentDialogFilterResponseModel> result =
            new PagingResponse<DepartmentDialogFilterResponseModel>();

        PagingResult<CrmCcDepartmentCmn> pagingResult =
            departmentDialogLogic.getEntityList(criteria);

        PagingResult<DepartmentDialogFilterResponseModel> model =
            departmentDialogMapper.convert(pagingResult);

        result.setRows(model.getResultList());
        result.setTotal(model.getAllRecordCount());
        return result;
    }
}
