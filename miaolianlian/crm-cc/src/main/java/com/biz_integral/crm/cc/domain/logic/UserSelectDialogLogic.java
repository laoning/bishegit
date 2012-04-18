/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.dto.UserDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.UserDialogReslutDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * ユーザダイアログのロジックです。
 */
public interface UserSelectDialogLogic {

    /**
     * ユーザ対象一覧を取得します。
     * 
     * @param dto
     *            ユーザ対象一覧検索条件モデル
     * 
     * @return ユーザ対象一覧エンティティ
     * 
     */
    public PagingResult<UserDialogReslutDto> getEntityList(
            UserDialogCriteriaDto dto);
}
