/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント属性検索画面で利用するマッパーです。
 */
public interface SearchMapper {

    /**
     * アカウント属性検索画面で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param searchFilterRequestModel
     *            アカウント属性検索画面で入力された検索条件
     * @return アカウント属性一覧検索条件モデル
     */
    public AccountAttributeSearchCriteriaDto convert(
            SearchFilterRequestModel searchFilterRequestModel);

    /**
     * エンティティクラスをアカウント属性一覧検索条件モデルに変換します。
     * 
     * @param accountAttributeSearchResultDto
     *            アカウント属性検索画面一覧のエンティティクラス
     * @return {@link SearchFilterResponseModel}
     */
    public PagingResult<SearchFilterResponseModel> convert(
            PagingResult<AccountAttributeSearchResultDto> accountAttributeSearchResultDto);

    /**
     * アカウント属性検索画面で入力された検索条件をビジネスロジックの<br>
     * エクスポート処理引数DTOに変換します。
     * 
     * @param searchExportFileRequestModel
     *            アカウント属性検索画面で入力された検索条件
     * @return アカウント属性一覧検索条件モデル
     */
    AccountAttributeSearchCriteriaDto convert(
            SearchExportFileRequestModel searchExportFileRequestModel);

}
