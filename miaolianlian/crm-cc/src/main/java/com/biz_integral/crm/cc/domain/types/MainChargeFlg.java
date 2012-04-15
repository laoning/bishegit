/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * MainChargeFlgを表す列挙です。<br/>
 * <p>
 * 主担当の実行区分
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum MainChargeFlg implements NamedEnumAware {

    /**
     * 空欄<br/>
     */
    NONMAIN("0"),

    /**
     * 主<br/>
     */
    MAIN("1");

    /** 主担当 */
    private String value;

    /**
     * MainChargeFlgを構築します。<br/>
     * 
     * @param value
     *            主担当
     */
    private MainChargeFlg(String value) {
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
     * 主担当に対応する{@link MainChargeFlg }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する主担当の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            主担当
     * @return 対応する主担当の列挙定数。 主担当に対応する定数が定義されていない場合には{@code null}。
     */
    public static MainChargeFlg getEnum(String value) {
        return EnumUtil.toNamedEnum(MainChargeFlg.class, value);
    }

}
