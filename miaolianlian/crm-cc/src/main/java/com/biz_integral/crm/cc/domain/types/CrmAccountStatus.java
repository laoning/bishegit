/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * CrmAccountStatusを表す列挙です。<br/>
 * <p>
 * 状況を判断する
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum CrmAccountStatus implements NamedEnumAware {

    /**
     * 仮登録<br/>
     * <p>
     * 仮登録
     * </p>
     */
    TEMPENTRY("1"),

    /**
     * 見込み客<br/>
     * <p>
     * 見込み客
     * </p>
     */
    CONTEMPLATIONCUSTOMER("2"),

    /**
     * 取引中<br/>
     * <p>
     * 取引中
     * </p>
     */
    TRADING("3"),

    /**
     * 取引停止<br/>
     * <p>
     * 取引停止
     * </p>
     */
    TRADINGSTOP("4"),

    /**
     * 重複末梢<br/>
     * <p>
     * 重複末梢
     * </p>
     */
    DUPLICATIONCANCEL("5"), ;

    /** 状況値 */
    private String value;

    /**
     * UseTypeを構築します。<br/>
     * 
     * @param value
     *            状況値
     */
    private CrmAccountStatus(String value) {
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
     * 状況値に対応する{@link CrmAccountStatus }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する状況値の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            状況値
     * @return 対応する状況値の列挙定数。 状況値に対応する定数が定義されていない場合には{@code null}。
     */
    public static CrmAccountStatus getEnum(String value) {
        return EnumUtil.toNamedEnum(CrmAccountStatus.class, value);
    }
}