/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

/**
 * 機能アクセス制御のロジックです。
 */
public interface FunctionAccessLogic {

    /**
     * 権限チェックをします。
     * 
     * @param authorityId
     *            権限ID
     * @return true:機能アクセス制御が付与されている/false:機能アクセス制御が付与されていない
     */
    public abstract boolean checkAuthority(String authorityId);

}
