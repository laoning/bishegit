/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage;

import com.biz_integral.crm.cc.domain.dto.AccountClassAthSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthSearchCriteriaDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類所属検索画面で利用するマッパーです。
 */
public interface SearchMapper {

    /**
     * アカウント分類所属検索画面で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            アカウント分類所属検索画面で入力された検索条件
     * @return {@link CrmCcAccountClassAthSearchCriteriaDto}
     */
    public CrmCcAccountClassAthSearchCriteriaDto convert(
            SearchFilterRequestModel model);

    /**
     * エンティティクラスをアカウント分類所属一覧検索条件モデルに変換します。
     * 
     * @param accountClassAthSearchResultDto
     *            アカウント分類所属検索画面一覧のエンティティクラス
     * @return {@link SearchFilterResponseModel}
     */
    public PagingResult<SearchFilterResponseModel> convert(
            PagingResult<AccountClassAthSearchResultDto> accountClassAthSearchResultDto);

    /**
     * アカウント分類所属検索画面で入力された検索条件をエクスポートビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            アカウント分類所属検索画面で入力された検索条件
     * @return {@link CrmCcAccountClassAthSearchCriteriaDto}
     */
    public CrmCcAccountClassAthSearchCriteriaDto convert(
            SearchExportFileRequestModel model);

}
