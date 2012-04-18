/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.CrmCcAccountClassMapper;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.logic.CrmAccountClassLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;

/**
 * CRMアカウント分類
 * 
 */
public class CrmCcAccountClassController {

    /**
     * {@link CrmAccountClassLogic}
     */
    @Resource
    protected CrmAccountClassLogic crmAccountClassLogic;

    /**
     * CRMアカウント分類コード検索用マッパー
     */
    @Resource
    protected CrmCcAccountClassMapper crmCcAccountClassMapper;

    /**
     * 
     * CRMアカウント分類コードテキストボックス
     * 
     * @param model
     *            CRMアカウント分類コード検索用リクエスト
     * @return CrmCcAccountClassResponseModel
     */
    @Validation
    @Execute
    public CrmCcAccountClassResponseModel getCrmAccountClassName(
            CrmCcAccountClassRequestModel model) {

        if (model.crmAccountClassCd == null
            || model.crmAccountClassCd.length() == 0) {
            return new CrmCcAccountClassResponseModel();
        }

        CrmCcAccountClass criteria = crmCcAccountClassMapper.convert(model);
        CrmCcAccountClass crmCcAccountClassResult =
            crmAccountClassLogic.getByPkNoException(criteria);

        CrmCcAccountClassResponseModel resultmodel =
            crmCcAccountClassMapper.convert(crmCcAccountClassResult);
        return resultmodel;
    }
}
