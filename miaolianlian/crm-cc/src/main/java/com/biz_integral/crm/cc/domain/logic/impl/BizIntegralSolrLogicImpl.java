/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import jp.co.nttdata.intra_mart.solr.exception.SearchException;

import com.biz_integral.crm.cc.domain.logic.BizIntegralSolrLogic;
import com.biz_integral.crm.extension.solr.BizIntegralSolrManager;
import com.biz_integral.crm.extension.solr.util.BizIntegralSolrConfig;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.FeatureStateOperation;
import com.biz_integral.foundation.core.logging.BizLogger;
import com.biz_integral.foundation.core.logging.BizLoggerFactory;

/**
 * BizIntegralSolrLogicロジックの実装です。
 */
public class BizIntegralSolrLogicImpl implements BizIntegralSolrLogic {

    /** BizLogger */
    protected BizLogger log = BizLoggerFactory.getLogger(this.getClass());

    /**
     * {@link FeatureStateOperation}
     */
    @Resource
    protected FeatureStateOperation featureStateOperation;

    /**
     * {@link ContextContainer}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {

        try {
            String loginGroupId =
                contextContainer.getUserContext().getLoginGroupID();

            String userCd = contextContainer.getUserContext().getUserID();

            // BizIntegralSolrManagerのconfigから会社コードの一覧を取得する
            BizIntegralSolrConfig config = new BizIntegralSolrConfig();
            List<String> companyCdList = config.getCompanyList(loginGroupId);

            // 会社コード全てのreloadを行う
            for (String companyCd : companyCdList) {
                try {

                    featureStateOperation.switchTo(companyCd);

                    contextContainer
                        .getApplicationContext()
                        .getFeatureContext()
                        .setCompanyCode(companyCd);

                    BizIntegralSolrManager mng =
                        BizIntegralSolrManager.getInstance(
                            userCd,
                            companyCd,
                            loginGroupId);

                    mng.reload();

                } catch (SearchException e) {
                    e.printStackTrace();
                }
            }

        } catch (SearchException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }
}
