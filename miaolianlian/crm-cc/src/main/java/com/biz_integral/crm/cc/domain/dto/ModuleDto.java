/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */

package com.biz_integral.crm.cc.domain.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * モジュール設定モデルです。
 */
public final class ModuleDto {

    /**
     * 選択済モジュールグラグ
     */
    public String selected;

    /**
     * モジュール名ID
     */
    public String moduleId;

    /**
     * モジュール名
     */
    public String moduleName;

    /**
     * バージョン番号
     */
    public String versionNo;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
