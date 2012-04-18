/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import javax.annotation.Resource;

import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;
import jp.co.intra_mart.foundation.security.license.LicenseManager;

import com.biz_integral.crm.cc.domain.dao.CrmCcParameterDao;
import com.biz_integral.crm.cc.domain.entity.CrmCcParameter;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.UseType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.UserContext;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.persistence.LogicalDelete;

/**
 * パラメータのロジックの実装です。
 */
public class ParameterLogicImpl implements ParameterLogic {
    private static final String SFA_APPLICATION_ID = "ZT";

    public ParameterLogicImpl() {
        super();
    }

    /**
     * パラメータに関するDAO
     */
    @Resource
    protected CrmCcParameterDao crmCcParameterDao;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getEntity(String parameterCd) throws IllegalArgumentException {

        LicenseManager licenseManagerObject;
        UserContext ucx;
        String loginGpId;
        String userId;

        ucx = contextContainer.getUserContext();
        loginGpId = ucx.getLoginGroupID();
        userId = ucx.getUserID();
        if (userId != null && userId.compareTo("crm_sfa_batch_user") != 0) {
            try {
                licenseManagerObject = new LicenseManager(loginGpId);

                if (!licenseManagerObject
                    .isRegisteredApplicationLicenseToAccount(
                        userId,
                        SFA_APPLICATION_ID)) {
                    // System.out.println("CRM/SFA Application License Error:user_id="
                    // + userId);
                    // ValidationResults results = new ValidationResults();
                    // results.add(new ValidationResult(MessageBuilder.create(
                    // "You have no license!").toMessage()));
                    // throw new ValidationException(results);
                }
            } catch (AccessSecurityException ase) {
                System.err.println(ase);
                ValidationResults results = new ValidationResults();
                results.add(new ValidationResult(MessageBuilder.create(
                    ase.toString()).toMessage()));
                throw new ValidationException(results);
            }
        }

        CrmCcParameter parameter =
            crmCcParameterDao.getValidEntityNoException(
                contextContainer
                    .getApplicationContext()
                    .getFeatureContext()
                    .getCompanyCode(),
                parameterCd,
                DateUtil.nowDate(),
                LogicalDelete.AVAILABLE);

        return castParameterValue(parameter);
    }

    /**
     * 
     * パラメータ値を指定された型に変換して返します。
     * 
     * @param parameter
     *            パラメータエンティティ
     * @return 型変換後の値
     * @throws IllegalArgumentException
     *             次の場合。
     *             <ul>
     *             <li>指定された型クラスが見つからなかった</li>
     *             </ul>
     */
    private Object castParameterValue(CrmCcParameter parameter)
        throws IllegalArgumentException {

        Object result = null;

        if (parameter == null) {
            return null;
        }

        if (StringUtil.isEmpty(parameter.getTypeId())) {
            // 区分IDが設定されていない場合
            String dataType = parameter.getParameterDataType();
            if (StringUtil.equals("1", dataType)) {
                result = Integer.valueOf(parameter.getParameterValue());
            } else if (StringUtil.equals("2", dataType)
                || StringUtil.equals("3", dataType)
                || StringUtil.equals("4", dataType)) {

                result = parameter.getParameterValue();
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            // 区分IDが設定されている場合
            result = UseType.getEnum(parameter.getParameterValue());
        }

        return result;
    }
}
