/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountClassManage;

import com.biz_integral.crm.cc.domain.dto.AccountClassSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSearchResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類検索画面で利用するマッパーです。
 */
public interface SearchMapper {

    /**
     * アカウント分類検索画面で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param searchFilterRequestModel
     *            アカウント分類検索画面で入力された検索条件
     * @return アカウント分類一覧検索条件
     */
    public AccountClassSearchCriteriaDto convert(
            SearchFilterRequestModel searchFilterRequestModel);

    /**
     * エンティティクラスをアカウント分類一覧検索条件モデルに変換します。
     * 
     * @param accountBelongClassSearchResultDto
     *            アカウント分類検索画面一覧のエンティティクラス
     * @return アカウント分類一覧検索結果
     */
    public PagingResult<SearchFilterResponseModel> convert(
            PagingResult<AccountClassSearchResultDto> accountBelongClassSearchResultDto);

    /**
     * アカウント分類検索画面で入力された検索条件をビジネスロジックの<br>
     * エクスポート処理引数DTOに変換します。
     * 
     * @param searchExportFileRequestModel
     *            アカウント分類検索画面で入力された検索条件
     * @return アカウント分類一覧検索条件
     */
    public AccountClassSearchCriteriaDto convert(
            SearchExportFileRequestModel searchExportFileRequestModel);

}
