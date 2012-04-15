/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountModule;
import com.biz_integral.crm.cc.domain.entity.CrmCcModule;
import com.biz_integral.crm.cc.domain.logic.CrmAccountAttributeLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountModuleLogic;
import com.biz_integral.crm.cc.domain.logic.CrmModuleLogic;
import com.biz_integral.crm.cc.domain.types.CrmAccountStatus;
import com.biz_integral.crm.cc.domain.types.DealClass;
import com.biz_integral.crm.cc.domain.types.IfUpdateWay;
import com.biz_integral.crm.cc.domain.types.MaintenanceTargetFlag;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.service.persistence.LogicalDelete;

/**
 * アカウント属性登録／更新画面のコントローラです。
 */
public class EntryController {

    /**
     * 区分に関する名称管理APIです。
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link ContextContainer}です
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link EntryMapper アカウント属性登録／更新Mapper}
     */
    @Resource
    protected EntryMapper entryMapper;

    /**
     * {@link CrmAccountAttributeLogic アカウント属性Logic}
     */
    @Resource
    protected CrmAccountAttributeLogic crmAccountAttributeLogic;

    /**
     * {@link CrmAccountModuleLogic アカウント利用モジュール設定Logic}
     */
    @Resource
    protected CrmAccountModuleLogic crmAccountModuleLogic;

    /**
     * {@link CrmModuleLogic 利用モジュールLogic}
     */
    @Resource
    protected CrmModuleLogic crmModuleLogic;

    /**
     * 処理モード（更新）
     */
    private static final String OPERATION_MODE_UPDATE = "update";

    /**
     * 初期化を行います。
     * 
     * @param request
     *            初期化用引数モデル
     * 
     * @return 画面初期化に利用する項目
     */
    @Execute
    public EntryInitializeResponseModel initialize(
            EntryInitializeRequestModel request) {

        EntryInitializeResponseModel response =
            new EntryInitializeResponseModel();

        List<CrmCcModule> crmCcModuleList =
            crmModuleLogic.findByCompanyCd(contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode(), LogicalDelete.AVAILABLE);

        List<EntryAccountModuleModel> moduleModelList =
            entryMapper.convertToModuleList(crmCcModuleList);

        if (OPERATION_MODE_UPDATE.equalsIgnoreCase(request.processModeType)) {

            CrmCcAccountAttr criteria =
                entryMapper.convertInitializeToAttr(request);

            CrmCcAccountAttr attrEntity =
                crmAccountAttributeLogic.getEntity(criteria);
            response = entryMapper.convertAttrToResponse(attrEntity);

            CrmCcAccountModule moduleEntity =
                entryMapper.convertAttrToModule(attrEntity);

            List<CrmCcAccountModule> crmCcAccountModuleList =
                crmAccountModuleLogic.getEntityList(moduleEntity);

            for (EntryAccountModuleModel model : moduleModelList) {
                for (CrmCcAccountModule crmCcAccountModule : crmCcAccountModuleList) {
                    if (model.moduleId.equals(crmCcAccountModule.getModuleId())) {
                        model.select = "true";
                        break;
                    }
                }
            }
        }
        response.moduleList.addAll(moduleModelList);

        response.dealClassList.add(new KeyValueBean());
        for (DealClass type : DealClass.values()) {
            String name =
                enumNamesRegistry.getName(type, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.dealClassList.add(new KeyValueBean(name, type.value()));
        }

        response.crmAccountStatusList.add(new KeyValueBean());
        for (CrmAccountStatus type : CrmAccountStatus.values()) {
            String name =
                enumNamesRegistry.getName(type, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.crmAccountStatusList.add(new KeyValueBean(name, type
                .value()));
        }

        for (MaintenanceTargetFlag type : MaintenanceTargetFlag.values()) {
            String name =
                enumNamesRegistry.getName(type, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.maintTargetList.add(new KeyValueBean(name, type.value()));
        }

        response.ifUpdateWayList.add(new KeyValueBean());
        for (IfUpdateWay type : IfUpdateWay.values()) {
            String name =
                enumNamesRegistry.getName(type, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.ifUpdateWayList.add(new KeyValueBean(name, type.value()));
        }

        return response;
    }

    /**
     * アカウント属性の登録します。
     * 
     * @param request
     *            登録用引数モデル
     */
    @Execute
    @Validation
    public void save(EntrySaveRequestModel request) {

        CrmCcAccountAttr crmCcAccountAttr =
            entryMapper.convertSaveToAttr(request);

        crmAccountAttributeLogic.create(crmCcAccountAttr);
        List<CrmCcAccountModule> moduleList =
            entryMapper.convertSaveToModuleList(request);
        crmAccountModuleLogic.replace(moduleList);
    }

    /**
     * 
     * アカウント属性を更新します。
     * 
     * @param request
     *            アカウント属性登録／更新画面の更新引数モデル
     */
    @Execute
    @Validation
    public void update(EntryUpdateRequestModel request) {

        CrmCcAccountAttr crmCcAccountAttr =
            entryMapper.convertUpdateToAttr(request);

        crmAccountAttributeLogic.update(crmCcAccountAttr);

        List<CrmCcAccountModule> moduleList =
            entryMapper.convertUpdateToModuleList(request);
        crmAccountModuleLogic.replace(moduleList);
    }

    /**
     * 
     * アカウント属性を削除します。
     * 
     * @param request
     *            アカウント属性登録／更新画面の削除引数モデル
     */
    @Execute
    public void delete(EntryDeleteRequestModel request) {
        CrmCcAccountAttr crmCcAccountAttr = entryMapper.convertDelete(request);
        crmAccountAttributeLogic.delete(crmCcAccountAttr);
    }
}
