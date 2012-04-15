/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.CrmCcAccountClassRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcAccountClassResponseModel;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;

/**
 * CRMアカウント分類コード用マッパーです。
 */
public interface CrmCcAccountClassMapper {

    /**
     * CRMアカウント分類コード検索用リクエストをCRMアカウント分類エンティティに変換します。
     * 
     * @param model
     *            CRMアカウント分類コードに入力された検索条件
     * @return {@link CrmCcAccountClass}
     */
    public CrmCcAccountClass convert(CrmCcAccountClassRequestModel model);

    /**
     * CRMアカウント分類エンティティをCRMアカウント分類コード検索用レスポンスに変換します。
     * 
     * @param crmCcAccountClass
     *            CRMアカウント分類エンティティ
     * @return {@link CrmCcAccountClassResponseModel}
     */
    public CrmCcAccountClassResponseModel convert(
            CrmCcAccountClass crmCcAccountClass);

}
