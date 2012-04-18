/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.UserDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.UserDialogFilterResponseModel;
import com.biz_integral.crm.cc.domain.dto.UserDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.UserDialogReslutDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * ユーザダイアログで利用するマッピーです。
 */
public interface UserDialogControllerMapper {

    /**
     * ユーザダイアログで入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            ユーザダイアロで入力された検索条件
     * @return {@link UserDialogCriteriaDto}
     */
    public UserDialogCriteriaDto searchConvert(
            UserDialogFilterRequestModel request);

    /**
     * 
     * ユーザダイアログ表示レスポンスモデルに変換します。
     * 
     * @param searchResult
     *            ユーザ用エンティティ
     * @return {@link UserDialogFilterResponseModel}
     */
    public PagingResult<UserDialogFilterResponseModel> searchConvert(
            PagingResult<UserDialogReslutDto> searchResult);
}
