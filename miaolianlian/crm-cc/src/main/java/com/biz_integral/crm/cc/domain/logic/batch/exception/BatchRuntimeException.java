/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.batch.exception;

import com.biz_integral.foundation.core.exception.ApplicationRuntimeException;
import com.biz_integral.foundation.core.message.Message;
import com.biz_integral.foundation.core.message.MessageBuilder;

/**
 * バッチ例外です。
 */
public class BatchRuntimeException extends ApplicationRuntimeException {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = -6701914050604949622L;

    /**
     * コンストラクタ
     * 
     * @param messageCd
     *            メッセージ
     */
    public BatchRuntimeException(String messageCd) {
        super(new Message(messageCd));
    }

    /**
     * コンストラクタ
     * 
     * @param message
     *            メッセージオブジェクト
     */
    public BatchRuntimeException(Message message) {
        super(message);
    }

    /**
     * コンストラクタ
     * 
     * @param exception
     *            例外
     * @param messageCd
     *            メッセージコード
     */
    public BatchRuntimeException(Exception exception, String messageCd) {
        super(exception.getCause(), MessageBuilder
            .create(messageCd)
            .toMessage());
    }

    /**
     * コンストラクタ
     * 
     * @param exception
     *            例外
     * @param message
     *            メッセージオブジェクト
     */
    public BatchRuntimeException(Exception exception, Message... message) {
        super(exception.getCause(), message);
    }

}
