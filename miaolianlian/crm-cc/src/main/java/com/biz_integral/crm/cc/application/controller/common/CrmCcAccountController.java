/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.CrmCcAccountMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * 
 * CRMアカウントマの一件取得です。
 */
public class CrmCcAccountController {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected CrmAccountLogic crmAccountLogic;

    /**
     * CRMアカウントマッパー。
     */
    @Resource
    protected CrmCcAccountMapper crmCcAccountMapper;

    /**
     * 
     * RMアカウントの一件取得の処理です。
     * 
     * @param model
     *            CRMアカウント検索条件モデル
     * @return CRMアカウント検索結果モデル
     */
    @Execute
    @Validation
    public CrmCcAccountResponseModel getAccountName(
            CrmCcAccountRequestModel model) {
        if (model.crmAccountCd == null || model.crmAccountCd.length() == 0) {
            return new CrmCcAccountResponseModel();
        }

        CrmCcAccountDto criteria = crmCcAccountMapper.convert(model);
        CrmCcAccount crmCcAccountResult = crmAccountLogic.getEntity(criteria);

        CrmCcAccountResponseModel resultmodel =
            crmCcAccountMapper.convert(crmCcAccountResult);

        return resultmodel;

    }
}
