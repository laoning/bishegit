/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.accessor.impl;

import javax.annotation.Resource;
import javax.mail.Folder;
import javax.mail.FolderNotFoundException;
import javax.mail.MessagingException;
import javax.mail.Store;

import com.biz_integral.crm.extension.mail.accessor.MailFolderAccessor;
import com.biz_integral.crm.extension.mail.common.Model;
import com.biz_integral.crm.extension.mail.common.exception.AccessorException;
import com.biz_integral.crm.extension.mail.model.MailFolder;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * メールフォルダ管理情報アクセサ
 * 
 */
public class MailFolderAccessorImpl implements MailFolderAccessor {

    /**
     * {@link ContextContainer}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public MailFolder getFolder(Store store, String folderName, boolean mode)
        throws AccessorException {
        MailFolder mailFolder;
        try {
            // ネームスペースを取得
            String namespace = MailFolder.getNamespace(store);
            Folder fol;
            String fullName =
                MailFolder.getNameSpaceFolderName(namespace, folderName, store);
            // フォルダを取得
            fol = store.getFolder(fullName);
            if (fol != null) {
                // フォルダがある
                mailFolder = new MailFolder(fol);
                mailFolder.setWritable(mode);
            } else {
                return null;
            }
        } catch (FolderNotFoundException e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00093",
                contextContainer.getUserContext().getLocaleID(),
                e);
        } catch (IllegalStateException e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00093",
                contextContainer.getUserContext().getLocaleID(),
                e);
        } catch (MessagingException e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00093",
                contextContainer.getUserContext().getLocaleID(),
                e);
        } catch (Exception e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00093",
                contextContainer.getUserContext().getLocaleID(),
                e);
        }

        return mailFolder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MailFolder open(Store store, String folderName, boolean mode)
        throws AccessorException {
        // フォルダのオープン
        MailFolder mailFolder;
        try {
            // ネームスペースを取得
            String namespace = MailFolder.getNamespace(store);
            Folder fol;
            String fullName =
                MailFolder.getNameSpaceFolderName(namespace, folderName, store);
            // フォルダを取得
            fol = store.getFolder(fullName);
            if (fol != null) {
                // フォルダがある
                mailFolder = new MailFolder(fol);
                mailFolder.setWritable(mode);
                fol.open(mailFolder.getWritable());
            } else {
                return null;
            }
        } catch (FolderNotFoundException e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00093",
                contextContainer.getUserContext().getLocaleID(),
                e);
        } catch (IllegalStateException e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00093",
                contextContainer.getUserContext().getLocaleID(),
                e);
        } catch (MessagingException e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00093",
                contextContainer.getUserContext().getLocaleID(),
                e);
        } catch (Exception e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00093",
                contextContainer.getUserContext().getLocaleID(),
                e);
        }

        return mailFolder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close(MailFolder folder) throws AccessorException {
        try {
            // フォルダ変更内容確定(imap)
            Folder fol = folder.getFolder();
            fol.close(true);
        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00100",
                contextContainer.getUserContext().getLocaleID(),
                me);
        } catch (Exception e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00100",
                contextContainer.getUserContext().getLocaleID(),
                e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(Store store, String folderName)
        throws AccessorException {
        try {
            // ネームスペースを取得
            String namespace = MailFolder.getNamespace(store);
            Folder fol;
            String fullName =
                MailFolder.getNameSpaceFolderName(namespace, folderName, store);
            // フォルダを取得
            fol = store.getFolder(fullName);
            return fol.exists();
        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00093",
                contextContainer.getUserContext().getLocaleID(),
                me);
        } catch (Exception e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00093",
                contextContainer.getUserContext().getLocaleID(),
                e);
        }
    }

    /**
     * 使用しません。
     */
    public void setLoginGroupId(String arg0) {
    }

    /**
     * 使用しません。
     */
    public void update(Model arg0) throws AccessorException {
    }

    /**
     * 使用しません。
     */
    public void delete(Model arg0) throws AccessorException {
    }
}
