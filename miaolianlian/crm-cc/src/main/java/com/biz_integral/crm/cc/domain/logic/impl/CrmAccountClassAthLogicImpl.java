/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAthNames.deleteFlag;
import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAthNames.recordDate;
import static com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAthNames.recordUserCd;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.web.accountBelongClassManage.SearchExportFileResponseModel;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountClassAthDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountClassDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountDao;
import com.biz_integral.crm.cc.domain.dto.AccountClassAthSearchResultDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.dto.CrmCcAccountClassAthSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAth;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassAthNames;
import com.biz_integral.crm.cc.domain.logic.CrmAccountClassAthLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.mapper.CrmAccountClassAthLogicMapper;
import com.biz_integral.crm.cc.domain.util.FileUtil;
import com.biz_integral.foundation.core.configuration.ApplicationConfigurationRegistry;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.io.ColumnType;
import com.biz_integral.foundation.core.io.csv.CSVReader;
import com.biz_integral.foundation.core.io.csv.CSVReaderPreferences;
import com.biz_integral.foundation.core.io.csv.CSVWriter;
import com.biz_integral.foundation.core.io.csv.CSVWriterPreferences;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.persistence.LogicalDelete;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.service.persistence.util.EntityUtil;
import com.biz_integral.service.storage.SharedFile;
import com.biz_integral.service.storage.SharedStorageManager;

/**
 * CrmAccountClassAthLogicロジックの実装です。
 */
public class CrmAccountClassAthLogicImpl implements CrmAccountClassAthLogic {

    /**
     * {@link ContextContainer}
     */
    @Resource
    private ContextContainer contextContainer;

    /**
     * {@link SharedStorageManager}
     */
    @Resource
    private SharedStorageManager sharedStorageManager;

    /**
     * {@link ApplicationConfigurationRegistry}
     */
    @Resource
    protected ApplicationConfigurationRegistry applicationConfigurationRegistry;

    /**
     * アカウント分類所属DAO
     */
    @Resource
    protected CrmCcAccountClassAthDao crmCcAccountClassAthDao;

    /**
     * アカウント分類DAO
     */
    @Resource
    protected CrmCcAccountClassDao crmCcAccountClassDao;

    /**
     * アカウントDAO
     */
    @Resource
    protected CrmCcAccountDao crmCcAccountDao;

    /**
     * {@link ParameterLogic}
     */
    @Resource
    private ParameterLogic parameterLogic;

    /**
     * {@link CrmAccountClassAthLogicMapper}
     */
    @Resource
    private CrmAccountClassAthLogicMapper crmAccountClassAthLogicMapper;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AccountClassAthSearchResultDto> getAccountBelongClassList(
            CrmCcAccountClassAthSearchCriteriaDto dto) {
        return crmCcAccountClassAthDao
            .findByAccountClassAthSearchCriteriaDto(dto);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AccountClassAthSearchResultDto> getAccountBelongClassListForEdit(
            CrmCcAccountClassAthSearchCriteriaDto dto) {
        return crmCcAccountClassAthDao
            .findByAccountClassAthSearchCriteriaDtoForEdit(dto);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void create(List<CrmCcAccountClassAth> entityList) {

        // アカウント選択チェックを行います。
        validateHasAccountSelected(entityList);

        // 入力値をチェックします。
        validateCheck(entityList);

        // アカウント分類所属を登録する
        for (CrmCcAccountClassAth entity : entityList) {
            CrmCcAccountClassAth target =
                crmCcAccountClassAthDao.getEntity(entity);
            if (target == null) {
                crmCcAccountClassAthDao.insert(entity);
            } else {
                if (target.isDeleteFlag()) {
                    // 論理削除を取り消す
                    target.setDeleteFlag(false);
                    crmCcAccountClassAthDao.updateIncludes(
                        target,
                        deleteFlag(),
                        recordUserCd(),
                        recordDate());
                }
            }
        }
    }

    /**
     * 登録前入力値チェック
     * 
     * @param entityList
     *            アカウント分類所属リスト
     */
    private void validateCheck(List<CrmCcAccountClassAth> entityList) {
        ValidationResults validationResults = new ValidationResults();
        for (CrmCcAccountClassAth entity : entityList) {
            // アカウント分類所属の重複チェック
            CrmCcAccountClassAth result =
                crmCcAccountClassAthDao.getByPkNoException(entity
                    .getCompanyCd(), entity.getCrmAccountClassCd(), entity
                    .getCrmAccountCd());
            if (result != null && !result.isDeleteFlag()) {
                validationResults.add(new ValidationResult(MessageBuilder
                    .create("E.CRM.CC.00102")
                    .addParameter(MessageBuilder.$("CRM.CC.crmAccountClassCd"))
                    .addParameter(entity.getCrmAccountClassCd())
                    .addParameter(MessageBuilder.$("CRM.CC.crmAccountCd"))
                    .addParameter(entity.getCrmAccountCd())
                    .toMessage()));
            }
        }
        if (0 < validationResults.size()) {
            throw new ValidationException(validationResults);
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void delete(List<CrmCcAccountClassAth> entityList) {

        // アカウント選択チェックを行います。
        validateHasAccountSelected(entityList);

        for (CrmCcAccountClassAth entity : entityList) {

            // 削除対象を取得します。
            CrmCcAccountClassAth deleteTarget =
                crmCcAccountClassAthDao.getByPkNoException(entity
                    .getCompanyCd(), entity.getCrmAccountClassCd(), entity
                    .getCrmAccountCd());
            if (deleteTarget != null) {
                deleteTarget.setDeleteFlag(true);

                crmCcAccountClassAthDao.updateIncludes(
                    deleteTarget,
                    deleteFlag(),
                    recordUserCd(),
                    recordDate());
            }
        }
    }

    /**
     * アカウント選択チェックを行います。
     * 
     * @param entityList
     *            アカウント一覧
     */
    private void validateHasAccountSelected(
            List<CrmCcAccountClassAth> entityList) {
        ValidationResults validationResults = new ValidationResults();

        if (entityList == null || entityList.size() == 0) {
            validationResults.add(new ValidationResult(MessageBuilder.create(
                "E.CRM.CC.00001").addParameter(
                MessageBuilder.$("CRM.CC.crmAccount")).toMessage()));
            throw new ValidationException(validationResults);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountClassAth> createModel(InputStream inputStream) {

        // 件数チェック
        try {
            if (inputStream == null || inputStream.read() <= 0) {
                throwValidationException("E.CRM.CC.00026");
            }
        } catch (IOException e) {
            throwValidationException("E.CRM.CC.00026");
        }

        CSVReaderPreferences preferences = FileUtil.newCSVReaderPreferences();
        int readerIndex = 0;
        preferences.addMapping(
            readerIndex++,
            "crmAccountClassCd",
            ColumnType.STRING);
        preferences
            .addMapping(readerIndex++, "crmAccountCd", ColumnType.STRING);

        CSVReader<CrmCcAccountClassAthCsvReaderModelDto> reader =
            new CSVReader<CrmCcAccountClassAthCsvReaderModelDto>(
                CrmCcAccountClassAthCsvReaderModelDto.class,
                preferences,
                inputStream);

        List<CrmCcAccountClassAthCsvReaderModelDto> dtlList = null;
        try {
            dtlList = reader.read();
            if (dtlList == null || dtlList.isEmpty()) {
                throwValidationException("E.CRM.CC.00026");
            }
        } catch (Exception e) {
            throwValidationException("E.CRM.CC.00026");
        }

        return crmAccountClassAthLogicMapper.convertDtlList(dtlList);
    }

    /**
     * ValidationException例外を処理します。
     * 
     * @param messageKey
     *            メッセージ
     */
    private void throwValidationException(String messageKey) {
        ValidationResults validationResults = new ValidationResults();
        validationResults.add(new ValidationResult(MessageBuilder.create(
            messageKey).toMessage()));
        throw new ValidationException(validationResults);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isExistModel(List<CrmCcAccountClassAth> entityList) {

        ValidationResults validationResultsNoExist = new ValidationResults();
        ValidationResults validationResults = new ValidationResults();

        for (CrmCcAccountClassAth entity : entityList) {
            // アカウント分類を取得します。
            List<CrmCcAccountClass> crmCcAccountClassList =
                crmCcAccountClassDao.findByPkIgnoreLocale(
                    entity.getCompanyCd(),
                    entity.getCrmAccountClassCd(),
                    LogicalDelete.AVAILABLE);
            if (crmCcAccountClassList == null
                || crmCcAccountClassList.isEmpty()) {
                validationResultsNoExist.add(new ValidationResult(
                    MessageBuilder
                        .create("E.CRM.CC.00024")
                        .addParameter(
                            MessageBuilder.$("CRM.CC.crmAccountClassCd"))
                        .addParameter(entity.getCrmAccountCd())
                        .toMessage()));
            }

            // アカウントを取得します。
            List<CrmCcAccount> crmCcAccountList =
                crmCcAccountDao.findByPkIgnoreLocaleAndTerm(
                    entity.getCompanyCd(),
                    entity.getCrmAccountCd(),
                    LogicalDelete.AVAILABLE);
            if (crmCcAccountList == null || crmCcAccountList.isEmpty()) {
                validationResultsNoExist.add(new ValidationResult(
                    MessageBuilder.create("E.CRM.CC.00024").addParameter(
                        MessageBuilder.$("CRM.CC.crmAccountCd")).addParameter(
                        entity.getCrmAccountCd()).toMessage()));
            }
        }
        if (0 < validationResultsNoExist.size()) {
            // パラメータ.インポート時メッセージ表示件数です。
            int msgCount = (Integer) parameterLogic.getEntity("CRMCC0003");
            if (validationResultsNoExist.size() < msgCount) {
                msgCount = validationResultsNoExist.size();
            }

            validationResults.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00035")
                .addParameter(validationResultsNoExist.size())
                .toMessage()));
            for (int i = 0; i < msgCount; i++) {
                validationResults.add(validationResultsNoExist
                    .getResultList()
                    .get(i));
            }
        }
        if (0 < validationResults.size()) {
            throw new ValidationException(validationResults);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void replace(String companyCd, List<CrmCcAccountClassAth> targetList) {

        List<String> accountClassCdList = new ArrayList<String>();
        // targetListからアカウント分類コードの一覧を作成する
        for (CrmCcAccountClassAth target : targetList) {
            if (accountClassCdList.indexOf(target.getCrmAccountClassCd()) == -1) {
                accountClassCdList.add(target.getCrmAccountClassCd());
            }
        }

        for (String crmAccountClassCd : accountClassCdList) {
            // アカウント分類所属を一覧検索します。
            List<CrmCcAccountClassAth> entityList =
                crmCcAccountClassAthDao.findByCompanyCdAndCrmAccountClassCd(
                    companyCd,
                    crmAccountClassCd,
                    LogicalDelete.NO_RESTRICTION);

            boolean isTargetExist = false;
            for (CrmCcAccountClassAth target : targetList) {
                if (crmAccountClassCd.equals(target.getCrmAccountClassCd())) {
                    isTargetExist = false;
                    for (CrmCcAccountClassAth entity : entityList) {
                        if (EntityUtil.isTheSamePK(target, entity)) {
                            if (entity.isDeleteFlag()) {
                                // アカウント分類所属の論理削除を取り消す。
                                entity.setDeleteFlag(false);
                                crmCcAccountClassAthDao.updateIncludes(
                                    entity,
                                    CrmCcAccountClassAthNames.deleteFlag(),
                                    CrmCcAccountClassAthNames.recordUserCd(),
                                    CrmCcAccountClassAthNames.recordDate());
                            }
                            isTargetExist = true;
                            break;
                        }
                    }
                    if (!isTargetExist) {
                        // アカウント分類所属を追加する。
                        crmCcAccountClassAthDao.insert(target);
                    }
                }
            }

            for (CrmCcAccountClassAth entity : entityList) {
                if (crmAccountClassCd.equals(entity.getCrmAccountClassCd())) {
                    isTargetExist = false;
                    for (CrmCcAccountClassAth target : targetList) {
                        if (EntityUtil.isTheSamePK(target, entity)) {
                            isTargetExist = true;
                            break;
                        }
                    }
                    if (!isTargetExist && !entity.isDeleteFlag()) {
                        // アカウント分類所属の論理削除する。
                        entity.setDeleteFlag(true);
                        crmCcAccountClassAthDao.updateIncludes(
                            entity,
                            CrmCcAccountClassAthNames.deleteFlag(),
                            CrmCcAccountClassAthNames.recordUserCd(),
                            CrmCcAccountClassAthNames.recordDate());
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchExportFileResponseModel createFile(
            CrmCcAccountClassAthSearchCriteriaDto criteriaDto) {

        SearchExportFileResponseModel model =
            new SearchExportFileResponseModel();

        PagingResult<AccountClassAthSearchResultDto> dataList =
            crmCcAccountClassAthDao
                .findByAccountClassAthSearchCriteriaDto(criteriaDto);

        if (dataList != null && (0 < dataList.size())) {

            // ファイルに出力します。
            SharedFile sharedFile =
                FileUtil.createSharedFile(
                    criteriaDto.fileName,
                    UniqueIdGenerator.getInstance().create(),
                    contextContainer);

            CSVWriter<AccountClassAthSearchResultDto> writer =
                new CSVWriter<AccountClassAthSearchResultDto>(
                    makeCSVWriterPreferences(sharedFile));
            writer.write(dataList);

            // 作成したファイルのダウンロードに必要な情報を共有ストレージサービスを使用して作成します。
            SharedFile downLoadSharedFile =
                sharedStorageManager.register(sharedFile, writer
                    .getOutputAsFile());

            writer.close();

            // ファイル作成処理結果モデルを設定します。
            model.fileId = FileUtil.createKey(downLoadSharedFile);
            model.fileName = criteriaDto.fileName;
        }
        return model;
    }

    /**
     * 設定された値をCSVファイルに出力する
     * 
     * @param sharedFile
     *            ファイル
     * @return CSVファイル
     */
    private CSVWriterPreferences makeCSVWriterPreferences(SharedFile sharedFile) {
        CSVWriterPreferences preferences =
            FileUtil.newCSVWriterPreferences(
                applicationConfigurationRegistry,
                sharedFile,
                true);

        String[] headerNames =
            {
                "CRM.CC.crmAccountClassCd",
                "CRM.CC.crmAccountClassName",
                "CRM.CC.crmAccountCd",
                "CRM.CC.crmAccountName" };
        FileUtil.putHeaderNamesMap(headerNames, preferences);

        int dataIndex = 0;
        preferences.addMapping(dataIndex++, "crmAccountClassCd");
        preferences.addMapping(dataIndex++, "crmAccountClassName");
        preferences.addMapping(dataIndex++, "crmAccountCd");
        preferences.addMapping(dataIndex++, "crmAccountName");

        return preferences;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long deleteByCrmCcAccountClass(
            CrmCcAccountClass... crmCcAccountClass) {
        long count = 0;
        for (CrmCcAccountClass entity : crmCcAccountClass) {
            List<CrmCcAccountClassAth> deleteList =
                crmCcAccountClassAthDao.findByCrmCcAccountClass(entity
                    .getCompanyCd(), entity.getCrmAccountClassCd());
            for (CrmCcAccountClassAth delete : deleteList) {
                delete(delete);
                count++;
            }
        }
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(CrmCcAccountClassAth crmCcAccountClassAth) {
        crmCcAccountClassAth.setDeleteFlag(true);
        crmCcAccountClassAthDao.updateIncludes(
            crmCcAccountClassAth,
            CrmCcAccountClassAthNames.deleteFlag(),
            CrmCcAccountClassAthNames.recordDate(),
            CrmCcAccountClassAthNames.recordUserCd());
    }

}
