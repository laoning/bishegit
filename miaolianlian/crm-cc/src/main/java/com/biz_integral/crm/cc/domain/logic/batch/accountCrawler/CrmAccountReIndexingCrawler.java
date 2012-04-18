/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.accountCrawler;

import com.biz_integral.crm.cc.domain.logic.batch.types.BatchCrawlingType;

/**
 * CRMアカウント差分クローラ
 */
public class CrmAccountReIndexingCrawler extends CrmAccountCrawlerBase {

    /**
     * コンストラクタ
     */
    public CrmAccountReIndexingCrawler() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BatchCrawlingType getCrawlingType() {
        return BatchCrawlingType.REINDEXING;
    }
    // /**
    // * 実行制御<br>
    // * CRMアカウントの差分クローラを実行します。
    // *
    // * @param プロパティ
    // * @throws RuntimeExceptionが発生
    // * @throws Exceptionが発生
    // */
    // public void execute(Properties args) throws RuntimeException, Error {

    // String loginGroupId = (String) args.get("group");

    // ContextContainerLifecycle lifecycle =
    // SingletonS2Container.getComponent(ContextContainerLifecycle.class);
    // // String groupId = (String) args.get("group");
    // ConfiguratableContextBuilder builder =
    // new ConfiguratableContextBuilder();
    // builder.setApplicationID("crm");
    // builder.setCompanyCode("001");
    // builder.setBootType(BootType.BATCH.getValue());
    // builder.setCurrentTime(DateUtil.nowDate());
    // builder.setLoginGroupID("default");
    // builder.setModuleID("cc");
    // // builder.setUseCaseID("account_crawler");
    // // builder.setFeatureID("crawler");
    // builder.setUserID("account");
    // builder.setUserLocale(LocaleUtil.toLocale("ja"));
    // //
    // builder.setUserLocale(LocaleUtil.toLocale(execution.getLocaleID()));
    // lifecycle.create(builder);
    // CrmAccountCrawlingLogic logic =
    // SingletonS2Container.getComponent(CrmAccountCrawlingLogic.class);
    //
    // logic.execute(BatchCrawlingType.REINDEXING);

    // CrawlerLogicImpl mng = new CrawlerLogicImpl();
    // mng.run(loginGroupId, BatchCrawlingType.REINDEXING);
    // }
}
