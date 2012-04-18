/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.NamedEnumAware;

import com.biz_integral.foundation.core.types.EnumUtil;

/**
 * IF更新方法を表す列挙です。<br/>
 */
@BizEnum(application = "crm", module = "cc")
public enum IfUpdateWay implements NamedEnumAware {

    /**
     * 全洗換<br/>
     */
    ALLUPDATE("1"),

    /**
     * 部分更新<br/>
     */
    PARTIALUPDATE("2"),
    ;

    /** 区分値 */
    private String value;

    /**
     * IfUpdateWayを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private IfUpdateWay(String value) {
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
     * 区分値に対応する{@link IfUpdateWay }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する区分値の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * @param value 区分値
     * @return 対応する区分値の列挙定数。
     *         区分値に対応する定数が定義されていない場合には{@code null}。
     */
    public static IfUpdateWay getEnum(String value) {
        return EnumUtil.toNamedEnum(IfUpdateWay.class , value);
    }
}