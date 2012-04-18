/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.AccountSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.AccountSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント選択ダイアログで利用するマッピーです。
 */
public interface AccountDialogMapper {

    /**
     * アカウント検索画面で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            アカウント検索画面で入力された検索条件
     * @return {@link AccountSearchCriteriaDto}
     */
    public AccountSearchCriteriaDto convert(
            AccountSelectDialogFilterRequestModel model);

    /**
     * エンティティクラスをアカウント一覧検索結果モデルに変換します。
     * 
     * @param crmCcAccount
     *            アカウント検索画面一覧のエンティティクラス
     * @return {@link AccountSelectDialogFilterResponseModel}
     */
    public PagingResult<AccountSelectDialogFilterResponseModel> convert(
            PagingResult<AccountSearchResultDto> crmCcAccount);
}
