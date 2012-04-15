/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage;

import java.util.ArrayList;
import java.util.List;

import com.biz_integral.service.persistence.pager.PagerSupport;

/**
 * パラメータ検索画面の検索ボタン押下レスポンスモデル
 */
public class SearchFilterResponseModel extends PagerSupport {

    /**
     * 初期設定パラメータリスト
     */
    public List<GrdParameterListResponseModel> grdParameterListDefaults =
        new ArrayList<GrdParameterListResponseModel>();

    /**
     * 表示・コントロールパラメータリスト
     */
    public List<GrdParameterListResponseModel> grdParameterListDisplayControls =
        new ArrayList<GrdParameterListResponseModel>();

    /**
     * データ件数
     */
    public String total;

}