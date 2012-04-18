/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.dao.CrmCcSynonymPatternDao;
import com.biz_integral.crm.cc.domain.entity.CrmCcSynonymPattern;
import com.biz_integral.crm.cc.domain.logic.SynonymPatternLogic;
import com.biz_integral.crm.cc.domain.logic.batch.config.SynonymPatternConfig;
import com.biz_integral.crm.cc.domain.logic.batch.config.impl.SynonymPatternConfigImpl;
import com.biz_integral.crm.extension.file.TextWriter;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.FeatureStateOperation;
import com.biz_integral.foundation.core.logging.BizLogger;
import com.biz_integral.foundation.core.logging.BizLoggerFactory;
import com.biz_integral.foundation.core.logging.LogLevel;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.CharacterEncoding;
import com.biz_integral.foundation.core.util.CollectionsUtil;
import com.biz_integral.foundation.core.util.StringUtil;

/**
 * SynonymPatternLogicロジックの実装です。
 */
public class SynonymPatternLogicImpl implements SynonymPatternLogic {

    /** BizLogger */
    protected BizLogger log = BizLoggerFactory.getLogger(this.getClass());

    /**
     * {@link CrmCcSynonymPatternDao 類義語に関するDAO}
     */
    @Resource
    protected CrmCcSynonymPatternDao crmCcSynonymPatternDao;

    /**
     * {@link FeatureStateOperation}
     */
    @Resource
    protected FeatureStateOperation featureStateOperation;

    /**
     * {@link ContextContainer}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(CrmCcSynonymPattern synonymPattern) {
        // 類義語パターンを一件取得する。
        CrmCcSynonymPattern crmCcSynonymPattern =
            crmCcSynonymPatternDao.getEntity(synonymPattern);

        // (a)の類義語パターンエンティティが取得できた場合は処理を終了する（同じデータは登録しない）
        if (crmCcSynonymPattern == null || crmCcSynonymPattern.getId() == null) {

            // 類義語パターンを登録する
            crmCcSynonymPatternDao.createSynonymPattern(synonymPattern);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void outputFile() {

        /** バッチ処理実行設定 */
        SynonymPatternConfig config = new SynonymPatternConfigImpl();

        List<String> companyCdList = config.getCompanyList();

        for (String companyCd : companyCdList) {

            featureStateOperation.switchTo(companyCd);

            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .setCompanyCode(companyCd);

            log.log(
                LogLevel.INFO,
                MessageBuilder
                    .create("I.CRM.CC.00017")
                    .addParameter(companyCd)
                    .toMessage()
                    .toText(
                        new Locale(contextContainer
                            .getUserContext()
                            .getLocaleID())));

            String folder = config.getFolder(companyCd);
            String file = config.getFile(companyCd);
            boolean companyMode = config.getCompanyMode(companyCd);
            boolean localeMode = config.getLocaleMode(companyCd);
            String encoding = config.getEncoding(companyCd);

            boolean outputFlag = false;

            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("company", companyMode);
            criteria.put("locale", localeMode);
            criteria.put("companyCd", companyCd);

            String orgCompanyCd = "";
            String orgLocaleId = "";
            CrmCcSynonymPattern orgEntity = null;

            List<String> lineList = new ArrayList<String>();
            List<String> orglineList = new ArrayList<String>();

            try {

                List<CrmCcSynonymPattern> parentList =
                    crmCcSynonymPatternDao.findNameList(criteria);

                for (int i = 0; i < parentList.size(); i++) {

                    CrmCcSynonymPattern parent = parentList.get(i);

                    List<CrmCcSynonymPattern> childList =
                        crmCcSynonymPatternDao.findSynonymNameList(parent);

                    String[] nameList = new String[childList.size() + 1];

                    nameList[0] = parent.getName();

                    for (int j = 0; j < childList.size(); j++) {
                        nameList[j + 1] = childList.get(j).getSynonymName();
                    }

                    lineList.add(StringUtil.concat(nameList, ","));

                    if (companyMode || localeMode) {

                        if (companyMode && !StringUtil.isEmpty(orgCompanyCd)) {
                            if (!orgCompanyCd.equals(childList
                                .get(0)
                                .getCompanyCd())) {
                                outputFlag = true;
                            }
                        }
                        if (localeMode && !StringUtil.isEmpty(orgLocaleId)) {
                            if (!orgLocaleId.equals(childList
                                .get(0)
                                .getLocaleId())) {
                                outputFlag = true;
                            }
                        }
                        if (outputFlag) {
                            outputFile(folder, makeFileName(
                                companyMode,
                                localeMode,
                                file,
                                orgEntity), encoding, orglineList);

                            for (int k = 0; k < orglineList.size(); k++) {
                                lineList.remove(0);
                            }

                        }
                        outputFlag = false;

                    }
                    if (i == parentList.size() - 1) {
                        outputFile(folder, makeFileName(
                            companyMode,
                            localeMode,
                            file,
                            childList.get(0)), encoding, lineList);
                    }

                    orgEntity = childList.get(0);
                    orgCompanyCd = childList.get(0).getCompanyCd();
                    orgLocaleId = childList.get(0).getLocaleId();
                    orglineList = CollectionsUtil.newArrayList(lineList);
                }

            } catch (Exception e) {

                e.printStackTrace();
                log.log(LogLevel.INFO, e.getMessage());
            }
        }
    }

    private void outputFile(String folder, String file, String encoding,
            List<String> lineList) {

        String separator = System.getProperty("file.separator");

        TextWriter writer = new TextWriter();
        writer.write(folder.concat(separator).concat(file), CharacterEncoding
            .getCharacterEncoding(encoding), lineList, false);
        writer.close();
    }

    private String makeFileName(boolean companyMode, boolean localeMode,
            String file, CrmCcSynonymPattern entity) {

        String param = "";

        if (entity != null) {
            if (companyMode && localeMode) {
                param =
                    "_".concat(entity.getCompanyCd()).concat("_").concat(
                        entity.getLocaleId());
            } else if (companyMode) {
                param = entity.getCompanyCd();
            } else if (localeMode) {
                param = entity.getLocaleId();
            }
        }
        return file.replace("[0]", param);
    }
}
