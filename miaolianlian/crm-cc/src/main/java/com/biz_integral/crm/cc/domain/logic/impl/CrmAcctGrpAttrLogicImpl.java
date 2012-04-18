/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.biz_integral.crm.cc.application.web.accountGroupAttributeManage.EntryInitializeRequestModel;
import com.biz_integral.crm.cc.application.web.accountGroupAttributeManage.EntryResponseModel;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountClassDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountGrpAttrDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountGrpModuleDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcModuleDao;
import com.biz_integral.crm.cc.domain.dto.ModuleDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpModule;
import com.biz_integral.crm.cc.domain.entity.CrmCcModule;
import com.biz_integral.crm.cc.domain.logic.CrmAcctGrpAttrLogic;
import com.biz_integral.crm.cc.domain.types.DealClass;
import com.biz_integral.crm.cc.domain.types.IfUpdateWay;
import com.biz_integral.crm.cc.domain.types.MaintenanceTargetFlag;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.persistence.LogicalDelete;

/**
 * CrmAccountLogicロジックの実装です。
 */
public class CrmAcctGrpAttrLogicImpl implements CrmAcctGrpAttrLogic {

    /**
     * コンテキストコンテナ。
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * 利用モジュール設定DAOです。
     */
    @Resource
    protected CrmCcModuleDao crmCcModuleDao;

    /**
     * CRMアカウントグループ分類所属DAO。
     */
    @Resource
    protected CrmCcAccountClassDao crmCcAccountClassDao;

    /**
     * CRMアカウントグループ属性DAO。
     */
    @Resource
    protected CrmCcAccountGrpAttrDao crmCcAccountGrpAttrDao;

    /**
     * CRMアカウントグループ利用モジュール設定DAO。
     */
    @Resource
    protected CrmCcAccountGrpModuleDao crmCcAccountGrpModuleDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public EntryResponseModel initialize(EntryInitializeRequestModel reqMdl) {

        // コンボボックス用データ取得
        EntryResponseModel response = getTypesForInitialize();

        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntryResponseModel initializeUpdate(CrmCcAccountGrpAttr dto) {

        String companyCd = dto.getCompanyCd();
        String crmAccountClassCd = dto.getCrmAccountClassCd();
        String dealClass = dto.getDealClass();

        // コンボボックス用データ取得
        EntryResponseModel response = getTypesForInitialize();

        List<ModuleDto> moduleDtoList = response.moduleList;

        CrmCcAccountGrpModule entity = new CrmCcAccountGrpModule();
        entity.setCompanyCd(companyCd);
        entity.setDealClass(dealClass);
        entity.setCrmAccountClassCd(crmAccountClassCd);

        // CRMアカウントグルール属性検索
        CrmCcAccountGrpAttr entityAttr = new CrmCcAccountGrpAttr();
        entityAttr.setCompanyCd(companyCd);
        entityAttr.setDealClass(dealClass);
        entityAttr.setCrmAccountClassCd(crmAccountClassCd);

        List<CrmCcAccountGrpAttr> entityAttrLst =
            crmCcAccountGrpAttrDao.getEntityList(entityAttr);

        // 存在しない。
        if (entityAttrLst == null || entityAttrLst.size() <= 0) {
            this.throwErrorMessage("E.CRM.CC.00039");
        }

        entityAttr = entityAttrLst.get(0);
        response.crmAccountClassCd = crmAccountClassCd;
        response.dealClass = dealClass;
        if (entityAttr.isMaintenanceTargetFlag()) {
            response.maintenanceTargetFlag = "1";
        }
        response.ifUpdateWay = entityAttr.getIfUpdateWay();
        response.versionNo = entityAttr.getVersionNo().toString();

        // 設定済「CRMアカウントグループ利用モジュール」検索
        List<CrmCcAccountGrpModule> rltLst =
            crmCcAccountGrpModuleDao.getEntityList(entity);

        String moduleId;
        for (ModuleDto md : moduleDtoList) {
            moduleId = md.moduleId;
            for (CrmCcAccountGrpModule e : rltLst) {
                if (moduleId.equals(e.getModuleId())) {
                    md.selected = "true";
                    break;
                }
            }
        }
        return response;
    }

    @Override
    public void create(CrmCcAccountGrpAttr dto) {

        String crmAccountClassCd = dto.getCrmAccountClassCd();

        // CRMアカウント分類存在チェック
        CrmCcAccountClass clasEntity =
            crmCcAccountClassDao.getByPkNoException(
                getCompanyCd(),
                crmAccountClassCd,
                getLocaleId(),
                LogicalDelete.AVAILABLE);

        if (clasEntity == null) {
            throwErrorMessage("E.CRM.CC.00116");
        }

        // 登録済CRMアカウントグループ属性かチェック
        CrmCcAccountGrpAttr entity = new CrmCcAccountGrpAttr();
        entity.setCompanyCd(dto.getCompanyCd());
        entity.setDealClass(dto.getDealClass());
        entity.setCrmAccountClassCd(dto.getCrmAccountClassCd());

        // 登録
        CrmCcAccountGrpAttr e = crmCcAccountGrpAttrDao.getEntityByPk(entity);
        if (e == null) {
            crmCcAccountGrpAttrDao.insert(dto);
        } else {
            if (!e.isDeleteFlag()) {
                // 既に登録済アカウントグループ属性です。
                throwErrorMessage("E.CRM.CC.00120");
            } else {
                e.setDeleteFlag(false);
                // 更新
                crmCcAccountGrpAttrDao.update(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createModule(List<CrmCcAccountGrpModule> dtoList) {

        // 設定されたモジュールIDが無い場合
        if (dtoList == null || dtoList.size() <= 0) {
            return;
        }

        CrmCcAccountGrpModule entity = dtoList.get(0);
        String companyCd = entity.getCompanyCd();
        String crmAccountClassCd = entity.getCrmAccountClassCd();
        String dealClass = entity.getDealClass();

        // 既存のCRMアカウントグループ利用モジュール設定を検索
        CrmCcAccountGrpModule criteria = new CrmCcAccountGrpModule();
        criteria.setCompanyCd(companyCd);
        criteria.setCrmAccountClassCd(crmAccountClassCd);
        criteria.setDealClass(dealClass);

        // 検索
        List<CrmCcAccountGrpModule> rltLst =
            crmCcAccountGrpModuleDao.getEntityList(criteria);
        if (rltLst != null && rltLst.size() > 0) {
            // 存在する場合
            String id;
            boolean isExist = false;
            for (CrmCcAccountGrpModule e : rltLst) {
                id = e.getModuleId();
                isExist = false;
                for (CrmCcAccountGrpModule client : dtoList) {
                    if (id.equals(client.getModuleId())) {
                        isExist = true;
                        break;
                    }
                }
                // DBにあるが画面からデータに含まれてない：削除
                if (!isExist) {
                    e.setDeleteFlag(true);
                    crmCcAccountGrpModuleDao.updateWithLock(e);
                }
            }
        }

        // CRMアカウントグループ利用モジュール設定
        for (CrmCcAccountGrpModule e : dtoList) {
            entity =
                crmCcAccountGrpModuleDao.getEntity(
                    e.getCompanyCd(),
                    e.getDealClass(),
                    e.getCrmAccountClassCd(),
                    e.getModuleId(),
                    null);
            // 登録
            if (entity == null) {
                crmCcAccountGrpModuleDao.insert(e);
            } else {
                entity.setDeleteFlag(false);
                crmCcAccountGrpModuleDao.update(entity);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(CrmCcAccountGrpAttr dto) {
        String crmAccountClassCd = dto.getCrmAccountClassCd();

        // CRMアカウント分類存在チェック
        CrmCcAccountClass clasEntity =
            crmCcAccountClassDao.getByPkNoException(
                getCompanyCd(),
                crmAccountClassCd,
                getLocaleId(),
                LogicalDelete.AVAILABLE);

        if (clasEntity == null) {
            throwErrorMessage("E.CRM.CC.00116");
        }

        // 登録済CRMアカウントグループ属性チェック
        CrmCcAccountGrpAttr entity = new CrmCcAccountGrpAttr();
        entity.setCompanyCd(dto.getCompanyCd());
        entity.setCrmAccountClassCd(dto.getCrmAccountClassCd());
        entity.setDealClass(dto.getDealClass());
        List<CrmCcAccountGrpAttr> rltLst =
            crmCcAccountGrpAttrDao.getEntityList(entity);
        // 更新対象が別の所で更新された。
        if (rltLst == null || rltLst.size() <= 0) {
            // 更新対象データが見つかりません。
            throwErrorMessage("E.CRM.CC.00039");
        }

        // CRMアカウントグループ属性更新
        crmCcAccountGrpAttrDao.updateWithLock(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(CrmCcAccountGrpAttr dto) {

        // 登録済CRMアカウントグループ属性チェック
        CrmCcAccountGrpAttr entity = new CrmCcAccountGrpAttr();
        entity.setCompanyCd(dto.getCompanyCd());
        entity.setCrmAccountClassCd(dto.getCrmAccountClassCd());
        entity.setDealClass(dto.getDealClass());

        List<CrmCcAccountGrpAttr> rltLst =
            crmCcAccountGrpAttrDao.getEntityList(entity);

        // 更新対象が別の所で更新された。
        if (rltLst != null && rltLst.size() > 0) {
            // CRMアカウントグループ属性更新
            dto.setDeleteFlag(true);
            crmCcAccountGrpAttrDao.updateWithLock(dto);
        }

        // 既存のCRMアカウントグループ利用モジュール設定を検索
        CrmCcAccountGrpModule criteria = new CrmCcAccountGrpModule();
        criteria.setCompanyCd(dto.getCompanyCd());
        criteria.setCrmAccountClassCd(dto.getCrmAccountClassCd());
        criteria.setDealClass(dto.getDealClass());

        List<CrmCcAccountGrpModule> dtoList =
            crmCcAccountGrpModuleDao.getEntityList(criteria);

        // CRMアカウントグループ利用モジュール設定を削除します。
        for (CrmCcAccountGrpModule e : dtoList) {
            e.setDeleteFlag(true);
            crmCcAccountGrpModuleDao.updateWithLock(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersionNo(CrmCcAccountGrpAttr dto) {

        String verNo = StringUtils.EMPTY;

        CrmCcAccountGrpAttr entity = new CrmCcAccountGrpAttr();
        entity.setCompanyCd(dto.getCompanyCd());
        entity.setDealClass(dto.getDealClass());
        entity.setCrmAccountClassCd(dto.getCrmAccountClassCd());

        // 登録
        CrmCcAccountGrpAttr e = crmCcAccountGrpAttrDao.getEntityByPk(entity);
        if (e != null) {
            verNo = e.getVersionNo().toString();
        }
        return verNo;
    }

    /**
     * 会社コードを取得します。
     * 
     * @param String
     * 
     */
    private String getCompanyCd() {
        return contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode();
    }

    /**
     * ロケールIDを取得します。
     * 
     * @param String
     * 
     */
    private String getLocaleId() {
        return contextContainer.getUserContext().getLocaleID();
    }

    /**
     * エラーメッセージをスローする。
     * 
     * @param String
     *            messageId：メッセージID
     * 
     * @return なし
     */
    private void throwErrorMessage(String messageId) {
        ValidationResults validationResults = new ValidationResults();
        validationResults.add(new ValidationResult(MessageBuilder.create(
            messageId).toMessage()));
        throw new ValidationException(validationResults);
    }

    /**
     * エラーメッセージをスローする。
     * 
     * @param String
     *            messageId：メッセージID
     * 
     * @return なし
     */
    private EntryResponseModel getTypesForInitialize() {
        EntryResponseModel response = new EntryResponseModel();

        // 取引種別の一覧を取得します。
        response.dealClassList.add(new KeyValueBean());
        for (DealClass dealClass : DealClass.values()) {
            String name =
                this.enumNamesRegistry.getName(dealClass, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.dealClassList
                .add(new KeyValueBean(name, dealClass.value()));
        }

        // 画面メンテ対象の一覧を取得します。
        for (MaintenanceTargetFlag mt : MaintenanceTargetFlag.values()) {
            String name =
                this.enumNamesRegistry.getName(mt, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.maintenanceTargetFlagList.add(new KeyValueBean(name, mt
                .value()));
        }

        // IF更新方法の一覧を取得します。
        response.ifUpdateWayList.add(new KeyValueBean());
        for (IfUpdateWay ifUpdateWay : IfUpdateWay.values()) {
            String name =
                this.enumNamesRegistry.getName(ifUpdateWay, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.ifUpdateWayList.add(new KeyValueBean(name, ifUpdateWay
                .value()));
        }

        // モジュール一覧を取得します。
        List<CrmCcModule> entityLst =
            crmCcModuleDao.getEntityListByCompanyCd(this.getCompanyCd());

        List<ModuleDto> moduleDtoLst = new ArrayList<ModuleDto>();

        // 変換
        for (CrmCcModule entity : entityLst) {
            ModuleDto modDto = new ModuleDto();
            modDto.moduleId = entity.getModuleId();
            modDto.moduleName = entity.getModuleName();
            moduleDtoLst.add(modDto);
        }

        response.moduleList = moduleDtoLst;

        return response;

    }

}
