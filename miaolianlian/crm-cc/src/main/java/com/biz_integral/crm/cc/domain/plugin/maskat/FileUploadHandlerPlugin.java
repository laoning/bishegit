/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.plugin.maskat;

import java.io.File;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.foundation.core.context.FeatureContext;
import com.biz_integral.foundation.core.context.lifecycle.BizContext;
import com.biz_integral.foundation.core.exception.IORuntimeException;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.service.maskat.exception.IllegalRequestException;
import com.biz_integral.service.maskat.logic.AbstractFileUploadHandler;
import com.biz_integral.service.maskat.model.FileUploadHandlerModel;

/**
 * ファイルアップロードハンドラ.
 */
public class FileUploadHandlerPlugin extends AbstractFileUploadHandler {

    /**
     * 共有ストレージ上の論理パスのテンプレート.
     * 
     * <p>
     * フォーマットは次の通り.<br>
     * ・"biz-integral"/[アプリケーションID]/[会社コード]/"upload"/[モジュールID]/[ユースケースID]/[
     * アップロードコンテキストID]-[論理ファイル名]
     * </p>
     */
    protected static final String STORAGE_PATH_TEMPLATE =
        "%1$s/%2$s/%3$s/%4$s/%5$s/%6$s/%7$s-%8$s";

    /**
     * UPLOAD_FILE
     */
    protected static final String FILE_USAGE_UPLOAD = "UPLOAD_FILE";

    /**
     * {@link ParameterLogic}
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * {@inheritDoc}
     * 
     * <p>
     * 引数modelのファイル用途には、固定値「UPLOAD_FILE」を設定します。 また、保存日数には固定値「7」を設定します。
     * </p>
     * 
     * @param model
     *            {@link FileUploadHandlerModel}のインスタンス。次の条件を満たすこと。
     *            <ul>
     *            <li>引数modelが{@code null}でない
     *            <li>引数modelのファイル管理IDが{@code null}
     *            <li>引数modelのアップロード処理結果IDが{@code null}
     *            <li>引数modelの一時保存ファイルのパスが{@code null}でない、かつ空文字でない
     *            <li>引数modelの会社コードが{@code null}でない、かつ空文字でない
     *            <li>引数modelのファイルアップロードコンテキストIDが{@code null}でない、かつ空文字でない
     *            <li>引数modelの論理ファイル名が{@code null}でない、かつ空文字でない
     *            <li>引数modelのユーザIDが{@code null}でない、かつ空文字でない
     *            </ul>
     * @return ファイルアップロードハンドラモデル
     * @throws IORuntimeException
     *             引数modelの{@link FileUploadHandlerModel#getTemporaryPath()}
     *             のファイルが存在しない場合
     */
    @BizContext
    @Override
    public FileUploadHandlerModel execute(FileUploadHandlerModel model) {

        this.validateUploadFile(super.getUploadFile(model));
        super.register(model);
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFileUsage(FileUploadHandlerModel model) {
        return FILE_USAGE_UPLOAD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getStoragePath(FileUploadHandlerModel model) {

        FeatureContext fc =
            contextContainer.getApplicationContext().getFeatureContext();

        return String.format(
            STORAGE_PATH_TEMPLATE,
            "biz-integral",
            fc.getApplicationID(),
            fc.getCompanyCode(),
            "upload",
            fc.getModuleID(),
            fc.getUseCaseID(),
            model.getFileUploadContextId(),
            model.getLogicalFileName());
    }

    /**
     * ファイルの有効性を認証する。
     * 
     * @param file
     *            ファイル
     */
    protected void validateUploadFile(File file) {
        if (!file.exists() || file.isDirectory()) {
            throw new RuntimeException("file is not avaiable! file="
                + file.getAbsolutePath());
        }
        if (!isValidFileSize(file)) {
            throw new IllegalRequestException(MessageBuilder.create(
                "E.CRM.CC.00080").toMessage());
        }
    }

    /**
     * ファイルのサイズを認証する。
     * 
     * @param file
     *            ファイル
     * @return true(サイズ有効)/false(サイズ無効)
     */
    protected boolean isValidFileSize(File file) {
        long fileSize =
            Long.valueOf(parameterLogic.getEntity("CRMCC0012").toString());
        long byteLength = file.length();
        return !((1 * 1024 * fileSize) <= byteLength);
    }
}
