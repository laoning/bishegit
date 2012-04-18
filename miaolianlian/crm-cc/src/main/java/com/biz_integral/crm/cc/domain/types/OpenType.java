/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * OpenTypeを表す列挙です。<br/>
 * <p>
 * 公開を判断する
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum OpenType implements NamedEnumAware {
    /**
     * 未公開<br/>
     */
    UNOPEN("0"),

    /**
     * 公開
     */
    OPEN("1");

    /** 公開 */
    private String value;

    /**
     * OpenTypeを構築します。<br/>
     * 
     * @param value
     *            公開
     */
    private OpenType(String value) {
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
     * 公開に対応する{@link OpenType }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する公開の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            公開
     * @return 対応する公開の列挙定数。 公開に対応する定数が定義されていない場合には{@code null}。
     */
    public static OpenType getEnum(String value) {
        return EnumUtil.toNamedEnum(OpenType.class, value);
    }
}
