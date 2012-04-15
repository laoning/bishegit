/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * ConfirmWayを表す列挙です。<br/>
 * <p>
 * 確認方法
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum ConfirmWay implements NamedEnumAware {
    /**
     * すぐに確認する<br/>
     * <p>
     * すぐに確認する
     * </p>
     */
    IMMEDIATELY_CONFIRM("1"),

    /**
     * あとで確認する<br/>
     * <p>
     * あとで確認する
     * </p>
     */
    LATER_CONFIRM("2"), ;

    /** 確認方法 */
    private String value;

    /**
     * ConfirmWayを構築します。<br/>
     * 
     * @param value
     *            確認方法
     */
    private ConfirmWay(String value) {
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
     * 確認方法に対応する{@link ConfirmWay }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する確認方法の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            確認方法
     * @return 対応する確認方法の列挙定数。 確認方法に対応する定数が定義されていない場合には{@code null}。
     */
    public static ConfirmWay getEnum(String value) {
        return EnumUtil.toNamedEnum(ConfirmWay.class, value);
    }
}
