/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.controller.common;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.mapper.ContactDialogMapper;
import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.CrmContactLogic;
import com.biz_integral.crm.cc.domain.types.SexType;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * コンタクト選択ダイアログ画面のコントローラです。
 */
public class ContactSelectDialogController {
    /**
     * {@link ContactDialogMapper 検索Mapper}
     */
    @Resource
    protected ContactDialogMapper contactDialogMapper;

    /**
     * {@link CrmContactLogic コンタクト検索Logic}
     */
    @Resource
    protected CrmContactLogic crmContactLogic;

    /**
     * {@link EnumNamesRegistry 区分に関する名称管理}
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * 検索結果一覧ページャー押下の処理です。
     * 
     * @param request
     *            コンタクトダイアログの一覧検索条件モデル
     * @return 一覧検索結果
     */
    @Validation
    @Execute
    public PagingResponse<ContactSelectDialogFilterResponseModel> filter(
            ContactSelectDialogFilterRequestModel request) {
        ContactSearchCriteriaDto criteria =
            contactDialogMapper.convert(request);

        PagingResponse<ContactSelectDialogFilterResponseModel> result =
            new PagingResponse<ContactSelectDialogFilterResponseModel>();

        PagingResult<ContactSearchResultDto> pagingResult =
            crmContactLogic.getEntityList(criteria);

        PagingResult<ContactSelectDialogFilterResponseModel> pagingList =
            contactDialogMapper.convert(pagingResult);

        for (ContactSelectDialogFilterResponseModel model : pagingList) {
            if (model.sex != null) {
                model.sex =
                    this.enumNamesRegistry.getName(
                        SexType.getEnum(model.sex),
                        LocaleUtil.toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
        }

        result.setRows(pagingList.getResultList());
        result.setTotal(pagingList.getAllRecordCount());

        return result;
    }
}