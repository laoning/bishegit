/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.accountCrawler.impl;

import javax.annotation.Resource;

import jp.co.intra_mart.foundation.solr.crawler.exception.SolrCrawlerException;

import com.biz_integral.crm.cc.application.web.accountCrawler.CrawlerLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountCrawlerLogic;
import com.biz_integral.crm.cc.domain.logic.batch.types.BatchCrawlingType;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.lifecycle.BizContext;

/**
 * クローラロジックの実行クラス。
 */
public class CrawlerLogicImpl implements CrawlerLogic {

    /**
     * コンストラクタ
     */
    public CrawlerLogicImpl() {
    }

    /** コンテキスト */
    @Resource
    public ContextContainer contextContainer;

    /** アカウントクローラロジック */
    @Resource
    public CrmAccountCrawlerLogic crmAccountCrawlerLogic;

    /**
     * {@inheritDoc}
     */
    @BizContext
    public void execute(BatchCrawlingType crawlingType) {

        try {
            crmAccountCrawlerLogic.execute(crawlingType);
        } catch (SolrCrawlerException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
// try {
// startLock(APPLICATION_ID, MODULE_ID, BUSINESS_ID);
//
// // createContext(loginGroupId, APPLICATION_ID, MODULE_ID,
// // BUSINESS_ID);
//
// BizIntegralSolrCrawlerConfig config = new CrmSolrCrawlerConfig();
//
// createContext(loginGroupId, APPLICATION_ID, MODULE_ID, BUSINESS_ID);
//
// // コンテキストの作成
// // ContextContainerLifecycle lifecycle =
// // SingletonS2Container
// // .getComponent(ContextContainerLifecycle.class);
// // ConfiguratableContextBuilder builder =
// // new ConfiguratableContextBuilder();
// // builder.setApplicationID(APPLICATION_ID);
// // builder.setCompanyCode("001");
// // builder.setBootType(BootType.BATCH.getValue());
// // builder.setCurrentTime(DateUtil.nowDate());
// // builder.setLoginGroupID(loginGroupId);
// // builder.setModuleID(MODULE_ID);
// // builder.setUseCaseID("accountManage");
// // builder.setFeatureID("crawler");
// // // builder.setUserLocale(LocaleUtil.toLocale("ja"));
// //
// // builder.setUserLocale(LocaleUtil.toLocale(execution.getLocaleID()));
// // lifecycle.create(builder);
//
// CrmAccountCrawlerLogic logic =
// SingletonS2Container.getComponent(CrmAccountCrawlerLogic.class);
//
// logic.execute(crawlingType, config);
// // new CrmAccountCrawlingServiceImpl(solrMng, context
// // .getUserContext()
// // .getLoginGroupID(), crawlingType, BUSINESS_ID);
//
// // }
// } catch (SolrCrawlerException e) {
// throw new RuntimeException(e.getMessage(), e);
// } catch (Exception e) {
// throw new Error(e.getMessage(), e);
// } finally {
// finallyLock(APPLICATION_ID, MODULE_ID, BUSINESS_ID);
// }
// // }
//
// // ContextBuilder builder = new ContextBuilderImpl();
// // builder.createContextContainerLifecycle("crm","cc","group","001",
// // "account");
// // try {
// // // 会社コード毎に繰り返す
// // for (String companyCd : getCompanyCdList()) {
// //
// // // SolrManagerの初期化
// // BizIntegralSolrManager solrMng =
// // BizIntegralSolrManager.getInstance(
// // BUSINESS_ID,
// // companyCd,
// // context.getUserContext().getLoginGroupID());
// // // サービスの初期化
// // CrmAccountCrawlingServiceImpl service =
// // new CrmAccountCrawlingServiceImpl(solrMng, context
// // .getUserContext()
// // .getLoginGroupID(), crawlingType, BUSINESS_ID);
// //
// // // 実行
// // service.execute();
// // solrMng.commit();
// // solrMng.optimize();
// //
// // }
// // } catch (SearchException e) {
// // throw new SolrCrawlerException("SolrManagerの初期化に失敗", e);
// // }
// //
// }
//
// }
