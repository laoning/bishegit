/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import java.util.List;

import com.biz_integral.crm.cc.domain.dto.AccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CampaignCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CampaignResultDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeDeptListChangeResultDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeAfterDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeBeforeDto;
import com.biz_integral.crm.cc.domain.dto.ChargeUserListChangeResultDto;
import com.biz_integral.crm.cc.domain.dto.ContactDeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactChargeUserResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcContactDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactCompetition;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * コンタクト登録/更新で利用するマッピーです。
 */
public interface EntryMapper {

    /**
     * コンタクト登録/更新で初期化条件をビジネスロジックの処理引数CRMコンタクトDTOに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で初期化条件
     * @return {@link CrmCcContactDto}
     */
    public CrmCcContactDto convertToCrmCcContactDto(
            EntryInitializeRequestModel request);

    /**
     * コンタクト登録/更新エンティティを初期表示レスポンスモデルに変換します。
     * 
     * @param crmCcContact
     *            コンタクト登録/更新エンティティ
     * @param response
     *            初期表示レスポンスモデル
     */
    public void convertToInitResponseModel(CrmCcContact crmCcContact,
            EntryInitializeResponseModel response);

    /**
     * コンタクト登録/更新で初期化条件をビジネスロジックの処理引数CRMコンタクト担当者DTOに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で初期化条件
     * @return {@link CrmCcContactChargeUserCriteriaDto}
     */
    public CrmCcContactChargeUserCriteriaDto convertToCrmCcContactChargeUserCriteriaDto(
            EntryInitializeRequestModel request);

    /**
     * コンタクト登録/更新で初期化結果CRMコンタクト担当者DTOをレスポンスモデルに変換します。
     * 
     * @param dto
     *            コンタクト登録/更新で初期化結果CRMアカウント担当者DTO
     * @return {@link EntryContactChargeUserResponseModel}
     */
    public EntryContactChargeUserResponseModel convertToChargeUserResponseModel(
            CrmCcContactChargeUserResultDto dto);

    /**
     * コンタクト登録/更新で初期化条件をビジネスロジックの処理引数CRMコンタクト担当組織DTOに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で初期化条件
     * @return {@link CrmCcContactChargeDeptCriteriaDto}
     */
    public CrmCcContactChargeDeptCriteriaDto convertToCrmCcContactChargeDeptCriteriaDto(
            EntryInitializeRequestModel request);

    /**
     * コンタクト登録/更新で初期化結果CRMコンタクト担当組織DTOをレスポンスモデルに変換します。
     * 
     * @param dto
     *            コンタクト登録/更新で初期化結果CRMアカウント担当組織DTO
     * @return {@link EntryContactChargeDeptResponseModel}
     */
    public EntryContactChargeDeptResponseModel convertToChargeDeptResponseModel(
            CrmCcContactChargeDeptResultDto dto);

    /**
     * コンタクト登録/更新で登録処理引数CRMコンタクト担当組織DTOに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で登録条件
     * @return {@link CrmCcContactChargeDeptCriteriaDto}
     */
    public CrmCcContactChargeDeptCriteriaDto convertToCrmCcContactChargeDeptCriteriaDto(
            EntryCreateDeptRequestModel request);

    /**
     * CRMコンタクト担当者処理引数モデルをCRMコンタクト担当者処理引数Dtoに変換します。
     * 
     * @param request
     *            CRMコンタクト担当者処理引数モデル
     * @return CRMコンタクト担当者処理引数Dto
     */
    public CrmCcContactChargeUserCriteriaDto convertToCrmCcContactChargeUserCriteriaDto(
            EntryCreateUserRequestModel request);

    /**
     * コンタクト登録/更新で登録条件をビジネスロジックの処理引数CRMコンタクトDTOに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で登録条件
     * @return {@link CrmCcContactDto}
     */
    public CrmCcContactDto convertToCrmCcContactDto(
            EntrySaveRequestModel request);

    /**
     * コンタクト登録/更新で更新条件をビジネスロジックの処理引数CRMコンタクトDTOに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で更新条件
     * @return {@link CrmCcContactDto}
     */
    public CrmCcContactDto convertToUpdateCrmCcContactDto(
            EntryUpdateRequestModel request);

    /**
     * コンタクト登録/更新で削除条件をビジネスロジックの処理引数CRMコンタクトDTOに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で削除条件
     * @return {@link CrmCcContactDto}
     */
    public CrmCcContactDto convertToDeleteCrmCcContactDto(
            EntryDeleteRequestModel request);

    /**
     * コンタクト登録/更新で競合検索条件をCRMコンタクト競合Dtoに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で初期化結果CRMコンタクト競合DTO
     * @return {@link CrmCcContactCompetitionCriteriaDto}
     */
    public CrmCcContactCompetitionCriteriaDto convertToCrmCcContactCompetitionCriteriaDto(
            EntryCompetitionFilterRequestModel request);

    /**
     * エンティティクラスをCRMコンタクト競合一覧検索条件モデルに変換します。
     * 
     * @param crmCcContactCompetition
     *            CRMコンタクト競合のエンティティクラス
     * @return {@link EntryCompetitionFilterResponseModel}
     */
    public PagingResult<EntryCompetitionFilterResponseModel> convertToCompetitionResponseModel(
            PagingResult<CrmCcContactCompetition> crmCcContactCompetition);

    /**
     * コンタクト登録/更新で活動一覧検索条件をCRMコンタクト活動一覧Dtoに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で活動一覧検索条件
     * @return {@link SalesActivityCriteriaDto}
     */
    public SalesActivityCriteriaDto converToCrmSaSalesAct(
            EntrySalesActivityFilterRequestModel request);

    /**
     * CRMコンタクト活動一覧DtoをCRMコンタクト活動一覧モデルに変換します。
     * 
     * @param pagingResult
     *            CRMコンタクト活動一覧Dto
     * @return {@link EntrySalesActivityFilterResponseModel}
     */
    public PagingResult<EntrySalesActivityFilterResponseModel> convertSalesActivityResponseModel(
            PagingResult<SalesActivityResultDto> pagingResult);

    /**
     * コンタクト登録/更新でキャンペーン検索条件をCRMコンタクトキャンペーンDtoに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で初期化条件CRMコンタクトキャンペーンDTO
     * @return {@link CampaignCriteriaDto}
     */
    public CampaignCriteriaDto convertToCampaignCriteriaDto(
            EntryCampaignFilterRequestModel request);

    /**
     * キャンペーンDtoをCRMコンタクトキャンペーン一覧検索結果モデルに変換します。
     * 
     * @param resultDto
     *            CRMコンタクトキャンペーンのDto
     * @return {@link EntryCampaignFilterResponseModel}
     */
    public PagingResult<EntryCampaignFilterResponseModel> convertToCampaignResponseModel(
            PagingResult<CampaignResultDto> resultDto);

    /**
     * 担当組織設定押下変更後条件モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param modelList
     *            担当組織設定押下条件モデル
     * @return {@link ChargeDeptListChangeAfterDto}
     */
    public List<ChargeDeptListChangeAfterDto> convertToChargeDeptListChangeAfterDto(
            List<EntryFixChargeDeptListRequestModel> modelList);

    /**
     * 担当組織設定押下変更前条件モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param modelList
     *            担当組織設定押下条件モデル
     * @return {@link ChargeDeptListChangeBeforeDto}
     */
    public List<ChargeDeptListChangeBeforeDto> convertToChargeDeptListChangeBeforeDto(
            List<EntryGridChargeDeptListRequestModel> modelList);

    /**
     * 担当組織設定押下結果Dtoを変更後結果モデルに変換します。
     * 
     * @param resultList
     *            担当組織設定押下条件モデル
     * @return {@link EntryContactChargeDeptResponseModel}
     */
    public List<EntryContactChargeDeptResponseModel> convertToContactChargeDeptResponseModel(
            List<ChargeDeptListChangeResultDto> resultList);

    /**
     * 担当者設定押下変更後条件モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param modelList
     *            担当者設定押下条件モデル
     * @return {@link ChargeUserListChangeAfterDto}
     */
    public List<ChargeUserListChangeAfterDto> convertToChargeUserListChangeAfterDto(
            List<EntryFixChargeUserListRequestModel> modelList);

    /**
     * 担当者設定押下変更前条件モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param modelList
     *            担当組織設定押下条件モデル
     * @return {@link ChargeUserListChangeBeforeDto}
     */
    public List<ChargeUserListChangeBeforeDto> convertToChargeUserListChangeBeforeDto(
            List<EntryGridChargeUserListRequestModel> modelList);

    /**
     * 担当者設定押下結果Dtoを変更後結果モデルに変換します。
     * 
     * @param resultList
     *            担当組織設定押下条件モデル
     * @return {@link EntryContactChargeUserResponseModel}
     */
    public List<EntryContactChargeUserResponseModel> convertToContactChargeUserResponseModel(
            List<ChargeUserListChangeResultDto> resultList);

    /**
     * アカウント追加モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            アカウント追加モデル
     * @return {@link AccountContactCriteriaDto}
     */
    public List<AccountContactCriteriaDto> convertToAccountContactCriteriaDtoList(
            EntryAccountAddRequestModel request);

    /**
     * コンタクト登録/更新で競合検索条件をCRMアカウントコンタクトDtoに変換します。
     * 
     * @param request
     *            コンタクト登録/更新で初期化結果CRMアカウントコンタクトDTO
     * @return {@link CrmCcAccountContactCriteriaDto}
     */
    public CrmCcAccountContactCriteriaDto convertToCrmCcAccountContactCriteriaDto(
            EntryAccountFilterRequestModel request);

    /**
     * アカウント一覧検索結果DtoをCRMアカウントコンタクト一覧検索条件モデルに変換します。
     * 
     * @param resultDto
     *            CRMアカウントコンタクトのDto
     * @return {@link EntryAccountFilterResponseModel}
     */
    public PagingResult<EntryAccountFilterResponseModel> convertToAccountFilterResponseModel(
            PagingResult<CrmCcAccountContactResultDto> resultDto);

    /**
     * 削除セル(アカウント)押下条件モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            削除セル(アカウント)押下条件モデル
     * @return {@link AccountContactCriteriaDto}
     */
    public AccountContactCriteriaDto convertToAccountContactCriteriaDto(
            EntryAccountDeleteRequestModel request);

    /**
     * 削除セル(競合)押下条件モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            削除セル(競合)押下条件モデル
     * @return {@link ContactDeleteCompetitionCriteriaDto}
     */
    public ContactDeleteCompetitionCriteriaDto convertToContactDeleteCompetitionCriteriaDto(
            EntryDeleteCompetitionRequestModel request);

    /**
     * CRMコンタクト担当チェックDtoを設定します。
     * 
     * @param crmContactCd
     *            CRMコンタクトコード
     * @return CRMコンタクト担当チェックDto
     */
    public CrmContactCheckChargeDto convertToCrmContactCheckChargeDto(
            String crmContactCd);

    /**
     * CRMアカウント担当チェックDto設定します。
     * 
     * @param request
     *            カウント権限チェック条件モデル
     * @return {@link CrmAccountCheckChargeDto}
     */
    public CrmAccountCheckChargeDto convertToCrmAccountCheckChargeDto(
            EntryAccountCheckRequestModel request);
}
