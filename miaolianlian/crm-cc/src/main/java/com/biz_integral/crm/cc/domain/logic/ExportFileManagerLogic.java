/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

/**
 * 非同期処理登録ロジックです。
 */
public interface ExportFileManagerLogic<T> {
    /**
     * 非同期処理登録です。
     * 
     * @param dto
     *            検索条件
     * @param asyncTaskConfigCd
     *            非同期タスク設定コード
     * @return 非同期タスク登録ID
     */
    abstract public String register(T dto, String asyncTaskConfigCd);
}
