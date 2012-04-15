/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AccountBelongClassSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountBelongClassSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類所属のロジックです。
 */
public interface CrmAccountBelongClassLogic {

    /**
     * アカウント分類所属一覧を取得します。
     * 
     * @param dto
     *            アカウント分類所属一覧検索条件モデル
     * 
     * @return アカウント分類所属一覧エンティティ
     * 
     */
    public abstract PagingResult<AccountBelongClassSearchResultDto> getAccountBelongClassList(
            AccountBelongClassSearchCriteriaDto dto);

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

}
