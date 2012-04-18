/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.CrmCcItemCatCmnRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcItemCatCmnResponseModel;
import com.biz_integral.crm.cc.domain.entity.CrmCcItemCatCmn;

/**
 * 品目コードの一件取得で利用するマッパーです。
 */
public interface CrmCcItemCatCmnMapper {

    /**
     * 
     * 品目コード入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            品目コード検索条件モデル
     * @return 品目コードDTO
     */
    public CrmCcItemCatCmn convert(CrmCcItemCatCmnRequestModel model);

    /**
     * エンティティクラスを品目コード検索結果モデルに変換します。
     * 
     * @param crmCcItemCmnResult
     *            エンティティクラス
     * @return {@link CrmCcItemCatCmnResponseModel}
     */
    public CrmCcItemCatCmnResponseModel convert(
            CrmCcItemCatCmn crmCcItemCmnResult);
}
