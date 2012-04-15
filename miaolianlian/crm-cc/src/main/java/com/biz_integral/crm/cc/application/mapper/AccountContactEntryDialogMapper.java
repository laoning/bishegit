/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.AccountContactEntryDialogInitializeRequestModel;
import com.biz_integral.crm.cc.application.controller.common.AccountContactEntryDialogInitializeResponseModel;
import com.biz_integral.crm.cc.application.controller.common.AccountContactEntryDialogUpdateRequestModel;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCheckItemDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;

/**
 * CRMアカウントコンタクト登録/更新のマッパーです。
 */
public interface AccountContactEntryDialogMapper {

    /**
     * アカウントコンタクトの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            アカウントコンタクトの一件取得の検索条件
     * @return {@link CrmCcAccountContactDto}
     */
    public CrmCcAccountContactDto convert(
            AccountContactEntryDialogInitializeRequestModel model);

    /**
     * アカウントの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            アカウントの一件取得の検索条件
     * @return {@link CrmCcAccountDto}
     */
    public CrmCcAccountDto convertToCrmCcAccountDto(
            AccountContactEntryDialogInitializeRequestModel model);

    /**
     * コンタクトの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            コンタクトの一件取得の検索条件
     * @return {@link CrmCcContactDto}
     */
    public CrmCcContactDto convertToCrmCcContactDto(
            AccountContactEntryDialogInitializeRequestModel model);

    /**
     * 
     * ビジネスロジックの処理引数DTOをアカウントコンタクトの一件取得の結果に変換します。
     * 
     * @param crmCcAccountContact
     *            CrmCcAccountContactエンティティクラス
     * @param crmCcAccount
     *            CrmCcAccountエンティティクラス
     * @param crmCcContact
     *            CrmCcContactエンティティクラス
     * @return {@link AccountContactEntryDialogInitializeResponseModel}
     */
    public AccountContactEntryDialogInitializeResponseModel convert(
            CrmCcAccountContact crmCcAccountContact, CrmCcAccount crmCcAccount,
            CrmCcContact crmCcContact);

    /**
     * 更新条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            更新条件
     * @return {@link CrmCcAccountContactCheckItemDto}
     */
    public CrmCcAccountContactCheckItemDto convert(
            AccountContactEntryDialogUpdateRequestModel model);
}
