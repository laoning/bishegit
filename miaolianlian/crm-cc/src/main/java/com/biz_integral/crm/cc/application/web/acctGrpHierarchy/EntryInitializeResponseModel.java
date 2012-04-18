/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrpHierarchy;

import java.util.ArrayList;
import java.util.List;

import com.biz_integral.crm.cc.domain.dto.NodeDto;

/**
 * 分析階層登録画面の初期表示情報を保持するレスポンスモデル
 */
public class EntryInitializeResponseModel {

    /**
     * ツリーノードオブジェクトのリスト
     */
    public List<NodeDto> treeNodeList = new ArrayList<NodeDto>();

    public String fileName;

}
