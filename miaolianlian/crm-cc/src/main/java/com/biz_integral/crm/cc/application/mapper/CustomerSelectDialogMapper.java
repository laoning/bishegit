/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.CustomerSelectDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CustomerSelectDialogFilterResponseModel;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * 取引先コードの一覧取得で利用するマッパーです。
 */
public interface CustomerSelectDialogMapper {

    /**
     * 
     * 取引先コード入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            取引先コード検索条件モデル
     * @return 取引先コードDTO
     */
    public CustomerSelectDialogCriteriaDto convert(
            CustomerSelectDialogFilterRequestModel model);

    /**
     * エンティティクラスを取引先コード検索条件モデルに変換します。
     * 
     * @param crmCcCustomerCmn
     *            エンティティクラス
     * @return {@link CustomerSelectDialogFilterResponseModel}
     */
    public PagingResult<CustomerSelectDialogFilterResponseModel> convert(
            PagingResult<CrmCcCustomerCmn> crmCcCustomerCmn);
}
