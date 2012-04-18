package com.biz_integral.crm.cc.domain.logic.impl;

import java.io.IOException;
import java.text.MessageFormat;

import javax.annotation.Resource;

import jp.co.intra_mart.foundation.service.client.file.NetworkFile;

import com.biz_integral.crm.cc.domain.logic.LastCrawlingDateLogic;
import com.biz_integral.crm.cc.domain.logic.batch.exception.BatchRuntimeException;
import com.biz_integral.crm.cc.domain.logic.constants.CrmSolrConstans;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.message.MessageBuilder;

/**
 * 最終クローラー起動日時を管理する実装です。
 */
public class LastCrawlingDateLogicImpl implements LastCrawlingDateLogic {
    /** コンテキスト */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastCrawlingDateFileName(String documentId) {
        return new StringBuilder().append(documentId).append(
            CrmSolrConstans.LAST_CRAWLING_DATE_FILENAME).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastCrawlingDateFilePath(String documentId) {
        return MessageFormat.format(
            CrmSolrConstans.LAST_CRAWLING_DATE_PATH,
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getApplicationID(),
            contextContainer
                .getApplicationContext()
                .getFeatureContext()
                .getCompanyCode());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastCrawlingDate(String documentId) {
        // パス作成
        String filepath = getLastCrawlingDateFilePath(documentId);
        String filename = getLastCrawlingDateFileName(documentId);
        String lastCrawlingDate = null;
        // try {
        try {
            NetworkFile vf = new NetworkFile(filepath);
            // ディレクトリが存在しなかった場合
            if (!vf.isDirectory()) {
                return null;
            }
            vf =
                new NetworkFile(new StringBuilder()
                    .append(filepath)
                    .append("/")
                    .append(filename)
                    .toString());
            // ファイルが存在しなかった場合
            if (!vf.isFile()) {
                return null;
            }

            lastCrawlingDate = vf.read();

        } catch (IOException e) {
            throw new BatchRuntimeException(e, MessageBuilder.create(
                "E.CRM.CC.00088").addParameter(filename).toMessage());
        }

        return lastCrawlingDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLastCrawlingDate(String documentId, String nowDate) {
        // パス作成
        String filepath = getLastCrawlingDateFilePath(documentId);
        String filename = getLastCrawlingDateFileName(documentId);

        try {
            NetworkFile vf = new NetworkFile(filepath);
            if (!vf.isDirectory()) {
                vf.makeDirectories();
            }

            vf =
                new NetworkFile(new StringBuilder()
                    .append(filepath)
                    .append("/")
                    .append(filename)
                    .toString());
            vf.write(nowDate);

        } catch (IOException e) {
            throw new BatchRuntimeException(e, MessageBuilder.create(
                "E.CRM.CC.00088").addParameter(filename).toMessage());
        }
    }
}
