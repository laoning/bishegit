/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * CrmAccountTypeを表す列挙です。<br/>
 * <p>
 * 区分を判断する
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum CrmAccountType implements NamedEnumAware {

    /**
     * 大<br/>
     * <p>
     * 大
     * </p>
     */
    LARGE("1"),

    /**
     * 中<br/>
     * <p>
     * 中
     * </p>
     */
    MEDIUM("2"),

    /**
     * 小<br/>
     * <p>
     * 小
     * </p>
     */
    SMALL("3"), ;

    /** 区分値 */
    private String value;

    /**
     * UseTypeを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private CrmAccountType(String value) {
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
     * 区分値に対応する{@link CrmAccountType }インスタンスを返却します。
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
    public static CrmAccountType getEnum(String value) {
        return EnumUtil.toNamedEnum(CrmAccountType.class, value);
    }
}