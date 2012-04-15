/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSelectDialogResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント分類選択ダイアログのロジックです。
 */
public interface AccountClassSelectDialogLogic {

    /**
     * アカウント分類一覧を取得します。
     * 
     * @param dto
     *            アカウント分類一覧検索条件モデル
     * 
     * @return アカウント分類一覧エンティティ
     * 
     */
    public abstract PagingResult<AccountClassSelectDialogResultDto> getAccountClassList(
            AccountClassSelectDialogCriteriaDto dto);
}
