/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import com.biz_integral.crm.cc.domain.entity.CrmCcSynonymPattern;

/**
 * 類義語パターン
 */
public interface SynonymPatternLogic {

    /**
     * 類義語パターンの登録します。
     * 
     * @param synonymPattern
     *            類義語パターンエンティティ
     */
    public abstract void create(CrmCcSynonymPattern synonymPattern);

    /**
     * 類義語ファイルの出力を行います。
     * 
     */
    public abstract void outputFile();
}
