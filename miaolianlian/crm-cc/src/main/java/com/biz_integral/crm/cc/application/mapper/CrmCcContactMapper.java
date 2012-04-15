/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.CrmCcContactRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcContactResponseModel;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;

/**
 * CRMコンタクトの一件取得で利用するマッパーです。
 */
public interface CrmCcContactMapper {

    /**
     * 
     * CRMコンタクト入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            CRMアカウント検索条件モデル
     * @return CRMアカウントDTO
     */
    public CrmCcContactDto convert(CrmCcContactRequestModel model);

    /**
     * エンティティクラスをCRMコンタクト検索条件モデルに変換します。
     * 
     * @param crmCcContact
     *            エンティティクラス
     * @return {@link CrmCcContactResponseModel}
     */
    public CrmCcContactResponseModel convert(CrmCcContact crmCcContact);
}
