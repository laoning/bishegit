/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.types;

import com.biz_integral.foundation.core.types.BizEnum;
import com.biz_integral.foundation.core.types.EnumUtil;
import com.biz_integral.foundation.core.types.NamedEnumAware;

/**
 * FileKindを表す列挙です。<br/>
 * <p>
 * ファイル出力時の出力形式
 * </p>
 */
@BizEnum(application = "crm", module = "cc")
public enum FileKind implements NamedEnumAware {

    /**
     * CSV出力<br/>
     * <p>
     * CSV出力
     * </p>
     */
    CSV("1"),

    /**
     * EXCEL出力<br/>
     * <p>
     * EXCEL出力
     * </p>
     */
    EXCEL("2"), ;

    /** ファイル出力時の出力形式 */
    private String value;

    /**
     * FileKindを構築します。<br/>
     * 
     * @param value
     *            ファイル出力時の出力形式
     */
    private FileKind(String value) {
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
     * ファイル出力時の出力形式に対応する{@link FileKind }インスタンスを返却します。
     * </p>
     * 
     * <p>
     * 対応するファイル出力時の出力形式の列挙定数が定義されていない場合には{@code null}を返却します。
     * </p>
     * 
     * @param value
     *            ファイル出力時の出力形式
     * @return 対応するファイル出力時の出力形式の列挙定数。 ファイル出力時の出力形式に対応する定数が定義されていない場合には{@code
     *         null}。
     */
    public static FileKind getEnum(String value) {
        return EnumUtil.toNamedEnum(FileKind.class, value);
    }
}
