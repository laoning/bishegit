/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * CompetitionTypeを表す列挙です。<br/>
 * <p>
 * 競合度を判断する
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum CompetitionType implements NamedEnumAware {

    /**
     * A<br/>
     */
    A("1"),

    /**
     * B<br/>
     */
    B("2"),

    /**
     * C
     */
    C("3");

    /**
     * 競合度
     */
    private String value;

    /**
     * {@inheritDoc}
     */
    @Override
    public String value() {
        return value;
    }

    /**
     * CompetitionTypeeを構築します。<br/>
     * 
     * @param value
     *            競合度
     */
    private CompetitionType(String value) {
        this.value = value;
    }

    /**
     * <p>
     * 競合度に対応する{@link SexType }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する競合度の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            競合度
     * @return 対応する競合度の列挙定数。 競合度に対応する定数が定義されていない場合には{@code null}。
     */
    public static CompetitionType getEnum(String value) {
        return EnumUtil.toNamedEnum(CompetitionType.class, value);
    }

}
