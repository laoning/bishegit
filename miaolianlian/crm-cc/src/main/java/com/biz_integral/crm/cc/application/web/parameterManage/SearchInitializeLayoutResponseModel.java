/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.parameterManage;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import com.biz_integral.extension.mvc.maskat.beans.KeyValueBean;

/**
 * CRMパラメータ検索画面初期化結果モデルです。
 */
public class SearchInitializeLayoutResponseModel {

    /**
     * 対象モジュールリスト
     */
    public List<KeyValueBean> targetModules = newArrayList();

    /**
     * 初期設定パラメータリスト
     */
    public List<GrdParameterListResponseModel> grdParameterListDefaults;

    /**
     * 表示・コントロールパラメータリスト
     */
    public List<GrdParameterListResponseModel> grdParameterListDisplayControls;

}