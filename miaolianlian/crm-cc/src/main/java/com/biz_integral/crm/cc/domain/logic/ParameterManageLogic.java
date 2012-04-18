/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.ParameterManageDto;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * パラメータ管理ロジック。
 */
public interface ParameterManageLogic {

    /**
     * 表示項目取得.
     * <p>
     * パラメータ登録ダイアログの画面上に表示する項目を取得します。
     * </p>
     * <ul>
     * <li>引数dtoの会社コードに合致する
     * <li>引数dtoのパラメータコードに合致する
     * <li>引数dtoの期間コードに合致する
     * <li>引数dtoのモジュール分類に合致する
     * <li>引数dtoのロケールIDに合致する
     * </ul>
     * 
     * @param dto
     *            パラメータ登録初期化モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>引数dtoの会社コードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのパラメータコードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoの期間コードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのモジュール分類が{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのロケールIDが{@code null}でない、かつ空文字でない</li>
     *            </ul>
     * @return パラメータViewDTOモデル。
     */
    public ParameterManageDto getEntity(ParameterManageDto dto);

    /**
     * パラメータ検索のパラメータ明細一覧検索処理.
     * 
     * <p>
     * 引数の条件に従ってパラメータ、パラメータ明細を検索し、<br/>
     * 成功した場合はユーザロケールIDに従った名称にて初期設定の検索結果一覧を返します。
     * </p>
     * 
     * @param model
     *            パラメータ検索検索条件モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>引数dtoが{@code null}でない
     *            <li>引数dtoの会社コードが{@code null}でない、かつ空文字でない
     *            <li>引数dtoのロケールIDが{@code null}でない、かつ空文字でない
     *            <li>引数dtoの検索基準日が{@code null}でない、かつ空文字でない
     *            <li>引数dtoのモジュール分類
     *            </ul>
     * @return ページング検索結果<パラメータViewDTO>
     */
    public PagingResult<ParameterManageDto> search(ParameterManageDto model);

    /**
     * 登録入力チェック.
     * <p>
     * パラメータ登録ダイアログの登録ボタン押下時の入力値をチェックします。
     * </p>
     * 
     * @param dto
     *            パラメータ登録登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>引数dtoの会社コードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoの期間コードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのパラメータコードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのユーザコードが{@code null}でない、かつ空文字でない</li>
     *            <li>引数dtoのパラメータ値が{@code null}でない、かつ空文字でない</li>
     *            </ul>
     * @return 妥当性検証結果
     */
    public ValidationResults validate(ParameterManageDto dto);

    /**
     * パラメータ登録.
     * <p>
     * パラメータ値の更新処理を行います。
     * </p>
     * 
     * @param dto
     *            パラメータ登録登録モデル
     */
    public void update(ParameterManageDto dto);
}
