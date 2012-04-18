/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountModule;

/**
 * アカウント利用モジュール設定のロジックです。
 */
public interface CrmAccountModuleLogic {

    /**
     * アカウント利用モジュール設定一覧を取得します。
     * 
     * @param entity
     *            アカウント属性モデル
     * 
     * @return アカウント利用モジュール設定リスト
     * 
     */
    public abstract List<CrmCcAccountModule> getEntityList(
            CrmCcAccountModule entity);

    /**
     * アカウント利用モジュール設定を登録します。
     * 
     * @param entity
     *            アカウント属性モデル
     */
    public abstract void create(CrmCcAccountModule entity);

    /**
     * アカウント利用モジュール設定を削除します。
     * 
     * @param entity
     *            アカウント属性モデル
     */
    public abstract void delete(CrmCcAccountModule entity);

    /**
     * アカウント利用モジュール設定を更新します。
     * 
     * @param entityList
     *            アカウント属性モデルリスト
     */
    public abstract void replace(List<CrmCcAccountModule> entityList);

    /**
     * CRMアカウント属性に関連するテーブルを論理削除します。<br>
     * CRMアカウント属性にはPKを必須で設定してください。
     * 
     * @param crmCcAccountAttr
     *            CRMアカウント属性
     * @return 削除件数
     */
    public abstract long deleteByCrmCcAccountAttr(
            CrmCcAccountAttr... crmCcAccountAttr);
}
