/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.application.web.accountGroupAttributeManage.EntryInitializeRequestModel;
import com.biz_integral.crm.cc.application.web.accountGroupAttributeManage.EntryResponseModel;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpModule;

/**
 * CRMアカウントグループ属性のロジックです。
 */
public interface CrmAcctGrpAttrLogic {

    /**
     * CRMアカウントグループ属性登録／更新画面の登録モード初期処理
     * 
     * @param EntryInitializeRequestModel
     *            リスエストモデル
     */
    public abstract EntryResponseModel initialize(
            EntryInitializeRequestModel reqMdl);

    /**
     * CRMアカウントグループ属性を登録します。
     * 
     * @param CrmCcAcctGrp
     *            CRMアカウントグループEntity
     */
    public abstract void create(CrmCcAccountGrpAttr dto);

    /**
     * CRMアカウントグループ属性を登録します。
     * 
     * @param CrmCcAcctGrp
     *            CRMアカウントグループEntity
     */
    public abstract void createModule(List<CrmCcAccountGrpModule> dtoList);

    /**
     * CRMアカウントグループ属性を更新します。
     * 
     * @param CrmCcAcctGrp
     *            CRMアカウントグループEntity
     */
    public abstract void update(CrmCcAccountGrpAttr dto);

    /**
     * CRMアカウントグループ属性を削除します。
     * 
     * @param String
     *            CRMアカウントグループコード
     * @return なし
     */
    public abstract void delete(CrmCcAccountGrpAttr dto);

    /**
     * CRMアカウントグループ属性登録／更新画面の更新モード初期処理
     * 
     * @param EntryInitializeRequestModel
     *            リスエストモデル
     * @return EntryResponseModel
     */
    public abstract EntryResponseModel initializeUpdate(CrmCcAccountGrpAttr dto);

    /**
     * CRMアカウントグループ属性のバージョン番号を取得
     * 
     * @param EntryInitializeRequestModel
     *            リスエストモデル
     * @return String
     */
    public abstract String getVersionNo(CrmCcAccountGrpAttr dto);
}
