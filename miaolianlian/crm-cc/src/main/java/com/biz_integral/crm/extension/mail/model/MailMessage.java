/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.model;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.UIDFolder;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MailDateFormat;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import jp.co.intra_mart.foundation.security.SystemManager;

import com.biz_integral.crm.extension.mail.common.Model;
import com.biz_integral.crm.extension.mail.util.MailLog;
import com.biz_integral.crm.extension.mail.util.Utility;
import com.sun.mail.pop3.POP3Folder;

/**
 * メールメッセージ・ヘッダ
 * 
 */
public class MailMessage implements Model {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -5639593020514104391L;

    private MimeMessage mime;
    private Locale locale = SystemManager.getInstance().getDefaultLocale();
    private String charset = null;
    private MailSettings serverInfo;
    private Session session = null;
    private boolean SMTPMessage = false;
    private MailContent jsc = null;

    /**
     * 登録ユーザコード
     */
    private String registUserCd;

    /**
     * 登録ユーザ名
     */
    private String registUserName;

    /**
     * 登録日
     */
    private Date registDate;

    /**
     * 更新ユーザコード
     */
    private String updateUserCd;

    /**
     * 更新ユーザ名
     */
    private String updateUserName;

    /**
     * 更新日
     */
    private Date updateDate;

    /**
     * 登録ユーザコード
     */
    private String recordUserCd;

    /**
     * コンストラクタ
     * 
     * @param serverInfo
     *            メールサーバ設定
     */
    public MailMessage(MailSettings serverInfo) {
        this.serverInfo = serverInfo;
        mime = null;
    }

    /**
     * コンストラクタ
     * 
     * @param mime
     *            メールメッセージ
     * @param session
     *            メールセッション
     * @param serverInfo
     *            メールサーバ設定
     */
    public MailMessage(MimeMessage mime, Session session,
            MailSettings serverInfo) {
        this.mime = mime;
        this.serverInfo = serverInfo;
        this.session = session;
    }

    /**
     * 新しいメッセージを作成します。
     * 
     * @param debug
     *            　デバッグモード
     * 
     */
    public void newSMTPMessage(boolean debug) {
        // セッションを開く
        Properties props = new Properties();

        props.put("mail.smtp.host", serverInfo.getSMTPHost());
        props.put("mail.smtp.port", String.valueOf(serverInfo.getSMTPPort()));
        // Message-ID補正
        props.put("mail.from", serverInfo.getFromAddr());

        Authenticator auth = serverInfo.getSMTPAuth();

        if (auth != null) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }

        session = Session.getInstance(props, auth);
        session.setDebug(debug);
        mime = new MimeMessage(session);
        SMTPMessage = true;
    }

    /**
     * Toヘッダを取得します。
     * 
     * @return Toアドレス
     */
    public String getTo() {

        return getHeader("To", "");
    }

    public String getBCC() {
        String temp = null;
        StringBuffer mailaddr = new StringBuffer();
        InternetAddress[] bcc;
        try {
            bcc =
                (InternetAddress[]) mime
                    .getRecipients(Message.RecipientType.BCC);
            if (bcc != null) {

                for (int i = 0; i < bcc.length; i++) {
                    String email = bcc[i].getAddress();
                    if (email == null) {
                        email = "";
                    } else {
                        if (charset != null && (!charset.equals(""))) {
                            email = Utility.decode(email, charset);
                        } else {
                            email = Utility.decode(email, null);
                        }
                        Pattern pattern = Pattern.compile("\\\\\"");
                        Matcher matcher = pattern.matcher(email);
                        if (matcher.find()) {
                            email = matcher.replaceAll("\"");
                        }
                        pattern = Pattern.compile("\\\\\\\\");
                        matcher = pattern.matcher(email);
                        if (matcher.find()) {
                            email = matcher.replaceAll("\\\\");
                        }

                    }
                    String personal = bcc[i].getPersonal();

                    if (personal == null) {
                        personal = "";
                    } else {

                        if (charset != null && (!charset.equals(""))) {
                            personal = Utility.decode(personal, charset);
                        } else {
                            personal = Utility.decode(personal, null);
                        }
                        Pattern pattern = Pattern.compile("\\\\\"");
                        Matcher matcher = pattern.matcher(personal);
                        if (matcher.find()) {
                            personal = matcher.replaceAll("\"");
                        }
                        pattern = Pattern.compile("\\\\\\\\");
                        matcher = pattern.matcher(personal);
                        if (matcher.find()) {
                            personal = matcher.replaceAll("\\\\");
                        }
                    }
                    mailaddr.append("\"" + personal + "\" ");
                    mailaddr.append("<");
                    mailaddr.append(email);
                    mailaddr.append(">");
                    mailaddr.append(",");

                }
                if (mailaddr.length() > 0)
                    mailaddr.deleteCharAt(mailaddr.length() - 1);
                temp = mailaddr.toString();

            }

        } catch (MessagingException e) {

        }

        return temp;
    }

    /**
     * CCヘッダを取得します。
     * 
     * @return CCアドレス
     */
    public String getCC() {
        return getHeader("Cc", "");
    }

    /**
     * 件名を取得します。
     * 
     * @return 件名
     */
    public String getSubject() {
        return getHeader("Subject", "");
    }

    /**
     * 優先度を取得します。
     * 
     * @return 優先度フラグ
     */
    public boolean getHighPriorty() {
        if (getHeader("X-Priority").equalsIgnoreCase("1")
            || getHeader("X-MSMail-Priority").equalsIgnoreCase("high")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 開封通知アドレスを取得します。
     * 
     * @return 開封通知アドレス
     */
    public String getNotification() {
        try {
            if (!mime.isSet(Flags.Flag.SEEN)) {
                // メールが未読場合
                return getHeader("Disposition-Notification-To", "");
            } else {
                return "";
            }
        } catch (MessagingException e) {
            new MailLog().write(e);
            return "";
        }
    }

    /**
     * 開封通知フラグを取得します。
     * 
     * @return 開封通知フラグ
     */
    public boolean isNotification() {
        String s;

        s = getNotification();

        if (s != null && !s.equals("")) {
            // 開封通知がある場合
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返信アドレスを取得します。
     * 
     * @return 返信アドレス
     */
    public String getReplyTo() {
        return getHeader("Reply-To", "");
    }

    /**
     * Referencesを取得します。
     * 
     * @return References
     */
    public String getReferences() {
        return getHeader("References", "");
    }

    /**
     * Localeを取得します。
     * 
     * @return Locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * charsetを取得します。
     * 
     * @return charset
     */
    public String getCharset() {
        if (charset == null || charset.equals("")) {
            return null;
        }
        return charset;
    }

    /**
     * Fromを取得します。
     * 
     * @return From
     */
    public String getFrom() {
        return getHeader("From", "");
    }

    /**
     * 未読既読を取得します。
     * 
     * @return 未読既読
     */
    public boolean getIsSeen() {
        try {
            return mime.isSet(Flags.Flag.SEEN);
        } catch (MessagingException e) {
            new MailLog().write(e);
            return false;
        }
    }

    /**
     * メッセージIDの取得します。
     * 
     * @return メッセージID
     */
    public String getMsgID() {
        return getHeader("Message-id", "");
    }

    /**
     * メッセージNoの取得します。
     * 
     * @return メッセージNo
     */
    public int getMsgNo() {
        return mime.getMessageNumber();
    }

    /**
     * UIDの取得します。
     * 
     * @return UID
     */
    public String getUID() {
        Folder fol = mime.getFolder();

        try {
            if (fol instanceof POP3Folder) {
                POP3Folder pfol = (POP3Folder) fol;
                // ほんとはUIDLです。だから、String。
                String uid = pfol.getUID(mime);

                if (uid != null) {
                    return uid;
                } else {
                    return null;
                }
            } else if (fol instanceof UIDFolder) {
                // IMAPフォルダ
                UIDFolder ufol = (UIDFolder) fol;
                long uid = ufol.getUID(mime);

                return String.valueOf(uid);
            } else {
                return null;
            }
        } catch (MessagingException e) {
            new MailLog().write(e);
            return null;
        }
    }

    /**
     * サイズの取得します。
     * 
     * @return サイズ
     */
    public int getSize() {
        try {
            return mime.getSize();
        } catch (MessagingException e) {
            new MailLog().write(e);
            return 0;
        }
    }

    /**
     * 送信日付の取得します。
     * 
     * @return 日付
     */
    public String getSentDate() {
        try {
            String[] strDate = mime.getHeader("Date");
            if (strDate != null && strDate.length != 0 && strDate[0] != null) {
                // 日付がある
                MailDateFormat format = new MailDateFormat();
                Date date = format.parse(strDate[0]);
                SimpleDateFormat sf =
                    new SimpleDateFormat("yyyy/MM/dd|HH:mm:ss");

                if (date != null) {
                    String strSentDate = sf.format(date);
                    return strSentDate;
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } catch (MessagingException e) {
            new MailLog().write(e);
            return "";
        } catch (ParseException e) {
            new MailLog().write(e);
            return "";
        }
    }

    /**
     * multipartかどうかの取得します。
     * 
     * @return multipartかどうか
     */
    public boolean isMultipart() {
        try {
            String[] type = mime.getHeader("Content-Type");

            if (type != null) {
                if (type.length > 0) {
                    // タイプがある場合
                    type[0] = type[0].toLowerCase();
                }

                boolean isMultipart =
                    mime.isMimeType("multipart/*")
                        || type[0].startsWith("multipart/");
                return new Boolean(isMultipart).booleanValue();
            } else {
                return false;
            }
        } catch (MessagingException e) {
            new MailLog().write(e);
            return false;
        } catch (Exception ex) {
            new MailLog().write(ex);
            return false;
        }
    }

    /**
     * 添付ファイルかどうかの取得します。
     * 
     * @return 添付ファイルかどうか
     */
    public boolean hasAttachment() {
        try {
            String[] type = mime.getHeader("Content-Type");

            if (type != null) {
                if (type.length > 0) {
                    // タイプがある場合
                    type[0] = type[0].toLowerCase();
                }

                if (type[0].startsWith("multipart/alternative")
                    || type[0].startsWith("multipart/related"))
                    return false;
                boolean isMultipart =
                    (mime.isMimeType("multipart/*"))
                        || (type[0].startsWith("multipart/"));
                return new Boolean(isMultipart).booleanValue();
            } else {
                return false;
            }
        } catch (MessagingException e) {
            new MailLog().write(e);
            return false;
        } catch (Exception ex) {
            new MailLog().write(ex);
            return false;
        }
    }

    /**
     * ReplyAllの取得します。
     * 
     * @return ReplyAllアドレス
     */
    public HashMap<String, String> getReplyAll(String email) {
        HashMap<String, String> ht = new HashMap<String, String>();

        InternetAddress[] reply_to;
        InternetAddress[] from;
        InternetAddress[] to;
        InternetAddress[] cc;

        reply_to = getInternetAddress("Reply-To");
        from = getInternetAddress("From");
        to = getInternetAddress("To");
        cc = getInternetAddress("Cc");

        String replyString = getReplyAddress(reply_to, email);
        String fromString = getReplyAddress(from, email);

        String resultTo = "";

        if (!replyString.equals("")) {
            resultTo = replyString;
        } else if (!fromString.equals("")) {
            resultTo = fromString;
        }

        ht.put("to", resultTo);

        String toString = getReplyAddress(to, email);
        String ccString = getReplyAddress(cc, email);

        String resultCc = "";

        if (toString.equals("") || ccString.equals("")) {
            resultCc = toString + ccString;
        } else {
            resultCc = toString + " , " + ccString;
        }

        ht.put("cc", resultCc);

        return ht;

    }

    /**
     * コンテントを取得する
     * 
     * @return　コンテント
     */
    public MailContent getContent() {
        if (jsc == null) {
            jsc = new MailContent(mime, session, serverInfo, SMTPMessage);
            jsc.setCharset(charset);
        }
        return jsc;
    }

    /**
     * Mimeを取得する
     * 
     * @return　Mime
     */
    public MimeMessage getMime() {
        return mime;
    }

    /**
     * Toヘッダを設定します。
     * 
     * @param toAddr
     *            アドレスの配列
     */
    public void setTo(Hashtable[] toAddr) {
        InternetAddress[] addr;

        try {
            addr = getAddress(toAddr);
        } catch (Exception e) {
            new MailLog().write(e);
            return;
        }

        try {
            mime.addRecipients(Message.RecipientType.TO, addr);
        } catch (MessagingException me) {
            new MailLog().write(me, "smtp", serverInfo.getHost(), serverInfo
                .getPort(), "");
        }
    }

    /**
     * Ccヘッダを設定します。
     * 
     * @param ccAddr
     *            アドレスの配列
     */
    public void setCC(Hashtable[] ccAddr) {
        InternetAddress[] addr;

        try {
            addr = getAddress(ccAddr);
        } catch (Exception e) {
            new MailLog().write(e);
            return;
        }

        try {
            mime.addRecipients(Message.RecipientType.CC, addr);
        } catch (MessagingException me) {
            new MailLog().write(me, "smtp", serverInfo.getHost(), serverInfo
                .getPort(), "");
        }
    }

    /**
     * Bccヘッダを設定します。
     * 
     * @param bccAddr
     *            アドレスの配列
     */
    public void setBCC(Hashtable[] bccAddr) {
        InternetAddress[] addr;

        try {
            addr = getAddress(bccAddr);
        } catch (Exception e) {
            new MailLog().write(e);
            return;
        }

        try {
            mime.addRecipients(Message.RecipientType.BCC, addr);
        } catch (MessagingException me) {
            new MailLog().write(me, "smtp", serverInfo.getHost(), serverInfo
                .getPort(), "");
        }
    }

    /**
     * 件名を設定します。
     * 
     * @param sub
     *            件名
     */
    public void setSubject(String sub) {
        try {
            sub = Utility.changeSinglebyteKana(sub, charset);
            sub = Utility.getEncodedWord(sub, locale, charset);
            if ("UTF-8".equalsIgnoreCase(charset)) {
                mime.setSubject(sub, charset);
            } else {
                mime.setHeader("Subject", sub);
            }
        } catch (MessagingException me) {
            new MailLog().write(me, "smtp", serverInfo.getHost(), serverInfo
                .getPort(), "");
        }
    }

    /**
     * その他ヘッダを設定します。
     * 
     * @param name
     *            その他ヘッダ
     * @param value
     *            ヘッダ値
     */
    public void setHeader(String name, String value) {

        try {
            String cs = MailSysInfo.getCharset();
            if (charset != null) {
                cs = charset;
            }
            value =
                MimeUtility.encodeWord(value, cs, MailSysInfo.getWordEncode());
            mime.setHeader(name, value);
        } catch (MessagingException me) {
            new MailLog().write(me, "smtp", serverInfo.getHost(), serverInfo
                .getPort(), "");
        } catch (UnsupportedEncodingException uee) {
            new MailLog().write(uee, "smtp", serverInfo.getHost(), serverInfo
                .getPort(), "");
        }
    }

    /**
     * Notificationアドレスを設定します。
     */
    public void setNotification() {
        try {
            String from_name =
                Utility.changeSinglebyteKana(serverInfo.getFromName(), charset);
            from_name = Utility.getEncodedWord(from_name, this.locale, charset);
            String addrString;

            if (from_name != null && !from_name.equals("")) {
                boolean flagName = false;
                if (!from_name.startsWith("=?")) {
                    String fromSpecial[] =
                        {
                            "\"",
                            "[",
                            "]",
                            "(",
                            ")",
                            "{",
                            "}",
                            "「",
                            "」",
                            "｛",
                            "｝",
                            "（",
                            "）",
                            "”" };

                    for (int i = 0; i < fromSpecial.length; i++) {
                        if (from_name.indexOf(fromSpecial[i]) != -1) {
                            flagName = true;
                            break;
                        }
                    }
                }
                if (flagName)
                    from_name = "\"" + from_name + "\"";
                addrString = from_name + " <" + serverInfo.getFromAddr() + ">";
            } else {
                addrString = serverInfo.getFromAddr();
            }

            mime.setHeader("Disposition-Notification-To", addrString);
        } catch (MessagingException me) {
            new MailLog().write(me, "smtp", serverInfo.getHost(), serverInfo
                .getPort(), "");
        }
    }

    /**
     * InReplyToを設定します。
     * 
     * @param in_reply_to
     */
    public void setInReplyTo(String in_reply_to) {
        try {
            mime.setHeader("In-Reply-To", in_reply_to);
        } catch (MessagingException me) {
            new MailLog().write(me, "smtp", serverInfo.getHost(), serverInfo
                .getPort(), "");
        }
    }

    /**
     * Referencesを設定します。
     * 
     * @param references
     */
    public void setReferences(String references) {
        try {
            mime.setHeader("References", references);
        } catch (MessagingException me) {
            new MailLog().write(me, "smtp", serverInfo.getHost(), serverInfo
                .getPort(), "");
        }
    }

    /**
     * 未読既読を設定します。
     * 
     * @param seen
     *            未読既読
     * @throws MessagingException
     */
    public void setSeen(boolean seen) throws MessagingException {
        mime.setFlag(Flags.Flag.SEEN, seen);
    }

    /**
     * Localeを設定します。
     * 
     * @param language
     *            ローカル
     */
    public void setLocale(String language) {
        if (language == null) {
            return;
        }
        String localeString = language.trim();
        if (localeString.length() == 0) {
            return;
        }
        int index = localeString.indexOf("_");
        if (index == -1) {
            // セパレータ文字がない＝言語指定のみ
            this.locale = new Locale(localeString);
            return;
        }
        // 言語コードの特定
        String lang = localeString.substring(0, index);

        // 国コードの検索
        index = localeString.indexOf("_", index + 1);
        if (index == -1) {
            // セパレータ文字が見つからない＝バリアントがない
            String country = localeString.substring(lang.length() + 1);
            this.locale = new Locale(lang, country);
        } else {
            // 国コードの特定
            String country = localeString.substring(lang.length() + 1, index);
            // バリアントの特定
            String variant = localeString.substring(index + 1);

            this.locale = new Locale(lang, country, variant);
        }
    }

    /**
     * charsetを設定します。
     * 
     * @param charset
     *            文字コード
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * その他ヘッダを取得します。
     * 
     * @param name
     *            ヘッダ名
     * @return その他ヘッダ
     */
    public String getHeader(String name) {
        return getHeader(name, "");
    }

    private String getHeader(String name, String delimiter) {
        String value = "";

        try {

            value = mime.getHeader(name, delimiter);
            if (value != null
                && !"".equals(value)
                && !Utility.isBlankOnly(value)) {
                // ヘッダが存在場合
                value = Utility.jis_detect(value);
                if (charset != null && (!charset.equals(""))) {
                    value = Utility.decode(value, charset);
                } else {
                    value = Utility.decode(value, null);
                }
                Pattern pattern = Pattern.compile("\\\\\"");
                Matcher matcher = pattern.matcher(value);
                if (matcher.find()) {
                    value = matcher.replaceAll("\"");
                }
                pattern = Pattern.compile("\\\\\\\\");
                matcher = pattern.matcher(value);
                if (matcher.find()) {
                    value = matcher.replaceAll("\\\\");
                }
            } else {
                value = "";
            }
        } catch (Exception e) {
            new MailLog().write(e);
            return "";
        }

        return value;
    }

    private InternetAddress[] getInternetAddress(String name) {
        InternetAddress[] addr = null;

        if (name == null)
            return addr;

        String value = getHeader(name, ";");

        try {
            addr = InternetAddress.parse(value);
        } catch (AddressException e) {
            new MailLog().write(e);
        }

        return addr;
    }

    private String getReplyAddress(InternetAddress[] addrs, String email) {
        if (addrs == null)
            return "";
        if (email == null)
            email = "";

        HashMap<String, String> map = new HashMap<String, String>();

        String result = "";

        for (int i = 0; i < addrs.length; i++) {
            InternetAddress addr = addrs[i];
            String addrString = "";
            try {
                addrString = addr.getAddress();
            } catch (Exception e) {
                new MailLog().write(e);
                // 例外発生時はスキップ
                continue;
            }

            // 指定されているアドレスが含まれていればスキップ
            if (email.equals(addrString)) {
                continue;
            }

            // ダブっているアドレスが含まれていればスキップ
            if (map.containsKey(addrString)) {
                continue;
            }

            result += addr.toString();
            map.put(addr.toString(), "");

            if (i != addrs.length - 1) {
                result += " , ";
            }
        }

        return result;
    }

    private InternetAddress[] getAddress(Hashtable[] data) {
        InternetAddress[] addrs;
        String name = "";
        String addr;

        addrs = new InternetAddress[data.length];

        for (int i = 0; i < addrs.length; i++) {
            Hashtable ht = data[i];

            if (ht.containsKey("name") && ht.containsKey("address")) {
                // アドレスと名前がある場合
                addr = (String) ht.get("address");
                name = (String) ht.get("name");
                try {
                    name = Utility.changeSinglebyteKana(name, charset);
                    name = Utility.getEncodedWord(name, this.locale, charset);

                    if (name != "") {
                        // 名前がある場合
                        addrs[i] = new InternetAddress(addr, name);
                    } else {
                        addrs[i] = new InternetAddress(addr);
                    }
                } catch (UnsupportedEncodingException uee) {
                    new MailLog().write(
                        uee,
                        "smtp",
                        serverInfo.getHost(),
                        serverInfo.getPort(),
                        "");
                } catch (AddressException ae) {
                    new MailLog().write(
                        ae,
                        "smtp",
                        serverInfo.getHost(),
                        serverInfo.getPort(),
                        "");
                }
            } else {
                addrs[i] = new InternetAddress();
            }
        }

        return addrs;
    }

    /**
     * @return recordUserCd
     */
    public String getRecordUserCd() {
        return recordUserCd;
    }

    /**
     * @param recordUserCd
     *            設定する recordUserCd
     */
    public void setRecordUserCd(String recordUserCd) {
        this.recordUserCd = recordUserCd;
    }

    /**
     * @return registDate
     */
    public Date getRegistDate() {
        return registDate;
    }

    /**
     * @param registDate
     *            設定する registDate
     */
    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    /**
     * @return registUserCd
     */
    public String getRegistUserCd() {
        return registUserCd;
    }

    /**
     * @param registUserCd
     *            設定する registUserCd
     */
    public void setRegistUserCd(String registUserCd) {
        this.registUserCd = registUserCd;
    }

    /**
     * @return registUserName
     */
    public String getRegistUserName() {
        return registUserName;
    }

    /**
     * @param registUserName
     *            設定する registUserName
     */
    public void setRegistUserName(String registUserName) {
        this.registUserName = registUserName;
    }

    /**
     * @return updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     *            設定する updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return updateUserCd
     */
    public String getUpdateUserCd() {
        return updateUserCd;
    }

    /**
     * @param updateUserCd
     *            設定する updateUserCd
     */
    public void setUpdateUserCd(String updateUserCd) {
        this.updateUserCd = updateUserCd;
    }

    /**
     * @return updateUserName
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * @param updateUserName
     *            設定する updateUserName
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
