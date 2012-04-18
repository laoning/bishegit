/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto.solr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * クローラワークDTO
 */
public class CrawlerWorkDto implements Serializable {

    /** デフォルト・シリアルバージョンUID */
    private static final long serialVersionUID = 1L;
    /** 会社コードリスト */
    public List<String> companyCdList;
    /** 開始日付 */
    public Date startDate;
    /** 終了日付 */
    public Date endDate;
    /** 開始日付（時分秒なし） {@link CrawlerWorkDto#startDate}と同日で時分秒が未設定 */
    public Date simpleStartDate;
    /** 終了日付（時分秒なし） {@link CrawlerWorkDto#endDate}+1日で時分秒が未設定 */
    public Date simpleEndDate;

    /**
     * コンストラクタ
     */
    public CrawlerWorkDto() {
        companyCdList = new ArrayList<String>();
    }

}
