/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import jp.co.intra_mart.common.aid.jdk.java.io.BASE64DecodeInputStream;
import jp.co.intra_mart.common.aid.jdk.java.io.BASE64EncodeOutputStream;
import jp.co.intra_mart.foundation.mail.javamail.property.JavaMailPropertyException;
import jp.co.intra_mart.foundation.mail.javamail.property.JavaMailPropertyHandler;
import jp.co.intra_mart.foundation.security.AccessSecurityManager;
import jp.co.intra_mart.foundation.security.SessionInfo;
import jp.co.intra_mart.system.javascript.NativeArray;
import jp.co.intra_mart.system.javascript.Scriptable;
import jp.co.intra_mart.system.javascript.ScriptableObject;
import jp.co.intra_mart.system.javascript.imapi.RuntimeObject;
import jp.co.intra_mart.system.javascript.imapi.ValueObject;

import com.biz_integral.crm.extension.mail.model.MailSysInfo;
import com.sun.mail.util.QDecoderStream;

/**
 * 共通関数
 * 
 */
public class Utility {

    /**
     * Scriptableアドレスを取得する
     * 
     * @param address
     *            　メールアドレス
     * @return　Scriptableアドレス
     */
    public static Scriptable getAddress(String address) {
        int i;
        InternetAddress[] iaddrs = null, tempiaddrs = null;
        Hashtable<String, String> ht;
        ValueObject[] jObj;

        try {
            iaddrs = InternetAddress.parse(address);
        } catch (AddressException ae) {
            jObj = new ValueObject[0];
            return RuntimeObject.newArrayInstance(jObj);
        }

        jObj = new ValueObject[iaddrs.length];

        for (i = 0; i < iaddrs.length; i++) {
            ht = new Hashtable<String, String>();
            tempiaddrs = new InternetAddress[1];
            tempiaddrs[0] = iaddrs[i];

            if (iaddrs[i].getPersonal() != null) {
                ht.put("name", iaddrs[i].getPersonal());
            } else {
                ht.put("name", "");
            }

            if (iaddrs[i].getAddress() != null) {
                ht.put("address", iaddrs[i].getAddress());
            } else {
                ht.put("address", "");
            }

            try {
                ht.put("row", InternetAddress.toString(tempiaddrs));
            } catch (ClassCastException cce) {
                ht.put("row", "");
            }

            jObj[i] = new ValueObject(ht);
        }

        return RuntimeObject.newArrayInstance(jObj);
    }

    /**
     * JISに変換する
     * 
     * @param s
     *            変換される文字列
     * @return　String 変換した文字列
     */
    public static String jis_detect(String s) {
        if (s != null) {
            try {
                s = new String(s.getBytes("8859_1"), "JISAutoDetect");
            } catch (Exception e) {
                // 変換に失敗したときはなにもしません
            }
        }

        return s;
    }

    /**
     * 文字列をデコードする
     * 
     * @param s
     *            デコードされる文字列
     * @return　String デコードした文字列
     */
    public static String decode(String s, String convertCharset)
        throws MessagingException {
        String result = "";
        String decodeString = "";
        int i;
        int j;

        if (s == null) {
            return s;
        }

        s = delimString(s);

        i = s.indexOf("=?");

        if (i == -1) {
            result = s;
            return result;
        }

        j = s.indexOf("?", i + 2);

        if (j == -1) {
            result = s;
            return result;
        }

        j = s.indexOf("?", j + 1);

        if (j == -1) {
            result = s;
            return result;
        }

        j = s.indexOf("?", j + 1);

        if (j == -1) {
            result = s;
            return result;
        }

        j += 2;

        try {
            if (j > s.length()) {
                j = s.length();
            }
            decodeString = MimeDecoder(s.substring(i, j), convertCharset);
        } catch (Exception e) {
        }

        if (s.length() > j) {
            result =
                s.substring(0, i)
                    + decodeString
                    + decode(s.substring(j), convertCharset);
        } else {
            result = s.substring(0, i) + decodeString;
        }

        return result;
    }

    /**
     * 文字列の分割符を削除する
     * 
     * @param s
     *            　文字列
     * @return　String 分割符ない文字列
     */
    public static String delimString(String s) {
        if (s == null)
            return null;

        s = splitter(s, "\r\n ");
        s = splitter(s, "\r\n\t");

        return s;
    }

    /**
     * NativeArrayからオブジェクト配列を取得する。
     * 
     * @param array
     *            　NativeArray
     * @return　オブジェクト配列
     */
    public static Object[] get_JsArray(NativeArray array) {
        int i, index;
        Object[] o_index = array.getIds();
        Object[] result;

        result = new Object[o_index.length];

        for (i = 0; i < o_index.length; i++) {
            index = ((Integer) o_index[i]).intValue();
            result[i] = array.get(index, array);
        }

        return result;
    }

    /**
     * ScriptableObjectからオブジェクトを取得する。
     * 
     * @param so
     *            　ScriptableObject
     * @return　オブジェクト
     */
    public static Hashtable<Object, Object> get_JsObject(ScriptableObject so) {
        int i;
        Object[] ids;
        Hashtable<Object, Object> ht = new Hashtable<Object, Object>();

        ids = so.getAllIds();

        for (i = 0; i < ids.length; i++) {
            ht.put(ids[i], so.get((String) ids[i], so));
        }

        return ht;
    }

    /**
     * メール設定を取得する
     * 
     * @param property
     *            　設定のキー
     * @return String キーの値取得
     */
    public static String getWebMailProps(String property) {
        ResourceBundle rb;
        String result;

        // デフォルト情報のセット
        try {
            SessionInfo info =
                (AccessSecurityManager.getInstance()).getSessionInfo();
            Locale locale = info.getLocale();
            rb =
                ResourceBundle.getBundle(
                    "com.biz_integral.crm.extension.mail.model.product",
                    locale);

            result = rb.getString(property);
        } catch (MissingResourceException mre) {
            result = "";
            new MailLog().write(mre);
        } catch (Exception e) {
            result = "";
            new MailLog().write(e);
        }

        return result;
    }

    /**
     * Mimeデコードする。
     * 
     * @param target
     *            　デコードされる文字列
     * @return　String デコードした文字列
     */
    public static String MimeDecoder(String target, String convertCharset) {
        int equal = 61;
        int que = 63;

        byte[] targetBytes = target.getBytes();
        ByteArrayInputStream bis = new ByteArrayInputStream(targetBytes);
        int in;
        byte[] tempBytes;

        String charset;
        String encoding;

        in = bis.read();

        // 最初の"=?"
        if (in != equal) {
            return "";
        }
        if ((in = bis.read()) != que) {
            return "";
        }

        in = bis.read();

        ByteArrayOutputStream charsetBos = new ByteArrayOutputStream();
        // 文字コードの抽出
        while (in != que) {
            charsetBos.write(in);

            in = bis.read();

            if (in == -1) {
                return "";
            }
        }

        tempBytes = charsetBos.toByteArray();
        charset = new String(tempBytes);
        if (convertCharset != null && !convertCharset.equals(""))
            charset = convertCharset;

        in = bis.read();

        ByteArrayOutputStream encodingBos = new ByteArrayOutputStream();
        // エンコーディングの抽出
        while (in != que) {
            encodingBos.write(in);

            in = bis.read();

            if (in == -1) {
                return "";
            }
        }

        tempBytes = encodingBos.toByteArray();
        encoding = new String(tempBytes);

        in = bis.read();

        ByteArrayOutputStream dataBos = new ByteArrayOutputStream();
        // wordの抽出
        while (in != que) {
            dataBos.write(in);

            tempBytes = dataBos.toByteArray();

            in = bis.read();

            if (in == -1) {
                break;
            }
        }

        ByteArrayInputStream dataBis = new ByteArrayInputStream(tempBytes);
        ByteArrayOutputStream resultBos = new ByteArrayOutputStream();
        String result = "";

        if (encoding.equalsIgnoreCase("B")) {
            BASE64DecodeInputStream base64Is =
                new BASE64DecodeInputStream(dataBis);
            try {
                in = base64Is.read();
            } catch (Exception e) {
                // いきなり例外が発生したときには処理を終了する
                in = -1;
            }

            while (in != -1) {
                resultBos.write(in);

                try {
                    in = base64Is.read();
                } catch (Exception e) {
                    // 例外が発生するまでは頑張る
                    in = -1;
                }
            }

            tempBytes = resultBos.toByteArray();
            int length = tempBytes.length;
            try {
                if (charset.equalsIgnoreCase("iso2022jp")
                    || charset.equalsIgnoreCase("iso-2022-jp")) {
                    result =
                        changeJISToSJIS(new ByteArrayInputStream(tempBytes));
                } else if (charset.equalsIgnoreCase("utf-8")
                    || charset.equalsIgnoreCase("utf8")
                    || charset.equalsIgnoreCase("unicode")) {
                    result = new String(tempBytes, "UTF-8");
                } else if ("".equals(charset) || charset == null) {
                    result = new String(tempBytes, "JISAutoDetect");
                } else {
                    result = new String(tempBytes, charset);
                }

                if (length != 0 && result.equals("")) {
                    // 文字コードがウソをついてる場合の対応
                    // JISAutoDetectで変換を試みます
                    result = new String(tempBytes, "JISAutoDetect");
                }
            } catch (UnsupportedEncodingException uee) {
                // エンコード方法がサポートされていない場合
                uee.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (encoding.equalsIgnoreCase("Q")) {
            QDecoderStream qDecoderS = new QDecoderStream(dataBis);
            try {
                in = qDecoderS.read();
            } catch (Exception e) {
                // いきなり例外が発生したときには処理を終了する
                in = -1;
            }

            while (in != -1) {
                resultBos.write(in);

                try {
                    in = qDecoderS.read();
                } catch (Exception e) {
                    // 例外が発生するまでは頑張る
                    in = -1;
                }
            }

            tempBytes = resultBos.toByteArray();
            int length = tempBytes.length;
            try {
                result = new String(tempBytes, charset);

                if (length != 0 && result.equals("")) {
                    // 文字コードがウソをついてる場合の対応
                    // JISAutoDetectで変換を試みます
                    result = new String(tempBytes, "JISAutoDetect");
                }
            } catch (UnsupportedEncodingException uee) {
                // エンコード方法がサポートされていない場合
                uee.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // その他の場合はなにもしない
        }

        // デコードした文字列内に制御コードがある場合XMLのParseに失敗するため
        // 該当のASCIIコードを削除する
        if (result.length() != 0) {
            Pattern pattern = Pattern.compile("[\\x00-\\x1f\\x7f]");
            Matcher matcher = pattern.matcher(result);
            result = matcher.replaceAll("");
        }

        return result;
    }

    /**
     * 指定する文字列を文字列中から削除する
     * 
     * @param s
     *            　処理したい文字列
     * @param splitter
     *            　削除したい文字列
     * @return　String 削除した文字列
     */
    public static String splitter(String s, String splitter) {
        int i;
        int start = 0;
        String s2 = "";

        i = s.indexOf(splitter);
        while (i != -1) {
            s2 += s.substring(start, i);
            start = i + splitter.length();
            i = s.indexOf(splitter, start);
        }
        s2 += s.substring(start);

        return s2;
    }

    /**
     * 文字列を分割する
     * 
     * @param str
     *            　分割される文字列
     * @param delim
     *            　分割符
     * @return　分割した文字列配列
     */
    public static String[] stringSplitter(String str, String delim) {
        Vector<String> vec = new Vector<String>();
        StringReader sr = new StringReader(str);
        StringWriter sw = new StringWriter();
        int in = 0, idx;
        int i;

        idx = str.indexOf(delim);

        // 一つも区切り文字がなかったとき
        if (idx == -1) {
            String[] result = { str };
            return result;
        }

        while (idx != -1) {
            for (i = 0; i < idx && in != -1; i++) {
                try {
                    in = sr.read();
                } catch (IOException e) {
                    break;
                }

                if (i == -1) {
                    break;
                }

                sw.write(in);
            }

            vec.add(sw.toString());
            sw = new StringWriter();

            try {
                sr.skip(delim.length());
            } catch (IOException e) {
                break;
            }

            str = str.substring(idx + delim.length());
            idx = str.indexOf(delim);
        }

        vec.add(str);

        int strLength = vec.size();
        String[] result = new String[strLength];

        for (i = 0; i < strLength; i++) {
            result[i] = (String) vec.get(i);
        }

        return result;
    }

    /**
     * ヘッダをパースする
     * 
     * @param target
     *            　ヘッダ文字列
     * @return　ヘッダマップ
     */
    public static HashMap<String, String> parseHeader(String target) {
        if (target == null)
            return null;

        // 改行スペースおよび改行タブを除去
        target = delimString(target);

        String token = ";";
        String[] tempArray;
        String[] targetArray;
        int i;
        HashMap<String, String> map = new HashMap<String, String>();
        boolean isRFC2231;

        map.put("attribute", "");

        // ";"でスプリットします
        targetArray = stringSplitter(target, token);
        Vector<String> rfc2231Vec = new Vector<String>();

        for (i = 0; i < targetArray.length; i++) {
            // まずは前後のスペースとタブを省きます
            String tempString = trimSPTAB(targetArray[i]);

            // 一番目の要素はおそらく属性
            // attribute=""で返します
            if (i == 0) {
                map.put("attribute", tempString.toLowerCase());
                continue;
            }

            // RFC2231属性かをチェック
            isRFC2231 = isRFC2231(tempString);

            if (isRFC2231) {
                rfc2231Vec.add(tempString);
            } else {
                // 2番目以降はパラメータ=パラメータ値の形で帰す
                tempArray = statementAnalizer(tempString, "=");

                if (tempArray != null && tempArray.length == 2) {
                    // 頭と末尾の連続したスペースとタブを除去
                    tempArray[1] = trimSPTAB(tempArray[1]);
                    // 頭と末尾のダブルクオートを除去
                    tempArray[1] = trimQuot(tempArray[1]);
                    // パラメータとパラメータ値を保持
                    map.put(tempArray[0].toLowerCase(), tempArray[1]);
                }
            }
        }

        if (rfc2231Vec.size() != 0) {
            map.putAll(parseRFC2231(rfc2231Vec));
        }

        return map;
    }

    /**
     * 文字列の前後のスペースとタブを省きます
     * 
     * @param s
     *            処理される文字列
     * @return　String 処理した文字列
     */
    private static String trimSPTAB(String s) {
        int i;
        int idx = 0;

        if (s.length() == 0) {
            return s; // 空文字を返却
        }

        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' && s.charAt(i) != '\t') {
                idx = i;
                break;
            }
        }

        if (idx == 0 && s.length() == i) {
            // 全てスペースとタブで構成されている文字列のため空文字を返却
            return null;
        }

        s = s.substring(idx);

        idx = s.length();

        // 文字列がnull文字または1文字の場合はループをしない
        if (idx == 1) {
            if (s.charAt(0) == ' ' || s.charAt(0) == '\t') {
                // 全てスペースとタブで構成されている文字列のため空文字を返却
                return null;
            } else {
                // 1文字なのでsubstringする必要はない
                // ここまでの処理で1文字なので後ろにスペースとタブは入っているはずがない
                return s;
            }
        } else {
            for (i = s.length() - 1; i > 0; i--) {
                if (s.charAt(i) != ' ' && s.charAt(i) != '\t') {
                    idx = i;
                    break;
                }
            }
        }

        s = s.substring(0, idx + 1);

        return s;
    }

    /**
     * 文字列を分割する
     * 
     * @param s
     *            処理したい文字列
     * @param token
     *            分割符
     * @return　String[] 分割した文字列配列
     */
    private static String[] statementAnalizer(String s, String token) {
        if (s == null)
            return null;

        int idx;
        String leftStr, rightStr;
        String[] result = null;

        idx = s.indexOf(token);

        if (idx != -1) {
            leftStr = s.substring(0, idx);
            rightStr = s.substring(idx + token.length());

            result = new String[2];

            result[0] = trimSPTAB(leftStr);
            result[1] = trimSPTAB(rightStr);
        }

        return result;
    }

    /**
     * 文字のタイプはrfc2231であるかどうか
     * 
     * @param s
     *            　RFC2231文字列
     * @return　boolean 真偽値としてチェックします
     */
    private static boolean isRFC2231(String s) {
        if (s == null)
            return false;

        int i, idx;
        boolean result = false;
        String[] token =
            { "*=", "*0", "*1", "*2", "*3", "*4", "*5", "*6", "*7", "*8", "*9" };

        for (i = 0; i < token.length; i++) {
            idx = s.indexOf(token[i]);

            if (idx != -1) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * RFC2231場合、list対象はHashMap対象になります
     * 
     * @param list
     *            　list対象
     * @return　HashMap対象
     */
    private static HashMap<String, String> parseRFC2231(List<String> list) {
        if (list == null || list.size() == 0)
            return null;

        Iterator<String> iter = list.iterator();
        Vector<String> vec = new Vector<String>();
        HashMap<String, String> map = new HashMap<String, String>();

        while (iter.hasNext()) {
            String s = (String) iter.next();
            String[] targetArray = statementAnalizer(s, "*=");

            if (targetArray == null) {
                targetArray = statementAnalizer(s, "=");
            }

            if (targetArray == null || targetArray.length != 2)
                continue;

            String param1 = targetArray[0];
            String param2;
            int idx = param1.indexOf("*");
            if (idx != -1) {
                param2 = param1.substring(0, idx);
            } else {
                param2 = param1;
            }

            // RFC2231形式で入っている属性のリストを格納する
            if (!vec.contains(param2)) {
                vec.add(param2);
            }

            map.put(targetArray[0], targetArray[1]);
        }

        iter = vec.iterator();
        HashMap<String, String> map2 = new HashMap<String, String>();

        while (iter.hasNext()) {
            String name = (String) iter.next();
            int i = 0;
            String value = "";

            while (true) {
                if (!map.containsKey(name + "*" + i)) {
                    if (i == 0 && map.containsKey(name)) {
                        value += map.get(name);
                    }

                    break;
                }

                value += map.get(name + "*" + i);

                i++;
            }

            value = decodeRFC2231(value);

            map2.put(name, value);
        }

        return map2;
    }

    /**
     * RFC2231デコードする
     * 
     * @param target
     *            　RFC2231文字列
     * @return　String デコードした文字列
     */
    public static String decodeRFC2231(String target) {
        if (target == null)
            return null;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        String[] array = stringSplitter(target, "'");

        if (array.length != 3)
            return null;

        StringReader sr = new StringReader(array[2]);

        int in;
        try {
            in = sr.read();
        } catch (IOException e) {
            in = -1;
        }

        while (in != -1) {
            if (in == 37) {
                StringWriter sw = new StringWriter();
                // "%"を見つけたら2バイト分読み込む
                try {
                    in = sr.read();

                    if (in != -1)
                        sw.write(in);
                    else
                        break;

                    in = sr.read();

                    if (in != -1)
                        sw.write(in);
                    else
                        break;

                    String s2 = sw.toString();

                    in = Integer.valueOf(s2, 16).intValue();
                } catch (IOException e2) {
                    in = -1;
                }
            }

            bos.write(in);

            try {
                in = sr.read();
            } catch (IOException e1) {
                in = -1;
            }
        }

        String result = "";

        String decodeCharset = "JISAutoDetect";
        if (array[0] != null && !"".equals(array[0])) {
            decodeCharset = array[0];
        }

        try {

            result = bos.toString(decodeCharset);

        } catch (UnsupportedEncodingException e1) {
            result = null;
        }

        return result;
    }

    /**
     * 文字列の前後の引用符を省きます
     * 
     * @param s
     *            処理される文字列
     * @return　String 処理した文字列
     */
    private static String trimQuot(String s) {
        if (s == null || s.length() == 0)
            return null;

        if (s.charAt(0) == '"') {
            s = s.substring(1);
        }

        if (s.charAt(s.length() - 1) == '"') {
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }

    /**
     * JISをSJISに変更する
     * 
     * @param is
     *            　Input Stream
     * @return　変更した文字列
     */
    public static String changeJISToSJIS(InputStream is) {
        int in = -1;
        int i;
        boolean mode = false;
        boolean jisx = false;
        byte[] escapeSeq = new byte[2];
        byte[] bytes = new byte[2];

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        while (true) {
            try {
                in = is.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (in == -1)
                break;

            if (in == 0x1b) {
                try {
                    i = is.read(escapeSeq, 0, 2);

                    if (i != 2)
                        break;

                    if (escapeSeq[0] == 0x24) {
                        mode = true;
                    }

                    if (escapeSeq[0] == 0x28) {
                        if (escapeSeq[1] == 0x49) { // JIS X 0201 カナ(半角カナ)
                            jisx = true;
                            mode = true;
                        } else {
                            jisx = false;
                            mode = false;
                        }
                    }

                    escapeSeq = new byte[2];
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                continue;
            }
            if (mode && !jisx) {
                // 通常のJISコード(JIS X 0208)の場合 エスケープシーケンス(1B 24 42)ESC $ B
                // 2byteの場合
                bytes[0] = (byte) (in < 0x80 ? in : in - 0x100);
                try {
                    in = is.read();

                    if (in == -1)
                        break;

                    bytes[1] = (byte) (in < 0x80 ? in : in - 0x100);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                if (JIStoSJISMap.checkMap(bytes[0], bytes[1])) {
                    bytes = JIStoSJISMap.getSJISCode(bytes);

                    try {
                        bos.write(bytes);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else {
                    try {
                        byte[] bytes2 = new byte[8];
                        bytes2[0] = 0x1b;
                        bytes2[1] = 0x24;
                        bytes2[2] = 0x42;
                        bytes2[3] = bytes[0];
                        bytes2[4] = bytes[1];
                        bytes2[5] = 0x1b;
                        bytes2[6] = 0x28;
                        bytes2[7] = 0x42;

                        String str = new String(bytes2, "ISO2022JP");
                        byte[] sjisByte = str.getBytes("Windows-31J");
                        bos.write(sjisByte);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (mode && jisx) {
                // 半角カナJISコードの場合 エスケープシーケンス(1B 28 49)ESC ( I
                bytes[0] = (byte) (in < 0x80 ? in : in - 0x100);
                try {
                    byte[] bytes2 = new byte[7];
                    bytes2[0] = 0x1b;
                    bytes2[1] = 0x28;
                    bytes2[2] = 0x49;
                    bytes2[3] = bytes[0];
                    bytes2[4] = 0x1b;
                    bytes2[5] = 0x28;
                    bytes2[6] = 0x42;

                    String str = new String(bytes2, "ISO2022JP");

                    byte[] sjisByte = str.getBytes("Windows-31J");
                    bos.write(sjisByte);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                // 1byteの場合
                bos.write(in);
            }
        }

        byte[] resultByte = bos.toByteArray();
        String resultStr = "";
        try {
            resultStr = new String(resultByte, "Windows-31J");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    /**
     * SJISをJISに変更する
     * 
     * @param is
     *            　Input Stream
     * @return　変更した文字列
     */
    public static byte[] changeSJIStoJIS(InputStream is) {
        int in = -1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            in = is.read();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (in == -1) {
            return new byte[0];
        }

        boolean pre = false;

        while (true) {
            if (in >= 0x80) {
                if (!pre) {
                    // 漢字inをつけます
                    bos.write(0x1b);
                    bos.write(0x24);
                    bos.write(0x42);
                }
                pre = true;
                byte[] sjisBytes = new byte[2];

                sjisBytes[0] = (byte) in;

                try {
                    in = is.read();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    in = -1;
                }

                if (in == -1)
                    break;

                sjisBytes[1] = (byte) in;

                if (SJIStoJISMap.checkMap(sjisBytes[0], sjisBytes[1])) {
                    byte[] jisBytes = SJIStoJISMap.getSJISCode(sjisBytes);

                    try {
                        bos.write(jisBytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    String str;
                    try {
                        str = new String(sjisBytes, "Windows-31J");

                        byte[] jisBytes = str.getBytes("ISO2022JP");
                        if (jisBytes.length > 5) {
                            bos.write(jisBytes[3]);
                            bos.write(jisBytes[4]);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (pre) {
                    // 漢字outをつけます
                    bos.write(0x1b);
                    bos.write(0x28);
                    bos.write(0x42);
                }

                pre = false;
                bos.write(in);
            }

            try {
                in = is.read();
            } catch (IOException e) {
                e.printStackTrace();
                in = -1;
            }

            if (in == -1) {
                if (pre == true) {
                    // 漢字outをつけます
                    bos.write(0x1b);
                    bos.write(0x28);
                    bos.write(0x42);
                }

                break;
            }
        }
        return bos.toByteArray();
    }

    /**
     * utf8に固定 ,メールヘッダをデコードする。
     * 
     * @return String デコードした後文字列
     */
    public static String getEncodedUTF8Word(String str) {

        String result = "";

        String charset = "UTF-8";

        try {
            result = MimeUtility.encodeText(str, charset, "B");
        } catch (UnsupportedEncodingException e) {
            // サポートされないエンコーディングの場合、そのまま返してしまう
            result = str;
        }

        return result;
    }

    /**
     * メールヘッダをデコードする。
     * 
     * @param str
     *            　ヘッダ文字列
     * @param locale
     *            　ローカル
     * @param encoding
     *            　エンコード
     * @return String デコードした後文字列
     */
    public static String getEncodedWord(String str, Locale locale,
            String encoding) {
        int i, j;
        boolean kanji = false;
        String result = "";

        String charset = getCharSet(locale);
        String mimeEncoding = null;
        if (encoding != null) {
            charset = MailSysInfo.getMailCharset(encoding);
            if (charset == null) {
                charset = encoding;
            }
            mimeEncoding = MailSysInfo.getMailMimeEncoding(encoding);
        }
        if (mimeEncoding == null) {
            mimeEncoding = getMimeEncoding(locale);
        }

        if (charset.equalsIgnoreCase("iso-2022-jp")
            && mimeEncoding.equalsIgnoreCase("B")) {
            byte[] sjisBytes;

            try {
                sjisBytes = str.getBytes("Windows-31J");
            } catch (UnsupportedEncodingException e2) {
                // ありえないのが一応デフォルトエンコーディングで
                sjisBytes = str.getBytes();
            }
            byte[] bytes =
                Utility.changeSJIStoJIS(new ByteArrayInputStream(sjisBytes));

            for (i = 0; i < bytes.length; i++) {
                if (bytes[i] == 0x1b) {
                    if (i + 1 < bytes.length && bytes[i + 1] == 0x24) {
                        if (i + 2 < bytes.length && bytes[i + 2] == 0x42) {
                            kanji = true;
                            break;
                        }
                    }
                }
            }

            if (kanji) {
                byte[] mimeStart =
                    ("=?" + charset + "?" + mimeEncoding + "?").getBytes();
                byte[] mimeEnd = "?=".getBytes();
                byte[] crlfsp = "\n ".getBytes();

                JISSplitterForMime splitter = new JISSplitterForMime(30, 36);

                for (i = 0; i < bytes.length; i++) {
                    splitter.put(bytes[i]);
                }

                List<List<Byte>> list = splitter.getList();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                BASE64EncodeOutputStream b64os =
                    new BASE64EncodeOutputStream(bos);

                for (i = 0; i < list.size(); i++) {
                    try {
                        bos.write(mimeStart);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    List<Byte> jisList = (List<Byte>) list.get(i);
                    byte[] jisByte = new byte[jisList.size()];
                    for (j = 0; j < jisList.size(); j++) {
                        jisByte[j] = ((Byte) jisList.get(j)).byteValue();
                    }

                    try {
                        b64os.write(jisByte);
                        b64os.finish();

                        bos.write(mimeEnd);
                        if (i != list.size() - 1) {
                            bos.write(crlfsp);
                        }
                    } catch (IOException e1) {
                        // あえて無視
                        e1.printStackTrace();
                    }
                }

                try {
                    result = new String(bos.toByteArray(), "8859_1");
                } catch (UnsupportedEncodingException e) {
                    // あえて無視
                    e.printStackTrace();
                }

                try {
                    bos.close();
                } catch (IOException e1) {
                    // あえて無視
                    e1.printStackTrace();
                }
            } else {
                try {
                    result = new String(bytes, "8859_1");
                } catch (UnsupportedEncodingException e) {
                    // あえて無視
                    e.printStackTrace();
                }
            }
        } else {
            try {
                result = MimeUtility.encodeText(str, charset, mimeEncoding);
            } catch (UnsupportedEncodingException e) {
                // サポートされないエンコーディングの場合、そのまま返してしまう
                result = str;
            }
        }
        return result;
    }

    /**
     * 半角カナを含む文字列を、全角カナになおして返却します。
     * 
     * @param str
     *            置換元文字列
     * @param encoding
     *            エンコード
     * @return 半角カナが全角に変換された文字列
     */
    public static String changeSinglebyteKana(String str, String encoding) {
        if (str == null)
            return "";

        int in, in2;

        String charset = "Windows-31J";
        if (encoding != null) {
            charset = MailSysInfo.getMailCharset(encoding);
            if (charset == null) {
                charset = encoding;
            }
        }
        if (charset.equalsIgnoreCase("iso-2022-jp")) {
            try {
                byte[] bytes = str.getBytes("Windows-31J");
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                in = bis.read();

                while (in != -1) {
                    if (in >= 0x80) {
                        // 2byte文字 or 半角かな
                        byte b = (byte) (in < 0x80 ? in : in - 0x100);

                        if (SinglebyteKanaMap.checkMap(b)) {
                            // 半角かなの場合
                            byte[] result = SinglebyteKanaMap.getSJISCode(b);
                            try {
                                bos.write(result);
                            } catch (IOException e1) {
                                // 無視
                                e1.printStackTrace();
                            }
                        } else {
                            // 2byte 文字の場合
                            in2 = bis.read();
                            bos.write(in);
                            bos.write(in2);
                        }
                    } else {
                        // 半角英数字の場合
                        bos.write(in);
                    }

                    in = bis.read();
                }

                byte[] resultBytes = bos.toByteArray();

                return new String(resultBytes, "Windows-31J");
            } catch (UnsupportedEncodingException e) {
                // ほとんど起こり得ない
                return str;
            }
        } else {
            // 何か起きたときは、そのままリターン
            return str;
        }
    }

    /**
     * ローカルに対するCharsetを取得する
     * 
     * @param locale
     *            　ローカル
     * @return　String Charset
     */
    public static String getCharSet(Locale locale) {
        String charset = "iso-2022-jp";

        try {
            JavaMailPropertyHandler jm = JavaMailPropertyHandler.getInstance();
            charset = jm.getCharset(locale);
        } catch (JavaMailPropertyException e) {
            // 取れなければ、「iso-2022-jp」でいきます。
            charset = "iso-2022-jp";
        }
        return charset;
    }

    /**
     * ローカルに対するMimeEncodingを取得する
     * 
     * @param locale
     *            　ローカル
     * @return　MimeEncoding
     */
    public static String getMimeEncoding(Locale locale) {
        String mimeEncoding = "B";

        try {
            JavaMailPropertyHandler jm = JavaMailPropertyHandler.getInstance();
            mimeEncoding = jm.getMimeEncoding(locale);
        } catch (JavaMailPropertyException e) {
            // 取れなければ、「B」でいきます。
            mimeEncoding = "B";
        }
        return mimeEncoding;
    }

    /**
     * ローカルに対するContent　Encodingを取得する
     * 
     * @param locale
     *            　ローカル
     * @return　Content Encoding，7ビットまたは8ビット
     */
    public static String getContentEncoding(Locale locale) {
        String contentEncoding = "7bit";

        try {
            JavaMailPropertyHandler jm = JavaMailPropertyHandler.getInstance();
            contentEncoding = jm.getContentTransferEncoding(locale);
        } catch (JavaMailPropertyException e) {
            // 取れなければ、「7bit」でいきます。
            contentEncoding = "7bit";
        }
        return contentEncoding;
    }

    /**
     * ブランクかどうかを判断する
     * 
     * @param str
     *            　文字列
     * @return　ブランクかどうか
     */
    public static boolean isBlankOnly(String str) {
        int i;
        boolean result = true;

        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ' && str.charAt(i) != '　') {
                result = false;
            }
        }
        return result;
    }

    /**
     * 文字コードを変更する
     * 
     * @param s
     *            　変更される文字列
     * @param charset
     *            　文字コード
     * @return　変更した文字列
     */
    public static String convertCharset(String s, String charset) {
        if (s != null) {
            try {
                s = new String(s.getBytes("8859_1"), charset);
            } catch (Exception e) {
                // 変換に失敗したときはなにもしません
            }
        }
        return s;
    }
}
