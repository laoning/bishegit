/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.common;

import com.biz_integral.crm.extension.mail.common.exception.AccessorException;

/**
 * モデル操作インタフェース
 */
public interface Accessor {

    /**
     * ログイングループＩＤをセットします。<br>
     * データベースの接続先として使用されます。<br>
     * 指定がない場合はログイン中のログイングループＩＤが使用されます。
     * 
     * @param groupId
     *            ログイングループＩＤ。
     */
    public void setLoginGroupId(String groupId);

    /**
     * モデルを更新します。<br>
     * 主キーが既に登録済みの場合は「更新データ」として扱い、<br>
     * 未登録の場合は「新規データ」として扱います。
     * 
     * @param model
     *            モデルオブジェクト
     * @throws AccessorException
     */
    public void update(Model model) throws AccessorException;

    /**
     * モデルを削除します。
     * 
     * @param model
     *            モデルオブジェクト
     * @throws AccessorException
     */
    public void delete(Model model) throws AccessorException;
}
