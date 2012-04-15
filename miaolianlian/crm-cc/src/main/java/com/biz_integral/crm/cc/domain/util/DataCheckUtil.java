/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.util;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;

/**
 * 業務共通関数（データチェック）.<br>
 * データチェックに関する共通処理を行う。
 */
public class DataCheckUtil {

    /**
     * 数値桁数チェック.<br>
     * 正常な数値桁数以下であるかどうかチェックを行い。
     * 
     * @param targetData
     *            (チェック対象)入力値
     * @param intDigit
     *            ドメイン定義の整数桁数値(CONST)
     * @param decDigit
     *            ドメイン定義の少数桁数値(CONST)
     * @param message
     *            置換文字列
     */
    public static void checkNumericDigit(String targetData, long intDigit,
            long decDigit, String message) {

        // ローカル変数宣言 //
        boolean checkResult = true; // 桁数チェック結果
        StringTokenizer checkToken = null; // チェック用トークナイザ
        String result = targetData;

        try {

            // チェック対象入力値がNULLまたは空文字以外の場合
            if (StringUtil.isNotNull(result)) {

                checkToken = new StringTokenizer(result, "."); // チェック用トークナイザ

                // 入力値がマイナスの場合マイナスを除去する
                if (result.substring(0, 1).equals("-")
                    || result.substring(0, 1).equals("+")) {
                    result = result.substring(1, result.length());
                }

                // 左端の文字が．の場合0を付加する（文字列が．のみの場合を除く）
                if (!result.equals(".")) {
                    if (result.substring(0, 1).equals(".")) {
                        result = "0".concat(result);
                    }
                }

                // 入力値が整数の場合
                if (result.indexOf(".") == -1) {

                    // 入力値のレングスが整数桁数値を超える場合falseを返却
                    if (intDigit < result.length()) {
                        checkResult = false;

                        // 入力値のレングスが整数桁数値未満の場合trueを返却
                    } else if (result.length() < intDigit) {
                        checkResult = true;
                    }

                    // 入力値が小数の場合かつ整数桁数値、小数桁数値がNULLでない場合、かつドットが２つ以上ない場合
                } else if (0 < result.indexOf(".")
                    && String.valueOf(intDigit) != null
                    && String.valueOf(decDigit) != null
                    && checkToken.countTokens() <= 2) {

                    // 入力値の整数部分のレングスが整数桁数値より大きい、または小数桁数値より大きい場合
                    if (intDigit < result
                        .substring(0, result.indexOf("."))
                        .length()
                        || decDigit < result
                            .substring(result.indexOf(".") + 1)
                            .length()) {

                        // チェック結果にfalseを返却
                        checkResult = false;

                        // 入力値の整数部分のレングスが整数桁数値より大きい、かつ小数桁数値より大きい場合
                    } else if (intDigit < result.substring(
                        0,
                        result.indexOf(".")).length()
                        && decDigit < result
                            .substring(result.indexOf(".") + 1)
                            .length()) {

                        // チェック結果にfalseを返却
                        checkResult = false;

                        // それ以外の場合
                    } else {

                        // チェック結果にtrueを返却
                        checkResult = true;
                    }

                    // 入力値がそれ以外の場合
                } else {

                    // チェック結果にfalseを返却
                    checkResult = false;
                }

            }

        } catch (Exception e) {
            // 例外発生時、値の保護を行う。
            checkResult = false;
        }

        if (!checkResult) {
            ValidationResults validationResult = new ValidationResults();
            String parmeter = "";
            for (int i = 0; i < intDigit; i++) {
                parmeter = StringUtil.concat(parmeter, "9");
            }
            validationResult.add(new ValidationResult(MessageBuilder
                .create("E.BIZ.FC.14016")
                .addParameter(MessageBuilder.$(message))
                .addParameter("0")
                .addParameter(parmeter)
                .toMessage()));
            throw new ValidationException(validationResult);
        }
    }

    /**
     * 文字列桁数チェック.<br>
     * 正常な文字列桁数以下であるかどうかチェックを行い。
     * 
     * @param targetData
     *            (チェック対象)入力値
     * @param domainDigit
     *            ドメイン定義の桁数値(CONST)
     * @param message
     *            置換文字列
     */
    public static void checkStringDigit(String targetData, long domainDigit,
            String message) {

        // ローカル変数宣言 //
        boolean checkResult = false; // チェック結果格納用

        try {
            // チェック対象入力値がNULLまたは空文字の場合
            if (StringUtil.isNotNull(targetData)) {

                // チェック対象入力値のレングスが桁数値より大きい場合
                if (domainDigit < targetData.length()) {

                    // チェック結果にfalseを代入する
                    checkResult = false;

                    // それ以外の場合
                } else {
                    // チェック結果にtrueを代入する
                    checkResult = true;
                }
                // それ以外の場合
            } else {
                // チェック結果にtrueを代入する
                checkResult = true;
            }

        } catch (Exception e) {
            checkResult = false;
        }

        // チェック結果を返却する
        if (!checkResult) {
            ValidationResults validationResult = new ValidationResults();
            validationResult.add(new ValidationResult(MessageBuilder
                .create("E.BIZ.FC.14008")
                .addParameter(MessageBuilder.$(message))
                .addParameter(domainDigit)
                .toMessage()));
            throw new ValidationException(validationResult);
        }

    }

    /**
     * 半角英数字チェックチェック.<br>
     * 半角英数字チェックであるかどうかチェックを行い。
     * 
     * @param targetData
     *            (チェック対象)入力値
     * @param message
     *            置換文字列
     */
    public static void checkNumericAlphaString(String targetData, String message) {
        // ローカル変数宣言 //
        boolean result = true; // チェック結果
        Pattern pattern = Pattern.compile("([0-9a-zA-Z]*)"); // 正規表現

        try {
            // チェック対象入力値がNullまたは空文字でない場合
            if (StringUtil.isNotNull(targetData)) {

                // 正規表現に合っているかどうかチェックする
                if (!pattern.matcher(targetData).matches()) {
                    result = false;
                }

            }
        } catch (Exception e) {
            result = false;
        }

        // チェック結果を返却する
        if (!result) {
            ValidationResults validationResult = new ValidationResults();
            validationResult.add(new ValidationResult(MessageBuilder
                .create("E.BIZ.FC.14013")
                .addParameter(MessageBuilder.$(message))
                .toMessage()));
            throw new ValidationException(validationResult);
        }
    }

    /**
     * 数値型チェック.<br>
     * 正常な数値文字列であるかどうかチェックを行い、可否を返す。
     * 
     * @param targetData
     *            (チェック対象)入力値
     * @param message
     *            置換文字列
     */
    public static void isNumericType(String targetData, String message) {

        // ローカル変数宣言 //
        boolean result = true; // チェック結果
        // 正規表現
        Pattern pattern =
            Pattern
                .compile("([+-]?\\d*\\.?\\d+)|([+-]?\\d+\\.?\\d*)|([+-]?\\d{1,3}(,\\d{3})*\\.?\\d*)");

        try {
            // チェック対象入力値がNullまたは空文字でない場合
            if (StringUtil.isNotNull(targetData)) {

                // 正規表現に合っているかどうかチェックする
                if (!pattern.matcher(targetData).matches()) {
                    result = false;
                }

            }
        } catch (Exception e) {
            result = false;
        }

        // チェック結果を返却する
        if (!result) {
            ValidationResults validationResult = new ValidationResults();
            validationResult.add(new ValidationResult(MessageBuilder
                .create("E.BIZ.FC.14012")
                .addParameter(MessageBuilder.$(message))
                .toMessage()));
            throw new ValidationException(validationResult);
        }
    }

}
