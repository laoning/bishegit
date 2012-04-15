/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountGrpModuleDao;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpModule;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpModuleNames;
import com.biz_integral.crm.cc.domain.logic.CrmAccountGroupModuleLogic;

/**
 * CrmAccountModuleLogicロジックの実装です。
 */
public class CrmAccountGroupModuleLogicImpl implements
        CrmAccountGroupModuleLogic {

    /**
     * アカウント利用モジュール設定DAO
     */
    @Resource
    protected CrmCcAccountGrpModuleDao crmCcAccountGrpModuleDao;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void delete(CrmCcAccountGrpModule entity) {

        entity.setDeleteFlag(true);

        crmCcAccountGrpModuleDao.updateIncludes(
            entity,
            CrmCcAccountGrpModuleNames.deleteFlag(),
            CrmCcAccountGrpModuleNames.recordDate(),
            CrmCcAccountGrpModuleNames.recordUserCd());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long deleteByCrmCcAccountGrpAttr(
            CrmCcAccountGrpAttr... crmCcAccountGrpAttr) {
        long count = 0;
        for (CrmCcAccountGrpAttr entity : crmCcAccountGrpAttr) {
            List<CrmCcAccountGrpModule> deleteList =
                crmCcAccountGrpModuleDao.findByCrmCcAccountGrpAttr(entity
                    .getCompanyCd(), entity.getDealClass(), entity
                    .getCrmAccountClassCd());
            for (CrmCcAccountGrpModule delete : deleteList) {
                delete(delete);
                count++;
            }
        }
        return count;
    }

}
