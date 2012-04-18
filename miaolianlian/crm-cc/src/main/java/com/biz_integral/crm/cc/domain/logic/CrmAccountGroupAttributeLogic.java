/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.io.InputStream;
import java.util.List;

import com.biz_integral.crm.cc.application.web.accountGroupAttributeManage.SearchExportFileResponseModel;
import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウントグループ属性のロジックです。
 */
public interface CrmAccountGroupAttributeLogic {

    /**
     * アカウントグループ属性一覧を取得します。
     * 
     * @param dto
     *            アカウントグループ属性一覧検索条件モデル
     * 
     * @return アカウントグループ属性一覧エンティティ
     * 
     */
    public abstract PagingResult<AccountGroupAttributeSearchResultDto> getAccountGroupAttributeList(
            AccountGroupAttributeSearchCriteriaDto dto);

    /**
     * アカウントグループ属性エンティティモデルを作成します。
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param inputStream
     *            入力ストリーム
     * @return アカウントグループ属性エンティティモデル一覧
     */
    public abstract List<CrmCcAccountGrpAttr> createModel(String companyCd,
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
            List<CrmCcAccountGrpAttr> entityList);

    /**
     * アカウントグループ属性を更新します。
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param entityList
     *            アカウントグループ属性エンティティモデル一覧
     */
    public abstract void replace(String companyCd,
            List<CrmCcAccountGrpAttr> entityList);

    /**
     * ファイルを作成します。
     * 
     * @param criteriaDto
     *            アカウントグループ属性作成条件
     * @param registerNo
     *            registerNo
     * @return ファイル出力レスポンスモデル
     */
    public abstract SearchExportFileResponseModel createFile(
            AccountGroupAttributeSearchCriteriaDto criteriaDto);

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

    /**
     * CRMアカウントグループ属性を論理削除します。 以下の処理により関連テーブルも削除されます。<br>
     * {@link CrmAccountGroupModuleLogic#deleteByCrmCcAccountGrpAttr(CrmCcAccountGrpAttr...)}
     * <br>
     * 
     * @param crmCcAccountGrpAttr
     */
    void delete(CrmCcAccountGrpAttr crmCcAccountGrpAttr);

}
