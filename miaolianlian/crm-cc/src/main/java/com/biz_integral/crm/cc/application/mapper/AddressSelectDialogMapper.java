/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.AddressSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.AddressSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.domain.dto.AddressSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcSkcsCmn;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 住所選択ダイアログで利用するマッパーです。
 */
public interface AddressSelectDialogMapper {

    /**
     * 住所選択ダイアログ一覧で入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            住所選択ダイアログ一覧で入力された検索条件
     * @return {@link AddressSelectDialogCriteriaDto}
     */
    public AddressSelectDialogCriteriaDto convert(
            AddressSelectDialogFilterRequestModel model);

    /**
     * エンティティクラスを住所選択ダイアログ一覧検索結果モデルに変換します。
     * 
     * @param crmCcSkcsCmn
     *            エンティティクラス
     * @return {@link AddressSelectDialogFilterResponseModel}
     */
    public PagingResult<AddressSelectDialogFilterResponseModel> convert(
            PagingResult<CrmCcSkcsCmn> crmCcSkcsCmn);

}
