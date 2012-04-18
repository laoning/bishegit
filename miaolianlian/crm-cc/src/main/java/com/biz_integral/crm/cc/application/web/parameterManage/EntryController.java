/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.ParameterManageDto;
import com.biz_integral.crm.cc.domain.logic.ParameterManageLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResults;

/**
 * パラメータ登録ダイアログ画面コントローラ。
 * <p>
 * 
 * <h2>処理概要</h2>
 * <ul>
 * <li>初期化処理を行います。</li>
 * <li>登録処理を行います。</li>
 * </ul>
 * <h2>利用している画面</h2>
 * <ul>
 * <li>パラメータ登録ダイアログ</li>
 * </ul>
 * </p>
 */
public class EntryController {

    /** パラメータ管理ロジック */
    @Resource
    public ParameterManageLogic parameterManageLogic;

    /** パラメータ登録ダイアログマッパー */
    @Resource
    protected EntryMapper entryMapper;

    /**
     * パラメータ登録ダイアログの初期化処理を行います。
     * 
     * @param requestModel
     *            パラメータ登録ダイアログ画面初期表示requestモデル
     * @return パラメータ登録ダイアログ画面初期表示responseモデル
     */
    @Execute
    public EntryInitializeLayoutResponseModel initializeLayout(
            final EntryInitializeLayoutRequestModel requestModel) {

        // パラメータ登録初期化モデルを作成する。
        ParameterManageDto dto = entryMapper.getLayoutSubmitModel(requestModel);

        // 画面初期表示項目を検索する。
        ParameterManageDto result = parameterManageLogic.getEntity(dto);

        // パラメータ登録ダイアログ画面初期表示responseモデルを作成する。
        EntryInitializeLayoutResponseModel responseModel =
            entryMapper.setLayoutResponseModel(result);

        // パラメータ登録ダイアログ画面初期表示responseモデルを返す。
        return responseModel;

    }

    /**
     * パラメータ登録ダイアログの登録処理を行います。
     * 
     * @param requestModel
     *            パラメータ登録ダイアログ登録ボタン押下requestモデル
     */
    @Execute
    @Validation
    public void registration(final EntryCreateRequestModel requestModel) {

        // パラメータ登録登録モデルを作成する。
        ParameterManageDto dto =
            entryMapper.getRegistrationSubmitModel(requestModel);

        // 入力チェックを行う。
        ValidationResults results = parameterManageLogic.validate(dto);

        // エラー判定
        if (results.hasResult()) {
            // 妥当性検証結果にエラーがある場合
            // 検証例外をスローする。
            throw new ValidationException(results);
        }

        // パラメータ更新処理
        parameterManageLogic.update(dto);

    }
}