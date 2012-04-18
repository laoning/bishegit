/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.AccountClassSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.AccountClassSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類選択ダイアログで利用するマッパーです。
 */
public interface AccountClassSelectDialogControllerMapper {

    /**
     * アカウント分類選択ダイアログで入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            アカウント分類選択ダイアロで入力された検索条件
     * @return {@link AccountClassSelectDialogCriteriaDto}
     */
    public AccountClassSelectDialogCriteriaDto searchConvert(
            AccountClassSelectDialogFilterRequestModel request);

    /**
     * 
     * アカウント分類選択ダイアログ表示レスポンスモデルに変換します。
     * 
     * @param searchResult
     *            アカウント分類選択ダイアログ結果DTO
     * @return {@link AccountClassSelectDialogFilterResponseModel}
     */
    public PagingResult<AccountClassSelectDialogFilterResponseModel> searchConvert(
            PagingResult<AccountClassSelectDialogResultDto> searchResult);
}
