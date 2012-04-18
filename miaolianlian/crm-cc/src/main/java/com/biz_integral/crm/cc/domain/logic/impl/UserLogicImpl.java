/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcDepartmentCmnDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcUserCmnDao;
import com.biz_integral.crm.cc.domain.dto.CrmCcUserCmnDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcUserCmn;
import com.biz_integral.crm.cc.domain.logic.UserLogic;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * ユーザーロジックです。
 */
public class UserLogicImpl implements UserLogic {

    /**
     * CrmCcUserCmnのDaoクラスです。
     */
    @Resource
    protected CrmCcUserCmnDao crmCcUserCmnDao;

    /**
     * CrmCcDepartmentCmnのDaoクラスです。
     */
    @Resource
    protected CrmCcDepartmentCmnDao crmCcDepartmentCmnDao;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcUserCmn getEntity(CrmCcUserCmnDto dto) {

        Date date =
            DateUtil.parse(
                DateUtil.nowDateString(DateUtil.DATE_FORMAT),
                DateUtil.DATE_FORMAT);
        return crmCcUserCmnDao.getValidLocaleEntityNoException(
            dto.chargeUserCd,
            dto.localeId,
            date);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcUserCmn> getEntityList(CrmCcUserCmn entity) {
        return crmCcUserCmnDao.getEntityList(entity);
    }

}
