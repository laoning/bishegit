/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.io.InputStream;
import java.util.List;

import com.biz_integral.crm.cc.application.web.accountClassManage.SearchExportFileResponseModel;
import com.biz_integral.crm.cc.domain.dto.AccountClassSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類のロジックです。
 */
public interface CrmAccountClassLogic {

    /**
     * アカウント分類一覧を取得します。
     * 
     * @param dto
     *            アカウント分類一覧検索条件モデル
     * 
     * @return アカウント分類一覧エンティティ
     * 
     */
    public abstract PagingResult<AccountClassSearchResultDto> getAccountClassList(
            AccountClassSearchCriteriaDto dto);

    /**
     * アカウント分類を取得します。
     * 
     * @param entity
     *            アカウント分類エンティティ
     * @return アカウント分類エンティティ
     */
    public abstract CrmCcAccountClass getByPk(CrmCcAccountClass entity);

    /**
     * アカウント分類を取得します。<br>
     * 存在していない場合はnullを返却します。
     * 
     * @param entity
     *            アカウント分類エンティティ
     * @return アカウント分類エンティティ
     */
    public abstract CrmCcAccountClass getByPkNoException(
            CrmCcAccountClass entity);

    /**
     * アカウント分類を登録します。
     * 
     * @param entity
     *            アカウント分類エンティティ
     */
    public abstract void create(CrmCcAccountClass entity);

    /**
     * アカウント分類を更新します。
     * 
     * @param entity
     *            アカウント分類エンティティ
     */
    public abstract void update(CrmCcAccountClass entity);

    /**
     * アカウント分類に関連するデータの有無を確認します。。<br>
     * 
     * @param entity
     *            アカウント分類エンティティ
     * @return 確認結果 true:関連データあり false:関連データなし
     */
    public abstract boolean deleteConfirm(CrmCcAccountClass entity);

    /**
     * アカウント分類を論理削除します。<br>
     * 以下の処理により関連テーブルも削除されます。<br>
     * {@link CrmAccountClassAthLogic#deleteByCrmCcAccountClass(CrmCcAccountClass...)}
     * <br>
     * {@link CrmAccountClassGrpAthLogic#deleteByCrmCcAccountClass(CrmCcAccountClass...)}
     * <br>
     * {@link CrmAccountAttributeLogic#deleteByCrmCcAccountClass(CrmCcAccountClass...)}
     * <br>
     * {@link CrmAccountGroupAttributeLogic#deleteByCrmCcAccountClass(CrmCcAccountClass...)}
     * <br>
     * 
     * @param entity
     *            アカウント分類エンティティ
     */
    public abstract void delete(CrmCcAccountClass entity);

    /**
     * アカウント分類エンティティモデルを作成します。
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param inputStream
     *            入力ストリーム
     * @return アカウント分類エンティティモデル一覧
     */
    public abstract List<CrmCcAccountClass> createModel(String companyCd,
            String localeId, InputStream inputStream);

    /**
     * アカウント分類を更新します。
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param entityList
     *            アカウント分類エンティティモデル一覧
     */
    public abstract void replace(String companyCd, String localeId,
            List<CrmCcAccountClass> entityList);

    /**
     * ファイルを作成します。
     * 
     * @param criteriaDto
     *            アカウント分類作成条件
     * @param registerNo
     *            registerNo
     * @return ファイル出力レスポンスモデル
     */
    public abstract SearchExportFileResponseModel createFile(
            AccountClassSearchCriteriaDto criteriaDto);

}
