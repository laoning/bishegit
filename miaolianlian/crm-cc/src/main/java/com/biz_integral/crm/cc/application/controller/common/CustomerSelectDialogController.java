/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.CustomerSelectDialogMapper;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.crm.cc.domain.logic.ImCustomerLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 取引先選択ダイアログ検索画面のコントローラです。
 */
public class CustomerSelectDialogController {

    /**
     * 取引先選択ダイアログロジック
     */
    @Resource
    protected ImCustomerLogic imCustomerLogic;

    /**
     * 取引先選択ダイアログ検索画面で利用するマッパー
     */
    @Resource
    protected CustomerSelectDialogMapper customerDialogMapper;

    /**
     * 初期化を行います。
     * 
     * @return 画面初期化に利用する項目
     */
    @Execute
    public CustomerSelectDialogInitializeResponseModel initialize() {
        CustomerSelectDialogInitializeResponseModel model =
            new CustomerSelectDialogInitializeResponseModel();

        model.searchBaseDate = DateUtil.nowDate();

        return model;
    }

    /**
     * 検索結果一覧ページャー押下の処理です。
     * 
     * @param request
     *            ToDo一覧検索条件モデル
     * @return 検索結果
     */
    @Execute
    @Validation
    public PagingResponse<CustomerSelectDialogFilterResponseModel> filter(
            CustomerSelectDialogFilterRequestModel request) {

        CustomerSelectDialogCriteriaDto criteria =
            customerDialogMapper.convert(request);

        PagingResponse<CustomerSelectDialogFilterResponseModel> result =
            new PagingResponse<CustomerSelectDialogFilterResponseModel>();

        PagingResult<CrmCcCustomerCmn> pagingResult =
            imCustomerLogic.getEntityList(criteria);

        PagingResult<CustomerSelectDialogFilterResponseModel> model =
            customerDialogMapper.convert(pagingResult);

        result.setRows(model.getResultList());
        result.setTotal(model.getAllRecordCount());
        return result;
    }
}
