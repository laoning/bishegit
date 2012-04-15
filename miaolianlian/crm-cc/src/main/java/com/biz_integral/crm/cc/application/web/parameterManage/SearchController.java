/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.ParameterManageDto;
import com.biz_integral.crm.cc.domain.logic.impl.ParameterManageLogicImpl;
import com.biz_integral.crm.cc.domain.types.ModuleCtg;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * パラメータ検索画面コントローラ
 * 
 * <p>
 * <h2>処理概要</h2>
 * <ul>
 * <li>パラメータの検索処理を行います。</li>
 * </ul>
 * <h2>利用している画面</h2>
 * <ul>
 * <li>パラメータ検索画面</li>
 * </ul>
 * </p>
 * 
 */
public class SearchController {

    /**
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * マッパクラスです。
     */
    @Resource
    private SearchMapper searchMapper;

    /**
     * コンテキストコンテナ。
     */
    @Resource
    protected ContextContainer contextContainer;

    /** パラメータ管理ビジネスロジックです。 */
    @Resource
    public ParameterManageLogicImpl parameterManageLogic;

    /**
     * 画面初期表示処理を行います。
     * 
     * @return パラメータ検索初期化ボタン押下responseモデル
     */
    @Execute
    public SearchInitializeLayoutResponseModel initializeLayout() {

        SearchInitializeLayoutResponseModel responseModel =
            new SearchInitializeLayoutResponseModel();

        String localeId = contextContainer.getUserContext().getLocaleID();

        // モジュール区分一覧を取得します。
        responseModel.targetModules.add(new KeyValueBean());// 空欄追加
        for (ModuleCtg moduleCtg : ModuleCtg.values()) {
            String name =
                this.enumNamesRegistry.getName(moduleCtg, LocaleUtil
                    .toLocale(localeId));
            responseModel.targetModules.add(new KeyValueBean(name, moduleCtg
                .value()));
        }

        // パラメータ検索画面初期表示responseモデルを返す
        return responseModel;
    }

    /**
     * パラメータ検索_検索ボタン押下_初期設定処理を行います。
     * 
     * @param requestModel
     *            パラメータ検索_検索ボタン押下requestモデル
     * 
     * @return パラメータ検索_検索ボタン押下responseモデル
     */
    @Execute
    public SearchFilterResponseModel filterDefault(
            SearchFilterRequestModel requestModel) {

        // パラメータ検索検索条件モデルを作成
        ParameterManageDto model =
            searchMapper.createParamSrchDefaultDto(requestModel);

        // パラメータ検索処理
        PagingResult<ParameterManageDto> list =
            parameterManageLogic.search(model);

        // パラメータ検索検索ボタン押下初期処理responseモデルを作成
        SearchFilterResponseModel responseModel =
            searchMapper.createSearchResponseModel(list);

        return responseModel;
    }

    /**
     * パラメータ検索_検索ボタン押下_表示コントロール処理を行います
     * 
     * @param requestModel
     *            パラメータ検索検索ボタン押下初期処理requestモデル
     * 
     * @return パラメータ検索_検索ボタン押下responseモデル
     */
    @Execute
    public SearchFilterResponseModel filterControl(
            SearchFilterRequestModel requestModel) {

        // パラメータ検索検索条件モデルを作成
        ParameterManageDto model =
            searchMapper.createParamSrchControlDto(requestModel);

        // パラメータ検索処理
        PagingResult<ParameterManageDto> list =
            parameterManageLogic.search(model);

        // パラメータ検索検索ボタン押下初期処理responseモデルを作成
        SearchFilterResponseModel responseModel =
            searchMapper.createSearchControlResponseModel(list);

        return responseModel;
    }
}
