/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.CompeteEntryDialogInitializeRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CompeteEntryDialogInitializeResponseModel;
import com.biz_integral.crm.cc.application.controller.common.CompeteEntryDialogRegistrationRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CompeteEntryDialogUpdateRequestModel;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;

/**
 * 競合登録／更新ダイアログで利用するマッパーです。
 */
public interface CompeteEntryDialogMapper {

    /**
     * CRMアカウントの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            CRMアカウントの一件取得の検索条件
     * @return {@link CrmCcAccountDto}
     * 
     */
    public CrmCcAccountDto convertToCrmCcAccountDto(
            CompeteEntryDialogInitializeRequestModel request);

    /**
     * CRMコンタクトの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            CRMコンタクトの一件取得の検索条件
     * @return {@link CrmCcContactDto}
     * 
     */
    public CrmCcContactDto convertToCrmCcContactDto(
            CompeteEntryDialogInitializeRequestModel request);

    /**
     * CRMアカウント競合の一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            CRMアカウント競合の一件取得の検索条件
     * @return {@link CrmCcAccountCompetitionDto}
     * 
     */
    public CrmCcAccountCompetitionDto convertToCrmCcAccountCompetitionDto(
            CompeteEntryDialogInitializeRequestModel request);

    /**
     * CRMコンタクト競合の一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            CRMコンタクト競合の一件取得の検索条件
     * @return {@link CrmCcContactCompetitionDto}
     * 
     */
    public CrmCcContactCompetitionDto convertToCrmCcContactCompetitionDto(
            CompeteEntryDialogInitializeRequestModel request);

    /**
	 * 
	 * ビジネスロジックの処理引数DTOをCRMアカウントの一件取得の結果に変換します。
	 * 
	 * @param crmCcAccount
	 *            CrmCcAccountエンティティクラス
	 * @param model
	 *            初期表示responseモデル
	 * @return {@link CompeteEntryDialogInitializeResponseModel}
	 * 
	 */
    public CompeteEntryDialogInitializeResponseModel convertToCrmCcAccountDto(
            CrmCcAccount crmCcAccount,
            CompeteEntryDialogInitializeResponseModel model);

    /**
	 * 
	 * ビジネスロジックの処理引数DTOをCRMコンタクトの一件取得の結果に変換します。
	 * 
	 * @param crmCcContact
	 *            CrmCcContactエンティティクラス
	 * @param model
	 *            初期表示responseモデル
	 * @return {@link CompeteEntryDialogInitializeResponseModel}
	 */
    public CompeteEntryDialogInitializeResponseModel convertToCrmCcContactDto(
            CrmCcContact crmCcContact,
            CompeteEntryDialogInitializeResponseModel model);

    /**
	 * 
	 * ビジネスロジックの処理引数DTOをアCRMアカウント競合の一件取得の結果に変換します。
	 * 
	 * @param crmCcAccountCompetition
	 *            CrmCcAccountCompetitionエンティティクラス
	 * @param model
	 *            初期表示responseモデル
	 * @return {@link CompeteEntryDialogInitializeResponseModel}
	 * 
	 */
    public CompeteEntryDialogInitializeResponseModel convertToCrmCcAccountCompetitionDto(
            CrmCcAccountCompetition crmCcAccountCompetition,
            CompeteEntryDialogInitializeResponseModel model);

    /**
     * 
     * ビジネスロジックの処理引数DTOをアCRMコンタクト競合の一件取得の結果に変換します。
     * 
     * @param crmCcContactCompetition
     *            CrmCcContactCompetitionエンティティクラス
	 * @param model
	 *            初期表示responseモデル
     * @return {@link CompeteEntryDialogInitializeResponseModel}
     * 
     */
    public CompeteEntryDialogInitializeResponseModel convertToCrmCcContactCompetitionDto(
            CrmCcContactCompetition crmCcContactCompetition,
            CompeteEntryDialogInitializeResponseModel model);

    /**
     * CRMアカウント競合の登録をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            CRMアカウント競合の登録
     * @return {@link CrmCcAccountCompetitionDto}
     */
    public CrmCcAccountCompetitionDto convertToCrmCcAccountCompetitionDto(
            CompeteEntryDialogRegistrationRequestModel model);

    /**
     * CRMDaoアカウントの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            CRMアカウントDaoの一件取得の検索条件
     * @return {@link CrmCcAccountDto}
     * 
     */
    public CrmCcAccountDto convertToCrmCcAccountDto(
            CompeteEntryDialogRegistrationRequestModel model);

    /**
     * CRMコンタクト競合の登録をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            CRMコンタクト競合の登録
     * @return {@link CrmCcContactCompetitionDto}
     * 
     */
    public CrmCcContactCompetitionDto convertToCrmCcContactCompetitionDto(
            CompeteEntryDialogRegistrationRequestModel model);

    /**
     * CRMコンタクトDaoの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            CRMコンタクトDaoの一件取得の検索条件
     * @return {@link CrmCcContactDto}
     * 
     */
    public CrmCcContactDto convertToCrmCcContactDto(
            CompeteEntryDialogRegistrationRequestModel model);

    /**
     * CRMアカウント競合の更新をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            CRMアカウント競合の更新
     * @return {@link CrmCcAccountCompetitionDto}
     * 
     */
    public CrmCcAccountCompetitionDto convertToCrmCcAccountCompetitionDto(
            CompeteEntryDialogUpdateRequestModel model);

    /**
     * CRMアカウントDaoの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            CRMアカウントDaoの一件取得の検索条件
     * @return {@link CrmCcAccountDto}
     * 
     */
    public CrmCcAccountDto convertToCrmCcAccountDto(
            CompeteEntryDialogUpdateRequestModel model);

    /**
     * CRMコンタクト競合の登録をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            CRMコンタクト競合の登録
     * @return {@link CrmCcContactCompetitionDto}
     * 
     */
    public CrmCcContactCompetitionDto convertToCrmCcContactCompetitionDto(
            CompeteEntryDialogUpdateRequestModel model);

    /**
     * CRMコンタクトDaoの一件取得の検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            CRMコンタクトDaoの一件取得の検索条件
     * @return {@link CrmCcContactDto}
     * 
     */
    public CrmCcContactDto convertToCrmCcContactDto(
            CompeteEntryDialogUpdateRequestModel model);

}
