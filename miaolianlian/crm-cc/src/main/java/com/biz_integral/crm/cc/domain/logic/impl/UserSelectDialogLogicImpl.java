/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcUserCmnDao;
import com.biz_integral.crm.cc.domain.dto.UserDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.UserDialogReslutDto;
import com.biz_integral.crm.cc.domain.logic.UserSelectDialogLogic;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * ユーザダイアログのロジックの実装です。
 */
public class UserSelectDialogLogicImpl implements UserSelectDialogLogic {

    /**
     * ユーザに関するDAO
     */
    @Resource
    protected CrmCcUserCmnDao crmCcUserCmnDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<UserDialogReslutDto> getEntityList(
            UserDialogCriteriaDto dto) {

        return crmCcUserCmnDao.findByUserDialogCriteriaDto(dto);
    }

}
