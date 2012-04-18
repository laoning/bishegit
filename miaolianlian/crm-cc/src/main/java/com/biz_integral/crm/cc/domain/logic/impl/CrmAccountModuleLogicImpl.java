/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountModuleNames.deleteFlag;
import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountModuleNames.recordDate;
import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountModuleNames.recordUserCd;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountModuleDao;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAthNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountModule;
import com.biz_integral.crm.cc.domain.logic.CrmAccountModuleLogic;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.util.EntityUtil;

/**
 * CrmAccountModuleLogicロジックの実装です。
 */
public class CrmAccountModuleLogicImpl implements CrmAccountModuleLogic {

    /**
     * アカウント利用モジュール設定DAO
     */
    @Resource
    protected CrmCcAccountModuleDao crmCcAccountModuleDao;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountModule> getEntityList(CrmCcAccountModule entity) {
        return crmCcAccountModuleDao.getEntityList(
            entity,
            LogicalDelete.AVAILABLE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void create(CrmCcAccountModule entity) {
        crmCcAccountModuleDao.insert(entity);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void delete(CrmCcAccountModule entity) {

        entity.setDeleteFlag(true);

        crmCcAccountModuleDao.updateIncludes(
            entity,
            deleteFlag(),
            recordUserCd(),
            recordDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void replace(List<CrmCcAccountModule> targetList) {

        if (targetList == null
            || targetList.isEmpty()
            || targetList.size() == 0) {
            return;
        }

        // アカウント属性を一覧検索します。
        List<CrmCcAccountModule> entityList =
            crmCcAccountModuleDao.getEntityList(
                targetList.get(0),
                LogicalDelete.NO_RESTRICTION);

        boolean isTargetExist = false;
        for (CrmCcAccountModule target : targetList) {
            isTargetExist = false;
            for (CrmCcAccountModule entity : entityList) {
                if (EntityUtil.isTheSamePK(target, entity)) {
                    if (entity.isDeleteFlag()) {
                        // アカウント利用モジュール設定の論理削除を取り消す。
                        entity.setDeleteFlag(false);
                        crmCcAccountModuleDao.updateIncludes(
                            entity,
                            CrmCcAccountClassAthNames.deleteFlag(),
                            CrmCcAccountClassAthNames.recordUserCd(),
                            CrmCcAccountClassAthNames.recordDate());
                    }
                    isTargetExist = true;
                    break;
                }
            }
            if (!isTargetExist) {
                // アカウント分類所属を追加する。
                crmCcAccountModuleDao.insert(target);
            }
        }

        for (CrmCcAccountModule entity : entityList) {
            isTargetExist = false;
            for (CrmCcAccountModule target : targetList) {
                if (EntityUtil.isTheSamePK(target, entity)) {
                    isTargetExist = true;
                    break;
                }
            }
            if (!isTargetExist && !entity.isDeleteFlag()) {
                // アカウント分類所属の論理削除する。
                entity.setDeleteFlag(true);
                crmCcAccountModuleDao.updateIncludes(
                    entity,
                    CrmCcAccountClassAthNames.deleteFlag(),
                    CrmCcAccountClassAthNames.recordUserCd(),
                    CrmCcAccountClassAthNames.recordDate());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long deleteByCrmCcAccountAttr(CrmCcAccountAttr... crmCcAccountAttr) {
        long count = 0;
        for (CrmCcAccountAttr entity : crmCcAccountAttr) {
            List<CrmCcAccountModule> deleteList =
                crmCcAccountModuleDao.findByCrmCcAccountAttr(entity
                    .getCompanyCd(), entity.getDealClass(), entity
                    .getCrmAccountClassCd(), entity.getCrmAccountStatus());
            for (CrmCcAccountModule delete : deleteList) {
                delete(delete);
                count++;
            }
        }
        return count;
    }
}
