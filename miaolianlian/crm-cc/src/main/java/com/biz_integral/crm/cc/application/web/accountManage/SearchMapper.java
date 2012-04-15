/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント検索画面で利用するマッパーです。
 */
public interface SearchMapper {

    /**
     * アカウント検索画面で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            アカウント検索画面で入力された検索条件
     * @return {@link AccountSearchCriteriaDto}
     */
    public AccountSearchCriteriaDto convert(SearchFilterRequestModel model);

    /**
     * エンティティクラスをアカウント一覧検索条件モデルに変換します。
     * 
     * @param accountSearchResultDto
     *            アカウント検索画面一覧のエンティティクラス
     * @return {@link SearchFilterResponseModel}
     */
    public PagingResult<SearchFilterResponseModel> convert(
            PagingResult<AccountSearchResultDto> accountSearchResultDto);

}
