/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.entity.CrmCcModule;
import com.biz_integral.service.persistence.LogicalDelete;

/**
 * 利用モジュール設定のロジックです。
 */
public interface CrmModuleLogic {

    /**
     * 利用モジュール設定一覧を取得します。
     * 
     * @param companyCd
     *            会社コード
     * @param logicalDelete
     *            論理削除の列挙体
     * 
     * @return 利用モジュール設定エンティティ一覧
     * 
     */
    public abstract List<CrmCcModule> findByCompanyCd(String companyCd,
            LogicalDelete logicalDelete);

}
