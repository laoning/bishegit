/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.NamedEnumAware;

import com.biz_integral.foundation.core.types.EnumUtil;

/**
 * パラメータ分類を表す列挙です。<br/>
 * <p>
 * 初期設定グリッドと表示・コントロールグリッドに表示するパラメータを分類するための区分
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum ParameterCtg implements NamedEnumAware {

    /**
     * 初期設定<br/>
     * <p>
     * 初期設定
     * </p>
     */
    DEFAULTDISPLAY("0"),

    /**
     * 表示・コントロール<br/>
     * <p>
     * 表示・コントロール
     * </p>
     */
    DISPLAYANDCONTROL("1"),
    ;

    /** 区分値 */
    private String value;

    /**
     * ParameterCtgを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private ParameterCtg(String value) {
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
     * 区分値に対応する{@link ParameterCtg }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する区分値の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * @param value 区分値
     * @return 対応する区分値の列挙定数。
     *         区分値に対応する定数が定義されていない場合には{@code null}。
     */
    public static ParameterCtg getEnum(String value) {
        return EnumUtil.toNamedEnum(ParameterCtg.class , value);
    }
}