/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.UserDialogControllerMapper;
import com.biz_integral.crm.cc.domain.dto.UserDialogReslutDto;
import com.biz_integral.crm.cc.domain.logic.UserSelectDialogLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * ユーザ選択ダイアログのコントローラです。
 */
public class UserSelectDialogController {

    /**
     * ユーザ選択ダイアログで利用するマッビー
     */
    @Resource
    protected UserDialogControllerMapper userDialogControllerMapper;

    /**
     * ユーザ選択ダイアログ対象ロジック
     */
    @Resource
    protected UserSelectDialogLogic userSelectDialogLogic;

    /**
     * 初期化を行います。
     * 
     * @return 検索基準日
     */
    @Execute
    public UserDialogInitializeResponseModel initialize() {
        UserDialogInitializeResponseModel response =
            new UserDialogInitializeResponseModel();
        response.systemDate = DateUtil.nowDate();
        return response;
    }

    /**
     * 検索ボタン押下の処理です。
     * 
     * @param request
     *            ユーザ選択ダイアログの一覧検索条件モデル
     * @return 画面初期化に利用する項目
     */
    @Validation
    @Execute
    public PagingResponse<UserDialogFilterResponseModel> filter(
            UserDialogFilterRequestModel request) {

        PagingResponse<UserDialogFilterResponseModel> result =
            new PagingResponse<UserDialogFilterResponseModel>();

        // 画面一覧データの取得です
        PagingResult<UserDialogReslutDto> searchResult =
            userSelectDialogLogic.getEntityList(userDialogControllerMapper
                .searchConvert(request));

        // 画面用データです
        PagingResult<UserDialogFilterResponseModel> pagingResult =
            userDialogControllerMapper.searchConvert(searchResult);

        result.setRows(pagingResult.getResultList());
        result.setTotal(pagingResult.getAllRecordCount());
        return result;
    }
}
