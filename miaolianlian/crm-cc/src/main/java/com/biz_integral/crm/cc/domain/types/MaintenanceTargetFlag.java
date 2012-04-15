/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * 画面メンテ対象フラグを表す列挙です。<br/>
 */
@BizEnum(application = "crm", module = "cc")
public enum MaintenanceTargetFlag implements NamedEnumAware {

    /**
     * 対象外<br/>
     */
    NOTTARGET("0"),

    /**
     * 対象<br/>
     */
    TARGET("1"), ;

    /** 区分値 */
    private String value;

    /**
     * MaintenanceTargetFlagを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private MaintenanceTargetFlag(String value) {
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
     * 区分値に対応する{@link MaintenanceTargetFlag }インスタンスを返却します。
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
    public static MaintenanceTargetFlag getEnum(String value) {
        return EnumUtil.toNamedEnum(MaintenanceTargetFlag.class, value);
    }
}