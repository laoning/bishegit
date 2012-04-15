/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.util;

import java.util.ArrayList;
import java.util.List;

/**
 * MIMEのEncodedWordを作成するためにJIS文字列を整形するための クラスです。
 * 
 */
public class JISSplitterForMime {
    List<List<Byte>> top = new ArrayList<List<Byte>>();
    List<Byte> second = new ArrayList<Byte>();

    int count = 0;

    boolean kanjiFlg = false;
    boolean inKanji = false;
    boolean inEscape = false;
    boolean blnEscAddedFlg = false;
    byte tempByte = 0;

    byte[] escape = { 0, 0, 0 };

    int firstSize;
    int secondSize;

    /**
     * コンストラクタ
     * 
     * @param firstSize
     * @param secondSize
     */
    public JISSplitterForMime(int firstSize, int secondSize) {
        super();

        this.firstSize = firstSize;
        this.secondSize = secondSize;
        this.count = firstSize;
    }

    /**
     * バイトを挿入する。
     * 
     * @param b
     *            バイト
     */
    public void put(byte b) {
        if (b == 0x1b)
            inEscape = true;

        if (second.isEmpty() && kanjiFlg) {
            // 改行直後で漢字モード中の場合は漢字Inを最初に挿入します。
            if (!blnEscAddedFlg) {
                second.add(new Byte((byte) 0x1b));
                second.add(new Byte((byte) 0x24));
                second.add(new Byte((byte) 0x42));
                blnEscAddedFlg = true;
            }
        }

        // 漢字モードかどうかの判定
        escape[0] = escape[1];
        escape[1] = escape[2];
        escape[2] = b;

        if (escape[0] == 0x1b && escape[1] == 0x24 && escape[2] == 0x42)
            kanjiFlg = true;
        if (escape[0] == 0x1b && escape[1] == 0x28 && escape[2] == 0x42)
            kanjiFlg = false;

        // 列に入れていく
        if (inEscape) {
            if (escape[0] == 0x1b && escape[1] == 0x24 && escape[2] == 0x42) {
                if (!blnEscAddedFlg) {
                    second.add(new Byte((byte) 0x1b));
                    second.add(new Byte((byte) 0x24));
                    second.add(new Byte((byte) 0x42));
                    blnEscAddedFlg = true;
                }
                inEscape = false;
            } else if (escape[0] == 0x1b
                && escape[1] == 0x28
                && escape[2] == 0x42) {
                if (!blnEscAddedFlg) {
                    second.add(new Byte((byte) 0x1b));
                    second.add(new Byte((byte) 0x28));
                    second.add(new Byte((byte) 0x42));
                    blnEscAddedFlg = true;
                }
                inEscape = false;
            }
        } else if (kanjiFlg && inKanji) {
            if (second.isEmpty()) {
                second.add(new Byte((byte) 0x1b));
                second.add(new Byte((byte) 0x24));
                second.add(new Byte((byte) 0x42));
            }
            second.add(new Byte(tempByte));
            second.add(new Byte(b));
            inKanji = false;
            blnEscAddedFlg = false;
        } else if (kanjiFlg && !inKanji) {
            tempByte = b;
            inKanji = true;
        } else {
            // 漢字じゃないときはそのまま
            second.add(new Byte(b));
            blnEscAddedFlg = false;
        }

        // 長さ判定をして折り返します。
        if (checkLength()) {
            if (kanjiFlg) {
                // 漢字モード中の時は漢字Outを最後に挿入します。
                if (!blnEscAddedFlg) {
                    second.add(new Byte((byte) 0x1b));
                    second.add(new Byte((byte) 0x28));
                    second.add(new Byte((byte) 0x42));
                    blnEscAddedFlg = true;
                }
            }

            top.add(second);

            second = new ArrayList<Byte>();
            this.count = secondSize;
        }
    }

    /**
     * 長さをチェックする
     * 
     * @return 長さをチェックフラグ
     */
    public boolean checkLength() {
        boolean result = false;

        if (kanjiFlg) {
            if (inEscape) {
                // エスケープシーケンス書き込み中
                result = false;
            } else if (inKanji) {
                // 漢字書き込み中
                result = false;
            } else {
                if (count - second.size() <= 3) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } else {
            if (count - second.size() <= 0) {
                result = true;
            } else {
                result = false;
            }
        }

        return result;
    }

    /**
     * トップリストを取得する
     * 
     * @return　トップリスト
     */
    public List<List<Byte>> getList() {
        if (second.size() > 0 && !checkBlank())
            top.add(second);

        return this.top;
    }

    /**
     * ブランクをチェックする。
     * 
     * @return ブランクかどうか
     */
    public boolean checkBlank() {
        if (second.size() == 6
            && ((Byte) second.get(0)).byteValue() == 0x1b
            && ((Byte) second.get(1)).byteValue() == 0x24
            && ((Byte) second.get(2)).byteValue() == 0x42
            && ((Byte) second.get(3)).byteValue() == 0x1b
            && ((Byte) second.get(4)).byteValue() == 0x28
            && ((Byte) second.get(5)).byteValue() == 0x42) {
            return true;
        } else {
            return false;
        }
    }

}
