/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpModule;

/**
 * アカウント利用モジュール設定のロジックです。
 */
public interface CrmAccountGroupModuleLogic {

    /**
     * アカウント利用モジュール設定を削除します。
     * 
     * @param entity
     *            アカウント属性モデル
     */
    public abstract void delete(CrmCcAccountGrpModule entity);

    /**
     * CRMアカウント属性に関連するテーブルを論理削除します。<br>
     * CRMアカウント属性にはPKを必須で設定してください。
     * 
     * @param crmCcAccountGrpAttr
     *            CRMアカウント属性
     * @return 削除件数
     */
    public abstract long deleteByCrmCcAccountGrpAttr(
            CrmCcAccountGrpAttr... crmCcAccountGrpAttr);
}
