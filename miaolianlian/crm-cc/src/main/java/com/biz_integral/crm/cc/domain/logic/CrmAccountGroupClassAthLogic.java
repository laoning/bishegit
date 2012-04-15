/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpClassAth;

/**
 * アカウントグループ分類所属のロジックです。
 */
public interface CrmAccountGroupClassAthLogic {

    /**
     * CRMアカウント分類に関連するテーブルを論理削除します。<br>
     * CRMアカウント分類にはPKを必須で設定してください。
     * 
     * @param crmCcAccountClass
     *            CRMアカウント分類
     * @return 削除件数
     */
    public abstract long deleteByCrmCcAccountClass(
            CrmCcAccountClass... crmCcAccountClass);

    /**
     * アカウントグループ分類所属を論理削除します。
     * 
     * @param crmCcAcctGrpClassAth
     */
    public abstract void delete(CrmCcAcctGrpClassAth crmCcAcctGrpClassAth);
}
