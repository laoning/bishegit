/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 共通関数（日付）クラス（DateUtil）.<br>
 * 日付に関する共通処理を行う。
 */
public class DateUtil {

    /**
     * 日計算.<br>
     * 計算対象日付に引数で渡された日数を加えた日付を返す。
     * 
     * @param targetDate
     *            計算対象日付
     * @param addNumber
     *            加算日数（負数：過去、正数：未来）
     * @return 計算後日付
     */
    public static Date getAddDay(Date targetDate, int addNumber) {

        // ローカル変数宣言 //
        Calendar calObject = null; // Calendarクラスデータ型変数
        Date resultDate = null; // 返却日付格納用

        // 計算対象日付に引数の日数を加えた日付を取得する
        // オブジェクトを生成し、日付を設定する
        calObject = Calendar.getInstance();
        calObject.setTime(targetDate);

        // 加算日数を加える
        calObject.add(Calendar.DATE, addNumber);

        // 計算後日付を取得する
        resultDate = calObject.getTime();

        // 計算後の日付を返す
        return resultDate;
    }

}
