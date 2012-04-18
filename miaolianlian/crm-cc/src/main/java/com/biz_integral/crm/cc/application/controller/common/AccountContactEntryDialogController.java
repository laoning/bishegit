/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.AccountContactEntryDialogMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCheckItemDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.logic.AccountContactLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;

/**
 * アカウントコンタクト登録/更新ダイアログ検索画面のコントローラです。
 */
public class AccountContactEntryDialogController {

    /**
     * {@link AccountContactEntryDialogMapper}
     */
    @Resource
    protected AccountContactEntryDialogMapper accountContactEntryDialogMapper;

    /**
     * {@link AccountContactLogic}
     */
    @Resource
    protected AccountContactLogic accountContactLogic;

    /**
     * {@link CrmAccountLogic}
     */
    @Resource
    protected CrmAccountLogic crmAccountLogic;

    /**
     * {@link CrmContactLogic}
     */
    @Resource
    protected CrmContactLogic crmContactLogic;

    /**
     * 初期化を行います。
     * 
     * @param request
     *            アカウントコンタクト登録/更新の初期表示submitモデル
     * @return アカウント初期化結果
     */
    @Execute
    public AccountContactEntryDialogInitializeResponseModel initialize(
            AccountContactEntryDialogInitializeRequestModel request) {

        // アカウントコンタクトの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
        CrmCcAccountContactDto dto =
            accountContactEntryDialogMapper.convert(request);

        // アカウントコンタクトの一件を取得します。
        CrmCcAccountContact crmCcAccountContact =
            accountContactLogic.getEntity(dto);

        // アカウントの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
        CrmCcAccountDto crmCcAccountDto =
            accountContactEntryDialogMapper.convertToCrmCcAccountDto(request);

        // アカウントの一件取得です。
        CrmCcAccount crmCcAccount = crmAccountLogic.getEntity(crmCcAccountDto);

        // コンタクトの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
        CrmCcContactDto crmCcContactDto =
            accountContactEntryDialogMapper.convertToCrmCcContactDto(request);

        // コンタクトの一件取得です。
        CrmCcContact crmCcContact = crmContactLogic.getEntity(crmCcContactDto);

        // ビジネスロジックの処理引数DTOをアカウントコンタクトの一件取得の結果に変換します。
        AccountContactEntryDialogInitializeResponseModel model =
            accountContactEntryDialogMapper.convert(
                crmCcAccountContact,
                crmCcAccount,
                crmCcContact);

        return model;
    }

    /**
     * 
     *アカウントコンタクトを更新します。
     * 
     * @param request
     *            アカウントコンタクト登録/更新の更新submitモデル
     */
    @Execute
    @Validation
    public void update(AccountContactEntryDialogUpdateRequestModel request) {

        CrmCcAccountContactCheckItemDto dto =
            accountContactEntryDialogMapper.convert(request);

        // アカウントコンタクトの期間化更新判定を行います。
        accountContactLogic.termUpdateCheck(dto);
    }
}
