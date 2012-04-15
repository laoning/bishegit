/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.util;

import java.util.HashMap;

/**
 * JISをSJISに変更マップ
 * 
 */
public class JIStoSJISMap {
    private static HashMap<String, String> charMap =
        new HashMap<String, String>();

    static {
        // NEC特殊文字
        charMap.put("213d", "815c");
        charMap.put("2d21", "8740");
        charMap.put("2d22", "8741");
        charMap.put("2d23", "8742");
        charMap.put("2d24", "8743");
        charMap.put("2d25", "8744");
        charMap.put("2d26", "8745");
        charMap.put("2d27", "8746");
        charMap.put("2d28", "8747");
        charMap.put("2d29", "8748");
        charMap.put("2d2a", "8749");
        charMap.put("2d2b", "874a");
        charMap.put("2d2c", "874b");
        charMap.put("2d2d", "874c");
        charMap.put("2d2e", "874d");
        charMap.put("2d2f", "874e");
        charMap.put("2d30", "874f");
        charMap.put("2d31", "8750");
        charMap.put("2d32", "8751");
        charMap.put("2d33", "8752");
        charMap.put("2d34", "8753");
        charMap.put("2d35", "8754");
        charMap.put("2d36", "8755");
        charMap.put("2d37", "8756");
        charMap.put("2d38", "8757");
        charMap.put("2d39", "8758");
        charMap.put("2d3a", "8759");
        charMap.put("2d3b", "875a");
        charMap.put("2d3c", "875b");
        charMap.put("2d3d", "875c");
        charMap.put("2d3e", "875d");
        charMap.put("2d40", "875f");
        charMap.put("2d41", "8760");
        charMap.put("2d42", "8761");
        charMap.put("2d43", "8762");
        charMap.put("2d44", "8763");
        charMap.put("2d45", "8764");
        charMap.put("2d46", "8765");
        charMap.put("2d47", "8766");
        charMap.put("2d48", "8767");
        charMap.put("2d49", "8768");
        charMap.put("2d4a", "8769");
        charMap.put("2d4b", "876a");
        charMap.put("2d4c", "876b");
        charMap.put("2d4d", "876c");
        charMap.put("2d4e", "876d");
        charMap.put("2d4f", "876e");
        charMap.put("2d50", "876f");
        charMap.put("2d51", "8770");
        charMap.put("2d52", "8771");
        charMap.put("2d53", "8772");
        charMap.put("2d54", "8773");
        charMap.put("2d55", "8774");
        charMap.put("2d56", "8775");
        charMap.put("2d5f", "877e");
        charMap.put("2d60", "8780");
        charMap.put("2d61", "8781");
        charMap.put("2d62", "8782");
        charMap.put("2d63", "8783");
        charMap.put("2d64", "8784");
        charMap.put("2d65", "8785");
        charMap.put("2d66", "8786");
        charMap.put("2d67", "8787");
        charMap.put("2d68", "8788");
        charMap.put("2d69", "8789");
        charMap.put("2d6a", "878a");
        charMap.put("2d6b", "878b");
        charMap.put("2d6c", "878c");
        charMap.put("2d6d", "878d");
        charMap.put("2d6e", "878e");
        charMap.put("2d6f", "878f");
        charMap.put("2d70", "8790");
        charMap.put("2d71", "8791");
        charMap.put("2d72", "8792");
        charMap.put("2d73", "8793");
        charMap.put("2d74", "8794");
        charMap.put("2d75", "8795");
        charMap.put("2d76", "8796");
        charMap.put("2d77", "8797");
        charMap.put("2d78", "8798");
        charMap.put("2d79", "8799");
        charMap.put("2d7a", "879a");
        charMap.put("2d7b", "879b");
        charMap.put("2d7c", "879c");

        // 波文字など
        charMap.put("2141", "8160");
        charMap.put("2142", "8161");
        charMap.put("215d", "817c");

        // 第2水準
        charMap.put("7921", "ed40");
        charMap.put("7922", "ed41");
        charMap.put("7923", "ed42");
        charMap.put("7924", "ed43");
        charMap.put("7925", "ed44");
        charMap.put("7926", "ed45");
        charMap.put("7927", "ed46");
        charMap.put("7928", "ed47");
        charMap.put("7929", "ed48");
        charMap.put("792a", "ed49");
        charMap.put("792b", "ed4a");
        charMap.put("792c", "ed4b");
        charMap.put("792d", "ed4c");
        charMap.put("792e", "ed4d");
        charMap.put("792f", "ed4e");
        charMap.put("7930", "ed4f");
        charMap.put("7931", "ed50");
        charMap.put("7932", "ed51");
        charMap.put("7933", "ed52");
        charMap.put("7934", "ed53");
        charMap.put("7935", "ed54");
        charMap.put("7936", "ed55");
        charMap.put("7937", "ed56");
        charMap.put("7938", "ed57");
        charMap.put("7939", "ed58");
        charMap.put("793a", "ed59");
        charMap.put("793b", "ed5a");
        charMap.put("793c", "ed5b");
        charMap.put("793d", "ed5c");
        charMap.put("793e", "ed5d");
        charMap.put("793f", "ed5e");
        charMap.put("7940", "ed5f");
        charMap.put("7941", "ed60");
        charMap.put("7942", "ed61");
        charMap.put("7943", "ed62");
        charMap.put("7944", "ed63");
        charMap.put("7945", "ed64");
        charMap.put("7946", "ed65");
        charMap.put("7947", "ed66");
        charMap.put("7948", "ed67");
        charMap.put("7949", "ed68");
        charMap.put("794a", "ed69");
        charMap.put("794b", "ed6a");
        charMap.put("794c", "ed6b");
        charMap.put("794d", "ed6c");
        charMap.put("794e", "ed6d");
        charMap.put("794f", "ed6e");
        charMap.put("7950", "ed6f");
        charMap.put("7951", "ed70");
        charMap.put("7952", "ed71");
        charMap.put("7953", "ed72");
        charMap.put("7954", "ed73");
        charMap.put("7955", "ed74");
        charMap.put("7956", "ed75");
        charMap.put("7957", "ed76");
        charMap.put("7958", "ed77");
        charMap.put("7959", "ed78");
        charMap.put("795a", "ed79");
        charMap.put("795b", "ed7a");
        charMap.put("795c", "ed7b");
        charMap.put("795d", "ed7c");
        charMap.put("795e", "ed7d");
        charMap.put("795f", "ed7e");
        charMap.put("7960", "ed80");
        charMap.put("7961", "ed81");
        charMap.put("7962", "ed82");
        charMap.put("7963", "ed83");
        charMap.put("7964", "ed84");
        charMap.put("7965", "ed85");
        charMap.put("7966", "ed86");
        charMap.put("7967", "ed87");
        charMap.put("7968", "ed88");
        charMap.put("7969", "ed89");
        charMap.put("796a", "ed8a");
        charMap.put("796b", "ed8b");
        charMap.put("796c", "ed8c");
        charMap.put("796d", "ed8d");
        charMap.put("796e", "ed8e");
        charMap.put("796f", "ed8f");
        charMap.put("7970", "ed90");
        charMap.put("7971", "ed91");
        charMap.put("7972", "ed92");
        charMap.put("7973", "ed93");
        charMap.put("7974", "ed94");
        charMap.put("7975", "ed95");
        charMap.put("7976", "ed96");
        charMap.put("7977", "ed97");
        charMap.put("7978", "ed98");
        charMap.put("7979", "ed99");
        charMap.put("797a", "ed9a");
        charMap.put("797b", "ed9b");
        charMap.put("797c", "ed9c");
        charMap.put("797d", "ed9d");
        charMap.put("797e", "ed9e");
        charMap.put("7a21", "ed9f");
        charMap.put("7a22", "eda0");
        charMap.put("7a23", "eda1");
        charMap.put("7a24", "eda2");
        charMap.put("7a25", "eda3");
        charMap.put("7a26", "eda4");
        charMap.put("7a27", "eda5");
        charMap.put("7a28", "eda6");
        charMap.put("7a29", "eda7");
        charMap.put("7a2a", "eda8");
        charMap.put("7a2b", "eda9");
        charMap.put("7a2c", "edaa");
        charMap.put("7a2d", "edab");
        charMap.put("7a2e", "edac");
        charMap.put("7a2f", "edad");
        charMap.put("7a30", "edae");
        charMap.put("7a31", "edaf");
        charMap.put("7a32", "edb0");
        charMap.put("7a33", "edb1");
        charMap.put("7a34", "edb2");
        charMap.put("7a35", "edb3");
        charMap.put("7a36", "edb4");
        charMap.put("7a37", "edb5");
        charMap.put("7a38", "edb6");
        charMap.put("7a39", "edb7");
        charMap.put("7a3a", "edb8");
        charMap.put("7a3b", "edb9");
        charMap.put("7a3c", "edba");
        charMap.put("7a3d", "edbb");
        charMap.put("7a3e", "edbc");
        charMap.put("7a3f", "edbd");
        charMap.put("7a40", "edbe");
        charMap.put("7a41", "edbf");
        charMap.put("7a42", "edc0");
        charMap.put("7a43", "edc1");
        charMap.put("7a44", "edc2");
        charMap.put("7a45", "edc3");
        charMap.put("7a46", "edc4");
        charMap.put("7a47", "edc5");
        charMap.put("7a48", "edc6");
        charMap.put("7a49", "edc7");
        charMap.put("7a4a", "edc8");
        charMap.put("7a4b", "edc9");
        charMap.put("7a4c", "edca");
        charMap.put("7a4d", "edcb");
        charMap.put("7a4e", "edcc");
        charMap.put("7a4f", "edcd");
        charMap.put("7a50", "edce");
        charMap.put("7a51", "edcf");
        charMap.put("7a52", "edd0");
        charMap.put("7a53", "edd1");
        charMap.put("7a54", "edd2");
        charMap.put("7a55", "edd3");
        charMap.put("7a56", "edd4");
        charMap.put("7a57", "edd5");
        charMap.put("7a58", "edd6");
        charMap.put("7a59", "edd7");
        charMap.put("7a5a", "edd8");
        charMap.put("7a5b", "edd9");
        charMap.put("7a5c", "edda");
        charMap.put("7a5d", "eddb");
        charMap.put("7a5e", "eddc");
        charMap.put("7a5f", "eddd");
        charMap.put("7a60", "edde");
        charMap.put("7a61", "eddf");
        charMap.put("7a62", "ede0");
        charMap.put("7a63", "ede1");
        charMap.put("7a64", "ede2");
        charMap.put("7a65", "ede3");
        charMap.put("7a66", "ede4");
        charMap.put("7a67", "ede5");
        charMap.put("7a68", "ede6");
        charMap.put("7a69", "ede7");
        charMap.put("7a6a", "ede8");
        charMap.put("7a6b", "ede9");
        charMap.put("7a6c", "edea");
        charMap.put("7a6d", "edeb");
        charMap.put("7a6e", "edec");
        charMap.put("7a6f", "eded");
        charMap.put("7a70", "edee");
        charMap.put("7a71", "edef");
        charMap.put("7a72", "edf0");
        charMap.put("7a73", "edf1");
        charMap.put("7a74", "edf2");
        charMap.put("7a75", "edf3");
        charMap.put("7a76", "edf4");
        charMap.put("7a77", "edf5");
        charMap.put("7a78", "edf6");
        charMap.put("7a79", "edf7");
        charMap.put("7a7a", "edf8");
        charMap.put("7a7b", "edf9");
        charMap.put("7a7c", "edfa");
        charMap.put("7a7d", "edfb");
        charMap.put("7a7e", "edfc");
        charMap.put("7b21", "ee40");
        charMap.put("7b22", "ee41");
        charMap.put("7b23", "ee42");
        charMap.put("7b24", "ee43");
        charMap.put("7b25", "ee44");
        charMap.put("7b26", "ee45");
        charMap.put("7b27", "ee46");
        charMap.put("7b28", "ee47");
        charMap.put("7b29", "ee48");
        charMap.put("7b2a", "ee49");
        charMap.put("7b2b", "ee4a");
        charMap.put("7b2c", "ee4b");
        charMap.put("7b2d", "ee4c");
        charMap.put("7b2e", "ee4d");
        charMap.put("7b2f", "ee4e");
        charMap.put("7b30", "ee4f");
        charMap.put("7b31", "ee50");
        charMap.put("7b32", "ee51");
        charMap.put("7b33", "ee52");
        charMap.put("7b34", "ee53");
        charMap.put("7b35", "ee54");
        charMap.put("7b36", "ee55");
        charMap.put("7b37", "ee56");
        charMap.put("7b38", "ee57");
        charMap.put("7b39", "ee58");
        charMap.put("7b3a", "ee59");
        charMap.put("7b3b", "ee5a");
        charMap.put("7b3c", "ee5b");
        charMap.put("7b3d", "ee5c");
        charMap.put("7b3e", "ee5d");
        charMap.put("7b3f", "ee5e");
        charMap.put("7b40", "ee5f");
        charMap.put("7b41", "ee60");
        charMap.put("7b42", "ee61");
        charMap.put("7b43", "ee62");
        charMap.put("7b44", "ee63");
        charMap.put("7b45", "ee64");
        charMap.put("7b46", "ee65");
        charMap.put("7b47", "ee66");
        charMap.put("7b48", "ee67");
        charMap.put("7b49", "ee68");
        charMap.put("7b4a", "ee69");
        charMap.put("7b4b", "ee6a");
        charMap.put("7b4c", "ee6b");
        charMap.put("7b4d", "ee6c");
        charMap.put("7b4e", "ee6d");
        charMap.put("7b4f", "ee6e");
        charMap.put("7b50", "ee6f");
        charMap.put("7b51", "ee70");
        charMap.put("7b52", "ee71");
        charMap.put("7b53", "ee72");
        charMap.put("7b54", "ee73");
        charMap.put("7b55", "ee74");
        charMap.put("7b56", "ee75");
        charMap.put("7b57", "ee76");
        charMap.put("7b58", "ee77");
        charMap.put("7b59", "ee78");
        charMap.put("7b5a", "ee79");
        charMap.put("7b5b", "ee7a");
        charMap.put("7b5c", "ee7b");
        charMap.put("7b5d", "ee7c");
        charMap.put("7b5e", "ee7d");
        charMap.put("7b5f", "ee7e");
        charMap.put("7b60", "ee80");
        charMap.put("7b61", "ee81");
        charMap.put("7b62", "ee82");
        charMap.put("7b63", "ee83");
        charMap.put("7b64", "ee84");
        charMap.put("7b65", "ee85");
        charMap.put("7b66", "ee86");
        charMap.put("7b67", "ee87");
        charMap.put("7b68", "ee88");
        charMap.put("7b69", "ee89");
        charMap.put("7b6a", "ee8a");
        charMap.put("7b6b", "ee8b");
        charMap.put("7b6c", "ee8c");
        charMap.put("7b6d", "ee8d");
        charMap.put("7b6e", "ee8e");
        charMap.put("7b6f", "ee8f");
        charMap.put("7b70", "ee90");
        charMap.put("7b71", "ee91");
        charMap.put("7b72", "ee92");
        charMap.put("7b73", "ee93");
        charMap.put("7b74", "ee94");
        charMap.put("7b75", "ee95");
        charMap.put("7b76", "ee96");
        charMap.put("7b77", "ee97");
        charMap.put("7b78", "ee98");
        charMap.put("7b79", "ee99");
        charMap.put("7b7a", "ee9a");
        charMap.put("7b7b", "ee9b");
        charMap.put("7b7c", "ee9c");
        charMap.put("7b7d", "ee9d");
        charMap.put("7b7e", "ee9e");
        charMap.put("7c21", "ee9f");
        charMap.put("7c22", "eea0");
        charMap.put("7c23", "eea1");
        charMap.put("7c24", "eea2");
        charMap.put("7c25", "eea3");
        charMap.put("7c26", "eea4");
        charMap.put("7c27", "eea5");
        charMap.put("7c28", "eea6");
        charMap.put("7c29", "eea7");
        charMap.put("7c2a", "eea8");
        charMap.put("7c2b", "eea9");
        charMap.put("7c2c", "eeaa");
        charMap.put("7c2d", "eeab");
        charMap.put("7c2e", "eeac");
        charMap.put("7c2f", "eead");
        charMap.put("7c30", "eeae");
        charMap.put("7c31", "eeaf");
        charMap.put("7c32", "eeb0");
        charMap.put("7c33", "eeb1");
        charMap.put("7c34", "eeb2");
        charMap.put("7c35", "eeb3");
        charMap.put("7c36", "eeb4");
        charMap.put("7c37", "eeb5");
        charMap.put("7c38", "eeb6");
        charMap.put("7c39", "eeb7");
        charMap.put("7c3a", "eeb8");
        charMap.put("7c3b", "eeb9");
        charMap.put("7c3c", "eeba");
        charMap.put("7c3d", "eebb");
        charMap.put("7c3e", "eebc");
        charMap.put("7c3f", "eebd");
        charMap.put("7c40", "eebe");
        charMap.put("7c41", "eebf");
        charMap.put("7c42", "eec0");
        charMap.put("7c43", "eec1");
        charMap.put("7c44", "eec2");
        charMap.put("7c45", "eec3");
        charMap.put("7c46", "eec4");
        charMap.put("7c47", "eec5");
        charMap.put("7c48", "eec6");
        charMap.put("7c49", "eec7");
        charMap.put("7c4a", "eec8");
        charMap.put("7c4b", "eec9");
        charMap.put("7c4c", "eeca");
        charMap.put("7c4d", "eecb");
        charMap.put("7c4e", "eecc");
        charMap.put("7c4f", "eecd");
        charMap.put("7c50", "eece");
        charMap.put("7c51", "eecf");
        charMap.put("7c52", "eed0");
        charMap.put("7c53", "eed1");
        charMap.put("7c54", "eed2");
        charMap.put("7c55", "eed3");
        charMap.put("7c56", "eed4");
        charMap.put("7c57", "eed5");
        charMap.put("7c58", "eed6");
        charMap.put("7c59", "eed7");
        charMap.put("7c5a", "eed8");
        charMap.put("7c5b", "eed9");
        charMap.put("7c5c", "eeda");
        charMap.put("7c5d", "eedb");
        charMap.put("7c5e", "eedc");
        charMap.put("7c5f", "eedd");
        charMap.put("7c60", "eede");
        charMap.put("7c61", "eedf");
        charMap.put("7c62", "eee0");
        charMap.put("7c63", "eee1");
        charMap.put("7c64", "eee2");
        charMap.put("7c65", "eee3");
        charMap.put("7c66", "eee4");
        charMap.put("7c67", "eee5");
        charMap.put("7c68", "eee6");
        charMap.put("7c69", "eee7");
        charMap.put("7c6a", "eee8");
        charMap.put("7c6b", "eee9");
        charMap.put("7c6c", "eeea");
        charMap.put("7c6d", "eeeb");
        charMap.put("7c6e", "eeec");
        charMap.put("7c71", "eeef");
        charMap.put("7c72", "eef0");
        charMap.put("7c73", "eef1");
        charMap.put("7c74", "eef2");
        charMap.put("7c75", "eef3");
        charMap.put("7c76", "eef4");
        charMap.put("7c77", "eef5");
        charMap.put("7c78", "eef6");
        charMap.put("7c79", "eef7");
        charMap.put("7c7a", "eef8");
        charMap.put("7c7b", "eef9");
        charMap.put("7c7c", "eefa");
        charMap.put("7c7e", "eefc");

        // 鶴亀メール対応？？
        charMap.put("972a", "eeea");
        charMap.put("972b", "eeeb");
        charMap.put("972c", "eeec");
    }

    /**
     * 文字は特殊文字かどうかをチェックする
     * 
     * @param upper
     *            文字のアップバイト
     * @param lower
     *            文字のローバイト
     * @return 特殊文字かどうか
     */
    public static boolean checkMap(byte upper, byte lower) {
        int upperInt = (int) upper;
        int lowerInt = (int) lower;

        if (upperInt < 0)
            upperInt += 256;
        if (lowerInt < 0)
            lowerInt += 256;

        String key =
            Integer.toHexString(upperInt) + Integer.toHexString(lowerInt);

        return charMap.containsKey(key);
    }

    /**
     * JISをSJISに変更する。
     * 
     * @param bytes
     *            JIS文字バイト
     * @return SJIS文字バイト
     */
    public static byte[] getSJISCode(byte[] bytes) {
        byte[] result = new byte[2];

        int upperInt = (int) bytes[0];
        int lowerInt = (int) bytes[1];

        if (upperInt < 0)
            upperInt += 256;
        if (lowerInt < 0)
            lowerInt += 256;

        String key =
            Integer.toHexString(upperInt) + Integer.toHexString(lowerInt);
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
