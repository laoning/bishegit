/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * SexTypeを表す列挙です。<br/>
 * <p>
 * 性別を判断する
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum SexType implements NamedEnumAware {

    /**
     * 男<br/>
     */
    MALE("0"),

    /**
     * 女
     */
    FEMALE("1");

    private String value;

    /**
     * {@inheritDoc}
     */
    @Override
    public String value() {
        return value;
    }

    /**
     * SexTypeを構築します。<br/>
     * 
     * @param value
     *            性別
     */
    private SexType(String value) {
        this.value = value;
    }

    /**
     * <p>
     * 性別に対応する{@link SexType }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する性別の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            性別
     * @return 対応する性別の列挙定数。 性別に対応する定数が定義されていない場合には{@code null}。
     */
    public static SexType getEnum(String value) {
        return EnumUtil.toNamedEnum(SexType.class, value);
    }

}
