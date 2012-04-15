/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.crm.cc.domain.logic.FunctionAccessLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.UseType;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * コンタクト検索画面のコントローラです。
 */
public class SearchController {

    /**
     * {@link SearchMapper コンタクト検索Mapper}
     */
    @Resource
    protected SearchMapper searchMapper;

    /**
     * {@link CrmContactLogic コンタクト検索Logic}
     */
    @Resource
    protected CrmContactLogic crmContactLogic;

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
     * コンタクト新規権限ID
     */
    private static final String ACCESS_NEW_CONTACT_ID =
        "CRM.CC.FUNC_ACCESS_NEW_CONTACT";

    /**
     * 初期化ボタン押下の処理です。
     * 
     * @return コンタクト検索初期化ボタン押下responseモデル
     */
    @Execute
    public SearchInitializeResponseModel initialize() {
        SearchInitializeResponseModel response =
            new SearchInitializeResponseModel();
        UseType useType = (UseType) parameterLogic.getEntity("CRMCC0006");
        response.useType = useType.value();
        response.contactNewFlag =
            functionAccessLogic.checkAuthority(ACCESS_NEW_CONTACT_ID);

        return response;
    }

    /**
     * 検索結果一覧ページャー押下の処理です。
     * 
     * @param request
     *            CRMコンタクト一覧検索条件モデル
     * @return {@link SearchFilterResponseModel}
     */
    @Execute
    @Validation
    public PagingResponse<SearchFilterResponseModel> filter(
            SearchFilterRequestModel request) {
        ContactSearchCriteriaDto criteria = searchMapper.convert(request);
        PagingResponse<SearchFilterResponseModel> result =
            new PagingResponse<SearchFilterResponseModel>();
        PagingResult<ContactSearchResultDto> pagingResult =
            crmContactLogic.getEntityList(criteria);

        PagingResult<SearchFilterResponseModel> pagingList =
            searchMapper.convert(pagingResult);

        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());
        return result;
    }
}
