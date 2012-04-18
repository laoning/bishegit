/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.mapper;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * CRMアカウント属性ロジックの実装で利用するマッパーです。
 */
public interface CrmAccountGroupAttributeLogicMapper {

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
    public List<CrmCcAccountGrpAttr> convertDtoList(String companyCd,
            List<AccountGroupAttributeCsvReaderModelDto> dtoList);

    /**
     * 取得した結果の出力内容を、列挙体とフラグを名称に変換します。
     * 
     * @param searchResult
     *            CRMアカウント属性検索結果DTOリスト（名称変換前）
     * @return CRMアカウント属性検索結果DTOリスト（名称変換後）
     */
    public PagingResult<AccountGroupAttributeSearchResultDto> convert(
            PagingResult<AccountGroupAttributeSearchResultDto> searchResult);
}
