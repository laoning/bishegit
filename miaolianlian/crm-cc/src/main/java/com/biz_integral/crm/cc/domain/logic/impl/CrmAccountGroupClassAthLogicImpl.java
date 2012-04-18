/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcAcctGrpClassAthDao;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAthNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpClassAth;
import com.biz_integral.crm.cc.domain.logic.CrmAccountGroupClassAthLogic;

/**
 * CrmAccountClassAthLogicロジックの実装です。
 */
public class CrmAccountGroupClassAthLogicImpl implements
        CrmAccountGroupClassAthLogic {

    /**
     * アカウント分類所属DAO
     */
    @Resource
    protected CrmCcAcctGrpClassAthDao crmCcAcctGrpClassAthDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public long deleteByCrmCcAccountClass(
            CrmCcAccountClass... crmCcAccountClass) {
        long count = 0;
        for (CrmCcAccountClass entity : crmCcAccountClass) {
            List<CrmCcAcctGrpClassAth> deleteList =
                crmCcAcctGrpClassAthDao.findByCrmCcAccountClass(entity
                    .getCompanyCd(), entity.getCrmAccountClassCd());
            for (CrmCcAcctGrpClassAth delete : deleteList) {
                delete(delete);
                count++;
            }
        }
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(CrmCcAcctGrpClassAth crmCcAcctGrpClassAth) {
        crmCcAcctGrpClassAth.setDeleteFlag(true);
        crmCcAcctGrpClassAthDao.updateIncludes(
            crmCcAcctGrpClassAth,
            CrmCcAccountClassAthNames.deleteFlag(),
            CrmCcAccountClassAthNames.recordDate(),
            CrmCcAccountClassAthNames.recordUserCd());
    }

}
