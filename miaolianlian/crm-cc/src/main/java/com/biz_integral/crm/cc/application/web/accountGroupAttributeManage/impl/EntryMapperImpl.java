/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountGroupAttributeManage.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.biz_integral.crm.cc.application.web.accountGroupAttributeManage.EntryInitializeRequestModel;
import com.biz_integral.crm.cc.application.web.accountGroupAttributeManage.EntryMapper;
import com.biz_integral.crm.cc.application.web.accountGroupAttributeManage.EntryRequestModel;
import com.biz_integral.crm.cc.domain.dto.ModuleDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpModule;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.mapper.BeanMapper;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;

/**
 * {@link EntryMapper}の実装です。
 */
public class EntryMapperImpl implements EntryMapper {

    /**
     * {@link ContextContainer コンテキストコンテナ}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BeanMapper}
     */
    @Resource(name = "crmCcBeanMapper")
    protected BeanMapper beanMapper;

    @Override
    public CrmCcAccountGrpAttr convertToCrmCcAccountGrpAttr(
            EntryRequestModel model) {
        CrmCcAccountGrpAttr dto =
            beanMapper.map(model, CrmCcAccountGrpAttr.class);

        dto.setCompanyCd(getCompanyCd());
        dto.setSortKey(0L);
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountGrpModule> convertToCrmCcAccountGrpModule(
            EntryRequestModel model) {

        String crmAccountClassCd = model.crmAccountClassCd;
        String companyCd = getCompanyCd();

        List<CrmCcAccountGrpModule> dtoList =
            new ArrayList<CrmCcAccountGrpModule>();

        List<ModuleDto> mdlList = model.moduleList;

        for (ModuleDto mdl : mdlList) {
            if ("true".equalsIgnoreCase(mdl.selected)) {
                CrmCcAccountGrpModule entity = new CrmCcAccountGrpModule();
                entity = beanMapper.map(model, CrmCcAccountGrpModule.class);
                entity.setModuleId(mdl.moduleId);
                entity.setCompanyCd(companyCd);
                entity.setCrmAccountClassCd(crmAccountClassCd);
                entity.setVersionNo(null);
                entity.setSortKey(0L);
                dtoList.add(entity);
            }
        }
        return dtoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountGrpAttr convert(EntryInitializeRequestModel model) {
        String companyCd = getCompanyCd();
        String crmAccountClassCd = model.crmAccountClassCd;
        String dealClass = model.dealClass;

        if (StringUtils.isEmpty(crmAccountClassCd)
            || StringUtils.isEmpty(dealClass)) {
            this.throwErrorMessage("E.CRM.CC.00039");
        }

        CrmCcAccountGrpAttr dto = new CrmCcAccountGrpAttr();
        dto.setCompanyCd(companyCd);
        dto.setCrmAccountClassCd(crmAccountClassCd);
        dto.setDealClass(dealClass);
        return dto;
    }

    /**
     * 会社コードを取得します。
     * 
     * @param String
     * 
     */
    private String getCompanyCd() {
        return contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode();
    }

    /**
     * エラーメッセージをスローする。
     * 
     * @param String
     *            messageId：メッセージID
     * 
     * @return なし
     */
    private void throwErrorMessage(String messageId) {
        ValidationResults validationResults = new ValidationResults();
        validationResults.add(new ValidationResult(MessageBuilder.create(
            messageId).toMessage()));
        throw new ValidationException(validationResults);
    }
}
