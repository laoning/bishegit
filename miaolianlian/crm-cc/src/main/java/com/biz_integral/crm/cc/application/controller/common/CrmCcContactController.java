/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.CrmCcContactMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * 
 * CRMコンタクトの一件取得です。
 */
public class CrmCcContactController {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected CrmContactLogic crmContactLogic;

    /**
     * CRMコンタクトマッパー。
     */
    @Resource
    protected CrmCcContactMapper crmCcContactMapper;

    /**
     * 
     * CRMコンタクトの一件取得の処理です。
     * 
     * @param model
     *            CRMコンタクト検索条件モデル
     * @return {@link CrmCcContactRequestModel}
     */
    @Execute
    @Validation
    public CrmCcContactResponseModel getContactName(
            CrmCcContactRequestModel model) {
        if (model.crmContactCd == null || model.crmContactCd.length() == 0) {
            return new CrmCcContactResponseModel();
        }

        CrmCcContactDto criteria = crmCcContactMapper.convert(model);

        CrmCcContact crmCcContact = crmContactLogic.getEntity(criteria);

        CrmCcContactResponseModel resultmodel =
            crmCcContactMapper.convert(crmCcContact);

        return resultmodel;

    }
}
