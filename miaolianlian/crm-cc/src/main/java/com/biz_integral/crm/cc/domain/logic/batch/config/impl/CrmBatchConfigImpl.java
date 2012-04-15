/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.config.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.container.SingletonS2Container;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.biz_integral.crm.cc.domain.logic.batch.config.CrmBatchConfig;
import com.biz_integral.crm.cc.domain.logic.batch.config.CrmConfigBase;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.exception.FileNotFoundRuntimeException;

/**
 * CRMバッチ設定ファイルを扱います。
 */
public class CrmBatchConfigImpl extends CrmConfigBase implements CrmBatchConfig {

    /**
     * {@link ContextContainer}
     */
    @Resource
    protected ContextContainer contextContainer;

    /** separator */
    private static final String SEPARATOR = "_";

    /** 設定ファイルパス */
    private static final String CONFIG_FILE = "conf/biz-batch-config-crm.xml";

    /** バッチ別の対象会社コード */
    private static final String XPATH_TARGET_COMPANY =
        "/biz-batch-config/target_company/batch_type[@id=''{0}'']";

    /**
     * コンストラクタ<br>
     * biz-batch-config-crm.xmlの読み込みを行います。
     * 
     * @throws FileNotFoundRuntimeException
     */
    public CrmBatchConfigImpl() {
        this(CONFIG_FILE);
    }

    /**
     * コンストラクタ<br>
     * biz-batch-config-crm.xmlの読み込みを行います。
     * 
     * @param fileName
     *            コンフィグファイル
     * @throws FileNotFoundRuntimeException
     */
    public CrmBatchConfigImpl(String fileName) {
        super(fileName);
        contextContainer =
            SingletonS2Container.getComponent(ContextContainer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBatchType() {

        return contextContainer
            .getApplicationContext()
            .getApplicationID()
            .concat(SEPARATOR)
            .concat(contextContainer.getApplicationContext().getModuleID())
            .concat(SEPARATOR)
            .concat(contextContainer.getApplicationContext().getUseCaseID());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBatchType(String applicationId, String moduleId,
            String usecaseId) {

        return applicationId.concat(SEPARATOR).concat(moduleId).concat(
            SEPARATOR).concat(usecaseId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getCompanyList(String batchType) {

        Node parentNode =
            getNode(MessageFormat.format(XPATH_TARGET_COMPANY, batchType));
        NodeList nodeList = parentNode.getChildNodes();

        List<String> companyList = new ArrayList<String>();
        for (int i = 0, length = nodeList.getLength(); i < length; i++) {
            Node node = nodeList.item(i);
            if (toBoolean(node.getTextContent())) {
                companyList.add(node
                    .getAttributes()
                    .getNamedItem("code")
                    .getTextContent());
            }
        }
        return companyList;
    }
}
