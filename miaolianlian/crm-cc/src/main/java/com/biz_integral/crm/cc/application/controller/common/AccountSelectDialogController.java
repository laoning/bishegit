/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.AccountDialogMapper;
import com.biz_integral.crm.cc.domain.dto.AccountSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmAccountLogic;
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
 * アカウント選択ダイアログのコントローラです。
 */
public class AccountSelectDialogController {

    /**
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link AccountDialogMapper アカウント検索Mapper}
     */
    @Resource
    protected AccountDialogMapper accountDialogMapper;

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
     * 初期化を行います。
     * 
     * @return アカウント初期化結果
     */
    @Execute
    public AccountSelectDialogInitializeResponseModel initialize() {
        AccountSelectDialogInitializeResponseModel model =
            new AccountSelectDialogInitializeResponseModel();

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

        return model;
    }

    /**
     * 検索ボタン押下の処理です。
     * 
     * @param request
     *            アカウントダイアログの一覧検索条件モデル
     * @return 画面一覧検索結果
     */
    @Validation
    @Execute
    public PagingResponse<AccountSelectDialogFilterResponseModel> filter(
            AccountSelectDialogFilterRequestModel request) {
        AccountSearchCriteriaDto criteria =
            accountDialogMapper.convert(request);
        PagingResponse<AccountSelectDialogFilterResponseModel> result =
            new PagingResponse<AccountSelectDialogFilterResponseModel>();
        PagingResult<AccountSearchResultDto> pagingResult =
            crmAccountLogic.getEntityList(criteria);

        PagingResult<AccountSelectDialogFilterResponseModel> pagingList =
            accountDialogMapper.convert(pagingResult);

        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());

        return result;
    }
}