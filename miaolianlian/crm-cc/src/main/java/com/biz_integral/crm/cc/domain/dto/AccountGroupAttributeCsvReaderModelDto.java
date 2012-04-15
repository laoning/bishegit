/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz_integral.crm.cc.domain.types.DealClass;
import com.biz_integral.crm.cc.domain.types.IfUpdateWay;
import com.biz_integral.crm.cc.domain.types.MaintenanceTargetFlag;
import com.biz_integral.crm.cc.domain.util.DataCheckUtil;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;

/**
 * 入力ストリームのドメインです。
 */
public final class AccountGroupAttributeCsvReaderModelDto {

    /** アカウント分類コード */
    public String crmAccountClassCd;
    /** 取引種別 */
    public String dealClass;
    /** 画面メンテ対象 */
    public String maintenanceTargetFlag;
    /** IF更新方法 */
    public String ifUpdateWay;

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
     * 取引情報をチェックします。
     */
    public void checkDealClass() {
        if (StringUtil.isEmpty(dealClass)) {
            ValidationResults validationResult = new ValidationResults();
            validationResult.add(new ValidationResult(MessageBuilder.create(
                "E.BIZ.FC.14019").addParameter(
                MessageBuilder.$("CRM.CC.dealClass")).toMessage()));
            throw new ValidationException(validationResult);

        } else if (DealClass.getEnum(dealClass) == null) {
            ValidationResults validationResult = new ValidationResults();
            validationResult.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00024")
                .addParameter(MessageBuilder.$("CRM.CC.dealClass"))
                .addParameter(dealClass)
                .toMessage()));
            throw new ValidationException(validationResult);
        }

    }

    /**
     * 画面メンテ対象フラグをチェックします。
     */
    public void checkMaintenanceTargetFlag() {
        if (!StringUtil.isEmpty(maintenanceTargetFlag)
            && MaintenanceTargetFlag.getEnum(maintenanceTargetFlag) == null) {
            ValidationResults validationResult = new ValidationResults();
            validationResult.add(new ValidationResult(MessageBuilder.create(
                "E.CRM.CC.00024").addParameter(
                MessageBuilder.$("CRM.CC.maintenanceTargetFlag")).addParameter(
                maintenanceTargetFlag).toMessage()));
            throw new ValidationException(validationResult);
        }
    }

    /**
     * IF更新方法をチェックします。
     */
    public void checkIfUpdateWay() {
        if (!StringUtil.isEmpty(ifUpdateWay)
            && IfUpdateWay.getEnum(ifUpdateWay) == null) {
            ValidationResults validationResult = new ValidationResults();
            validationResult.add(new ValidationResult(MessageBuilder.create(
                "E.CRM.CC.00024").addParameter(
                MessageBuilder.$("CRM.CC.ifUpdateWay")).addParameter(
                ifUpdateWay).toMessage()));
            throw new ValidationException(validationResult);
        }
    }
}