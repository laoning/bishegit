/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.domain.entity.CrmCcAccount;
import com.biz_integral.crm.extension.solr.model.BizIntegralInputDocument;
import com.biz_integral.foundation.core.util.DateUtil;

/**
 * 登録用の文書を作成します。
 */
public interface CrmAccountCrawlerCreateLogic {

    /**
     * Solr登録用の文書を作成します。
     * 
     * @param id
     *            文書の一意ID
     * @param nowDate
     *            現在日付 Bizのdefault形式 {@link DateUtil#DEFAULT_DATE_FORMAT}
     * @param appDocTypeList
     *            基本文書種別リスト
     * @param account
     *            アカウントモデル
     * @return 文書
     */
    public BizIntegralInputDocument createSolrDocument(String id,
            String nowDate, List<String> appDocTypeList, CrmCcAccount account);
}
