/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountGroupAttributeManage;

import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウントグループ属性検索画面で利用するマッパーです。
 */
public interface SearchMapper {

    /**
     * アカウントグループ属性検索画面で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param searchFilterRequestModel
     *            アカウントグループ属性検索画面で入力された検索条件
     * @return アカウントグループ属性一覧検索条件モデル
     */
    public AccountGroupAttributeSearchCriteriaDto convert(
            SearchFilterRequestModel searchFilterRequestModel);

    /**
     * エンティティクラスをアカウントグループ属性一覧検索条件モデルに変換します。
     * 
     * @param accountGroupAttributeSearchResultDto
     *            アカウントグループ属性検索画面一覧のエンティティクラス
     * @return {@link SearchFilterResponseModel}
     */
    public PagingResult<SearchFilterResponseModel> convert(
            PagingResult<AccountGroupAttributeSearchResultDto> accountGroupAttributeSearchResultDto);

    /**
     * アカウントグループ属性検索画面で入力された検索条件をビジネスロジックの<br>
     * エクスポート処理引数DTOに変換します。
     * 
     * @param searchExportFileRequestModel
     *            アカウントグループ属性検索画面で入力された検索条件
     * @return アカウントグループ属性一覧検索条件モデル
     */
    AccountGroupAttributeSearchCriteriaDto convert(
            SearchExportFileRequestModel searchExportFileRequestModel);

}
