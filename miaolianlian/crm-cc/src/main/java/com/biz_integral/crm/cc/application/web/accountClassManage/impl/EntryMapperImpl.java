/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountClassManage.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.web.accountClassManage.EntryDeleteRequestModel;
import com.biz_integral.crm.cc.application.web.accountClassManage.EntryMapper;
import com.biz_integral.crm.cc.application.web.accountClassManage.EntryModelBase;
import com.biz_integral.crm.cc.application.web.accountClassManage.EntryRegistrationRequestModel;
import com.biz_integral.crm.cc.application.web.accountClassManage.EntryUpdateInitializeRequestModel;
import com.biz_integral.crm.cc.application.web.accountClassManage.EntryUpdateInitializeResponseModel;
import com.biz_integral.crm.cc.application.web.accountClassManage.EntryUpdateRequestModel;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;

/**
 * {@link EntryMapper}の実装です。
 */
public class EntryMapperImpl implements EntryMapper {

    /**
     * {@link ContextContainer コンテキストコンテナ}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public EntryUpdateInitializeResponseModel convertInitialResponse(
            CrmCcAccountClass crmCcAccountClass) {
        EntryUpdateInitializeResponseModel model =
            beanMapper.map(
                crmCcAccountClass,
                EntryUpdateInitializeResponseModel.class);
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountClass convert(
            EntryUpdateInitializeRequestModel entryUpdateInitializeRequestModel) {
        CrmCcAccountClass model =
            convertBase(entryUpdateInitializeRequestModel);
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountClass convert(
            EntryRegistrationRequestModel entryRegistrationRequestModel) {
        CrmCcAccountClass model = convertBase(entryRegistrationRequestModel);
        model.setDeleteFlag(false);
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountClass convert(
            EntryUpdateRequestModel entryUpdateRequestModel) {
        return convertBase(entryUpdateRequestModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountClass convert(
            EntryDeleteRequestModel entryDeleteRequestModel) {
        CrmCcAccountClass model = convertBase(entryDeleteRequestModel);
        model.setDeleteFlag(true);
        return model;
    }

    /**
     * 登録、更新、削除のモデルをエンティティに変換します。
     * 
     * @param entryModelBase
     *            モデルの基底クラス
     * @return {@link CrmCcAccountClass CRMアカウント分類エンティティ}
     */
    private CrmCcAccountClass convertBase(EntryModelBase entryModelBase) {

        CrmCcAccountClass model =
            beanMapper.map(entryModelBase, CrmCcAccountClass.class);
        model.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        return model;
    }

}
