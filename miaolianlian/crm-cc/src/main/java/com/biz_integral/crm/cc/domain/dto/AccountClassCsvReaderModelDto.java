/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.crm.cc.domain.util.DataCheckUtil;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;

/**
 * 入力ストリームのドメインです。
 */
public final class AccountClassCsvReaderModelDto {

    /** アカウント分類コード */
    public String crmAccountClassCd;
    /** アカウント分類名 */
    public String crmAccountClassName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * アカウント分類コードをチェックします。
     */
    public void checkCrmAccountClassCd() {
        if (StringUtil.isNotEmpty(crmAccountClassCd)) {
            DataCheckUtil.checkStringDigit(
                crmAccountClassCd,
                20,
                "CRM.CC.crmAccountClassCd");

            DataCheckUtil.checkNumericAlphaString(
                crmAccountClassCd,
                "CRM.CC.crmAccountClassCd");
        } else {
            ValidationResults validationResult = new ValidationResults();
            validationResult.add(new ValidationResult(MessageBuilder.create(
                "E.BIZ.FC.14019").addParameter(
                MessageBuilder.$("CRM.CC.crmAccountClassCd")).toMessage()));
            throw new ValidationException(validationResult);
        }
    }

    /**
     * アカウント分類名をチェックします。
     */
    public void checkCrmAccountClassName() {
        if (StringUtil.isNotEmpty(crmAccountClassName)) {
            DataCheckUtil.checkStringDigit(
                crmAccountClassName,
                20,
                "CRM.CC.crmAccountClassName");
        } else {
            ValidationResults validationResult = new ValidationResults();
            validationResult.add(new ValidationResult(MessageBuilder.create(
                "E.BIZ.FC.14019").addParameter(
                MessageBuilder.$("CRM.CC.crmAccountClassName")).toMessage()));
            throw new ValidationException(validationResult);
        }
    }

}
