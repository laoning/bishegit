/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

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
import com.biz_integral.crm.cc.domain.logic.AccountContactLogic;
import com.biz_integral.crm.cc.domain.logic.CampaignLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountChargeDepartmentLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountChargeUserLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountCompetitionLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.CrmChargeDepartmentListLogic;
import com.biz_integral.crm.cc.domain.logic.CrmChargeUserListLogic;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.crm.cc.domain.logic.ImCustomerLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.logic.ProposalLogic;
import com.biz_integral.crm.cc.domain.logic.SalesActivityLogic;
import com.biz_integral.crm.cc.domain.types.CrmAccountStatus;
import com.biz_integral.crm.cc.domain.types.CrmAccountType;
import com.biz_integral.crm.cc.domain.types.DealClass;
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
 * アカウント登録/更新画面のコントローラです。
 */
public class EntryController {

    /**
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * CRMアカウントロジック
     */
    @Resource
    protected CrmAccountLogic crmAccountLogic;

    /**
     * CRMアカウント担当組織ロジック
     */
    @Resource
    protected CrmAccountChargeDepartmentLogic crmAccountChargeDepartmentLogic;

    /**
     * CRMアカウント担当者ロジック
     */
    @Resource
    protected CrmAccountChargeUserLogic crmAccountChargeUserLogic;

    /**
     * CRMアカウントコンタクトロジック
     */
    @Resource
    protected AccountContactLogic accountContactLogic;

    /**
     * CRMアカウント競合ロジック
     */
    @Resource
    protected CrmAccountCompetitionLogic crmAccountCompetitionLogic;

    /**
     * CRMアカウント案件のロジック
     */
    @Resource
    protected ProposalLogic proposalLogic;

    /**
     * CRMアカウントキャンペーンのロジック
     */
    @Resource
    protected CampaignLogic campaignLogic;

    /**
     * 営業活動ロジック
     */
    @Resource
    protected SalesActivityLogic salesActivityLogic;

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
     * 取引先_共通用ロジック
     */
    @Resource
    protected ImCustomerLogic imCustomerLogic;

    /**
     * アカウント登録/更新で利用するマッビー
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
        response.crmAccountStatusList.add(new KeyValueBean());

        // 状況の一覧を取得します。
        for (CrmAccountStatus crmAccountStatus : CrmAccountStatus.values()) {
            String name =
                this.enumNamesRegistry.getName(crmAccountStatus, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.crmAccountStatusList.add(new KeyValueBean(
                name,
                crmAccountStatus.value()));
        }

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
        response.crmAccountTypeList.add(new KeyValueBean());

        // 区分の一覧を取得します。
        for (CrmAccountType crmAccountType : CrmAccountType.values()) {
            String name =
                this.enumNamesRegistry.getName(crmAccountType, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.crmAccountTypeList.add(new KeyValueBean(
                name,
                crmAccountType.value()));
        }

        // 1件目に空欄を追加します。
        response.dealClassList.add(new KeyValueBean());

        // 取引種別の一覧を取得します。

        for (DealClass dealClass : DealClass.values()) {
            String name =
                this.enumNamesRegistry.getName(dealClass, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            response.dealClassList
                .add(new KeyValueBean(name, dealClass.value()));
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
        response.contactFlag =
            ((UseType) parameterLogic.getEntity("CRMCC0006")).value();
        response.sfaFlag =
            ((UseType) parameterLogic.getEntity("CRMCC0007")).value();
        response.crmAccountAddInfoFlag =
            ((UseType) parameterLogic.getEntity("CRMCC0008")).value();
        response.crmDomainCd = parameterLogic.getEntity("CRMCC0002").toString();
        return response;
    }

    /**
     * 更新モード初期化を行いします。
     * 
     * @param request
     *            アカウント登録/更新画面の初期化条件
     * @return 画面初期化に利用する項目
     */
    @Execute
    @Validation
    public EntryInitializeResponseModel updateInitialize(
            EntryInitializeRequestModel request) {
        EntryInitializeResponseModel response =
            new EntryInitializeResponseModel();

        // アカウントを取得します。
        if ("update".equals(request.processModeType)) {
            String crmDomainCd =
                parameterLogic.getEntity("CRMCC0002").toString();

            // CRMアカウントを一件取得します。
            CrmCcAccountDto crmCcAccountDto =
                entryMapper.convertToCrmCcAccountDto(request);
            CrmCcAccount crmCcAccount =
                crmAccountLogic.getEntity(crmCcAccountDto);
            if (crmCcAccount == null
                || StringUtil.isEmpty(crmCcAccount.getCrmAccountCd())) {
                throw new OptimisticLockRuntimeException(null);
            }
            entryMapper.convert(crmCcAccount, response);

            // CRMアカウントの担当をチェックします。
            CrmAccountCheckChargeDto crmAccountCheckChargeDto =
                entryMapper.convertToCrmAccountCheckChargeDto(
                    request.crmAccountCd,
                    crmDomainCd);
            crmAccountLogic.isCharge(crmAccountCheckChargeDto);

            // CRMアカウント担当者を一覧検索します。
            CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto =
                entryMapper.convertToCrmCcAccountChargeUserCriteriaDto(request);
            chargeUserCriteriaDto.crmDomainCd = crmDomainCd;
            List<CrmCcAccountChargeUserResultDto> chargeUserDtoList =
                crmAccountChargeUserLogic.getEntityList(chargeUserCriteriaDto);

            for (CrmCcAccountChargeUserResultDto chargeUserDto : chargeUserDtoList) {
                if (chargeUserDto.mainCharge != null) {
                    chargeUserDto.mainCharge =
                        this.enumNamesRegistry.getName(MainChargeFlg
                            .getEnum(chargeUserDto.mainCharge), LocaleUtil
                            .toLocale(contextContainer
                                .getUserContext()
                                .getLocaleID()));
                }
                EntryAccountChargeUserResponseModel chargeUserResponse =
                    entryMapper.convertToChargeUserResponseModel(chargeUserDto);
                response.accountChargeUserResponseList.add(chargeUserResponse);
            }

            // CRMアカウント担当組織を一覧検索します。
            CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto =
                entryMapper.convertToCrmCcAccountChargeDeptCriteriaDto(request);
            chargeDeptCriteriaDto.crmDomainCd = crmDomainCd;

            List<CrmCcAccountChargeDeptResultDto> chargeDeptDtoList =
                crmAccountChargeDepartmentLogic
                    .getEntityList(chargeDeptCriteriaDto);
            for (CrmCcAccountChargeDeptResultDto chargeDeptDto : chargeDeptDtoList) {
                if (chargeDeptDto.mainCharge != null) {
                    chargeDeptDto.mainCharge =
                        this.enumNamesRegistry.getName(MainChargeFlg
                            .getEnum(chargeDeptDto.mainCharge), LocaleUtil
                            .toLocale(contextContainer
                                .getUserContext()
                                .getLocaleID()));
                }
                EntryAccountChargeDeptResponseModel chargeDeptResponse =
                    entryMapper.convertToChargeDeptResponseModel(chargeDeptDto);
                response.accountChargeDeptResponseList.add(chargeDeptResponse);
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

        // CRMアカウント担当組織を登録します。
        createDept(request.accountChargeDeptList, request.crmAccountCd);

        // CRMアカウント担当者を登録します。
        createUser(request.accountChargeUserList, request.crmAccountCd);

        // CRMアカウントを登録します。
        CrmCcAccountDto crmCcAccountDto =
            entryMapper.convertToCrmCcAccountDto(request);
        CrmCcAccount crmCcAccount = crmAccountLogic.create(crmCcAccountDto);
        response.versionNo = String.valueOf(crmCcAccount.getVersionNo());
        response.termCd = crmCcAccount.getTermCd();
        return response;
    }

    /**
     * コンタクト一覧ページャーを行いします。
     * 
     * @param request
     *            コンタク一覧ページャー条件モデル
     * @return コンタク一覧ページャー結果モデル
     */
    @Execute
    @Validation
    public PagingResponse<EntryContactFilterResponseModel> contactFilter(
            EntryContactFilterRequestModel request) {
        PagingResponse<EntryContactFilterResponseModel> result =
            new PagingResponse<EntryContactFilterResponseModel>();

        CrmCcAccountContactCriteriaDto criteriadto =
            entryMapper.convertToCrmCcAccountContactCriteriaDto(request);

        PagingResult<CrmCcAccountContactResultDto> pagingResult =
            accountContactLogic.getContactEntityList(criteriadto);
        PagingResult<EntryContactFilterResponseModel> pagingList =
            entryMapper.convertToContactResponseModel(pagingResult);
        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());

        return result;
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

        CrmCcAccountCompetitionDto dto =
            entryMapper.convertToCrmCcAccountCompetitionDto(request);
        PagingResult<CrmCcAccountCompetition> pagingResult =
            crmAccountCompetitionLogic.getEntityList(dto);

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
     * 案件一覧ページャーを行いします。
     * 
     * @param request
     *            案件一覧モデル
     * @return 案件一覧結果モデル
     */
    @Execute
    @Validation
    public PagingResponse<EntryProposalFilterResponseModel> proposalFilter(
            EntryProposalFilterRequestModel request) {

        PagingResponse<EntryProposalFilterResponseModel> result =
            new PagingResponse<EntryProposalFilterResponseModel>();
        ProposalCriteriaDto dto =
            entryMapper.convertToProposalCriteriaDto(request);
        dto.itemCategorySetCd =
            parameterLogic.getEntity("CRMCC0005").toString();
        PagingResult<ProposalResultDto> pagingResult =
            proposalLogic.getEntityList(dto);
        PagingResult<EntryProposalFilterResponseModel> pagingList =
            entryMapper.convertToProposalResponseModel(pagingResult);
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
        dto.isAccountCdCertainly = true;
        PagingResult<CampaignResultDto> pagingResult =
            campaignLogic.getEntityList(dto);
        PagingResult<EntryCampaignFilterResponseModel> pagingList =
            entryMapper.convertToCampaignResponseModel(pagingResult);
        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());

        return result;
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

        // CRMアカウント担当組織を登録します。
        createDept(request.accountChargeDeptList, request.crmAccountCd);

        // CRMアカウント担当者を登録します。
        createUser(request.accountChargeUserList, request.crmAccountCd);

        // CRMアカウントを更新します。
        CrmCcAccountDto crmCcAccountDto =
            entryMapper.convertToCrmCcAccountDto(request);
        crmAccountLogic.update(crmCcAccountDto);
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
        CrmCcAccountDto crmCcAccountDto =
            entryMapper.convertToCrmCcAccountDto(request);
        crmAccountLogic.delete(crmCcAccountDto);
    }

    /**
     * 取引先登録を行いします。
     * 
     * @param request
     *            取引先登録条件モデル
     */
    @Execute
    @Validation
    public void customerEntry(EntryCustomerEntryRequestModel request) {

        // CRMアカウント担当組織を登録します。
        createDept(request.accountChargeDeptList, request.crmAccountCd);

        // CRMアカウント担当者を登録します。
        createUser(request.accountChargeUserList, request.crmAccountCd);

        // CRMアカウントを更新し取引先を登録します。
        CrmCcAccountDto crmCcAccountDto =
            entryMapper.convertToCrmCcAccountDto(request);
        crmAccountLogic.createCustomer(crmCcAccountDto);
    }

    /**
     * CRMアカウント担当組織を登録します。
     * 
     * @param accountChargeDeptList
     *            担当組織一覧
     * @param crmAccountCd
     *            アカウントコード
     */
    private void createDept(
            List<EntryCreateDeptRequestModel> accountChargeDeptList,
            String crmAccountCd) {
        List<CrmCcAccountChargeDeptCriteriaDto> chargeDeptCriteriaDtoList =
            new ArrayList<CrmCcAccountChargeDeptCriteriaDto>();
        String crmDomainCd = parameterLogic.getEntity("CRMCC0002").toString();
        for (EntryCreateDeptRequestModel chargeDeptRequestModel : accountChargeDeptList) {
            CrmCcAccountChargeDeptCriteriaDto chargeDeptCriteriaDto =
                entryMapper
                    .convertToCrmCcAccountChargeDeptCriteriaDto(chargeDeptRequestModel);
            chargeDeptCriteriaDto.crmAccountCd = crmAccountCd;
            chargeDeptCriteriaDto.crmDomainCd = crmDomainCd;
            chargeDeptCriteriaDtoList.add(chargeDeptCriteriaDto);
        }
        crmAccountChargeDepartmentLogic.create(chargeDeptCriteriaDtoList);
    }

    /**
     * CRMアカウント担当者を登録します。
     * 
     * @param accountChargeUserList
     *            担当者一覧
     * @param crmAccountCd
     *            アカウントコード
     */
    private void createUser(
            List<EntryCreateUserRequestModel> accountChargeUserList,
            String crmAccountCd) {
        List<CrmCcAccountChargeUserCriteriaDto> chargeUserCriteriaDtoList =
            new ArrayList<CrmCcAccountChargeUserCriteriaDto>();
        String crmDomainCd = parameterLogic.getEntity("CRMCC0002").toString();
        for (EntryCreateUserRequestModel chargeUserRequestModel : accountChargeUserList) {
            CrmCcAccountChargeUserCriteriaDto chargeUserCriteriaDto =
                entryMapper
                    .convertToCrmCcAccountChargeUserCriteriaDto(chargeUserRequestModel);
            chargeUserCriteriaDto.crmAccountCd = crmAccountCd;
            chargeUserCriteriaDto.crmDomainCd = crmDomainCd;
            chargeUserCriteriaDtoList.add(chargeUserCriteriaDto);
        }
        crmAccountChargeUserLogic.create(chargeUserCriteriaDtoList);
    }

    /**
     * コンタクト追加ボタン押下の処理です。
     * 
     * @param request
     *            コンタクト追加ボタンの条件モデル
     */
    @Execute
    @Validation
    public void contactAdd(EntryContactAddRequestModel request) {
        List<AccountContactCriteriaDto> accountContactCriteriaDtoList =
            entryMapper.convertToAccountContactCriteriaDtoList(request);
        accountContactLogic.createContact(
            accountContactCriteriaDtoList,
            request.crmDomainCd);
    }

    /**
	 * 取引先選択ボタン押下の処理です。
	 * 
	 * @param request
	 *            取引先選択ボタンの条件モデル
	 * @return アカウント登録/更新画面の取引先選択ボタン結果モデル
	 */
    @Execute
    @Validation
    public EntryCustomerSelectResponseModel customerSelect(
            EntryCustomerSelectRequestModel request) {
        CustomerSelectCriteriaDto dto =
            entryMapper.convertToCustomerSelectCriteriaDto(request);
        CrmCcCustomerCmn crmCcCustomerCmn = imCustomerLogic.getEntity(dto);
        EntryCustomerSelectResponseModel result =
            entryMapper.convertToCustomerSelectResponseModel(crmCcCustomerCmn);
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
        response.accountChargeDeptResponseList =
            entryMapper.convertToAccountChargeDeptResponseModel(resultDto);
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
        response.accountChargeDeptResponseList =
            entryMapper.convertToAccountChargeDeptResponseModel(resultDto);
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
        response.accountChargeUserResponseList =
            entryMapper.convertToAccountChargeUserResponseModel(resultDto);
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
        response.accountChargeUserResponseList =
            entryMapper.convertToAccountChargeUserResponseModel(resultDto);
        return response;
    }

    /**
     * 編集セル(コンタクト)押下の処理です。
     * 
     * @param request
     *            コンタクトコード
     */
    @Execute
    @Validation
    public void accountContactEdit(EntryContactCheckRequestModel request) {

        CrmContactCheckChargeDto crmContactCheckChargeDto =
            entryMapper.convertToCrmContactCheckChargeDto(request.crmContactCd);
        crmContactLogic.isCharge(crmContactCheckChargeDto);
    }

    /**
     * 詳細セル(コンタクト)押下の処理です。
     * 
     * @param request
     *            コンタクトコード
     */
    @Execute
    @Validation
    public void contactEdit(EntryContactCheckRequestModel request) {
        CrmContactCheckChargeDto crmContactCheckChargeDto =
            entryMapper.convertToCrmContactCheckChargeDto(request.crmContactCd);
        crmContactLogic.isCharge(crmContactCheckChargeDto);
    }

    /**
     * 削除セル(コンタクト)押下の処理です。
     * 
     * @param request
     *            削除セル(コンタクト)押下条件モデル
     */
    @Execute
    @Validation
    public void contactDelete(EntryContactDeleteRequestModel request) {
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
    public void deleteCompetition(EntryDeleteCompetitionRequestModel request) {
        DeleteCompetitionCriteriaDto criteriaDto =
            entryMapper.convertToDeleteCompetitionCriteriaDto(request);
        crmAccountCompetitionLogic.delete(criteriaDto);
    }

    /**
     * 詳細セル(案件)押下の処理です。
     * 
     * @param request
     *            詳細セル(案件)押下条件モデル
     */
    @Execute
    @Validation
    public void proposalEdit(EntryProposalEditRequestModel request) {
        ProposalCheckChargeDto proposalCheckChargeDto =
            entryMapper.convertToProposalCheckChargeDto(request.proposalCd);

        proposalLogic.isCharge(proposalCheckChargeDto);
    }
}
