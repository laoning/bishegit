/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.CrmCcItemCatCmnMapper;
import com.biz_integral.crm.cc.domain.entity.CrmCcItemCatCmn;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.sa.domain.logic.ItemCatCmnLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * 
 * 品目コードの一件取得です。
 */
public class CrmCcItemCatCmnController {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ItemCatCmnLogic itemCatCmnLogic;

    /**
     * {@link ParameterLogic}です
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * 品目コードマッパー。
     */
    @Resource
    protected CrmCcItemCatCmnMapper crmCcItemCmnMapper;

    /**
     * 
     * 品目コードの一件取得です。
     * 
     * @param model
     *            品目コード検索結果モデル 　
     * @return {@link CrmCcItemCatCmnResponseModel}
     */
    @Execute
    @Validation
    public CrmCcItemCatCmnResponseModel getItemCategoryName(
            CrmCcItemCatCmnRequestModel model) {
        if (model.itemCd == null || model.itemCd.length() == 0) {
            return new CrmCcItemCatCmnResponseModel();
        }

        CrmCcItemCatCmn criteria = crmCcItemCmnMapper.convert(model);

        CrmCcItemCatCmn crmCcItemCmnResult =
            itemCatCmnLogic.getEntity(criteria);

        CrmCcItemCatCmnResponseModel resultmodel =
            crmCcItemCmnMapper.convert(crmCcItemCmnResult);

        return resultmodel;

    }
}
