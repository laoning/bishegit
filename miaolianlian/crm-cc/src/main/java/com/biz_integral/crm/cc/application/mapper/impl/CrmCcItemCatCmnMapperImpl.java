/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.controller.common.CrmCcItemCatCmnRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcItemCatCmnResponseModel;
import com.biz_integral.crm.cc.application.mapper.CrmCcItemCatCmnMapper;
import com.biz_integral.crm.cc.domain.entity.CrmCcItemCatCmn;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;

/**
 * 品目コードの一件取得で利用するマッパーの実装です。
 */
public class CrmCcItemCatCmnMapperImpl implements CrmCcItemCatCmnMapper {

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}の実装です。
     */
    @Resource
    protected BeanMapper beanMapper;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcItemCatCmn convert(CrmCcItemCatCmnRequestModel model) {
        CrmCcItemCatCmn crmCcItemCatCmn =
            beanMapper.map(model, CrmCcItemCatCmn.class);
        crmCcItemCatCmn.setItemCategoryCd(model.itemCd);
        return crmCcItemCatCmn;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcItemCatCmnResponseModel convert(
            CrmCcItemCatCmn crmCcItemCmnResult) {
        CrmCcItemCatCmnResponseModel model = new CrmCcItemCatCmnResponseModel();
        if (crmCcItemCmnResult != null) {
            model =
                beanMapper.map(
                    crmCcItemCmnResult,
                    CrmCcItemCatCmnResponseModel.class);
        }

        return model;
    }
}
