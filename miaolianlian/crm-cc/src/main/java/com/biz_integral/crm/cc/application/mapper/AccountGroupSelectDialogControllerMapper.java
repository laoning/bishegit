/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.AccountGroupSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.AccountGroupSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウントグループ選択ダイアログで利用するマッパーです。
 */
public interface AccountGroupSelectDialogControllerMapper {

    /**
     * アカウントグループ選択ダイアログで入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            アカウントグループ選択ダイアロで入力された検索条件
     * @return {@link AccountGroupSelectDialogCriteriaDto}
     */
    public AccountGroupSelectDialogCriteriaDto searchConvert(
            AccountGroupSelectDialogFilterRequestModel request);

    /**
     * 
     * アカウントグループ選択ダイアログ表示レスポンスモデルに変換します。
     * 
     * @param searchResult
     *            アカウントグループ選択ダイアログ結果DTO
     * @return {@link AccountGroupSelectDialogFilterResponseModel}
     */
    public PagingResult<AccountGroupSelectDialogFilterResponseModel> searchConvert(
            PagingResult<AccountGroupSelectDialogResultDto> searchResult);
}
