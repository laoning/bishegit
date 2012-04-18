/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcDepartmentCmnDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcDepartmentCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.cc.domain.logic.DepartmentLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * 組織ロジックの実装です。
 */
public class DepartmentLogicImpl implements DepartmentLogic {

    /**
     * CrmCcDepartmentCmnのDaoクラスです。
     */
    @Resource
    protected CrmCcDepartmentCmnDao crmCcDepartmentCmnDao;

    /**
     * {@link ParameterLogic}です
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcDepartmentCmn getEntity(CrmCcDepartmentCmnDto dto) {

        dto.departmentSetCd = parameterLogic.getEntity("CRMCC0004").toString();

        Date date =
            DateUtil.parse(
                DateUtil.nowDateString(DateUtil.DATE_FORMAT),
                DateUtil.DATE_FORMAT);

        return crmCcDepartmentCmnDao.getValidLocaleEntityNoException(
            dto.companyCd,
            dto.departmentSetCd,
            dto.departmentCd,
            dto.localeId,
            date);
    }
}
