/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.web.accountClassManage.SearchExportFileResponseModel;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountClassDao;
import com.biz_integral.crm.cc.domain.dto.AccountClassCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountClassSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClassNames;
import com.biz_integral.crm.cc.domain.logic.CrmAccountAttributeLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountClassAthLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountClassLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountGroupAttributeLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountGroupClassAthLogic;
import com.biz_integral.crm.cc.domain.mapper.CrmAccountClassLogicMapper;
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
 * CrmAccountClassLogicロジックの実装です。
 */
public class CrmAccountClassLogicImpl implements CrmAccountClassLogic {

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
     * アカウント分類に関するマッパー
     */
    @Resource
    protected CrmAccountClassLogicMapper crmAccountClassLogicMapper;

    /**
     * アカウント分類に関するDAO
     */
    @Resource
    protected CrmCcAccountClassDao crmCcAccountClassDao;

    /**
     * アカウント分類所属に関するロジック
     */
    @Resource
    protected CrmAccountClassAthLogic crmAccountClassAthLogic;

    /**
     * アカウントグループ分類所属に関するロジック
     */
    @Resource
    protected CrmAccountGroupClassAthLogic crmAccountGroupClassAthLogic;

    /**
     * アカウント属性に関するロジック
     */
    @Resource
    protected CrmAccountAttributeLogic crmAccountAttributeLogic;

    /**
     * アカウントグループ属性に関するロジック
     */
    @Resource
    protected CrmAccountGroupAttributeLogic crmAccountGroupAttributeLogic;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AccountClassSearchResultDto> getAccountClassList(
            AccountClassSearchCriteriaDto dto) {
        return crmCcAccountClassDao.findByAccountClassSearchCriteriaDto(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountClass getByPk(CrmCcAccountClass entity) {
        return crmCcAccountClassDao.getByPk(
            entity.getCompanyCd(),
            entity.getCrmAccountClassCd(),
            entity.getLocaleId(),
            LogicalDelete.AVAILABLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountClass getByPkNoException(CrmCcAccountClass entity) {
        return crmCcAccountClassDao.getByPkNoException(
            entity.getCompanyCd(),
            entity.getCrmAccountClassCd(),
            entity.getLocaleId(),
            LogicalDelete.AVAILABLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(CrmCcAccountClass entity) {
        CrmCcAccountClass result =
            crmCcAccountClassDao.getByPkNoException(
                entity.getCompanyCd(),
                entity.getCrmAccountClassCd(),
                entity.getLocaleId(),
                LogicalDelete.NO_RESTRICTION);
        if (result == null || result.getCrmAccountClassCd() == null) {
            crmCcAccountClassDao.insert(entity);

        } else if (result.isDeleteFlag()) {
            entity.setVersionNo(result.getVersionNo());
            crmCcAccountClassDao.updateIncludes(
                entity,
                CrmCcAccountClassNames.crmAccountClassName(),
                CrmCcAccountClassNames.deleteFlag(),
                CrmCcAccountClassNames.recordDate(),
                CrmCcAccountClassNames.recordUserCd());
        } else {
            ValidationResults vr = new ValidationResults();
            vr.add(new ValidationResult(MessageBuilder
                .create("E.CRM.CC.00125")
                .toMessage()));
            throw new ValidationException(vr);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteConfirm(CrmCcAccountClass entity) {
        return crmCcAccountClassDao.getCountRelationTable(entity) > 0L;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(CrmCcAccountClass entity) {
        entity.setDeleteFlag(true);
        crmCcAccountClassDao.updateIncludes(
            entity,
            CrmCcAccountClassNames.deleteFlag(),
            CrmCcAccountClassNames.recordDate(),
            CrmCcAccountClassNames.recordUserCd());

        // 関連テーブルを論理削除
        // アカウント属性
        crmAccountAttributeLogic.deleteByCrmCcAccountClass(entity);
        // アカウント分類所属
        crmAccountClassAthLogic.deleteByCrmCcAccountClass(entity);
        // アカウントグループ属性
        crmAccountGroupAttributeLogic.deleteByCrmCcAccountClass(entity);
        // アカウントグループ分類所属
        crmAccountGroupClassAthLogic.deleteByCrmCcAccountClass(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(CrmCcAccountClass entity) {
        crmCcAccountClassDao.updateIncludes(
            entity,
            CrmCcAccountClassNames.crmAccountClassName(),
            CrmCcAccountClassNames.deleteFlag(),
            CrmCcAccountClassNames.recordDate(),
            CrmCcAccountClassNames.recordUserCd());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountClass> createModel(String companyCd,
            String localeId, InputStream inputStream) {

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
        preferences.addMapping(
            readerIndex++,
            "crmAccountClassName",
            ColumnType.STRING);

        CSVReader<AccountClassCsvReaderModelDto> reader =
            new CSVReader<AccountClassCsvReaderModelDto>(
                AccountClassCsvReaderModelDto.class,
                preferences,
                inputStream);

        List<AccountClassCsvReaderModelDto> dtoList = null;
        try {
            dtoList = reader.read();
            if (dtoList == null || dtoList.isEmpty()) {
                throwValidationException("E.CRM.CC.00026");
            }
        } catch (Exception e) {
            throwValidationException("E.CRM.CC.00026");
        }

        return crmAccountClassLogicMapper.convertDtoList(
            companyCd,
            localeId,
            dtoList);
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
    public void replace(String companyCd, String localeId,
            List<CrmCcAccountClass> targetList) {

        // アカウント分類を一覧検索します。
        List<CrmCcAccountClass> entityList =
            crmCcAccountClassDao.findByCompanyCdAndLocaleId(
                companyCd,
                localeId,
                LogicalDelete.NO_RESTRICTION);

        boolean isExist = false;
        for (CrmCcAccountClass target : targetList) {
            isExist = false;
            for (CrmCcAccountClass entity : entityList) {
                if (EntityUtil.isTheSamePK(target, entity)) {
                    target.setVersionNo(entity.getVersionNo());
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                crmCcAccountClassDao.update(target);
            } else {
                crmCcAccountClassDao.insert(target);
            }
        }

        for (CrmCcAccountClass entity : entityList) {
            isExist = false;
            for (CrmCcAccountClass target : targetList) {
                if (EntityUtil.isTheSamePK(target, entity)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist && !entity.isDeleteFlag()) {
                delete(entity);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchExportFileResponseModel createFile(
            AccountClassSearchCriteriaDto criteriaDto) {

        SearchExportFileResponseModel model =
            new SearchExportFileResponseModel();

        PagingResult<AccountClassSearchResultDto> dataList =
            crmCcAccountClassDao
                .findByAccountClassSearchCriteriaDto(criteriaDto);

        if (dataList != null && (0 < dataList.size())) {

            // ファイルに出力します。
            SharedFile sharedFile =
                FileUtil.createSharedFile(
                    criteriaDto.fileName,
                    UniqueIdGenerator.getInstance().create(),
                    contextContainer);

            CSVWriter<AccountClassSearchResultDto> writer =
                new CSVWriter<AccountClassSearchResultDto>(
                    makeCSVWriterPreferences(sharedFile));
            writer.write(dataList);

            // 作成したファイルのダウンロードに必要な情報を
            // 共有ストレージサービスを使用して作成します。
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
            { "CRM.CC.crmAccountClassCd", "CRM.CC.crmAccountClassName" };
        FileUtil.putHeaderNamesMap(headerNames, preferences);

        int dataIndex = 0;
        preferences.addMapping(dataIndex++, "crmAccountClassCd");
        preferences.addMapping(dataIndex++, "crmAccountClassName");

        return preferences;
    }

}
