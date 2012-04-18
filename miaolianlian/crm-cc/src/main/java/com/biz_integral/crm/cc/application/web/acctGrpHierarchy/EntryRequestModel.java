/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrpHierarchy;

import static com.biz_integral.foundation.core.util.CollectionsUtil.newArrayList;

import java.util.List;

/**
 * アカウントグループ階層レストボンズモデル。
 * 
 */
public class EntryRequestModel {

    /**
     * アカウントグループ階層パス
     */
    public String path;

    /**
     * アカウントグループ階層パス
     */
    public String targetPath;

    /**
     * アカウントグループコード
     */
    public String grpCd;

    /**
     * 追加先
     */
    public String addHierarchy;

    /**
     * 追加先がTOPフラグ（最上位）
     */
    public String isTop = "0";

    /**
     * 追加先がルートフラグ
     */
    public String isRoot = "0";

    /**
     * 追加するグループリスト
     */
    public List<EntryAddRequestModel> addGrpList = newArrayList();
}
