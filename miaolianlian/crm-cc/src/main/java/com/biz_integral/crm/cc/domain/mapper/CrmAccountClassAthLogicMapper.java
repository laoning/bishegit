/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.mapper;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;

/**
 * アカウント分類所属ロジックの実装で利用するマッパーです。
 */
public interface CrmAccountClassAthLogicMapper {

    /**
     * 入力ストリームのドメインをCrmCcAccountClassAthエンティティに変換します。<br>
     * 
     * @param dtoList
     *            アカウント分類所属チェック条件モデル
     */
    public abstract List<CrmCcAccountClassAth> convertDtlList(
            List<CrmCcAccountClassAthCsvReaderModelDto> dtoList);

    /**
     * CrmCcAccountClassAthエンティティをCrmCcAccountDto変換します。<br>
     * 
     * @param entity
     *            CrmCcAccountClassAthエンティティ
     * @return CrmCcAccountDtoモデル
     */
    public abstract CrmCcAccountDto convertCrmCcAccount(
            CrmCcAccountClassAth entity);

}