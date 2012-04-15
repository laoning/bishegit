/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcDepartmentAthCmnDao;
import com.biz_integral.crm.cc.domain.logic.CrmCcDepartmentAthCmnLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;

/**
 * 組織所属_共通のロジックです。
 */
public class CrmCcDepartmentAthCmnLogicImpl implements
        CrmCcDepartmentAthCmnLogic {

    /**
     * {@link CrmCcDepartmentAthCmnDao}
     */
    @Resource
    protected CrmCcDepartmentAthCmnDao crmCcDepartmentAthCmnDao;

    /**
     * {@link ParameterLogic}
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * パラーメタCRM用組織セットコードの名称
     */
    private static final String DEPARTMENTSETCD = "CRMCC0004";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getUserCdList(List<String> departmentCdList) {
        // 会社組織セットコード
        String departmentSetCd =
            (String) parameterLogic.getEntity(DEPARTMENTSETCD);
        return crmCcDepartmentAthCmnDao.findByDepartmentCdList(
            departmentCdList,
            departmentSetCd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getDepartmentCdList(String userCd) {
        // 会社組織セットコード
        String departmentSetCd =
            (String) parameterLogic.getEntity(DEPARTMENTSETCD);
        return crmCcDepartmentAthCmnDao.fingByUserCd(userCd, departmentSetCd);
    }

}
