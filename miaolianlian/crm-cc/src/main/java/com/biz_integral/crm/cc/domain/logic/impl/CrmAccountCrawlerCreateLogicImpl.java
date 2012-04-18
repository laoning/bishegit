/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Resource;

import jp.co.intra_mart.foundation.solr.crawler.exception.SolrCrawlerException;
import jp.co.nttdata.intra_mart.solr.domain.EveryoneAccessControlEntry;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.cc.domain.entity.CrmCcAccountNames;
import com.biz_integral.crm.cc.domain.logic.CrmAccountCrawlerCreateLogic;
import com.biz_integral.crm.cc.domain.logic.constants.CrmSolrConstans;
import com.biz_integral.crm.cc.domain.util.SolrUtil;
import com.biz_integral.crm.extension.solr.constants.BizIntegralSolrConstants;
import com.biz_integral.crm.extension.solr.domain.BizIntegralSolrInputDocument;
import com.biz_integral.crm.extension.solr.model.BizIntegralInputDocument;
import com.biz_integral.foundation.core.logging.BizLogger;
import com.biz_integral.foundation.core.logging.BizLoggerFactory;
import com.biz_integral.foundation.core.logging.LogLevel;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.domain.master.locale.BizLocaleInfo;
import com.biz_integral.service.domain.master.locale.BizLocaleManager;

/**
 * アカウントのSolr文書作成クラス
 */
public class CrmAccountCrawlerCreateLogicImpl implements
        CrmAccountCrawlerCreateLogic {

    /** ログ */
    private BizLogger log =
        BizLoggerFactory.getLogger(CrmAccountCrawlerCreateLogicImpl.class);

    /**
     * コンストラクタ
     * 
     * @throws SolrCrawlerException
     */
    public CrmAccountCrawlerCreateLogicImpl() throws SolrCrawlerException {
    }

    /** Bizロケール管理クラス */
    @Resource
    protected BizLocaleManager bizLocaleManager;

    // /** 名称設定 */
    // @Resource
    // protected EnumNamesRegistry enumNamesRegistry;
    //
    // /** コンテキスト */
    // @Resource
    // protected ContextContainer contextContainer;
    //
    // /** フィーチャコンテキスト操作クラス */
    // @Resource
    // protected FeatureStateOperation featureStateOperation;

    /**
     * {@inheritDoc}
     */
    public BizIntegralInputDocument createSolrDocument(String id,
            String nowDate, List<String> appDocTypeList, CrmCcAccount account) {

        BizIntegralInputDocument inputDoc =
            new BizIntegralSolrInputDocument(
                BizIntegralSolrConstants.SEARCH_FUNCTION.SYNONYM_SEARCH);

        // ロケールの作成
        BizLocaleInfo locale =
            bizLocaleManager.getLocale(account.getLocaleId());

        // id の設定
        inputDoc.setDocumentId(id);

        // type の設定
        setDocumentType(inputDoc, appDocTypeList);

        // title の設定
        inputDoc.setTitle(account.getCrmAccountCd());

        // synonym の設定
        setDocumentSynonym(inputDoc, account);

        // sids_allowed と sids_deniedの設定
        setAccessControl(inputDoc, account);

        // record_date の設定
        inputDoc.setRecordDate(DateUtil.parse(nowDate));

        // company の設定
        inputDoc.setCompany(account.getCompanyCd());

        // locale
        inputDoc.setLocale(locale.getLocaleId());

        // dynamic_filedの設定
        setDynamicField(inputDoc, account);

        // update_date_string の設定
        inputDoc.addField(CrmSolrConstans.CRM_FIELD_UPDATE_DATE, account
            .getRecordDate());

        log
            .log(
                LogLevel.DEBUG,
                MessageFormat
                    .format(
                        "Entry crmAccount document [companyCd={0}, localeId={1}, crmAccountCd={2}]",
                        account.getCompanyCd(),
                        account.getLocaleId(),
                        account.getCrmAccountCd()));

        return inputDoc;
    }

    /**
     * タイプフィールドに設定します。
     * 
     * @param inputDoc
     *            文書
     * @param appDocTypeList
     *            基本文書種別リスト
     */
    private void setDocumentType(BizIntegralInputDocument inputDoc,
            List<String> appDocTypeList) {

        for (String type : appDocTypeList) {
            inputDoc.addType(type);
        }
    }

    /**
     * 名寄せ検索用のフィールドに設定します。<br>
     * name_synonym_morphとname_synonym_ngramに値を設定します。
     * 
     * @param inputDoc
     *            文書
     * @param account
     *            アカウントモデル
     */
    private void setDocumentSynonym(BizIntegralInputDocument inputDoc,
            CrmCcAccount account) {
        inputDoc.addNameSynonym(account.getCrmAccountCd());
        inputDoc.addNameSynonym(account.getCrmAccountName());
        inputDoc.addNameSynonym(account.getCrmAccountNameKana());
        inputDoc.addNameSynonym(account.getCrmAccountShortName());
        inputDoc.addNameSynonym(account.getCrmAccountSearchName());
    }

    /**
     * 閲覧可能権限と閲覧不可権限を設定します。
     * 
     * @param inputDoc
     *            文書
     * @param account
     *            アカウントモデル ロケールID
     */
    private void setAccessControl(BizIntegralInputDocument inputDoc,
            CrmCcAccount account) {
        // 名寄せ処理用として全ユーザが検索可能
        inputDoc.addAccessControlEntry(new EveryoneAccessControlEntry());
    }

    /**
     * ダイナミックフィールドを設定します。
     * 
     * @param inputDoc
     *            文書
     * @param account
     *            アカウントモデル
     */
    private void setDynamicField(BizIntegralInputDocument inputDoc,
            CrmCcAccount account) {
        inputDoc.addField(SolrUtil.getDynamicName(CrmCcAccountNames
            .crmAccountCd(), SolrUtil.DATA_TYPE_SUFFIX.STRING), account
            .getCrmAccountCd());
    }

}
