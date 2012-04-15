/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupSelectDialogResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウントグループ選択ダイアログのロジックです。
 */
public interface AccountGroupSelectDialogLogic {

    /**
     * アカウントグループ一覧を取得します。
     * 
     * @param dto
     *            アカウントグループ一覧検索条件モデル
     * 
     * @return アカウントグループ一覧エンティティ
     * 
     */
    public abstract PagingResult<AccountGroupSelectDialogResultDto> getAccountGroupList(
            AccountGroupSelectDialogCriteriaDto dto);
}
