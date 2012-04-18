/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.ContactSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.ContactSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * コンタクト選択ダイアログで利用するマッパーです。
 */
public interface ContactDialogMapper {
    /**
     * コンタクト検索画面で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            コンタクト検索画面で入力された検索条件
     * @return {@link ContactSearchCriteriaDto}
     */
    public ContactSearchCriteriaDto convert(
            ContactSelectDialogFilterRequestModel model);

    /**
     * エンティティクラスをコンタクト一覧検索条件モデルに変換します。
     * 
     * @param crmCcContact
     *            コンタクト検索画面一覧のエンティティクラス
     * @return {@link ContactSelectDialogFilterResponseModel}
     */
    public PagingResult<ContactSelectDialogFilterResponseModel> convert(
            PagingResult<ContactSearchResultDto> crmCcContact);

}
