/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.NamedEnumAware;

import com.biz_integral.foundation.core.types.EnumUtil;

/**
 * UseTypeを表す列挙です。<br/>
 * <p>
 * 利用を判断する（全般的に使用）
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum UseType implements NamedEnumAware {

    /**
     * 利用しない<br/>
     * <p>
     * 利用しない
     * </p>
     */
    NONUSE("0"),

    /**
     * 利用する<br/>
     * <p>
     * 利用する
     * </p>
     */
    USE("1"),
    ;

    /** 区分値 */
    private String value;

    /**
     * UseTypeを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private UseType(String value) {
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
     * 区分値に対応する{@link UseType }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する区分値の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * @param value 区分値
     * @return 対応する区分値の列挙定数。
     *         区分値に対応する定数が定義されていない場合には{@code null}。
     */
    public static UseType getEnum(String value) {
        return EnumUtil.toNamedEnum(UseType.class , value);
    }
}