/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * DealClassを表す列挙です。<br/>
 * <p>
 * 取引種別を判断する
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum DealClass implements NamedEnumAware {

    /**
     * 直接<br/>
     */
    DIRECT("1"),

    /**
     * 間接<br/>>
     */
    INDIRECT("2");

    private String value;

    /**
     * {@inheritDoc}
     */
    @Override
    public String value() {
        return value;
    }

    /**
     * DealClassを構築します。<br/>
     * 
     * @param value
     *            取引種別
     */
    private DealClass(String value) {
        this.value = value;
    }

    /**
     * <p>
     * 取引種別に対応する{@link DealClass }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する取引種別の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            取引種別
     * @return 対応する取引種別の列挙定数。 取引種別に対応する定数が定義されていない場合には{@code null}。
     */
    public static DealClass getEnum(String value) {
        return EnumUtil.toNamedEnum(DealClass.class, value);
    }

}
