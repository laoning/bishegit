/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.accessor;

import javax.mail.Store;

import com.biz_integral.crm.extension.mail.common.Accessor;
import com.biz_integral.crm.extension.mail.common.exception.AccessorException;
import com.biz_integral.crm.extension.mail.model.MailFolder;

/**
 * メールフォルダ管理情報アクセサインタフェース
 * 
 */
public abstract interface MailFolderAccessor extends Accessor {

    /**
     * フォルダを取得します。<br>
     * 
     * @param store
     *            メールStore
     * @param folderName
     *            フォルダ名
     * @param mode
     *            書けるフラグ
     * @return フォルダ
     * @throws AccessorException
     */
    public abstract MailFolder getFolder(Store store, String folderName,
            boolean mode) throws AccessorException;

    /**
     * フォルダをオープンします。<br>
     * 
     * @param store
     *            メールStore
     * @param folderName
     *            フォルダ名
     * @param mode
     *            書けるフラグ
     * @return フォルダ
     * @throws AccessorException
     */
    public abstract MailFolder open(Store store, String folderName, boolean mode)
        throws AccessorException;

    /**
     * フォルダをクローズします。<br>
     * 
     * @param folder
     *            フォルダ
     * @throws AccessorException
     */
    public abstract void close(MailFolder folder) throws AccessorException;

    /**
     * フォルダの存在を判断します。<br>
     * 
     * @param store
     *            メールStore
     * @param folderName
     *            フォルダ名
     * @return 存在かどうか
     * @throws AccessorException
     */
    public abstract boolean exists(Store store, String folderName)
        throws AccessorException;
}
