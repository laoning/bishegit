/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.CompeteEntryDialogMapper;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.crm.cc.domain.logic.CrmAccountCompetitionLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactCompetitionLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.crm.cc.domain.types.CompetitionType;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;

/**
 * 競合登録／更新ダイアログ画面のコントローラです。
 */
public class CompeteEntryDialogController {

    /**
     * {@link EnumNamesRegistry 区分に関する名称管理}
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link CompeteEntryDialogMapper 検索Mapper}
     */
    @Resource
    protected CompeteEntryDialogMapper competeEntryDialogMapper;

    /**
     * {@link CrmAccountCompetitionLogic}
     */
    @Resource
    protected CrmAccountCompetitionLogic crmAccountCompetitionLogic;

    /**
     * {@link CrmContactCompetitionLogic}
     */
    @Resource
    protected CrmContactCompetitionLogic crmContactCompetitionLogic;

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
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * 処理対象（アカウント）を制御します。
     */
    protected static final String ACCOUNT = "account";
    
    /**
     * 処理対象（コンタクト）を制御します。
     */
    protected static final String CONTACT = "contact";

    /**
     * 処理モード（登録）を制御します。
     */
    protected static final String ENTRY = "entry";
    
    /**
     * 処理モード（更新）を制御します。
     */
    protected static final String UPDATE = "update";

    /**
	 * 初期化を行います。
	 * 
	 * @param request
	 *            初期表示submitモデル
	 * @return 画面初期化に利用する項目
	 */
    @Execute
    public CompeteEntryDialogInitializeResponseModel initialize(
            CompeteEntryDialogInitializeRequestModel request) {

        CompeteEntryDialogInitializeResponseModel model =
            new CompeteEntryDialogInitializeResponseModel();

        // 1件目に空欄を追加します。
        model.competitionType.add(new KeyValueBean());

        // 競合度の一覧を取得します。
        for (CompetitionType competitionType : CompetitionType.values()) {
            String name =
                this.enumNamesRegistry.getName(competitionType, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            model.competitionType.add(new KeyValueBean(name, competitionType
                .value()));
        }

        if (ENTRY.equals(request.processMode)
            || UPDATE.equals(request.processMode)) {

            if (ACCOUNT.equals(request.processTarget)) {
                // CRMアカウントの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
                CrmCcAccountDto crmCcAccountDto =
                    competeEntryDialogMapper.convertToCrmCcAccountDto(request);
                // CRMアカウントの一件取得です。
                CrmCcAccount crmCcAccount =
                    crmAccountLogic.getEntity(crmCcAccountDto);
                model =
                    competeEntryDialogMapper.convertToCrmCcAccountDto(
                        crmCcAccount,
                        model);

            } else if (CONTACT.equals(request.processTarget)) {
                // CRMコンタクトの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
                CrmCcContactDto crmCcContactDto =
                    competeEntryDialogMapper.convertToCrmCcContactDto(request);
                // CRMコンタクトの一件取得です。
                CrmCcContact crmCcContact =
                    crmContactLogic.getEntity(crmCcContactDto);
                model =
                    competeEntryDialogMapper.convertToCrmCcContactDto(
                        crmCcContact,
                        model);
            }

        }

        if (UPDATE.equals(request.processMode)) {
            if (ACCOUNT.equals(request.processTarget)) {
                // CRMアカウント競合の一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
                CrmCcAccountCompetitionDto crmCcAccountCompetitionDto =
                    competeEntryDialogMapper
                        .convertToCrmCcAccountCompetitionDto(request);
                // CRMアカウント競合の一件取得です。
                CrmCcAccountCompetition crmCcAccountCompetition =
                    crmAccountCompetitionLogic
                        .getEntity(crmCcAccountCompetitionDto);
                model =
                    competeEntryDialogMapper
                        .convertToCrmCcAccountCompetitionDto(
                            crmCcAccountCompetition,
                            model);

            } else if (CONTACT.equals(request.processTarget)) {
                // CRMコンタクト競合の一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
                CrmCcContactCompetitionDto crmCcContactCompetitionDto =
                    competeEntryDialogMapper
                        .convertToCrmCcContactCompetitionDto(request);
                // CRMコンタクト競合の一件取得です。
                CrmCcContactCompetition crmCcContactCompetition =
                    crmContactCompetitionLogic
                        .getEntity(crmCcContactCompetitionDto);

                // ビジネスロジックの処理引数DTOをCRMアカウント競合の一件取得の結果に変換します。
                model =
                    competeEntryDialogMapper
                        .convertToCrmCcContactCompetitionDto(
                            crmCcContactCompetition,
                            model);
            }
        }

        return model;
    }

    /**
	 * 登録ボタン押下の処理です。
	 * 
	 * @param request
	 *            登録submitモデル
	 */
    @Validation
    @Execute
    public void registration(CompeteEntryDialogRegistrationRequestModel request) {
        // CRMアカウント競合を登録の処理です。
        if (ACCOUNT.equals(request.processTarget)) {

            CrmCcAccountCompetitionDto crmCcAccountCompetitionDto =
                competeEntryDialogMapper
                    .convertToCrmCcAccountCompetitionDto(request);
            CrmCcAccountDto crmCcAccountDto =
                competeEntryDialogMapper.convertToCrmCcAccountDto(request);

            crmAccountCompetitionLogic.create(
                crmCcAccountCompetitionDto,
                crmCcAccountDto);
            // CRMコンタクト競合を登録の処理です。
        } else if (CONTACT.equals(request.processTarget)) {

            CrmCcContactCompetitionDto crmCcContactCompetitionDto =
                competeEntryDialogMapper
                    .convertToCrmCcContactCompetitionDto(request);
            CrmCcContactDto crmCcContactDto =
                competeEntryDialogMapper.convertToCrmCcContactDto(request);

            crmContactCompetitionLogic.create(
                crmCcContactCompetitionDto,
                crmCcContactDto);
        }
    }

    /**
	 * 更新ボタン押下の処理です。
	 * 
	 * @param request
	 *            更新submitモデル
	 */
    @Validation
    @Execute
    public void update(CompeteEntryDialogUpdateRequestModel request) {
        // CRMアカウント競合を更新の処理です。
        if (ACCOUNT.equals(request.processTarget)) {
            CrmCcAccountCompetitionDto crmCcAccountCompetitionDto =
                competeEntryDialogMapper
                    .convertToCrmCcAccountCompetitionDto(request);

            CrmCcAccountDto crmCcAccountDto =
                competeEntryDialogMapper.convertToCrmCcAccountDto(request);

            crmAccountCompetitionLogic.update(
                crmCcAccountCompetitionDto,
                crmCcAccountDto);
            // CRMコンタクト競合を更新の処理です。
        } else if (CONTACT.equals(request.processTarget)) {

            CrmCcContactCompetitionDto crmCcContactCompetitionDto =
                competeEntryDialogMapper
                    .convertToCrmCcContactCompetitionDto(request);

            CrmCcContactDto crmCcContactDto =
                competeEntryDialogMapper.convertToCrmCcContactDto(request);

            crmContactCompetitionLogic.update(
                crmCcContactCompetitionDto,
                crmCcContactDto);

        }
    }
}