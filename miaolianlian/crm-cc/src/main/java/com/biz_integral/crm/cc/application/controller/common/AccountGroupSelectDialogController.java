/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.AccountGroupSelectDialogControllerMapper;
import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogResultDto;
import com.biz_integral.crm.cc.domain.logic.AccountGroupSelectDialogLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウントグループ選択ダイアログのコントローラです。
 */
public class AccountGroupSelectDialogController {

    /**
     * アカウントグループ選択ダイアログで利用するマッパー
     */
    @Resource
    protected AccountGroupSelectDialogControllerMapper accountGroupSelectDialogControllerMapper;

    /**
     * アカウントグループ選択ダイアログ対象ロジック
     */
    @Resource
    protected AccountGroupSelectDialogLogic accountGroupSelectDialogLogic;

    /**
     * 検索ボタン押下の処理です。
     * 
     * @param request
     *            アカウントグループ選択ダイアログの一覧検索条件モデル
     * @return 画面初期化に利用する項目
     */
    @Validation
    @Execute
    public PagingResponse<AccountGroupSelectDialogFilterResponseModel> filter(
            AccountGroupSelectDialogFilterRequestModel request) {

        PagingResponse<AccountGroupSelectDialogFilterResponseModel> result =
            new PagingResponse<AccountGroupSelectDialogFilterResponseModel>();

        // 画面一覧データの取得です
        PagingResult<AccountGroupSelectDialogResultDto> searchResult =
            accountGroupSelectDialogLogic
                .getAccountGroupList(accountGroupSelectDialogControllerMapper
                    .searchConvert(request));

        // 画面用データです
        PagingResult<AccountGroupSelectDialogFilterResponseModel> pagingResult =
            accountGroupSelectDialogControllerMapper
                .searchConvert(searchResult);

        result.setRows(pagingResult.getResultList());
        result.setTotal(pagingResult.getAllRecordCount());
        return result;
    }
}
