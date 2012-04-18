/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.AccountClassSelectDialogControllerMapper;
import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogResultDto;
import com.biz_integral.crm.cc.domain.logic.AccountClassSelectDialogLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類選択ダイアログのコントローラです。
 */
public class AccountClassSelectDialogController {

    /**
     * アカウント分類選択ダイアログで利用するマッパー
     */
    @Resource
    protected AccountClassSelectDialogControllerMapper accountClassSelectDialogControllerMapper;

    /**
     * アカウント分類選択ダイアログ対象ロジック
     */
    @Resource
    protected AccountClassSelectDialogLogic accountClassSelectDialogLogic;

    /**
     * 検索ボタン押下の処理です。
     * 
     * @param request
     *            アカウント分類選択ダイアログの一覧検索条件モデル
     * @return 画面初期化に利用する項目
     */
    @Validation
    @Execute
    public PagingResponse<AccountClassSelectDialogFilterResponseModel> filter(
            AccountClassSelectDialogFilterRequestModel request) {

        PagingResponse<AccountClassSelectDialogFilterResponseModel> result =
            new PagingResponse<AccountClassSelectDialogFilterResponseModel>();

        // 画面一覧データの取得です
        PagingResult<AccountClassSelectDialogResultDto> searchResult =
            accountClassSelectDialogLogic
                .getAccountClassList(accountClassSelectDialogControllerMapper
                    .searchConvert(request));

        // 画面用データです
        PagingResult<AccountClassSelectDialogFilterResponseModel> pagingResult =
            accountClassSelectDialogControllerMapper
                .searchConvert(searchResult);

        result.setRows(pagingResult.getResultList());
        result.setTotal(pagingResult.getAllRecordCount());
        return result;
    }
}
