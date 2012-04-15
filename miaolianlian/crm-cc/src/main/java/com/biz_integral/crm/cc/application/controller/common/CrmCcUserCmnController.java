/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.CrmCcUserCmnMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcUserCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;
import com.biz_integral.crm.cc.domain.logic.UserLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * 担当者コード共通
 * 
 */
public class CrmCcUserCmnController {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected UserLogic userLogic;

    /**
     * 担当者コード共通利用するマッパー
     */
    @Resource
    protected CrmCcUserCmnMapper crmCcUserCmnMapper;

    /**
     * 
     * 担当者コードテキストボックス
     * 
     * @param model
     *            担当者コード検索用リクエスト
     * @return CrmCcUserCmnResponseModel
     */
    @Validation
    @Execute
    public CrmCcUserCmnResponseModel getUserName(CrmCcUserCmnRequestModel model) {
        if (model.chargeUserCd == null || model.chargeUserCd.length() == 0) {
            return new CrmCcUserCmnResponseModel();
        }

        CrmCcUserCmnDto criteria = crmCcUserCmnMapper.convert(model);
        CrmCcUserCmn crmCcUserCmnResult = userLogic.getEntity(criteria);

        CrmCcUserCmnResponseModel resultmodel =
            crmCcUserCmnMapper.convert(crmCcUserCmnResult);
        return resultmodel;
    }
}
