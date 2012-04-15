/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.accessor.impl;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.UIDFolder;
import javax.mail.internet.MimeMessage;

import com.biz_integral.crm.extension.mail.accessor.IMAPMailMessageAccessor;
import com.biz_integral.crm.extension.mail.common.Model;
import com.biz_integral.crm.extension.mail.common.exception.AccessorException;
import com.biz_integral.crm.extension.mail.model.MailFolder;
import com.biz_integral.crm.extension.mail.model.MailMessage;
import com.biz_integral.crm.extension.mail.model.MailSettings;
import com.biz_integral.foundation.core.context.ContextContainer;

/**
 * IMAPメールメッセージのアクセサ
 * 
 */
public class IMAPMailMessageAccessorImpl implements IMAPMailMessageAccessor {

    /**
     * {@link ContextContainer}
     */
    @Resource
    protected ContextContainer contextContainer;

    protected MailSettings serverInfo;
    protected Session session;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(Session session, MailSettings serverInfo) {
        this.serverInfo = serverInfo;
        this.session = session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MailMessage[] getUIDMessages(MailFolder folder, String[] uid_arr)
        throws AccessorException {

        long[] uid;
        Message[] mes;
        MailMessage[] jsmes;
        UIDFolder ufol = (UIDFolder) folder.getIMAPFolder();
        uid = new long[uid_arr.length];
        jsmes = new MailMessage[uid_arr.length];

        try {
            for (int i = 0; i < uid_arr.length; i++) {
                uid[i] = Long.parseLong(uid_arr[i]);
            }

            mes = ufol.getMessagesByUID(uid);

            for (int i = 0; i < mes.length; i++) {
                if (mes[i] != null) {
                    jsmes[i] =
                        new MailMessage(
                            (MimeMessage) mes[i],
                            session,
                            serverInfo);
                } else {
                    jsmes[i] = null;
                }
            }

        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00094",
                contextContainer.getUserContext().getLocaleID(),
                me);
        }

        return jsmes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MailMessage getUIDMessage(MailFolder folder, String uids,
            String charset) throws AccessorException {

        long uid;
        UIDFolder ufol;
        MimeMessage mime;

        uid = (new Long(uids)).longValue();
        ufol = (UIDFolder) folder.getIMAPFolder();

        try {
            mime = ((MimeMessage) ufol.getMessageByUID(uid));

            if (mime == null) {
                throw AccessorException.throwAccessorException(
                    "E.CRM.CC.00094",
                    contextContainer.getUserContext().getLocaleID());
            }
            MailMessage message = new MailMessage(mime, session, serverInfo);
            message.setCharset(charset);
            return message;
        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00094",
                contextContainer.getUserContext().getLocaleID(),
                me);
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
