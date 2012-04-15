/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.io.InputStream;

import com.biz_integral.crm.cc.application.web.acctGrpHierarchy.EntryInitializeResponseModel;
import com.biz_integral.crm.cc.domain.dto.AcctGrpHierarchyDto;
import com.biz_integral.service.maskat.service.model.FileDownloadResponseModel;

/**
 * アカウントグループ階層登録／更新ロジック
 */
public interface AcctGrpHierarchyLogic {
    /** 区切り文字 */
    public final static String PATH_SPLIT_CHAR = "#";

    /**
     * アカウントグループ階層検索処理。
     * 
     * <p>
     * アカウントグループ階層を検索し、検索結果一覧を返します。
     * </p>
     * 
     * @param model
     *            検索条件モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>引数dtoが{@code null}でない
     *            <li>引数dtoの会社コードが{@code null}でない、かつ空文字でない
     *            <li>引数dtoのロケールIDが{@code null}でない、かつ空文字でない
     *            <li>引数dtoの検索基準日が{@code null}でない、かつ空文字でない
     *            </ul>
     * @return リスト検索結果<アカウントグループ階層DTO>
     */
    public EntryInitializeResponseModel searchAll(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層を下位から表示処理。
     * 
     * <p>
     * アカウントグループ階層を検索し、検索結果一覧を返します。
     * </p>
     * 
     * @param model
     *            検索条件モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>引数dtoが{@code null}でない
     *            <li>引数dtoの会社コードが{@code null}でない、かつ空文字でない
     *            <li>引数dtoのロケールIDが{@code null}でない、かつ空文字でない
     *            <li>引数dtoの検索基準日が{@code null}でない、かつ空文字でない
     *            </ul>
     * @return レスポンスモデル
     */
    public EntryInitializeResponseModel displayReverse(AcctGrpHierarchyDto model);

    /**
     * アカウントグループ階層最上位追加チェック。
     * <p>
     * アカウントグループ階層最上位追加時のチェックをします。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li></li>
     *            </ul>
     * @return 妥当性検証結果
     */
    public void validateAppendTop(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層追加チェック。
     * <p>
     * アカウントグループ階層追加時のチェックをします。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li></li>
     *            </ul>
     * @return 妥当性検証結果
     */
    public void validateAppend(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層削除チェック。
     * <p>
     * アカウントグループ階層削除時のチェックをします。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li></li>
     *            </ul>
     * @return 妥当性検証結果
     */
    public void validateDelete(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層移動チェック。
     * <p>
     * アカウントグループ階層移動時のチェックをします。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li></li>
     *            </ul>
     * @return 妥当性検証結果
     */
    public void validateMove(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層をルートに移動チェック。
     * <p>
     * アカウントグループ階層をルートに移動時のチェックをします。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li></li>
     *            </ul>
     * @return 妥当性検証結果
     */
    public void validateMoveToRoot(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層移動。
     * <p>
     * アカウントグループ階層を移動します。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>移動するグループ</li>
     *            <li>移動先グループ</li>
     *            </ul>
     * @return なし
     */
    public void move(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層をルートに移動。
     * <p>
     * アカウントグループ階層をルートに移動します。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>移動するグループ</li>
     *            </ul>
     * @return なし
     */
    public void moveToRoot(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層削除。
     * <p>
     * アカウントグループ階層を削除します。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>削除するグループ</li>
     *            </ul>
     * @return なし
     */
    public void delete(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層追加。
     * <p>
     * アカウントグループ階層を追加します。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>追加するグループ</li>
     *            <li>追加先グループ</li>
     *            </ul>
     * @return なし
     */
    public void appendAcctGrp(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層最上位追加。
     * <p>
     * アカウントグループ階層最上位を追加します。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>追加するグループ</li>
     *            </ul>
     * @return なし
     */
    public void appendTop(AcctGrpHierarchyDto dto);

    /**
     * アカウントグループ階層ルート追加。
     * <p>
     * アカウントグループ階層ルートを追加します。
     * </p>
     * 
     * @param dto
     *            登録モデル。次の条件を満たすこと。
     *            <ul>
     *            <li>追加するグループ</li>
     *            </ul>
     * @return なし
     */
    public void appendAcctGrpRoot(AcctGrpHierarchyDto dto);

    public FileDownloadResponseModel exportFile(AcctGrpHierarchyDto dto,
            String registerNo);

    public String asyncRegister(AcctGrpHierarchyDto dto);

    public void importFile(InputStream inputStream);

}
