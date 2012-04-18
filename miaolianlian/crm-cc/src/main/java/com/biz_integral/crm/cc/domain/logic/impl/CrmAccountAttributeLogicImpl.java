/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.application.web.accountAttributeManage.SearchExportFileResponseModel;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountAttrDao;
import com.biz_integral.crm.cc.domain.dao.CrmCcAccountClassDao;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeCsvReaderModelDto;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AccountAttributeSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttr;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountAttrNames;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountClass;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountModule;
import com.biz_integral.crm.cc.domain.logic.CrmAccountAttributeLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountModuleLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.mapper.CrmAccountAttributeLogicMapper;
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
 * CrmAccountAttributeLogicロジックの実装です。
 */
public class CrmAccountAttributeLogicImpl implements CrmAccountAttributeLogic {

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
     * アカウント分類に関するマッパー
     */
    @Resource
    protected CrmAccountAttributeLogicMapper crmAccountAttributeLogicMapper;

    /**
     * アカウント分類に関するDAO
     */
    @Resource
    protected CrmCcAccountClassDao crmCcAccountClassDao;
    /**
     * アカウント属性に関するDAO
     */
    @Resource
    protected CrmCcAccountAttrDao crmCcAccountAttrDao;

    /**
     * {@link CrmAccountModuleLogic}です
     */
    @Resource
    protected CrmAccountModuleLogic crmAccountModuleLogic;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public PagingResult<AccountAttributeSearchResultDto> getAccountAttributeList(
            AccountAttributeSearchCriteriaDto dto) {
        return crmAccountAttributeLogicMapper.convert(crmCcAccountAttrDao
            .findByAccountAttributeSearchCriteriaDto(dto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CrmCcAccountAttr> createModel(String companyCd,
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
            "crmAccountStatus",
            ColumnType.STRING);
        preferences.addMapping(
            readerIndex++,
            "maintenanceTargetFlag",
            ColumnType.STRING);
        preferences.addMapping(readerIndex++, "ifUpdateWay", ColumnType.STRING);

        CSVReader<AccountAttributeCsvReaderModelDto> reader =
            new CSVReader<AccountAttributeCsvReaderModelDto>(
                AccountAttributeCsvReaderModelDto.class,
                preferences,
                inputStream);

        List<AccountAttributeCsvReaderModelDto> dtoList = null;
        try {
            dtoList = reader.read();
            if (dtoList == null || dtoList.isEmpty()) {
                throwValidationException("E.CRM.CC.00026");
            }
        } catch (Exception e) {
            throwValidationException("E.CRM.CC.00026");
        }

        return crmAccountAttributeLogicMapper
            .convertDtoList(companyCd, dtoList);
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
            List<CrmCcAccountAttr> entityList) {

        ValidationResults validationResultsNoExist = new ValidationResults();
        ValidationResults validationResults = new ValidationResults();

        for (CrmCcAccountAttr entity : entityList) {
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
    public void replace(String companyCd, List<CrmCcAccountAttr> targetList) {

        // アカウント属性を一覧検索します。
        List<CrmCcAccountAttr> entityList =
            crmCcAccountAttrDao.findByCompanyCd(
                companyCd,
                LogicalDelete.NO_RESTRICTION);

        boolean isExist = false;
        for (CrmCcAccountAttr target : targetList) {
            isExist = false;
            for (CrmCcAccountAttr entity : entityList) {
                if (EntityUtil.isTheSamePK(target, entity)) {
                    target.setVersionNo(entity.getVersionNo());
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                crmCcAccountAttrDao.update(target);
            } else {
                crmCcAccountAttrDao.insert(target);
            }
        }

        for (CrmCcAccountAttr entity : entityList) {
            isExist = false;
            for (CrmCcAccountAttr target : targetList) {
                if (EntityUtil.isTheSamePK(target, entity)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist && !entity.isDeleteFlag()) {
                entity.setDeleteFlag(true);
                crmCcAccountAttrDao.updateIncludes(
                    entity,
                    CrmCcAccountAttrNames.deleteFlag(),
                    CrmCcAccountAttrNames.recordDate(),
                    CrmCcAccountAttrNames.recordUserCd());
                crmAccountModuleLogic.deleteByCrmCcAccountAttr(entity);

            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchExportFileResponseModel createFile(
            AccountAttributeSearchCriteriaDto criteriaDto) {

        SearchExportFileResponseModel model =
            new SearchExportFileResponseModel();

        PagingResult<AccountAttributeSearchResultDto> dataList =
            crmAccountAttributeLogicMapper.convert(crmCcAccountAttrDao
                .findByAccountAttributeSearchCriteriaDto(criteriaDto));

        if (dataList != null && (0 < dataList.size())) {

            // ファイルに出力します。
            SharedFile sharedFile =
                FileUtil.createSharedFile(
                    criteriaDto.fileName,
                    UniqueIdGenerator.getInstance().create(),
                    contextContainer);

            CSVWriter<AccountAttributeSearchResultDto> writer =
                new CSVWriter<AccountAttributeSearchResultDto>(
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
                "CRM.CC.crmAccountStatus",
                "CRM.CC.maintenanceTargetFlag",
                "CRM.CC.ifUpdateWay" };
        FileUtil.putHeaderNamesMap(headerNames, preferences);

        int dataIndex = 0;
        preferences.addMapping(dataIndex++, "crmAccountClassCd");
        preferences.addMapping(dataIndex++, "crmAccountClassName");
        preferences.addMapping(dataIndex++, "dealClass");
        preferences.addMapping(dataIndex++, "crmAccountStatus");
        preferences.addMapping(dataIndex++, "maintenanceTargetFlag");
        preferences.addMapping(dataIndex++, "ifUpdateWay");

        return preferences;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CrmCcAccountAttr getEntity(CrmCcAccountAttr entity) {
        return crmCcAccountAttrDao.getByPk(entity.getCompanyCd(), entity
            .getDealClass(), entity.getCrmAccountClassCd(), entity
            .getCrmAccountStatus(), LogicalDelete.AVAILABLE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void create(CrmCcAccountAttr crmCcAccountAttr) {

        validateCheckForCrmCcAccountClass(crmCcAccountAttr);

        CrmCcAccountAttr checkResult =
            validateCheckForCrmCcAccountAttr(crmCcAccountAttr);

        if (checkResult == null) {
            crmCcAccountAttrDao.insert(crmCcAccountAttr);
        } else {
            crmCcAccountAttr.setVersionNo(checkResult.getVersionNo());
            crmCcAccountAttr.setDeleteFlag(false);
            crmCcAccountAttrDao.updateIncludes(
                crmCcAccountAttr,
                CrmCcAccountAttrNames.maintenanceTargetFlag(),
                CrmCcAccountAttrNames.ifUpdateWay(),
                CrmCcAccountAttrNames.deleteFlag(),
                CrmCcAccountAttrNames.recordDate(),
                CrmCcAccountAttrNames.recordUserCd());
        }
    }

    /**
     * CRMアカウント分類のチェック
     * 
     * @param crmCcAccountAttr
     *            CRMアカウント属性
     */
    private void validateCheckForCrmCcAccountClass(
            CrmCcAccountAttr crmCcAccountAttr) {

        // アカウント分類の存在チェック
        if (!StringUtil.isEmpty(crmCcAccountAttr.getCrmAccountClassCd())) {
            CrmCcAccountClass resultClass =
                crmCcAccountClassDao.getByPkNoException(
                    crmCcAccountAttr.getCompanyCd(),
                    crmCcAccountAttr.getCrmAccountClassCd(),
                    contextContainer.getUserContext().getLocaleID(),
                    LogicalDelete.AVAILABLE);
            if (resultClass == null) {
                ValidationResults validationResults = new ValidationResults();
                validationResults.add(new ValidationResult(MessageBuilder
                    .create("E.CRM.CC.00024")
                    .addParameter(MessageBuilder.$("CRM.CC.crmAccountClassCd"))
                    .addParameter(
                        MessageBuilder.$(crmCcAccountAttr
                            .getCrmAccountClassCd()))
                    .toMessage()));
                throw new ValidationException(validationResults);
            }
        }
    }

    /**
     * アカウント属性のチェック
     * 
     * @param entity
     *            CRMアカウント属性
     * @return CRMアカウント属性
     */
    private CrmCcAccountAttr validateCheckForCrmCcAccountAttr(
            CrmCcAccountAttr entity) {

        // アカウント属性の重複チェック
        CrmCcAccountAttr resultAttr =
            crmCcAccountAttrDao.getByPkNoException(
                entity.getCompanyCd(),
                entity.getDealClass(),
                entity.getCrmAccountClassCd(),
                entity.getCrmAccountStatus(),
                LogicalDelete.NO_RESTRICTION);
        if (resultAttr != null && !resultAttr.isDeleteFlag()) {
            ValidationResults validationResults = new ValidationResults();
            validationResults.add(new ValidationResult(MessageBuilder.create(
                "E.CRM.CC.00121").toMessage()));
            throw new ValidationException(validationResults);
        }
        return resultAttr;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void update(CrmCcAccountAttr crmCcAccountAttr) {

        crmCcAccountAttrDao.updateIncludes(
            crmCcAccountAttr,
            CrmCcAccountAttrNames.maintenanceTargetFlag(),
            CrmCcAccountAttrNames.ifUpdateWay(),
            CrmCcAccountAttrNames.crmAccountStatus(),
            CrmCcAccountAttrNames.recordUserCd(),
            CrmCcAccountAttrNames.recordDate());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void delete(CrmCcAccountAttr crmCcAccountAttr) {

        crmCcAccountAttrDao.updateIncludes(
            crmCcAccountAttr,
            CrmCcAccountAttrNames.deleteFlag(),
            CrmCcAccountAttrNames.recordUserCd(),
            CrmCcAccountAttrNames.recordDate());

        CrmCcAccountModule crmCcAccountModule =
            crmAccountAttributeLogicMapper.convert(crmCcAccountAttr);

        List<CrmCcAccountModule> crmCcAccountModuleList =
            crmAccountModuleLogic.getEntityList(crmCcAccountModule);

        for (CrmCcAccountModule target : crmCcAccountModuleList) {
            crmAccountModuleLogic.delete(target);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long deleteByCrmCcAccountClass(
            CrmCcAccountClass... crmCcAccountClass) {
        long count = 0;
        for (CrmCcAccountClass entity : crmCcAccountClass) {
            List<CrmCcAccountAttr> deleteList =
                crmCcAccountAttrDao.findByCrmCcAccountClass(entity
                    .getCompanyCd(), entity.getCrmAccountClassCd());
            for (CrmCcAccountAttr delete : deleteList) {
                delete.setDeleteFlag(true);
                crmCcAccountAttrDao.updateIncludes(
                    delete,
                    CrmCcAccountAttrNames.deleteFlag(),
                    CrmCcAccountAttrNames.recordUserCd(),
                    CrmCcAccountAttrNames.recordDate());
                crmAccountModuleLogic.deleteByCrmCcAccountAttr(delete);

                count++;
            }
        }
        return count;
    }
}
