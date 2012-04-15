/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import jp.co.intra_mart.foundation.solr.crawler.exception.SolrCrawlerException;
import jp.co.nttdata.intra_mart.solr.exception.QuotaException;
import jp.co.nttdata.intra_mart.solr.exception.SearchException;

import com.biz_integral.crm.cc.domain.dao.CrmCcAccountDao;
import com.biz_integral.crm.cc.domain.dto.solr.CrawlerWorkDto;
import com.biz_integral.crm.cc.domain.dto.solr.CrawlerWorkPagerDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.logic.CrmAccountCrawlerCreateLogic;
import com.biz_integral.crm.cc.domain.logic.CrmAccountCrawlerLogic;
import com.biz_integral.crm.cc.domain.logic.CrmCrawlerBaseLogic;
import com.biz_integral.crm.cc.domain.logic.batch.types.BatchCrawlingType;
import com.biz_integral.crm.cc.domain.logic.constants.CrmSolrConstans;
import com.biz_integral.crm.extension.solr.common.BizIntegralCrawlerUtil;
import com.biz_integral.crm.extension.solr.model.BizIntegralInputDocument;
import com.biz_integral.foundation.core.logging.LogLevel;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.message.MessageSource;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * CRMアカウントのクローラロジック
 */
public class CrmAccountCrawlerLogicImpl extends CrmCrawlerBaseLogic implements
        CrmAccountCrawlerLogic {

    /** CRMアカウントDAO */
    @Resource
    private CrmCcAccountDao crmCcAccountDao;

    /** CRMアカウントSolr文書作成ロジック */
    @Resource
    private CrmAccountCrawlerCreateLogic crmAccountCrawlerCreateLogic;

    /** コンストラクタ */
    public CrmAccountCrawlerLogicImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getDocumentId() {
        return CrmSolrConstans.DOCUMENT_INFO.CRM_ACCOUNT.documentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void crawler(String companyCd) throws SolrCrawlerException {

        // オフセット
        long offset = 0L;
        // 登録・更新件数
        long addCount = 0L;
        // 削除件数
        long delCount = 0L;

        long infoCount = getCount(companyCd);

        List<CrmCcAccount> targetList = null;
        if (infoCount > 0L) {
            targetList = getTargetList(companyCd, offset);
        }

        int index = 0;

        try {

            do {
                if (targetList == null || targetList.isEmpty()) {
                    break;
                }

                CrmCcAccount account = targetList.get(index++);
                String id =
                    BizIntegralCrawlerUtil.makeDocumentId(contextContainer
                        .getApplicationContext()
                        .getApplicationID(), contextContainer
                        .getApplicationContext()
                        .getModuleID(), getDocumentId(), companyCd, account
                        .getLocaleId(), account.getCrmAccountCd());

                if (account.isDeleteFlag()) {
                    delCount++;

                    if (crawlingType == BatchCrawlingType.DELTA
                        || (!noLimitFlg && DateUtil
                            .parse(super.startDate)
                            .before(account.getRecordDate()))) {
                        // 差分作成モード、もしくは再作成モードの削除したデータより過去データの場合
                        // 削除
                        deleteIndexById(id);
                    }
                } else {
                    addCount++;

                    BizIntegralInputDocument solrDocument =
                        crmAccountCrawlerCreateLogic.createSolrDocument(
                            id,
                            nowDate,
                            appDocTypeList,
                            account);

                    solrMng.addDocument(solrDocument);
                }
                offset++;
                if (index == targetList.size()) {
                    targetList = null;
                    if (infoCount >= offset + 1L) {
                        index = 0;
                        targetList = getTargetList(companyCd, offset);
                    }
                }
            } while (true);

            log.log(LogLevel.INFO, MessageSource.getMessage(MessageBuilder
                .create("I.CRM.CC.00013")
                .addParameter(companyCd)
                .addParameter(getDocumentId())
                .addParameter(addCount)
                .toMessage(), new Locale(contextContainer
                .getUserContext()
                .getLocaleID())));
            log.log(LogLevel.INFO, MessageSource.getMessage(MessageBuilder
                .create("I.CRM.CC.00014")
                .addParameter(companyCd)
                .addParameter(getDocumentId())
                .addParameter(delCount)
                .toMessage(), new Locale(contextContainer
                .getUserContext()
                .getLocaleID())));

        } catch (QuotaException e) {
            throw new SolrCrawlerException(e.getMessage(), e);
        } catch (SearchException e) {
            throw new SolrCrawlerException(e.getMessage(), e);
        }
    }

    /**
     * 会社コードで検索したクローラ作成対象一覧を取得します。
     * 
     * @param companyCd
     *            会社コード
     * @param offset
     *            オフセット
     * @return 検索結果
     */
    private List<CrmCcAccount> getTargetList(String companyCd, long offset) {
        CrawlerWorkPagerDto dto = getPagerDto(companyCd, offset);

        return crmCcAccountDao.findByUpdate(dto);
    }

    /**
     * 会社コードで検索した件数を返却します。
     * 
     * @param companyCd
     *            会社コード
     * @return 件数
     */
    private long getCount(String companyCd) {
        CrawlerWorkDto dto = getDto(companyCd);

        return crmCcAccountDao.getCountByUpdate(dto);
    }
}
