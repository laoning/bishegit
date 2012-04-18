/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.web.accountAttributeManage.EntryAccountModuleModel;
import com.biz_integral.crm.cc.application.web.accountAttributeManage.EntryDeleteRequestModel;
import com.biz_integral.crm.cc.application.web.accountAttributeManage.EntryInitializeRequestModel;
import com.biz_integral.crm.cc.application.web.accountAttributeManage.EntryInitializeResponseModel;
import com.biz_integral.crm.cc.application.web.accountAttributeManage.EntryMapper;
import com.biz_integral.crm.cc.application.web.accountAttributeManage.EntryModuleRequestModel;
import com.biz_integral.crm.cc.application.web.accountAttributeManage.EntrySaveRequestModel;
import com.biz_integral.crm.cc.application.web.accountAttributeManage.EntryUpdateRequestModel;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountModule;
import com.biz_integral.crm.cc.domain.entity.CrmCcModule;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.service.persistence.pager.PagingResult;

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
    public CrmCcAccountAttr convertInitializeToAttr(
            EntryInitializeRequestModel request) {

        CrmCcAccountAttr entity =
            beanMapper.map(request, CrmCcAccountAttr.class);

        entity.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntryInitializeResponseModel convertAttrToResponse(
            CrmCcAccountAttr crmCcAccountAttr) {

        EntryInitializeResponseModel response =
            beanMapper
                .map(crmCcAccountAttr, EntryInitializeResponseModel.class);
        if (crmCcAccountAttr.isMaintenanceTargetFlag()) {
            response.maintenanceTargetFlag = "1";
        } else {
            response.maintenanceTargetFlag = "0";
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<EntryAccountModuleModel> convertToModuleList(
            List<CrmCcModule> crmCcModuleList) {

        PagingResult<EntryAccountModuleModel> modelList =
            new PagingResult<EntryAccountModuleModel>();

        for (CrmCcModule entity : crmCcModuleList) {
            EntryAccountModuleModel model =
                beanMapper.map(entity, EntryAccountModuleModel.class);
            modelList.add(model);
        }
        return modelList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountModule convertAttrToModule(
            CrmCcAccountAttr crmCcAccountAttr) {
        return beanMapper.map(crmCcAccountAttr, CrmCcAccountModule.class);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountAttr convertSaveToAttr(EntrySaveRequestModel model) {

        CrmCcAccountAttr crmCcAccountAttr =
            beanMapper.map(model, CrmCcAccountAttr.class);

        crmCcAccountAttr.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());

        return crmCcAccountAttr;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountModule convertSaveToModule(EntrySaveRequestModel model) {

        CrmCcAccountModule crmCcAccountModule =
            beanMapper.map(model, CrmCcAccountModule.class);

        crmCcAccountModule.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());

        return crmCcAccountModule;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountModule> convertSaveToModuleList(
            EntrySaveRequestModel model) {

        List<CrmCcAccountModule> result = new ArrayList<CrmCcAccountModule>();

        for (EntryModuleRequestModel moduleModel : model.moduleList) {
            if (moduleModel.select != null
                && moduleModel.select.equalsIgnoreCase("true")) {
                CrmCcAccountModule crmCcAccountModule =
                    this.convertSaveToModule(model);
                crmCcAccountModule.setModuleId(moduleModel.moduleId);
                result.add(crmCcAccountModule);
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountAttr convertUpdateToAttr(EntryUpdateRequestModel model) {

        CrmCcAccountAttr crmCcAccountAttr =
            beanMapper.map(model, CrmCcAccountAttr.class);

        crmCcAccountAttr.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());

        return crmCcAccountAttr;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountModule convertUpdateToModule(
            EntryUpdateRequestModel model) {

        CrmCcAccountModule crmCcAccountModule =
            beanMapper.map(model, CrmCcAccountModule.class);

        crmCcAccountModule.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());

        return crmCcAccountModule;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountModule> convertUpdateToModuleList(
            EntryUpdateRequestModel model) {

        List<CrmCcAccountModule> result = new ArrayList<CrmCcAccountModule>();

        for (EntryModuleRequestModel moduleModel : model.moduleList) {
            if (moduleModel.select != null
                && moduleModel.select.equalsIgnoreCase("true")) {
                CrmCcAccountModule crmCcAccountModule =
                    this.convertUpdateToModule(model);
                crmCcAccountModule.setModuleId(moduleModel.moduleId);
                result.add(crmCcAccountModule);
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountAttr convertDelete(EntryDeleteRequestModel model) {

        CrmCcAccountAttr crmCcAccountAttr =
            beanMapper.map(model, CrmCcAccountAttr.class);

        crmCcAccountAttr.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());

        crmCcAccountAttr.setDeleteFlag(true);

        return crmCcAccountAttr;
    }

}
