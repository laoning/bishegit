/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * InformContentTypeを表す列挙です。<br/>
 */
@BizEnum(application = "crm", module = "cc")
public enum InformContentType implements NamedEnumAware {

    /**
     * 有<br/>
     * <p>
     * 有
     * </p>
     */
    EXISTENCE("1"),

    /**
     * 無<br/>
     * <p>
     * 無
     * </p>
     */
    NONEXISTENCE("2"), ;

    /** 区分値 */
    private String value;

    /**
     * InformcontentTypeを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private InformContentType(String value) {
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
     * 区分値に対応する{@link InformContentType }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する区分値の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            区分値
     * @return 対応する区分値の列挙定数。 区分値に対応する定数が定義されていない場合には{@code null}。
     */
    public static InformContentType getEnum(String value) {
        return EnumUtil.toNamedEnum(InformContentType.class, value);
    }
}