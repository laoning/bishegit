/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage.impl;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

import com.biz_integral.crm.cc.application.web.parameterManage.GrdParameterListResponseModel;
import com.biz_integral.crm.cc.application.web.parameterManage.SearchFilterRequestModel;
import com.biz_integral.crm.cc.application.web.parameterManage.SearchFilterResponseModel;
import com.biz_integral.crm.cc.application.web.parameterManage.SearchMapper;
import com.biz_integral.crm.cc.domain.context.CrmContext;
import com.biz_integral.crm.cc.domain.dto.ParameterManageDto;
import com.biz_integral.crm.cc.domain.types.ParameterCtg;
import com.biz_integral.extension.beans.BizBeans;
import com.biz_integral.foundation.core.util.CollectionsUtil;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link SearchMapper パラメータ検索}の実装です。
 */
public class SearchMapperImpl implements SearchMapper {

    /** CRMコンテキスト */
    @Resource
    protected CrmContext crmContext;

    /**
     *{@inheritDoc}
     */
    @Override
    public ParameterManageDto createParamSrchDefaultDto(
            SearchFilterRequestModel requestModel) {

        ParameterManageDto dto =
            BizBeans
                .createAndCopy(ParameterManageDto.class, requestModel)
                .execute();

        // 条件設定
        dto.setCompanyCd(crmContext.getCompanyCd());
        dto.setLocaleId(crmContext.getLocaleId());
        dto.setSrchBaseDte(DateUtil.parse(DateUtil
            .nowDateString(DateUtil.DATE_FORMAT), DateUtil.DATE_FORMAT));

        if (!StringUtil.isEmpty(requestModel.sortBy)) {
            boolean asc = BooleanConversionUtil.toBoolean(requestModel.isAsc);
            OrderingSpec spec = asc ? OrderingSpec.ASC : OrderingSpec.DESC;
            dto.setFirstOrderBySpec(requestModel.sortBy, spec);
        }

        // パラメータ分類 0：初期設定
        dto.setParameterCategory(ParameterCtg.DEFAULTDISPLAY.value());

        return dto;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public SearchFilterResponseModel createSearchResponseModel(
            PagingResult<ParameterManageDto> list) {
        SearchFilterResponseModel responseModel =
            new SearchFilterResponseModel();

        List<GrdParameterListResponseModel> tempList =
            CollectionsUtil.newArrayList();

        GrdParameterListResponseModel tempModel = null;

        if (list != null && !list.isEmpty()) {
            for (ParameterManageDto dto : list) {

                tempModel =
                    BizBeans.createAndCopy(
                        GrdParameterListResponseModel.class,
                        dto).execute();

                tempList.add(tempModel);
            }
        }
        responseModel.total = String.valueOf(list.getAllRecordCount());
        responseModel.grdParameterListDefaults = tempList;

        return responseModel;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ParameterManageDto createParamSrchControlDto(
            SearchFilterRequestModel requestModel) {

        ParameterManageDto dto =
            BizBeans
                .createAndCopy(ParameterManageDto.class, requestModel)
                .execute();

        // 条件設定
        dto.setCompanyCd(crmContext.getCompanyCd());
        dto.setLocaleId(crmContext.getLocaleId());
        dto.setSrchBaseDte(DateUtil.parse(DateUtil
            .nowDateString(DateUtil.DATE_FORMAT), DateUtil.DATE_FORMAT));

        if (!StringUtil.isEmpty(requestModel.sortBy)) {
            boolean asc = BooleanConversionUtil.toBoolean(requestModel.isAsc);
            OrderingSpec spec = asc ? OrderingSpec.ASC : OrderingSpec.DESC;
            dto.setFirstOrderBySpec(requestModel.sortBy, spec);
        }

        // パラメータ分類 1：表示・コントロール
        dto.setParameterCategory(ParameterCtg.DISPLAYANDCONTROL.value());

        return dto;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public SearchFilterResponseModel createSearchControlResponseModel(
            PagingResult<ParameterManageDto> list) {
        SearchFilterResponseModel responseModel =
            new SearchFilterResponseModel();

        List<GrdParameterListResponseModel> tempList =
            CollectionsUtil.newArrayList();

        GrdParameterListResponseModel tempModel = null;

        if (list != null && !list.isEmpty()) {
            for (ParameterManageDto dto : list) {
                tempModel =
                    BizBeans.createAndCopy(
                        GrdParameterListResponseModel.class,
                        dto).execute();

                tempList.add(tempModel);
            }
        }
        responseModel.total = String.valueOf(list.getAllRecordCount());
        responseModel.grdParameterListDisplayControls = tempList;
        return responseModel;
    }
}
