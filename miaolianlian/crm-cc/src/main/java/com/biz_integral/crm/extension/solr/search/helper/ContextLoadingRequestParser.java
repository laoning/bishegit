/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.search.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.intra_mart.framework.base.service.ServiceManager;
import jp.co.intra_mart.framework.base.service.ServiceManagerException;
import jp.co.intra_mart.framework.base.service.ServicePropertyException;
import jp.co.intra_mart.framework.system.exception.SystemException;

import com.biz_integral.crm.extension.solr.model.ContextLoadingModel;
import com.biz_integral.service.maskat.service.ContextLoadingServiceController;

/**
 * <p>
 * {@link ContextLoadingServiceController}へ引き渡されたリクエストの内容を解析します。
 * </p>
 * 
 */
public class ContextLoadingRequestParser {

    /**
     * Intramart Service Framework ApplicationID/ServiceIDに含まれるデリミタ
     */
    protected static final String TOKEN_DELIMITER = "_";

    /**
     * 会社コードパラメータ名
     */
    protected static final String FEATURE_KEY = "companyCode";

    /**
     * {@link HttpServletRequest}
     */
    private final HttpServletRequest request;

    /**
     * {@link HttpServletResponse}
     */
    private final HttpServletResponse response;

    /**
     * コンストラクタです。
     * 
     * @param request
     *            {@link HttpServletRequest}
     * @param response
     *            {@link HttpServletResponse}
     */
    public ContextLoadingRequestParser(HttpServletRequest request,
            HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * リクエストパラメータを解析し、{@link ContextLoadingModel}へリクエスト内容を設定します。
     * 
     * @param model
     *            {@link ContextLoadingModel}
     * @throws SystemException
     *             {@code ServiceManager}操作時に例外が発生した場合に発生します。
     */
    public void parse(ContextLoadingModel model) throws SystemException {
        parseIMApplicationID(model);
        parseIMServiceID(model);
    }

    /**
     * <p>
     * Intramart Service Frameworkで利用するServiceIDを解析します。
     * </p>
     * <p>
     * ServiceIDを ユースケースIDとみなします。
     * </p>
     * 
     * @param model
     *            {@link ContextLoadingModel}
     * @throws SystemException
     *             {@code ServiceManager}操作時に例外が発生した場合に発生します。
     */
    private void parseIMServiceID(ContextLoadingModel model)
        throws SystemException {
        model.setUsecaseID(ServiceManager.getServiceManager().getService(
            request,
            response));
    }

    /**
     * <p>
     * Intramart Service Frameworkで利用するApplicationIDを解析します。
     * </p>
     * <p>
     * ApplicationIDを{@link ContextLoadingRequestParser#TOKEN_DELIMITER}で分割し、
     * アプリケーションIDおよびモジュールIDとみなします。
     * </p>
     * 
     * @param model
     *            {@link ContextLoadingModel}
     * @throws ServiceManagerException
     *             サービスマネージャーの取得に失敗した場合に発生します。
     * @throws ServicePropertyException
     *             アプリケーションIDの取得に失敗した場合に発生します。
     */
    private void parseIMApplicationID(ContextLoadingModel model)
        throws ServiceManagerException, ServicePropertyException {
        String imApplicationID =
            ServiceManager
                .getServiceManager()
                .getApplication(request, response);
        String[] token1 = imApplicationID.split(TOKEN_DELIMITER);
        model.setApplicationID(token1[0]);
        model.setModuleID(token1[1]);
    }

}
