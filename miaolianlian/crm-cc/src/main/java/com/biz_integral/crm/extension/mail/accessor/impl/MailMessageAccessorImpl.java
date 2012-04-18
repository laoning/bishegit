/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.accessor.impl;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.UIDFolder;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;

import org.seasar.framework.container.SingletonS2Container;

import com.biz_integral.crm.extension.mail.accessor.MailMessageAccessor;
import com.biz_integral.crm.extension.mail.common.Model;
import com.biz_integral.crm.extension.mail.common.exception.AccessorException;
import com.biz_integral.crm.extension.mail.datasource.ByteArrayDataSource;
import com.biz_integral.crm.extension.mail.datasource.CorrectJISDataSource;
import com.biz_integral.crm.extension.mail.datasource.CorrectJISHTMLDataSource;
import com.biz_integral.crm.extension.mail.datasource.MDNDataSource;
import com.biz_integral.crm.extension.mail.datasource.UTF8DataSource;
import com.biz_integral.crm.extension.mail.model.MailContent;
import com.biz_integral.crm.extension.mail.model.MailFolder;
import com.biz_integral.crm.extension.mail.model.MailMessage;
import com.biz_integral.crm.extension.mail.model.MailSettings;
import com.biz_integral.crm.extension.mail.model.MailSysInfo;
import com.biz_integral.crm.extension.mail.util.MailLog;
import com.biz_integral.crm.extension.mail.util.Utility;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.sun.mail.imap.IMAPFolder;

/**
 * メールメッセージのアクセサ
 * 
 */
public class MailMessageAccessorImpl implements MailMessageAccessor {

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
        contextContainer =
            SingletonS2Container.getComponent(ContextContainer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMail(MailMessage message, Store store, boolean debug,
            String folderName) throws AccessorException {

        // アプリ情報の取得
        String charset = message.getCharset();
        if (charset == null) {
            charset = MailSysInfo.getCharset();
        }
        String version = MailSysInfo.getVersion();

        MimeMessage msg = message.getMime();
        MailContent content = message.getContent();

        // fromヘッダのセット
        try {
            String fromName =
                Utility.getEncodedWord(serverInfo.getFromName(), message
                    .getLocale(), message.getCharset());
            InternetAddress from =
                new InternetAddress(serverInfo.getFromAddr(), fromName);
            msg.setFrom(from);

            // X-Mailerと送信日付のセット
            msg.setHeader("X-Mailer", "intra-mart webmail "
                + version
                + " sendMail Module");
            msg.setSentDate(new Date());

            boolean isHTML = !(content.getHtml().equals(""));
            boolean isAttachment = (content.getAttachmentList().size() != 0);

            if (!isHTML && !isAttachment) {
                // テキストメール
                DataSource ds =
                    new CorrectJISDataSource(content.getBody(), message
                        .getLocale(), message.getCharset());
                DataHandler dh = new DataHandler(ds);

                String contentEncoding =
                    Utility.getContentEncoding(message.getLocale());

                if (message.getCharset() != null) {
                    String contentEncode =
                        MailSysInfo.getMailMimeEncoding(message.getCharset());
                    if (contentEncode != null) {
                        if (contentEncode.equalsIgnoreCase("B")) {
                            contentEncoding = "Base64";
                        } else if (contentEncode.equalsIgnoreCase("Q")) {
                            contentEncoding = "Quoted-Printable";
                        }
                    }

                }

                msg.setDataHandler(dh);
                msg.setHeader("Content-Transfer-Encoding", contentEncoding);
            } else {
                // マルチパートメール
                int i;
                String contentEncoding =
                    Utility.getContentEncoding(message.getLocale());
                if (message.getCharset() != null) {
                    String contentEncode =
                        MailSysInfo.getMailMimeEncoding(message.getCharset());
                    if (contentEncode != null) {
                        if (contentEncode.equalsIgnoreCase("B")) {
                            contentEncoding = "Base64";
                        } else if (contentEncode.equalsIgnoreCase("Q")) {
                            contentEncoding = "Quoted-Printable";
                        }
                    }

                }
                MimeMultipart mp = new MimeMultipart();

                if (isHTML && isAttachment) {
                    // 添付ファイル付HTMLメールの場合
                    mp = new MimeMultipart();
                    mp.setSubType("mixed");

                    // alternativeパートをくっつっける
                    MimeMultipart altMp =
                        getAlternativePart(
                            content.getBody(),
                            content.getHtml(),
                            message.getLocale(),
                            message.getCharset());

                    MimeBodyPart altBp = new MimeBodyPart();
                    altBp.setContent(altMp);

                    mp.addBodyPart(altBp);
                    // 添付ファイルをくっつける
                    for (i = 0; i < content.getAttachmentList().size(); i++) {
                        MimeBodyPart attachmentPart = new MimeBodyPart();

                        HashMap<String, String> hm =
                            content.getAttachmentList().get(i);
                        String fname = (String) hm.get("name");
                        String fdata = (String) hm.get("contents");
                        byte[] bdata = fdata.getBytes("8859_1");

                        // バイト配列用のDataHandler作成
                        DataSource ds =
                            new ByteArrayDataSource(bdata, fname, message
                                .getLocale(), message.getCharset());
                        DataHandler dh = new DataHandler(ds);

                        attachmentPart.setDataHandler(dh);
                        attachmentPart.setHeader(
                            "Content-Transfer-Encoding",
                            "base64");

                        // JavaMailのfilenameのセットがおかしい
                        String disposition = "attachment;\r\n";
                        disposition += " filename=\"" + ds.getName() + "\"";
                        attachmentPart.setDisposition(disposition);

                        // JavaMailのnameのセットがおかしい
                        String contentType = ds.getContentType() + ";\r\n";
                        contentType += " name=\"" + ds.getName() + "\"";

                        attachmentPart.setHeader("Content-Type", contentType);

                        mp.addBodyPart(attachmentPart);
                    }
                } else if (isHTML && !isAttachment) {
                    // 添付ファイル無しのHTMLメールの場合
                    mp =
                        getAlternativePart(
                            content.getBody(),
                            content.getHtml(),
                            message.getLocale(),
                            message.getCharset());
                } else if (!isHTML && isAttachment) {
                    // 添付ファイルありのテキストメールの場合
                    mp = new MimeMultipart();
                    mp.setSubType("mixed");

                    // テキスト部をくっつける
                    DataSource textDs =
                        new CorrectJISDataSource(content.getBody(), message
                            .getLocale(), message.getCharset());
                    DataHandler textDh = new DataHandler(textDs);

                    MimeBodyPart textPart = new MimeBodyPart();
                    textPart.setHeader(
                        "Content-Transfer-Encoding",
                        contentEncoding);
                    textPart.setDataHandler(textDh);

                    mp.addBodyPart(textPart);

                    // 添付ファイルのセット
                    for (i = 0; i < content.getAttachmentList().size(); i++) {
                        MimeBodyPart attachmentPart = new MimeBodyPart();

                        HashMap<String, String> hm =
                            content.getAttachmentList().get(i);
                        String fname = (String) hm.get("name");
                        String fdata = (String) hm.get("contents");
                        byte[] bdata = fdata.getBytes("8859_1");

                        // バイト配列用のDataHandler作成
                        DataSource ds =
                            new ByteArrayDataSource(bdata, fname, message
                                .getLocale(), message.getCharset());
                        DataHandler dh = new DataHandler(ds);

                        attachmentPart.setDataHandler(dh);
                        attachmentPart.setHeader(
                            "Content-Transfer-Encoding",
                            "base64");

                        // JavaMailのfilenameのセットがおかしい
                        String disposition = "attachment;\r\n";
                        disposition += " filename=\"" + ds.getName() + "\"";
                        attachmentPart.setDisposition(disposition);

                        // JavaMailのnameのセットがおかしい
                        String contentType = ds.getContentType() + ";\r\n";
                        contentType += " name=\"" + ds.getName() + "\"";

                        attachmentPart.setHeader("Content-Type", contentType);

                        mp.addBodyPart(attachmentPart);
                    }
                }

                msg.setContent(mp);
            }

            Transport.send(msg);

        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00097",
                contextContainer.getUserContext().getLocaleID(),
                me);
        } catch (UnsupportedEncodingException uee) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00097",
                contextContainer.getUserContext().getLocaleID(),
                uee);
        }
        return true;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendErrorMail(MailMessage message) throws AccessorException {

        if (message == null) {
            return false;
        }
        try {
            Transport.send(message.getMime());

            return true;
        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00097",
                contextContainer.getUserContext().getLocaleID(),
                me);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MailMessage createErrorMessage(MailMessage orgMessage,
            String errorMessage, boolean debug) throws AccessorException {

        // 設定値チェック
        if (orgMessage == null) {
            return null;
        }

        MailMessage notiMsg = new MailMessage(serverInfo);
        notiMsg.newSMTPMessage(debug);
        MimeMessage mes = notiMsg.getMime();

        try {
            // bodyの取得
            String text = MailSysInfo.getErrorBody();
            text = replaceWord(text, orgMessage, errorMessage);

            // fromヘッダのセット,utf8に固定する
            String fromName =
                Utility.getEncodedUTF8Word(serverInfo.getFromName());

            InternetAddress from =
                new InternetAddress(serverInfo.getFromAddr(), fromName);
            mes.setFrom(from);

            // subjectのセット
            String subject = MailSysInfo.getErrorSubject();
            subject = replaceWord(subject, orgMessage, errorMessage);

            // utf8に固定する
            subject = Utility.getEncodedUTF8Word(subject);
            mes.setSubject(subject);

            InternetAddress[] ia_to =
                InternetAddress.parse(orgMessage.getFrom());

            String[] toname = new String[ia_to.length];
            String[] toaddr = new String[ia_to.length];
            // toのセット
            for (int i = 0; i < ia_to.length; i++) {
                toname[i] = ia_to[i].getPersonal();

                if (toname[i] == null) {
                    toname[i] = "";
                } else {
                    // utf8に固定する
                    toname[i] = toname[i];
                }

                toaddr[i] = ia_to[i].getAddress();

                ia_to[i] = new InternetAddress(toaddr[i], toname[i]);
            }

            mes.setRecipients(Message.RecipientType.TO, ia_to);
            mes.addHeader("References", orgMessage.getMsgID());
            mes.addHeader("In-Reply-To", orgMessage.getMsgID());
            mes.addHeader("X-Mailer", "biz-integral Mail "
                + MailSysInfo.getVersion()
                + " sendMail Module");
            mes.setSentDate(new Date());

            MimeMultipart mp =
                new MimeMultipart(
                    "report;\n\treport-type=disposition-notification");
            mp.setSubType("mixed");
            MimeBodyPart mbp1 = new MimeBodyPart();

            // 第1パート
            // utf8に固定する
            DataSource ds2 = new UTF8DataSource(text);
            DataHandler dh2 = new DataHandler(ds2);

            mbp1.setHeader("Content-Transfer-Encoding", "Base64");
            mbp1.setDataHandler(dh2);
            mp.addBodyPart(mbp1);

            MimeBodyPart mbp2 = new MimeBodyPart();
            // 第2パート
            String mode = "";
            InternetHeaders ih = new InternetHeaders();

            // 自動的な場合
            mode = "automatic-action/MDN-sent-atuomatically; ";
            mode = mode + "dispatched";

            ih.addHeader("Final-Recipient", "rfc822; "
                + serverInfo.getFromAddr());
            ih.addHeader("Original-Message-Id", orgMessage.getMsgID());
            ih.addHeader("Disposition", mode);

            DataSource ds = new MDNDataSource(ih);
            DataHandler dh = new DataHandler(ds);

            mbp2.setDataHandler(dh);

            mbp2.setDisposition(Part.INLINE);

            mp.addBodyPart(mbp2);

            mes.setContent(mp);
        } catch (Exception e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00097",
                contextContainer.getUserContext().getLocaleID(),
                e);
        }
        return notiMsg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MailMessage[] getMessages(MailFolder folder, int start, int end)
        throws AccessorException {

        Message[] mes;
        MailMessage[] messages;

        try {
            Folder fol = folder.getFolder();
            FetchProfile profile = new FetchProfile();
            if (fol instanceof IMAPFolder) {
                profile.add(IMAPFolder.FetchProfileItem.CONTENT_INFO);
                profile.add(IMAPFolder.FetchProfileItem.ENVELOPE);
                profile.add(IMAPFolder.FetchProfileItem.FLAGS);
                profile.add(UIDFolder.FetchProfileItem.UID);
                IMAPFolder mfol = (IMAPFolder) fol;
                mes = mfol.getMessages(start, end);
                mfol.fetch(mes, profile);
            } else {
                profile.add(FetchProfile.Item.CONTENT_INFO);
                profile.add(FetchProfile.Item.ENVELOPE);
                profile.add(FetchProfile.Item.FLAGS);
                profile.add(UIDFolder.FetchProfileItem.UID);

                mes = fol.getMessages(start, end);
                fol.fetch(mes, profile);
            }

        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00094",
                contextContainer.getUserContext().getLocaleID(),
                me);
        } catch (Exception e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00094",
                contextContainer.getUserContext().getLocaleID(),
                e);
        }

        messages = new MailMessage[mes.length];

        for (int i = 0; i < messages.length; i++) {
            messages[i] =
                new MailMessage((MimeMessage) mes[i], session, serverInfo);
        }

        return messages;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MailMessage getMessage(MailFolder folder, int msgno, String charset)
        throws AccessorException {
        Message mes;

        try {
            Folder fol = folder.getFolder();
            mes = fol.getMessage(msgno);
            if (mes == null) {
                throw AccessorException.throwAccessorException(
                    "E.CRM.CC.00094",
                    contextContainer.getUserContext().getLocaleID());
            }
            MailMessage message =
                new MailMessage((MimeMessage) mes, session, serverInfo);

            message.setCharset(charset);

            return message;
        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00094",
                contextContainer.getUserContext().getLocaleID(),
                me);
        } catch (Exception e) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00094",
                contextContainer.getUserContext().getLocaleID(),
                e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMessageCount(MailFolder folder) throws AccessorException {

        int num;

        try {
            Folder fol = folder.getFolder();
            num = fol.getMessageCount();
        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00096",
                contextContainer.getUserContext().getLocaleID(),
                me);
        }

        return num;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(MailMessage message, String folderName)
        throws AccessorException {

        try {
            MimeMessage mime = message.getMime();
            // 直接に削除する
            mime.setFlag(Flags.Flag.DELETED, true);

        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00099",
                contextContainer.getUserContext().getLocaleID(),
                me);
        } catch (IllegalStateException ise) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00099",
                contextContainer.getUserContext().getLocaleID(),
                ise);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MailMessage[] searchMessage(MailFolder folder, Date startDate,
            Date endDate, String mode, String[] searchTerms)
        throws AccessorException {

        Message[] mes = null, tempjmsgs;
        MailMessage[] jsmes;
        int i, j;
        SearchTerm[] search;
        ArrayList<Message> matchmessage = new ArrayList<Message>();
        String[] tempsub = new String[1];

        if (!mode.equals("subject")
            && !mode.equals("body")
            && !mode.equals("both")
            && !mode.equals("from")
            && !mode.equals("to")) {
            // 検索モードが指定する内容以外
            throw AccessorException.throwAccessorException(
                "I.CRM.CC.00015",
                contextContainer.getUserContext().getLocaleID());
        }

        try {
            Folder fol = folder.getFolder();
            if ((fol.getType() & Folder.HOLDS_MESSAGES) == 0) {
                // フォルダにはメッセージを持っていない
                throw AccessorException.throwAccessorException(
                    "I.CRM.CC.00015",
                    contextContainer.getUserContext().getLocaleID());
            }

            if (mode.equals("subject")) {
                // 件名を検索する場合
                search = new SearchTerm[2];
                search[0] = new SentDateTerm(ComparisonTerm.GE, startDate);
                search[1] = new SentDateTerm(ComparisonTerm.LE, endDate);

                tempjmsgs = fol.search(new AndTerm(search));
                for (i = 0; i < tempjmsgs.length; i++) {
                    try {
                        tempsub = tempjmsgs[i].getHeader("Subject");
                    } catch (Exception e) {
                        e.printStackTrace();
                        tempsub[0] = "";
                    }
                    if (tempsub != null && tempsub[0] != null) {
                        // 件名がある場合、デコードする
                        try {
                            tempsub[0] = Utility.jis_detect(tempsub[0]);
                            tempsub[0] = Utility.decode(tempsub[0], null);
                        } catch (Exception e) {
                            new MailLog().write(e, serverInfo);
                        }
                        // 検索条件と比較する
                        for (j = 0; j < searchTerms.length; j++) {
                            if (tempsub[0].indexOf(searchTerms[j]) != -1
                                && matchmessage.indexOf(tempjmsgs[i]) == -1) {
                                matchmessage.add(tempjmsgs[i]);
                            }
                        }
                    }
                }

                mes = new Message[matchmessage.size()];

                for (i = 0; i < mes.length; i++) {
                    mes[i] = (Message) matchmessage.get(i);
                }
            } else if (mode.equals("body")) {
                // 本文を検索する場合
                search = new SearchTerm[searchTerms.length + 2];
                for (i = 0; i < searchTerms.length; i++) {
                    search[i] = new BodyTerm(searchTerms[i]);
                }
                search[searchTerms.length] =
                    new SentDateTerm(ComparisonTerm.GE, startDate);
                search[searchTerms.length + 1] =
                    new SentDateTerm(ComparisonTerm.LE, endDate);

                mes = fol.search(new AndTerm(search));
            } else if (mode.equals("from")) {
                // 差出人を検索する場合
                search = new SearchTerm[2];
                search[0] = new SentDateTerm(ComparisonTerm.GE, startDate);
                search[1] = new SentDateTerm(ComparisonTerm.LE, endDate);

                tempjmsgs = fol.search(new AndTerm(search));
                for (i = 0; i < tempjmsgs.length; i++) {
                    try {
                        tempsub = tempjmsgs[i].getHeader("From");
                    } catch (Exception e) {
                        e.printStackTrace();
                        tempsub[0] = "";
                    }
                    if (tempsub != null && tempsub[0] != null) {
                        // 差出人の名前がある場合、デコードする
                        try {
                            tempsub[0] = Utility.jis_detect(tempsub[0]);
                            tempsub[0] = Utility.decode(tempsub[0], null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // 検索条件と比較する
                        for (j = 0; j < searchTerms.length; j++) {
                            if (tempsub[0].indexOf(searchTerms[j]) != -1
                                && matchmessage.indexOf(tempjmsgs[i]) == -1) {
                                matchmessage.add(tempjmsgs[i]);
                            }
                        }
                    }
                }

                mes = new Message[matchmessage.size()];

                for (i = 0; i < mes.length; i++) {
                    mes[i] = (Message) matchmessage.get(i);
                }
            } else if (mode.equals("to")) {
                // 差出人を検索する場合
                search = new SearchTerm[2];
                search[0] = new SentDateTerm(ComparisonTerm.GE, startDate);
                search[1] = new SentDateTerm(ComparisonTerm.LE, endDate);

                tempjmsgs = fol.search(new AndTerm(search));
                for (i = 0; i < tempjmsgs.length; i++) {
                    try {
                        tempsub = tempjmsgs[i].getHeader("To");
                    } catch (Exception e) {
                        e.printStackTrace();
                        tempsub[0] = "";
                    }
                    if (tempsub != null && tempsub[0] != null) {
                        // 差出人の名前がある場合、デコードする
                        try {
                            tempsub[0] = Utility.jis_detect(tempsub[0]);
                            tempsub[0] = Utility.decode(tempsub[0], null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // 検索条件と比較する
                        for (j = 0; j < searchTerms.length; j++) {
                            if (tempsub[0].indexOf(searchTerms[j]) != -1
                                && matchmessage.indexOf(tempjmsgs[i]) == -1) {
                                matchmessage.add(tempjmsgs[i]);
                            }
                        }
                    }
                }

                mes = new Message[matchmessage.size()];

                for (i = 0; i < mes.length; i++) {
                    mes[i] = (Message) matchmessage.get(i);
                }
            }

            jsmes = new MailMessage[mes.length];
            // メッセージを戻す
            for (i = 0; i < mes.length; i++) {
                jsmes[i] =
                    new MailMessage((MimeMessage) mes[i], session, serverInfo);
            }
            return jsmes;
        } catch (MessagingException me) {
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00095",
                contextContainer.getUserContext().getLocaleID(),
                me);
        } catch (Exception e) {
            if (e instanceof AccessorException) {
                if (((AccessorException) e).getErrorMessage() != null) {
                    throw (AccessorException) e;
                }
            }
            throw AccessorException.throwAccessorException(
                "E.CRM.CC.00095",
                contextContainer.getUserContext().getLocaleID(),
                e);
        }
    }

    private String replaceWord(String data, MailMessage orgMsg, String errMsg)
        throws ParseException {

        int idx;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:dd");
        String dispositionDate = format.format(new Date());

        String[] key =
            { "%subject%", "%from%", "%to%", "%send_date%", "%date%", "%error%" };
        String[] rep =
            {
                orgMsg.getSubject(),
                orgMsg.getFrom(),
                orgMsg.getTo(),
                orgMsg.getSentDate(),
                dispositionDate,
                errMsg };

        for (int i = 0; i < key.length; i++) {
            idx = data.indexOf(key[i]);

            if (idx != -1) {
                data =
                    data.substring(0, idx)
                        + rep[i]
                        + replaceWord(
                            data.substring(idx + key[i].length()),
                            orgMsg,
                            errMsg);
            }
        }

        return data;
    }

    private MimeMultipart getAlternativePart(String text, String html,
            Locale local, String encoding) throws MessagingException {
        MimeMultipart mp = new MimeMultipart();
        String contentEncoding = Utility.getContentEncoding(local);

        if (encoding != null) {
            String contentEncode = MailSysInfo.getMailMimeEncoding(encoding);
            if (contentEncode != null) {
                if (contentEncode.equalsIgnoreCase("B")) {
                    contentEncoding = "Base64";
                } else if (contentEncode.equalsIgnoreCase("Q")) {
                    contentEncoding = "Quoted-Printable";
                }
            }

        }
        mp.setSubType("alternative");

        // テキスト部をくっつける
        DataSource textDs = new CorrectJISDataSource(text, local, encoding);
        DataHandler textDh = new DataHandler(textDs);

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setHeader("Content-Transfer-Encoding", contentEncoding);
        textPart.setDataHandler(textDh);

        mp.addBodyPart(textPart);

        // HTML部をくっつける
        DataSource htmlSource =
            new CorrectJISHTMLDataSource(html, local, encoding);
        DataHandler htmlHandler = new DataHandler(htmlSource);

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setHeader("Content-Transfer-Encoding", contentEncoding);
        htmlPart.setDataHandler(htmlHandler);

        mp.addBodyPart(htmlPart);

        return mp;
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
