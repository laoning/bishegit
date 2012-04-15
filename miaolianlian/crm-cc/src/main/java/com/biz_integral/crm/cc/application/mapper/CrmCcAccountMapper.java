/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.CrmCcAccountRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcAccountResponseModel;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;

/**
 * アカウントコードの一件取得で利用するマッパーです。
 */
public interface CrmCcAccountMapper {

    /**
     * 
     * アカウントコード入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            CRMアカウント検索条件モデル
     * @return CRMアカウントDTO
     */
    public CrmCcAccountDto convert(CrmCcAccountRequestModel model);

    /**
     * エンティティクラスをアカウントコード検索結果モデルに変換します。
     * 
     * @param crmCcAccountResult
     *            エンティティクラス
     * @return {@link CrmCcAccountResponseModel}
     */
    public CrmCcAccountResponseModel convert(CrmCcAccount crmCcAccountResult);
}
