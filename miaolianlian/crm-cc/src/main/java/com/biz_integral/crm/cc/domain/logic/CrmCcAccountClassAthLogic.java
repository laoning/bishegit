/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.io.InputStream;
import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AccountClassAthSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類所属のロジックです。
 */
public interface CrmCcAccountClassAthLogic {

    /**
     * アカウント分類所属一覧を取得します。
     * 
     * @param dto
     *            アカウント分類所属一覧検索条件モデル
     * 
     * @return アカウント分類所属一覧エンティティ
     * 
     */
    public abstract PagingResult<AccountClassAthSearchResultDto> getAccountBelongClassList(
            CrmCcAccountClassAthSearchCriteriaDto dto);

    /**
     * アカウント分類所属一覧を取得します。（登録/更新用）
     * 
     * @param dto
     *            アカウント分類所属一覧検索条件モデル
     * 
     * @return アカウント分類所属一覧エンティティ
     * 
     */
    public abstract PagingResult<AccountClassAthSearchResultDto> getAccountBelongClassListForEdit(
            CrmCcAccountClassAthSearchCriteriaDto dto);

    /**
     * アカウント分類所属を追加します。
     * 
     * @param entityList
     *            アカウント分類所属エンティティリスト
     */
    public abstract void create(List<CrmCcAccountClassAth> entityList);

    /**
     * アカウント分類所属を削除します。
     * 
     * @param entityList
     *            アカウント分類所属エンティティリスト
     */
    public abstract void delete(List<CrmCcAccountClassAth> entityList);

    /**
     * アカウント分類所属エンティティモデルを作成します。
     * 
     * @param inputStream
     *            入力ストリーム
     * @return List<CrmTdAllTodoTarget> 全社ToDo対象モデル一覧
     */
    public abstract List<CrmCcAccountClassAth> createModel(
            InputStream inputStream);

    /**
     * コードの存在チェックを実行します。
     * 
     * @param criteriaDtoList
     *            List<全社ToDo対象モデル>
     */
    public abstract void isExistModel(List<CrmCcAccountClassAth> entityList);

    /**
     * 全社ToDo対象マスタを更新します。
     * 
     * @param companyCd
     *            会社コード
     * @param entityList
     *            アカウント分類所属エンティティリスト
     */
    public abstract void replace(String companyCd,
            List<CrmCcAccountClassAth> entityList);

    // /**
    // * ファイルを作成します。
    // *
    // * @param criteriaDto
    // * アカウント分類所属作成条件
    // * @param registerNo
    // * registerNo
    // * @return ファイル出力レスポンスモデル
    // */
    // public abstract SearchExportFileResponseModel createFile(
    // CrmCcAccountClassAthSearchCriteriaDto criteriaDto);

}
