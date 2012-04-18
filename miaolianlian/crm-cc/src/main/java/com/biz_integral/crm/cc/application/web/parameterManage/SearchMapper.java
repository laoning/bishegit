/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage;

import com.biz_integral.crm.cc.domain.dto.ParameterManageDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * パラメータ検索マッパー.
 */
public interface SearchMapper {

    /**
     * パラメータ検索検索条件モデルを作成します。
     * 
     * @param model
     *            パラメータ検索検索ボタン押下初期処理requestモデル
     * @return パラメータ検索検索条件モデル
     */
    public abstract ParameterManageDto createParamSrchDefaultDto(
            SearchFilterRequestModel model);

    /**
     * (初期設定)パラメータ検索画面初期表示responseモデルを作成します。
     * 
     * @param list
     *            ページング検索結果<パラメータViewDTO>
     * @return パラメータ検索検索ボタン押下初期処理responseモデル
     */
    public abstract SearchFilterResponseModel createSearchResponseModel(
            PagingResult<ParameterManageDto> list);

    /**
     * パラメータ検索検索条件モデルを作成します。
     * 
     * @param requestModel
     *            パラメータ検索検索ボタン押下初期処理requestモデル
     * @return パラメータ検索検索条件モデル
     */
    public abstract ParameterManageDto createParamSrchControlDto(
            SearchFilterRequestModel requestModel);

    /**
     * (表示コントロール)パラメータ検索画面初期表示responseモデルを作成します。
     * 
     * @param list
     *            ページング検索結果<パラメータViewDTO>
     * @return パラメータ検索検索ボタン押下初期処理responseモデル
     */
    public abstract SearchFilterResponseModel createSearchControlResponseModel(
            PagingResult<ParameterManageDto> list);
}
