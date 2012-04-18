/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.CrmCcUserCmnRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcUserCmnResponseModel;
import com.biz_integral.crm.cc.domain.dto.CrmCcUserCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;

/**
 * 担当者コード共通利用するマッパーです。
 */
public interface CrmCcUserCmnMapper {

    /**
     * 担当者コード共通をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            担当者コード共通で入力された検索条件
     * @return {@link CrmCcUserCmnDto}
     */
    public CrmCcUserCmnDto convert(CrmCcUserCmnRequestModel model);

    /**
     * 担当者コード共通モデルに変換します。
     * 
     * @param crmCcUserCmn
     *            エンティティクラス
     * @return {@link CrmCcUserCmnResponseModel}
     */
    public CrmCcUserCmnResponseModel convert(CrmCcUserCmn crmCcUserCmn);

}
