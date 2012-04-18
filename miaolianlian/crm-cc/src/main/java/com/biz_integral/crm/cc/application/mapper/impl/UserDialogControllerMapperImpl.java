/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

import com.biz_integral.crm.cc.application.controller.common.UserDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.UserDialogFilterResponseModel;
import com.biz_integral.crm.cc.application.mapper.UserDialogControllerMapper;
import com.biz_integral.crm.cc.domain.dto.UserDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.UserDialogReslutDto;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link UserDialogControllerMapper}の実装です。
 */
public class UserDialogControllerMapperImpl implements
        UserDialogControllerMapper {

    /**
     * コンテキストコンテナ。
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link ParameterLogic}です
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@link BeanMapper}の実装です。
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDialogCriteriaDto searchConvert(
            UserDialogFilterRequestModel request) {
        UserDialogCriteriaDto criteria =
            beanMapper.map(request, UserDialogCriteriaDto.class);

        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteria.localeId = contextContainer.getUserContext().getLocaleID();
        criteria.companyDepartmentSetCd =
            (String) parameterLogic.getEntity("CRMCC0004");
        criteria.deleteFlag = "0";

        String sortBy = request.sortBy;
        if (!StringUtil.isEmpty(sortBy)) {
            boolean isAsc = BooleanConversionUtil.toBoolean(request.isAsc);
            OrderingSpec spec;
            if (isAsc) {
                spec = OrderingSpec.ASC;
            } else {
                spec = OrderingSpec.DESC;
            }

            if ("effectiveTermStart".equals(sortBy)) {

                sortBy = "startDate";
            } else if ("effectiveTermEnd".equals(sortBy)) {
                sortBy = "endDate";
            }

            criteria.setFirstOrderBySpec(sortBy, spec);
        }
        return criteria;
    }

    /**
     * {@inheritDoc}
     */
    public PagingResult<UserDialogFilterResponseModel> searchConvert(
            PagingResult<UserDialogReslutDto> searchResult) {

        PagingResult<UserDialogFilterResponseModel> result =
            new PagingResult<UserDialogFilterResponseModel>();
        UserDialogFilterResponseModel model;
        for (UserDialogReslutDto dist : searchResult) {
            model = beanMapper.map(dist, UserDialogFilterResponseModel.class);
            model.effectiveTermStart = dist.getStartDate();
            model.effectiveTermEnd = dist.getEndDate();

            result.add(model);
        }
        result.setAllRecordCount(searchResult.getAllRecordCount());
        result.setLimit(searchResult.getLimit());
        result.setOffset(searchResult.getOffset());

        return result;
    }
}
