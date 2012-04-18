/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.util;

import java.util.HashMap;

/**
 * 半角カナを判定して、SJISの全角カナに変換するためのクラス
 * 
 */
public class SinglebyteKanaMap {
    private static HashMap<String, String> charMap =
        new HashMap<String, String>();

    static {
        // コードリスト
        charMap.put("a1", "8142");
        charMap.put("a2", "8175");
        charMap.put("a3", "8176");
        charMap.put("a4", "8141");
        charMap.put("a5", "8145");
        charMap.put("a6", "8392");
        charMap.put("a7", "8340");
        charMap.put("a8", "8342");
        charMap.put("a9", "8344");
        charMap.put("aa", "8346");
        charMap.put("ab", "8348");
        charMap.put("ac", "8383");
        charMap.put("ad", "8385");
        charMap.put("ae", "8387");
        charMap.put("af", "8362");
        charMap.put("b0", "815b");
        charMap.put("b1", "8341");
        charMap.put("b2", "8343");
        charMap.put("b3", "8345");
        charMap.put("b4", "8347");
        charMap.put("b5", "8349");
        charMap.put("b6", "834a");
        charMap.put("b7", "834c");
        charMap.put("b8", "834e");
        charMap.put("b9", "8350");
        charMap.put("ba", "8352");
        charMap.put("bb", "8354");
        charMap.put("bc", "8356");
        charMap.put("bd", "8358");
        charMap.put("be", "835a");
        charMap.put("bf", "835c");
        charMap.put("c0", "835e");
        charMap.put("c1", "8360");
        charMap.put("c2", "8363");
        charMap.put("c3", "8365");
        charMap.put("c4", "8367");
        charMap.put("c5", "8369");
        charMap.put("c6", "836a");
        charMap.put("c7", "836b");
        charMap.put("c8", "836c");
        charMap.put("c9", "836d");
        charMap.put("ca", "836e");
        charMap.put("cb", "8371");
        charMap.put("cc", "8374");
        charMap.put("cd", "8377");
        charMap.put("ce", "837a");
        charMap.put("cf", "837d");
        charMap.put("d0", "837e");
        charMap.put("d1", "8380");
        charMap.put("d2", "8381");
        charMap.put("d3", "8382");
        charMap.put("d4", "8384");
        charMap.put("d5", "8386");
        charMap.put("d6", "8388");
        charMap.put("d7", "8389");
        charMap.put("d8", "838a");
        charMap.put("d9", "838b");
        charMap.put("da", "838c");
        charMap.put("db", "838d");
        charMap.put("dc", "838f");
        charMap.put("dd", "8393");
        charMap.put("de", "814a");
        charMap.put("df", "814b");
    }

    /**
     * 半角カナかどうかをチェックする
     * 
     * @param upper
     *            半角カナバイト
     * @return 半角カナかどうか
     */
    public static boolean checkMap(byte upper) {
        int upperInt = (int) upper;

        if (upperInt < 0)
            upperInt += 256;

        String key = Integer.toHexString(upperInt);

        return charMap.containsKey(key);
    }

    /**
     * 半角カナをSJISの全角カナに変更する。
     * 
     * @param bytes
     *            半角カナバイト
     * @return SJISSJISの全角カナバイト
     */
    public static byte[] getSJISCode(byte bytes) {
        byte[] result = new byte[2];

        int upperInt = (int) bytes;

        if (upperInt < 0)
            upperInt += 256;

        String key = Integer.toHexString(upperInt);
        String value = (String) charMap.get(key);

        String upperSJISstr = value.substring(0, 2);
        String lowerSJISstr = value.substring(2);

        int upperSJISint = Integer.parseInt(upperSJISstr, 16);
        int lowerSJISint = Integer.parseInt(lowerSJISstr, 16);

        byte upperSJISbyte =
            (byte) (upperSJISint < 0x80 ? upperSJISint : upperSJISint - 0x100);
        byte lowerSJISbyte =
            (byte) (lowerSJISint < 0x80 ? lowerSJISint : lowerSJISint - 0x100);

        result[0] = upperSJISbyte;
        result[1] = lowerSJISbyte;

        return result;
    }
}
