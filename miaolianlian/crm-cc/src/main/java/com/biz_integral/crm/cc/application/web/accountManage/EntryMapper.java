/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

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
import com.biz_integral.crm.cc.domain.dto.CrmAccountCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeDeptResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountChargeUserResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountCompetitionDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountContactResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountDto;
import com.biz_integral.crm.cc.domain.dto.CrmContactCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.CustomerSelectCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.DeleteCompetitionCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ProposalCheckChargeDto;
import com.biz_integral.crm.cc.domain.dto.ProposalCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ProposalResultDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.SalesActivityResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountCompetition;
import com.biz_integral.crm.cc.domain.entity.CrmCcCustomerCmn;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント登録/更新で利用するマッピーです。
 */
public interface EntryMapper {

    /**
     * アカウント登録/更新で初期化条件をビジネスロジックの処理引数CRMアカウントDTOに変換します。
     * 
     * @param request
     *            アカウント登録/更新で初期化条件
     * @return {@link CrmCcAccountDto}
     */
    public CrmCcAccountDto convertToCrmCcAccountDto(
            EntryInitializeRequestModel request);

    /**
     * アカウント登録/更新エンティティを初期表示レスポンスモデルに変換します。
     * 
     * @param crmCcAccount
     *            アカウント登録/更新エンティティ
     * @param response
     *            初期表示レスポンスモデル
     */
    public void convert(CrmCcAccount crmCcAccount,
            EntryInitializeResponseModel response);

    /**
     * アカウント登録/更新で初期化条件をビジネスロジックの処理引数CRMアカウント担当者DTOに変換します。
     * 
     * @param request
     *            アカウント登録/更新で初期化条件
     * @return {@link CrmCcAccountChargeUserCriteriaDto}
     */
    public CrmCcAccountChargeUserCriteriaDto convertToCrmCcAccountChargeUserCriteriaDto(
            EntryInitializeRequestModel request);

    /**
     * アカウント登録/更新で初期化条件をビジネスロジックの処理引数CRMアカウント担当組織DTOに変換します。
     * 
     * @param request
     *            アカウント登録/更新で初期化条件
     * @return {@link CrmCcAccountChargeDeptCriteriaDto}
     */
    public CrmCcAccountChargeDeptCriteriaDto convertToCrmCcAccountChargeDeptCriteriaDto(
            EntryInitializeRequestModel request);

    /**
     * アカウント登録/更新で登録条件をビジネスロジックの処理引数CRMアカウントDTOに変換します。
     * 
     * @param request
     *            アカウント登録/更新で登録条件
     * @return {@link CrmCcAccountDto}
     */
    public CrmCcAccountDto convertToCrmCcAccountDto(
            EntrySaveRequestModel request);

    /**
     * アカウント登録/更新で初期化結果CRMアカウント担当組織DTOをレスポンスモデルに変換します。
     * 
     * @param dto
     *            アカウント登録/更新で初期化結果CRMアカウント担当組織DTO
     * @return {@link EntryAccountChargeDeptResponseModel}
     */
    public EntryAccountChargeDeptResponseModel convertToChargeDeptResponseModel(
            CrmCcAccountChargeDeptResultDto dto);

    /**
     * アカウント登録/更新で初期化結果CRMアカウント担当者DTOをレスポンスモデルに変換します。
     * 
     * @param dto
     *            アカウント登録/更新で初期化結果CRMアカウント担当者DTO
     * @return {@link EntryAccountChargeUserResponseModel}
     */
    public EntryAccountChargeUserResponseModel convertToChargeUserResponseModel(
            CrmCcAccountChargeUserResultDto dto);

    /**
     * アカウント登録/更新で競合検索条件をCRMアカウントコンタクトDtoに変換します。
     * 
     * @param request
     *            アカウント登録/更新で初期化結果CRMアカウントコンタクトDTO
     * @return {@link CrmCcAccountContactCriteriaDto}
     */
    public CrmCcAccountContactCriteriaDto convertToCrmCcAccountContactCriteriaDto(
            EntryContactFilterRequestModel request);

    /**
     * アカウント登録/更新で競合検索条件をCRMアカウント競合Dtoに変換します。
     * 
     * @param request
     *            アカウント登録/更新で初期化結果CRMアカウント競合DTO
     * @return {@link CrmCcAccountCompetitionDto}
     */
    public CrmCcAccountCompetitionDto convertToCrmCcAccountCompetitionDto(
            EntryCompetitionFilterRequestModel request);

    /**
     * アカウント登録/更新で案件検索条件をCRMアカウント案件Dtoに変換します。
     * 
     * @param request
     *            アカウント登録/更新で初期化条件CRMアカウント案件DTO
     * @return {@link ProposalCriteriaDto}
     */
    public ProposalCriteriaDto convertToProposalCriteriaDto(
            EntryProposalFilterRequestModel request);

    /**
     * アカウント登録/更新でキャンペーン検索条件をCRMアカウントキャンペーンDtoに変換します。
     * 
     * @param request
     *            アカウント登録/更新で初期化条件CRMアカウントキャンペーンDTO
     * @return {@link CampaignCriteriaDto}
     */
    public CampaignCriteriaDto convertToCampaignCriteriaDto(
            EntryCampaignFilterRequestModel request);

    /**
     * キャンペーンDtoをCRMアカウントキャンペーン一覧検索結果モデルに変換します。
     * 
     * @param resultDto
     *            CRMアカウントキャンペーンのDto
     * @return {@link EntryCampaignFilterResponseModel}
     */
    public PagingResult<EntryCampaignFilterResponseModel> convertToCampaignResponseModel(
            PagingResult<CampaignResultDto> resultDto);

    /**
     * 案件DtoをCRMアカウント案件一覧検索結果モデルに変換します。
     * 
     * @param resultDto
     *            CRMアカウントコンタクトのDto
     * @return {@link EntryProposalFilterResponseModel}
     */
    public PagingResult<EntryProposalFilterResponseModel> convertToProposalResponseModel(
            PagingResult<ProposalResultDto> resultDto);

    /**
     * コンタクト一覧検索結果DtoをCRMアカウントコンタクト一覧検索条件モデルに変換します。
     * 
     * @param resultDto
     *            CRMアカウントコンタクトのDto
     * @return {@link EntryContactFilterResponseModel}
     */
    public PagingResult<EntryContactFilterResponseModel> convertToContactResponseModel(
            PagingResult<CrmCcAccountContactResultDto> resultDto);

    /**
     * エンティティクラスをCRMアカウント競合一覧検索条件モデルに変換します。
     * 
     * @param crmCcAccountCompetition
     *            CRMアカウント競合のエンティティクラス
     * @return {@link CrmCcAccountCompetition}
     */
    public PagingResult<EntryCompetitionFilterResponseModel> convertToCompetitionResponseModel(
            PagingResult<CrmCcAccountCompetition> crmCcAccountCompetition);

    /**
     * アカウント登録/更新で活動一覧検索条件をCRMアカウント活動一覧Dtoに変換します。
     * 
     * @param request
     *            アカウント登録/更新で活動一覧検索条件
     * @return {@link SalesActivityCriteriaDto}
     */
    public SalesActivityCriteriaDto converToCrmSaSalesAct(
            EntrySalesActivityFilterRequestModel request);

    /**
     * CRMアカウント活動一覧DtoをCRMアカウント活動一覧モデルに変換します。
     * 
     * @param pagingResult
     *            CRMアカウント活動一覧Dto
     * @return {@link EntrySalesActivityFilterResponseModel}
     */
    public PagingResult<EntrySalesActivityFilterResponseModel> convertSalesActivityResponseModel(
            PagingResult<SalesActivityResultDto> pagingResult);

    /**
     * アカウント登録/更新で登録の処理引数CRMアカウント担当組織DTOに変換します。
     * 
     * @param request
     *            アカウント登録/更新で登録条件
     * @return {@link CrmCcAccountChargeDeptCriteriaDto}
     */
    public CrmCcAccountChargeDeptCriteriaDto convertToCrmCcAccountChargeDeptCriteriaDto(
            EntryCreateDeptRequestModel request);

    /**
     * CRMアカウント担当者処理引数モデルをCRMアカウント担当者処理引数Dtoに変換します。
     * 
     * @param request
     *            CRMアカウント担当者処理引数モデル
     * @return CRMアカウント担当者処理引数Dto
     */
    public CrmCcAccountChargeUserCriteriaDto convertToCrmCcAccountChargeUserCriteriaDto(
            EntryCreateUserRequestModel request);

    /**
     * アカウント登録/更新で更新条件をビジネスロジックの処理引数CRMアカウントDTOに変換します。
     * 
     * @param request
     *            アカウント登録/更新で更新条件
     * @return {@link CrmCcAccountDto}
     */
    public CrmCcAccountDto convertToCrmCcAccountDto(
            EntryUpdateRequestModel request);

    /**
     * アカウント登録/更新で削除条件をビジネスロジックの処理引数CRMアカウントDTOに変換します。
     * 
     * @param request
     *            アカウント登録/更新で削除条件
     * @return {@link CrmCcAccountDto}
     */
    public CrmCcAccountDto convertToCrmCcAccountDto(
            EntryDeleteRequestModel request);

    /**
     * アカウント登録/更新で取引先登録条件をビジネスロジックの処理引数CRMアカウントDTOに変換します。
     * 
     * @param request
     *            アカウント登録/更新で取引先登録条件
     * @return {@link CrmCcAccountDto}
     */
    public CrmCcAccountDto convertToCrmCcAccountDto(
            EntryCustomerEntryRequestModel request);

    /**
     * コンタクト追加モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            コンタクト追加モデル
     * @return {@link AccountContactCriteriaDto}
     */
    public List<AccountContactCriteriaDto> convertToAccountContactCriteriaDtoList(
            EntryContactAddRequestModel request);

    /**
     * 取引先選択追加モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            取引先選択モデル
     * @return {@link CustomerSelectCriteriaDto}
     */
    public CustomerSelectCriteriaDto convertToCustomerSelectCriteriaDto(
            EntryCustomerSelectRequestModel request);

    /**
     * エンティティクラスを取引先選択結果モデルに変換します。
     * 
     * @param entity
     *            取引先エンティティ
     * @return {@link EntryCustomerSelectResponseModel}
     */
    public EntryCustomerSelectResponseModel convertToCustomerSelectResponseModel(
            CrmCcCustomerCmn entity);

    /**
     * 削除セル(コンタクト)押下条件モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            削除セル(コンタクト)押下条件モデル
     * @return {@link AccountContactCriteriaDto}
     */
    public AccountContactCriteriaDto convertToAccountContactCriteriaDto(
            EntryContactDeleteRequestModel request);

    /**
     * 削除セル(競合)押下条件モデルをビジネスロジックの処理引数DTOに変換します。
     * 
     * @param request
     *            削除セル(競合)押下条件モデル
     * @return {@link DeleteCompetitionCriteriaDto}
     */
    public DeleteCompetitionCriteriaDto convertToDeleteCompetitionCriteriaDto(
            EntryDeleteCompetitionRequestModel request);

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
     * @return {@link EntryAccountChargeDeptResponseModel}
     */
    public List<EntryAccountChargeDeptResponseModel> convertToAccountChargeDeptResponseModel(
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
     * @return {@link EntryAccountChargeUserResponseModel}
     */
    public List<EntryAccountChargeUserResponseModel> convertToAccountChargeUserResponseModel(
            List<ChargeUserListChangeResultDto> resultList);

    /**
     * CRMアカウント担当チェックDto設定します。
     * 
     * @param crmAccountCd
     *            アカウントコード
     * @param crmDomainCd
     *            CRM領域コード
     * @return {@link CrmAccountCheckChargeDto}
     */
    public CrmAccountCheckChargeDto convertToCrmAccountCheckChargeDto(
            String crmAccountCd, String crmDomainCd);

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
     * CRM案件担当チェックDtoを設定します。
     * 
     * @param proposalCd
     *            案件コード
     * @return 案件担当チェックDto
     */
    public ProposalCheckChargeDto convertToProposalCheckChargeDto(
            String proposalCd);
}
