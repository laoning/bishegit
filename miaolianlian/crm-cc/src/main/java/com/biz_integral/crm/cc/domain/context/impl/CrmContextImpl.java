/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.context.impl;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.context.CrmContext;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.FeatureStateOperation;
import com.biz_integral.foundation.core.context.ScopedAttributes;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.foundation.core.util.impl.ImartUniqueIdGenerator;

/**
 * CRMアプリケーション独自で定義するコンテキストです。
 * 
 * @author
 * 
 */
public class CrmContextImpl implements CrmContext {

    /** アプリケーションID */
    public static final String APPLICATION_ID = "crm";

    /** 組織コードのキー */
    public static final String DEPARTMENT_CODE_KEY = "crmDepartmentCode";

    /** 識別ID */
    public static final String IDENTIFIER_KEY = "crmIdentifier";

    /**
     * コンテキストの内容を保持するコンテナです。
     */
    @Resource
    protected ContextContainer container;

    /** featureStateOperation */
    @Resource
    protected FeatureStateOperation featureStateOperation = null;

    /**
     * {@inheritDoc}
     */
    public void setMainDepartmentCd(String departmentCd) {
        getApplicationSessionScope().setAttribute(
            DEPARTMENT_CODE_KEY,
            departmentCd);
    }

    /**
     * {@inheritDoc}
     */
    public String getMainDepartmentCd() {
        Object value =
            getApplicationSessionScope().getAttribute(DEPARTMENT_CODE_KEY);
        if (value != null) {
            return (String) value;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getUserCd() {
        return container.getUserContext().getUserID();
    }

    /**
     * {@inheritDoc}
     */
    public String getLocaleId() {
        return container.getUserContext().getLocaleID();
    }

    /**
     * {@inheritDoc}
     */
    public String getCompanyCd() {
        return container.getCurrentFeatureContext().getCompanyCode();
    }

    /**
     * {@inheritDoc}
     */
    public String getIdentifier() {
        return (String) container
            .getApplicationContext()
            .getSessionScope()
            .get(IDENTIFIER_KEY);
    }

    /**
     * {@inheritDoc}
     */
    public void setIdentifier() {

        if (getIdentifier() != null) {
            return;
        }
        UniqueIdGenerator generator = ImartUniqueIdGenerator.getInstance();
        container.getApplicationContext().getSessionScope().setAttribute(
            IDENTIFIER_KEY,
            generator.create());
    }

    /**
     * {@inheritDoc}
     */
    public void switchSession(String companyCd, String departmentCd) {
        // 会社コードの更新
        featureStateOperation.switchSession(companyCd, APPLICATION_ID);
        featureStateOperation.switchTo(companyCd);

        // 組織コードの更新
        container.getApplicationContext().getSessionScope().setAttribute(
            DEPARTMENT_CODE_KEY,
            departmentCd);
    }

    /**
     * アプリケーション単位に提供される{@link ScopedAttributes}を取得します。
     * 
     * @return {@link ScopedAttributes}
     */
    protected ScopedAttributes getApplicationSessionScope() {
        return container.getApplicationContext().getSessionScope();
    }
}
