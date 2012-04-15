/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.web.parameterManage.EntryCreateRequestModel;
import com.biz_integral.crm.cc.application.web.parameterManage.EntryInitializeLayoutRequestModel;
import com.biz_integral.crm.cc.application.web.parameterManage.EntryInitializeLayoutResponseModel;
import com.biz_integral.crm.cc.application.web.parameterManage.EntryMapper;
import com.biz_integral.crm.cc.domain.context.CrmContext;
import com.biz_integral.crm.cc.domain.dto.ParameterManageDto;
import com.biz_integral.extension.beans.BizBeans;

/**
 * {@link EntryMapper}の実装クラス。
 */
public class EntryMapperImpl implements EntryMapper {

    /** CRMコンテキスト */
    @Resource
    protected CrmContext crmContext;

    /**
     *{@inheritDoc}
     */
    @Override
    public ParameterManageDto getLayoutSubmitModel(
            EntryInitializeLayoutRequestModel requestModel) {

        ParameterManageDto model =
            BizBeans
                .createAndCopy(ParameterManageDto.class, requestModel)
                .execute();
        model.setCompanyCd(crmContext.getCompanyCd());
        model.setLocaleId(crmContext.getLocaleId());
        model.setSrchBaseDte(new Date());
        return model;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public EntryInitializeLayoutResponseModel setLayoutResponseModel(
            ParameterManageDto model) {

        EntryInitializeLayoutResponseModel responseModel =
            BizBeans.createAndCopy(
                EntryInitializeLayoutResponseModel.class,
                model).execute();

        return responseModel;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ParameterManageDto getRegistrationSubmitModel(
            EntryCreateRequestModel requestModel) {

        ParameterManageDto model =
            BizBeans
                .createAndCopy(ParameterManageDto.class, requestModel)
                .execute();

        model.setCompanyCd(crmContext.getCompanyCd());
        model.setLocaleId(crmContext.getLocaleId());
        model.setSrchBaseDte(new Date());
        model.setUserCd(crmContext.getUserCd());
        return model;
    }

}
