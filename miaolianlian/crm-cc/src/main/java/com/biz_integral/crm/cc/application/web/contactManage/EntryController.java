/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import com.biz_integral.crm.cc.domain.logic.AccountContactLogic;
import com.biz_integral.crm.cc.domain.logic.CampaignLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmChargeDepartmentListLogic;
import com.biz_integral.crm.cc.domain.logic.CrmChargeUserListLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactChargeDepartmentLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactChargeUserLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactCompetitionLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.logic.SalesActivityLogic;
import com.biz_integral.crm.cc.domain.types.ImportantLevelType;
import com.biz_integral.crm.cc.domain.types.MainChargeFlg;
import com.biz_integral.crm.cc.domain.types.SexType;
import com.biz_integral.crm.cc.domain.types.UseType;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.service.persistence.exception.OptimisticLockRuntimeException;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * コンタクト登録／更新画面のコントローラです。
 */
public class EntryController {

    /**
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * パラメータロジック
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * CRMコンタクトロジック
     */
    @Resource
    protected CrmContactLogic crmContactLogic;

    /**
     * CRMアカウントロジック
     */
    @Resource
    protected CrmAccountLogic crmAccountLogic;

    /**
     * CRMコンタクト担当者ロジック
     */
    @Resource
    protected CrmContactChargeUserLogic crmContactChargeUserLogic;

    /**
     * CRMコンタクト担当組織ロジック
     */
    @Resource
    protected CrmContactChargeDepartmentLogic crmContactChargeDepartmentLogic;

    /**
     * CRMコンタクト競合ロジック
     */
    @Resource
    protected CrmContactCompetitionLogic crmContactCompetitionLogic;

    /**
     * 営業活動ロジック
     */
    @Resource
    protected SalesActivityLogic salesActivityLogic;

    /**
     * キャンペーンのロジック
     */
    @Resource
    protected CampaignLogic campaignLogic;

    /**
     * CRM担当組織一覧のロジック
     */
    @Resource
    protected CrmChargeDepartmentListLogic crmChargeDepartmentListLogic;

    /**
     * CRM担当者一覧のロジック
     */
    @Resource
    protected CrmChargeUserListLogic crmChargeUserListLogic;

    /**
     * CRMアカウントコンタクトロジック
     */
    @Resource
    protected AccountContactLogic accountContactLogic;

    /**
     * コンタクト登録/更新で利用するマッビー
     */
    @Resource
    protected EntryMapper entryMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * 初期化を行いします。
     * 
     * @return 画面初期化に利用する項目
     */
    @Execute
    public EntryInitializeResponseModel initialize() {
        EntryInitializeResponseModel response =
            new EntryInitializeResponseModel();

        // 1件目に空欄を追加します。
        response.importantLevelTypeList.add(new KeyValueBean());

        // 重要度の一覧を取得します。
        for (ImportantLevelType importantLevelType : ImportantLevelType
            .values()) {
            String name =
                this.enumNamesRegistry.getName(importantLevelType, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.importantLevelTypeList.add(new KeyValueBean(
                name,
                importantLevelType.value()));
        }

        // 1件目に空欄を追加します。
        response.sexTypeList.add(new KeyValueBean());

        // 性別の一覧を取得します。
        for (SexType sexType : SexType.values()) {
            String name =
                this.enumNamesRegistry.getName(sexType, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.sexTypeList.add(new KeyValueBean(name, sexType.value()));
        }

        // パラメータを取得します。
        response.sfaFlag =
            ((UseType) parameterLogic.getEntity("CRMCC0007")).value();
        response.crmContactAddInfoFlag =
            ((UseType) parameterLogic.getEntity("CRMCC0009")).value();
        response.crmDomainCd = parameterLogic.getEntity("CRMCC0002").toString();
        return response;
    }

    /**
     * 更新モード初期化を行いします。
     * 
     * @param request
     *            コンタクト登録/更新画面の初期化条件
     * @return 画面初期化に利用する項目
     */
    @Execute
    @Validation
    public EntryInitializeResponseModel updateInitialize(
            EntryInitializeRequestModel request) {
        EntryInitializeResponseModel response =
            new EntryInitializeResponseModel();

        // コンタクトを取得します。
        if ("update".equals(request.processModeType)) {

            // CRMコンタクトを一件取得します。
            CrmCcContactDto crmCcContactDto =
                entryMapper.convertToCrmCcContactDto(request);
            CrmCcContact crmCcContact =
                crmContactLogic.getEntity(crmCcContactDto);
            if (crmCcContact == null
                || StringUtil.isEmpty(crmCcContact.getCrmContactCd())) {
                throw new OptimisticLockRuntimeException(null);
            }
            entryMapper.convertToInitResponseModel(crmCcContact, response);

            // CRMコンタクトの担当をチェックします。
            CrmContactCheckChargeDto crmContactCheckChargeDto =
                entryMapper
                    .convertToCrmContactCheckChargeDto(request.crmContactCd);
            crmContactLogic.isCharge(crmContactCheckChargeDto);

            // CRMコンタクト担当者を一覧検索します。
            CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto =
                entryMapper.convertToCrmCcContactChargeUserCriteriaDto(request);
            List<CrmCcContactChargeUserResultDto> chargeUserDtoList =
                crmContactChargeUserLogic.getEntityList(chargeUserCriteriaDto);

            for (CrmCcContactChargeUserResultDto chargeUserDto : chargeUserDtoList) {
                if (chargeUserDto.mainCharge != null) {
                    chargeUserDto.mainCharge =
                        this.enumNamesRegistry.getName(MainChargeFlg
                            .getEnum(chargeUserDto.mainCharge), LocaleUtil
                            .toLocale(contextContainer
                                .getUserContext()
                                .getLocaleID()));
                }
                EntryContactChargeUserResponseModel chargeUserResponse =
                    entryMapper.convertToChargeUserResponseModel(chargeUserDto);
                response.contactChargeUserResponseList.add(chargeUserResponse);
            }

            // CRMコンタクト担当組織を一覧検索します。
            CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto =
                entryMapper.convertToCrmCcContactChargeDeptCriteriaDto(request);

            List<CrmCcContactChargeDeptResultDto> chargeDeptDtoList =
                crmContactChargeDepartmentLogic
                    .getEntityList(chargeDeptCriteriaDto);
            for (CrmCcContactChargeDeptResultDto chargeDeptDto : chargeDeptDtoList) {
                if (chargeDeptDto.mainCharge != null) {
                    chargeDeptDto.mainCharge =
                        this.enumNamesRegistry.getName(MainChargeFlg
                            .getEnum(chargeDeptDto.mainCharge), LocaleUtil
                            .toLocale(contextContainer
                                .getUserContext()
                                .getLocaleID()));
                }
                EntryContactChargeDeptResponseModel chargeDeptResponse =
                    entryMapper.convertToChargeDeptResponseModel(chargeDeptDto);
                response.contactChargeDeptResponseList.add(chargeDeptResponse);
            }
        }
        return response;
    }

    /**
     * 保存を行いします。
     * 
     * @param request
     *            登録条件モデル
     * @return 登録結果モデル
     */
    @Execute
    @Validation
    public EntrySaveResponseModel save(EntrySaveRequestModel request) {
        EntrySaveResponseModel response = new EntrySaveResponseModel();

        // CRMコンタクト担当組織を登録します。
        createDept(
            request.contactChargeDeptList,
            request.contactChargeUserList,
            request.crmContactCd);

        // CRMコンタクト担当者を登録します。
        createUser(request.contactChargeUserList, request.crmContactCd);

        // CRMコンタクトを登録します。
        CrmCcContactDto crmCcContactDto =
            entryMapper.convertToCrmCcContactDto(request);
        CrmCcContact crmCcContact = crmContactLogic.create(crmCcContactDto);
        response.versionNo = String.valueOf(crmCcContact.getVersionNo());
        response.termCd = crmCcContact.getTermCd();
        return response;
    }

    /**
	 * CRMコンタクト担当組織を登録します。
	 * 
	 * @param contactChargeDeptList
	 *            担当組織の条件モデル
	 * @param contactChargeUserList
	 *            担当者の条件モデル
	 * @param crmContactCd
	 *            コンタクトコード
	 */
    private void createDept(
            List<EntryCreateDeptRequestModel> contactChargeDeptList,
            List<EntryCreateUserRequestModel> contactChargeUserList,
            String crmContactCd) {
        List<CrmCcContactChargeDeptCriteriaDto> chargeDeptCriteriaDtoList =
            new ArrayList<CrmCcContactChargeDeptCriteriaDto>();
        for (EntryCreateDeptRequestModel chargeDeptRequestModel : contactChargeDeptList) {
            CrmCcContactChargeDeptCriteriaDto chargeDeptCriteriaDto =
                entryMapper
                    .convertToCrmCcContactChargeDeptCriteriaDto(chargeDeptRequestModel);
            chargeDeptCriteriaDto.crmContactCd = crmContactCd;
            chargeDeptCriteriaDtoList.add(chargeDeptCriteriaDto);
        }

        List<CrmCcContactChargeUserCriteriaDto> chargeUserCriteriaDtoList =
            new ArrayList<CrmCcContactChargeUserCriteriaDto>();
        for (EntryCreateUserRequestModel chargeUserRequestModel : contactChargeUserList) {
            CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto =
                entryMapper
                    .convertToCrmCcContactChargeUserCriteriaDto(chargeUserRequestModel);
            chargeUserCriteriaDto.crmContactCd = crmContactCd;
            chargeUserCriteriaDtoList.add(chargeUserCriteriaDto);
        }

        // CRMコンタクト担当組織を登録します。
        crmContactChargeDepartmentLogic.create(
            chargeDeptCriteriaDtoList,
            chargeUserCriteriaDtoList,
            crmContactCd);
    }

    /**
     * CRMコンタクト担当者を登録します。
     * 
     * @param contactChargeUserList
     *            担当者一覧
     * @param crmContactCd
     *            コンタクトコード
     */
    private void createUser(
            List<EntryCreateUserRequestModel> contactChargeUserList,
            String crmContactCd) {
        List<CrmCcContactChargeUserCriteriaDto> chargeUserCriteriaDtoList =
            new ArrayList<CrmCcContactChargeUserCriteriaDto>();
        for (EntryCreateUserRequestModel chargeUserRequestModel : contactChargeUserList) {
            CrmCcContactChargeUserCriteriaDto chargeUserCriteriaDto =
                entryMapper
                    .convertToCrmCcContactChargeUserCriteriaDto(chargeUserRequestModel);
            chargeUserCriteriaDto.crmContactCd = crmContactCd;
            chargeUserCriteriaDtoList.add(chargeUserCriteriaDto);
        }
        crmContactChargeUserLogic.create(
            chargeUserCriteriaDtoList,
            crmContactCd);
    }

    /**
     * 更新を行いします。
     * 
     * @param request
     *            更新条件モデル
     */
    @Execute
    @Validation
    public void update(EntryUpdateRequestModel request) {

        // CRMコンタクト担当組織を登録します。
        createDept(
            request.contactChargeDeptList,
            request.contactChargeUserList,
            request.crmContactCd);

        // CRMコンタクト担当者を登録します。
        createUser(request.contactChargeUserList, request.crmContactCd);

        if (request.contactChargeUserList == null
            || request.contactChargeUserList.isEmpty()) {

        }

        // CRMコンタクトを更新します。
        CrmCcContactDto crmCcContactDto =
            entryMapper.convertToUpdateCrmCcContactDto(request);
        crmContactLogic.update(crmCcContactDto);
    }

    /**
     * 削除を行いします。
     * 
     * @param request
     *            削除条件モデル
     */
    @Execute
    @Validation
    public void delete(EntryDeleteRequestModel request) {
        CrmCcContactDto crmCcContactDto =
            entryMapper.convertToDeleteCrmCcContactDto(request);
        crmContactLogic.delete(crmCcContactDto);
    }

    /**
     * 競合一覧ページャーを行いします。
     * 
     * @param request
     *            競合一覧ページャー条件モデル
     * @return 競合一覧ページャー結果モデル
     */
    @Execute
    @Validation
    public PagingResponse<EntryCompetitionFilterResponseModel> competitionFilter(
            EntryCompetitionFilterRequestModel request) {
        PagingResponse<EntryCompetitionFilterResponseModel> result =
            new PagingResponse<EntryCompetitionFilterResponseModel>();

        CrmCcContactCompetitionCriteriaDto criteriadto =
            entryMapper.convertToCrmCcContactCompetitionCriteriaDto(request);
        PagingResult<CrmCcContactCompetition> pagingResult =
            crmContactCompetitionLogic.getEntityList(criteriadto);

        PagingResult<EntryCompetitionFilterResponseModel> pagingList =
            entryMapper.convertToCompetitionResponseModel(pagingResult);
        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());
        return result;
    }

    /**
     * 活動一覧ページャーを行いします。
     * 
     * @param request
     *            活動一覧条件モデル
     * @return 活動一覧結果モデル
     */
    @Execute
    @Validation
    public PagingResponse<EntrySalesActivityFilterResponseModel> salesActivityFilter(
            EntrySalesActivityFilterRequestModel request) {
        PagingResponse<EntrySalesActivityFilterResponseModel> result =
            new PagingResponse<EntrySalesActivityFilterResponseModel>();

        SalesActivityCriteriaDto salesActivityCriteriaDto =
            entryMapper.converToCrmSaSalesAct(request);
        salesActivityCriteriaDto.contactFlag =
            ((UseType) parameterLogic.getEntity("CRMCC0006")).value();
        PagingResult<SalesActivityResultDto> pagingResult =
            salesActivityLogic.getEntityList(salesActivityCriteriaDto);

        PagingResult<EntrySalesActivityFilterResponseModel> pagingList =
            entryMapper.convertSalesActivityResponseModel(pagingResult);
        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());
        return result;
    }

    /**
     * キャンペーン一覧ページャーを行いします。
     * 
     * @param request
     *            キャンペーン一覧ページャー条件モデル
     * @return キャンペーン一覧ページャー結果モデル
     */
    @Execute
    @Validation
    public PagingResponse<EntryCampaignFilterResponseModel> campaignFilter(
            EntryCampaignFilterRequestModel request) {
        PagingResponse<EntryCampaignFilterResponseModel> result =
            new PagingResponse<EntryCampaignFilterResponseModel>();
        CampaignCriteriaDto dto =
            entryMapper.convertToCampaignCriteriaDto(request);
        dto.contactFlag =
            ((UseType) parameterLogic.getEntity("CRMCC0006")).value();
        PagingResult<CampaignResultDto> pagingResult =
            campaignLogic.getEntityList(dto);
        PagingResult<EntryCampaignFilterResponseModel> pagingList =
            entryMapper.convertToCampaignResponseModel(pagingResult);
        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());

        return result;
    }

    /**
     * 担当組織設定ボタン押下の処理です。
     * 
     * @param request
     *            担当組織設定ボタン条件モデル
     * @return 担当組織設定ボタン結果モデル
     */
    @Execute
    @Validation
    public EntrySetDepartmentListResponseModel setDepartmentList(
            EntrySetDepartmentListRequestModel request) {
        EntrySetDepartmentListResponseModel response =
            new EntrySetDepartmentListResponseModel();
        List<ChargeDeptListChangeAfterDto> changeAfterList =
            entryMapper
                .convertToChargeDeptListChangeAfterDto(request.fixChargeDeptList);
        List<ChargeDeptListChangeBeforeDto> changeBeforeList =
            entryMapper
                .convertToChargeDeptListChangeBeforeDto(request.gridChargeDeptList);
        List<ChargeDeptListChangeResultDto> resultDto =
            crmChargeDepartmentListLogic.setDepartmentList(
                changeAfterList,
                changeBeforeList);
        response.contactChargeDeptResponseList =
            entryMapper.convertToContactChargeDeptResponseModel(resultDto);
        return response;
    }

    /**
     * 担当者設定ボタン押下の処理です。
     * 
     * @param request
     *            担当者設定ボタン条件モデル
     * @return 担当者設定ボタン結果モデル
     */
    @Execute
    @Validation
    public EntrySetUserListResponseModel setUserList(
            EntrySetUserListRequestModel request) {
        EntrySetUserListResponseModel response =
            new EntrySetUserListResponseModel();
        List<ChargeUserListChangeAfterDto> changeAfterList =
            entryMapper
                .convertToChargeUserListChangeAfterDto(request.fixChargeUserList);
        List<ChargeUserListChangeBeforeDto> changeBeforeList =
            entryMapper
                .convertToChargeUserListChangeBeforeDto(request.gridChargeUserList);
        List<ChargeUserListChangeResultDto> resultDto =
            crmChargeUserListLogic.setUserList(
                changeAfterList,
                changeBeforeList);
        response.contactChargeUserResponseList =
            entryMapper.convertToContactChargeUserResponseModel(resultDto);
        return response;
    }

    /**
     * 主選択セル(組織)押下の処理です。
     * 
     * @param request
     *            担当組織主選択設定条件モデル
     * @return 担当組織主選択設定結果モデル
     */
    @Execute
    @Validation
    public EntrySetMainChargeDepartmentResponseModel setMainChargeDepartment(
            EntrySetMainChargeDepartmentRequestModel request) {
        EntrySetMainChargeDepartmentResponseModel response =
            new EntrySetMainChargeDepartmentResponseModel();
        List<ChargeDeptListChangeBeforeDto> changeBeforeList =
            entryMapper
                .convertToChargeDeptListChangeBeforeDto(request.gridChargeDeptList);
        List<ChargeDeptListChangeResultDto> resultDto =
            crmChargeDepartmentListLogic.setMainChargeDepartment(
                request.selectedDepartmentCd,
                changeBeforeList);
        response.contactChargeDeptResponseList =
            entryMapper.convertToContactChargeDeptResponseModel(resultDto);
        return response;
    }

    /**
     * 主選択セル(担当者)押下の処理です。
     * 
     * @param request
     *            担当者主選択設定条件モデル
     * @return 担当者主選択設定結果モデル
     */
    @Execute
    @Validation
    public EntrySetMainChargeUserResponseModel setMainChargeUser(
            EntrySetMainChargeUserRequestModel request) {
        EntrySetMainChargeUserResponseModel response =
            new EntrySetMainChargeUserResponseModel();
        List<ChargeUserListChangeBeforeDto> changeBeforeList =
            entryMapper
                .convertToChargeUserListChangeBeforeDto(request.gridChargeUserList);
        List<ChargeUserListChangeResultDto> resultDto =
            crmChargeUserListLogic.setMainChargeUser(
                request.selectedUserCd,
                changeBeforeList);
        response.contactChargeUserResponseList =
            entryMapper.convertToContactChargeUserResponseModel(resultDto);
        return response;
    }

    /**
     * アカウント追加ボタン押下の処理です。
     * 
     * @param request
     *            アカウント追加ボタンの条件モデル
     */
    @Execute
    @Validation
    public void accountAdd(EntryAccountAddRequestModel request) {
        List<AccountContactCriteriaDto> accountContactCriteriaDtoList =
            entryMapper.convertToAccountContactCriteriaDtoList(request);

        accountContactLogic.createAccount(
            accountContactCriteriaDtoList,
            request.crmDomainCd);
    }

    /**
     * アカウント一覧ページャーを行いします。
     * 
     * @param request
     *            アカウント一覧ページャー条件モデル
     * @return アカウント一覧ページャー結果モデル
     */
    @Execute
    @Validation
    public PagingResponse<EntryAccountFilterResponseModel> accountFilter(
            EntryAccountFilterRequestModel request) {
        PagingResponse<EntryAccountFilterResponseModel> result =
            new PagingResponse<EntryAccountFilterResponseModel>();

        CrmCcAccountContactCriteriaDto criteriadto =
            entryMapper.convertToCrmCcAccountContactCriteriaDto(request);

        PagingResult<CrmCcAccountContactResultDto> pagingResult =
            accountContactLogic.getAccountEntityList(criteriadto);
        PagingResult<EntryAccountFilterResponseModel> pagingList =
            entryMapper.convertToAccountFilterResponseModel(pagingResult);
        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());

        return result;
    }

    /**
     * 編集セル(アカウント)押下の処理です。
     * 
     * @param request
     *            アカウントコードモード
     */
    @Execute
    @Validation
    public void accountContactEdit(EntryAccountCheckRequestModel request) {

        CrmAccountCheckChargeDto crmAccountCheckChargeDto =
            entryMapper.convertToCrmAccountCheckChargeDto(request);
        crmAccountLogic.isCharge(crmAccountCheckChargeDto);
    }

    /**
     * 詳細セル(アカウント)押下の処理です。
     * 
     * @param request
     *            アカウントコード
     */
    @Execute
    @Validation
    public void accountEdit(EntryAccountCheckRequestModel request) {

        CrmAccountCheckChargeDto crmAccountCheckChargeDto =
            entryMapper.convertToCrmAccountCheckChargeDto(request);
        crmAccountLogic.isCharge(crmAccountCheckChargeDto);
    }

    /**
     * 削除セル(アカウント)押下の処理です。
     * 
     * @param request
     *            削除セル(アカウント)押下条件モデル
     */
    @Execute
    @Validation
    public void accountDelete(EntryAccountDeleteRequestModel request) {
        AccountContactCriteriaDto criteriaDto =
            entryMapper.convertToAccountContactCriteriaDto(request);
        accountContactLogic.delete(criteriaDto, request.crmDomainCd);
    }

    /**
     * 削除セル(競合)押下の処理です。
     * 
     * @param request
     *            削除セル(競合)押下条件モデル
     */
    @Execute
    @Validation
    public void competitionDelete(EntryDeleteCompetitionRequestModel request) {
        ContactDeleteCompetitionCriteriaDto criteriaDto =
            entryMapper.convertToContactDeleteCompetitionCriteriaDto(request);
        crmContactCompetitionLogic.delete(criteriaDto);
    }
}
