/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.types;

/**
 * バッチのクローリング種別を管理します。
 */
public enum BatchCrawlingType {

    /** 差分作成 */
    DELTA,
    /** 再作成 */
    REINDEXING

}
