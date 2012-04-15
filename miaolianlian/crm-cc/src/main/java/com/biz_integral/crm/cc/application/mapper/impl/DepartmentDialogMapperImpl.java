/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.StringUtil;

import com.biz_integral.crm.cc.application.controller.common.DepartmentDialogFilterRequestModel;
import com.biz_integral.crm.cc.application.controller.common.DepartmentDialogFilterResponseModel;
import com.biz_integral.crm.cc.application.mapper.DepartmentDialogMapper;
import com.biz_integral.crm.cc.domain.dto.DepartmentDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcDepartmentCmn;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link DepartmentDialogMapper}の実装です。
 * 
 */
public class DepartmentDialogMapperImpl implements DepartmentDialogMapper {

    /**
     * {@link BeanMapper}の実装です。
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link ParameterLogic}のクラスです。
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@inheritDoc}
     */
    @Override
    public DepartmentDialogCriteriaDto convert(
            DepartmentDialogFilterRequestModel model) {

        DepartmentDialogCriteriaDto criteria =
            beanMapper.map(model, DepartmentDialogCriteriaDto.class);

        String sortItem = model.sortItem;
        if (StringUtil.isNotEmpty(sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(model.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }

            if ("effectiveTermStart".equals(sortItem)) {
                sortItem = "startDate";

            } else if ("effectiveTermEnd".equals(sortItem)) {
                sortItem = "endDate";
            }

            criteria.setFirstOrderBySpec(sortItem, spec);
        }
        criteria.localeId = contextContainer.getUserContext().getLocaleID();
        criteria.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();

        String departmentSetCd =
            String.valueOf(parameterLogic.getEntity("CRMCC0004"));
        criteria.departmentSetCd = departmentSetCd;

        return criteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<DepartmentDialogFilterResponseModel> convert(
            PagingResult<CrmCcDepartmentCmn> crmCcDepartmentCmn) {

        PagingResult<DepartmentDialogFilterResponseModel> pagingList =
            new PagingResult<DepartmentDialogFilterResponseModel>();

        for (CrmCcDepartmentCmn depCmn : crmCcDepartmentCmn.getResultList()) {
            DepartmentDialogFilterResponseModel model =
                beanMapper.map(
                    depCmn,
                    DepartmentDialogFilterResponseModel.class);

            model.effectiveTermStart = depCmn.getStartDate();
            model.effectiveTermEnd = depCmn.getEndDate();
            pagingList.add(model);
        }
        pagingList.setAllRecordCount(crmCcDepartmentCmn.getAllRecordCount());
        pagingList.setLimit(crmCcDepartmentCmn.getLimit());
        pagingList.setOffset(crmCcDepartmentCmn.getOffset());
        return pagingList;
    }
}
