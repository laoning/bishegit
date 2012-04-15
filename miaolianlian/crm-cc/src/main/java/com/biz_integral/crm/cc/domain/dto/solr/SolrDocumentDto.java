/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto.solr;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeSet;

import com.biz_integral.crm.extension.solr.model.BizIntegralInputDocument;

/**
 * 文書作成時のデータを格納するDTO
 */
public class SolrDocumentDto implements Serializable {

    /** デフォルト・シリアルバージョンID */
    private static final long serialVersionUID = 1L;

    /** ソーラの登録用文書 */
    public BizIntegralInputDocument solrDocument;
    /** 動的文書種別 */
    public Map<String, String> activeDocumentType;
    /** 更新記録セット */
    public TreeSet<Date> recordSet;
}
