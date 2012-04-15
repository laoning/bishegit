/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountAttributeManage;

import java.util.List;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountModule;
import com.biz_integral.crm.cc.domain.entity.CrmCcModule;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント属性登録／更新画面で利用するマッパーです。
 */
public interface EntryMapper {

    /**
     * アカウント属性登録/更新で初期化条件をCRMアカウント属性に変換します。
     * 
     * @param request
     *            初期化条件モデル
     * @return {@link CrmCcAccountAttr}
     */
    public abstract CrmCcAccountAttr convertInitializeToAttr(
            EntryInitializeRequestModel request);

    /**
     * 利用モジュール設定を初期表示レスポンスモデルに変換します。
     * 
     * @param crmCcAccountAttr
     *            アカウント属性
     * @return {@link EntryAccountModuleModel}
     */
    public abstract EntryInitializeResponseModel convertAttrToResponse(
            CrmCcAccountAttr crmCcAccountAttr);

    /**
     * 利用モジュール設定を初期表示レスポンスモデルに変換します。
     * 
     * @param crmCcModuleList
     *            アカウント利用モジュール設定モデルリスト
     * @return {@link EntryAccountModuleModel}
     */
    public abstract PagingResult<EntryAccountModuleModel> convertToModuleList(
            List<CrmCcModule> crmCcModuleList);

    /**
     * アカウント属性をアカウント利用モジュール設定に変換します。
     * 
     * @param crmCcAccountAttr
     *            アカウント属性
     * @return {@link CrmCcAccountModule}
     */
    public abstract CrmCcAccountModule convertAttrToModule(
            CrmCcAccountAttr crmCcAccountAttr);

    /**
     * アカウント属性保存条件モデルをアカウント属性モデルに変換します。
     * 
     * @param model
     *            アカウント属性
     * @return {@link CrmCcAccountAttr}
     */
    public abstract CrmCcAccountAttr convertSaveToAttr(
            EntrySaveRequestModel model);

    /**
     * アカウント属性保存条件モデルをアカウント利用モジュール設定モデルに変換します。
     * 
     * @param model
     *            アカウント属性
     * @return {@link CrmCcAccountModule}
     */
    public abstract CrmCcAccountModule convertSaveToModule(
            EntrySaveRequestModel model);

    /**
     * アカウント属性保存条件モデルをアカウント利用モジュール設定モデルリストに変換します。
     * 
     * @param model
     *            アカウント属性
     * @return {@link CrmCcAccountModule}
     */
    public List<CrmCcAccountModule> convertSaveToModuleList(
            EntrySaveRequestModel model);

    /**
     * アカウント属性更新条件モデルをアカウント属性モデルに変換します。
     * 
     * @param model
     *            アカウント属性
     * @return {@link CrmCcAccountAttr}
     */
    public abstract CrmCcAccountAttr convertUpdateToAttr(
            EntryUpdateRequestModel model);

    /**
     * アカウント属性更新条件モデルをアカウント利用モジュール設定モデルに変換します。
     * 
     * @param model
     *            アカウント属性
     * @return {@link CrmCcAccountModule}
     */
    public abstract CrmCcAccountModule convertUpdateToModule(
            EntryUpdateRequestModel model);

    /**
     * アカウント属性更新条件モデルをアカウント利用モジュール設定モデルリストに変換します。
     * 
     * @param model
     *            アカウント属性
     * @return {@link CrmCcAccountModule}
     */
    public List<CrmCcAccountModule> convertUpdateToModuleList(
            EntryUpdateRequestModel model);

    /**
     * アカウント属性削除条件モデルをアカウント属性モデルに変換します。
     * 
     * @param model
     *            アカウント属性
     * @return {@link CrmCcAccountModule}
     */
    public abstract CrmCcAccountAttr convertDelete(EntryDeleteRequestModel model);

}
