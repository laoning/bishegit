/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.config.impl;

import java.text.MessageFormat;
import java.util.List;

import jp.co.intra_mart.foundation.service.provider.file.StorageDirectory;

import org.w3c.dom.Node;

import com.biz_integral.crm.cc.domain.logic.batch.config.SynonymPatternConfig;
import com.biz_integral.foundation.core.exception.FileNotFoundRuntimeException;
import com.biz_integral.foundation.core.util.StringUtil;

/**
 * バッチ処理設定ファイルを扱います。
 */
public class SynonymPatternConfigImpl extends CrmBatchConfigImpl implements
        SynonymPatternConfig {

    /** デフォルトファイル名 */
    private static final String DEFAULT_FILE_NAME = "synonyms.txt";

    /** デフォルトエンコード */
    private static final String DEFAULT_ENCODE = "UTF-8";

    private static final String XPATH_SYNONYM_CONFIG_FOLDER =
        "/biz-batch-config/synonym_config/company[@code=''{0}'']/folder";

    private static final String XPATH_SYNONYM_CONFIG_FILE =
        "/biz-batch-config/synonym_config/company[@code=''{0}'']/file";

    private static final String XPATH_SYNONYM_CONFIG_COMPANY_MODE =
        "/biz-batch-config/synonym_config/company[@code=''{0}'']/company";

    private static final String XPATH_SYNONYM_CONFIG_LOCALE_MODE =
        "/biz-batch-config/synonym_config/company[@code=''{0}'']/locale";

    private static final String XPATH_SYNONYM_CONFIG_ENCODING =
        "/biz-batch-config/synonym_config/company[@code=''{0}'']/encoding";

    /**
     * コンストラクタ<br>
     * biz-batch-config-crm.xmlの読み込みを行います。
     * 
     * @throws FileNotFoundRuntimeException
     */
    public SynonymPatternConfigImpl() {
        super();
    }

    /**
     * コンストラクタ<br>
     * biz-batch-config-crm.xmlの読み込みを行います。
     * 
     * @param fileName
     *            コンフィグファイル
     * @throws FileNotFoundRuntimeException
     */
    public SynonymPatternConfigImpl(String fileName) {
        super(fileName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getCompanyList() {
        return getCompanyList(getBatchType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFolder(String companyCd) {

        Node node =
            getNode(MessageFormat
                .format(XPATH_SYNONYM_CONFIG_FOLDER, companyCd));

        if (node != null) {
            String value = node.getTextContent();
            if (!StringUtil.isEmpty(value)) {
                return value;
            }
        }

        String path = StorageDirectory.instance().getPath();
        return path.concat("/crm");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFile(String companyCd) {

        Node node =
            getNode(MessageFormat.format(XPATH_SYNONYM_CONFIG_FILE, companyCd));

        if (node != null) {
            String value = node.getTextContent();
            if (!StringUtil.isEmpty(value)) {
                return value;
            }
        }
        return DEFAULT_FILE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getCompanyMode(String companyCd) {

        Node node =
            getNode(MessageFormat.format(
                XPATH_SYNONYM_CONFIG_COMPANY_MODE,
                companyCd));

        if (node != null) {
            String value = node.getTextContent();
            if (!StringUtil.isEmpty(value)) {
                return toBoolean(value);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getLocaleMode(String companyCd) {

        Node node =
            getNode(MessageFormat.format(
                XPATH_SYNONYM_CONFIG_LOCALE_MODE,
                companyCd));

        if (node != null) {
            String value = node.getTextContent();
            if (!StringUtil.isEmpty(value)) {
                return toBoolean(value);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEncoding(String companyCd) {

        Node node =
            getNode(MessageFormat.format(
                XPATH_SYNONYM_CONFIG_ENCODING,
                companyCd));

        if (node != null) {
            String value = node.getTextContent();
            if (!StringUtil.isEmpty(value)) {
                return value;
            }
        }
        return DEFAULT_ENCODE;
    }
}
