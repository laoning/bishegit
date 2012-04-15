/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.contactManage.impl;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;

import com.biz_integral.crm.cc.application.web.contactManage.SearchFilterRequestModel;
import com.biz_integral.crm.cc.application.web.contactManage.SearchFilterResponseModel;
import com.biz_integral.crm.cc.application.web.contactManage.SearchMapper;
import com.biz_integral.crm.cc.domain.dto.ContactSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.ContactSearchResultDto;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.SexType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.types.EnumNamesRegistry;
import com.biz_integral.foundation.core.util.LocaleUtil;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * {@link SearchMapper}の実装です。
 */
public class SearchMapperImpl implements SearchMapper {

    /**
     * {@link BeanMapper}
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

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
     * 区分に関する名称管理API
     */
    @Resource
    protected EnumNamesRegistry enumNamesRegistry;

    /**
     * {@inheritDoc}
     */
    @Override
    public ContactSearchCriteriaDto convert(SearchFilterRequestModel model) {
        ContactSearchCriteriaDto criteriaDto =
            beanMapper.map(model, ContactSearchCriteriaDto.class);

        criteriaDto.companyCd =
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode();
        criteriaDto.userCode = contextContainer.getUserContext().getUserID();
        criteriaDto.localeID = contextContainer.getUserContext().getLocaleID();

        criteriaDto.departmentSetCd =
            (String) parameterLogic.getEntity("CRMCC0004");

        if (StringUtil.isNotEmpty(model.sortItem)) {
            boolean asc = BooleanConversionUtil.toBoolean(model.sort);
            OrderingSpec spec = OrderingSpec.DESC;
            if (asc) {
                spec = OrderingSpec.ASC;
            }

            if ("emailAddress".equals(model.sortItem)) {
                model.sortItem = "emailAddress1";
            }
            criteriaDto.setFirstOrderBySpec(model.sortItem, spec);
        }

        return criteriaDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingResult<SearchFilterResponseModel> convert(
            PagingResult<ContactSearchResultDto> contactSearchResultDto) {

        PagingResult<SearchFilterResponseModel> pagingList =
            new PagingResult<SearchFilterResponseModel>();

        for (ContactSearchResultDto resultDto : contactSearchResultDto
            .getResultList()) {
            SearchFilterResponseModel model =
                beanMapper.map(resultDto, SearchFilterResponseModel.class);

            // 性別を取得します。
            String sex = resultDto.getSex();
            if (StringUtil.isNotEmpty(sex)) {
                model.sex =
                    enumNamesRegistry.getName(SexType.getEnum(sex), LocaleUtil
                        .toLocale(contextContainer
                            .getUserContext()
                            .getLocaleID()));
            }
            pagingList.add(model);
        }
        pagingList
            .setAllRecordCount(contactSearchResultDto.getAllRecordCount());

        return pagingList;
    }
}
