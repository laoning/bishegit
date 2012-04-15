/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp;

import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmAcctGrpLogic;
import com.biz_integral.crm.cc.domain.logic.FunctionAccessLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.util.CollectionsUtil;
import com.biz_integral.service.domain.master.locale.BizLocaleInfo;
import com.biz_integral.service.domain.master.locale.BizLocaleManager;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウントグループ検索画面のコントローラです。
 */
public class SearchController {

    /**
     * {@link SearchMapper アカウントグループ検索Mapper}
     */
    @Resource
    protected SearchMapper searchMapper;

    /**
     * {@link CrmAcctGroupLogic アカウントグループLogic}
     */
    @Resource
    protected CrmAcctGrpLogic crmAcctGrpLogic;

    /**
     * {@link ParameterLogic パラメータLogic}
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@link ContextContainer コンテキストコンテナ}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link FunctionAccessLogic}
     */
    @Resource
    protected FunctionAccessLogic functionAccessLogic;

    /**
     * {@link BizLocaleManager ロケールマネージャー}
     */
    @Resource
    public BizLocaleManager bizLocaleManager;

    /**
     * CRM領域コード取得用のパラメータコード値
     */
    private static final String crmDomainCd = "CRMCC0002";

    /**
     * CRM組織コード取得用のパラメータコード値
     */
    private static final String crmDepartmentSetCd = "CRMCC0004";

    /**
     * アカウントグループ新規の権限ID
     */
    private static final String ACCESS_NEW_ACCT_GRP_ID =
        "CRM.CC.FUNC_ACCESS_NEW_ACCT_GRP";

    /**
     * 初期化を行います。
     * 
     * @return 画面初期化に利用する項目
     */
    @Execute
    public SearchInitializeResponseModel initialize() {

        SearchInitializeResponseModel model =
            new SearchInitializeResponseModel();

        // ロケールIDの一覧を取得します。
        model.localeId.addAll(this.getLocaleForComboList());

        // 初期ロケールID
        model.defaultLocaleId = contextContainer.getUserContext().getLocaleID();

        model.accountGroupNewFlag =
            functionAccessLogic.checkAuthority(ACCESS_NEW_ACCT_GRP_ID);

        return model;
    }

    /**
     * 検索結果一覧ページャー押下の処理です。
     * 
     * @param request
     *            アカウントグループ一覧検索条件モデル
     * @return {@link SearchFilterResponseModel}
     */
    @Execute
    @Validation
    public PagingResponse<SearchFilterResponseModel> filter(
            SearchFilterRequestModel request) {

        AcctGrpSearchCriteriaDto criteria = searchMapper.convert(request);

        criteria.crmDomainCd = (String) parameterLogic.getEntity(crmDomainCd);
        criteria.crmDepartmentSetCd =
            (String) parameterLogic.getEntity(crmDepartmentSetCd);

        PagingResponse<SearchFilterResponseModel> result =
            new PagingResponse<SearchFilterResponseModel>();
        PagingResult<AcctGrpSearchResultDto> pagingResult =
            crmAcctGrpLogic.getAcctGrpList(criteria);

        PagingResult<SearchFilterResponseModel> pagingList =
            searchMapper.convert(pagingResult);

        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());
        return result;
    }

    /**
     * ロケールコンボ用のリストを取得します。
     * 
     * @return ロケールコンボボックス用のリスト
     */
    private List<KeyValueBean> getLocaleForComboList() {

        List<BizLocaleInfo> bizLocaleInfoList =
            bizLocaleManager.getApplicationLocales(contextContainer
                .getCurrentFeatureContext()
                .getApplicationID());

        List<KeyValueBean> localeIdList = CollectionsUtil.newArrayList();
        for (BizLocaleInfo entity : bizLocaleInfoList) {
            KeyValueBean item = new KeyValueBean();
            item.key = entity.getLocaleId();
            item.value = entity.getDisplayName();
            localeIdList.add(item);
        }
        return localeIdList;
    }

}
