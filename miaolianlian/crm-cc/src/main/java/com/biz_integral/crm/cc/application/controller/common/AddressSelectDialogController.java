/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.AddressSelectDialogMapper;
import com.biz_integral.crm.cc.domain.dto.AddressSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcSkcsCmn;
import com.biz_integral.crm.cc.domain.logic.BizSkcsLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 住所選択ダイアログ検索画面のコントローラです。
 */
public class AddressSelectDialogController {

    /**
     * 住所選択ダイアログロジック
     */
    @Resource
    protected BizSkcsLogic bizSkcsLogic;

    /**
     * 住所選択ダイアログ検索画面で利用するマッパー
     */
    @Resource
    protected AddressSelectDialogMapper addressSelectDialogMapper;

    /**
     * 初期化を行います。
     * 
     * @return 画面初期化に利用する項目
     */
    @Execute
    public AddressSelectDialogInitializeResponseModel initialize() {
        AddressSelectDialogInitializeResponseModel model =
            new AddressSelectDialogInitializeResponseModel();

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
    public PagingResponse<AddressSelectDialogFilterResponseModel> filter(
            AddressSelectDialogFilterRequestModel request) {

        AddressSelectDialogCriteriaDto criteria =
            addressSelectDialogMapper.convert(request);

        PagingResponse<AddressSelectDialogFilterResponseModel> result =
            new PagingResponse<AddressSelectDialogFilterResponseModel>();

        PagingResult<CrmCcSkcsCmn> pagingResult =
            bizSkcsLogic.getEntityList(criteria);

        PagingResult<AddressSelectDialogFilterResponseModel> model =
            addressSelectDialogMapper.convert(pagingResult);

        result.setRows(model.getResultList());
        result.setTotal(model.getAllRecordCount());
        return result;
    }
}
