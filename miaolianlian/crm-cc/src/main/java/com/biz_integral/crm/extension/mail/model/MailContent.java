/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.ContentDisposition;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.biz_integral.crm.extension.mail.datasource.NonMimeDataSource;
import com.biz_integral.crm.extension.mail.util.MailLog;
import com.biz_integral.crm.extension.mail.util.Utility;

/**
 * メールメッセージ・コンテント
 * 
 */
public class MailContent {
    // マルチパートを取得ためのカウンター
    private int no = 0, no2 = 0;
    // Mainパート
    private Part mainpart;
    // ボディパート
    private ArrayList<Part> bodypart = new ArrayList<Part>();
    // HTMLパート
    private ArrayList<Part> htmlpart = new ArrayList<Part>();
    // メールセッション
    private Session session = null;
    // メッセージパート
    private Hashtable<String, PartData> messagepart =
        new Hashtable<String, PartData>();
    // 添付ファイルパート
    private Hashtable<String, PartData> attachpart =
        new Hashtable<String, PartData>();
    // パートの順番
    private int seq = 0;
    // 文字コード
    private String charset = null;
    // partの文字コード
    String contentCharset = null;
    // サーバ情報
    private MailSettings serverInfo = null;
    // ボディ内容
    private String body = "";
    // HTML内容
    private String html = "";
    // 添付ファイルリスト
    private ArrayList<HashMap<String, String>> attachment =
        new ArrayList<HashMap<String, String>>();
    // SMTPメッセージフラグ
    private boolean SMTPMessage = false;

    /**
     * コンストラクタ
     * 
     * @param mime
     *            メールメッセージ
     * @param session
     *            セッション
     * @param serverInfo
     *            メールサーバ設定
     * @param SMTPMessage
     *            SMTPメッセージフラグ
     */
    public MailContent(MimeMessage mime, Session session,
            MailSettings serverInfo, boolean SMTPMessage) {
        this.mainpart = (Part) mime;
        this.session = session;
        this.serverInfo = serverInfo;
        this.SMTPMessage = SMTPMessage;
        if (!SMTPMessage) {
            getMap(mainpart);
        }
    }

    /**
     * 受信メールの添付ファイルの名前を取得します
     * 
     * @return メッセージ各部の名
     */
    public Hashtable<String, String>[] getAttachNameList() {
        Enumeration<String> at_keys;
        String id;
        String name;
        PartData data;
        Hashtable<String, String> ht;
        Hashtable<String, String>[] names;

        names = new Hashtable[attachpart.size()];

        at_keys = attachpart.keys();

        for (int i = 0; at_keys.hasMoreElements(); i++) {
            ht = new Hashtable<String, String>();

            id = at_keys.nextElement();
            data = attachpart.get(id);
            name = data.getName();
            ht.put("id", id);
            ht.put("name", name);
            names[i] = ht;

        }

        return names;
    }

    /**
     * 受信メールのマルチメッセージ各部の名前を取得します
     * 
     * @return メッセージ各部の名
     */
    public Hashtable<String, String>[] getNameList() {
        Enumeration<String> mes_keys;
        String id, name;
        PartData data;
        Hashtable<String, String> ht;
        Hashtable<String, String>[] names;

        names = new Hashtable[messagepart.size()];

        mes_keys = messagepart.keys();

        for (int i = 0; mes_keys.hasMoreElements(); i++) {
            ht = new Hashtable<String, String>();

            id = mes_keys.nextElement();
            data = messagepart.get(id);
            name = data.getName();
            ht.put("id", id);
            ht.put("name", name);
            names[i] = ht;
        }

        return names;
    }

    /**
     * Bodyを取得します。
     * 
     * @return Body
     */
    public String getBody() {
        if (!SMTPMessage) {
            String content = "";
            Part p;
            Object o;

            try {
                // 全部ボディを取得する
                for (int i = 0; i < bodypart.size(); i++) {
                    p = bodypart.get(i);
                    o = getNonMimeContent(p);
                    if ((contentCharset == null || contentCharset.equals(""))
                        && (charset != null)
                        && (!charset.equals("")))
                        contentCharset = charset;
                    content = content + getContent(o);

                    if (i != bodypart.size() - 1) {
                        content += "\r\n\r\n";
                    }
                }
            } catch (IOException ie) {
                new MailLog().write(ie);
            } catch (MessagingException me) {
                new MailLog().write(me);
            } catch (Exception me) {
                new MailLog().write(me);
            }

            return content;
        } else {
            return body;
        }
    }

    /**
     * Htmlを取得します。
     * 
     * @return Html
     */
    public String getHtml() {
        if (!SMTPMessage) {
            String content = "";
            Part p;
            Object o;

            try {
                // 全部HTMLを取得する
                for (int i = 0; i < htmlpart.size(); i++) {
                    p = htmlpart.get(i);
                    o = getNonMimeContent(p);
                    content = content + getContent(o);

                    if (i != htmlpart.size() - 1) {
                        content += "\r\n\r\n";
                    }
                }
            } catch (IOException ie) {
                new MailLog().write(ie);
            } catch (MessagingException me) {
                new MailLog().write(me);
            }

            return content;
        } else {
            return html;
        }
    }

    /**
     * 一つRFC822メッセージを取得します
     * 
     * @param id
     * @return RFC822メッセージ
     * @throws IOException
     * @throws MessagingException
     */
    public MailMessage getMessageFromRFC822(String id) throws IOException,
        MessagingException {
        Part part;
        PartData data;
        MailMessage mes;

        if (messagepart.containsKey(id)) {
            // 取得したいのはRFC822メッセージ
            try {
                data = messagepart.get(id);
                part = data.getData();
                mes =
                    new MailMessage(
                        new MimeMessage(
                            session,
                            (InputStream) getCorrectDecode(part)),
                        session,
                        serverInfo);
            } catch (MessagingException me) {
                new MailLog().write(me);
                throw me;
            } catch (IOException ie) {
                new MailLog().write(ie);
                throw ie;
            }
        } else {
            // RFC822メッセージではない
            mes = null;
        }

        return mes;
    }

    /**
     * RFC822メッセージかどうかを判断します
     * 
     * @param id
     * @return RFC822メッセージかどうか
     */
    public boolean isMessageRFC822(String id) {
        return messagepart.containsKey(id);
    }

    /**
     * 全部RFC822メッセージを取得します
     * 
     * @return 全部RFC822メッセージ
     */
    public Hashtable<Object, Hashtable<String, String>> getMessageRFC822() {
        String name;
        String content = "";
        Object key;
        Part part;
        PartData data;
        Hashtable<Object, Hashtable<String, String>> ht =
            new Hashtable<Object, Hashtable<String, String>>();
        Hashtable<String, String> ht2;
        Enumeration<String> e;
        Object o;

        try {
            e = messagepart.keys();

            while (e.hasMoreElements()) {
                ht2 = new Hashtable<String, String>();

                key = e.nextElement();
                data = messagepart.get(key);

                part = data.getData();
                name = data.getName();

                o = getCorrectDecode(part);
                content = getContent(o);

                ht2.put("name", name);
                ht2.put("content", content);

                ht.put(key, ht2);
            }
        } catch (IOException ie) {
            new MailLog().write(ie);
        } catch (MessagingException me) {
            new MailLog().write(me);
        }

        return ht;
    }

    /**
     * 全部添付ファイルを取得します
     * 
     * @return 全部添付ファイル
     */
    public Hashtable<Object, Hashtable<String, String>> getAttachments() {
        String content = "";
        String name = "";
        Object key;
        PartData data;
        Part part;
        Hashtable<Object, Hashtable<String, String>> ht =
            new Hashtable<Object, Hashtable<String, String>>();
        Hashtable<String, String> ht2;
        Enumeration<String> e;
        Object o;

        try {
            e = attachpart.keys();

            while (e.hasMoreElements()) {
                ht2 = new Hashtable<String, String>();

                key = e.nextElement();
                data = attachpart.get(key);
                part = data.getData();
                name = data.getName();
                o = getCorrectDecode(part);
                content = getContent(o);

                ht2.put("name", name);
                ht2.put("content", content);

                ht.put(key, ht2);
            }
        } catch (IOException ie) {
            new MailLog().write(ie);
        } catch (MessagingException me) {
            new MailLog().write(me);
        }

        return ht;
    }

    /**
     * 一つ添付ファイルを取得します
     * 
     * @param id
     * @return 一つ添付ファイル
     * @throws IOException
     * @throws MessagingException
     */
    public Hashtable<String, String> getAttachment(String id)
        throws IOException, MessagingException {

        String name = "";
        String content = "";
        Part part;
        PartData data;
        Object o;
        Hashtable<String, String> ht = new Hashtable<String, String>();

        try {
            if (attachpart.containsKey(id)) {
                // 取得したいのは添付ファイル
                data = attachpart.get(id);

                part = data.getData();
                name = data.getName();

                o = getCorrectDecode(part);
                content = getContent(o);

                ht.put("name", name);
                ht.put("content", content);
            } else if (messagepart.containsKey(id)) {
                // 取得したいのはRFC822メッセージ
                data = messagepart.get(id);

                part = data.getData();
                name = data.getName();

                o = getCorrectDecode(part);
                content = getContent(o);

                ht.put("name", name);
                ht.put("content", content);
            }
        } catch (IOException ie) {
            new MailLog().write(ie);
            throw ie;
        } catch (MessagingException me) {
            new MailLog().write(me);
            throw me;
        }

        return ht;
    }

    /**
     * 一つRowDataを取得します
     * 
     * @param id
     * @return 一つRowData
     */
    public Hashtable<String, String> getAttachRowData(String id) {

        String name = "";
        String content = "";
        Part part;
        PartData data;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hashtable<String, String> ht = new Hashtable<String, String>();

        try {
            if (attachpart.containsKey(id)) {
                // 取得したいのは添付ファイル
                data = attachpart.get(id);

                part = data.getData();
                name = data.getName();

                part.writeTo(bos);
                content = bos.toString("8859_1");

                ht.put("name", name);
                ht.put("content", content);
            } else if (messagepart.containsKey(name)) {
                // 取得したいのはRFC822メッセージ
                data = messagepart.get(id);

                part = data.getData();
                name = data.getName();

                part.writeTo(bos);
                content = bos.toString("8859_1");

                ht.put("name", name);
                ht.put("content", content);
            }

        } catch (IOException ie) {
            new MailLog().write(ie);
        } catch (MessagingException me) {
            new MailLog().write(me);
        }

        return ht;
    }

    /**
     * 添付ファイルまたはメッセージのサイズを取得します
     * 
     * @param id
     * @return 添付ファイルまたはメッセージのサイズ
     */
    public int getSize(String id) {
        PartData data;
        Part part;
        int size = 0;

        try {
            if (attachpart.containsKey(id)) {
                // 取得したいのは添付ファイル
                data = attachpart.get(id);
                part = data.getData();
                size = part.getSize();
            } else if (messagepart.containsKey(id)) {
                // 取得したいのはRFC822メッセージ
                data = messagepart.get(id);
                part = data.getData();
                size = part.getSize();
            } else {
                size = 0;
            }
        } catch (MessagingException me) {
            new MailLog().write(me);
        }

        return size;
    }

    private void getMap(Part p) {

        HashMap<String, String> disp = null;
        String name = null;
        String ext_name = ".eml";
        String[] headers;

        try {
            // imapサーバによってmultipartなのにisMultiType("multipart/*")で
            // falseが帰ってしまう現象に対する処理
            headers = p.getHeader("Content-Type");

            if (headers != null
                && headers.length > 0
                && headers[0].toLowerCase().startsWith("multipart/")
                && !p.isMimeType("multipart/*")
                && session != null) {
                // ここのロジックに入った時は多少遅くなります。

                ByteArrayInputStream bis;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] bytes;
                MimeMessage message;

                // パートの情報を取り出して、もう一度入れ直すことでimapサーバの
                // MIME解析エンジンの実装の違いを吸収する
                p.writeTo(bos);

                bytes = bos.toByteArray();
                bis = new ByteArrayInputStream(bytes);
                message = new MimeMessage(session, bis);

                p = (Part) message;
            }
            if (p.isMimeType("multipart/*")) {
                Multipart mp = (Multipart) p.getContent();
                if (mp == null)
                    return;
                int count = mp.getCount();

                for (int i = 0; i < count; i++) {
                    getMap(mp.getBodyPart(i));
                }
                return;
            }

            String[] disp2 = p.getHeader("Content-Disposition");
            String[] disp3 = p.getHeader("Content-Type");

            if (disp2 != null && disp2.length != 0) {
                // Eudora等、生のJISでファイル名をつけてくるメーラへの対応
                // 下位バイトが22の時、添付ファイル名が取れない現象の対処です。
                if (disp2[0] != null) {
                    disp2[0] = Utility.jis_detect(disp2[0]);
                    disp2[0] = Utility.decode(disp2[0], charset);

                    // Content-Dispositionヘッダをparseします
                    disp = Utility.parseHeader(disp2[0]);

                    if (disp != null && disp.containsKey("filename")) {
                        ContentDisposition cd =
                            new ContentDisposition(disp2[0]);
                        String fileName = cd.getParameter("filename");
                        // ファイル名にセミコロンがある場合はfile名が取得できないため
                        // getFileNameで取得できた場合は、parseHeaderの結果を無視する。
                        if (fileName != null) {
                            name = fileName;
                        } else {
                            // filenameフィールドからファイル名を取得
                            name = (String) disp.get("filename");
                        }
                        // 条件：文末から最後の「.（ドット）」までの文字列を拡張子と判断する
                        Pattern pattern = Pattern.compile("\\.([^\\.]+)$");
                        Matcher matcher = pattern.matcher(name);
                        StringBuffer sb = new StringBuffer();
                        while (matcher.find()) {
                            String ext = matcher.group();
                            ext = ext.replaceAll("[ \\t]", ""); // 空白（\x20/tab）をカット。無条件
                            matcher.appendReplacement(sb, ext);
                        }
                        matcher.appendTail(sb);
                        name = sb.toString();
                    } else {
                        name = null;
                    }
                } else {
                    disp = null;
                    name = null;
                }
            }

            if ((name == null || name.equals(""))
                && disp3 != null
                && disp3.length != 0) {
                // Eudora等、生のJISでファイル名をつけてくるメーラへの対応
                // 下位バイトが22の時、添付ファイル名が取れない現象の対処です。
                if (disp3[0] != null) {
                    disp3[0] = Utility.jis_detect(disp3[0]);
                    disp3[0] = Utility.decode(disp3[0], charset);

                    // Content-Dispositionヘッダをparseします
                    disp = Utility.parseHeader(disp3[0]);

                    if (disp != null && disp.containsKey("name")) {
                        ContentType ct = new ContentType(disp3[0]);
                        String fileName = ct.getParameter("name");
                        // ファイル名にセミコロンがある場合はfile名が取得できないため
                        // getFileNameで取得できた場合は、parseHeaderの結果を無視する。
                        if (fileName != null) {
                            name = fileName;
                        } else {
                            // nameフィールドからファイル名を取得
                            name = (String) disp.get("name");
                        }
                        // 条件：文末から最後の「.（ドット）」までの文字列を拡張子と判断する
                        Pattern pattern = Pattern.compile("\\.([^\\.]+)$");
                        Matcher matcher = pattern.matcher(name);
                        StringBuffer sb = new StringBuffer();
                        while (matcher.find()) {
                            // extention
                            String ext = matcher.group();
                            ext = ext.replaceAll("[  \\t]", ""); // 空白（\x20/tab）をカット。無条件
                            matcher.appendReplacement(sb, ext);
                        }
                        matcher.appendTail(sb);

                        name = sb.toString();
                    } else {
                        name = null;
                    }
                } else {
                    disp = null;
                    name = null;
                }
                disp = null;
            }

            if (disp == null) {
                if ((p.isMimeType("text/plain") || p.isMimeType("text"))
                    && name == null) {
                    bodypart.add(p);
                } else if (p.isMimeType("text/html") && name == null) {
                    htmlpart.add(p);
                } else if (p.isMimeType("message/rfc822")) {
                    if (name == null) {
                        ext_name = "." + MailSysInfo.getDefaultExt();

                        setMessage(
                            "message" + String.valueOf(no2++) + ext_name,
                            p);
                    } else {
                        setMessage(name, p);
                    }
                } else {
                    if (name == null) {
                        ext_name = "." + MailSysInfo.getDefaultExt();

                        setAttach("file" + String.valueOf(no++) + ext_name, p);
                    } else {
                        setAttach(name, p);
                    }
                }
            } else if (((String) disp.get("attribute"))
                .equalsIgnoreCase(Part.INLINE)) {
                if (p.isMimeType("text/plain") && name == null) {
                    bodypart.add(p);
                } else if (p.isMimeType("text/html") && name == null) {
                    htmlpart.add(p);
                } else if (p.isMimeType("message/rfc822")) {
                    if (name == null) {
                        ext_name = "." + MailSysInfo.getDefaultExt();

                        setMessage(
                            "message" + String.valueOf(no2++) + ext_name,
                            p);
                    } else {
                        setMessage(name, p);
                    }
                } else {
                    if (name == null) {
                        ext_name = "." + MailSysInfo.getDefaultExt();

                        setAttach("file" + String.valueOf(no++) + ext_name, p);
                    } else {
                        setAttach(name, p);
                    }
                }
            } else {
                // Content-Dispositionヘッダがあって、inlineじゃないとき
                // 主に、attachmentの場合だが、それ以外も添付ファイルとして扱う
                if (p.isMimeType("message/rfc822")) {
                    if (name == null) {
                        ext_name = "." + MailSysInfo.getDefaultExt();

                        setMessage(
                            "message" + String.valueOf(no2++) + ext_name,
                            p);
                    } else {
                        setMessage(name, p);
                    }
                } else {
                    if (name == null) {
                        ext_name = "." + MailSysInfo.getDefaultExt();

                        setAttach("file" + String.valueOf(no++) + ext_name, p);
                    } else {
                        setAttach(name, p);
                    }
                }
            }
        } catch (MessagingException me) {
            new MailLog().write(me);
        } catch (IOException ie) {
            new MailLog().write(ie);
        } catch (Exception e) {
            new MailLog().write(e);
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
     * charsetを取得します。
     * 
     * @return charset
     */
    public String getCharset() {
        if (charset == null || charset.equals(""))
            return contentCharset;
        return charset;
    }

    /**
     * 新メールのBodyを設定します。
     * 
     * @param body
     */
    public void setBody(String body) {
        body = Utility.changeSinglebyteKana(body, charset);
        this.body = body;
    }

    /**
     * 新メールの添付ファイルを設定します。
     * 
     * @param name
     *            添付ファイル名
     * @param contents
     *            添付ファイルコンテント
     */
    public void setAttachment(String name, String contents) {
        HashMap<String, String> hm = new HashMap<String, String>();
        name = Utility.changeSinglebyteKana(name, charset);
        hm.put("name", name);
        hm.put("contents", contents);

        this.attachment.add(hm);
    }

    /**
     * 新メールの添付ファイルを取得します。
     * 
     * @return 添付ファイル配列
     */
    public ArrayList<HashMap<String, String>> getAttachmentList() {
        return attachment;
    }

    /**
     * 新メールのHtmlを設定します。
     * 
     * @param html
     */
    public void setHtml(String html) {
        html = Utility.changeSinglebyteKana(html, charset);
        this.html = html;
    }

    private String getContent(Object o) throws MessagingException, IOException {
        String str = "";

        if (o instanceof String) {
            // 既に文字列場合
            str = (String) o;
        } else if (o instanceof InputStream) {
            // データソースからの場合
            InputStream is;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            boolean yieldFlg = MailSysInfo.getYieldFlag();
            int yieldCount = MailSysInfo.getYieldCount();
            int c = -1;

            is = (InputStream) o;

            c = is.read();

            int count = 0;
            while (c != -1) {
                count++;
                if (yieldFlg && yieldCount < count) {
                    // カウントを初期化して、yield
                    count = 0;
                    Thread.yield();
                }

                bos.write(c);
                try {
                    c = is.read();
                } catch (IOException ie) {
                    // uuencode対応
                    break;
                }
            }

            try {
                str = bos.toString("8859_1");
            } catch (UnsupportedEncodingException ue) {
                new MailLog().write(ue);
            }
        } else if (o instanceof MimeMessage) {
            // message/rfc822の場合
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            ((MimeMessage) o).writeTo(bos);

            try {
                str = bos.toString("8859_1");
            } catch (UnsupportedEncodingException ue) {
                new MailLog().write(ue);
            }
        }

        return str;
    }

    private Object getNonMimeContent(Part p) throws MessagingException,
        IOException {
        DataHandler dh;
        String charSet = "JISAutoDetect";
        String result;

        // 強制的にcharsetでコンテントを取得
        if (charset != null && (!charset.equals(""))) {
            dh = new DataHandler(new NonMimeDataSource(p, charset, true));
        } else {
            dh = new DataHandler(new NonMimeDataSource(p, charSet, false));
        }

        String contentType = dh.getContentType();
        HashMap<String, String> contentMap = Utility.parseHeader(contentType);

        String headerCharSet = (String) contentMap.get("charset");

        if ((headerCharSet != null)
            && (headerCharSet.toLowerCase().indexOf("iso-2022-jp") != -1)) {
            // SJIS,JIS系の文字の場合文字化け防止処理にトライする
            result = Utility.changeJISToSJIS(dh.getInputStream());
        } else {
            // それ以外は普通に取る(例えばUTF-8)
            try {
                result = (String) dh.getContent();

            } catch (Throwable male) {
                // 文字コード体系に含まれないコードが含まれている時の対処
                String hcharset;
                InputStream is2, is3;
                InputStreamReader isr;
                ByteArrayInputStream bai;
                ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
                StringWriter sw = new StringWriter();
                byte[] ba2, ba3;
                int in, cnt, i, j;

                if (charset != null && (!charset.equals(""))) {
                    // 指定するコードを使用する
                    headerCharSet = charset;
                }
                hcharset = headerCharSet;

                if (hcharset == null) {
                    // 文字コードが存在しない場合
                    if (charset != null && (!charset.equals(""))) {
                        // 指定するコードを使用する
                        hcharset = charset;
                    } else {
                        // 日本語（自動）を設定する
                        hcharset = "JISAutoDetect";
                    }
                }
                is2 = dh.getInputStream();
                isr = new InputStreamReader(is2, hcharset);
                is3 = dh.getInputStream();

                while ((in = is3.read()) != -1) {
                    bos2.write(in);
                }

                ba2 = bos2.toByteArray();

                cnt = 0;
                while (true) {
                    try {
                        in = isr.read();

                        if (in > 128) {
                            cnt += 2;
                        } else {
                            cnt++;
                        }

                        if (in == -1) {
                            break;
                        }

                        sw.write(in);
                    } catch (IOException ie2) {
                        // 未知の文字コードは2バイトとして処理
                        cnt += 2;
                        ba3 = new byte[ba2.length - cnt];

                        for (i = cnt, j = 0; i < ba2.length; i++, j++) {
                            ba3[j] = ba2[i];
                        }

                        bai = new ByteArrayInputStream(ba3);
                        isr =
                            new InputStreamReader((InputStream) bai, hcharset);

                        cnt = 0;
                        ba2 = new byte[ba3.length];
                        ba2 = ba3;

                        in = 63;

                        sw.write(in);
                        sw.write(in);
                    }
                }

                result = sw.toString();
            }
        }

        // デコードした文字列内に制御コードがある場合XMLのParseに失敗するため
        // 該当のASCIIコードを削除する
        // 本文なので改行コードとタブはのこしておく
        if (result.length() != 0) {
            Pattern pattern =
                Pattern.compile("[\\x00-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]");
            Matcher matcher = pattern.matcher(result);
            result = matcher.replaceAll("");
        }
        if (charset == null || (charset.equals(""))) {
            this.charset = headerCharSet;
        }

        return result;
    }

    private Object getCorrectDecode(Part p) throws IOException,
        MessagingException {
        InputStream is;

        if (p instanceof MimeBodyPart) {
            MimeBodyPart mbp = (MimeBodyPart) p;

            is = mbp.getRawInputStream();

            if (mbp.getEncoding() != null
                && mbp
                    .getDataHandler()
                    .getDataSource()
                    .getClass()
                    .getName()
                    .indexOf("MimePartDataSource") == -1) {
                // エンコードされていないかも
                is = MimeUtility.decode(is, mbp.getEncoding());
            } else {
                String encoding;

                if (mbp.getEncoding() != null) {
                    encoding = mbp.getEncoding();
                } else {
                    encoding = "7bit";
                }

                is = MimeUtility.decode(is, encoding);
            }
        } else {
            is = p.getInputStream();
        }

        return (Object) is;
    }

    private void setAttach(String name, Part value) {
        PartData data = new PartData();

        data.setName(name);
        data.setData(value);

        attachpart.put("file" + String.valueOf(seq), data);

        seq++;
    }

    private void setMessage(String name, Part value) {
        PartData data = new PartData();

        data.setName(name);
        data.setData(value);

        messagepart.put("message" + String.valueOf(seq), data);

        seq++;
    }
}
