/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * CampaignStatusTypeを表す列挙です。<br/>
 * <p>
 * キャンペーンの実行区分
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum CampaignStatusType implements NamedEnumAware {

    /**
     * 未実施<br/>
     */
    BEFORE("0"),

    /**
     * 実施中<br/>
     */
    UNDERWAY("1"),

    /**
     * 完了<br/>
     */
    COMPLETE("2");

    /** 区分値 */
    private String value;

    /**
     * CampaignstatusTypeを構築します。<br/>
     * 
     * @param value
     *            区分値
     */
    private CampaignStatusType(String value) {
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
     * 区分値に対応する{@link CampaignstatusType }インスタンスを返却します。
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
    public static CampaignStatusType getEnum(String value) {
        return EnumUtil.toNamedEnum(CampaignStatusType.class, value);
    }
}