/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import jp.co.intra_mart.foundation.service.client.information.Lock;
import jp.co.intra_mart.foundation.service.provider.batch.ProcedureComponent;

import org.seasar.framework.container.SingletonS2Container;

import com.biz_integral.crm.cc.domain.logic.batch.exception.BatchRuntimeException;
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
 * バッチの基底クラスです。<br>
 * ロック処理とコンテキストの作成を行います。<br>
 * 業務の処理内容はrunメソッドをオーバーライドして記述して下さい。
 */
public abstract class CrmBatchBase implements ProcedureComponent {

    /** BizLogger */
    protected BizLogger log = BizLoggerFactory.getLogger(this.getClass());

    /** ロック処理判定用 */
    protected Lock lock;

    /** セパレータ文字列 */
    private static final String SEPARATOR = "_";

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

            startLock(getApplicationId(), getModuleId(), getBatchId());

            createContext(loginGroupId, getApplicationId());

            run(args);

        } finally {
            finallyLock(getApplicationId(), getModuleId(), getBatchId());

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
     * アプリケーションIDとして{@link CrmBatchBase#getApplicationId()}を設定します。<br>
     * ユーザIDとして「crm_sfa_batch_user」を設定します。<br>
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
        builder.setCompanyCode("crm_sfa_batch_company");
        builder.setUserID("crm_sfa_batch_user");
        // builder.setUserID(getBatchId());
        // builder.setCompanyCode("001");
        // builder.setFeatureID("crawler");
        // builder.setUserLocale(LocaleUtil.toLocale("ja"));
        // builder.setUserLocale(LocaleUtil.toLocale(execution.getLocaleID()));
        // builder.setModuleID(moduleId);
        // builder.setUseCaseID("accountManage");
        lifecycle.create(builder);
    }

    /**
     * ロック処理を開始します。<br>
     * 同一アプリケーションID、モジュールID、バッチIDで、<br>
     * バッチを同時に起動できないように抑制します。
     * 
     * @param applicationId
     *            アプリケーションID
     * @param moduleId
     *            モジュールID
     * @param batchId
     *            バッチID
     */
    public void startLock(String applicationId, String moduleId, String batchId) {
        // ロック開始
        lock =
            new Lock(new StringBuilder()
                .append(applicationId)
                .append(SEPARATOR)
                .append(moduleId)
                .append(SEPARATOR)
                .append(batchId)
                .toString());
        try {
            if (!lock.begin(1L)) {
                // ロック処理の開始に失敗
                lock = null;
                throw new BatchRuntimeException(MessageBuilder
                    .create("E.CRM.CC.00086")
                    .addParameter(this.getClass().getSimpleName())
                    .addParameter(getApplicationId())
                    .addParameter(getModuleId())
                    .addParameter(getBatchId())
                    .toMessage());

            }
        } catch (IOException e) {
            // ファイルの作成に失敗したための、ロック処理の開始に失敗
            throw new BatchRuntimeException(e, MessageBuilder
                .create("E.CRM.CC.00086")
                .addParameter(this.getClass().getSimpleName())
                .addParameter(getApplicationId())
                .addParameter(getModuleId())
                .addParameter(getBatchId())
                .toMessage());
        }
    }

    /**
     * ロック処理を終了します。<br>
     * executeメソッドの終了処理で呼出します。
     * 
     * @param applicationId
     *            アプリケーションID
     * @param moduleId
     *            モジュールID
     * @param batchId
     *            バッチID
     */
    public void finallyLock(String applicationId, String moduleId,
            String batchId) {
        if (lock != null && !Lock.releaseCurrentThread().isEmpty()) {
            // ロック処理の終了に失敗
            throw new BatchRuntimeException(MessageBuilder
                .create("E.CRM.CC.00087")
                .addParameter(this.getClass().getSimpleName())
                .addParameter(getApplicationId())
                .addParameter(getModuleId())
                .addParameter(getBatchId())
                .toMessage());
        }
    }
}
