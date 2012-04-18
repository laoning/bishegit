/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrp.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.OrderByItem.OrderingSpec;
import org.seasar.framework.util.BooleanConversionUtil;

import com.biz_integral.crm.cc.application.web.acctGrp.AcctGrpAthRequestModel;
import com.biz_integral.crm.cc.application.web.acctGrp.EntryMapper;
import com.biz_integral.crm.cc.application.web.acctGrp.EntryRequestModel;
import com.biz_integral.crm.cc.domain.dto.AcctGrpDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrp;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeUser;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.service.domain.master.BizConfigurationProvider;

/**
 * {@link EntryMapper}の実装です。
 */
public class EntryMapperImpl implements EntryMapper {

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
     * {@link BizConfigurationProvider コンテキストコンテナー}
     */
    @Resource
    protected BizConfigurationProvider bizConfigurationProvider;

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAcctGrp convertToCrmCcAcctGrp(EntryRequestModel request) {

        CrmCcAcctGrp entity = beanMapper.map(request, CrmCcAcctGrp.class);
        entity.setCompanyCd(getCompanyCd());
        entity.setCrmAccountGroupCd(request.crmAccountGroupCd);
        entity.setStartDate(bizConfigurationProvider.getStartDate());
        entity.setEndDate(bizConfigurationProvider.getEndDate());
        entity.setDeleteFlag(false);
        entity.setTermCd(UniqueIdGenerator.getInstance().create());
        entity.setSortKey(1L);

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AcctGrpDto convertToSearchAccount(AcctGrpAthRequestModel request) {

        AcctGrpDto criteria = beanMapper.map(request, AcctGrpDto.class);

        criteria.companyCd = this.getCompanyCd();

        String sortBy = request.sortBy;
        if (StringUtil.isNotEmpty(sortBy)) {
            boolean isAsc = BooleanConversionUtil.toBoolean(request.isAsc);
            OrderingSpec spec;
            if (isAsc) {
                spec = OrderingSpec.ASC;
            } else {
                spec = OrderingSpec.DESC;
            }
            criteria.setFirstOrderBySpec(sortBy, spec);
        }
        return criteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAcctGrpChargeDept convertToCrmCcAcctGrpCharDeptEntity(
            CrmCcAcctGrpChargeDept request) {
        CrmCcAcctGrpChargeDept entity =
            beanMapper.map(request, CrmCcAcctGrpChargeDept.class);
        entity.setCompanyCd(getCompanyCd());
        Date endDate = entity.getEndDate();
        endDate = DateUtil.getCalculator(endDate).addDay(1).asDate();
        entity.setEndDate(endDate);
        return entity;
    }

    @Override
    public CrmCcAcctGrpChargeUser convertToCrmCcAcctGrpCharUserEntity(
            CrmCcAcctGrpChargeUser request) {
        CrmCcAcctGrpChargeUser entity =
            beanMapper.map(request, CrmCcAcctGrpChargeUser.class);
        entity.setCompanyCd(getCompanyCd());

        Date endDate = entity.getEndDate();
        endDate = DateUtil.getCalculator(endDate).addDay(1).asDate();
        entity.setEndDate(endDate);

        return entity;
    }

    private String getCompanyCd() {
        return contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode();
    }

}
