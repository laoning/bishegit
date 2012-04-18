/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Resource;

import jp.co.intra_mart.foundation.solr.crawler.exception.SolrCrawlerException;
import jp.co.nttdata.intra_mart.solr.domain.EveryoneAccessControlEntry;

import com.biz_integral.crm.cc.domain.entity.CrmCcContact;
import com.biz_integral.crm.cc.domain.entity.CrmCcContactNames;
import com.biz_integral.crm.cc.domain.logic.CrmContactCrawlerCreateLogic;
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
 * コンタクトのSolr文書作成クラス
 */
public class CrmContactCrawlerCreateLogicImpl implements
        CrmContactCrawlerCreateLogic {

    /** ログ */
    private BizLogger log =
        BizLoggerFactory.getLogger(CrmContactCrawlerCreateLogicImpl.class);

    /**
     * コンストラクタ
     * 
     * @throws SolrCrawlerException
     */
    public CrmContactCrawlerCreateLogicImpl() throws SolrCrawlerException {
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
    @Override
    public BizIntegralInputDocument createSolrDocument(String id,
            String nowDate, List<String> appDocTypeList, CrmCcContact contact) {

        BizIntegralInputDocument inputDoc =
            new BizIntegralSolrInputDocument(
                BizIntegralSolrConstants.SEARCH_FUNCTION.SYNONYM_SEARCH);

        // ロケールの作成
        BizLocaleInfo locale =
            bizLocaleManager.getLocale(contact.getLocaleId());

        // id の設定
        inputDoc.setDocumentId(id);

        // type の設定
        setDocumentType(inputDoc, appDocTypeList);

        // title の設定
        inputDoc.setTitle(contact.getCrmContactCd());

        // synonym の設定
        setDocumentSynonym(inputDoc, contact);

        // sids_allowed と sids_deniedの設定
        setAccessControl(inputDoc, contact);

        // record_date の設定
        inputDoc.setRecordDate(DateUtil.parse(nowDate));

        // company の設定
        inputDoc.setCompany(contact.getCompanyCd());

        // locale
        inputDoc.setLocale(locale.getLocaleId());

        // dynamic_filedの設定
        setDynamicField(inputDoc, contact);

        // update_date_string の設定
        inputDoc.addField(CrmSolrConstans.CRM_FIELD_UPDATE_DATE, contact
            .getRecordDate());

        log
            .log(
                LogLevel.DEBUG,
                MessageFormat
                    .format(
                        "Entry crmContact document [companyCd={0}, localeId={1}, crmContactCd={2}]",
                        contact.getCompanyCd(),
                        contact.getLocaleId(),
                        contact.getCrmContactCd()));

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
     * @param contact
     *            コンタクトモデル
     */
    private void setDocumentSynonym(BizIntegralInputDocument inputDoc,
            CrmCcContact contact) {
        inputDoc.addNameSynonym(contact.getCrmContactCd());
        inputDoc.addNameSynonym(contact.getCrmContactName());
        inputDoc.addNameSynonym(contact.getCrmContactNameKana());
        inputDoc.addNameSynonym(contact.getCrmContactShortName());
        inputDoc.addNameSynonym(contact.getCrmContactSearchName());
    }

    /**
     * 閲覧可能権限と閲覧不可権限を設定します。
     * 
     * @param inputDoc
     *            文書
     * @param contact
     *            コンタクトモデル
     */
    private void setAccessControl(BizIntegralInputDocument inputDoc,
            CrmCcContact contact) {
        // 名寄せ処理用として全ユーザが検索可能
        inputDoc.addAccessControlEntry(new EveryoneAccessControlEntry());
    }

    /**
     * ダイナミックフィールドを設定します。
     * 
     * @param inputDoc
     *            文書
     * @param contact
     *            コンタクトモデル
     */
    private void setDynamicField(BizIntegralInputDocument inputDoc,
            CrmCcContact contact) {
        inputDoc.addField(SolrUtil.getDynamicName(CrmCcContactNames
            .crmContactCd(), SolrUtil.DATA_TYPE_SUFFIX.STRING), contact
            .getCrmContactCd());
    }
}
