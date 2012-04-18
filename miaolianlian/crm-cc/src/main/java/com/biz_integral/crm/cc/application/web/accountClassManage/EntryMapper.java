/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountClassManage;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;

/**
 * アカウント分類登録／更新画面で利用するマッパーです。
 */
public interface EntryMapper {

    /**
     * アカウント分類登録/更新画面の初期化モデルをアカウント分類エンティティに変換します。
     * 
     * @param entryUpdateInitializeRequestModel
     *            アカウント分類登録/更新画面の初期化モデル
     * @return CRMアカウント分類エンティティ
     */
    public abstract CrmCcAccountClass convert(
            EntryUpdateInitializeRequestModel entryUpdateInitializeRequestModel);

    /**
     * アカウント分類登録/更新画面の登録モデルをアカウント分類エンティティに変換します。
     * 
     * @param entryRegistrationRequestModel
     *            アカウント分類登録/更新画面の登録モデル
     * @return CRMアカウント分類エンティティ
     */
    public abstract CrmCcAccountClass convert(
            EntryRegistrationRequestModel entryRegistrationRequestModel);

    /**
     * アカウント分類登録/更新画面の更新モデルをアカウント分類エンティティに変換します。
     * 
     * @param entryUpdateRequestModel
     *            アカウント分類登録/更新画面の更新モデル
     * @return CRMアカウント分類エンティティ
     */
    public abstract CrmCcAccountClass convert(
            EntryUpdateRequestModel entryUpdateRequestModel);

    /**
     * アカウント分類登録/更新画面の削除モデルをアカウント分類エンティティに変換します。
     * 
     * @param entryDeleteRequestModel
     *            アカウント分類登録/更新画面の削除モデル
     * @return CRMアカウント分類エンティティ
     */
    public abstract CrmCcAccountClass convert(
            EntryDeleteRequestModel entryDeleteRequestModel);

    /**
     * CRMアカウント分類エンティティをアカウント分類登録/更新画面の初期化モデルに変換します。
     * 
     * @param crmCcAccountClass
     *            CRMアカウント分類エンティティ
     * @return アカウント分類登録／更新画面の初期化モデル
     */
    public abstract EntryUpdateInitializeResponseModel convertInitialResponse(
            CrmCcAccountClass crmCcAccountClass);

}
