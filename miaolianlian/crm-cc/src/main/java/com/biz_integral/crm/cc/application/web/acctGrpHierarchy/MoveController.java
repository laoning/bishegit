/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrpHierarchy;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AcctGrpHierarchyDto;
import com.biz_integral.crm.cc.domain.logic.AcctGrpHierarchyLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.service.domain.master.locale.BizLocaleManager;

/**
 * アカウントグループ階層移動画面のコントローラークラスです。<br/>
 */
public class MoveController {

    /**
     * {@link BizLocaleManager ロケールマネージャー}
     */
    @Resource
    protected BizLocaleManager bizLocaleManager;

    /**
     * {@link EntryMapper 階層マッパー}
     */
    @Resource
    protected EntryMapper entryMapper;

    /**
     * {@link AcctGrpHierarchyLogic 階層マッパー}
     */
    @Resource
    protected AcctGrpHierarchyLogic acctGrpHierarchyLogic;

    /**
     * 画面の初期化時に利用する情報を取得し、返します。<br/>
     * <p>
     * <ul>
     * <li>アカウントグループ階層を取得します。</li>
     * </ul>
     * </p>
     * 
     * @param requestModel
     *            画面の初期化時に利用するリクエストモデル
     * @return アカウントグループ階層情報をセットしたレスポンスモデル
     */
    @Execute
    @Validation
    public EntryInitializeResponseModel initialize(
            EntryRequestModel requestModel) {

        // 検索条件モデルを作成
        AcctGrpHierarchyDto model = entryMapper.createModel(requestModel);

        // 検索処理(全て)
        EntryInitializeResponseModel responseModel =
            acctGrpHierarchyLogic.searchAll(model);

        return responseModel;
    }

    /**
     * アカウントグループ階層を移動します。<br/>
     * <p>
     * <ul>
     * <li>アカウントグループ階層を移動します。</li>
     * </ul>
     * </p>
     * 
     * @param requestModel
     *            アカウントグループ階層を移動するリクエストモデル
     * @return 無し
     */
    @Execute
    @Validation
    public void move(EntryRequestModel requestModel) {

        // パラメータ登録登録モデルを作成する。
        AcctGrpHierarchyDto dto = entryMapper.createModel(requestModel);

        // 処理分岐
        if ("1".equals(dto.isRoot)) {
            // 入力チェックを行う。
            acctGrpHierarchyLogic.validateMoveToRoot(dto);
            // 階層移動処理
            acctGrpHierarchyLogic.moveToRoot(dto);
        } else {
            // 入力チェックを行う。
            acctGrpHierarchyLogic.validateMove(dto);
            // 階層移動処理
            acctGrpHierarchyLogic.move(dto);
        }

    }
}
