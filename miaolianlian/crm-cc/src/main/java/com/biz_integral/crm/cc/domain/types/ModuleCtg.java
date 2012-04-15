/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.NamedEnumAware;

import com.biz_integral.foundation.core.types.EnumUtil;

/**
 * モジュール分類を表す列挙です。<br/>
 * <p>
 * モジュールの種類を表す区分
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum ModuleCtg implements NamedEnumAware {

    /**
     * CRMコア<br/>
     * <p>
     * CRMコア
     * </p>
     */
    CRMCORE("CC"),

    /**
     * 活動計画・実績<br/>
     * <p>
     * 活動計画・実績
     * </p>
     */
    SALESACTIVITYPLAN("SA"),

    /**
     * キャンペーン管理<br/>
     * <p>
     * キャンペーン管理
     * </p>
     */
    CAMPAIGNMANAGE("SC"),

    /**
     * 案件管理<br/>
     * <p>
     * 案件管理
     * </p>
     */
    PROPOSALMANAGE("PM"),

    /**
     * 販売計画<br/>
     * <p>
     * 販売計画
     * </p>
     */
    SALESPLAN("SP"),

    /**
     * ToDo<br/>
     * <p>
     * ToDo
     * </p>
     */
    TODO("TD"),
    ;

    /** 区分値 */
    private String value;

    /**
     * ModuleCtgを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private ModuleCtg(String value) {
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
     * 区分値に対応する{@link ModuleCtg }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応する区分値の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * @param value 区分値
     * @return 対応する区分値の列挙定数。
     *         区分値に対応する定数が定義されていない場合には{@code null}。
     */
    public static ModuleCtg getEnum(String value) {
        return EnumUtil.toNamedEnum(ModuleCtg.class , value);
    }
}