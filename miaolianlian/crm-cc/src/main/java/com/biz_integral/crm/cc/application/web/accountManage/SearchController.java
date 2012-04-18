/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountManage;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
import com.biz_integral.crm.cc.domain.logic.FunctionAccessLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.CrmAccountStatus;
import com.biz_integral.crm.cc.domain.types.CrmAccountType;
import com.biz_integral.crm.cc.domain.types.ImportantLevelType;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウント検索画面のコントローラです。
 */
public class SearchController {

    /**
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link SearchMapper アカウント検索Mapper}
     */
    @Resource
    protected SearchMapper searchMapper;

    /**
     * {@link CrmAccountLogic アカウント検索Logic}
     */
    @Resource
    protected CrmAccountLogic crmAccountLogic;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link ParameterLogic}
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@link FunctionAccessLogic}
     */
    @Resource
    protected FunctionAccessLogic functionAccessLogic;

    /**
     * アカウント検索遷移先アプリケーションID
     */
    private static final String TRANSFER_APPLICATION_ID = "crm";

    /**
     * アカウント検索遷移先モジュールID
     */
    private static final String TRANSFER_MODULE_ID = "cc";

    /**
     * アカウント登録のユースケースID
     */
    private static final String TRANSFER_USECASE_ID = "accountManage";

    /**
     * アカウント登録のフィーチャーID
     */
    private static final String TRANSFER_FEATURE_ID = "entry";

    /**
     * アカウント新規権限ID
     */
    private static final String ACCESS_NEW_ACCOUNT_ID =
        "CRM.CC.FUNC_ACCESS_NEW_ACCOUNT";

    /**
     * 初期化を行います。
     * 
     * @return 画面初期化に利用する項目
     */
    @Execute
    public SearchInitializeResponseModel initialize() {
        SearchInitializeResponseModel model =
            new SearchInitializeResponseModel();
        // 1件目に空欄を追加します。
        model.crmAccountTypes.add(new KeyValueBean());
        // 区分の一覧を取得します。
        for (CrmAccountType crmAccountType : CrmAccountType.values()) {
            String name =
                this.enumNamesRegistry.getName(crmAccountType, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            model.crmAccountTypes.add(new KeyValueBean(name, crmAccountType
                .value()));
        }

        // 1件目に空欄を追加します。
        model.importantLevelTypes.add(new KeyValueBean());
        // 重要度の一覧を取得します。
        for (ImportantLevelType importantLevelType : ImportantLevelType
            .values()) {
            String name =
                this.enumNamesRegistry.getName(importantLevelType, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            model.importantLevelTypes.add(new KeyValueBean(
                name,
                importantLevelType.value()));
        }

        // 1件目に空欄を追加します。
        model.crmAccountStatusList.add(new KeyValueBean());
        // 状況の一覧を取得します。
        for (CrmAccountStatus crmAccountStatus : CrmAccountStatus.values()) {
            String name =
                this.enumNamesRegistry.getName(crmAccountStatus, LocaleUtil
                    .toLocale(contextContainer.getUserContext().getLocaleID()));
            model.crmAccountStatusList.add(new KeyValueBean(
                name,
                crmAccountStatus.value()));
        }

        Object transferApplicationID = parameterLogic.getEntity("CRMCC0014");
        Object transferModuleID = parameterLogic.getEntity("CRMCC0015");
        Object transferUseCaseID = parameterLogic.getEntity("CRMCC0016");
        Object transferFeatureID = parameterLogic.getEntity("CRMCC0017");
        if (transferApplicationID != null && !"".equals(transferApplicationID)) {
            model.transferApplicationID = transferApplicationID.toString();
        } else {
            model.transferApplicationID = TRANSFER_APPLICATION_ID;
        }
        if (transferModuleID != null && !"".equals(transferModuleID)) {
            model.transferModuleID = transferModuleID.toString();
        } else {
            model.transferModuleID = TRANSFER_MODULE_ID;
        }
        if (transferUseCaseID != null && !"".equals(transferUseCaseID)) {
            model.transferUseCaseID = transferUseCaseID.toString();
        } else {
            model.transferUseCaseID = TRANSFER_USECASE_ID;
        }
        if (transferFeatureID != null && !"".equals(transferFeatureID)) {
            model.transferFeatureID = transferFeatureID.toString();
        } else {
            model.transferFeatureID = TRANSFER_FEATURE_ID;
        }

        model.accountNewFlag =
            functionAccessLogic.checkAuthority(ACCESS_NEW_ACCOUNT_ID);

        return model;
    }

    /**
     * 検索結果一覧ページャー押下の処理です。
     * 
     * @param request
     *            CRMアカウント一覧検索条件モデル
     * @return {@link SearchFilterResponseModel}
     */
    @Execute
    @Validation
    public PagingResponse<SearchFilterResponseModel> filter(
            SearchFilterRequestModel request) {
        AccountSearchCriteriaDto criteria = searchMapper.convert(request);
        PagingResponse<SearchFilterResponseModel> result =
            new PagingResponse<SearchFilterResponseModel>();
        PagingResult<AccountSearchResultDto> pagingResult =
            crmAccountLogic.getEntityList(criteria);

        PagingResult<SearchFilterResponseModel> pagingList =
            searchMapper.convert(pagingResult);

        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());
        return result;
    }
}
