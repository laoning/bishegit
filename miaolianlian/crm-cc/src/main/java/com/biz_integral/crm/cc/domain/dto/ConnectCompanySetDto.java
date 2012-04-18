/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dto;

import java.util.Date;

import com.biz_integral.crm.cc.domain.entity.AbstractCrmCcDepartmentCmn;

/**
 * 接続会社設定用モデルです。
 * 
 */
public final class ConnectCompanySetDto extends AbstractCrmCcDepartmentCmn {

    /**
     * デフォルト・シリアルバージョンID
     */
    private static final long serialVersionUID = -4396598225274228216L;

    /** 会社コード */
    /** ロケールID */
    /** CRM用組織セットコード */

    /** ユーザコード */
    public String userCd;

    /** システム日付 */
    public Date sysDate;

}
