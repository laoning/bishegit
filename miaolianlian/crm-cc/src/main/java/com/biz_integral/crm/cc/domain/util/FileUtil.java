/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.util;

import java.io.File;
import java.util.Date;

import org.seasar.framework.util.UUID;

import com.biz_integral.crm.cc.domain.types.FileKind;
import com.biz_integral.crm.sp.application.controller.common.FileInputFileSelectDialogFileUploadRequestModel;
import com.biz_integral.foundation.core.configuration.ApplicationConfigurationRegistry;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.FeatureContext;
import com.biz_integral.foundation.core.io.csv.CSVReaderPreferences;
import com.biz_integral.foundation.core.io.csv.CSVWriterPreferences;
import com.biz_integral.foundation.core.io.excel.ExcelWriterPreferences;
import com.biz_integral.foundation.core.util.CharacterEncoding;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.service.storage.SharedFile;
import com.biz_integral.service.storage.util.DownloadParameterCrypto;

/**
 * 共通関数（ファイル）クラス（FileUtil）.<br>
 * ファイルに関する共通処理を行う。
 */
public class FileUtil {

    /**
     * 区切り文字
     */
    public static final char CSV_SEPARATOR = ',';

    /**
     * クォート文字文字
     */
    public static final char CSV_QUOTE = '\'';

    /**
     * エスケープ文字
     */
    public static final char CSV_ESCAPE = '\\';

    /**
     * CSVファイル
     */
    public static final String CSV_FILE = ".csv";

    /**
     * EXCELファイル
     */
    public static final String EXCEL_FILE = ".xls";

    /**
     * コンストラクタです。
     */
    private FileUtil() {
    }

    /**
     * 保存パス情報を取得します
     * 
     * @param sharedFile
     *            共有ファイル
     * @param registerNo
     *            登録ID
     * @return 保存パス情報
     */
    public static String getStoragePath(SharedFile sharedFile, String registerNo) {
        StringBuilder storagePath = new StringBuilder();
        storagePath.append("/biz-integral/");
        storagePath.append(sharedFile.getApplicationId());
        storagePath.append("/");
        storagePath.append(sharedFile.getCompanyCd());
        storagePath.append("/upload/");
        storagePath.append(sharedFile.getModuleId());
        storagePath.append("/");
        storagePath.append(registerNo);
        storagePath.append("-");
        storagePath.append(sharedFile.getLogicalFileName());
        return storagePath.toString();
    }

    /**
     * {@link SharedFile}を生成します。
     * 
     * @param fileName
     *            ファイル名
     * @param registerNo
     *            登録ID
     * @param context
     *            コンテキス
     * @return {@link SharedFile}
     */
    public static SharedFile createSharedFile(String fileName,
            String registerNo, ContextContainer context) {
        SharedFile sharedFile = new SharedFile();
        sharedFile.setAfterDeleteFlag(false);
        FeatureContext featureContex = context.getFeatureContext();
        sharedFile.setApplicationId(featureContex.getApplicationID());
        sharedFile.setCompanyCd(context
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        sharedFile.setModuleId(featureContex.getModuleID());
        sharedFile.setLogicalFileName(fileName);
        sharedFile.setStoragePath(getStoragePath(sharedFile, registerNo));
        sharedFile.setFileUsage("default");
        sharedFile.setRecordUserCd(context.getUserContext().getUserID());
        sharedFile.setCreateUserCd(context.getUserContext().getUserID());

        Date expiredDate =
            DateUtil.getCalculator(DateUtil.nowDate()).addDay(7).asDate();
        sharedFile.setExpiredDate(expiredDate);
        sharedFile.setDeleteExpiredDate(expiredDate);

        return sharedFile;
    }

    /**
     * {@link SharedFile}を生成します。
     * 
     * @param model
     *            {@link FileInputFileSelectDialogFileUploadRequestModel}
     * @param contextContainer
     *            コンテキス
     * @return {@link SharedFile}
     */
    public static SharedFile createSharedFile(
            FileInputFileSelectDialogFileUploadRequestModel model,
            ContextContainer contextContainer) {

        String logicalFileName =
            model.temporaryPath.substring(model.temporaryPath
                .lastIndexOf(File.separator) + 1);

        SharedFile sharedFile = new SharedFile();
        sharedFile.setAfterDeleteFlag(false);
        sharedFile.setApplicationId(contextContainer
            .getFeatureContext()
            .getApplicationID());
        sharedFile.setCompanyCd(contextContainer
            .getApplicationContext()
            .getFeatureContext()
            .getCompanyCode());
        sharedFile.setCreateUserCd(contextContainer
            .getUserContext()
            .getUserID());
        sharedFile.setFileUploadContextId(model.uploadContextId);
        sharedFile.setFileUsage("default");
        sharedFile.setLogicalFileName(logicalFileName);
        sharedFile.setModuleId(contextContainer
            .getFeatureContext()
            .getModuleID());
        sharedFile.setRecordUserCd(contextContainer
            .getUserContext()
            .getUserID());
        sharedFile.setStoragePath(createPath(sharedFile));
        return sharedFile;
    }

    /**
     * ファイルのパス情報を生成します。
     * 
     * @param sharedFile
     *            共有ストレージファイル
     * @return String パス
     */
    private static String createPath(SharedFile sharedFile) {
        StringBuilder storagePath = new StringBuilder();
        storagePath.append("/biz-integral/");
        storagePath.append(sharedFile.getApplicationId());
        storagePath.append("/");
        storagePath.append(sharedFile.getCompanyCd());
        storagePath.append("/upload/");
        storagePath.append(sharedFile.getModuleId());
        storagePath.append("/");
        storagePath.append(sharedFile.getFileUploadContextId());
        storagePath.append("-");
        storagePath.append(sharedFile.getLogicalFileName());
        return storagePath.toString();
    }

    /**
     * 暗号化パラメータします。
     * 
     * @param file
     *            ファイル
     * @return 暗号化ファイル
     */
    public static String createKey(SharedFile file) {
        return DownloadParameterCrypto.create(file);
    }

    /**
     * CSV読み込み時の設定を保持するクラスを生成します。
     * 
     * @param applicationConfigurationRegistry
     *            {@link ApplicationConfigurationRegistry}
     * @param isOutPutHeader
     *            Header生成します
     * @param sharedFile
     *            共有ファイル
     * @return CSV読み込み時の設定を保持するクラス
     */
    public static CSVWriterPreferences newCSVWriterPreferences(
            ApplicationConfigurationRegistry applicationConfigurationRegistry,
            SharedFile sharedFile, boolean isOutPutHeader) {
        return new CSVWriterPreferences(
            CSV_SEPARATOR,
            CSV_QUOTE,
            CSV_ESCAPE,
            CharacterEncoding.MS932,
            createTemporaryFileName(
                sharedFile,
                applicationConfigurationRegistry),
            CSVWriterPreferences.LF,
            isOutPutHeader);
    }

    /**
     * CSV読み込み時の設定を保持するクラスを生成します。
     * 
     * @param applicationConfigurationRegistry
     *            {@link ApplicationConfigurationRegistry}
     * @param isOutPutHeader
     *            Header生成します
     * @param sharedFile
     *            共有ファイル
     * @return CSV読み込み時の設定を保持するクラス
     */
    public static ExcelWriterPreferences newExcelWriterPreferences(
            ApplicationConfigurationRegistry applicationConfigurationRegistry,
            SharedFile sharedFile) {
        return new ExcelWriterPreferences(createTemporaryFileName(
            sharedFile,
            applicationConfigurationRegistry));
    }

    /**
     * 一時ファイル名を作成します。
     * 
     * @param sharedFile
     *            共有ファイル
     * @param applicationConfigurationRegistry
     *            {@link ApplicationConfigurationRegistry}
     * @return 一時ファイル名
     */
    private static String createTemporaryFileName(SharedFile sharedFile,
            ApplicationConfigurationRegistry applicationConfigurationRegistry) {
        String tempDir =
            applicationConfigurationRegistry.getConfiguration(
                sharedFile.getApplicationId()).getTemporaryDir();
        StringBuilder sb = new StringBuilder();
        sb.append(tempDir);
        sb.append("/");
        sb.append(UUID.create());
        sb.append("_").append(sharedFile.getLogicalFileName());
        return sb.toString();
    }

    /**
     * ヘッダ出力します。
     * 
     * @param headerNames
     *            ヘッダ名
     * @param preferences
     *            CSV読み込み時の設定を保持するクラス
     */
    public static void putHeaderNamesMap(String[] headerNames,
            CSVWriterPreferences preferences) {
        int headerNamesIndex = 0;
        for (String headerName : headerNames) {
            preferences.putHeaderNamesMap(headerNamesIndex++, headerName);
        }
    }

    /**
     * ヘッダ出力します。
     * 
     * @param headerNames
     *            ヘッダ名
     * @param preferences
     *            Excel読み込み時の設定を保持するクラス
     */
    public static void putHeaderNamesMapForExcel(String[] headerNames,
            ExcelWriterPreferences preferences) {
        int headerNamesIndex = 0;
        for (String headerName : headerNames) {
            preferences.putHeaderNamesMap(headerNamesIndex++, headerName);
        }
    }

    /**
     * CSV読み込み時の設定を保持するクラスを生成します。
     * 
     * @return CSV読み込み時の設定を保持するクラス
     */
    public static CSVReaderPreferences newCSVReaderPreferences() {
        return new CSVReaderPreferences(
            CSV_SEPARATOR,
            CSV_QUOTE,
            CSV_ESCAPE,
            CharacterEncoding.UTF8,
            1);
    }

    /**
     * 指定されたファイル種別に対応した拡張子を付加したファイル名を生成します。
     * 
     * @param fileName
     *            ファイル名
     * @param fileKind
     *            ファイル種別
     * @return 生成したファイル名
     */
    public static String addFileExtension(String fileName, String fileKind) {

        if (StringUtil.isEmpty(fileName)) {
            return fileName;
        }

        if (FileKind.CSV.equals(FileKind.getEnum(fileKind))) {
            if (!CSV_FILE.equals(fileName.substring(fileName.length()
                - CSV_FILE.length()))) {
                fileName = fileName.concat(CSV_FILE);
            }
        } else if (FileKind.EXCEL.equals(FileKind.getEnum(fileKind))) {
            if (!EXCEL_FILE.equals(fileName.substring(fileName.length()
                - EXCEL_FILE.length()))) {
                fileName = fileName.concat(EXCEL_FILE);
            }
        }

        return fileName;
    }

}
