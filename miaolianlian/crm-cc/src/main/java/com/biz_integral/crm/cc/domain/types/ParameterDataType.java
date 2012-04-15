/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.NamedEnumAware;

import com.biz_integral.foundation.core.types.EnumUtil;

/**
 * パラメータ型区分を表す列挙です。<br/>
 * <p>
 * パラメータの種類を分類するための区分
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum ParameterDataType implements NamedEnumAware {

    /**
     * 未設定<br/>
     * <p>
     * 未設定
     * </p>
     */
    UNSET("0"),

    /**
     * 半角数字<br/>
     * <p>
     * 半角数字
     * </p>
     */
    ONEBYTENUMBER("1"),

    /**
     * 半角英数字<br/>
     * <p>
     * 半角英数字
     * </p>
     */
    ONEBYTEENGLISHNUMBER("2"),

    /**
     * 全角文字<br/>
     * <p>
     * 全角文字
     * </p>
     */
    TWOBYTECHARACTERS("3"),

    /**
     * 半角カナ<br/>
     * <p>
     * 半角カナ
     * </p>
     */
    ONEBYTEKANA("4"),
    ;

    /** 区分値 */
    private String value;

    /**
     * ParameterdataTypeを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private ParameterDataType(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value() {
        return value;
    }

    /**
     * <p>
     * 区分値に対応する{@link ParameterDataType }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する区分値の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * @param value 区分値
     * @return 対応する区分値の列挙定数。
     *         区分値に対応する定数が定義されていない場合には{@code null}。
     */
    public static ParameterDataType getEnum(String value) {
        return EnumUtil.toNamedEnum(ParameterDataType.class , value);
    }
}