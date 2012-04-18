/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.CrmCcDepartmentCmnRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcDepartmentCmnResponseModel;
import com.biz_integral.crm.cc.domain.dto.CrmCcDepartmentCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;

/**
 * 組織コードの一件取得で利用するマッパーです。
 */
public interface CrmCcDepartmentCmnMapper {

    /**
     * 
     * 組織コード入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            組織コード検索条件モデル
     * @return 組織コードDTO
     */
    public CrmCcDepartmentCmnDto convert(CrmCcDepartmentCmnRequestModel model);

    /**
     * エンティティクラスを組織コード検索条件モデルに変換します。
     * 
     * @param crmCcDepartmentCmn
     *            エンティティクラス
     * @return {@link CrmCcDepartmentCmnResponseModel}
     */
    public CrmCcDepartmentCmnResponseModel convert(
            CrmCcDepartmentCmn crmCcDepartmentCmn);
}
