/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウントグループ検索画面で利用するマッパーです。
 */
public interface SearchMapper {

    /**
     * アカウントグループ検索画面で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            アカウントグループ検索画面で入力された検索条件
     * @return {@link AcctGrpSearchCriteriaDto}
     */
    public AcctGrpSearchCriteriaDto convert(
            SearchFilterRequestModel model);

    /**
     * エンティティクラスをアカウントグループ一覧検索条件モデルに変換します。
     * 
     * @param accountBelongClassSearchResultDto
     *            アカウントグループ検索画面一覧のエンティティクラス
     * @return {@link SearchFilterResponseModel}
     */
    public PagingResult<SearchFilterResponseModel> convert(
            PagingResult<AcctGrpSearchResultDto> accountBelongClassSearchResultDto);

}
