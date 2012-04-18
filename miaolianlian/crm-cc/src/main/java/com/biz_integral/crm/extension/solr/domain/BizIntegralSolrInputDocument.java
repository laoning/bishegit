/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.solr.domain;

import java.util.Collection;
import java.util.Date;

import jp.co.nttdata.intra_mart.solr.domain.IntramartSolrInputDocument;

import com.biz_integral.crm.extension.solr.constants.BizIntegralSolrConstants;
import com.biz_integral.crm.extension.solr.constants.BizIntegralSolrConstants.SEARCH_FUNCTION;
import com.biz_integral.crm.extension.solr.model.BizIntegralInputDocument;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.StringUtil;

/**
 * Biz用のSolr登録用ドキュメント
 */
public class BizIntegralSolrInputDocument extends IntramartSolrInputDocument
        implements BizIntegralInputDocument {

    /**
     * コンストラクタ
     */
    public BizIntegralSolrInputDocument() {
        super();
        // 対応検索機能を初期化
        for (SEARCH_FUNCTION function : SEARCH_FUNCTION.values()) {
            setField(function.field(), function.default_flag());
        }
    }

    /**
     * コンストラクタ
     * 
     * @param functionArray
     *            対応検索機能
     */
    public BizIntegralSolrInputDocument(SEARCH_FUNCTION... functionArray) {
        this();
        // 対応機能を設定
        for (SEARCH_FUNCTION function : functionArray) {
            setField(function.field(), "1");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCompany(String company) {
        addField(FIELD_COMPANY, company);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCompany(String company) {
        setField(FIELD_COMPANY, company);
    }

    /**
     * 会社コードを取得する。<br>
     * 会社コードが未設定の場合はnullを返却する。
     * 
     * @return 会社コードの配列
     */
    @SuppressWarnings("unchecked")
    public String[] getCompanies() {
        Object obj = getField(FIELD_COMPANY);
        if (obj == null)
            return null;
        if (obj instanceof Collection) {
            Collection<String> vals = (Collection<String>) obj;
            if (vals.size() <= 0) {
                return null;
            }
            return vals.toArray(new String[vals.size()]);

        }
        return (new String[] { (String) obj });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLocale(String locale) {
        addField(FIELD_LOCALE, locale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocale(String locale) {
        setField(FIELD_LOCALE, locale);
    }

    /**
     * ロケールIDを取得する。<br>
     * ロケールIDが未設定の場合はnullを返却する。
     * 
     * @return ロケールIDの配列
     */
    @SuppressWarnings("unchecked")
    public String[] getLocales() {
        Object obj = getField(FIELD_LOCALE);
        if (obj == null)
            return null;
        if (obj instanceof Collection) {
            Collection<String> vals = (Collection) obj;
            if (vals.size() <= 0) {
                return null;
            }
            return vals.toArray(new String[vals.size()]);

        }
        return (new String[] { (String) obj });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNameSynonym(String nameSynonym) {
        if (!StringUtil.isEmpty(nameSynonym)) {
            addField(FIELD_NAME_SYNONYM_NGRAM, nameSynonym);
            addField(FIELD_NAME_SYNONYM_MORPH, nameSynonym);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNameSynonym(String nameSynonym) {
        if (!StringUtil.isEmpty(nameSynonym)) {
            setField(FIELD_NAME_SYNONYM_NGRAM, nameSynonym);
            setField(FIELD_NAME_SYNONYM_MORPH, nameSynonym);
        }
    }

    /**
     * 名称マッチング文字列を取得する。<br>
     * 名称マッチング文字列が未設定の場合はnullを返却する。
     * 
     * @return 名称マッチング文字列の配列
     */
    @SuppressWarnings("unchecked")
    public String[] getNameSynonyms() {
        Object obj = getField(FIELD_NAME_SYNONYM_NGRAM);
        if (obj == null)
            return null;
        if (obj instanceof Collection) {
            Collection<String> vals = (Collection) obj;
            return vals.size() <= 0 ? null : (String[]) vals
                .toArray(new String[vals.size()]);
        }
        return (new String[] { (String) obj });
    }

    /**
     * 更新日をBizのdefault形式で保存
     * 
     * @see DateUtil#DEFAULT_DATE_FORMAT
     */
    @Override
    public void setRecordDate(Date recordDate) {
        setField(FIELD_RECORD_DATE, DateUtil.format(
            recordDate,
            DateUtil.DEFAULT_DATE_FORMAT));
    }

    /**
     * 更新日（Bizのdefault形式）を取得
     * 
     * @see DateUtil#DEFAULT_DATE_FORMAT
     */
    @Override
    public Date getRecordDate() {
        String dateStr = (String) getField(FIELD_RECORD_DATE);
        if (dateStr == null) {
            return null;
        }
        return DateUtil.parse(dateStr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFieldWithNullCheck(String s, Object obj) {
        if (obj == null) {
            return;
        } else if (obj instanceof String) {
            if (StringUtil.isEmpty((String) obj)) {
                return;
            }
        }
        addField(s, obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFieldWithNullCheck(String s, Object obj) {
        if (obj == null) {
            return;
        } else if (obj instanceof String) {
            if (StringUtil.isEmpty((String) obj)) {
                return;
            }
        }
        setField(s, obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTextWithNullCheck(String s) {
        if (!StringUtil.isEmpty(s)) {
            addText(s);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextWithNullCheck(String s) {
        if (!StringUtil.isEmpty(s)) {
            setText(s);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFunction(BizIntegralSolrConstants.SEARCH_FUNCTION function,
            String flag) {
        setField(function.field(), flag);
    }
}
