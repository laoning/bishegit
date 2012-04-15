/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcModuleDao;
import com.biz_integral.crm.cc.domain.entity.CrmCcModule;
import com.biz_integral.crm.cc.domain.logic.CrmModuleLogic;
import com.biz_integral.service.persistence.LogicalDelete;

/**
 * CrmAccountAttributeLogicロジックの実装です。
 */
public class CrmModuleLogicImpl implements CrmModuleLogic {

    /**
     * アカウント分類に関するDAO
     */
    @Resource
    protected CrmCcModuleDao crmCcModuleDao;

    @Override
    public List<CrmCcModule> findByCompanyCd(String companyCd,
            LogicalDelete logicalDelete) {
        return crmCcModuleDao
            .getEntityListByCompanyCd(companyCd, logicalDelete);
    }

}
