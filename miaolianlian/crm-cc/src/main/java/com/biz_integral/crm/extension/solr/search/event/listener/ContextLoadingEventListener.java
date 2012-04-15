/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.event.listener;

import static com.biz_integral.foundation.core.util.StringUtil.isEmpty;
import jp.co.intra_mart.framework.base.event.Event;
import jp.co.intra_mart.framework.base.event.EventResult;
import jp.co.intra_mart.framework.base.event.StandardEventListener;
import jp.co.intra_mart.framework.system.exception.ApplicationException;
import jp.co.intra_mart.framework.system.exception.SystemException;

import com.biz_integral.crm.extension.solr.model.ContextLoadingModel;
import com.biz_integral.crm.extension.solr.search.event.ContextLoadingEvent;
import com.biz_integral.crm.extension.solr.search.event.ContextLoadingEventResult;
import com.biz_integral.foundation.core.configuration.ApplicationConfiguration;
import com.biz_integral.foundation.core.configuration.ApplicationConfigurationRegistry;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.FeatureStateManager;
import com.biz_integral.foundation.core.context.UserContext;
import com.biz_integral.foundation.core.message.Message;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.security.UserPrincipal;
import com.biz_integral.service.maskat.exception.IllegalRequestException;

/**
 * Maskatと連動するコンテキストの内容を収集する{@code StandardEventListener}実装です。
 * 
 */
public class ContextLoadingEventListener extends StandardEventListener {

    /**
     * {@link FeatureStateManager}インスタンス
     */
    protected FeatureStateManager featureStateManager = null;

    /**
     * {@link ApplicationConfigurationRegistry}
     */
    private ApplicationConfigurationRegistry registory = null;

    /**
     * {@link ContextContainer}
     */
    private ContextContainer contextContainer = null;

    /**
     * {@inheritDoc}
     */
    @Override
    protected EventResult fire(Event event) throws SystemException,
        ApplicationException {
        ContextLoadingModel model = ((ContextLoadingEvent) event).getModel();
        populateUserContext(model);
        populateFeature(model);
        return new ContextLoadingEventResult(model);
    }

    /**
     * Featureの内容を収集します。
     * 
     * @param model
     *            {@link ContextLoadingModel}
     */
    protected void populateFeature(ContextLoadingModel model) {
        UserPrincipal principal = getCurrentUserContext();
        ApplicationConfiguration configuration =
            this.registory.getConfiguration(model.getApplicationID());

        if (!isEmpty(model.getCompanyCode())) {
            validateCompanyConfiguration(
                model.getCompanyCode(),
                principal,
                configuration);
            return;
        }

        String companyCode =
            this.featureStateManager.getCurrentCompanyCode(principal, model
                .getApplicationID());

        if (isEmpty(companyCode)) {
            Message message =
                MessageBuilder.create("F.BIZ.MASKAT.01005").toMessage();
            throw new IllegalRequestException(message);
        }
        validateCompanyConfiguration(companyCode, principal, configuration);
        model.setCompanyCode(companyCode);
        this.featureStateManager.saveCurrentCompanyCode(
            principal,
            companyCode,
            model.getApplicationID());

    }

    /**
     * 現在のユーザ情報を取得します。
     * 
     * @return ユーザ情報
     */
    private UserContext getCurrentUserContext() {
        return contextContainer.getUserContext();
    }

    /**
     * 指定の会社に対応するアプリケーション設定ファイルがあるか否かを精査します。
     * 
     * @param companyCode
     *            会社コード
     * @param principal
     *            ユーザ
     * @param configuration
     *            該当アプリケーション設定内容
     */
    private void validateCompanyConfiguration(String companyCode,
            UserPrincipal principal, ApplicationConfiguration configuration) {
        if (!configuration.hasLoginGroup(principal.getLoginGroupID())) {
            Message message =
                MessageBuilder.create("F.BIZ.MASKAT.01007").addParameter(
                    principal.getLoginGroupID()).toMessage();
            throw new IllegalRequestException(message);
        }
        if (!configuration
            .getLoginGroup(principal.getLoginGroupID())
            .hasCompany(companyCode)) {
            Message message =
                MessageBuilder.create("F.BIZ.MASKAT.01006").addParameter(
                    companyCode).toMessage();
            throw new IllegalRequestException(message);

        }
        if (!this.featureStateManager.validate(principal, companyCode)) {
            Message message =
                MessageBuilder.create("F.BIZ.MASKAT.01006").addParameter(
                    companyCode).toMessage();
            throw new IllegalRequestException(message);
        }
    }

    /**
     * 現在の利用者の内容を設定します。
     * 
     * @param model
     *            {@link ContextLoadingModel}
     */
    private void populateUserContext(ContextLoadingModel model) {
        UserContext userContext = getCurrentUserContext();
        model.setUserID(userContext.getUserID());
        model.setLoginGroupID(userContext.getLoginGroupID());
        model.setLocaleID(userContext.getLocaleID());
    }

    /**
     * <p>
     * FeatureStateManagerを設定します。
     * </p>
     * 
     * <p>
     * 本アクセサは{@code Seasar2}に利用されます。
     * </p>
     * 
     * @param featureStateManager
     *            FeatureStateManager
     */
    public void setFeatureStateManager(FeatureStateManager featureStateManager) {
        this.featureStateManager = featureStateManager;
    }

    /**
     * {@link ApplicationConfigurationRegistry}を設定します。
     * <p>
     * 本アクセサは{@code Seasar2}に利用されます。
     * </p>
     * 
     * @param registory
     *            {@link ApplicationConfigurationRegistry}
     */
    public void setRegistory(ApplicationConfigurationRegistry registory) {
        this.registory = registory;
    }

    /**
     * {@link ContextContainer}を設定します。
     * <p>
     * 本アクセサは{@code Seasar2}に利用されます。
     * </p>
     * 
     * @param contextContainer
     *            {@link ContextContainer}
     */
    public void setContextContainer(ContextContainer contextContainer) {
        this.contextContainer = contextContainer;
    }

}
