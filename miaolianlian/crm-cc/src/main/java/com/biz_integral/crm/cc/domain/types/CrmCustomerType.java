/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * CrmCustomerTypeを表す列挙です。<br/>
 * <p>
 * CRM顧客区分を判断する
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum CrmCustomerType implements NamedEnumAware {

    /**
     * アカウント<br/>
     * <p>
     * アカウント
     * </p>
     */
    ACCOUNT("1"),

    /**
     * アカウント - コンタクト<br/>
     * <p>
     * アカウント - コンタクト
     * </p>
     */
    ACCOUNTCONTACT("2"), ;

    /** 区分値 */
    private String value;

    /**
     * CrmCustomerTypeを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private CrmCustomerType(String value) {
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
     * 区分値に対応する{@link CrmCustomerType }インスタンスを返却します。
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
    public static CrmCustomerType getEnum(String value) {
        return EnumUtil.toNamedEnum(CrmCustomerType.class, value);
    }
}