/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountBelongClassManage;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AccountClassAthSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類所属登録／更新画面で利用するマッパーです。
 */
public interface EntryMapper {

    /**
     * アカウント分類所属登録／更新画面で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            アカウント分類所属登録／更新画面で入力された検索条件
     * @return {@link CrmCcAccountClassAthSearchCriteriaDto}
     */
    public abstract CrmCcAccountClassAthSearchCriteriaDto convert(
            EntryFilterRequestModel model);

    /**
     * エンティティクラスをアカウント分類所属一覧検索条件モデルに変換します。
     * 
     * @param accountClassAthSearchResultDto
     *            アカウント分類所属検索画面一覧のエンティティクラス
     * @return {@link EntryFilterResponseModel}
     */
    public abstract PagingResult<EntryFilterResponseModel> convert(
            PagingResult<AccountClassAthSearchResultDto> accountClassAthSearchResultDto);

    /**
     * アカウント分類所属登録/更新画面の追加ボタン条件モデルをアカウント分類所属エンティティに変換します。
     * 
     * @param entryAccountAddRequestModel
     *            アカウント分類所属登録/更新画面の追加ボタンの条件モデル
     * @return {@link CrmCcAccountClassAth}
     */
    public abstract List<CrmCcAccountClassAth> convert(
            EntryAccountAddRequestModel entryAccountAddRequestModel);

    /**
     * アカウント分類所属登録/更新画面の削除ボタン条件モデルをアカウント分類所属エンティティに変換します。
     * 
     * @param entryAccountDeleteRequestModel
     *            アカウント分類所属登録/更新画面の削除ボタンの条件モデル
     * @return {@link CrmCcAccountClassAth}
     */
    public abstract List<CrmCcAccountClassAth> convert(
            EntryAccountDeleteRequestModel entryAccountDeleteRequestModel);

}
