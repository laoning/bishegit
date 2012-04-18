/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.extension.mail.common.exception;

import java.util.Locale;

import org.seasar.framework.util.StringUtil;

import com.biz_integral.foundation.core.message.MessageBuilder;

/**
 * アクセサ例外クラス
 */
public class AccessorException extends Exception {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -6050439866488778474L;

    /** エラーメッセージ */
    private String errorId;

    /** エラーメッセージ */
    private String errorMessage;

    /**
     * コンストラクタ
     * 
     * @param errorId
     *            エラーID
     * @param errorMessage
     *            エラーメッセージ
     * @param cause
     *            原因
     */
    public AccessorException(String errorId, String errorMessage,
            Throwable cause) {
        super(errorMessage, cause);
        this.errorId = errorId;
        this.errorMessage = errorMessage;
    }

    /**
     * コンストラクタ
     * 
     * @param errorId
     *            エラーID
     * @param errorMessage
     *            エラーメッセージ
     */
    private AccessorException(String errorId, String errorMessage) {
        super(errorMessage);
        this.errorId = errorId;
        this.errorMessage = errorMessage;
    }

    /**
     * AccessorExceptionインスタンスを生成します。
     * 
     * @param messageId
     *            メッセージID
     * @param locale
     *            ロケール
     * @param cause
     *            原因
     * @return AccessorException
     */
    public static AccessorException throwAccessorException(String messageId,
            Locale locale, Throwable cause) throws AccessorException {

        String message =
            MessageBuilder.create(messageId).toMessage().toText(locale);

        return new AccessorException(messageId, message, cause);
    }

    /**
     * AccessorExceptionインスタンスを生成します。
     * 
     * @param messageId
     *            メッセージID
     * @param localeId
     *            ロケールID
     * @param cause
     *            原因
     * @return AccessorException
     */
    public static AccessorException throwAccessorException(String messageId,
            String localeId, Throwable cause) throws AccessorException {

        Locale locale;
        if (!StringUtil.isEmpty(localeId)) {
            locale = new Locale(localeId);
        } else {
            locale = Locale.getDefault();
        }

        String message =
            MessageBuilder.create(messageId).toMessage().toText(locale);

        return new AccessorException(messageId, message, cause);
    }

    /**
     * AccessorExceptionインスタンスを生成します。
     * 
     * @param messageId
     *            メッセージID
     * @param cause
     *            原因
     * @return AccessorException
     */
    public static AccessorException throwAccessorException(String messageId,
            Throwable cause) throws AccessorException {

        Locale locale = Locale.getDefault();

        String message =
            MessageBuilder.create(messageId).toMessage().toText(locale);

        return new AccessorException(messageId, message, cause);
    }

    /**
     * AccessorExceptionインスタンスを生成します。
     * 
     * @param messageId
     *            メッセージID
     * @param localeId
     *            ロケールID
     * @return AccessorException
     */
    public static AccessorException throwAccessorException(String messageId,
            String localeId) throws AccessorException {

        Locale locale;
        if (!StringUtil.isEmpty(localeId)) {
            locale = new Locale(localeId);
        } else {
            locale = Locale.getDefault();
        }

        String message =
            MessageBuilder.create(messageId).toMessage().toText(locale);

        return new AccessorException(messageId, message);
    }

    /**
     * AccessorExceptionインスタンスを生成します。
     * 
     * @param messageId
     *            メッセージID
     * @param locale
     *            ロケール
     * @return AccessorException
     */
    public static AccessorException throwAccessorException(String messageId,
            Locale locale) throws AccessorException {

        String message =
            MessageBuilder.create(messageId).toMessage().toText(locale);

        return new AccessorException(messageId, message);
    }

    /**
     * AccessorExceptionインスタンスを生成します。
     * 
     * @param messageId
     *            メッセージID
     * @return AccessorException
     */
    public static AccessorException throwAccessorException(String messageId)
        throws AccessorException {

        Locale locale = Locale.getDefault();

        String message =
            MessageBuilder.create(messageId).toMessage().toText(locale);

        return new AccessorException(messageId, message);
    }

    /**
     * エラーIDを取得します。
     * 
     * @return エラーID
     */
    public String getErrorId() {
        return this.errorId;
    }

    /**
     * エラーメッセージを取得します。
     * 
     * @return エラーメッセージ
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
