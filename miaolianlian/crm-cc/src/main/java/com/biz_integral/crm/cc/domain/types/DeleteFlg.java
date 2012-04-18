/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * DeleteFlgを表す列挙です。<br/>
 * <p>
 * 削除フラグ
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum DeleteFlg implements NamedEnumAware {

    /**
     * 活動中<br/>
     */
    ACTIVE("0"),

    /**
     * 非活動<br/>
     */
    INACTIVE("1");

    /** 削除フラグ値 */
    private String value;

    /**
     * DeleteFlgを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private DeleteFlg(String value) {
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
     * 削除フラグに対応する{@link DeleteFlg }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する削除フラグの列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            削除フラグ
     * @return 対応する削除フラグの列挙定数。 削除フラグに対応する定数が定義されていない場合には{@code null}。
     */
    public static DeleteFlg getEnum(String value) {
        return EnumUtil.toNamedEnum(DeleteFlg.class, value);
    }
}
