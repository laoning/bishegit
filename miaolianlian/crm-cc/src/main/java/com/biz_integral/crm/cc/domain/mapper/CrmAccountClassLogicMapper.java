/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.mapper;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AccountClassCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;

/**
 * CRMアカウント分類ロジックの実装で利用するマッパーです。
 */
public interface CrmAccountClassLogicMapper {

    /**
     * 入力ストリームをCRMアカウント分類エンティティに変換します。<br>
     * 
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケールID
     * @param dtoList
     *            CRMアカウント分類CSV読込みモデル
     * @return CRMアカウント分類エンティティ
     */
    public List<CrmCcAccountClass> convertDtoList(String companyCd,
            String localeId, List<AccountClassCsvReaderModelDto> dtoList);

}
