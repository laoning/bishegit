/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage;

import com.biz_integral.crm.cc.domain.dto.ParameterManageDto;

/**
 * パラメータ登録ダイアログマッパー。
 */
public interface EntryMapper {

    /**
     * パラメータ登録初期化モデルを作成します。
     * 
     * @param requestModel
     *            パラメータ登録ダイアログ画面初期表示submitモデル
     * @return パラメータ登録初期化モデル
     */
    public abstract ParameterManageDto getLayoutSubmitModel(
            EntryInitializeLayoutRequestModel requestModel);

    /**
     * パラメータ登録ダイアログ画面初期表示responseモデルを作成します。
     * 
     * @param model
     *            パラメータViewDTOモデル
     * @return パラメータ登録ダイアログ画面初期表示responseモデル
     */
    public abstract EntryInitializeLayoutResponseModel setLayoutResponseModel(
            ParameterManageDto model);

    /**
     * パラメータ登録登録モデルを作成します。
     * 
     * @param requestModel
     *            パラメータ登録ダイアログ登録ボタン押下submitモデル
     * @return パラメータ登録登録モデル
     */
    public abstract ParameterManageDto getRegistrationSubmitModel(
            EntryCreateRequestModel requestModel);

}
