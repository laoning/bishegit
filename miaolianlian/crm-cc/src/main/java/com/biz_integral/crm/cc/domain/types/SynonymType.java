/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * 類義語区分です。<br/>
 */
@BizEnum(application = "crm", module = "cc")
public enum SynonymType implements NamedEnumAware {

    /**
     * ACCOUNT<br/>
     * <p>
     * アカウント
     * </p>
     */
    ACCOUNT("0"),

    /**
     * CONTACT<br/>
     * <p>
     * コンタクト
     * </p>
     */
    CONTACT("1"),

    /**
     * PROPOSAL<br/>
     * <p>
     * 案件
     * </p>
     */
    PROPOSAL("2");

    /** 区分値 */
    private String value;

    /**
     * SynonymTypeを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private SynonymType(String value) {
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
     * 区分値に対応する{@link SynonymType }インスタンスを返却します。
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
    public static SynonymType getEnum(String value) {
        return EnumUtil.toNamedEnum(SynonymType.class, value);
    }
}
