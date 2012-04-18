/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * ImportantLevelTypeを表す列挙です。<br/>
 * <p>
 * 重要度を判断する
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum ImportantLevelType implements NamedEnumAware {

    /**
     * 5<br/>
     * <p>
     * 5
     * </p>
     */
    HIGH("1"),

    /**
     * 4<br/>
     * <p>
     * 4
     * </p>
     */
    MIDDLE_HIGH("2"),

    /**
     * 3<br/>
     * <p>
     * 3
     * </p>
     */
    MIDDLE("3"),

    /**
     * 2<br/>
     * <p>
     * 2
     * </p>
     */
    MIDDLE_LOW("4"),

    /**
     * 1<br/>
     * <p>
     * 1
     * </p>
     */
    LOW("5"), ;

    /** 重要度値 */
    private String value;

    /**
     * UseTypeを構築します。<br/>
     * 
     * @param value
     *            重要度値
     */
    private ImportantLevelType(String value) {
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
     * 重要度値に対応する{@link ImportantLevelType }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する重要度値の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            重要度値
     * @return 対応する重要度値の列挙定数。 重要度値に対応する定数が定義されていない場合には{@code null}。
     */
    public static ImportantLevelType getEnum(String value) {
        return EnumUtil.toNamedEnum(ImportantLevelType.class, value);
    }
}