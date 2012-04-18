/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.io.InputStream;
import java.util.List;

import com.biz_integral.crm.cc.application.web.accountAttributeManage.SearchExportFileResponseModel;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント属性のロジックです。
 */
public interface CrmAccountAttributeLogic {

    /**
     * アカウント属性一覧を取得します。
     * 
     * @param dto
     *            アカウント属性一覧検索条件モデル
     * 
     * @return アカウント属性一覧エンティティ
     * 
     */
    public abstract PagingResult<AccountAttributeSearchResultDto> getAccountAttributeList(
            AccountAttributeSearchCriteriaDto dto);

    /**
     * アカウント属性エンティティモデルを作成します。
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param inputStream
     *            入力ストリーム
     * @return アカウント属性エンティティモデル一覧
     */
    public abstract List<CrmCcAccountAttr> createModel(String companyCd,
            InputStream inputStream);

    /**
     * コードの存在チェックを実行します。
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param entityList
     */
    public abstract void isExistModel(String companyCd, String localeId,
            List<CrmCcAccountAttr> entityList);

    /**
     * アカウント属性を更新します。
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param entityList
     *            アカウント属性エンティティモデル一覧
     */
    public abstract void replace(String companyCd,
            List<CrmCcAccountAttr> entityList);

    /**
     * ファイルを作成します。
     * 
     * @param criteriaDto
     *            アカウント属性作成条件
     * @param registerNo
     *            registerNo
     * @return ファイル出力レスポンスモデル
     */
    public abstract SearchExportFileResponseModel createFile(
            AccountAttributeSearchCriteriaDto criteriaDto);

    /**
     * アカウント属性を取得します。
     * 
     * @param entity
     *            アカウント属性モデル
     * 
     * @return アカウント属性エンティティ
     */
    public abstract CrmCcAccountAttr getEntity(CrmCcAccountAttr entity);

    /**
     * アカウント属性を登録します。
     * 
     * @param entity
     *            アカウント属性エンティティ
     */
    public abstract void create(CrmCcAccountAttr entity);

    /**
     * アカウント属性を更新します。
     * 
     * @param entity
     *            アカウント属性エンティティ
     */
    public abstract void update(CrmCcAccountAttr entity);

    /**
     * アカウント属性を削除します。
     * 
     * @param entity
     *            アカウント属性エンティティ
     */
    public abstract void delete(CrmCcAccountAttr entity);

    /**
     * CRMアカウント分類に関連するテーブルを論理削除します。<br>
     * CRMアカウント分類にはPKを必須で設定してください。
     * 
     * @param crmCcAccountClass
     *            CRMアカウント分類
     * @return 削除件数
     */
    public abstract long deleteByCrmCcAccountClass(
            CrmCcAccountClass... crmCcAccountClass);
}
