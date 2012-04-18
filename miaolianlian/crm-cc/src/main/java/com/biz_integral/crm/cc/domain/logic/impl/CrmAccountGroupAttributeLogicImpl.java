/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.web.accountGroupAttributeManage.SearchExportFileResponseModel;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountClassDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountGrpAttrDao;
import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountGroupAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttrNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountGrpAttr;
import com.biz_integral.crm.cc.domain.logic.CrmAccountGroupAttributeLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountGroupModuleLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.mapper.CrmAccountGroupAttributeLogicMapper;
import com.biz_integral.crm.cc.domain.util.FileUtil;
import com.biz_integral.foundation.core.configuration.ApplicationConfigurationRegistry;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.io.ColumnType;
import com.biz_integral.foundation.core.io.csv.CSVReader;
import com.biz_integral.foundation.core.io.csv.CSVReaderPreferences;
import com.biz_integral.foundation.core.io.csv.CSVWriter;
import com.biz_integral.foundation.core.io.csv.CSVWriterPreferences;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.StringUtil;
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
 * CrmAccountGroupAttributeLogicロジックの実装です。
 */
public class CrmAccountGroupAttributeLogicImpl implements
        CrmAccountGroupAttributeLogic {

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
     * {@link ParameterLogic}です
     */
    @Resource
    protected ParameterLogic parameterLogic;

    /**
     * アカウントグループ属性に関するマッパー
     */
    @Resource
    protected CrmAccountGroupAttributeLogicMapper crmAccountGroupAttributeLogicMapper;

    /**
     * アカウント分類に関するDAO
     */
    @Resource
    protected CrmCcAccountClassDao crmCcAccountClassDao;

    /**
     * アカウント属性に関するDAO
     */
    @Resource
    protected CrmCcAccountGrpAttrDao crmCcAccountGrpAttrDao;

    /**
     * アカウント属性に関するDAO
     */
    @Resource
    protected CrmAccountGroupModuleLogic crmAccountGroupModuleLogic;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AccountGroupAttributeSearchResultDto> getAccountGroupAttributeList(
            AccountGroupAttributeSearchCriteriaDto dto) {
        return crmAccountGroupAttributeLogicMapper
            .convert(crmCcAccountGrpAttrDao
                .findByAccountGroupAttributeSearchCriteriaDto(dto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountGrpAttr> createModel(String companyCd,
            InputStream inputStream) {

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
        preferences.addMapping(readerIndex++, "dealClass", ColumnType.STRING);
        preferences.addMapping(
            readerIndex++,
            "maintenanceTargetFlag",
            ColumnType.STRING);
        preferences.addMapping(readerIndex++, "ifUpdateWay", ColumnType.STRING);

        CSVReader<AccountGroupAttributeCsvReaderModelDto> reader =
            new CSVReader<AccountGroupAttributeCsvReaderModelDto>(
                AccountGroupAttributeCsvReaderModelDto.class,
                preferences,
                inputStream);

        List<AccountGroupAttributeCsvReaderModelDto> dtoList = null;
        try {
            dtoList = reader.read();
            if (dtoList == null || dtoList.isEmpty()) {
                throwValidationException("E.CRM.CC.00026");
            }
        } catch (Exception e) {
            throwValidationException("E.CRM.CC.00026");
        }

        return crmAccountGroupAttributeLogicMapper.convertDtoList(
            companyCd,
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
    public void isExistModel(String companyCd, String localeId,
            List<CrmCcAccountGrpAttr> entityList) {

        ValidationResults validationResultsNoExist = new ValidationResults();
        ValidationResults validationResults = new ValidationResults();

        for (CrmCcAccountGrpAttr entity : entityList) {
            // アカウント分類を取得します。
            CrmCcAccountClass crmCcAccountClass =
                crmCcAccountClassDao.getByPkNoException(companyCd, entity
                    .getCrmAccountClassCd(), localeId, LogicalDelete.AVAILABLE);
            if (crmCcAccountClass == null
                || StringUtil.isEmpty(crmCcAccountClass.getCrmAccountClassCd())) {
                validationResultsNoExist.add(new ValidationResult(
                    MessageBuilder
                        .create("E.CRM.CC.00024")
                        .addParameter(
                            MessageBuilder.$("CRM.CC.crmAccountClassCd"))
                        .addParameter(entity.getCrmAccountClassCd())
                        .toMessage()));
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
    public void replace(String companyCd, List<CrmCcAccountGrpAttr> targetList) {

        // アカウント属性を一覧検索します。
        List<CrmCcAccountGrpAttr> entityList =
            crmCcAccountGrpAttrDao.findByCompanyCd(
                companyCd,
                LogicalDelete.NO_RESTRICTION);

        boolean isExist = false;
        for (CrmCcAccountGrpAttr target : targetList) {
            isExist = false;
            for (CrmCcAccountGrpAttr entity : entityList) {
                if (EntityUtil.isTheSamePK(target, entity)) {
                    target.setVersionNo(entity.getVersionNo());
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                crmCcAccountGrpAttrDao.update(target);
            } else {
                crmCcAccountGrpAttrDao.insert(target);
            }
        }

        for (CrmCcAccountGrpAttr entity : entityList) {
            isExist = false;
            for (CrmCcAccountGrpAttr target : targetList) {
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
            AccountGroupAttributeSearchCriteriaDto criteriaDto) {

        SearchExportFileResponseModel model =
            new SearchExportFileResponseModel();

        PagingResult<AccountGroupAttributeSearchResultDto> dataList =
            crmAccountGroupAttributeLogicMapper.convert(crmCcAccountGrpAttrDao
                .findByAccountGroupAttributeSearchCriteriaDto(criteriaDto));

        if (dataList != null && (0 < dataList.size())) {

            // ファイルに出力します。
            SharedFile sharedFile =
                FileUtil.createSharedFile(
                    criteriaDto.fileName,
                    UniqueIdGenerator.getInstance().create(),
                    contextContainer);

            CSVWriter<AccountGroupAttributeSearchResultDto> writer =
                new CSVWriter<AccountGroupAttributeSearchResultDto>(
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
            {
                "CRM.CC.crmAccountClassCd",
                "CRM.CC.crmAccountClassName",
                "CRM.CC.dealClass",
                "CRM.CC.maintenanceTargetFlag",
                "CRM.CC.ifUpdateWay" };
        FileUtil.putHeaderNamesMap(headerNames, preferences);

        int dataIndex = 0;
        preferences.addMapping(dataIndex++, "crmAccountClassCd");
        preferences.addMapping(dataIndex++, "crmAccountClassName");
        preferences.addMapping(dataIndex++, "dealClass");
        preferences.addMapping(dataIndex++, "maintenanceTargetFlag");
        preferences.addMapping(dataIndex++, "ifUpdateWay");

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
            List<CrmCcAccountGrpAttr> deleteList =
                crmCcAccountGrpAttrDao.findByCrmCcAccountClass(entity
                    .getCompanyCd(), entity.getCrmAccountClassCd());
            for (CrmCcAccountGrpAttr delete : deleteList) {
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
    public void delete(CrmCcAccountGrpAttr crmCcAccountGrpAttr) {
        crmCcAccountGrpAttr.setDeleteFlag(true);
        crmCcAccountGrpAttrDao.updateIncludes(
            crmCcAccountGrpAttr,
            CrmCcAccountAttrNames.deleteFlag(),
            CrmCcAccountAttrNames.recordDate(),
            CrmCcAccountAttrNames.recordUserCd());
        crmAccountGroupModuleLogic
            .deleteByCrmCcAccountGrpAttr(crmCcAccountGrpAttr);
    }
}
