/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.mapper;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AccountAttributeCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountModule;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMアカウント属性ロジックの実装で利用するマッパーです。
 */
public interface CrmAccountAttributeLogicMapper {

    /**
     * 入力ストリームをCRMアカウント属性エンティティに変換します。<br>
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param dtoList
     *            CRMアカウント属性CSV読込みモデル
     * @return CRMアカウント属性エンティティ
     */
    public List<CrmCcAccountAttr> convertDtoList(String companyCd,
            List<AccountAttributeCsvReaderModelDto> dtoList);

    /**
     * 取得した結果の出力内容を、列挙体とフラグを名称に変換します。
     * 
     * @param searchResult
     *            CRMアカウント属性検索結果DTOリスト（名称変換前）
     * @return CRMアカウント属性検索結果DTOリスト（名称変換後）
     */
    public PagingResult<AccountAttributeSearchResultDto> convert(
            PagingResult<AccountAttributeSearchResultDto> searchResult);

    /**
     * CRMアカウント属性をCRMアカウント利用モジュール設定に変換します。<br>
     * 
     * @param request
     *            初期化用引数モデル
     */
    public abstract CrmCcAccountModule convert(CrmCcAccountAttr crmCcAccountAttr);

}
