/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * SummaryClassを表す列挙です。<br/>
 * <p>
 * 重要度
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum SummaryClass implements NamedEnumAware {

    /**
     * 集計<br/>
     * <p>
     * 
     * </p>
     */
    SUMMARY("1"),

    /**
     * 品目<br/>
     * <p>
     * 
     * </p>
     */
    ITEM_CATEGORY("2"),

    /**
     * アカウント<br/>
     * <p>
     * 
     * </p>
     */
    CRM_ACCOUNT("3"),

    /**
     * 担当者<br/>
     * <p>
     * 
     * </p>
     */
    CHARGE_USER("4");

    /** 区分値 */
    private String value;

    /**
     * ImportantlevelTypeを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private SummaryClass(String value) {
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
     * 区分値に対応する{@link SummaryClass }インスタンスを返却します。
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
    public static SummaryClass getEnum(String value) {
        return EnumUtil.toNamedEnum(SummaryClass.class, value);
    }
}
