/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.logic.FunctionAccessLogic;
import com.biz_integral.service.security.authorization.functional.FunctionalAuthorization;

/**
 * 機能アクセス制御ロジックの実装です。
 */
public class FunctionAccessLogicImpl implements FunctionAccessLogic {

    /**
     * {@link FunctionalAuthorization 機能アクセス権限}
     */
    @Resource
    protected FunctionalAuthorization functionalAuthorization;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkAuthority(String authorityId) {

        return functionalAuthorization.hasAuthority(authorityId);

    }

}
