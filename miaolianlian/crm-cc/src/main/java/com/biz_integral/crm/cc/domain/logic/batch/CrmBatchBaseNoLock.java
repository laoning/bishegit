/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch;

import java.util.Locale;
import java.util.Properties;

import jp.co.intra_mart.foundation.service.provider.batch.ProcedureComponent;

import org.seasar.framework.container.SingletonS2Container;

import com.biz_integral.foundation.core.context.lifecycle.ContextContainerLifecycle;
import com.biz_integral.foundation.core.logging.BizLogger;
import com.biz_integral.foundation.core.logging.BizLoggerFactory;
import com.biz_integral.foundation.core.logging.BootType;
import com.biz_integral.foundation.core.logging.LogLevel;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.message.MessageSource;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.imart.context.lifecycle.ConfiguratableContextBuilder;

/**
 * バッチの基底(ロックなし)クラスです。<br>
 * ロック処理とコンテキストの作成を行います。<br>
 * 業務の処理内容はrunメソッドをオーバーライドして記述して下さい。
 */
public abstract class CrmBatchBaseNoLock implements ProcedureComponent {

    /** BizLogger */
    protected BizLogger log = BizLoggerFactory.getLogger(this.getClass());

    /**
     * バッチの処理内容を記述します。<br>
     * 主に {applicationId}.{moduleId}.domain.logic.{usecaseId} の処理を呼び出します。
     * 
     * @param args
     *            引数
     */
    protected abstract void run(Properties args);

    /**
     * アプリケーションIDを取得します。<br>
     * コンテキストの作成、ロック処理で利用します。
     * 
     * @return アプリケーションID
     */
    protected abstract String getApplicationId();

    /**
     * モジュールIDを取得します。<br>
     * ロック処理で利用します。
     * 
     * @return モジュールID
     */
    protected abstract String getModuleId();

    /**
     * バッチID（バッチの一意キー）を取得します。<br>
     * ユースケースIDなどで代替されます。<br>
     * ロック処理で利用します。
     * 
     * @return バッチID（バッチの一意キー）
     */
    protected abstract String getBatchId();

    /**
     * バッチを実行します。
     * 
     * @param args
     *            引数
     */
    public void execute(Properties args) {
        // defaultのログイングループを取得
        String loginGroupId = (String) args.get("group");

        try {
            // バッチ処理開始ログ
            log.log(LogLevel.INFO, MessageSource.getMessage(MessageBuilder
                .create("I.CRM.CC.00011")
                .addParameter(this.getClass().getSimpleName())
                .addParameter(getApplicationId())
                .addParameter(getModuleId())
                .addParameter(getBatchId())
                .toMessage(), Locale.getDefault()));

            createContext(loginGroupId, getApplicationId());

            run(args);

        } finally {
            // バッチ処理終了ログ
            log.log(LogLevel.INFO, MessageSource.getMessage(MessageBuilder
                .create("I.CRM.CC.00012")
                .addParameter(this.getClass().getSimpleName())
                .addParameter(getApplicationId())
                .addParameter(getModuleId())
                .addParameter(getBatchId())
                .toMessage(), Locale.getDefault()));
        }
    }

    /**
     * コンテキストを作成します。<br>
     * アプリケーションIDとして{@link CrmBatchBaseNoLock#getApplicationId()}を設定します。<br>
     * BizContextアノテーションのメソッドをもつapplication.logicを呼び出した際、<br>
     * 呼び出しクラスのパッケージでコンテキストの値は更新されます。
     * 
     * @param loginGroupId
     *            ログイングループID
     * @param applicationId
     *            アプリケーションID
     */
    public void createContext(String loginGroupId, String applicationId) {
        // コンテキストの作成
        ContextContainerLifecycle lifecycle =
            SingletonS2Container.getComponent(ContextContainerLifecycle.class);
        ConfiguratableContextBuilder builder =
            new ConfiguratableContextBuilder();
        builder.setBootType(BootType.BATCH.getValue());
        builder.setCurrentTime(DateUtil.nowDate());
        builder.setLoginGroupID(loginGroupId);
        builder.setApplicationID(applicationId);
        lifecycle.create(builder);
    }

}
